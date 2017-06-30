import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CW1000 extends PRINTER
{
	private SerialPort _serialPort;
//	private int _id=0x20;
//	private int _gettedBytes=0;


	//private boolean _writeLog=true;
	private boolean _writeLog=false;

	private boolean _printLogotype=true;

	private ArrayOfBytes _bENQ = new ArrayOfBytes();
	private ArrayOfBytes _bACK = new ArrayOfBytes();



	public CW1000()
	{
		_bENQ.append(0x05);
		_bACK.append(0x06);
	}

	public String deviceType() {
		return "PRINTER";
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

	public static String getErrorDetails(int error)
	{
		String str="";

	    switch (error)
	    {
//	        case 0:
//	            break;
//	        case 1:
//	            str="Ошибка 01h - Функция невыполнима при данном статусе ККМ";
//	            break;
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

//		writePort(_bENQ);
//		if (readPort(fromPort))
//		{
//			if (fromPort.at(0)==_bACK.at(0))
//			{
//				result = 0;
//			}
//			else
//			{
//				out.append(fromPort.at(0));
//				result = 1;
//			}
//		}
//		else result = -1;
		
		return result;
	}

	private int errorAnalisis(ArrayOfBytes response)
	{
		if (_writeLog) Common.log("errorAnalisis");

		int error=0;
		String tmpError="";

//		tmpError+=(char)(response.at(4));
//		tmpError+=(char)(response.at(5));

//		error=Integer.parseInt(tmpError, 16);

		return error;
	}

	private int getResponse(ArrayOfBytes response)
	{
		if (_writeLog) Common.log("getResponse");

		int error=0;

//		ArrayOfBytes fromPort = new ArrayOfBytes();
//		boolean startByteWasReceived=false;
//		int resultLength=0;
//
//
//		response.clear();
//		int resultReadPort = 0;
//
//		for (int i=0; ;i++)
//		{
//			fromPort.clear();
//			if (readPort(fromPort))
//			{
//				resultReadPort=1;
//			}
//			else
//			{
//				resultReadPort = testConnect(fromPort);
//
//				if(resultReadPort==1)
//				{
//					//Common.log("!!!!! get another byte on test connect!!!");
//				}
//				else if(resultReadPort==-1)
//				{
//					error=NO_RESPONSE_FR;
//					break;
//				}
//
//			}
//
//			if (resultReadPort==1)
//			{
//				if (fromPort.at(0)==(byte)(0x02)) startByteWasReceived=true;
//				if (startByteWasReceived==true)
//				{
//					response.append(fromPort.at(0));
//				}
//				if (fromPort.at(0)==(byte)(0x03)) resultLength=response.length()+2;
//
//				if ((response.length()==resultLength)&&(response.length()>0))
//				{
//
//					error=errorAnalisis(response);
//
//					break;
//
//				}
//
//			}
//
//		}


		return error;
	}
	
	private int transaction(ArrayOfBytes toPort, ArrayOfBytes result)
	{
		if (_writeLog) Common.log("transaction");
		int error=0;

		if (!writePort(toPort)) error = ERROR_SEND;

		if (error == 0) error=getResponse(result);

		return error;
	}

	public String getKkmType() throws FrException
	{
	    if (_writeLog) Common.log("getKkmType");
	    int error=0;
	    String result="";
	    String version="";

		result="CW1000";

		if (_writeLog) Common.log(result);

	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getKkmVersion() throws FrException
	{
	    if (_writeLog) Common.log("getKkmVersion");
	    int error=0;
        String result="";

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

//		commandStr.append(0x02);
//		commandStr.append("PONE");
//		commandStr.append(id());
//		commandStr.append("A5");
//		commandStr.append("0");
//		commandStr.append(0x1C);
//		commandStr.append(0x03);
//
//
//		if (error==0) error=transaction(CRC(commandStr), getStr);
//
//		if (error==0)
//		{
//			ArrayOfBytes tmp = new ArrayOfBytes();
//			tmp=getStr.mid(6);
//			result=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");
//
//			if (_writeLog) Common.log(result);
//		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getSerialNumber() throws FrException
	{
		if (_writeLog) Common.log("getSerialNumber");
		int error=0;
		String result="";

		result="";

		if (_writeLog) Common.log(result);

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

		return result;
	}

	public String getPrinterInfo() throws FrException
	{
	    if (_writeLog) Common.log("getPrinterInfo");
	    int error=0;
		String result="";

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

//		commandStr.append(0x02);
//		commandStr.append("PONE");
//		commandStr.append(id());
//		commandStr.append("AF");
//		commandStr.append("6");
//		commandStr.append(0x1C);
//		commandStr.append(0x03);
//
//
//		if (error==0) error=transaction(CRC(commandStr), getStr);
//
//		if (error==0)
//		{
//			ArrayOfBytes tmp = new ArrayOfBytes();
//			tmp=getStr.mid(6);
//			result+="boot-";
//			result+=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");
//
//			if (_writeLog) Common.log(result);
//		}
//
//		if (error==0)
//		{
//			commandStr.clear();
//
//			commandStr.append(0x02);
//			commandStr.append("PONE");
//			commandStr.append(id());
//			commandStr.append("AF");
//			commandStr.append("7");
//			commandStr.append(0x1C);
//			commandStr.append(0x03);
//
//			error=transaction(CRC(commandStr), getStr);
//		}
//
//		if (error==0)
//		{
//			ArrayOfBytes tmp = new ArrayOfBytes();
//			tmp=getStr.mid(6);
//			result+=" flash-";
//			result+=tmp.mid(0, tmp.indexOf(0x1C)).toString("CP866");
//
//			//if (_writeLog)
//				Common.log("result = "+result);
//		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public String getCurrentStatus() throws FrException
	{
	    if (_writeLog) Common.log("getCurrentStatus");
	    int error=0;
        String result="";
		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

//		commandStr.append(0x02);
//		commandStr.append("PONE");
//		commandStr.append(id());
//		commandStr.append("A0");
//		commandStr.append(0x1C);
//		commandStr.append(0x03);
//
//
//		if (error==0) error=transaction(CRC(commandStr), getStr);
//
//		if (error==0)
//		{
//			result=getStr.mid(6, 1).toString();
//			result+=getStr.mid(8, 1).toString();
//			result+=getStr.mid(10, 1).toString();
//
//			if (_writeLog) Common.log(result);
//		}


	    if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

	    return result;
	}

	public int init() throws FrException
	{
		if (_writeLog) Common.log("init");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int printText(String text) throws FrException
	{
		if (_writeLog) Common.log("printText");

		int error=0;

		ArrayOfBytes commandStr=new ArrayOfBytes();
		ArrayOfBytes getStr=new ArrayOfBytes();

		commandStr.append(text, "CP866");
		commandStr.append(0x0A);

		if (error==0) error=transaction(commandStr, getStr);

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int printTextEx(String text, int mask) throws FrException
	{
		if (_writeLog) Common.log("printTextEx");

		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int printImage(BufferedImage image) throws FrException
	{
		if (_writeLog) Common.log("printImage");
		int error=0;

		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		ArrayOfBytes imageArray=new ArrayOfBytes();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;		
	}

	public int printImageFromFile(String filePath) throws FrException
	{
		if (_writeLog) Common.log("printImageFromFile");
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

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error; // Example: printBarCode(2, 40, "Code 39", "1234567890");

	}

	public int printQrCodeFast(String codeText) throws FrException
	{
		if (_writeLog) Common.log("printQrCodeFast");
		int error=0;


		ArrayOfBytes getStr=new ArrayOfBytes();
		ArrayOfBytes commandStr=new ArrayOfBytes();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;		
	}


	public int printQrCode(String codeText) throws FrException
	{
		if (_writeLog) Common.log("printQrCode");
		int error=0;


		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int eraseLogotype() throws FrException
	{
		if (_writeLog) Common.log("eraseLogotype");
		int error=0;

		ArrayOfBytes commandStr=new ArrayOfBytes();
		ArrayOfBytes getStr=new ArrayOfBytes();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int printLogotype() throws FrException
	{
		if (_writeLog) Common.log("printLogotype");
		int error=0;

		ArrayOfBytes commandStr=new ArrayOfBytes();
		commandStr.append(0x1c);
		commandStr.append(0x70);
		commandStr.append(0x01);
		commandStr.append(0x00);


		if (_printLogotype == true) {
			if (!writePort(commandStr)) error = ERROR_SEND;
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}

	public int loadLogotype(String filePath) throws FrException
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

		byte invertor = bmpArray[0x3A];

		int realImageWidth = (imageWidth/8);
		if (((imageWidth/8)%4)>0) realImageWidth = ((realImageWidth/4)*4+4);
		int realImageHeight = imageHeight;
		if ((realImageHeight%8)>0) realImageHeight = realImageHeight+(8-(realImageHeight%8));

		ArrayOfBytes bmpArrayToPrinter=new ArrayOfBytes();
		bmpArrayToPrinter.append(0x1C);
		bmpArrayToPrinter.append(0x71);
		bmpArrayToPrinter.append(0x01);
		bmpArrayToPrinter.append(realImageWidth);
		bmpArrayToPrinter.append(0);
		bmpArrayToPrinter.append(realImageHeight/8);
		bmpArrayToPrinter.append(0);

		for(int byteOfWidth=0; byteOfWidth<realImageWidth; byteOfWidth++)
		{
			for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
			{
				int currentBitInByteOfHeight=0;
				int byteOfImageForPrinter = 0;
				for (int bitOfHeight=realImageHeight-1; bitOfHeight>=0; bitOfHeight--)
				{
					if (bitOfHeight<imageHeight)
					{
						int currentByte = startByteOfImageData + (bitOfHeight * realImageWidth) + byteOfWidth;

						if (((byteOfWidth*8)+currentBitInByteOfWidth)< imageWidth) {
							if ((bmpArray[currentByte] & (128 >> currentBitInByteOfWidth))>0)
								byteOfImageForPrinter += (128 >> currentBitInByteOfHeight);
						}
						else byteOfImageForPrinter += ((invertor&128) >> currentBitInByteOfHeight);
					}
					else byteOfImageForPrinter += ((invertor&128) >> currentBitInByteOfHeight);

					currentBitInByteOfHeight++;
					if (currentBitInByteOfHeight == 8) {
						bmpArrayToPrinter.append(byteOfImageForPrinter^invertor);
						byteOfImageForPrinter = 0;
						currentBitInByteOfHeight = 0;
					}

				}
			}
		}


		if (error == 0) {
			if (!writePort(bmpArrayToPrinter)) error = ERROR_SEND;
		}

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;
	}
}





