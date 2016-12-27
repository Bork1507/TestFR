/**
 * Created by Bork on 02.12.2016.
 */

public class SPOLE_JNI {
    static{
        String osType=System.getProperty("os.name");
        String programDir=System.getProperty("user.dir");
        System.out.printf(osType);
        System.out.printf("\n");
        if (osType.contains("Linux")){
            System.load( programDir+"/java/lib/linux/SPOLE_JNI.so" );
        }
        else{
            System.load( programDir+"/java/lib/windows/SPOLE_JNI.dll" );
        }
    }

    public native int nativeConnect(int portNumber, int baud);

    public native int nativeCutAndPrint();

    public native String nativeGetShortECRStatus();

    public native int nativeGetResultCode();

    public native int nativeGetEndOfPrinting();

    public native String nativeGetKkmType();
    public native String nativeGetKkmVersion();
    public native String nativeGetSerialNumber();

    public native String nativeGetLastShiftInFiscalMemory();

    public native int nativeInit(String inputDate, String inputTime);

    public native int nativeInstall(String inputDate, String inputTime, String serialNumber);
    public native int nativeInstallEx(String inputDate, String inputTime, String serialNumber);

    public native int nativeSetDate(String inputDate);
    public native int nativeConfirmDate(String inputDate);
    public native int nativeSetTime(String inputTime);

    public native int nativePrintText(String text);

    public native int nativeBuy(String itemName, String articul, String qantity, String cost, String depType, String taxType);
    public native int nativeSale(String itemName, String articul, String qantity, String cost, String depType, String taxType);
    public native int nativeReturnSale(String itemName, String articul, String qantity, String cost, String depType, String taxType);

    public native int nativeCheckSubTotal();
    public native int nativeCloseCheck(String pay1, String pay2, String pay3, String pay4, String text);

    public native int nativeCancelCheck();

    public native int nativeXreport(String operatorName);
    public native int nativeZreport(String operatorName);

    public native int nativeJournalPrint(String operatorName);
    public native String nativeJournalRead(int operation, int parameter);
    public native int nativeGetJournalNumber(); //return JournalNumber
    public native int nativeGetJournalRecordCount(); //return JournalRecordCount
    public native String nativeGetJournalRecord(int RecordNumber);
    public native int nativeGetJournalReceiptByIndex(int ReceiptIndex); //return JournalRecordNumber
    public native int nativeGetJournalReceiptByNumber(int ReceiptNumber); //return JournalRecordNumber

    public native int nativePrintEklzReportFullByDate(String from, String to);
    public native int nativePrintEklzReportShortByDate(String from, String to);
    public native int nativePrintEklzReportFullByShift(int from, int to);
    public native int nativePrintEklzReportShortByShift(int from, int to);
    public native int nativePrintEklzReportControlTape(int shift);

    public native int nativeClosePort();

    public native int fiscal54Fz();
}
