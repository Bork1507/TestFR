import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

//import org.apache.commons.codec.binary.Hex;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;




public class FPRINT extends FR
{

      public static final int REMOVE_FFFFFF_WHEN_TYPE_CAST=256;

	private SerialPort _serialPort;
	private int _gettedBytes=0; 
	
	//private boolean _writeLog=true;
	private boolean _writeLog=false;

	private String ReceiptType="";

      private ArrayOfBytes _bEOT = new ArrayOfBytes();
	private ArrayOfBytes _bENQ = new ArrayOfBytes();
	private ArrayOfBytes _bACK = new ArrayOfBytes();
	private ArrayOfBytes _bNAK = new ArrayOfBytes();



	public FPRINT()
	{
            _bEOT.append(0x04);
		_bENQ.append(0x05);
		_bACK.append(0x06);
		_bNAK.append(0x15);
	}


	private ArrayOfBytes turnString(ArrayOfBytes str)
	{
		if (_writeLog) Common.log("getByteArrayFromString");

		ArrayOfBytes out=new ArrayOfBytes();
		for(int i=str.length()-1;i>-1;i--)
		{
			out.append(str.at(i));
		}
		return out;
	}

	private ArrayOfBytes getByteArrayFromString(String str)
	{
		if (_writeLog) Common.log("getByteArrayFromString");

		ArrayOfBytes strOut= new ArrayOfBytes();
		if ((str.length()%2)>0) str="0"+str;


		for(int i=0;i<str.length()-1;i+=2)
		{
			strOut.append(Integer.parseInt(str.substring(i, (i + 2)), 16));
		}

		return strOut;
	}

      private ArrayOfBytes toAtolCodePage(String str)
      {
            if (_writeLog) Common.log("ToAtolCodePage");

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
                  if(in.at(i)==0x10)
                  {
                        if ((in.at(i+1)==0x10) || (in.at(i+1)==0x03)); // do nothing
                        else out.append(in.at(i));
                  }
                  else  out.append(in.at(i));
            }
            out.append(in.at(i));

            if (_writeLog)
            {
                  String strLog="delEscapeCharacter == ";
                  for (int j=0;j<out.length();j++) strLog+=String.format("%02x", out.at(j));
                  Common.log(strLog);
            }


