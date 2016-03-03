import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.file.*;

class FrException extends Exception
{

	private String _errorCode;
	private String _errorDetail;


	FrException(String errorCode, String errorDetail)
	{
		_errorCode=errorCode;
		_errorDetail=errorDetail;
	}

	public String toString()
	{
		return "FrException : Error - "+_errorCode+" - "+_errorDetail;
	}
}

abstract class FR
{
	private boolean _wrileLog=false;

	public static final String PayType0="Cash0";
	public static final String PayType1="Cash1";
	public static final String PayType2="Cash2";
	public static final String PayType3="Cash3";
	public static final String PayType4="Cash4";
	public static final String PayType5="Cash5";
	public static final String PayType6="Cash6";
	public static final String PayType7="Cash7";
	public static final String PayType8="Cash8";
	public static final String PayType9="Cash9";
	public static final String PayType10="Cash10";
	public static final String PayType11="Cash11";
	public static final String PayType12="Cash12";
	public static final String PayType13="Cash13";
	public static final String PayType14="Cash14";
	public static final String PayType15="Cash15";
	
	public static final String ReceiptTypeSale="ReceiptTypeSale";
	public static final String ReceiptTypeReturnSale="ReceiptTypeReturnSale";

	public static final int ERROR_PORT          =1001;
	public static final int ERROR_CONNECT       =1002;
	public static final int END_OF_FILE         =1003;
	public static final int NO_RESPONSE_FR      =1004;
	public static final int FILE_NOT_FOUND      =1005;
	public static final int FR_IS_NOT_SUPPORTED =1006;
	public static final int FR_NOT_FISCAL       =1007;
	public static final int ERROR_PAY_TYPE      =1008;
	public static final int ERROR_SET_BAUD      =1010;
	public static final int ANY_LOGICAL_ERROR   =1011;
	public static final int ERROR_SEND          =1013;


	public class ArrayOfBytes
	{
		private byte bytesArray[]=new byte [0];

		ArrayOfBytes()
		{

		}

		ArrayOfBytes(int ... addBytes)
		{
			for(int i=0;i<addBytes.length;i++) this.append(addBytes[i]);
			//for(byte i:addBytes) this.append(i);
		}

		ArrayOfBytes(byte addByte)
		{
			this.append(addByte);
		}

		ArrayOfBytes(byte addBytes[])
		{
			this.append(addBytes);
		}

		public byte at(int i)
		{
			return bytesArray[i];
		}		

		public int atInt(int i)
		{
			return bytesArray[i];
		}		

		public void append(byte addBytes[])
		{
			byte tmp[]=new byte[bytesArray.length+addBytes.length];
			int i=0;
			for(;i<bytesArray.length;i++)
			{
				tmp[i]=bytesArray[i];
			}
			for(int j=0;j<addBytes.length;i++,j++)
			{
				tmp[i]=addBytes[j];
			}
			bytesArray=tmp;
		}

		public void append(byte addByte)
		{
			byte tmp[]=new byte[bytesArray.length+1];
			int i=0;
			for(;i<bytesArray.length;i++)
			{
				tmp[i]=bytesArray[i];
			}
			
			tmp[i]=addByte;
			bytesArray=tmp;
		}

		public void append(ArrayOfBytes addByte)
		{
			this.append(addByte.getBytes());
		}

		public void append(int addByte)
		{
			this.append((byte)(addByte));
		}

		public void append(String addString)
		{
			this.append(addString.getBytes());
		}

		public void append(String addString, String charsetName)
		{
			try 
			{
				this.append(addString.getBytes(charsetName));
			}
			catch (UnsupportedEncodingException ie) {
			    System.out.println("Unsupported character set");
			}			
		}

		public byte[] getBytes()
		{
			return bytesArray;
		}		
		
		public int length()
		{
			return bytesArray.length;
		}		

		public void clear()
		{
			bytesArray=new byte [0];
		}		

		public boolean equals(ArrayOfBytes anotherBytesArray)
		{
			boolean result=true;

			if (this.length()==anotherBytesArray.length())
			{
				for(int i=0;i<this.length();i++)
				{
					if(this.at(i)!=anotherBytesArray.at(i))
					{
						result=false;
						break;
					}

			    	//Log(String.format("%02x", this.at(i))+" == "+String.format("%02x", anotherBytesArray.at(i)));

				}
			}
			else result=false;

			return result;
		}		

