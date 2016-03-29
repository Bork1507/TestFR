import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;



public class SP extends FR
{
	private SerialPort _serialPort;
	private int _id=0x20; 
	private int _gettedBytes=0; 
	

	//private boolean _wrileLog=true;
	private boolean _wrileLog=false;

	private ArrayOfBytes _bENQ = new ArrayOfBytes();
	private ArrayOfBytes _bACK = new ArrayOfBytes();



	public SP()
	{
		_bENQ.append(0x05);
		_bACK.append(0x06);
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

	private int id()
	{
		if (_id==0xFD) _id=0x20;
		else _id++;
//		if (_wrileLog) log("Id = "+_id);
		return _id;
	}

	// private String CRC(String in)
	// {
	// 	String out=in;

	// 	byte res=0;
	// 	for(int i=1; i<in.length(); i++)
	// 	{
	// 		try 
	// 		{
	// 			res=(byte)(res^in.getBytes("cp866")[i]);
	// 			//System.out.println(i+" - "+String.format("%02x", in.getBytes("cp866")[i]));
	// 		}
	// 		catch (UnsupportedEncodingException ie) 
	// 		{}
	// 	}
	// 	out+=String.format("%02x", res);
		
	// 	return out;
	// }

	private ArrayOfBytes CRC(ArrayOfBytes in)
	{
		ArrayOfBytes out=new ArrayOfBytes();
		out.append(in);

		byte res=0;
		for(int i=1; i<out.length(); i++)
		{
				res=(byte)(res^out.at(i));
		}
		out.append(String.format("%02x", res));
		
		return out;
	}

	public static String getErrorDetails(int error)
	{
		String str="";

	    switch (error)
	    {
	        case 0:
	            break;
	        case 1:
	            str="Ошибка 01h - Функция невыполнима при данном статусе ККМ";
	            break;
	        case 2:
	            str="Ошибка 02h - В команде указан неверный номер функции";
	            break;
	        case 3:
	            str="Ошибка 03h - В команде указано неверное, больше чем максимально возможное или несоответствующее типу данных значение";
	            break;
	        case 4:
	            str="Ошибка 04h - Переполнение буфера коммуникационного порта";
	            break;
	        case 5:
	            str="Ошибка 05h - Таймаут при передаче байта информации";
	            break;
	        case 6:
	            str="Ошибка 06h - В команде указан неверный пароль";
	            break;
	        case 7:
	            str="Ошибка 07h - Ошибка контрольной суммы в команде";
	            break;
	        case 8:
	            str="Ошибка 08h - Конец бумаги";
	            break;
	        case 9:
	            str="Ошибка 09h - Принтер не готов";
	            break;
	        case 10:
	            str="Ошибка 0Ah - Текущая смена больше 24 часов";
	            break;
	        case 11:
	            str="Ошибка 0Bh - Разница во времени, ККМ и указанной в команде установки времени, больше 8 минут";
	            break;
	        case 12:
	            str="Ошибка 0Ch - Время последнего документа больше нового времени более чем на один час (с учетом летнего/зимнего перехода)";
	            break;
	        case 13:
	            str="Ошибка 0Dh - Не был задан заголовок документа, что делает невозможным формирование фискального документа.";
	            break;
	        case 14:
	            str="Ошибка 0Eh - Отрицательный результат";
	            break;
	        case 15:
	            str="Ошибка 0Fh - Дисплей покупателя не готов";
	            break;
	        case 32:
	            str="Ошибка 20h - Фатальная ошибка ККМ";
	            break;
	        case 33:
	            str="Ошибка 21h - Нет свободного места в фискальной памяти ККМ";
	            break;
	        case 65:
	            str="Ошибка 41h - Некорректный формат или параметр команды";
	            break;
	        case 66:
	            str="Ошибка 42h - Некорректное состояние ЭКЛЗ";
	            break;
	        case 67:
	            str="Ошибка 43h - Авария  ЭКЛЗ";
	            break;
	        case 68:
	            str="Ошибка 44h - Авария  КС  (Криптографического сопроцессора) в составе ЭКЛЗ";
	            break;
	        case 69:
	            str="Ошибка 45h - Исчерпан временной ресурс использования ЭКЛЗ";
	            break;
	        case 70:
	            str="Ошибка 46h - ЭКЛЗ  переполнена";
	            break;
	        case 71:
	            str="Ошибка 47h - Неверные дата или время";
	            break;
	        case 72:
	            str="Ошибка 48h - Нет запрошенных данных";
	            break;
	        case 73:
	            str="Ошибка 49h - Переполнение (отрицательный итог документа, слишком много отделов для клиента)";
	            break;
	        case 74:
	            str="Ошибка 4Ah - Нет ответа от ЭКЛЗ";
	            break;
	        case 75:
	            str="Ошибка 4Bh - Ошибка при обмене данными с ЭКЛЗ";
	            break;
	        default:
	            str=FR.getErrorDetails(error);
	            break;   
	    }
	    return str;
	}


    public void openPort(String portName, String baud) throws FrException
    {
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
		    //_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
			//                              SerialPort.FLOWCONTROL_RTSCTS_OUT);

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
		if (_wrileLog) log("writePort");
		try {

		    	_serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to   port -> ";
		    	for (int j=0;j<toPort.length();j++) strLog+=String.format("%02x", toPort.at(j));
			    log(strLog);
		    	// String strLog="to port -> ";
		    	// for (int j=0;j<toPort.length();j++) strLog+=String.format("%c", toPort.at(j));
			    // log(strLog);
		}
		catch (SerialPortException ex) 
		{
			// System.out.println("afassdfasfafasfasfasfas");
		     System.out.println(ex);
		}
		return true;

	}

	private boolean readPort(ArrayOfBytes fromPort)
	{
		if (_wrileLog) log("readPort");

		fromPort.clear();

		try 
		{

		    fromPort.append(_serialPort.readBytes(1, 1000));

	    	String strLog="from port <- ";
	    	for (int j=0;j<fromPort.length();j++) strLog+=String.format("%02x", fromPort.at(j));
		    log(strLog);
		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
        catch (SerialPortTimeoutException ext) 
        {
            return false;
        }
				
		if (_wrileLog) log("End of readPort");
		return true;
	}
	
	private boolean testConnect()
	{
		boolean result = true;

		ArrayOfBytes fromPort = new ArrayOfBytes();
		fromPort.clear();

		writePort(_bENQ);
		if (readPort(fromPort))
		{
			if (fromPort.at(0)!=_bACK.at(0)) 
			{
				result = false;
			}			
		}
		else result = false;
		
		return result;
	}

	
	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{
		if (_wrileLog) log("transaction");
		int error=0;


		ArrayOfBytes fromPort = new ArrayOfBytes();
		String tmpError="";

		boolean startByteWasReceived=false;
		int resultLength=0;


		result.clear();
		writePort(toPort);

		for (int i=0; ;i++) 
		{
			fromPort.clear();
			if (readPort(fromPort))
			{
				if (fromPort.at(0)==(byte)(0x02)) startByteWasReceived=true;
				if (startByteWasReceived==true)
				{
					result.append(fromPort.at(0));
				}
				if (fromPort.at(0)==(byte)(0x03)) resultLength=result.length()+2;

				if ((result.length()==resultLength)&&(result.length()>0))
				{
					tmpError+=(char)(result.at(4));
					tmpError+=(char)(result.at(5));

					error=Integer.parseInt(tmpError, 16);

					break;

				}

			}
			else
			{
				if(!testConnect()) 
				{
					error=NO_RESPONSE_FR;
					break;
				}

			}
								
		}


		return error;
	}

	public int init() throws FrException
	{
		if (_wrileLog) log("Init");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("00");
		commandStr.append(curDate());
		commandStr.append(0x1C);
		commandStr.append(curTime());
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException
	{
		if (_wrileLog) log("OpenDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (docType) 
		{
			case "RECEIPT_TYPE_SALE" :
				docType="2";
				break;
			case "RECEIPT_TYPE_RETURN_SALE" :
				docType="3";
				break;
			default: 
				docType="0";
				break;
		}

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("20");
		commandStr.append(docType);
		commandStr.append(0x1C);
		commandStr.append(depType);
		commandStr.append(0x1C);
		commandStr.append(operName, "cp866");
		commandStr.append(0x1C);
		commandStr.append(docNumber);
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}


	public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
	{
		if (_wrileLog) log("AddItem");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("30");
		commandStr.append(itemName, "cp866");
		commandStr.append(0x1C);
		commandStr.append(articul, "cp866");
		commandStr.append(0x1C);
		commandStr.append(qantity);
		commandStr.append(0x1C);
		commandStr.append(cost);
		commandStr.append(0x1C);
		commandStr.append(depType);
		commandStr.append(0x1C);
		commandStr.append(taxType);
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int total() throws FrException
	{
		if (_wrileLog) log("Total");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("34");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int pay(String payType, String sum, String text) throws FrException
	{
		if (_wrileLog) log("Pay");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (payType) 
		{
			case "CASH_0" :
				payType="0";
				break;
			case "CASH_1" :
				payType="1";
				break;
			case "CASH_2" :
				payType="2";
				break;
			case "CASH_3" :
				payType="3";
				break;
			case "CASH_4" :
				payType="4";
				break;
			case "CASH_5" :
				payType="5";
				break;
			case "CASH_6" :
				payType="6";
				break;
			case "CASH_7" :
				payType="7";
				break;
			case "CASH_8" :
				payType="8";
				break;
			case "CASH_9" :
				payType="9";
				break;
			case "CASH_10":
				payType="10";
				break;
			case "CASH_11":
				payType="11";
				break;
			case "CASH_12":
				payType="12";
				break;
			case "CASH_13":
				payType="13";
				break;
			case "CASH_14":
				payType="14";
				break;
			case "CASH_15":
				payType="15";
				break;
			default: 
				payType="0";
				break;
		}

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("35");
		commandStr.append(payType);
		commandStr.append(0x1C);
		commandStr.append(sum);
		commandStr.append(0x1C);
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int cancelDocument() throws FrException
	{
		if (_wrileLog) log("CancelDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("23");
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int closeDocument(String text) throws FrException
	{
		if (_wrileLog) log("CloseDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("22");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}


	public int xReport(String text) throws FrException
	{
		if (_wrileLog) log("Xreport");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("60");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int zReport(String text) throws FrException
	{
		if (_wrileLog) log("Zreport");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("61");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int receiptSale() throws FrException
	{
		if (_wrileLog) log("ReceiptSale");
		int error=0;

		if (error==0) error=openDocument("2", "0", "Test", "0");
		if (error==0) error=addItem("тест", "1234567", "1.000", "123.45", "0", "");
		if (error==0) error=total();
		if (error==0) error=pay("0", "1000.00", "");
		if (error==0) error=closeDocument("");

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

}



