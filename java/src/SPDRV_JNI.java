/**
 * Created by Bork on 29.11.2016.
 */
public class SPDRV_JNI {
    static{
        String osType=System.getProperty("os.name");
        String programDir=System.getProperty("user.dir");
        System.out.printf(osType);
        if (osType.contains("Linux")){
            System.load( programDir+"/java/lib/linux/SPDRV_JNI.so" );
        }
        else{
            System.load( programDir+"/java/lib/windows/SPDRV_JNI.dll" );
        }
    }

    public native int nativeConnect(int portNumber, int baud);

    public native String nativeGetShortECRStatus();

    public native int nativeGetResultCode();

    public native int nativeGetEndOfPrinting();

    public native String nativeGetKkmType();
    public native String nativeGetKkmVersion();

    public native String nativeGetLastShiftInFiscalMemory();

    public native int nativeInit();

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

    public native int nativeXreport();
    public native int nativeZreport();



    public native int nativePrintEklzReportFullByDate(String from, String to);
    public native int nativePrintEklzReportShortByDate(String from, String to);
    public native int nativePrintEklzReportFullByShift(int from, int to);
    public native int nativePrintEklzReportShortByShift(int from, int to);
    public native int nativePrintEklzReportControlTape(int shift);

    public native int nativeClosePort();

    public native int fiscal54Fz();
}
