/**
 * Created by s.baryagin on 06.06.2017.
 */
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AXIOHM extends PRINTER
{
    private SerialPort _serialPort;
//	private int _id=0x20;
//	private int _gettedBytes=0;


    //private boolean _writeLog=true;
    private boolean _writeLog=false;

    private boolean _printLogotype=true;

    private ArrayOfBytes _bENQ = new ArrayOfBytes();
    private ArrayOfBytes _bACK = new ArrayOfBytes();



    public AXIOHM()
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

        result="AXIOHM";

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


//        int imageWidth = 0;
//        int imageHeight = 0;
//
//        BufferedImage image;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try
//        {
//            image = ImageIO.read(new File(filePath));
//            ImageIO.write(image, "bmp", baos);
//            baos.flush();
//            baos.close();
//
//            imageWidth = image.getWidth();
//            imageHeight = image.getHeight();
//
//        }
//        catch (IOException ex)
//        {
//            ex.printStackTrace();
//            error=ANY_LOGICAL_ERROR;
//        }
//
//
//        byte[] bmpArray = baos.toByteArray();
//        int startByteOfImageData=bmpArray[10]; // see to https://en.wikipedia.org/wiki/BMP_file_format#Bitmap_file_header
//
//        ArrayOfBytes bmpArrayToPrinter=new ArrayOfBytes();
//        bmpArrayToPrinter.append(0x1C);
//        bmpArrayToPrinter.append(0x71);
//        bmpArrayToPrinter.append(0x01);
//
//        int realImageWidth = (imageWidth/8);
//        if (((imageWidth/8)%4)>0) realImageWidth = ((realImageWidth/4)*4+4);
//        int realImageHeight = imageHeight;
//        if ((realImageHeight%8)>0) realImageHeight = realImageHeight+(8-(realImageHeight%8));
//        bmpArrayToPrinter.append(realImageWidth);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(realImageHeight/8);
//        bmpArrayToPrinter.append(0);
//
//        for(int byteOfWidth=0; byteOfWidth<realImageWidth; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                int currentBitInByteOfHeight=0;
//                String byteOfImageForPrinter = "";
//                for (int bitOfHeight=realImageHeight-1; bitOfHeight>=0; bitOfHeight--)
//                {
//                    if (bitOfHeight<imageHeight)
//                    {
//                        int currentByte = byteOfWidth + (bitOfHeight * (realImageWidth)) + startByteOfImageData;
//
//                        System.out.println(Integer.toBinaryString(bmpArray[currentByte]) + " << " + currentBitInByteOfWidth + " == " + Integer.toBinaryString(bmpArray[currentByte] << currentBitInByteOfWidth) + " & 128 == " + Integer.toBinaryString((bmpArray[currentByte] << currentBitInByteOfWidth) & 128));
//                        if (((bmpArray[currentByte] << currentBitInByteOfWidth) & 128) == 128)
//                            byteOfImageForPrinter = byteOfImageForPrinter + "1";
//                        else byteOfImageForPrinter = byteOfImageForPrinter + "0";
//                    }
//                    else byteOfImageForPrinter = byteOfImageForPrinter + "0";
//
//                    currentBitInByteOfHeight++;
//                    if (currentBitInByteOfHeight == 8) {
//                        bmpArrayToPrinter.append(Integer.valueOf(byteOfImageForPrinter, 2));
//                        byteOfImageForPrinter = "";
//                        currentBitInByteOfHeight = 0;
//                    }
//
//                }
//            }
//        }
//
//
//        ArrayOfBytes send0A=new ArrayOfBytes();
//        send0A.append(0x0A);
//
//        ArrayOfBytes printLogo=new ArrayOfBytes();
//        printLogo.append(0x1c);
//        printLogo.append(0x70);
//        printLogo.append(0x01);
//        printLogo.append(0x01);
//
//        ArrayOfBytes cut=new ArrayOfBytes();
//        cut.append(0x1D);
//        cut.append(0x56);
//        cut.append(0x01);
//
//
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//
//
//        writePort(bmpArrayToPrinter);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//
//
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//
//        writePort(printLogo);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(send0A);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}
//        writePort(cut);
//        try {int w=200; Common.log("Pause "+w+" ms ..."); Thread.sleep(w);} catch (InterruptedException ie) {}

        return error;
    }

    public int printCode39(int width, int height, String codeText) throws FrException
    {
        if (_writeLog) Common.log("printBarCode");
        int error=0;

        ArrayOfBytes commandStr=new ArrayOfBytes();
        ArrayOfBytes getStr=new ArrayOfBytes();

        commandStr.append(0x1d);
        commandStr.append(0x6b);
        commandStr.append(0x45);
        commandStr.append(codeText.length());
//        commandStr.append(0x2a);
        commandStr.append(codeText);
//        commandStr.append(0x2a);

        if (error==0) error=transaction(commandStr, getStr);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error; // Example: printBarCode(2, 40, "Code 39", "1234567890");

    }
    public int printCode128(int width, int height, String codeText) throws FrException
    {
        if (_writeLog) Common.log("printBarCode");
        int error=0;

        ArrayOfBytes commandStr=new ArrayOfBytes();
        ArrayOfBytes getStr=new ArrayOfBytes();

        commandStr.append(0x1d);
        commandStr.append(0x6b);
        commandStr.append(0x49);
        commandStr.append(codeText.length()+1);
        commandStr.append(0x67);
        for (int i=0;i<codeText.length();i++) commandStr.append(codeText.charAt(i)-0x20);
//        commandStr.append(0x2a);

        if (error==0) error=transaction(commandStr, getStr);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error; // Example: printBarCode(2, 40, "Code 39", "1234567890");

    }
    public int printBarCode(int width, int height, String codeType, String codeText) throws FrException
    {
        if (_writeLog) Common.log("printBarCode");
        int error=0;

//        String localWidth=Integer.valueOf(width).toString();
//        String localHight=Integer.valueOf(height).toString();

        ArrayOfBytes commandStr=new ArrayOfBytes();
        ArrayOfBytes getStr=new ArrayOfBytes();

        if (error==0)
        {
            // Set Barcode width
            commandStr.clear();
            commandStr.append(0x1d);
            commandStr.append(0x77);
            commandStr.append(width);
            error=transaction(commandStr, getStr);
        }

        if (error==0)
        {
            // Set Barcode height
            commandStr.clear();
            commandStr.append(0x1d);
            commandStr.append(0x68);
            commandStr.append(height);
            error=transaction(commandStr, getStr);
        }

        if (error==0)
        {
            // Set position of HRI characters
            commandStr.clear();
            commandStr.append(0x1d);
            commandStr.append(0x48);
            commandStr.append(0x02);
            error=transaction(commandStr, getStr);
        }

        if (error==0)
        {
            // Set pitch of HRI characters
            commandStr.clear();
            commandStr.append(0x1d);
            commandStr.append(0x66);
//            commandStr.append(0x01);
            commandStr.append(0);
            error=transaction(commandStr, getStr);
        }

        if (error==0)
        {
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
                    error = printCode39(width, height, codeText);
                    localCodeType="4";
                    break;
                case "Interleaved 2 of 5":
                    localCodeType="5";
                    break;
                case "Codabar":
                    localCodeType="6";
                    break;
                case "Code 128":
                    error = printCode128(width, height, codeText);
                    localCodeType="7";
                    break;

            }
        }

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

