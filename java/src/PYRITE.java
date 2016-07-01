import java.util.*;
import java.text.SimpleDateFormat;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;


public class PYRITE extends FR{
	private SerialPort _serialPort;
	private int _id=0x20; 
	private int _gettedBytes=0; 
	

	//private boolean _writeLog=true;
	private boolean _writeLog=false;

	private ArrayOfBytes _bENQ = new ArrayOfBytes();
	private ArrayOfBytes _bACK = new ArrayOfBytes();



	public PYRITE(){
		_bENQ.append(0x05);
		_bACK.append(0x06);
	}

	private String _passOfConnect="PIRI";

	private String getPassOfConnect(){		
		return _passOfConnect;
	}


	private String curDate(){
		String strDate;
		
		Date dt= new Date();
		strDate = new SimpleDateFormat("ddMMyy").format(dt);
		
		return strDate;
	}

	private String curTime(){
		String strTime;

		Date dt= new Date();
		strTime = new SimpleDateFormat("HHmmss").format(dt);

		return strTime;
	}

	private int id(){
		if (_id==0xFD) _id=0x20;
		else _id++;
		return _id;
	}

	private ArrayOfBytes CRC(ArrayOfBytes in){
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

	public static String getErrorDetails(int error){
		String str="";

	    switch (error){
	        case 0:
	            break;
	        case 1:
	            str="Ошибка 01h - Функция невыполнима при данном статусе ККМ";
	            break;
	        case 2:
	            str="Ошибка 02h - В команде указан неверный номер функции";
	            break;
	        case 3:
	            str="Ошибка 03h - Некорректный формат или параметр команды";
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
	            str="Ошибка 0Ch - Вводимая дата более ранняя, чем дата последней фискальной операции";
	            break;
	        case 13:
	            str="Ошибка 0Dh - Неверный пароль доступа к ФП";
	            break;
	        case 14:
	            str="Ошибка 0Eh - Отрицательный результат";
	            break;
	        case 15:
	            str="Ошибка 0Fh - Для выполнения команды необходимо закрыть смену";
	            break;
	        case 32:
	            str="Ошибка 20h - Фатальная ошибка ККМ";
	            break;
	        case 33:
	            str="Ошибка 21h - Нет свободного места в фискальной памяти ККМ";
	            break;
	        case 65:
	            str="Ошибка 41h - Некорректный формат или параметр команды ЭКЛЗ";
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


    public void openPort(String portName, String baud) throws FrException{
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
		catch (SerialPortException ex){
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

	private boolean writePort(ArrayOfBytes toPort){
		if (_writeLog) Common.log("writePort");
		try {

		    	_serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to   port -> ";
		    	for (int j=0;j<toPort.length();j++) strLog+=String.format("%02x", toPort.at(j));
			    Common.log(strLog);
		}
		catch (SerialPortException ex){
		     System.out.println(ex);
		}
		return true;

	}

	private boolean readPort(ArrayOfBytes fromPort){
		if (_writeLog) Common.log("readPort");

		fromPort.clear();

		try {

		    fromPort.append(_serialPort.readBytes(1, 1000));

	    	String strLog="from port <- ";
	    	for (int j=0;j<fromPort.length();j++) strLog+=String.format("%02x", fromPort.at(j));
		    Common.log(strLog);
		}
		catch (SerialPortException ex){
		    System.out.println(ex);
		}
        catch (SerialPortTimeoutException ext){
            return false;
        }
				
		if (_writeLog) Common.log("End of readPort");
		return true;
	}
	
	private boolean testConnect(){
		boolean result = true;

		ArrayOfBytes fromPort = new ArrayOfBytes();
		fromPort.clear();

		writePort(_bENQ);
		if (readPort(fromPort)){
			if (fromPort.at(0)!=_bACK.at(0)){
				result = false;
			}			
		}
		else result = false;
		
		return result;
	}

	private int errorAnalisis(ArrayOfBytes response){
		if (_writeLog) Common.log("errorAnalisis");

		int error=0;
		String tmpError="";

		tmpError+=(char)(response.at(4));
		tmpError+=(char)(response.at(5));

		error=Integer.parseInt(tmpError, 16);

		return error;
	}

	private int getResponse(ArrayOfBytes response){
		if (_writeLog) Common.log("getResponse");

		int error=0;

		ArrayOfBytes fromPort = new ArrayOfBytes();
		boolean startByteWasReceived=false;
		int resultLength=0;


		response.clear();

		for (int i=0; ;i++){
			fromPort.clear();
			if (readPort(fromPort)){
				if (fromPort.at(0)==(byte)(0x02)) startByteWasReceived=true;
				if (startByteWasReceived==true){
					response.append(fromPort.at(0));
				}
				if (fromPort.at(0)==(byte)(0x03)) resultLength=response.length()+2;

				if ((response.length()==resultLength)&&(response.length()>0)){
					
					error=errorAnalisis(response);

					break;

				}

			}
			else{
				if(!testConnect()){
					error=NO_RESPONSE_FR;
					break;
				}

			}
								
		}


		return error;
	}
	
	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result){
		if (_writeLog) Common.log("transaction");
		int error=0;

		writePort(toPort);

		error=getResponse(result);

		return error;
	}

	public String getKkmType() throws FrException{
	    if (_writeLog) Common.log("getKkmType");
	    int error=0;
	    String result="";
	    String version="";

		try{
			version=getKkmVersion();
			switch(version){
				case "13": 
					result="ПИРИТ ФР01К";
					break;
				case "14": 
					result="ПИРИТ ФР01К";
					break;
				case "15": 
					result="ПИРИТ ФР01К";
					break;
				case "16": 
					result="ПИРИТ ФР01К";
					break;
				case "215": 
					result="PIRIT K";
					break;
			}
		}
		catch (FrException frEx){
			error=frEx.getErrorCodeAsInt();
		}

		if (_writeLog) Common.log(result);

	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}
	public String getKkmVersion() throws FrException{
	    if (_writeLog) Common.log("getKkmVersion");
	    int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("02");
		commandStr.append("2");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0){
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(8);
			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getCurrentStatus() throws FrException{
	    if (_writeLog) Common.log("getCurrentStatus");
	    int error=0;
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("00");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0){
			result=getStr.mid(6, 1).toString();
			result+=getStr.mid(8, 1).toString();
			result+=getStr.mid(10, 1).toString();

			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getLastShiftInFiscalMemory() throws FrException{
	    if (_writeLog) Common.log("getLastShiftInFiscalMemory");
	    int error=0;

	    String status="";
		try{
	    	status=getCurrentStatus();
		}
		catch (FrException frEx){
			error=frEx.getErrorCodeAsInt();
		}

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("01");
		commandStr.append("1");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);

        String result="";
		if (error==0){
			ArrayOfBytes tmp = new ArrayOfBytes();
			tmp=getStr.mid(8);
			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");

			result=String.valueOf(Integer.valueOf(result)-1);
			if (_writeLog) Common.log(result);
		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public int init() throws FrException{
		if (_writeLog) Common.log("Init");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("10");
		commandStr.append(curDate());
		commandStr.append(0x1C);
		commandStr.append(curTime());
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException{
		if (_writeLog) Common.log("OpenDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (docType){
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
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("30");
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

	public int printText(String text) throws FrException{
		if (_writeLog) Common.log("printText");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("40");
		commandStr.append(text, "CP866");
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException{
		if (_writeLog) Common.log("AddItem");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("42");
		commandStr.append(itemName, "cp866");
		commandStr.append(0x1C);
		commandStr.append(articul, "cp866");
		commandStr.append(0x1C);
		commandStr.append(qantity);
		commandStr.append(0x1C);
		commandStr.append(cost);
		commandStr.append(0x1C);
		commandStr.append(0x1C);
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int total() throws FrException{
		if (_writeLog) Common.log("Total");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("44");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int pay(String payType, String sum, String text) throws FrException{
		if (_writeLog) Common.log("Pay");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (payType){
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
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("47");
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

	public int cancelDocument() throws FrException{
		if (_writeLog) Common.log("CancelDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("32");
		commandStr.append(0x1C);
		commandStr.append(0x03);


		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int closeDocument(String text) throws FrException{
		if (_writeLog) Common.log("CloseDocument");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("31");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}


	public int xReport(String text) throws FrException{
		if (_writeLog) Common.log("Xreport");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("20");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int zReport(String text) throws FrException{
		if (_writeLog) Common.log("Zreport");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append(getPassOfConnect());
		commandStr.append(id());
		commandStr.append("21");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int printQrCode(String codeText) throws FrException{
		if (_writeLog) Common.log("printQrCode");
		int error=0;

		// The function is not supported in current fiscal printer :(

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
			commandStr.append(getPassOfConnect());
			commandStr.append(id());
			commandStr.append("75");
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
			commandStr.append(getPassOfConnect());
			commandStr.append(id());
			commandStr.append("75");
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
			commandStr.append(getPassOfConnect());
			commandStr.append(id());
			commandStr.append("74");
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
			commandStr.append(getPassOfConnect());
			commandStr.append(id());
			commandStr.append("74");
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
			commandStr.append(getPassOfConnect());
			commandStr.append(id());
			commandStr.append("72");
			commandStr.append(strShift);
			commandStr.append(0x1C);
			commandStr.append(0x03);			
		}

		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return error;
	}


}