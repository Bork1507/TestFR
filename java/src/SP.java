import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;




public class SP extends FR
{
	private SerialPort _serialPort;
	private int _id=0x20; 
	private int _gettedBytes=0; 
	

	//private boolean _writeLog=true;
	private boolean _writeLog=false;

	private ArrayOfBytes _bENQ = new ArrayOfBytes();
	private ArrayOfBytes _bACK = new ArrayOfBytes();



	public SP()
	{
		_bENQ.append(0x05);
		_bACK.append(0x06);
	}

	public String deviceType() {
		return "FISCAL_PRINTER";
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
//		if (_writeLog) Common.log("Id = "+_id);
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

	public void closePort()
	{
		if (_writeLog) Common.log("closePort");

		try {
			//Закрываем порт
			_serialPort.closePort();
		}
		catch (SerialPortException ex)
		{
			Common.log(ex.toString());
		}
	}

	private boolean writePort(ArrayOfBytes toPort)
	{
		if (_writeLog) Common.log("writePort");
		try {

		    	_serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to   port -> ";
		    	for (int j=0;j<toPort.length();j++) strLog+=String.format("%02x", toPort.at(j));
			    Common.log(strLog);
		    	// String strLog="to port -> ";
		    	// for (int j=0;j<toPort.length();j++) strLog+=String.format("%c", toPort.at(j));
			    // Common.log(strLog);
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
	
	private int testConnect(ArrayOfBytes out)
	{
		// timeout = -1
		// ok      = 0
		// another = 1


		int result = -1;

		out.clear();

		ArrayOfBytes fromPort = new ArrayOfBytes();
		fromPort.clear();

		writePort(_bENQ);
		if (readPort(fromPort))
		{
			if (fromPort.at(0)==_bACK.at(0))
			{
				result = 0;
			}
			else
			{
				out.append(fromPort.at(0));
				result = 1;
			}
		}
		else result = -1;
		
		return result;
	}

	private int errorAnalisis(ArrayOfBytes response)
	{
		if (_writeLog) Common.log("errorAnalisis");

		int error=0;
		String tmpError="";

		tmpError+=(char)(response.at(4));
		tmpError+=(char)(response.at(5));

		error=Integer.parseInt(tmpError, 16);

		return error;
	}

	private int getResponse(ArrayOfBytes response)
	{
		if (_writeLog) Common.log("getResponse");

		int error=0;

		ArrayOfBytes fromPort = new ArrayOfBytes();
		boolean startByteWasReceived=false;
		int resultLength=0;


		response.clear();
		int resultReadPort = 0;

		for (int i=0; ;i++) 
		{
			fromPort.clear();
			if (readPort(fromPort))
			{
				resultReadPort=1;
			}
			else
			{
				resultReadPort = testConnect(fromPort);

				if(resultReadPort==1)
				{
					//Common.log("!!!!! get another byte on test connect!!!");
				}
				else if(resultReadPort==-1)
				{
					error=NO_RESPONSE_FR;
					break;
				}

			}

			if (resultReadPort==1)
			{
				if (fromPort.at(0)==(byte)(0x02)) startByteWasReceived=true;
				if (startByteWasReceived==true)
				{
					response.append(fromPort.at(0));
				}
				if (fromPort.at(0)==(byte)(0x03)) resultLength=response.length()+2;

				if ((response.length()==resultLength)&&(response.length()>0))
				{

					error=errorAnalisis(response);

					break;

				}

			}
								
		}


		return error;
	}
	
	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{
		if (_writeLog) Common.log("transaction");
		int error=0;

		writePort(toPort);

		error=getResponse(result);

		return error;
	}

	public String getKkmType() throws FrException
	{
	    if (_writeLog) Common.log("getKkmType");
	    int error=0;
	    String result="";
	    String version="";

		try
		{
			version=getKkmVersion();
			switch(version)
			{
				case "128": 
					result="СП101ФР-К";
					break;
				case "130": 
					result="СП101ФР-К";
					break;
				case "132": 
					result="СП101ФР-К";
					break;
				case "160":
					result="СП101-Ф";
					break;
				case "402":
					result="СП402ФР-К";
					break;
				case "403": 
					result="СП402ФР-К";
					break;
				case "404": 
					result="СП402ФР-К";
					break;
				case "414":
					result="СП412ФР";
					break;
				case "614":
					result="СП614БУ";
					break;
				case "460":
					result="СП402-Ф";
					break;
				case "601":
					result="СП601-К";
					break;
			}
		}
		catch (FrException frEx)
		{
			error=frEx.getErrorCodeAsInt();
		}

		if (_writeLog) Common.log(result);

	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}
	public String getKkmVersion() throws FrException
	{
	    if (_writeLog) Common.log("getKkmVersion");
	    int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A5");
		commandStr.append("0");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getSerialNumber() throws FrException
	{
		if (_writeLog) Common.log("getSerialNumber");
		int error=0;
		String result="";

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A6");
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);

		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			if (_writeLog) Common.log(result);
		}

		if (_writeLog) Common.log(result);

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return result;
	}

	public String getPrinterInfo() throws FrException
	{
	    if (_writeLog) Common.log("getPrinterInfo");
	    int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("AF");
		commandStr.append("6");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result+="boot-";
			result+=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			if (_writeLog) Common.log(result);
		}

		if (error==0)
		{
			commandStr.clear();

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("AF");
			commandStr.append("7");
			commandStr.append(0x1C);
			commandStr.append(0x03);

			error=transaction(CRC(commandStr), getStr);
		}

		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result+=" flash-";
			result+=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			//if (_writeLog)
				Common.log("result = "+result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getKkmParameter(int rowNumber, int columnNumber) throws FrException
	{
		if (_writeLog) Common.log("getKkmParameter");
		int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A1");
		commandStr.append(String.valueOf(rowNumber));
		commandStr.append(0x1C);
		commandStr.append(String.valueOf(columnNumber));
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);

		String result="";
		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result+=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			//if (_writeLog)
				Common.log("result = "+result);
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return result;
	}

	public int setKkmParameter(int rowNumber, int columnNumber, String value) throws FrException {
		if (_writeLog) Common.log("setKkmParameter");
		int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A2");
		commandStr.append(String.valueOf(rowNumber));
		commandStr.append(0x1C);
		commandStr.append(String.valueOf(columnNumber));
		commandStr.append(0x1C);
		commandStr.append(value, "CP866");
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}



	public String getCurrentStatus() throws FrException
	{
	    if (_writeLog) Common.log("getCurrentStatus");
	    int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A0");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0)
		{
			result=getStr.mid(6, 1).toString();
			result+=getStr.mid(8, 1).toString();
			result+=getStr.mid(10, 1).toString();

			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getLastShiftInFiscalMemory() throws FrException
	{
	    if (_writeLog) Common.log("getLastShiftInFiscalMemory");
	    int error=0;

	    String status="";
		try
		{
	    	status=getCurrentStatus();
		}
		catch (FrException frEx)
		{
			error=frEx.getErrorCodeAsInt();
		}


		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("A5");
		commandStr.append("1");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0)
		{
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(6);
			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			result=String.valueOf(Integer.valueOf(result)-1);
			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public int init() throws FrException
	{
		if (_writeLog) Common.log("init");

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
		if (_writeLog) Common.log("openDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (docType) 
		{
			case "RECEIPT_TYPE_NON_FISCAL_DOCUMENT" :
				docType="1";
				break;
			case "RECEIPT_TYPE_SALE" :
				docType="2";
				break;
			case "RECEIPT_TYPE_RETURN_SALE" :
				docType="3";
				break;
			case "RECEIPT_TYPE_CASHIN" :
				docType="4";
				break;
			case "RECEIPT_TYPE_CASHOUT" :
				docType="5";
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

	public int printText(String text) throws FrException
	{
		if (_writeLog) Common.log("printText");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("21");
		commandStr.append(text, "CP866");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int printTextEx(String text, int mask) throws FrException
	{
		if (_writeLog) Common.log("printTextEx");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("21");
		commandStr.append(text, "CP866");
		commandStr.append(0x1C);
		commandStr.append(String.valueOf(mask));
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}


	public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
	{
		if (_writeLog) Common.log("addItem");
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


	public int addCashInCashOutSum(String itemName, String sum) throws FrException
	{
		if (_writeLog) Common.log("addCashInCashOutSum");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("36");
		commandStr.append(itemName, "cp866");
		commandStr.append(0x1C);
		commandStr.append(sum);
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}


	public int total() throws FrException
	{
		if (_writeLog) Common.log("total");
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
		if (_writeLog) Common.log("pay");
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
		if (_writeLog) Common.log("cancelDocument");
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
		if (_writeLog) Common.log("closeDocument");
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
		if (_writeLog) Common.log("xReport");
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
		if (_writeLog) Common.log("zReport");
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

	// private BufferedImage getQrImage(String codeText, int imageWidth, int imageHeight)
	// {
	// 	// QrCode image = new QrCode();
	// 	// image.setImageWidth(imageWidth);
	// 	// image.setImageHeight(imageHeight);
	// 	// image.getQrImageFile(codeText, "QrFile.bmp");

	// 	return QrCode.getQrImage(codeText, imageWidth, imageHeight);
	// }

	public int printImage(BufferedImage image) throws FrException
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
				// for(int j=i*imageWidthInBytes+startByteOfImageData, k=0;k<imageWidthInBytes;j++, k++)
				// {
				// 	imageArray.append(~bmpArray[j]);
				// }

                for(int j=i*imageWidthInBytesReal+startByteOfImageData, k=0;k<imageWidthInBytesReal;j++, k++)
                {
                      if (k<imageWidthInBytes) imageArray.append(~bmpArray[j]);
                }
			}


			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("28");
			commandStr.append(Integer.toString(imageSize));
			commandStr.append(0x1C);
			commandStr.append(0x03);

			if (writePort(CRC(commandStr)))
			{
				for(int i=0;i<100; i++)
				{
					if (readPort(getStr))
					{
						if (getStr.at(0)==_bACK.at(0)) break;
					}
					if (i==99) error=NO_RESPONSE_FR;
				}
			}
			else error=ERROR_SEND;
		}



		if (error==0)
		{
			commandStr.clear();
			getStr.clear();

			commandStr.append(imageArray);

			if (writePort(commandStr))
			{

				// int count=0;
				// while(!testConnect())
				// {
				// 	if (count>20)
				// 	{
				// 		error=NO_RESPONSE_FR;
				// 		break;
				// 	}
				// 	count++;
				// }


				try 
				{
					Common.log("Pause "+1000+" ms ...");
					Thread.sleep(1000);
				} catch (InterruptedException ie) {}	
				
			}
			else error=ERROR_SEND;
		}
		if (error==0)
		{
			error=getResponse(getStr);
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;		
	}

	public int printBarCode(int width, int height, String codeType, String codeText) throws FrException
	{
		if (_writeLog) Common.log("printBarCode");
		int error=0;

		String localWidth=Integer.valueOf(width).toString();
		String localHight=Integer.valueOf(height).toString();
		String localCodeType="";
		switch(codeType)
		{
			case "UPC-A":
				localCodeType="0";
				break;
			case "UPC-E":
				localCodeType="1";
				break;
			case "EAN-13":
				localCodeType="2";
				break;
			case "EAN-8":
				localCodeType="3";
				break;
			case "Code 39":
				localCodeType="4";
				break;
			case "Interleaved 2 of 5":
				localCodeType="5";
				break;
			case "Codabar":
				localCodeType="6";
				break;

		}

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("24");
		commandStr.append("2");
		commandStr.append(0x1C);
		commandStr.append(localWidth);
		commandStr.append(0x1C);
		commandStr.append(localHight);
		commandStr.append(0x1C);
		commandStr.append(localCodeType);
		commandStr.append(0x1C);
		commandStr.append(codeText);
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error; // Example: printBarCode(2, 40, "Code 39", "1234567890");

	}



	public int printQrCodeFast(String codeText) throws FrException
	{
		if (_writeLog) Common.log("printQrCodeFast");
		int error=0;


		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("29");
		commandStr.append("5");
		commandStr.append(0x1C);
		commandStr.append("2");
		commandStr.append(0x1C);
		commandStr.append(codeText);
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;		
	}


	public int printQrCode(String codeText) throws FrException
	{
		if (_writeLog) Common.log("printQrCode");
		int error=0;

		String kkmType="";
		String kkmVersion="";

		int printFast=-1;

		try
		{
			kkmVersion=getKkmVersion();
			kkmType=getKkmType();
		}
		catch (FrException frEx)
		{
			error=frEx.getErrorCodeAsInt();
		}



		int imageWidth=0;
		int imageHeight=200;

		switch(kkmType)
		{
			case "СП101ФР-К": 
				imageWidth=576;
				break;
			case "СП101-Ф":
				imageWidth=576;
				break;
			case "СП402ФР-К":
				imageWidth=448;
				break;
			case "СП402-Ф":
				imageWidth=448;
				break;
			case "СП601-К":
				imageWidth=576;
				break;
		}

		// QrCode image = new QrCode();
		// image.setImageWidth(imageWidth);
		// image.setImageHeight(imageHeight);
		// image.getQrImageFile(codeText, "QrFile.bmp");

		if ((kkmType=="СП101ФР-К")||(kkmType=="СП101-Ф")){
			try{
				if ((Integer.valueOf(kkmVersion)==130)){
					String printerInfo="";
					printerInfo=getPrinterInfo();

					if ((printerInfo.indexOf("3.04")>1)&&(printerInfo.indexOf("3.01")>1)){
						printFast=1;
					}
					else{
//						Common.log("printFast=false");
//						printFast=true;
					}

				}
				else if ((Integer.valueOf(kkmVersion)>=132)){
					String printerInfo="";
					printerInfo=getKkmParameter(33, 0);

					switch (Integer.valueOf(printerInfo)){
						case 0: printFast=0;
							break;
						case 1: printFast=1;
							break;
						case 2: printFast=1;
							break;
						default: printFast=0;
							break;

					}
				}
				else{
					// Commands 0x28 and 0x29 are not supported
					printFast=-1;
				}
			}
			catch (FrException frEx){
				error=frEx.getErrorCodeAsInt();
			}

		}
		
		if (printFast==1){
			if (error==0) error=printQrCodeFast(codeText);
			if (error==0) error=printText("");
		}
		else if (printFast==0){
        	if (error==0) error=printImage(QrCode.getQrImage(codeText, imageWidth, imageHeight));
		}
		else {
			// do nothing
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int eraseLogotype() throws FrException
	{
		if (_writeLog) Common.log("eraseLogotype");
		int error=0;

		ArrayOfBytes commandStr=new ArrayOfBytes();
		ArrayOfBytes getStr=new ArrayOfBytes();

		if (error==0) {
			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("E3");
			commandStr.append(0x1C);
			commandStr.append(0x03);
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int loadLogotype(String filePath) throws FrException
	{
		if (_writeLog) Common.log("loadLogotype");
		int error=0;

		ArrayOfBytes bmpFileArray=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();
		ArrayOfBytes getStr=new ArrayOfBytes();

		long position=0;

		try {
			RandomAccessFile bmpFile = new RandomAccessFile(new File(filePath), "r");

			position = bmpFile.length();

			for (int i=0; i<position; i++) {

				if (i==0) {
					bmpFile.seek(i);
					if (bmpFile.readByte()!=0x1b) bmpFileArray.append(0x1b);
				}
				bmpFile.seek(i);
				bmpFileArray.append(bmpFile.readByte());
			}
		}
		catch (FileNotFoundException e) {
			error = FILE_NOT_FOUND;
		}
		catch (IOException e) {}


		if (error==0) {
			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("E2");
			commandStr.append(String.valueOf(bmpFileArray.length()));
			commandStr.append(0x1C);
			commandStr.append(0x03);

			if (writePort(CRC(commandStr))) {
				for(int i=0;i<100; i++) {
					if (readPort(getStr)) {
						if (getStr.at(0)==_bACK.at(0)) break;
					}
					if (i==99) error=NO_RESPONSE_FR;
				}
			}
			else error=ERROR_SEND;
		}

		if (error==0) {
			if (writePort(bmpFileArray)) {
				try {
					Common.log("Pause "+1000+" ms ...");
					Thread.sleep(1000);
				} catch (InterruptedException ie) {}

			}
			else error=ERROR_SEND;
		}
		if (error==0) {
			error=getResponse(getStr);
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int receiptSale() throws FrException{
		if (_writeLog) Common.log("ReceiptSale");
		int error=0;

		if (error==0) error=openDocument("2", "0", "Test", "0");
		if (error==0) error=addItem("тест", "1234567", "1.000", "123.45", "0", "");
		if (error==0) error=total();
		if (error==0) error=pay("0", "1000.00", "");
		if (error==0) error=closeDocument("");

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int printEklzReportFullByDate(Date from, Date to) throws FrException{
		if (_writeLog) Common.log("printEklzReportFullByDate");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (to.compareTo(from)<0) error=ANY_LOGICAL_ERROR;

		if (error==0){
			String strFrom = new SimpleDateFormat("ddMMyy").format(from);
			String strTo = new SimpleDateFormat("ddMMyy").format(to);

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("67");
			commandStr.append("1");
			commandStr.append(0x1C);
			commandStr.append(strFrom);
			commandStr.append(0x1C);
			commandStr.append(strTo);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}
	public int printEklzReportShortByDate(Date from, Date to) throws FrException{
		if (_writeLog) Common.log("printEklzReportShortByDate");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (to.compareTo(from)<0) error=ANY_LOGICAL_ERROR;

		if (error==0){
			String strFrom = new SimpleDateFormat("ddMMyy").format(from);
			String strTo = new SimpleDateFormat("ddMMyy").format(to);

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("67");
			commandStr.append("0");
			commandStr.append(0x1C);
			commandStr.append(strFrom);
			commandStr.append(0x1C);
			commandStr.append(strTo);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}
	public int printEklzReportFullByShift(int from, int to) throws FrException{
		if (_writeLog) Common.log("printEklzReportFullByShift");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (to<from) error=ANY_LOGICAL_ERROR;

		if (error==0){
			String strFrom = String.format("%d", from);
			String strTo = String.format("%d", to);

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("66");
			commandStr.append("1");
			commandStr.append(0x1C);
			commandStr.append(strFrom);
			commandStr.append(0x1C);
			commandStr.append(strTo);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}
	public int printEklzReportShortByShift(int from, int to) throws FrException{
		if (_writeLog) Common.log("printEklzReportShortByShift");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (to<from) error=ANY_LOGICAL_ERROR;

		if (error==0){
			String strFrom = String.format("%d", from);
			String strTo = String.format("%d", to);

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("66");
			commandStr.append("0");
			commandStr.append(0x1C);
			commandStr.append(strFrom);
			commandStr.append(0x1C);
			commandStr.append(strTo);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}
	public int printEklzReportControlTape(int shift) throws FrException{
		if (_writeLog) Common.log("printEklzReportControlTape");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (error==0){
			String strShift = String.format("%d", shift);

			commandStr.append(0x02);
			commandStr.append("PONE");
			commandStr.append(id());
			commandStr.append("64");
			commandStr.append(strShift);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}
	public int testJNIfunctions(String text) throws FrException{
		int error=0;

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}
	public int fiscal54Fz() throws FrException{
		if (_writeLog) Common.log("fiscal54");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(id());
		commandStr.append("08");
		commandStr.append("0", "cp866");
		commandStr.append(0x1C);
		commandStr.append("ООО\"АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ\"", "cp866");
		commandStr.append(0x1C);
		commandStr.append("123456789012", "cp866");
		commandStr.append(0x1C);
		commandStr.append("01234012340123401234", "cp866");
		commandStr.append(0x1C);
		commandStr.append("Иванов абвгдеёжзийклмнопрстуфхцчшщъыьэюя", "cp866");
		commandStr.append(0x1C);
		commandStr.append("Город !@#$%^&*()\"№;%:?*<>[]{}\'`~-=_+", "cp866");
		commandStr.append(0x1C);
		commandStr.append("Другой город", "cp866");
		commandStr.append(0x1C);
		commandStr.append("1", "cp866");
		commandStr.append(0x1C);
		commandStr.append("0", "cp866");
		commandStr.append(0x1C);
		commandStr.append("ОФД \"Первый ОФД\"", "cp866");
		commandStr.append(0x1C);
		commandStr.append("120987654321", "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		//if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) writePort(CRC(commandStr));

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

    public int printImageToCW1000(String filePath) throws FrException
    {
        if (_writeLog) Common.log("loadLogotype");
        int error=0;


        int imageWidth = 0;
        int imageHeight = 0;

        BufferedImage image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            image = ImageIO.read(new File(filePath));
            ImageIO.write(image, "bmp", baos);
            baos.flush();
            baos.close();

            imageWidth = image.getWidth();
            imageHeight = image.getHeight();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            error=ANY_LOGICAL_ERROR;
        }


        byte[] bmpArray = baos.toByteArray();
        int startByteOfImageData=bmpArray[10]; // see to https://en.wikipedia.org/wiki/BMP_file_format#Bitmap_file_header

        ArrayOfBytes bmpArrayToPrinter=new ArrayOfBytes();
        bmpArrayToPrinter.append(0x1C);
        bmpArrayToPrinter.append(0x71);
        bmpArrayToPrinter.append(0x01);
//        bmpArrayToPrinter.append(imageWidth/8);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(imageHeight/8);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(imageHeight/8);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(imageWidth/8);
//        bmpArrayToPrinter.append(0);





//        for(int byteOfWidth=0; byteOfWidth<imageWidth/8; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                int currentBitInByteOfHeight=0;
//                String byteOfImageForPrinter = "";
//                for (int bitOfHeight=imageHeight-1; bitOfHeight>=0; bitOfHeight--)
//                {
//                    int currentByte = byteOfWidth+(bitOfHeight*(imageWidth/8)) + startByteOfImageData;
//
//                    System.out.println(Integer.toBinaryString(bmpArray[currentByte])+" << "+currentBitInByteOfWidth+" == "+Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth)+" & 128 == "+Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
//                    if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128) byteOfImageForPrinter = byteOfImageForPrinter + "1";
//                    else byteOfImageForPrinter = byteOfImageForPrinter + "0";
//
//                    currentBitInByteOfHeight++;
//                    if (currentBitInByteOfHeight==8)
//                    {
//                        //bmpArrayToPrinter.append(bmpArray[currentByte]);
//                        bmpArrayToPrinter.append(~Integer.valueOf(byteOfImageForPrinter, 2));
//                        byteOfImageForPrinter = "";
//                        currentBitInByteOfHeight = 0;
//                    }
//                }
//            }
//        }


//        //for(int byteOfWidth=imageWidth/8-1; byteOfWidth>=0; byteOfWidth--)
//        for(int byteOfWidth=0; byteOfWidth<imageWidth/8; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                String byteOfImageForPrinter = "";
//
////                for (int byteOfHeight=0; byteOfHeight<imageHeight/8; byteOfHeight++)
//                for (int byteOfHeight=imageHeight/8-1; byteOfHeight>=0; byteOfHeight--)
//                {
//
//                    for (int bitOfCurrentByte=0; bitOfCurrentByte<8; bitOfCurrentByte++)
//                    //for (int bitOfCurrentByte=7; bitOfCurrentByte>-1; bitOfCurrentByte--)
//                    {
//                        int currentByte = byteOfWidth+((byteOfHeight*8+bitOfCurrentByte)*(imageWidth/8)) + startByteOfImageData;
//                        System.out.println(Integer.toBinaryString(bmpArray[currentByte]) + " << " + currentBitInByteOfWidth + " == " + Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth) + " & 128 == " + Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
//                        if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128)
//                            byteOfImageForPrinter = "1" + byteOfImageForPrinter;
//                        else byteOfImageForPrinter = "0" + byteOfImageForPrinter;
//                    }
//
//                        //bmpArrayToPrinter.append(bmpArray[currentByte]);
//                    bmpArrayToPrinter.append(~Integer.valueOf(byteOfImageForPrinter, 2));
//                    byteOfImageForPrinter = "";
//
//                }
//            }
//        }

//		bmpArrayToPrinter.append(5);
//		bmpArrayToPrinter.append(0);
//		bmpArrayToPrinter.append(10);
//		bmpArrayToPrinter.append(0);

		int realImageWidth = (imageWidth/8);
		if (((imageWidth/8)%4)>0) realImageWidth = ((realImageWidth/4)*4+4);
        int realImageHeight = imageHeight;
        if ((realImageHeight%8)>0) realImageHeight = realImageHeight+(8-(realImageHeight%8));
        bmpArrayToPrinter.append(realImageWidth);
        bmpArrayToPrinter.append(0);
		bmpArrayToPrinter.append(realImageHeight/8);
		bmpArrayToPrinter.append(0);

//        bmpArrayToPrinter.append(imageWidth/8);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(imageHeight/8);
//        bmpArrayToPrinter.append(0);




//		for(int currentRow=0; currentRow<imageHeight; currentRow++)
//		{
//			if (currentRow/8 == imageHeight/8) break;
//
//			for (int currentByteOfWidth = 0; currentByteOfWidth < realyImageWidth; currentByteOfWidth++)
//			{
//				int currentByte = startByteOfImageData+((currentRow*realyImageWidth)+currentByteOfWidth);
//				bmpArrayToPrinter.append(bmpArray[currentByte]);
//			}
//		}

//		for (int currentByteOfWidth = 0; currentByteOfWidth < realyImageWidth; currentByteOfWidth++)
//		{
//			for(int currentRow=0; currentRow<imageHeight; currentRow++)
//			{
//				if (currentRow/8 == imageHeight/8) break;
//
//				int currentByte = startByteOfImageData+((currentRow*realyImageWidth)+currentByteOfWidth);
//				bmpArrayToPrinter.append(bmpArray[currentByte]);
//			}
//
//		}



//        for(int byteOfWidth=0; byteOfWidth<realyImageWidth; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                String byteOfImageForPrinter = "";
//
//                for (int byteOfHeight=imageHeight/8-1; byteOfHeight>=0; byteOfHeight--)
//                {
//
//                    for (int bitOfCurrentByte=0; bitOfCurrentByte<8; bitOfCurrentByte++)
//                    {
//                        int currentByte = byteOfWidth+((byteOfHeight*8+bitOfCurrentByte)*(realyImageWidth)) + startByteOfImageData;
//                        System.out.println(Integer.toBinaryString(bmpArray[currentByte]) + " << " + currentBitInByteOfWidth + " == " + Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth) + " & 128 == " + Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
//                        if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128)
//                            byteOfImageForPrinter = "1" + byteOfImageForPrinter;
//                        else byteOfImageForPrinter = "0" + byteOfImageForPrinter;
//                    }
//
//                    bmpArrayToPrinter.append(Integer.valueOf(byteOfImageForPrinter, 2));
//                    byteOfImageForPrinter = "";
//                }
//            }
//        }

//        for(int byteOfWidth=0; byteOfWidth<realImageWidth; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                int currentBitInByteOfHeight=0;
//                String byteOfImageForPrinter = "";
//                for (int bitOfHeight=imageHeight-1; bitOfHeight>=0; bitOfHeight--)
//                {
//                    int currentByte = byteOfWidth+(bitOfHeight*(realImageWidth)) + startByteOfImageData;
//
//                    System.out.println(Integer.toBinaryString(bmpArray[currentByte])+" << "+currentBitInByteOfWidth+" == "+Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth)+" & 128 == "+Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
//                    if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128) byteOfImageForPrinter = byteOfImageForPrinter + "1";
//                    else byteOfImageForPrinter = byteOfImageForPrinter + "0";
//
//                    currentBitInByteOfHeight++;
//                    if (currentBitInByteOfHeight==8)
//                    {
//                        bmpArrayToPrinter.append(Integer.valueOf(byteOfImageForPrinter, 2));
//                        byteOfImageForPrinter = "";
//                        currentBitInByteOfHeight = 0;
//                    }
//                }
//            }
//        }

        for(int byteOfWidth=0; byteOfWidth<realImageWidth; byteOfWidth++)
        {
            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
            {
                int currentBitInByteOfHeight=0;
                String byteOfImageForPrinter = "";
                for (int bitOfHeight=realImageHeight-1; bitOfHeight>=0; bitOfHeight--)
                {
                    if (bitOfHeight<imageHeight)
                    {
                        int currentByte = byteOfWidth + (bitOfHeight * (realImageWidth)) + startByteOfImageData;

                        System.out.println(Integer.toBinaryString(bmpArray[currentByte]) + " << " + currentBitInByteOfWidth + " == " + Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth) + " & 128 == " + Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
                        if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128)
                            byteOfImageForPrinter = byteOfImageForPrinter + "1";
                        else byteOfImageForPrinter = byteOfImageForPrinter + "0";
                    }
                    else byteOfImageForPrinter = byteOfImageForPrinter + "0";

                    currentBitInByteOfHeight++;
                    if (currentBitInByteOfHeight == 8) {
                        bmpArrayToPrinter.append(Integer.valueOf(byteOfImageForPrinter, 2));
                        byteOfImageForPrinter = "";
                        currentBitInByteOfHeight = 0;
                    }

                }
            }
        }


        ArrayOfBytes send0A=new ArrayOfBytes();
        send0A.append(0x0A);

        ArrayOfBytes printLogo=new ArrayOfBytes();
        printLogo.append(0x1c);
        printLogo.append(0x70);
        printLogo.append(0x01);
        printLogo.append(0x01);

        ArrayOfBytes cut=new ArrayOfBytes();
        cut.append(0x1D);
        cut.append(0x56);
        cut.append(0x01);


        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
		writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}


        writePort(bmpArrayToPrinter);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}


        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}

        writePort(printLogo);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}

        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(send0A);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
        writePort(cut);
        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        ArrayOfBytes bmpFileArray=new ArrayOfBytes();
//        ArrayOfBytes commandStr=new ArrayOfBytes();
//        ArrayOfBytes getStr=new ArrayOfBytes();
//
//        long position=0;
//
//        try {
//            RandomAccessFile bmpFile = new RandomAccessFile(new File(filePath), "r");
//
//            position = bmpFile.length();
//
//            for (int i=0; i<position; i++) {
//
//                if (i==0) {
//                    bmpFile.seek(i);
//                    if (bmpFile.readByte()!=0x1b) bmpFileArray.append(0x1b);
//                }
//                bmpFile.seek(i);
//                bmpFileArray.append(bmpFile.readByte());
//            }
//        }
//        catch (FileNotFoundException e) {
//            error = FILE_NOT_FOUND;
//        }
//        catch (IOException e) {}
//
//
//        if (error==0) {
//            commandStr.append(0x02);
//            commandStr.append("PONE");
//            commandStr.append(id());
//            commandStr.append("E2");
//            commandStr.append(String.valueOf(bmpFileArray.length()));
//            commandStr.append(0x1C);
//            commandStr.append(0x03);
//
//            if (writePort(CRC(commandStr))) {
//                for(int i=0;i<100; i++) {
//                    if (readPort(getStr)) {
//                        if (getStr.at(0)==_bACK.at(0)) break;
//                    }
//                    if (i==99) error=NO_RESPONSE_FR;
//                }
//            }
//            else error=ERROR_SEND;
//        }
//
//        if (error==0) {
//            if (writePort(bmpFileArray)) {
//                try {
//                    Common.log("Pause "+1000+" ms ...");
//                    Thread.sleep(1000);
//                } catch (InterruptedException ie) {}
//
//            }
//            else error=ERROR_SEND;
//        }
//        if (error==0) {
//            error=getResponse(getStr);
//        }
//
//        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

}





