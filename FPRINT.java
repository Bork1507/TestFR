import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;



public class FPRINT extends FR
{

	private SerialPort serialPort;
	private int _gettedBytes=0; 
	
	//private boolean _wrileLog=true;
	private boolean _wrileLog=false;

	private String ReceiptType="";

      private ArrayOfBytes bEOT = new ArrayOfBytes();
	private ArrayOfBytes bENQ = new ArrayOfBytes();
	private ArrayOfBytes bACK = new ArrayOfBytes();
	private ArrayOfBytes bNAK = new ArrayOfBytes();



	public FPRINT()
	{
            bEOT.append(0x04);
		bENQ.append(0x05);
		bACK.append(0x06);
		bNAK.append(0x15);
	}


	private ArrayOfBytes turnString(ArrayOfBytes str)
	{
		if (_wrileLog) Log("getByteArrayFromString");

		ArrayOfBytes out=new ArrayOfBytes();
		for(int i=str.length()-1;i>-1;i--)
		{
			out.append(str.at(i));
		}
		return out;
	}

	private ArrayOfBytes getByteArrayFromString(String str)
	{
		if (_wrileLog) Log("getByteArrayFromString");

		ArrayOfBytes strOut= new ArrayOfBytes();
		if ((str.length()%2)>0) str="0"+str;


		for(int i=0;i<str.length()-1;i+=2)
		{
			strOut.append(Integer.parseInt(str.substring(i, (i + 2)), 16));
		}

		return strOut;
	}

      private ArrayOfBytes ToAtolCodePage(String str)
      {
            if (_wrileLog) Log("ToAtolCodePage");

            ArrayOfBytes strOut= new ArrayOfBytes();
            strOut.append(str, "cp866");
            //strOut.append("№$€", "cp866");


            for(int i=0;i<strOut.length();i++)
            {
                  switch(strOut.at(i))
                  {
                        case (byte)0x24: strOut.set(i, 0xFC); break; 
                        case (byte)0xFC: strOut.set(i, 0x24); break;
                        case (byte)0x3F: strOut.set(i, 0xF2); break;
                  }
            }

            return strOut;
      }

	private ArrayOfBytes curDate()
	{
		String strDate;
		
		Date dt= new Date();
		strDate = new SimpleDateFormat("ddMMyy").format(dt);
		
		return getByteArrayFromString(strDate);
	}

	private ArrayOfBytes curTime()
	{
		String strTime;

		Date dt= new Date();
		strTime = new SimpleDateFormat("HHmmss").format(dt);

		return getByteArrayFromString(strTime);
	}

	private ArrayOfBytes CRC(ArrayOfBytes in)
	{
		ArrayOfBytes out=new ArrayOfBytes();
		out.append((byte)(0x02));

		byte res=0;
		for(int i=0; i<in.length(); i++)
		{
                  switch (in.at(i))
                  {
                        case 0x03:
                        case 0x10:
                              out.append(0x10);
                              out.append(in.at(i));
                              res=(byte)(res^0x10);
                              break;
                        default:
                              out.append(in.at(i));
                              break;                              
                  }
			res=(byte)(res^in.at(i));
		}
            res=(byte)(res^0x03);
            out.append(0x03);
		out.append(res);
		
		return out;
	}

      private ArrayOfBytes delEscapeCharacter(ArrayOfBytes in)
      {
            ArrayOfBytes out=new ArrayOfBytes();
            int i=0;
            for (;i<in.length()-1;i++)
            {
                  if(in.at(i)==0x0A)
                  {
                        if ((in.at(i+1)!=0x0A) && (in.at(i+1)!=0x03)) out.append(in.at(i));
                  }
                  else  out.append(in.at(i));
            }
            out.append(in.at(i));

            if (_wrileLog)
            {
                  String strLog="delEscapeCharacter == ";
                  for (int j=0;j<out.length();j++) strLog+=String.format("%02x", out.at(j));
                  Log(strLog);
            }


            return out;
      }




