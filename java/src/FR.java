import java.io.*;
import java.nio.*;
import java.util.Date;

class FrException extends Exception
{

	private String _errorCode;
	private String _errorDetail;


	FrException(String errorCode, String errorDetail)
	{
		_errorCode=errorCode;
		_errorDetail=errorDetail;
	}

	public int getErrorCodeAsInt()
	{
		return Integer.parseInt(_errorCode);
	}

	public String getErrorCodeAsString()
	{
		return _errorCode;
	}

	public String toString()
	{
		return "FrException : Error - "+_errorCode+" - "+_errorDetail;
	}
}

abstract class FR
{
	private boolean _wrileLog=false;

	public static final String PAY_TYPE_0="CASH_0";
	public static final String PAY_TYPE_1="CASH_1";
	public static final String PAY_TYPE_2="CASH_2";
	public static final String PAY_TYPE_3="CASH_3";
	public static final String PAY_TYPE_4="CASH_4";
	public static final String PAY_TYPE_5="CASH_5";
	public static final String PAY_TYPE_6="CASH_6";
	public static final String PAY_TYPE_7="CASH_7";
	public static final String PAY_TYPE_8="CASH_8";
	public static final String PAY_TYPE_9="CASH_9";
	public static final String PAY_TYPE_10="CASH_10";
	public static final String PAY_TYPE_11="CASH_11";
	public static final String PAY_TYPE_12="CASH_12";
	public static final String PAY_TYPE_13="CASH_13";
	public static final String PAY_TYPE_14="CASH_14";
	public static final String PAY_TYPE_15="CASH_15";
	
	public static final String RECEIPT_TYPE_SALE="RECEIPT_TYPE_SALE";
	public static final String RECEIPT_TYPE_RETURN_SALE="RECEIPT_TYPE_RETURN_SALE";
	public static final String RECEIPT_TYPE_NON_FISCAL_DOCUMENT="RECEIPT_TYPE_NON_FISCAL_DOCUMENT";

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

		public int atUnsignedInt(int i)
		{
			return bytesArray[i]&0xFF;
		}		

		public String atHex(int i)
		{
			int localInt=0;
			
			if(bytesArray[i]>0) localInt=bytesArray[i];
			else localInt=bytesArray[i]&0xFF;
			
			String result=Integer.toHexString(localInt);
			if (result.length()==1) result="0"+result;
			
			return result;
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

		public void appendIntAsByteArray(int addInt)
		{
		    ByteBuffer bb = ByteBuffer.allocate(4); 
		    bb.putInt(addInt); 
    		this.append(bb.array());
		}

		public void appendCharAsByteArray(char addChar)
		{
		    ByteBuffer bb = ByteBuffer.allocate(2); 
		    bb.putChar(addChar); 
    		this.append(bb.array());
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

			    	//log(String.format("%02x", this.at(i))+" == "+String.format("%02x", anotherBytesArray.at(i)));

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

		public ArrayOfBytes mid(int startByte, int length)
		{
			if ((this.length()-1)<startByte) return new ArrayOfBytes();
			if (length<1) return new ArrayOfBytes();
			
			int localStart=startByte;
			int localLength=length;
			if (startByte<0) localStart=0;	
			if ((this.length()-localStart)<length) localLength=this.length()-localStart;


			return new ArrayOfBytes(java.util.Arrays.copyOfRange(bytesArray, localStart, localStart+localLength));        	
		}

		public ArrayOfBytes mid(int startByte)
		{
			if ((this.length()-1)<startByte) return new ArrayOfBytes();
			
			int localStart=startByte;
			if (startByte<0) localStart=0;	

			return new ArrayOfBytes(java.util.Arrays.copyOfRange(bytesArray, localStart, this.length()-1));        	
		}

		public String toHex()
		{
			int localInt=0;
			String result="";
			for (int i=0;i<length();i++) 
			{
				if(bytesArray[i]>0) localInt=bytesArray[i];
				else localInt=bytesArray[i]&0xFF;
				result+=Integer.toHexString(localInt);
			}
			return result;
		}		

		public String toString()
		{
			return new String(bytesArray);      	
		}
		public String toString(String charsetName)
		{
			String result="";
			try
			{
				result = new String(bytesArray, charsetName);
			}
			catch(UnsupportedEncodingException ex)
			{
				result = new String(bytesArray);
			}
			return result;
		}

		public int indexOf(byte key)
		{
			for (int i=0; i<this.length();i++)
			{
				if (this.at(i)==key) return i;
			}
			return -1; //java.util.Arrays.binarySearch(bytesArray, key);
		}

		public int indexOf(int key)
		{
			return this.indexOf((byte) key);
		}

	}

	public String rightJustified(String str, char ch, int length)
	{
		if (_wrileLog) Common.log("rightJustified");

		String out=str;
		while (out.length()<length)
		{
			out=ch+out;
		}
		return out;
	}

	public String leftJustified(String str, char ch, int length)
	{
		if (_wrileLog) Common.log("leftJustified");

		String out=str;
		while (out.length()<length)
		{
			out+=ch;
		}
		return out;
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


    abstract public void openPort(String portName, String baud) throws FrException;

	abstract public void closePort();

	abstract public String getKkmType() throws FrException;

    abstract public String getKkmVersion() throws FrException;

    abstract public String getLastShiftInFiscalMemory() throws FrException;

	abstract public int init() throws FrException;

	abstract public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException;

	abstract public int printText(String text) throws FrException;

	abstract public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException;

	abstract public int total() throws FrException;

	abstract public int pay(String payType, String sum, String text) throws FrException;

	abstract public int cancelDocument() throws FrException;

	abstract public int closeDocument(String text) throws FrException;

	abstract public int xReport(String text) throws FrException;

	abstract public int zReport(String text) throws FrException;

	abstract public int printQrCode(String url) throws FrException;

	abstract public int receiptSale() throws FrException;

	abstract public int printEklzReportFullByDate(Date from, Date to) throws FrException;
	abstract public int printEklzReportShortByDate(Date from, Date to) throws FrException;
	abstract public int printEklzReportFullByShift(int from, int to) throws FrException;
	abstract public int printEklzReportShortByShift(int from, int to) throws FrException; 
	abstract public int printEklzReportControlTape(int shift) throws FrException;


}



