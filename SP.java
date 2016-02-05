import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


public class SP 
{
	private static SerialPort serialPort;
	private static byte _id=0x20; 
	private static int _gettedBytes=0; 
	

	private static boolean _wrileLog=false;


	private static String curDate()
	{
		String strDate;
		
		Date dt= new Date();
		strDate = new SimpleDateFormat("ddMMyy").format(dt);
		
		return strDate;
	}

	private static String curTime()
	{
		String strTime;

		Date dt= new Date();
		strTime = new SimpleDateFormat("HHmmss").format(dt);

		return strTime;
	}

	private static String Id()
	{
		if (_id==0xFD) _id=0x20;
		else _id++;
		return String.format("%c", _id);
	}

	public static String CRC(String in)
	{
		String out=in;

		byte res=0;
		for(int i=1; i<in.length(); i++)
		{
			try 
			{
				res=(byte)(res^in.getBytes("cp866")[i]);
				//System.out.println(i+" - "+String.format("%02x", in.getBytes("cp866")[i]));
			}
			catch (UnsupportedEncodingException ie) 
			{}
		}
		out+=String.format("%02x", res);
		
		return out;
	}


    public static void openPort(String portName, String baud) 
    {
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
		    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
			                              SerialPort.FLOWCONTROL_RTSCTS_OUT);

		    //Устанавливаем ивент лисенер и маску
		    serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

		}
		catch (SerialPortException ex) 
		{
		    System.out.println(ex);
		}
    }

	
	private static boolean writePort(String toPort)
	{
		try {
			try {
		    	serialPort.writeBytes(toPort.getBytes("cp866"));
		    	System.out.println(toPort);
			  //str=new String(toPort, "UTF-8");
			  //str=new String(toPort, "cp866");
			} catch (UnsupportedEncodingException ie) {
			    //Handle exception
			}			
		}
		catch (SerialPortException ex) {
		    System.out.println(ex);
		}
		return true;

	}

	private static boolean readPort(StringBuffer fromPort) //throws InterruptedException 
	{
		for (int i=0;;i++)
		{
			//System.out.println("wait - "+i);
			
			if (_gettedBytes>0)
			{
				try {
				    fromPort = serialPort.readString(_gettedBytes);
				    System.out.println(fromPort);
			
				}
				catch (SerialPortException ex) {
				    System.out.println(ex);
				}
				
				_gettedBytes=0;
				break;
			}
			try {
			  Thread.sleep(200);
			} catch (InterruptedException ie) {
			    //Handle exception
			}			
		}
		return true;

	}

	
	private static int transaction(String toPort, StringBuffer fromPort)
	{
		if (_wrileLog) System.out.println("transaction");

		//String fromPort="";
		writePort(toPort);
		readPort(fromPort);
		System.out.println(fromPort);

		return 0;
	}

	public static int Init()
	{
		if (_wrileLog) System.out.println("Init");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="00";
		commandStr+=curDate();
		commandStr+=(char)(0x1C);
		commandStr+=curTime();
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int OpenDocument(String docType, String depType, String operName, String docNumber)
	{
		if (_wrileLog) System.out.println("OpenDocument");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="20";
		commandStr+=docType;
		commandStr+=(char)(0x1C);
		commandStr+=depType;
		commandStr+=(char)(0x1C);
		commandStr+=operName;
		commandStr+=(char)(0x1C);
		commandStr+=docNumber;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}


	public static int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType)
	{
		if (_wrileLog) System.out.println("AddItem");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="30";
		commandStr+=itemName;
		commandStr+=(char)(0x1C);
		commandStr+=articul;
		commandStr+=(char)(0x1C);
		commandStr+=qantity;
		commandStr+=(char)(0x1C);
		commandStr+=cost;
		commandStr+=(char)(0x1C);
		commandStr+=depType;
		commandStr+=(char)(0x1C);
		commandStr+=taxType;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int Total()
	{
		if (_wrileLog) System.out.println("Total");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="34";
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int Pay(String payType, String sum, String text)
	{
		if (_wrileLog) System.out.println("Pay");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="35";
		commandStr+=payType;
		commandStr+=(char)(0x1C);
		commandStr+=sum;
		commandStr+=(char)(0x1C);
		commandStr+=text;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int CancelDocument()
	{
		if (_wrileLog) System.out.println("CancelDocument");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="23";
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int CloseDocument(String text)
	{
		if (_wrileLog) System.out.println("CloseDocument");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="22";
		commandStr+=text;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}


	public static int Xreport(String text)
	{
		if (_wrileLog) System.out.println("Xreport");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="60";
		commandStr+=text;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int Zreport(String text)
	{
		if (_wrileLog) System.out.println("Zreport");

		String commandStr="";
		String getStr="";

		commandStr+=(char)(0x02);
		commandStr+="PONE";
		commandStr+=Id();
		commandStr+="61";
		commandStr+=text;
		commandStr+=(char)(0x1C);
		commandStr+=(char)(0x03);

		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public static int ReceiptSale()
	{
		OpenDocument("2", "0", "Test", "0");
		AddItem("тест", "1234567", "1.000", "123.45", "0", "");
		Total();
		Pay("0", "1000.00", "");
		CloseDocument("");

		if (_wrileLog) System.out.println("ReceiptSale");
		return 0;

	}


    private static class PortReader implements SerialPortEventListener 
    {
        public void serialEvent(SerialPortEvent event) 
        {
            if(event.isRXCHAR() && event.getEventValue() > 0)
	    	{
		    	_gettedBytes=event.getEventValue();            
        	}
    	}
    }


// тест2
}



