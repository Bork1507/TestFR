/**
 * Created by Bork on 29.11.2016.
 */
import jssc.SerialPort;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SPDRV extends FR
{
    private SerialPort _serialPort;
    private int _gettedBytes=0;

    private boolean _writeLog=true;
    //private boolean _writeLog=false;

    private String _receiptType="";

    private SPDRV_JNI _spNativeObject= new SPDRV_JNI();

    public SPDRV()
    {

    }

    public String deviceType() {
        return "FISCAL_PRINTER";
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
            case 0:
                break;
            case 1:
                str="Ошибка 01h - Функция невыполнима при данном статусе ККМ";
                break;
            case 2:
                str="Ошибка 02h - В команде указан неверный номер функции";
                break;
            case 3:
                str="Ошибка 03h - В команде указано неверное, больше чем максимально возможное или несоответствующее типу данных значение";
                break;
            case 4:
                str="Ошибка 04h - Переполнение буфера коммуникационного порта";
                break;
            case 5:
                str="Ошибка 05h - Таймаут при передаче байта информации";
                break;
            case 6:
                str="Ошибка 06h - В команде указан неверный пароль";
                break;
            case 7:
                str="Ошибка 07h - Ошибка контрольной суммы в команде";
                break;
            case 8:
                str="Ошибка 08h - Конец бумаги";
                break;
            case 9:
                str="Ошибка 09h - Принтер не готов";
                break;
            case 10:
                str="Ошибка 0Ah - Текущая смена больше 24 часов";
                break;
            case 11:
                str="Ошибка 0Bh - Разница во времени, ККМ и указанной в команде установки времени, больше 8 минут";
                break;
            case 12:
                str="Ошибка 0Ch - Время последнего документа больше нового времени более чем на один час (с учетом летнего/зимнего перехода)";
                break;
            case 13:
                str="Ошибка 0Dh - Не был задан заголовок документа, что делает невозможным формирование фискального документа.";
                break;
            case 14:
                str="Ошибка 0Eh - Отрицательный результат";
                break;
            case 15:
                str="Ошибка 0Fh - Дисплей покупателя не готов";
                break;
            case 32:
                str="Ошибка 20h - Фатальная ошибка ККМ";
                break;
            case 33:
                str="Ошибка 21h - Нет свободного места в фискальной памяти ККМ";
                break;
            case 65:
                str="Ошибка 41h - Некорректный формат или параметр команды";
                break;
            case 66:
                str="Ошибка 42h - Некорректное состояние ЭКЛЗ";
                break;
            case 67:
                str="Ошибка 43h - Авария  ЭКЛЗ";
                break;
            case 68:
                str="Ошибка 44h - Авария  КС  (Криптографического сопроцессора) в составе ЭКЛЗ";
                break;
            case 69:
                str="Ошибка 45h - Исчерпан временной ресурс использования ЭКЛЗ";
                break;
            case 70:
                str="Ошибка 46h - ЭКЛЗ  переполнена";
                break;
            case 71:
                str="Ошибка 47h - Неверные дата или время";
                break;
            case 72:
                str="Ошибка 48h - Нет запрошенных данных";
                break;
            case 73:
                str="Ошибка 49h - Переполнение (отрицательный итог документа, слишком много отделов для клиента)";
                break;
            case 74:
                str="Ошибка 4Ah - Нет ответа от ЭКЛЗ";
                break;
            case 75:
                str="Ошибка 4Bh - Ошибка при обмене данными с ЭКЛЗ";
                break;
            default:
                str=FR.getErrorDetails(error);
                break;
        }
        return str;
    }


    public void openPort(String portName, String baud) throws FrException
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(portName);
        int start = 0;
        String portNameOnlyDigits="";
        while (matcher.find(start)) {
            portNameOnlyDigits += portName.substring(matcher.start(), matcher.end());
            start = matcher.end();
        }

        int error=0;

        Common.log("Connect");

        error=_spNativeObject.nativeConnect(Integer.parseInt(portNameOnlyDigits), Integer.valueOf(baud));

        Common.log("Error = "+ error);
    }

    public void closePort()
    {
        int error=0;

        Common.log("ClosePort");

        error=_spNativeObject.nativeClosePort();

        Common.log("Error = "+ error);
    }

    private int getEndOfPrinting()
    {
        if (_writeLog) Common.log("getEndOfPrinting");
        int error=0;

        Common.log("getEndOfPrinting");

        if (error==0) error=_spNativeObject.nativeGetEndOfPrinting();

        Common.log("Error = "+ error);
        return error;
    }

    public int getShortStatus() throws FrException
    {
        if (_writeLog) Common.log("getShortStatus");
        int error=0;
        String result="";

        Common.log("GetShortECRStatus");
        if (error==0) result=_spNativeObject.nativeGetShortECRStatus();
        Common.log("ShortECRStatus = "+ result);
        Common.log("Error = "+ error);

        return error;
    }


    public int setCurrentDate() throws FrException
    {
        if (_writeLog) Common.log("SetCurrentDate");
        int error=0;

        String currentDate=curDate();


        try{
            //"Special for RETAIL-01K. Bugs in switching status!"
            Common.log("Pause "+Integer.parseInt("1000")+" ms ...");
            Thread.sleep(Integer.parseInt("1000"));
        } catch (InterruptedException ie) {}

        Common.log("SetDate");
        if (error==0) error=_spNativeObject.nativeSetDate(currentDate);
        Common.log("Error = "+ error);

        if (error==0) getShortStatus();


        Common.log("ConfirmDate");
        if (error==0) error=_spNativeObject.nativeConfirmDate(currentDate);
        Common.log("Error = "+ error);

        return error;
    }

    public int setCurrentTime() throws FrException
    {
        if (_writeLog) Common.log("SetCurrentTime");
        int error=0;

        String currentTime=curTime();

        Common.log("SetTime");
        if (error==0) error=_spNativeObject.nativeSetTime(currentTime);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return error;
    }

    public String getKkmType() throws FrException
    {
        if (_writeLog) Common.log("getKkmType");
        int error=0;
        String result="";
        String version="";

        try
        {
            version=getKkmVersion();
            switch(version)
            {
                case "128":
                    result="СП101ФР-К";
                    break;
                case "130":
                    result="СП101ФР-К";
                    break;
                case "132":
                    result="СП101ФР-К";
                    break;
                case "160":
                    result="СП101-Ф";
                    break;
                case "402":
                    result="СП402ФР-К";
                    break;
                case "403":
                    result="СП402ФР-К";
                    break;
                case "404":
                    result="СП402ФР-К";
                    break;
                case "414":
                    result="СП412ФР";
                    break;
                case "614":
                    result="СП614БУ";
                    break;
                case "460":
                    result="СП402-Ф";
                    break;
                case "601":
                    result="СП601-К";
                    break;
            }
        }
        catch (FrException frEx)
        {
            error=frEx.getErrorCodeAsInt();
        }

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return result;
    }
    public String getKkmVersion() throws FrException
    {
        if (_writeLog) Common.log("getKkmVersion");
        int error=0;
        String result="";

        if (error==0) result=_spNativeObject.nativeGetKkmVersion();
        if (error==0) error=_spNativeObject.nativeGetResultCode();

        if (_writeLog) Common.log(result);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return result;
    }

    public String getSerialNumber() throws FrException
    {
        if (_writeLog) Common.log("getSerialNumber");
        int error=0;
        String result="";

        if (error==0) result=_spNativeObject.nativeGetSerialNumber();
        if (error==0) error=_spNativeObject.nativeGetResultCode();

        if (_writeLog) Common.log(result);
        Common.log("Error = "+ error);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return result;
    }

    public String getKkmParameter(int rowNumber, int columnNumber) throws FrException
    {
        if (_writeLog) Common.log("getKkmParameter");
        int error=0;
        String result="";

        return result;
    }

    public int setKkmParameter(int rowNumber, int columnNumber, String value) throws FrException {
        if (_writeLog) Common.log("setKkmParameter");
        int error=0;

        return error;
    }

    public String getLastShiftInFiscalMemory() throws FrException
    {
        if (_writeLog) Common.log("getLastShiftInFiscalMemory");
        int error=0;
        String result="";

        Common.log("GetLastShiftInFiscalMemory");
        if (error==0) result=_spNativeObject.nativeGetLastShiftInFiscalMemory();
        if (error==0) error=_spNativeObject.nativeGetResultCode();

        if (_writeLog) Common.log(result);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return result;
    }


    public int init() throws FrException
    {
        if (_writeLog) Common.log("Init");
        int error=0;

        if (error==0) error=_spNativeObject.nativeInit();
        Common.log("Error = "+ error);

//        if (error==0) getShortStatus();
//        if (error==0) error= setCurrentDate();
//        if (error==0) getShortStatus();
//        if (error==0) error= setCurrentTime();
//        if (error==0) getShortStatus();

        //Common.log("Error - "+error+" - "+getErrorDetails(error));

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException
    {
        if (_writeLog) Common.log("OpenDocument");
        int error=0;

        switch (docType)
        {
            case "RECEIPT_TYPE_NON_FISCAL_DOCUMENT" :
                docType="1";
                break;
            case "RECEIPT_TYPE_SALE" :
                docType="2";
                break;
            case "RECEIPT_TYPE_RETURN_SALE" :
                docType="3";
                break;
            case "RECEIPT_TYPE_CASHIN" :
                docType="4";
                break;
            case "RECEIPT_TYPE_CASHOUT" :
                docType="5";
                break;
            default:
                docType="0";
                break;
        }

        if (error==0) error=_spNativeObject.nativeOpenDocument(Integer.valueOf(docType), Integer.valueOf(depType), operName, Integer.valueOf(docNumber));

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int printText(String text) throws FrException {
        if (_writeLog) Common.log("printText");
        int error = 0;

        if (error==0) error=_spNativeObject.nativePrintText(text);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return 0;

    }

    public int printTextEx(String text, int mask) throws FrException
    {
        if (_writeLog) Common.log("printTextEx");

        int error=0;

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int addItem(String itemName, String articul, String qantity, String cost, String depType, String taxType) throws FrException
    {
        if (_writeLog) Common.log("AddItem");
        int error=0;

        //int intReceiptType=0;
        //qantity=qantity.replace(".", "").replace(",", "");
        cost=cost.replace(".", "").replace(",", "");
        cost+="00"; //bank's format

        switch (_receiptType)
        {
            case "RECEIPT_TYPE_SALE" :
                Common.log("Sale");
                //if (error==0) error=_spNativeObject.nativeBuy(itemName, articul, qantity, cost, depType, taxType);
                if (error==0) error=_spNativeObject.nativeSale(itemName, articul, qantity, cost, depType, taxType);
                break;
            case "RECEIPT_TYPE_RETURN_SALE" :
                Common.log("ReturnSale");
                if (error==0) error=_spNativeObject.nativeReturnSale(itemName, articul, qantity, cost, depType, taxType);
                break;
            default:
                //intReceiptType=0;
                break;
        }

        if (error==0) error=getEndOfPrinting();
//
        getShortStatus();

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int addCashInCashOutSum(String itemName, String sum) throws FrException
    {
        if (_writeLog) Common.log("addCashInCashOutSum");
        int error=0;

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }


    public int total() throws FrException
    {
        if (_writeLog) Common.log("Total");

        int error=0;
        Common.log("CheckSubTotal");
        if (error==0) error=_spNativeObject.nativeCheckSubTotal();
        Common.log("Error = "+ error);

        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int pay(String payType, String sum, String text) throws FrException
    {
        if (_writeLog) Common.log("Pay");

        sum=sum.replace(".", "").replace(",", "");
        sum+="00"; //bank's format

        String pay1="";
        String pay2="";
        String pay3="";
        String pay4="";

        switch (payType)
        {
            case "CASH_0" :
                pay1=sum;
                pay2="0";
                pay3="0";
                pay4="0";
                break;
            case "CASH_1" :
                pay1="0";
                pay2=sum;
                pay3="0";
                pay4="0";
                break;
            case "CASH_2" :
                pay1="0";
                pay2="0";
                pay3=sum;
                pay4="0";
                break;
            case "CASH_3" :
                pay1="0";
                pay2="0";
                pay3="0";
                pay4=sum;
                break;
            case "CASH_4" :
            case "CASH_5" :
            case "CASH_6" :
            case "CASH_7" :
            case "CASH_8" :
            case "CASH_9" :
            case "CASH_10":
            case "CASH_11":
            case "CASH_12":
            case "CASH_13":
            case "CASH_14":
            case "CASH_15":
            default:
                pay1=sum;
                pay2="0";
                pay3="0";
                pay4="0";
                break;
        }


        int error=0;
        Common.log("CloseCheck");
        if (error==0) error=_spNativeObject.nativeCloseCheck(pay1, pay2, pay3, pay4, text);
        Common.log("Error = "+ error);


        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int cancelDocument() throws FrException
    {
        if (_writeLog) Common.log("CancelDocument");

        int error=0;
        if (error==0) error=_spNativeObject.nativeCancelDocument();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int closeDocument(String text) throws FrException
    {
        if (_writeLog) Common.log("CloseDocument");

        _receiptType="";

        return 0;

    }


    public int xReport(String text) throws FrException
    {
        if (_writeLog) Common.log("Xreport");

        int error=0;

        if (error==0) error=_spNativeObject.nativeXreport(text);
        Common.log("Error = "+ error);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int zReport(String text) throws FrException
    {
        if (_writeLog) Common.log("Zreport");

        int error=0;

        if (error==0) error=_spNativeObject.nativeZreport(text);
        Common.log("Error = "+ error);

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }

    public int printImage(BufferedImage image) throws FrException
    {
        if (_writeLog) Common.log("printImage");
        int error=0;


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int printImageExt(BufferedImage image) throws FrException
    {
        if (_writeLog) Common.log("printImage");
        int error=0;


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }


    public int printImageSpecialFor(BufferedImage image) throws FrException
    {
        if (_writeLog) Common.log("printImage");
        int error=0;


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }



    public  int printQrCode(String codeText) throws FrException
    {
        if (_writeLog) Common.log("printQrCode");
        int error=0;

        String kkmType="";
        String kkmVersion="";

        int imageWidth=300;
        int imageHeight=300;


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int eraseLogotype() throws FrException
    {
        if (_writeLog) Common.log("eraseLogotype");
        int error=0;

        return error;
    }

    public int printLogotype() throws FrException
    {
        if (_writeLog) Common.log("printLogotype");
        int error=0;

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int loadLogotype(String filePath) throws FrException
    {
        if (_writeLog) Common.log("loadLogotype");
        int error=0;

        return error;
    }

    public int receiptSale() throws FrException
    {
        if (_writeLog) Common.log("ReceiptSale");
        int error=0;

        if (error==0) error=openDocument("2", "0", "Test", "0");
        if (error==0) error=addItem("тест", "1234567", "1.000", "123.45", "0", "");
        if (error==0) error=total();
        if (error==0) error=pay("0", "1000.00", "");
        if (error==0) error=closeDocument("");

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }

    public int printEklzReportFullByDate(Date from, Date to) throws FrException{
        if (_writeLog) Common.log("printEklzReportFullByDate");
        int error=0;

        String strFrom = new SimpleDateFormat("ddMMyy").format(from);
        String strTo = new SimpleDateFormat("ddMMyy").format(to);

        Common.log("PrintEklzReportFullByDate");
        if (error==0) error=_spNativeObject.nativePrintEklzReportFullByDate(strFrom, strTo);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportShortByDate(Date from, Date to) throws FrException{
        if (_writeLog) Common.log("printEklzReportShortByDate");
        int error=0;

        String strFrom = new SimpleDateFormat("ddMMyy").format(from);
        String strTo = new SimpleDateFormat("ddMMyy").format(to);

        Common.log("PrintEklzReportShortByDate");
        if (error==0) error=_spNativeObject.nativePrintEklzReportShortByDate(strFrom, strTo);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportFullByShift(int from, int to) throws FrException{
        if (_writeLog) Common.log("printEklzReportFullByShift");
        int error=0;

        Common.log("PrintEklzReportFullByShift");
        if (error==0) error=_spNativeObject.nativePrintEklzReportFullByShift(from, to);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportShortByShift(int from, int to) throws FrException{
        if (_writeLog) Common.log("printEklzReportShortByShift");
        int error=0;

        Common.log("PrintEklzReportShortByShift");
        if (error==0) error=_spNativeObject.nativePrintEklzReportShortByShift(from, to);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportControlTape(int shift) throws FrException{
        if (_writeLog) Common.log("printEklzReportControlTape");
        int error=0;

        Common.log("PrintEklzReportControlTape");
        if (error==0) error=_spNativeObject.nativePrintEklzReportControlTape(shift);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int testJNIfunctions(String text) throws FrException{
        int error=0;

        //if (error == 0) error = _spNativeObject.nativeInstall(curDate(), curTime(), "123456789012");
        //if (error == 0) error = _spNativeObject.nativeInstallEx(curDate(), curTime(), "0053R0136763");

        int resultInt = 0;
        String result = "";
        String quantity = "";

        if (error == 0) error = _spNativeObject.nativeInit();

        resultInt = _spNativeObject.nativeGetPrinterStatus();
        error = _spNativeObject.nativeGetResultCode();
        Common.log("Error = " + error);
        Common.log("Result = " + Integer.toString(resultInt));

        //if (error==0) error=_spNativeObject.nativePrintJournal(text);

        if (error==0) error=_spNativeObject.nativePrintZCopy(1, 1);
        if (error==0) error=_spNativeObject.nativePrintZCopy(2, 1);
        if (error==0) error=_spNativeObject.nativePrintZCopy(9, 0);
        if (error==0) error=_spNativeObject.nativePrintZCopy(0, 0);
        if (error==0) error=_spNativeObject.nativePrintZCopy(3, 0);


//
//        if (error==0) error=_spNativeObject.nativeJournalPrint(text);
//        if (error == 0) result = _spNativeObject.nativeJournalRead(1, 0); // получить номер текущей контрольной ленты;
//        if (error == 0) error = _spNativeObject.nativeGetResultCode();
//        Common.log("Error = " + error);
//        Common.log(result);
//        if (error == 0) quantity = _spNativeObject.nativeJournalRead(2, 0); // получить количество записей в текущей контрольной ленте;
//        if (error == 0) error = _spNativeObject.nativeGetResultCode();
//        Common.log("Error = " + error);
//        Common.log(quantity);
//        if (error == 0) result = _spNativeObject.nativeJournalRead(4, 2); //поиск чека в контрольной ленте по порядковому номеру чека (положение в контрольной ленте);
//        if (error == 0) error = _spNativeObject.nativeGetResultCode();
//        Common.log("Error = " + error);
//        Common.log(result);
//        result = _spNativeObject.nativeJournalRead(5, 218); //поиск чека в контрольной ленте по номеру чека;
//        error = _spNativeObject.nativeGetResultCode();
//        Common.log("Error = " + error);
//        Common.log(result);
//        for (int i = 0; i < Integer.parseInt(quantity); i++)
//        {       result = _spNativeObject.nativeJournalRead(8, i); //запрос записи из контрольной ленты по номеру записи (получение шестнадцатеричного представления записи).
//                error = _spNativeObject.nativeGetResultCode();
//                Common.log("Error = " + error);
//                Common.log(result);
//        }


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int fiscal54Fz() throws FrException{
        if (_writeLog) Common.log("fiscal54");
        int error=0;

        ArrayOfBytes getStr=new ArrayOfBytes();
        ArrayOfBytes commandStr=new ArrayOfBytes();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;

    }
}
