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

		    	String strLog="to port -> ";
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

			try {
			  Thread.sleep(200);
			} catch (InterruptedException ie) {
			    //Handle exception
			}	
	
			if(i>20)		
			{
				Log("i>20" + i);
				break;
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
			
			if ((result.length()!=resultLength)||(result.length()==0)) error=FR.ERROR_COMMUNICATION;

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
		
		if ((result.length()!=resultLength)||(result.length()==0)) error=FR.ERROR_COMMUNICATION;


		return error;

	}

	public int Init()
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

	public int OpenDocument(String docType, String depType, String operName, String docNumber)
	{
		if (_wrileLog) Log("OpenDocument");

		ReceiptType=docType;

		return 0;

	}


	public int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType)
	{
		if (_wrileLog) Log("AddItem");

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


		
		int error=0;
		if (error==0) error=transaction(CRC(commandStr), getStr);
		if (error==0) error=getEndOfPrinting();

		return error;

	}

	public int Total()
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


		return error;

	}

	public int Pay(String payType, String sum, String text)
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

		return error;

	}

	public int CancelDocument()
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

		return error;

	}

	public int CloseDocument(String text)
	{
		if (_wrileLog) Log("CloseDocument");

		ReceiptType="";

		return 0;

	}


	public int Xreport(String text)
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

		return error;

	}

	public int Zreport(String text)
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

		return error;

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