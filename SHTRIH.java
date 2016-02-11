import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;



public class SHTRIH extends FR
{

	private SerialPort serialPort;
	private int _gettedBytes=0; 
	
	//private boolean _wrileLog=true;
	private boolean _wrileLog=false;

	private String ReceiptType="";

	private ArrayOfBytes bENQ = new ArrayOfBytes();
	private ArrayOfBytes bACK = new ArrayOfBytes();
	private ArrayOfBytes bNAK = new ArrayOfBytes();



	public SHTRIH()
	{
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


	private String rightJustified(String str, char ch, int length)
	{
		if (_wrileLog) Log("rightJustified");

		String out=str;
		while (out.length()<length)
		{
			out=ch+out;
		}
		return out;
	}

	private String leftJustified(String str, char ch, int length)
	{
		String out=str;
		while (out.length()<length)
		{
			out+=ch;
		}
		return out;
	}

	private String curDate()
	{
		String strDate;
		
		Date dt= new Date();
		strDate = new SimpleDateFormat("ddMMyy").format(dt);
		
		return strDate;
	}

	private String curTime()
	{
		String strTime;

		Date dt= new Date();
		strTime = new SimpleDateFormat("HHmmss").format(dt);

		return strTime;
	}

	private ArrayOfBytes CRC(ArrayOfBytes in)
	{
		ArrayOfBytes out=new ArrayOfBytes();
		out.append((byte)(0x02));
		out.append((byte)(in.length()));
		out.append(in);

		byte res=0;
		for(int i=1; i<out.length(); i++)
		{
				res=(byte)(res^out.at(i));
		}
		out.append(res);
		
		return out;
	}

	public static String getErrorDetails(int error)	
	{
		String str="";

        switch (error)
        {
            case 0:
                break;
            case 1:    str="Ошибка ФП: Неисправен накопитель ФП 1, ФП 2 или часы"; break;
            case 2:    str="Ошибка ФП: Отсутствует ФП 1"; break;
            case 3:    str="Ошибка ФП: Отсутствует ФП 2"; break;
            case 4:    str="Ошибка ФП: Некорректные параметры в команде обращения к ФП"; break;
            case 5:    str="Ошибка ФП: Нет запрошенных данных "; break;
            case 6:    str="Ошибка ФП: ФП в режиме вывода данных"; break;
            case 7:    str="Ошибка ФП: Некорректные параметры в команде для данной реализации ФП"; break;
            case 8:    str="Ошибка ФП: Команда не поддерживается в данной реализации ФП"; break;
            case 9:    str="Ошибка ФП: Некорректная длина команды "; break;
            case 10:   str="Ошибка ФП: Формат данных не BCD "; break;
            case 11:   str="Ошибка ФП: Неисправна ячейка памяти ФП при записи итога "; break;
            case 17:   str="Ошибка ФП: Не введена лицензия"; break;
            case 18:   str="Ошибка ФП: Заводской номер уже введен "; break;
            case 19:   str="Ошибка ФП: Текущая дата меньше даты последней записи в ФП "; break;
            case 20:   str="Ошибка ФП: Область сменных итогов ФП переполнена"; break;
            case 21:   str="Ошибка ФП: Смена уже открыта"; break;
            case 22:   str="Ошибка ФП: Смена не открыта"; break;
            case 23:   str="Ошибка ФП: Номер первой смены больше номера последней смены"; break;
            case 24:   str="Ошибка ФП: Дата первой смены больше даты последней смены"; break;
            case 25:   str="Ошибка ФП: Нет данных в ФП"; break;
            case 26:   str="Ошибка ФП: Область перерегистраций в ФП переполнена"; break;
            case 27:   str="Ошибка ФП: Заводской номер не введен"; break;
            case 28:   str="Ошибка ФП: В заданном диапазоне есть поврежденная запись"; break;
            case 29:   str="Ошибка ФП: Повреждена последняя запись сменных итогов "; break;
            case 30:   str="Ошибка ФП: Область перерегистраций ФП переполнена "; break;
            case 31:   str="Ошибка ФП: Отсутствует память регистров "; break;
            case 32:   str="Ошибка ФП: Переполнение денежного регистра при добавлении "; break;
            case 33:   str="Ошибка ФП: Вычитаемая сумма больше содержимого денежного регистра "; break;
            case 34:   str="Ошибка ФП: Неверная дата"; break;
            case 35:   str="Ошибка ФП: Нет записи активизации"; break;
            case 36:   str="Ошибка ФП: Область активизаций переполнена"; break;
            case 37:   str="Ошибка ФП: Нет активизации с запрашиваемым номером"; break;
            case 38:   str="Ошибка ФР: Вносимая клиентом сумма меньше суммы чека"; break;
            case 39:   str="Ошибка ФР: Повреждение контрольных сумм ФП"; break;
            case 43:   str="Ошибка ФР: Невозможно отменить предыдущую команду "; break;
            case 44:   str="Ошибка ФР: Обнулённая касса (повторное гашение невозможно)"; break;
            case 45:   str="Ошибка ФР: Сумма чека по секции меньше суммы сторно"; break;
            case 46:   str="Ошибка ФР: В ФР нет денег для выплаты "; break;
            case 48:   str="Ошибка ФР: ФР заблокирован, ждет ввода пароля налогового инспектора"; break;
            case 50:   str="Ошибка ФР: Требуется выполнение общего гашения"; break;
            case 51:   str="Ошибка ФР: Некорректные параметры в команде"; break;
            case 52:   str="Ошибка ФР: Нет данных "; break;
            case 53:   str="Ошибка ФР: Некорректный параметр при данных настройках"; break;
            case 54:   str="Ошибка ФР: Некорректные параметры в команде для данной реализации ФР"; break;
            case 55:   str="Ошибка ФР: Команда не поддерживается в данной реализации ФР"; break;
            case 56:   str="Ошибка ФР: Ошибка в ПЗУ"; break;
            case 57:   str="Ошибка ФР: Внутренняя ошибка ПО ФР"; break;
            case 58:   str="Ошибка ФР: Переполнение накопления по надбавкам в смене"; break;
            case 59:   str="Ошибка ФР: Переполнение накопления в смене"; break;
            case 60:   str="Ошибка ФР: ЭКЛЗ: неверный регистрационный номер "; break;
            case 61:   str="Ошибка ФР: Смена не открыта - операция невозможна "; break;
            case 62:   str="Ошибка ФР: Переполнение накопления по секциям в смене "; break;
            case 63:   str="Ошибка ФР: Переполнение накопления по скидкам в смене "; break;
            case 64:   str="Ошибка ФР: Переполнение диапазона скидок"; break;
            case 65:   str="Ошибка ФР: Переполнение диапазона оплаты наличными"; break;
            case 66:   str="Ошибка ФР: Переполнение диапазона оплаты типом 2"; break;
            case 67:   str="Ошибка ФР: Переполнение диапазона оплаты типом 3"; break;
            case 68:   str="Ошибка ФР: Переполнение диапазона оплаты типом 4"; break;
            case 69:   str="Ошибка ФР: Cумма всех типов оплаты меньше итога чека"; break;
            case 70:   str="Ошибка ФР: Не хватает наличности в кассе"; break;
            case 71:   str="Ошибка ФР: Переполнение накопления по налогам в смене "; break;
            case 72:   str="Ошибка ФР: Переполнение итога чека"; break;
            case 73:   str="Ошибка ФР: Операция невозможна в открытом чеке данного типа"; break;
            case 74:   str="Ошибка ФР: Открыт чек - операция невозможна"; break;
            case 75:   str="Ошибка ФР: Буфер чека переполнен"; break;
            case 76:   str="Ошибка ФР: Переполнение накопления по обороту налогов в смене "; break;
            case 77:   str="Ошибка ФР: Вносимая безналичной оплатой сумма больше суммы чека"; break;
            case 78:   str="Ошибка ФР: Смена превысила 24 часа"; break;
            case 79:   str="Ошибка ФР: Неверный пароль"; break;
            case 80:   str="Ошибка ФР: Идет печать предыдущей команды "; break;
            case 81:   str="Ошибка ФР: Переполнение накоплений наличными в смене"; break;
            case 82:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 2 в смене"; break;
            case 83:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 3 в смене"; break;
            case 84:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 4 в смене"; break;
            case 85:   str="Ошибка ФР: Чек закрыт - операция невозможна"; break;
            case 86:   str="Ошибка ФР: Нет документа для повтора"; break;
            case 87:   str="Ошибка ФР: ЭКЛЗ: количество закрытых смен не совпадает с ФП "; break;
            case 88:   str="Ошибка ФР: Ожидание команды продолжения печати"; break;
            case 89:   str="Ошибка ФР: Документ открыт другим оператором"; break;
            case 90:   str="Ошибка ФР: Скидка превышает накопления в чеке "; break;
            case 91:   str="Ошибка ФР: Переполнение диапазона надбавок"; break;
            case 92:   str="Ошибка ФР: Понижено напряжение 24В"; break;
            case 93:   str="Ошибка ФР: Таблица не определена"; break;
            case 94:   str="Ошибка ФР: Некорректная операция"; break;
            case 95:   str="Ошибка ФР: Отрицательный итог чека"; break;
            case 96:   str="Ошибка ФР: Переполнение при умножении "; break;
            case 97:   str="Ошибка ФР: Переполнение диапазона цены"; break;
            case 98:   str="Ошибка ФР: Переполнение диапазона количества"; break;
            case 99:   str="Ошибка ФР: Переполнение диапазона отдела"; break;
            case 100:  str="Ошибка ФР: ФП отсутствует "; break;
            case 101:  str="Ошибка ФР: Не хватает денег в секции"; break;
            case 102:  str="Ошибка ФР: Переполнение денег в секции"; break;
            case 103:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 104:  str="Ошибка ФР: Не хватает денег по обороту налогов"; break;
            case 105:  str="Ошибка ФР: Переполнение денег по обороту налогов"; break;
            case 106:  str="Ошибка ФР: Ошибка питания в момент ответа по I2C"; break;
            case 107:  str="Ошибка ФР: Нет чековой ленты"; break;
            case 108:  str="Ошибка ФР: Нет контрольной ленты"; break;
            case 109:  str="Ошибка ФР: Не хватает денег по налогу "; break;
            case 110:  str="Ошибка ФР: Переполнение денег по налогу"; break;
            case 111:  str="Ошибка ФР: Переполнение по выплате в смене"; break;
            case 112:  str="Ошибка ФР: Переполнение ФП"; break;
            case 113:  str="Ошибка ФР: Ошибка отрезчика"; break;
            case 114:  str="Ошибка ФР: Команда не поддерживается в данном подрежиме"; break;
            case 115:  str="Ошибка ФР: Команда не поддерживается в данном режиме"; break;
            case 116:  str="Ошибка ФР: Ошибка ОЗУ "; break;
            case 117:  str="Ошибка ФР: Ошибка питания "; break;
            case 118:  str="Ошибка ФР: Ошибка принтера: нет импульсов с тахогенератора"; break;
            case 119:  str="Ошибка ФР: Ошибка принтера: нет сигнала с датчиков"; break;
            case 120:  str="Ошибка ФР: Замена ПО"; break;
            case 121:  str="Ошибка ФР: Замена ФП"; break;
            case 122:  str="Ошибка ФР: Поле не редактируется"; break;
            case 123:  str="Ошибка ФР: Ошибка оборудования"; break;
            case 124:  str="Ошибка ФР: Не совпадает дата"; break;
            case 125:  str="Ошибка ФР: Неверный формат даты"; break;
            case 126:  str="Ошибка ФР: Неверное значение в поле длины "; break;
            case 127:  str="Ошибка ФР: Переполнение диапазона итога чека"; break;
            case 128:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 129:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 130:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 131:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 132:  str="Ошибка ФР: Переполнение наличности"; break;
            case 133:  str="Ошибка ФР: Переполнение по продажам в смене"; break;
            case 134:  str="Ошибка ФР: Переполнение по покупкам в смене"; break;
            case 135:  str="Ошибка ФР: Переполнение по возвратам продаж в смене"; break;
            case 136:  str="Ошибка ФР: Переполнение по возвратам покупок в смене"; break;
            case 137:  str="Ошибка ФР: Переполнение по внесению в смене"; break;
            case 138:  str="Ошибка ФР: Переполнение по надбавкам в чеке"; break;
            case 139:  str="Ошибка ФР: Переполнение по скидкам в чеке "; break;
            case 140:  str="Ошибка ФР: Отрицательный итог надбавки в чеке "; break;
            case 141:  str="Ошибка ФР: Отрицательный итог скидки в чеке"; break;
            case 142:  str="Ошибка ФР: Нулевой итог чека"; break;
            case 143:  str="Ошибка ФР: Касса не фискализирована "; break;
            case 144:  str="Ошибка ФР: Поле превышает размер, установленный в настройках"; break;
            case 145:  str="Ошибка ФР: Выход за границу поля печати при данных настройках шрифта"; break;
            case 146:  str="Ошибка ФР: Наложение полей"; break;
            case 147:  str="Ошибка ФР: Восстановление ОЗУ прошло успешно"; break;
            case 148:  str="Ошибка ФР: Исчерпан лимит операций в чеке "; break;
            case 160:  str="Ошибка ФР: Ошибка связи с ЭКЛЗ"; break;
            case 161:  str="Ошибка ФР: ЭКЛЗ отсутствует "; break;
            case 162:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Некорректный формат или параметр команды "; break;
            case 163:  str="Ошибка ЭКЛЗ: Некорректное состояние ЭКЛЗ"; break;
            case 164:  str="Ошибка ЭКЛЗ: Авария ЭКЛЗ"; break;
            case 165:  str="Ошибка ЭКЛЗ: Авария КС в составе ЭКЛЗ"; break;
            case 166:  str="Ошибка ЭКЛЗ: Исчерпан временной ресурс ЭКЛЗ "; break;
            case 167:  str="Ошибка ЭКЛЗ: ЭКЛЗ переполнена"; break;
            case 168:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Неверные дата и время"; break;
            case 169:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Нет запрошенных данных"; break;
            case 170:  str="Ошибка ЭКЛЗ: Переполнение ЭКЛЗ (отрицательный итог документа)"; break;
            case 176:  str="Ошибка ФР: ЭКЛЗ: Переполнение в параметре количество"; break;
            case 177:  str="Ошибка ФР: ЭКЛЗ: Переполнение в параметре сумма "; break;
            case 178:  str="Ошибка ФР: ЭКЛЗ: Уже активизирована "; break;
            case 192:  str="Ошибка ФР: Контроль даты и времени (подтвердите дату и время)"; break;
            case 193:  str="Ошибка ФР: ЭКЛЗ: суточный отчёт с гашением прервать нельзя"; break;
            case 194:  str="Ошибка ФР: Превышение напряжения в блоке питания"; break;
            case 195:  str="Ошибка ФР: Несовпадение итогов чека и ЭКЛЗ"; break;
            case 196:  str="Ошибка ФР: Несовпадение номеров смен"; break;
            case 197:  str="Ошибка ФР: Буфер подкладного документа пуст"; break;
            case 198:  str="Ошибка ФР: Подкладной документ отсутствует"; break;
            case 199:  str="Ошибка ФР: Поле не редактируется в данном режиме"; break;
            default:
	            str=FR.getErrorDetails(error);
	            break;   
        }
	    return str;
	}


    public void openPort(String portName, String baud) 
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

			if(i>20)		
			{
				Log("i>20" + i);
				break;
			}

			try {
			  Thread.sleep(200);
			} catch (InterruptedException ie) {
			    //Handle exception
			}	
			
		}
		if (_wrileLog) Log("End of readPort");
		return true;

	}

	private int getEndOfPrinting()
	{
		if (_wrileLog) Log("getEndOfPrinting");
		int error=0;

		ArrayOfBytes state = new ArrayOfBytes();
		ArrayOfBytes toPort = new ArrayOfBytes(0x02, 0x05, 0x10, 0x1E, 0x00, 0x00, 0x00, 0x0b);
		ArrayOfBytes fromPort = new ArrayOfBytes();
		ArrayOfBytes result = new ArrayOfBytes();
		
		boolean startByteWasReceived=false;
		boolean endOfPrinting=false;
		int resultLength=0;
		int mode=-1;
		int submode=-1;

		for(int i=0;;i++)
		{
			// Main cycle for receve end of printing status

			writePort(bENQ);
			readPort(state);

			result.clear();
			startByteWasReceived=false;
			endOfPrinting=false;
			resultLength=0;
			mode=-1;
			submode=-1;

			if (state.at(0)==bNAK.at(0))
			{
				writePort(toPort);

				for (int k=0;k<3;k++ ) 
				{
					// Cycle for receve current status

					fromPort.clear();
					readPort(fromPort);

					for (int j=0;j<fromPort.length();j++)
					{
						if (fromPort.at(j)==(byte)(0x02)) startByteWasReceived=true;
						if (startByteWasReceived==true)
						{
							result.append(fromPort.at(j));
						}
					}
					
					if ((result.length()>1)&&(resultLength==0)) 
					{
						writePort(bACK);
						resultLength=result.at(1)+3;
					}
					
					if ((result.length()==resultLength)&&(result.length()>0))
					{
						error=result.at(3);
						if (error!=0) break;

						mode=result.at(7);
						submode=result.at(8);

						if (_wrileLog) Log("error = "+error);
						if (_wrileLog) Log("mode = "+mode);
						if (_wrileLog) Log("submode = "+submode);


						if (submode==0)
		                {
		                    if ((mode!=11)&&(mode!=12)) endOfPrinting=true;
		                }
		                else if ((submode==1)||(submode==2)||(submode==3))
		                {
		                    error=107;
		                    endOfPrinting=true;
		                }

		                break; // current status was receve, bat not end of printing
					}


					try {Thread.sleep(100);} catch (InterruptedException ie) {}			
				}				
			}
			
			if ((result.length()!=resultLength)||(result.length()==0)) error=FR.NO_RESPONSE_FR;

			if (endOfPrinting) break;
			if (error!=0) break;
			try {Thread.sleep(100);} catch (InterruptedException ie) {}		
		}




		return error;
	}

	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{

		if (_wrileLog) Log("getEndOfPrinting");
		int error=0;

		ArrayOfBytes state = new ArrayOfBytes();
		ArrayOfBytes fromPort = new ArrayOfBytes();
		
		boolean startByteWasReceived=false;
		int resultLength=0;

		writePort(bENQ);
		readPort(state);

		result.clear();

		if (state.at(0)==bNAK.at(0))
		{
			writePort(toPort);

			for (int k=0;k<3;k++ ) 
			{
				// Cycle for receve current status

				fromPort.clear();
				readPort(fromPort);

				for (int j=0;j<fromPort.length();j++)
				{
					if (fromPort.at(j)==(byte)(0x02)) startByteWasReceived=true;
					if (startByteWasReceived==true)
					{
						result.append(fromPort.at(j));
					}
				}
				
				if ((result.length()>1)&&(resultLength==0)) 
				{
					writePort(bACK);
					resultLength=result.at(1)+3;
				}
				
				if ((result.length()==resultLength)&&(result.length()>0))
				{
					error=result.at(3);
					break;

				}
				try {Thread.sleep(100);} catch (InterruptedException ie) {}		
			}				
		}
		
		if ((result.length()!=resultLength)||(result.length()==0)) error=FR.NO_RESPONSE_FR;


		return error;

	}

	public int Init() throws FrException
	{
		if (_wrileLog) Log("Init");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x13);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);

		return error;

	}

	public int OpenDocument(String docType, String depType, String operName, String docNumber) throws FrException
	{
		if (_wrileLog) Log("OpenDocument");

		ReceiptType=docType;

		return 0;

	}


	public int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
	{
		if (_wrileLog) Log("AddItem");
		int error=0;

		int intReceiptType=0;
		switch (ReceiptType) 
		{
			case "ReceiptTypeSale" :
				intReceiptType=0x80;
				break;
			case "ReceiptTypeReturnSale" :
				intReceiptType=0x82;
				break;
			default: 
				intReceiptType=0;
				break;
		}


		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		qantity=qantity.replace(".", "").replace(",", "");
		cost=cost.replace(".", "").replace(",", "");


		commandStr.append(intReceiptType);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(qantity)), '0', 10))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(cost)), '0', 10))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified(depType, '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(leftJustified(itemName, (char)(0x00), 40), "cp1251");


		
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int Total() throws FrException
	{
		if (_wrileLog) Log("Total");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x89);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();


		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int Pay(String payType, String sum, String text) throws FrException
	{
		if (_wrileLog) Log("Pay");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		sum=sum.replace(".", "").replace(",", "");

		String pay1="";
		String pay2="";
		String pay3="";
		String pay4="";

		switch (payType) 
		{
			case "Cash0" :
				pay1=sum;
				pay2="0";
				pay3="0";
				pay4="0";
				break;
			case "Cash1" :
				pay1="0";
				pay2=sum;
				pay3="0";
				pay4="0";
				break;
			case "Cash2" :
				pay1="0";
				pay2="0";
				pay3=sum;
				pay4="0";
				break;
			case "Cash3" :
				pay1="0";
				pay2="0";
				pay3="0";
				pay4=sum;
				break;
			case "Cash4" :
			case "Cash5" :
			case "Cash6" :
			case "Cash7" :
			case "Cash8" :
			case "Cash9" :
			case "Cash10":
			case "Cash11":
			case "Cash12":
			case "Cash13":
			case "Cash14":
			case "Cash15":
			default: 
				pay1=sum;
				pay2="0";
				pay3="0";
				pay4="0";
				break;
		}


		commandStr.append(0x85);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(pay1)), '0', 10))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(pay2)), '0', 10))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(pay3)), '0', 10))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified(Integer.toHexString(Integer.parseInt(pay4)), '0', 10))));
		commandStr.append(0x0);
		commandStr.append(0x0);	
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(turnString(getByteArrayFromString(rightJustified("0", '0', 2))));
		commandStr.append(rightJustified(text, (char)(0x00), 40), "cp1251");

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int CancelDocument() throws FrException
	{
		if (_wrileLog) Log("CancelDocument");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x88);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int CloseDocument(String text) throws FrException
	{
		if (_wrileLog) Log("CloseDocument");

		ReceiptType="";

		return 0;

	}


	public int Xreport(String text) throws FrException
	{
		if (_wrileLog) Log("Xreport");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x40);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int Zreport(String text) throws FrException
	{
		if (_wrileLog) Log("Zreport");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x41);
		commandStr.append(0x1E);
		commandStr.append(0x0);
		commandStr.append(0x0);
		commandStr.append(0x0);

		

		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int ReceiptSale() throws FrException
	{
		if (_wrileLog) Log("ReceiptSale");
		int error=0;

		if (error==0) error=OpenDocument("2", "0", "Test", "0");
		if (error==0) error=AddItem("тест", "1234567", "1.000", "123.45", "0", "");
		if (error==0) error=Total();
		if (error==0) error=Pay("0", "1000.00", "");
		if (error==0) error=CloseDocument("");

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
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