            return out;
      }




	public static String getErrorDetails(int error)	
	{
		String str="";

            switch (error)
            {
                  case 0:
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
		if (_writeLog) Common.log("openPort");

		//Передаём в конструктор имя порта

		//serialPort = new SerialPort("/dev/ttyS0");
		_serialPort = new SerialPort(portName);

		try {
		    //Открываем порт
		    _serialPort.openPort();
		    //Выставляем параметры
		    _serialPort.setParams(Integer.parseInt(baud),
			                     SerialPort.DATABITS_8,
			                     SerialPort.STOPBITS_1,
			                     SerialPort.PARITY_NONE);
		    
		    //Включаем аппаратное управление потоком
		    //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
			 //                             SerialPort.FLOWCONTROL_RTSCTS_OUT);

		    //Устанавливаем ивент лисенер и маску
		    //_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
    }

	private boolean writePort(ArrayOfBytes toPort)
	{
		if (_writeLog) Common.log("writePort");
		try {
		    	_serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to   port -> ";
		    	for (int i=0;i<toPort.length();i++) strLog+=String.format("%02x", toPort.at(i));
			    Common.log(strLog);
		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
		return true;

	}


	private boolean readPort(ArrayOfBytes fromPort) //throws InterruptedException 
	{
            if (_writeLog) Common.log("readPort");

            fromPort.clear();

            try 
            {

                  fromPort.append(_serialPort.readBytes(1, 1000));

                  String strLog="from port <- ";
                  for (int j=0;j<fromPort.length();j++) strLog+=String.format("%02x", fromPort.at(j));
                      Common.log(strLog);
            }
            catch (SerialPortException ex) 
            {
                  System.out.println(ex);
            }
            catch (SerialPortTimeoutException ext) 
            {
                  return false;
            }
                  
            if (_writeLog) Common.log("End of readPort");
            return true;

	}

      private boolean testConnect()
      {
            boolean result=false;
            ArrayOfBytes fromPort = new ArrayOfBytes();

            writePort(_bENQ);
            for (int i=0;i<3;i++)
            {
                  readPort(fromPort);
                  if (_bACK.equals(fromPort))
                  {
                        result=true;
                        break;
                  }
                  if (_bENQ.equals(fromPort))
                  {
                        try {Thread.sleep(500);} catch (InterruptedException ie) {}
                        //getAnswer(fromPort);
                        fromPort.clear();
                        writePort(_bENQ);                       
                  }


                  try {Thread.sleep(200);} catch (InterruptedException ie) {}             
            }

            return result;

      }


	private int getEndOfPrinting()
	{
		if (_writeLog) Common.log("getEndOfPrinting");
		int error=0;

		ArrayOfBytes toPort = new ArrayOfBytes(0x02, 0x00, 0x00, 0x45, 0x03, 0x46);
		ArrayOfBytes fromPort = new ArrayOfBytes();

		boolean endOfPrinting=false;

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

            if (_writeLog) Common.log("sendToCom");
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
                        
                        if (_bACK.equals(fromPort))
                        {
                              writePort(_bEOT);
                              error=0;
                              break;

                        }
                        else if (_bNAK.equals(fromPort))
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

            if (_writeLog) Common.log("getLastError");
            int error=FR.NO_RESPONSE_FR;
            int mask=0;

            boolean getENQ=false;

            ArrayOfBytes temp = new ArrayOfBytes();
            ArrayOfBytes fromPort = new ArrayOfBytes();
            
            boolean startByteWasReceived=false;
            boolean finishByteWasReceived=false;

            result.clear();

            for (int i=0;;i++)
            {
                  if (!getENQ)
                  {
                        fromPort.clear();
                        readPort(fromPort);
                        if (_bENQ.equals(fromPort))
                        {
                              writePort(_bACK);
                              getENQ=true;
                        }                      
                  }
                  else// (getENQ)
                  {
                        fromPort.clear();
                        if (readPort(fromPort))
                        {
                              if (temp.length()>0) mask=temp.at(temp.length()-1);

                              if (fromPort.at(0)==(byte)(0x02)) startByteWasReceived=true;
                              if (startByteWasReceived==true)
                              {
                                    temp.append(fromPort.at(0));
                              }
                              if (fromPort.at(0)==(byte)(0x03)) 
                              {
                                    if (mask!=0x10) finishByteWasReceived=true;
                                    continue; // get next last byte
                              }
                              if (fromPort.at(0)==(byte)(0x04))
                              {
                                    //break;
                              }

                              if (finishByteWasReceived)
                              {
                                    writePort(_bACK);
                                    
                                    result.append(delEscapeCharacter(temp));
                                    
                                    error=result.atUnsignedInt(2);

                                    readPort(fromPort); //read last byte

                                    break;
                              }
                        }
                  }
            }
            


            return error;

      }

      private int transactionWithoutAnswer(ArrayOfBytes toPort)
      {

            if (_writeLog) Common.log("transactionWithoutAnswer");
            int error=0;

            if (error==0) error=sendToCom(toPort);
            return error;

      }


	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{

		if (_writeLog) Common.log("transaction");
		int error=0;

            if (error==0) error=sendToCom(toPort);
            if (error==0) error=getAnswer(result);
		return error;

	}

      public int exitMode() throws FrException
      {
            if (_writeLog) Common.log("exitMode");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x48);
            

            
            if (error==0) error=transaction(CRC(commandStr), getStr);

            Common.log(""+error);
            Common.log(""+Integer.toString(error));

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;

      }

       public int setMode(int mode, String pass) throws FrException
      {
            if (_writeLog) Common.log("setMode");
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

      public int setCurrentDate() throws FrException
      {
            if (_writeLog) Common.log("SetCurrentDate");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x64);
            commandStr.append(curDate());
                  
            if (error==0) error=transaction(CRC(commandStr), getStr);

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return error;
      }

      public int setCurrentTime() throws FrException
      {
            if (_writeLog) Common.log("SetCurrentTime");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x4B);
            commandStr.append(curTime());
                  
            if (error==0) error=transaction(CRC(commandStr), getStr);

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return error;
      }

      public String getKkmType() throws FrException
      {
            if (_writeLog) Common.log("getKkmType");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();
            String result="";

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0xA5);
                  
            if (error==0) error=transactionWithoutAnswer(CRC(commandStr));
            if (error==0) 
            {
                  getAnswer(getStr);
                  if (getStr.at(1)!=0) error=getStr.at(1);
            }
            if (error==0) 
            {
                  ArrayOfBytes tmp = new ArrayOfBytes();
                  tmp=getStr.mid(12);
                  result=tmp.mid(0, tmp.indexOf(0x03)).toString("CP866");
      
                  if (_writeLog) Common.log(result);
            }

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return result;
      }
      public String getKkmVersion() throws FrException
      {
            if (_writeLog) Common.log("getKkmVersion");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();
            String result="";

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0xA5);
                  
            if (error==0) error=transactionWithoutAnswer(CRC(commandStr));
            if (error==0) 
            {
                  getAnswer(getStr);
                  if (getStr.at(1)!=0) error=getStr.at(1);
            }
            if (error==0) 
            {
                  result=String.valueOf(getStr.at(7));
                  result+=".";
                  result+=String.valueOf(getStr.at(8));
                  result+=".";
                  result+=javax.xml.bind.DatatypeConverter.printHexBinary(getStr.mid(10, 2).getBytes());

                  if (_writeLog) Common.log(result);
            }

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

            return result;
      }

	public int init() throws FrException
	{
		if (_writeLog) Common.log("Init");
            int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

            commandStr.append(0x00);
            commandStr.append(0x00);
		commandStr.append(0x47);
      		

           	if (error==0) error=transactionWithoutAnswer(CRC(commandStr));

            if (error==0) error=setCurrentDate();
            if (error==0) error=setCurrentTime();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}

	public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException
	{
		if (_writeLog) Common.log("OpenDocument");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            int docTypeTMP=0;

            switch (docType) 
            {
                  case "RECEIPT_TYPE_SALE" :
                        docTypeTMP=1;
                        break;
                  case "RECEIPT_TYPE_RETURN_SALE" :
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


      public int printText(String text) throws FrException
      {
            if (_writeLog) Common.log("AddItem");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            String textTMP=text;

            commandStr.append(0x00);
            commandStr.append(0x00);
            commandStr.append(0x4C);
            commandStr.append(0x02);
            commandStr.append(toAtolCodePage(textTMP));
            commandStr.append(0x01);

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;
      }


	public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
	{
		if (_writeLog) Common.log("AddItem");
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

            
            if (error==0) error=printText(itemName);

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

	public int total() throws FrException
	{
		if (_writeLog) Common.log("Total");
            int error=0;

		return error;

	}

	public int pay(String payType, String sum, String text) throws FrException
	{
		if (_writeLog) Common.log("Pay");
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
			case "CASH_0" :
				payTypeTMP=1;
				break;
			case "CASH_1" :
                        payTypeTMP=2;
                        break;
			case "CASH_2" :
                        payTypeTMP=3;
                        break;
			case "CASH_3" :
                        payTypeTMP=4;
                        break;
			case "CASH_4" :
                        payTypeTMP=5;
                        break;
			case "CASH_5" :
                        payTypeTMP=6;
                        break;
			case "CASH_6" :
                        payTypeTMP=7;
                        break;
			case "CASH_7" :
                        payTypeTMP=8;
                        break;
			case "CASH_8" :
                        payTypeTMP=9;
                        break;
			case "CASH_9" :
                        payTypeTMP=10;
                        break;
			case "CASH_10":
			case "CASH_11":
			case "CASH_12":
			case "CASH_13":
			case "CASH_14":
			case "CASH_15":
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

	public int cancelDocument() throws FrException
	{
		if (_writeLog) Common.log("CancelDocument");
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

	public int closeDocument(String text) throws FrException
	{
		if (_writeLog) Common.log("CloseDocument");
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


	public int xReport(String text) throws FrException
	{
		if (_writeLog) Common.log("Xreport");
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

	public int zReport(String text) throws FrException
	{
		if (_writeLog) Common.log("Zreport");
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

      public  int printImage(BufferedImage image) throws FrException
      {
            if (_writeLog) Common.log("printImage");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            ArrayOfBytes imageArray=new ArrayOfBytes();

            int imageWidthInBytes=image.getWidth()/8;
            int imageHeight=image.getHeight();
            int imageSize=imageWidthInBytes*imageHeight;
            int imageWidthInBytesReal=imageWidthInBytes;
            while(imageWidthInBytesReal%4!=0) // see to https://en.wikipedia.org/wiki/BMP_file_format#Pixel_array_.28bitmap_data.29
            {
                  imageWidthInBytesReal++;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try 
            {
                  ImageIO.write(image, "bmp", baos);
                  baos.flush();
                  baos.close();
            } 
            catch (IOException ex) 
            {
                  ex.printStackTrace();
                  error=ANY_LOGICAL_ERROR;
            }

            if (error==0)
            {

                  byte[] bmpArray = baos.toByteArray();

                  int startByteOfImageData=bmpArray[10]; // see to https://en.wikipedia.org/wiki/BMP_file_format#Bitmap_file_header

                  for(int i=imageHeight-1;-1<i; i--) // inversion bytes and image
                  {
                        for(int j=i*imageWidthInBytesReal+startByteOfImageData, k=0;k<imageWidthInBytesReal;j++, k++)
                        {
                              if (k<imageWidthInBytes) imageArray.append(~bmpArray[j]);
                        }
                  }
            }


            int startByteOfPacketImage=0;
            int packetOfImage=1;

            int indexImageInKkt=0;

            if (error==0)error=setMode(4, "30"); //Programming

            while (error==0)
            {
                  // write array in kkt
                  commandStr.clear();
                  getStr.clear();

                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x8B);
                  commandStr.append(imageArray.mid(startByteOfPacketImage, imageWidthInBytes));

                  error=transaction(CRC(commandStr), getStr);

                  startByteOfPacketImage+=imageWidthInBytes;
                  
                  packetOfImage++;
                  if (packetOfImage>imageHeight) break;
            }

            if (error==0)
            {
                  // close image in kkt and get index of it
                  commandStr.clear();
                  getStr.clear();

                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x9E);

                  error=transaction(CRC(commandStr), getStr);
                  indexImageInKkt=getStr.at(3);
            }

            if (error==0)
            {
                  // printing image by index
                  commandStr.clear();
                  getStr.clear();

                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x8D);
                  commandStr.append(0x01);
                  commandStr.append(indexImageInKkt);
                  commandStr.append(0x00);
                  commandStr.append(0x00);

                  error=transaction(CRC(commandStr), getStr);
            }

            if (error==0)
            {
                  // delete image from kkt
                  commandStr.clear();
                  getStr.clear();

                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x8A);
                  commandStr.append(indexImageInKkt);

                  error=transaction(CRC(commandStr), getStr);
            }



            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;           
      }

      public  int printQrCodeFast(String codeText) throws FrException
      {
            if (_writeLog) Common.log("printQrCode");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            if (error==0)
            {
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0xc1);
                  commandStr.append(0x00);
                  commandStr.append(0x01);
                  commandStr.append(0x06);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(0x00);
                  commandStr.append(codeText);
            }

            if (error==0) error=transaction(CRC(commandStr), getStr);
            if (error==0) error=getEndOfPrinting();

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;            
      }

      public  int printQrCode(String codeText) throws FrException
      {
            if (_writeLog) Common.log("printQrCode");
            int error=0;

            ArrayOfBytes getStr=new ArrayOfBytes();
            ArrayOfBytes commandStr=new ArrayOfBytes();

            if (error==0) error=printText("  ");

            //if (error==0) error=printQrCodeFast(codeText);
            if (error==0) error=cancelDocument();
            if (error==0) error=printImage(QrCode.getQrImage(codeText, 250, 250));

            if (error==0) error=printText("  ");

            if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
            return error;            
      }


	public int receiptSale() throws FrException
	{
		if (_writeLog) Common.log("ReceiptSale");
		int error=0;

		// if (error==0) error=OpenDocument("2", "0", "Test", "0");
		// if (error==0) error=AddItem("тест", "1234567", "1.000", "123.45", "0", "");
		// if (error==0) error=Total();
		// if (error==0) error=Pay("0", "1000.00", "");
		// if (error==0) error=CloseDocument("");

		// if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}
	
}