	public static String getErrorDetails(int error)	
	{
		String str="";

            switch (error)
            {
                  case 0:
                  //ShowMessage("Ошибка 00h  Ошибок нет");
                  break;
                  case 1:    str="Ошибка 01h  Контрольная лента обработана без ошибок"; break;
                  case 8:    str="Ошибка 08h  Неверная цена (сумма)"; break;
                  case 10:   str="Ошибка 0Ah  Неверное количество"; break;
                  case 11:   str="Ошибка 0Bh  Переполнение счетчика наличности"; break;
                  case 12:   str="Ошибка 0Ch  Невозможно сторно последней операции"; break;
                  case 13:   str="Ошибка 0Dh  Сторно по коду невозможно (в чеке зарегистрировано меньшее количество товаров с указанным кодом)"; break;
                  case 14:   str="Ошибка 0Eh  Невозможен повтор последней операции"; break;
                  case 15:   str="Ошибка 0Fh  Повторная скидка на операцию невозможна"; break;
                  case 16:   str="Ошибка 10h  Скидка/надбавка на предыдущую операцию невозможна"; break;
                  case 17:   str="Ошибка 11h  Неверный код товара"; break;
                  case 18:   str="Ошибка 12h  Неверный штрихкод товара"; break;
                  case 19:   str="Ошибка 13h  Неверный формат"; break;
                  case 20:   str="Ошибка 14h  Неверная длина"; break;
                  case 21:   str="Ошибка 15h  ККТ заблокирована в режиме ввода даты"; break;
                  case 22:   str="Ошибка 16h  Требуется подтверждение ввода даты"; break;
                  case 24:   str="Ошибка 18h  Нет больше данных для передачи ПО ККТ"; break;
                  case 25:   str="Ошибка 19h  Нет подтверждения или отмены продажи"; break;
                  case 26:   str="Ошибка 1Ah  Отчет с гашением прерван. Вход в режим невозможен."; break;
                  case 27:   str="Ошибка 1Bh  Отключение контроля наличности невозможно (не настроены необходимые типы оплаты)."; break;
                  case 30:   str="Ошибка 1Eh  Вход в режим заблокирован"; break;
                  case 31:   str="Ошибка 1Fh  Проверьте дату и время"; break;
                  case 32:   str="Ошибка 20h  Дата и время в ККТ меньше чем в ЭКЛЗ/ФП"; break;
                  case 33:   str="Ошибка 21h  Невозможно закрыть архив"; break;
                  case 61:   str="Ошибка 3Dh  Товар не найден"; break;
                  case 62:   str="Ошибка 3Eh  Весовой штрихкод с количеством <>1.000"; break;
                  case 63:   str="Ошибка 3Fh  Переполнение буфера чека"; break;
                  case 64:   str="Ошибка 40h  Недостаточное количество товара"; break;
                  case 65:   str="Ошибка 41h  Сторнируемое количество больше проданного"; break;
                  case 66:   str="Ошибка 42h  Заблокированный товар не найден в буфере чека"; break;
                  case 67:   str="Ошибка 43h  Данный товар не продавался в чеке, сторно невозможно"; break;
                  case 68:   str="Ошибка 44h  Memo Plus 3 заблокировано с ПК"; break;
                  case 69:   str="Ошибка 45h  Ошибка контрольной суммы таблицы настроек Memo Plus 3"; break;
                  case 70:   str="Ошибка 46h  Неверная команда от ККТ"; break;
                  case 102:  str="Ошибка 66h  Команда не реализуется в данном режиме ККТ"; break;
                  case 103:  str="Ошибка 67h  Нет бумаги"; break;
                  case 104:  str="Ошибка 68h  Нет связи с принтером чеков"; break;
                  case 105:  str="Ошибка 69h  Механическая ошибка печатающего устройства"; break;
                  case 106:  str="Ошибка 6Ah  Неверный тип чека"; break;
                  case 107:  str="Ошибка 6Bh  Нет больше строк картинки"; break;
                  case 108:  str="Ошибка 6Ch  Неверный номер регистра"; break;
                  case 109:  str="Ошибка 6Dh  Недопустимое целевое устройство"; break;
                  case 110:  str="Ошибка 6Eh  Нет места в массиве картинок"; break;
                  case 111:  str="Ошибка 6Fh  Неверный номер картинки / картинка отсутствует"; break;
                  case 112:  str="Ошибка 70h  Сумма сторно больше, чем было получено данным типом оплаты"; break;
                  case 113:  str="Ошибка 71h  Сумма не наличных платежей превышает сумму чека "; break;
                  case 114:  str="Ошибка 72h  Сумма платежей меньше суммы чека"; break;
                  case 115:  str="Ошибка 73h  Накопление меньше суммы возврата или аннулирования"; break;
                  case 117:  str="Ошибка 75h  Переполнение суммы платежей"; break;
                  case 118:  str="Ошибка 76h  (зарезервировано)"; break;
                  case 122:  str="Ошибка 7Ah  Данная модель ККТ не может выполнить команду"; break;
                  case 123:  str="Ошибка 7Bh  Неверная величина скидки / надбавки"; break;
                  case 124:  str="Ошибка 7Ch  Операция после скидки / надбавки невозможна"; break;
                  case 125:  str="Ошибка 7Dh  Неверная секция"; break;
                  case 126:  str="Ошибка 7Eh  Неверный вид оплаты"; break;
                  case 127:  str="Ошибка 7Fh  Переполнение при умножении"; break;
                  case 128:  str="Ошибка 80h  Операция запрещена в таблице настроек"; break;
                  case 129:  str="Ошибка 81h  Переполнение итога чека"; break;
                  case 130:  str="Ошибка 82h  Открыт чек аннулирования – операция невозможна"; break;
                  case 132:  str="Ошибка 84h  Переполнение буфера контрольной ленты"; break;
                  case 134:  str="Ошибка 86h  Вносимая клиентом сумма меньше суммы чека"; break;
                  case 135:  str="Ошибка 87h  Открыт чек возврата – операция невозможна"; break;
                  case 136:  str="Ошибка 88h  Смена превысила 24 часа"; break;
                  case 137:  str="Ошибка 89h  Открыт чек продажи – операция невозможна"; break;
                  case 138:  str="Ошибка 8Ah  Переполнение ФП"; break;
                  case 140:  str="Ошибка 8Ch  Неверный пароль"; break;
                  case 141:  str="Ошибка 8Dh  Буфер контрольной ленты не переполнен"; break;
                  case 142:  str="Ошибка 8Eh  Идет обработка контрольной ленты"; break;
                  case 143:  str="Ошибка 8Fh  Обнуленная касса (повторное гашение невозможно)"; break;
                  case 145:  str="Ошибка 91h  Неверный номер таблицы"; break;
                  case 146:  str="Ошибка 92h  Неверный номер ряда"; break;
                  case 147:  str="Ошибка 93h  Неверный номер поля"; break;
                  case 148:  str="Ошибка 94h  Неверная дата"; break;
                  case 149:  str="Ошибка 95h  Неверное время "; break;
                  case 150:  str="Ошибка 96h  Сумма чека по секции меньше суммы сторно"; break;
                  case 151:  str="Ошибка 97h  Подсчет суммы сдачи невозможен"; break;
                  case 152:  str="Ошибка 98h  В ККТ нет денег для выплаты"; break;
                  case 154:  str="Ошибка 9Ah  Чек закрыт – операция невозможна"; break;
                  case 155:  str="Ошибка 9Bh  Чек открыт – операция невозможна"; break;
                  case 156:  str="Ошибка 9Ch  Смена открыта, операция невозможна"; break;
                  case 157:  str="Ошибка 9Dh  ККТ заблокирована, ждет ввода пароля доступа к ФП"; break;
                  case 158:  str="Ошибка 9Eh  Заводской номер уже задан"; break;
                  case 159:  str="Ошибка 9Fh  Исчерпан лимит перерегистраций"; break;
                  case 160:  str="Ошибка A0h  Ошибка ФП"; break;
                  case 162:  str="Ошибка A2h  Неверный номер смены"; break;
                  case 163:  str="Ошибка A3h  Неверный тип отчета"; break;
                  case 164:  str="Ошибка A4h  Недопустимый пароль"; break;
                  case 165:  str="Ошибка A5h  Недопустимый заводской номер ККТ"; break;
                  case 166:  str="Ошибка A6h  Недопустимый РНМ"; break;
                  case 167:  str="Ошибка A7h  Недопустимый ИНН"; break;
                  case 168:  str="Ошибка A8h  ККТ не фискализирована"; break;
                  case 169:  str="Ошибка A9h  Не задан заводской номер"; break;
                  case 170:  str="Ошибка AAh  Нет отчетов"; break;
                  case 171:  str="Ошибка ABh  Режим не активизирован"; break;
                  case 172:  str="Ошибка ACh  Нет указанного чека в КЛ"; break;
                  case 173:  str="Ошибка ADh  Нет больше записей КЛ"; break;
                  case 174:  str="Ошибка AEh  Некорректный код или номер кода защиты ККТ"; break;
                  case 176:  str="Ошибка B0h  Требуется выполнение общего гашения "; break;
                  case 177:  str="Ошибка B1h  Команда не разрешена введенными кодами защиты ККТ"; break;
                  case 178:  str="Ошибка B2h  Невозможна отмена скидки/надбавки"; break;
                  case 179:  str="Ошибка B3h  Невозможно закрыть чек данным типом оплаты (в чеке присутствуют операции без контроля наличных)"; break;
                  case 180:  str="Ошибка B4h  Неверный номер маршрута"; break;
                  case 181:  str="Ошибка B5h  Неверный номер начальной зоны"; break;
                  case 182:  str="Ошибка B6h  Неверный номер конечной зоны"; break;
                  case 183:  str="Ошибка B7h  Неверный тип тарифа"; break;
                  case 184:  str="Ошибка B8h  Неверный тариф"; break;
                  case 186:  str="Ошибка BAh  Ошибка обмена с фискальным модулем"; break;
                  case 190:  str="Ошибка BЕh  Необходимо провести профилактические работы"; break;
                  case 191:  str="Ошибка BFh  Неверные номера смен в ККТ и ЭКЛЗ"; break;
                  case 200:  str="Ошибка C8h  Нет устройства, обрабатывающего данную команду"; break;
                  case 201:  str="Ошибка C9h  Нет связи с внешним устройством"; break;
                  case 202:  str="Ошибка CAh  Ошибочное состояние ТРК"; break;
                  case 203:  str="Ошибка CBh  Больше одной регистрации в чеке"; break;
                  case 204:  str="Ошибка CСh  Ошибочный номер ТРК"; break;
                  case 205:  str="Ошибка CDh  Неверный делитель"; break;
                  case 207:  str="Ошибка CFh  Исчерпан лимит активизаций"; break;
                  case 208:  str="Ошибка D0h  Активизация данной ЭКЛЗ в составе данной ККТ невозможна"; break;
                  case 209:  str="Ошибка D1h  Перегрев головки принтера"; break;
                  case 210:  str="Ошибка D2h  Ошибка обмена с ЭКЛЗ на уровне интерфейса I2C"; break;
                  case 211:  str="Ошибка D3h  Ошибка формата передачи ЭКЛЗ"; break;
                  case 212:  str="Ошибка D4h  Неверное состояние ЭКЛЗ"; break;
                  case 213:  str="Ошибка D5h  Неисправимая ошибка ЭКЛЗ"; break;
                  case 214:  str="Ошибка D6h  Авария крипто-процессора ЭКЛЗ"; break;
                  case 215:  str="Ошибка D7h  Исчерпан временной ресурс ЭКЛЗ"; break;
                  case 216:  str="Ошибка D8h  ЭКЛЗ переполнена"; break;
                  case 217:  str="Ошибка D9h  В ЭКЛЗ переданы неверная дата или время"; break;
                  case 218:  str="Ошибка DAh  В ЭКЛЗ нет запрошенных данных"; break;
                  case 219:  str="Ошибка DBh  Переполнение ЭКЛЗ (итог чека)"; break;
                  case 220:  str="Ошибка DCh  Буфер переполнен"; break;
                  case 221:  str="Ошибка DDh  Невозможно напечатать вторую фискальную копию"; break;
                  case 222:  str="Ошибка DEh  Требуется гашение ЭЖ"; break;
                  case 223:  str="Ошибка DFh  Сумма налога больше суммы регистраций по чеку и/или итога или больше суммы регистрации"; break;
                  case 224:  str="Ошибка E0h  Начисление налога на последнюю операцию невозможно"; break;
                  case 225:  str="Ошибка E1h  Неверный номер ЭКЛЗ"; break;
                  case 228:  str="Ошибка E4h  Сумма сторно налога больше суммы зарегистрированного налога данного типа"; break;
                  case 229:  str="Ошибка E5h  Ошибка SD"; break;
                  case 230:  str="Ошибка E6h  Операция невозможна, недостаточно питания"; break;

                  default:
                        str=FR.getErrorDetails(error);
                        break;   
            }
	    return str;
	}


