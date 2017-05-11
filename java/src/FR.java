import java.io.*;
import java.nio.*;
import java.util.Date;

abstract class FR extends PosDevice
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
	public static final String RECEIPT_TYPE_CASHIN="RECEIPT_TYPE_CASHIN";
	public static final String RECEIPT_TYPE_CASHOUT="RECEIPT_TYPE_CASHOUT";

	public static String getErrorDetails(int error)
	{
		String str="";

	    switch (error)
	    {
	        default:
				str=PosDevice.getErrorDetails(error);
	            break;
	    }

	    return str;
	}


	abstract public String deviceType();

    abstract public void openPort(String portName, String baud) throws FrException;

	abstract public void closePort();

	abstract public String getKkmType() throws FrException;

    abstract public String getKkmVersion() throws FrException;

	abstract public String getSerialNumber() throws FrException;

    abstract public String getLastShiftInFiscalMemory() throws FrException;

	abstract public String getKkmParameter(int rowNumber, int columnNumber) throws FrException;

	abstract public int setKkmParameter(int rowNumber, int columnNumber, String value) throws FrException;

	abstract public int init() throws FrException;

	abstract public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException;

	abstract public int printText(String text) throws FrException;

	abstract public int printTextEx(String text, int mask) throws FrException;

	abstract public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException;

	abstract public int addCashInCashOutSum(String itemName, String sum) throws FrException;

	abstract public int total() throws FrException;

	abstract public int pay(String payType, String sum, String text) throws FrException;

	abstract public int cancelDocument() throws FrException;

	abstract public int closeDocument(String text) throws FrException;

	abstract public int xReport(String text) throws FrException;

	abstract public int zReport(String text) throws FrException;

	abstract public int printQrCode(String url) throws FrException;

	abstract public int eraseLogotype() throws FrException;
	abstract public int loadLogotype(String filePath) throws FrException;
	abstract public int printLogotype() throws FrException;

	abstract public int receiptSale() throws FrException;

	abstract public int printEklzReportFullByDate(Date from, Date to) throws FrException;
	abstract public int printEklzReportShortByDate(Date from, Date to) throws FrException;
	abstract public int printEklzReportFullByShift(int from, int to) throws FrException;
	abstract public int printEklzReportShortByShift(int from, int to) throws FrException; 
	abstract public int printEklzReportControlTape(int shift) throws FrException;

	abstract public int testJNIfunctions(String text) throws FrException;

	abstract public int fiscal54Fz() throws FrException;
}