//        ArrayOfBytes commandStr=new ArrayOfBytes();
//        commandStr.append(0x1c);
//        commandStr.append(0x70);
//        commandStr.append(0x01);
//        commandStr.append(0x00);
//
//
//        if (_printLogotype == true) {
//            if (!writePort(commandStr)) error = ERROR_SEND;
//        }

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int loadLogotype(String filePath) throws FrException
    {
        if (_writeLog) Common.log("loadLogotype");
        int error=0;

//        int imageWidth = 0;
//        int imageHeight = 0;
//
//        BufferedImage image;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try
//        {
//            image = ImageIO.read(new File(filePath));
//            ImageIO.write(image, "bmp", baos);
//            baos.flush();
//            baos.close();
//
//            imageWidth = image.getWidth();
//            imageHeight = image.getHeight();
//
//        }
//        catch (IOException ex)
//        {
//            ex.printStackTrace();
//            error=ANY_LOGICAL_ERROR;
//        }
//
//
//        byte[] bmpArray = baos.toByteArray();
//        int startByteOfImageData=bmpArray[10]; // see to https://en.wikipedia.org/wiki/BMP_file_format#Bitmap_file_header
//
//        byte invertor = bmpArray[0x3A];
//
//        int realImageWidth = (imageWidth/8);
//        if (((imageWidth/8)%4)>0) realImageWidth = ((realImageWidth/4)*4+4);
//        int realImageHeight = imageHeight;
//        if ((realImageHeight%8)>0) realImageHeight = realImageHeight+(8-(realImageHeight%8));
//
//        ArrayOfBytes bmpArrayToPrinter=new ArrayOfBytes();
//        bmpArrayToPrinter.append(0x1C);
//        bmpArrayToPrinter.append(0x71);
//        bmpArrayToPrinter.append(0x01);
//        bmpArrayToPrinter.append(realImageWidth);
//        bmpArrayToPrinter.append(0);
//        bmpArrayToPrinter.append(realImageHeight/8);
//        bmpArrayToPrinter.append(0);
//
//        for(int byteOfWidth=0; byteOfWidth<realImageWidth; byteOfWidth++)
//        {
//            for(int currentBitInByteOfWidth=0; currentBitInByteOfWidth<8; currentBitInByteOfWidth++)
//            {
//                int currentBitInByteOfHeight=0;
//                int byteOfImageForPrinter = 0;
//                for (int bitOfHeight=realImageHeight-1; bitOfHeight>=0; bitOfHeight--)
//                {
//                    if (bitOfHeight<imageHeight)
//                    {
//                        int currentByte = startByteOfImageData + (bitOfHeight * realImageWidth) + byteOfWidth;
//
//                        if (((byteOfWidth*8)+currentBitInByteOfWidth)< imageWidth) {
//                            if ((bmpArray[currentByte] & (128 >> currentBitInByteOfWidth))>0)
//                                byteOfImageForPrinter += (128 >> currentBitInByteOfHeight);
//                        }
//                        else byteOfImageForPrinter += ((invertor&128) >> currentBitInByteOfHeight);
//                    }
//                    else byteOfImageForPrinter += ((invertor&128) >> currentBitInByteOfHeight);
//
//                    currentBitInByteOfHeight++;
//                    if (currentBitInByteOfHeight == 8) {
//                        bmpArrayToPrinter.append(byteOfImageForPrinter^invertor);
//                        byteOfImageForPrinter = 0;
//                        currentBitInByteOfHeight = 0;
//                    }
//
//                }
//            }
//        }
//
//
//        if (error == 0) {
//            if (!writePort(bmpArrayToPrinter)) error = ERROR_SEND;
//        }

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
}