    public void openPort(String portName, String baud) throws FrException
    {
		if (_wrileLog) Log("openPort");

		//Передаём в конструктор имя порта

		//serialPort = new SerialPort("/dev/ttyS0");
		serialPort = new SerialPort(portName);

		try {
		    //Открываем порт
		    serialPort.openPort();
		    //Выставляем параметры
		    serialPort.setParams(Integer.parseInt(baud),
			                     SerialPort.DATABITS_8,
			                     SerialPort.STOPBITS_1,
			                     SerialPort.PARITY_NONE);
		    
		    //Включаем аппаратное управление потоком
		    //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
			 //                             SerialPort.FLOWCONTROL_RTSCTS_OUT);

		    //Устанавливаем ивент лисенер и маску
		    serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
    }

	private boolean writePort(ArrayOfBytes toPort)
	{
		if (_wrileLog) Log("writePort");
		try {
		    	serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to   port -> ";
		    	for (int i=0;i<toPort.length();i++) strLog+=String.format("%02x", toPort.at(i));
			    Log(strLog);
		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
		return true;

	}


	private boolean readPort(ArrayOfBytes fromPort) //throws InterruptedException 
	{
		if (_wrileLog) Log("readPort");

		fromPort.clear();

		for (int i=0;;i++)
		{
			
			if (_gettedBytes>0)
			{
				try {

				    fromPort.append(serialPort.readBytes());

			    	String strLog="from port <- ";
			    	for (int j=0;j<fromPort.length();j++) strLog+=String.format("%02x", fromPort.at(j));
				    Log(strLog);
				}
				catch (SerialPortException ex) {
				    System.out.println(ex);
				}
				
				_gettedBytes=0;
				break;
			}

			if(i>400)		
			{
				Log("i>20" + i);
				break;
			}

			try {
			  Thread.sleep(10);
			} catch (InterruptedException ie) {
			    //Handle exception
			}	
			
		}
		if (_wrileLog) Log("End of readPort");
		return true;

	}

      private boolean testConnect()
      {
            boolean result=false;
            ArrayOfBytes fromPort = new ArrayOfBytes();

            writePort(bENQ);
            for (int i=0;i<3;i++)
            {
                  readPort(fromPort);
                  if (bACK.equals(fromPort))
                  {
                        result=true;
                        break;
                  }
                  if (bENQ.equals(fromPort))
                  {
                        getAnswer(fromPort);
                        fromPort.clear();
                        writePort(bENQ);                       
                  }


                  try {Thread.sleep(200);} catch (InterruptedException ie) {}             
            }

            return result;

      }


	private int getEndOfPrinting()
	{
		if (_wrileLog) Log("getEndOfPrinting");
		int error=0;

		//ArrayOfBytes state = new ArrayOfBytes();
		ArrayOfBytes toPort = new ArrayOfBytes(0x02, 0x00, 0x00, 0x45, 0x03, 0x46);
		ArrayOfBytes fromPort = new ArrayOfBytes();
		//ArrayOfBytes result = new ArrayOfBytes();
		
		//boolean startByteWasReceived=false;
		boolean endOfPrinting=false;
		//int resultLength=0;
		//int mode=-1;
		//int submode=-1;

		for(int i=0;;i++)
		{

                  error=sendToCom(toPort);
                  if (error==0)
                  {
                        error=getAnswer(fromPort);
                        if((error!=0x17)&&(error!=0x22)&&(error!=0x23)&&
                           (error!=0x25)&&(error!=0x26)&&(error!=0x27)&&
                           (error!=0x32)&&(error!=0x52)&&(error!=0x62))
                        {
                            endOfPrinting=true;
                            error=0;
                            break;
                        }

                  }
                  else break;

			try {Thread.sleep(100);} catch (InterruptedException ie) {}		
		}




		return error;
	}

      private int sendToCom(ArrayOfBytes toPort)
      {

            if (_wrileLog) Log("sendToCom");
            int error=FR.NO_RESPONSE_FR;

            ArrayOfBytes fromPort = new ArrayOfBytes();
            
            if (testConnect())
            {
                  writePort(toPort);

                  for (int k=0;k<3;k++ ) 
                  {
                        // Cycle for receve current status

                        fromPort.clear();
                        readPort(fromPort);
                        
                        if (bACK.equals(fromPort))
                        {
                              writePort(bEOT);
                              error=0;
                              break;

                        }
                        else if (bNAK.equals(fromPort))
                        {
                              error=FR.ERROR_CONNECT;
                              break;

                        }

                        try {Thread.sleep(100);} catch (InterruptedException ie) {}       
                  }                       
            }
            else error=FR.ERROR_CONNECT;

            return error;
      }

      private int getAnswer(ArrayOfBytes result)
      {

            if (_wrileLog) Log("getLastError");
            int error=FR.NO_RESPONSE_FR;
            int mask=0;

            boolean getENQ=false;

            //ArrayOfBytes toPort = new ArrayOfBytes();
            ArrayOfBytes temp = new ArrayOfBytes();
            ArrayOfBytes fromPort = new ArrayOfBytes();
            
            boolean startByteWasReceived=false;
            boolean finishByteWasReceived=false;

            // writePort(bENQ);
            // readPort(state);

            result.clear();

            for (int i=0;i<5;i++)
            {
                  if (!getENQ)
                  {
                        fromPort.clear();
                        readPort(fromPort);
                        if (bENQ.equals(fromPort))
                        {
                              writePort(bACK);
                              getENQ=true;
                        }                      
                  }
                  else// (getENQ)
                  {
                        fromPort.clear();
                        readPort(fromPort);
                        for(int j=0;j<fromPort.length();j++)
                        {
                              if (fromPort.at(j)==(byte)(0x02)) startByteWasReceived=true;
                              if (startByteWasReceived==true)
                              {
                                    temp.append(fromPort.at(j));
                              }
                              if (fromPort.at(j)==(byte)(0x03)) 
                              {
                                    if (mask!=10) finishByteWasReceived=true;
                              }
                              if (fromPort.at(j)==(byte)(0x04))
                              {
                                    break;
                              }
                              if (j>0) mask=fromPort.at(j-1);
                        }
                        if (finishByteWasReceived)
                        {
                              writePort(bACK);
                              
                              result.append(delEscapeCharacter(temp));
                              
                              error=result.at(2);

                              readPort(fromPort);

                              break;
                        }

                        try {Thread.sleep(1000);} catch (InterruptedException ie) {}       
                  }
            }
            


            return error;

      }

      private int transactionWithoutAnswer(ArrayOfBytes toPort)
      {

            if (_wrileLog) Log("transactionWithoutAnswer");
            int error=0;

            if (error==0) error=sendToCom(toPort);
            return error;

      }


	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{

		if (_wrileLog) Log("transaction");
		int error=0;

            if (error==0) error=sendToCom(toPort);
            if (error==0) error=getAnswer(result);
		return error;

	}

      public int exitMode() throws FrException
      {
            if (_wrileLog) Log("exitMode");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x48);
            

            
            if (error==0) error=transaction(CRC(commandStr), getStr);

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;

      }

       public int setMode(int mode, String pass) throws FrException
      {
            if (_wrileLog) Log("setMode");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            pass=rightJustified(pass, '0', 8);


            error=exitMode();

            if (error==0)
            {
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x56);
                  commandStr.append(mode);
                  commandStr.append(getByteArrayFromString(pass));
            }
            if (error==0) error=transaction(CRC(commandStr), getStr);

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;

      }