		public void set(int i, int setByte)
		{
			if (i<this.length())
			{
				bytesArray[i]=(byte)setByte;
			}
		}		
		public void set(int i, byte setByte)
		{
			if (i<this.length())
			{
				bytesArray[i]=(byte)setByte;
			}
		}		


	}

	public String rightJustified(String str, char ch, int length)
	{
		if (_wrileLog) Log("rightJustified");

		String out=str;
		while (out.length()<length)
		{
			out=ch+out;
		}
		return out;
	}

	public String leftJustified(String str, char ch, int length)
	{
		if (_wrileLog) Log("leftJustified");

		String out=str;
		while (out.length()<length)
		{
			out+=ch;
		}
		return out;
	}


	public static void Log(String str)
	{
		LogConsole(str);
		LogFile(str);
	}

	public static void LogConsole(String str)
	{
		// String consoleEncoding = System.getProperty("consoleEncoding");
		// if (consoleEncoding != null) {
		//     try {
		//         System.setOut(new PrintStream(System.out, true, consoleEncoding));
		//     } catch (java.io.UnsupportedEncodingException ex) {
		//         System.err.println("Unsupported encoding set for console: "+consoleEncoding);
		//     }
		// }

		// System.out.println(consoleEncoding);



		String strDateTime;
		
		Date dt= new Date();
		strDateTime = new SimpleDateFormat("yyyy.dd.MM HH:mm:ss:SSS - ").format(dt);

		System.out.printf(strDateTime);
		System.out.println(str);
	}

	public static void LogFile(String str)
	{
		String strDateTime;
		String strFileName;
		String strSlash="/";
		String strPath="Logs";

		try {Files.createDirectory(Paths.get(strPath));}
		catch(IOException e){}

		
		Date dt= new Date();
		strDateTime = new SimpleDateFormat("yyyy.dd.MM HH:mm:ss:SSS - ").format(dt);
		strFileName = strPath+strSlash;
		strFileName += new SimpleDateFormat("yyyyddMM").format(dt);;
		strFileName += ".log";

		File file = new File(strFileName);

		try 
		{
			if(!file.exists())
			{
				file.createNewFile();
			}

			FileWriter out = new FileWriter(file.getAbsoluteFile(), true);

			try 
			{
				out.write(strDateTime+str+'\n');
			} 
			finally 
			{
				out.close();
			}
		} 
		catch(IOException e) 
		{
			throw new RuntimeException(e);
	    }

	}




	public static String getErrorDetails(int error)
	{
	    String str="Error ";
	    str+=error;
	    str+="!";
	    switch (error)
	    {
	        case ERROR_PORT:
	            str="Error 1001! Error opening port!";
	            break;
	        case ERROR_CONNECT:
	            str="Error 1002! Error connect!";
	            break;
	        case END_OF_FILE:
	            str="Error 1003! End of file!";
	            break;
	        case NO_RESPONSE_FR:
	            str="Error 1004! Error connection!";
	            break;
	        case FILE_NOT_FOUND:
	            str="Error 1005! File not found!";
	            break;
	        case FR_IS_NOT_SUPPORTED:
	            str="Error 1006! POS type not supported!!";
	            break;
	        case FR_NOT_FISCAL:
	            str="Error 1007! POS not fiskal!";
	            break;
	        case ERROR_PAY_TYPE:
	            str="Error 1008! Error pay type!";
	            break;
	        case ERROR_SET_BAUD:
	            str="Error 1010! Error set baud!";
	            break;
	        case ANY_LOGICAL_ERROR:
	            str="Error 1011! Any logical error!";
	            break;
	        case ERROR_SEND:
	            str="Error 1013! Error sending command!";
	            break;
	        default:
	            break;
	    }

	    return str;
	}


    abstract public void openPort(String portName, String baud) throws FrException;;

	abstract public  int Init() throws FrException;

	abstract public  int OpenDocument(String docType, String depType, String operName, String docNumber) throws FrException;

	abstract public  int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException;

	abstract public  int Total() throws FrException;

	abstract public  int Pay(String payType, String sum, String text) throws FrException;

	abstract public  int CancelDocument() throws FrException;

	abstract public  int CloseDocument(String text) throws FrException;

	abstract public  int Xreport(String text) throws FrException;

	abstract public  int Zreport(String text) throws FrException;

	abstract public  int ReceiptSale() throws FrException;

}



