import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


public class SP extends FR
{
	private SerialPort serialPort;
	private int _id=0x20; 
	private int _gettedBytes=0; 
	

	private boolean _wrileLog=true;
	//private boolean _wrileLog=false;


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

	private int Id()
	{
		if (_id==0xFD) _id=0x20;
		else _id++;
		if (_wrileLog) Log("Id = "+_id);
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



    public void openPort(String portName, String baud) 
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

	
	private boolean writePort(ArrayOfBytes toPort)
	{
		if (_wrileLog) Log("writePort");
		try {

		    	serialPort.writeBytes(toPort.getBytes());

		    	String strLog="to port -> ";
		    	for (int j=0;j<toPort.length();j++) strLog+=String.format("%02x", toPort.at(j));
			    Log(strLog);
		    	// String strLog="to port -> ";
		    	// for (int j=0;j<toPort.length();j++) strLog+=String.format("%c", toPort.at(j));
			    // Log(strLog);
		}
		catch (SerialPortException ex) 
		{
			System.out.println("afassdfasfafasfasfasfas");
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
			    	// String strLog="from port <- ";
			    	// for (int j=0;j<fromPort.length();j++) strLog+=String.format("%c", fromPort.at(j));
				    // Log(strLog);
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
		if (_wrileLog) Log("End of readPort");
		return true;
	}

	
	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{
		if (_wrileLog) Log("transaction");

		writePort(toPort);
		readPort(result);

		return 0;
	}

	public int Init()
	{
		if (_wrileLog) Log("Init");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("00");
		commandStr.append(curDate());
		commandStr.append(0x1C);
		commandStr.append(curTime());
		commandStr.append(0x1C);
		commandStr.append(0x03);
		

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int OpenDocument(String docType, String depType, String operName, String docNumber)
	{
		if (_wrileLog) Log("OpenDocument");

		switch (docType) 
		{
			case "ReceiptTypeSale" :
				docType="2";
				break;
			case "ReceiptTypeReturnSale" :
				docType="3";
				break;
			default: 
				docType="0";
				break;
		}


		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
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


		transaction(CRC(commandStr), getStr);

		return 0;

	}


	public int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType)
	{
		if (_wrileLog) Log("AddItem");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
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

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int Total()
	{
		if (_wrileLog) Log("Total");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("34");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int Pay(String payType, String sum, String text)
	{
		if (_wrileLog) Log("Pay");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		switch (payType) 
		{
			case "Cash0" :
				payType="0";
				break;
			case "Cash1" :
				payType="1";
				break;
			case "Cash2" :
				payType="2";
				break;
			case "Cash3" :
				payType="3";
				break;
			case "Cash4" :
				payType="4";
				break;
			case "Cash5" :
				payType="5";
				break;
			case "Cash6" :
				payType="6";
				break;
			case "Cash7" :
				payType="7";
				break;
			case "Cash8" :
				payType="8";
				break;
			case "Cash9" :
				payType="9";
				break;
			case "Cash10":
				payType="10";
				break;
			case "Cash11":
				payType="11";
				break;
			case "Cash12":
				payType="12";
				break;
			case "Cash13":
				payType="13";
				break;
			case "Cash14":
				payType="14";
				break;
			case "Cash15":
				payType="15";
				break;
			default: 
				payType="0";
				break;
		}

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("35");
		commandStr.append(payType);
		commandStr.append(0x1C);
		commandStr.append(sum);
		commandStr.append(0x1C);
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int CancelDocument()
	{
		if (_wrileLog) Log("CancelDocument");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("23");
		commandStr.append(0x1C);
		commandStr.append(0x03);


		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int CloseDocument(String text)
	{
		if (_wrileLog) Log("CloseDocument");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("22");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		transaction(CRC(commandStr), getStr);

		return 0;

	}


	public int Xreport(String text)
	{
		if (_wrileLog) Log("Xreport");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("60");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int Zreport(String text)
	{
		if (_wrileLog) Log("Zreport");

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		commandStr.append(0x02);
		commandStr.append("PONE");
		commandStr.append(Id());
		commandStr.append("61");
		commandStr.append(text, "cp866");
		commandStr.append(0x1C);
		commandStr.append(0x03);

		transaction(CRC(commandStr), getStr);

		return 0;

	}

	public int ReceiptSale()
	{
		if (_wrileLog) Log("ReceiptSale");

		OpenDocument("2", "0", "Test", "0");
		AddItem("тест", "1234567", "1.000", "123.45", "0", "");
		Total();
		Pay("0", "1000.00", "");
		CloseDocument("");

		return 0;
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