      public int SetCurrentDate() throws FrException
      {
            if (_wrileLog) Log("SetCurrentDate");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x64);
            commandStr.append(curDate());
                  

            if (error==0) error=transactionWithoutAnswer(CRC(commandStr));

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return error;
      }

      public int SetCurrentTime() throws FrException
      {
            if (_wrileLog) Log("SetCurrentTime");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x4B);
            commandStr.append(curTime());
                  

            if (error==0) error=transactionWithoutAnswer(CRC(commandStr));

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return error;
      }


	public int Init() throws FrException
	{
		if (_wrileLog) Log("Init");
            int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
		commandStr.append(0x47);
      		

           	if (error==0) error=transactionWithoutAnswer(CRC(commandStr));

            if (error==0) error=SetCurrentDate();
            if (error==0) error=SetCurrentTime();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}

	public int OpenDocument(String docType, String depType, String operName, String docNumber) throws FrException
	{
		if (_wrileLog) Log("OpenDocument");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            int docTypeTMP=0;

            switch (docType) 
            {
                  case "ReceiptTypeSale" :
                        docTypeTMP=1;
                        break;
                  case "ReceiptTypeReturnSale" :
                        docTypeTMP=2;
                        break;
                  default: 
                        docTypeTMP=0;
                        break;
            }

            error=setMode(1, "30");

            if (error==0)
            {
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x92);
                  commandStr.append(0x00);
                  commandStr.append(docTypeTMP);
            }

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}


      public int PrintText(String text) throws FrException
      {
            if (_wrileLog) Log("AddItem");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            String textTMP=text;

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x4C);
            commandStr.append(0x02);
            commandStr.append(ToAtolCodePage(textTMP));
            commandStr.append(0x01);

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;
      }


	public int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
	{
		if (_wrileLog) Log("AddItem");
		int error=0;

		// int intReceiptType=0;
		// switch (ReceiptType) 
		// {
		// 	case "ReceiptTypeSale" :
		// 		intReceiptType=0x80;
		// 		break;
		// 	case "ReceiptTypeReturnSale" :
		// 		intReceiptType=0x82;
		// 		break;
		// 	default: 
		// 		intReceiptType=0;
		// 		break;
		// }

            
            if (error==0) error=PrintText(itemName);

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		String qantityTMP=rightJustified(qantity.replace(".", "").replace(",", ""), '0', 10);
		String costTMP=rightJustified(cost.replace(".", "").replace(",", ""), '0', 10);


		commandStr.append(0x00);
		commandStr.append(0x00);
		commandStr.append(0x52);
            commandStr.append(0x02);
            commandStr.append(getByteArrayFromString(costTMP));
		commandStr.append(getByteArrayFromString(qantityTMP));
            commandStr.append(0x01);

	
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int Total() throws FrException
	{
		if (_wrileLog) Log("Total");
            int error=0;

		return error;

	}

	public int Pay(String payType, String sum, String text) throws FrException
	{
		if (_wrileLog) Log("Pay");
            int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

            String sumTMP=rightJustified(sum.replace(".", "").replace(",", ""), '0', 10);
            int payTypeTMP=0;

		String pay1="";
		String pay2="";
		String pay3="";
		String pay4="";

		switch (payType) 
		{
			case "Cash0" :
				payTypeTMP=1;
				break;
			case "Cash1" :
                        payTypeTMP=2;
                        break;
			case "Cash2" :
                        payTypeTMP=3;
                        break;
			case "Cash3" :
                        payTypeTMP=4;
                        break;
			case "Cash4" :
                        payTypeTMP=5;
                        break;
			case "Cash5" :
                        payTypeTMP=6;
                        break;
			case "Cash6" :
                        payTypeTMP=7;
                        break;
			case "Cash7" :
                        payTypeTMP=8;
                        break;
			case "Cash8" :
                        payTypeTMP=9;
                        break;
			case "Cash9" :
                        payTypeTMP=10;
                        break;
			case "Cash10":
			case "Cash11":
			case "Cash12":
			case "Cash13":
			case "Cash14":
			case "Cash15":
			default: 
                        payTypeTMP=0;
                        break;
		}


		commandStr.append(0x00);
		commandStr.append(0x00);
		commandStr.append(0x99);
		commandStr.append(0x00);
		commandStr.append(payTypeTMP);
		commandStr.append(getByteArrayFromString(sumTMP));
		

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int CancelDocument() throws FrException
	{
		if (_wrileLog) Log("CancelDocument");
            int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x00);
		commandStr.append(0x00);
		commandStr.append(0x59);

		
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int CloseDocument(String text) throws FrException
	{
		if (_wrileLog) Log("CloseDocument");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x4A);
            commandStr.append(0x00);
            commandStr.append(0x01);
            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x00);

            
            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return 0;

	}


	public int Xreport(String text) throws FrException
	{
		if (_wrileLog) Log("Xreport");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            error=setMode(2, "30");

            if (error==0)
            {
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x67);
                  commandStr.append(0x01);
            }

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int Zreport(String text) throws FrException
	{
		if (_wrileLog) Log("Zreport");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            error=setMode(3, "30");

            if (error==0)
            {
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x5A);
            }

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int ReceiptSale() throws FrException
	{
		if (_wrileLog) Log("ReceiptSale");
		int error=0;

		// if (error==0) error=OpenDocument("2", "0", "Test", "0");
		// if (error==0) error=AddItem("тест", "1234567", "1.000", "123.45", "0", "");
		// if (error==0) error=Total();
		// if (error==0) error=Pay("0", "1000.00", "");
		// if (error==0) error=CloseDocument("");

		// if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}


      private class PortReader implements SerialPortEventListener 
      {
            public void serialEvent(SerialPortEvent event) 
            {
                  if(event.isRXCHAR() && event.getEventValue() > 0)
                  {
                  	_gettedBytes=event.getEventValue();            
                  }
            }
      }

	
}