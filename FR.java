import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

abstract class FR
{
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


	public static final int ERROR_COMMUNICATION=1004;


	public class StringFromPort
	{
		private String _str="";

		public void StringFromPort()
		{
			_str="";
		}
		public void StringFromPort(String str)
		{
			_str=str;
		}
		public void setStr(String str)
		{
			_str=str;
		}
		public String getStr()
		{
			return _str;
		}

		public int length()
		{
			return _str.length();
		}
	}

	public class ArrayOfBytes
	{
		private byte bytesArray[]=new byte [0];

		ArrayOfBytes()
		{

		}

		ArrayOfBytes(int ... addBytes)
		{
			//this.append(addByte);
			for(int i=0;i<addBytes.length;i++) this.append(addBytes[i]);
		}

		ArrayOfBytes(byte addByte)
		{
			this.append(addByte);
			//for(byte i:addByte) this.append(i);
		}

		ArrayOfBytes(byte addBytes[])
		{
			this.append(addBytes);
		}

		public byte at(int i)
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

	}

	public static void Log(String str)
	{
		LogConsole(str);
	}

	public static void LogConsole(String str)
	{
		String strDateTime;
		
		Date dt= new Date();
		strDateTime = new SimpleDateFormat("yyyy.dd.MM HH:mm:ss:SSS - ").format(dt);

		System.out.printf(strDateTime);
		System.out.println(str);
	}

    abstract public void openPort(String portName, String baud);

	abstract public  int Init();

	abstract public  int OpenDocument(String docType, String depType, String operName, String docNumber);

	abstract public  int AddItem(String itemName, String articul, String qantity, String cost, String depType, String taxType);

	abstract public  int Total();

	abstract public  int Pay(String payType, String sum, String text);

	abstract public  int CancelDocument();

	abstract public  int CloseDocument(String text);

	abstract public  int Xreport(String text);

	abstract public  int Zreport(String text);

	abstract public  int ReceiptSale();

}



