import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SHTRIHDRV extends FR
{
    private SerialPort _serialPort;
	private int _gettedBytes=0;

	//private boolean _writeLog=true;
	private boolean _writeLog=false;

	private String _receiptType="";

    private SHTRIHDRV_JNI _shtrihNativeObject = new SHTRIHDRV_JNI();

	public SHTRIHDRV()
	{

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
            case 1:    str="Ошибка ФП: Неисправен накопитель ФП 1, ФП 2 или часы"; break;
            case 2:    str="Ошибка ФП: Отсутствует ФП 1"; break;
            case 3:    str="Ошибка ФП: Отсутствует ФП 2"; break;
            case 4:    str="Ошибка ФП: Некорректные параметры в команде обращения к ФП"; break;
            case 5:    str="Ошибка ФП: Нет запрошенных данных "; break;
            case 6:    str="Ошибка ФП: ФП в режиме вывода данных"; break;
            case 7:    str="Ошибка ФП: Некорректные параметры в команде для данной реализации ФП"; break;
            case 8:    str="Ошибка ФП: Команда не поддерживается в данной реализации ФП"; break;
            case 9:    str="Ошибка ФП: Некорректная длина команды "; break;
            case 10:   str="Ошибка ФП: Формат данных не BCD "; break;
            case 11:   str="Ошибка ФП: Неисправна ячейка памяти ФП при записи итога "; break;
            case 17:   str="Ошибка ФП: Не введена лицензия"; break;
            case 18:   str="Ошибка ФП: Заводской номер уже введен "; break;
            case 19:   str="Ошибка ФП: Текущая дата меньше даты последней записи в ФП "; break;
            case 20:   str="Ошибка ФП: Область сменных итогов ФП переполнена"; break;
            case 21:   str="Ошибка ФП: Смена уже открыта"; break;
            case 22:   str="Ошибка ФП: Смена не открыта"; break;
            case 23:   str="Ошибка ФП: Номер первой смены больше номера последней смены"; break;
            case 24:   str="Ошибка ФП: Дата первой смены больше даты последней смены"; break;
            case 25:   str="Ошибка ФП: Нет данных в ФП"; break;
            case 26:   str="Ошибка ФП: Область перерегистраций в ФП переполнена"; break;
            case 27:   str="Ошибка ФП: Заводской номер не введен"; break;
            case 28:   str="Ошибка ФП: В заданном диапазоне есть поврежденная запись"; break;
            case 29:   str="Ошибка ФП: Повреждена последняя запись сменных итогов "; break;
            case 30:   str="Ошибка ФП: Область перерегистраций ФП переполнена "; break;
            case 31:   str="Ошибка ФП: Отсутствует память регистров "; break;
            case 32:   str="Ошибка ФП: Переполнение денежного регистра при добавлении "; break;
            case 33:   str="Ошибка ФП: Вычитаемая сумма больше содержимого денежного регистра "; break;
            case 34:   str="Ошибка ФП: Неверная дата"; break;
            case 35:   str="Ошибка ФП: Нет записи активизации"; break;
            case 36:   str="Ошибка ФП: Область активизаций переполнена"; break;
            case 37:   str="Ошибка ФП: Нет активизации с запрашиваемым номером"; break;
            case 38:   str="Ошибка ФР: Вносимая клиентом сумма меньше суммы чека"; break;
            case 39:   str="Ошибка ФР: Повреждение контрольных сумм ФП"; break;
            case 43:   str="Ошибка ФР: Невозможно отменить предыдущую команду "; break;
            case 44:   str="Ошибка ФР: Обнулённая касса (повторное гашение невозможно)"; break;
            case 45:   str="Ошибка ФР: Сумма чека по секции меньше суммы сторно"; break;
            case 46:   str="Ошибка ФР: В ФР нет денег для выплаты "; break;
            case 48:   str="Ошибка ФР: ФР заблокирован, ждет ввода пароля налогового инспектора"; break;
            case 50:   str="Ошибка ФР: Требуется выполнение общего гашения"; break;
            case 51:   str="Ошибка ФР: Некорректные параметры в команде"; break;
            case 52:   str="Ошибка ФР: Нет данных "; break;
            case 53:   str="Ошибка ФР: Некорректный параметр при данных настройках"; break;
            case 54:   str="Ошибка ФР: Некорректные параметры в команде для данной реализации ФР"; break;
            case 55:   str="Ошибка ФР: Команда не поддерживается в данной реализации ФР"; break;
            case 56:   str="Ошибка ФР: Ошибка в ПЗУ"; break;
            case 57:   str="Ошибка ФР: Внутренняя ошибка ПО ФР"; break;
            case 58:   str="Ошибка ФР: Переполнение накопления по надбавкам в смене"; break;
            case 59:   str="Ошибка ФР: Переполнение накопления в смене"; break;
            case 60:   str="Ошибка ФР: ЭКЛЗ: неверный регистрационный номер "; break;
            case 61:   str="Ошибка ФР: Смена не открыта - операция невозможна "; break;
            case 62:   str="Ошибка ФР: Переполнение накопления по секциям в смене "; break;
            case 63:   str="Ошибка ФР: Переполнение накопления по скидкам в смене "; break;
            case 64:   str="Ошибка ФР: Переполнение диапазона скидок"; break;
            case 65:   str="Ошибка ФР: Переполнение диапазона оплаты наличными"; break;
            case 66:   str="Ошибка ФР: Переполнение диапазона оплаты типом 2"; break;
            case 67:   str="Ошибка ФР: Переполнение диапазона оплаты типом 3"; break;
            case 68:   str="Ошибка ФР: Переполнение диапазона оплаты типом 4"; break;
            case 69:   str="Ошибка ФР: Cумма всех типов оплаты меньше итога чека"; break;
            case 70:   str="Ошибка ФР: Не хватает наличности в кассе"; break;
            case 71:   str="Ошибка ФР: Переполнение накопления по налогам в смене "; break;
            case 72:   str="Ошибка ФР: Переполнение итога чека"; break;
            case 73:   str="Ошибка ФР: Операция невозможна в открытом чеке данного типа"; break;
            case 74:   str="Ошибка ФР: Открыт чек - операция невозможна"; break;
            case 75:   str="Ошибка ФР: Буфер чека переполнен"; break;
            case 76:   str="Ошибка ФР: Переполнение накопления по обороту налогов в смене "; break;
            case 77:   str="Ошибка ФР: Вносимая безналичной оплатой сумма больше суммы чека"; break;
            case 78:   str="Ошибка ФР: Смена превысила 24 часа"; break;
            case 79:   str="Ошибка ФР: Неверный пароль"; break;
            case 80:   str="Ошибка ФР: Идет печать предыдущей команды "; break;
            case 81:   str="Ошибка ФР: Переполнение накоплений наличными в смене"; break;
            case 82:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 2 в смене"; break;
            case 83:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 3 в смене"; break;
            case 84:   str="Ошибка ФР: Переполнение накоплений по типу оплаты 4 в смене"; break;
            case 85:   str="Ошибка ФР: Чек закрыт - операция невозможна"; break;
            case 86:   str="Ошибка ФР: Нет документа для повтора"; break;
            case 87:   str="Ошибка ФР: ЭКЛЗ: количество закрытых смен не совпадает с ФП "; break;
            case 88:   str="Ошибка ФР: Ожидание команды продолжения печати"; break;
            case 89:   str="Ошибка ФР: Документ открыт другим оператором"; break;
            case 90:   str="Ошибка ФР: Скидка превышает накопления в чеке "; break;
            case 91:   str="Ошибка ФР: Переполнение диапазона надбавок"; break;
            case 92:   str="Ошибка ФР: Понижено напряжение 24В"; break;
            case 93:   str="Ошибка ФР: Таблица не определена"; break;
            case 94:   str="Ошибка ФР: Некорректная операция"; break;
            case 95:   str="Ошибка ФР: Отрицательный итог чека"; break;
            case 96:   str="Ошибка ФР: Переполнение при умножении "; break;
            case 97:   str="Ошибка ФР: Переполнение диапазона цены"; break;
            case 98:   str="Ошибка ФР: Переполнение диапазона количества"; break;
            case 99:   str="Ошибка ФР: Переполнение диапазона отдела"; break;
            case 100:  str="Ошибка ФР: ФП отсутствует "; break;
            case 101:  str="Ошибка ФР: Не хватает денег в секции"; break;
            case 102:  str="Ошибка ФР: Переполнение денег в секции"; break;
            case 103:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 104:  str="Ошибка ФР: Не хватает денег по обороту налогов"; break;
            case 105:  str="Ошибка ФР: Переполнение денег по обороту налогов"; break;
            case 106:  str="Ошибка ФР: Ошибка питания в момент ответа по I2C"; break;
            case 107:  str="Ошибка ФР: Нет чековой ленты"; break;
            case 108:  str="Ошибка ФР: Нет контрольной ленты"; break;
            case 109:  str="Ошибка ФР: Не хватает денег по налогу "; break;
            case 110:  str="Ошибка ФР: Переполнение денег по налогу"; break;
            case 111:  str="Ошибка ФР: Переполнение по выплате в смене"; break;
            case 112:  str="Ошибка ФР: Переполнение ФП"; break;
            case 113:  str="Ошибка ФР: Ошибка отрезчика"; break;
            case 114:  str="Ошибка ФР: Команда не поддерживается в данном подрежиме"; break;
            case 115:  str="Ошибка ФР: Команда не поддерживается в данном режиме"; break;
            case 116:  str="Ошибка ФР: Ошибка ОЗУ "; break;
            case 117:  str="Ошибка ФР: Ошибка питания "; break;
            case 118:  str="Ошибка ФР: Ошибка принтера: нет импульсов с тахогенератора"; break;
            case 119:  str="Ошибка ФР: Ошибка принтера: нет сигнала с датчиков"; break;
            case 120:  str="Ошибка ФР: Замена ПО"; break;
            case 121:  str="Ошибка ФР: Замена ФП"; break;
            case 122:  str="Ошибка ФР: Поле не редактируется"; break;
            case 123:  str="Ошибка ФР: Ошибка оборудования"; break;
            case 124:  str="Ошибка ФР: Не совпадает дата"; break;
            case 125:  str="Ошибка ФР: Неверный формат даты"; break;
            case 126:  str="Ошибка ФР: Неверное значение в поле длины "; break;
            case 127:  str="Ошибка ФР: Переполнение диапазона итога чека"; break;
            case 128:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 129:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 130:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 131:  str="Ошибка ФР: Ошибка связи с ФП"; break;
            case 132:  str="Ошибка ФР: Переполнение наличности"; break;
            case 133:  str="Ошибка ФР: Переполнение по продажам в смене"; break;
            case 134:  str="Ошибка ФР: Переполнение по покупкам в смене"; break;
            case 135:  str="Ошибка ФР: Переполнение по возвратам продаж в смене"; break;
            case 136:  str="Ошибка ФР: Переполнение по возвратам покупок в смене"; break;
            case 137:  str="Ошибка ФР: Переполнение по внесению в смене"; break;
            case 138:  str="Ошибка ФР: Переполнение по надбавкам в чеке"; break;
            case 139:  str="Ошибка ФР: Переполнение по скидкам в чеке "; break;
            case 140:  str="Ошибка ФР: Отрицательный итог надбавки в чеке "; break;
            case 141:  str="Ошибка ФР: Отрицательный итог скидки в чеке"; break;
            case 142:  str="Ошибка ФР: Нулевой итог чека"; break;
            case 143:  str="Ошибка ФР: Касса не фискализирована "; break;
            case 144:  str="Ошибка ФР: Поле превышает размер, установленный в настройках"; break;
            case 145:  str="Ошибка ФР: Выход за границу поля печати при данных настройках шрифта"; break;
            case 146:  str="Ошибка ФР: Наложение полей"; break;
            case 147:  str="Ошибка ФР: Восстановление ОЗУ прошло успешно"; break;
            case 148:  str="Ошибка ФР: Исчерпан лимит операций в чеке "; break;
            case 160:  str="Ошибка ФР: Ошибка связи с ЭКЛЗ"; break;
            case 161:  str="Ошибка ФР: ЭКЛЗ отсутствует "; break;
            case 162:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Некорректный формат или параметр команды "; break;
            case 163:  str="Ошибка ЭКЛЗ: Некорректное состояние ЭКЛЗ"; break;
            case 164:  str="Ошибка ЭКЛЗ: Авария ЭКЛЗ"; break;
            case 165:  str="Ошибка ЭКЛЗ: Авария КС в составе ЭКЛЗ"; break;
            case 166:  str="Ошибка ЭКЛЗ: Исчерпан временной ресурс ЭКЛЗ "; break;
            case 167:  str="Ошибка ЭКЛЗ: ЭКЛЗ переполнена"; break;
            case 168:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Неверные дата и время"; break;
            case 169:  str="Ошибка ЭКЛЗ: ЭКЛЗ: Нет запрошенных данных"; break;
            case 170:  str="Ошибка ЭКЛЗ: Переполнение ЭКЛЗ (отрицательный итог документа)"; break;
            case 176:  str="Ошибка ФР: ЭКЛЗ: Переполнение в параметре количество"; break;
            case 177:  str="Ошибка ФР: ЭКЛЗ: Переполнение в параметре сумма "; break;
            case 178:  str="Ошибка ФР: ЭКЛЗ: Уже активизирована "; break;
            case 192:  str="Ошибка ФР: Контроль даты и времени (подтвердите дату и время)"; break;
            case 193:  str="Ошибка ФР: ЭКЛЗ: суточный отчёт с гашением прервать нельзя"; break;
            case 194:  str="Ошибка ФР: Превышение напряжения в блоке питания"; break;
            case 195:  str="Ошибка ФР: Несовпадение итогов чека и ЭКЛЗ"; break;
            case 196:  str="Ошибка ФР: Несовпадение номеров смен"; break;
            case 197:  str="Ошибка ФР: Буфер подкладного документа пуст"; break;
            case 198:  str="Ошибка ФР: Подкладной документ отсутствует"; break;
            case 199:  str="Ошибка ФР: Поле не редактируется в данном режиме"; break;
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

        error=_shtrihNativeObject.nativeConnect(Integer.parseInt(portNameOnlyDigits), Integer.valueOf(baud));

        Common.log("Error = "+ error);
    }

    public void closePort()
    {
        int error=0;

        Common.log("ClosePort");

        error=_shtrihNativeObject.nativeClosePort();

        Common.log("Error = "+ error);
    }

    private int getEndOfPrinting()
    {
        if (_writeLog) Common.log("getEndOfPrinting");
        int error=0;

        Common.log("getEndOfPrinting");

        if (error==0) error=_shtrihNativeObject.nativeGetEndOfPrinting();

        Common.log("Error = "+ error);
        return error;
    }

    public int getShortStatus() throws FrException
    {
        if (_writeLog) Common.log("getShortStatus");
        int error=0;
        String result="";

        Common.log("GetShortECRStatus");
        if (error==0) result=_shtrihNativeObject.nativeGetShortECRStatus();
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
        if (error==0) error=_shtrihNativeObject.nativeSetDate(currentDate);
        Common.log("Error = "+ error);

        if (error==0) getShortStatus();


        Common.log("ConfirmDate");
        if (error==0) error=_shtrihNativeObject.nativeConfirmDate(currentDate);
        Common.log("Error = "+ error);

        return error;
    }

    public int setCurrentTime() throws FrException
    {
        if (_writeLog) Common.log("SetCurrentTime");
        int error=0;

        String currentTime=curTime();

        Common.log("SetTime");
        if (error==0) error=_shtrihNativeObject.nativeSetTime(currentTime);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return error;
    }

    public String getKkmType() throws FrException
    {
        if (_writeLog) Common.log("getKkmType");
        int error=0;
        String result="";

        Common.log("GetKkmType");
        if (error==0) result=_shtrihNativeObject.nativeGetKkmType();
        if (error==0) error=_shtrihNativeObject.nativeGetResultCode();

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return result;
    }
    public String getKkmVersion() throws FrException
    {
        if (_writeLog) Common.log("getKkmVersion");
        int error=0;
        String result="";


        Common.log("GetKkmVersion");
        if (error==0) result=_shtrihNativeObject.nativeGetKkmVersion();
        if (error==0) error=_shtrihNativeObject.nativeGetResultCode();

        if (_writeLog) Common.log(result);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));

        return result;
    }

    public String getLastShiftInFiscalMemory() throws FrException
    {
        if (_writeLog) Common.log("getLastShiftInFiscalMemory");
        int error=0;
        String result="";

        Common.log("GetLastShiftInFiscalMemory");
        if (error==0) result=_shtrihNativeObject.nativeGetLastShiftInFiscalMemory();
        if (error==0) error=_shtrihNativeObject.nativeGetResultCode();

        if (_writeLog) Common.log(result);

        Common.log("Error = "+ error);
        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return result;
    }


	public int init() throws FrException
	{
		if (_writeLog) Common.log("Init");
        int error=0;

        Common.log("Beep");
		if (error==0) error=_shtrihNativeObject.nativeBeep();
        Common.log("Error = "+ error);

        if (error==0) getShortStatus();
        if (error==0) error= setCurrentDate();
        if (error==0) getShortStatus();
        if (error==0) error= setCurrentTime();
        if (error==0) getShortStatus();

        //Common.log("Error - "+error+" - "+getErrorDetails(error));

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int openDocument(String docType, String depType, String operName, String docNumber) throws FrException
	{
		if (_writeLog) Common.log("OpenDocument");

		_receiptType=docType;

		return 0;

	}

    public int printText(String text) throws FrException
    {
        if (_writeLog) Common.log("printText");
        int error=0;

        Common.log("CheckSubTotal");
        if (error==0) error=_shtrihNativeObject.nativePrintText(text);
        Common.log("Error = "+ error);

        if (error==0) error=getEndOfPrinting();

        return 0;

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
                //if (error==0) error=_shtrihNativeObject.nativeBuy(itemName, articul, qantity, cost, depType, taxType);
                if (error==0) error=_shtrihNativeObject.nativeSale(itemName, articul, qantity, cost, depType, taxType);
                break;
			case "RECEIPT_TYPE_RETURN_SALE" :
                Common.log("ReturnSale");
                if (error==0) error=_shtrihNativeObject.nativeReturnSale(itemName, articul, qantity, cost, depType, taxType);
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

	public int total() throws FrException
	{
		if (_writeLog) Common.log("Total");

		int error=0;
        Common.log("CheckSubTotal");
        if (error==0) error=_shtrihNativeObject.nativeCheckSubTotal();
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
        if (error==0) error=_shtrihNativeObject.nativeCloseCheck(pay1, pay2, pay3, pay4, text);
        Common.log("Error = "+ error);


		if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int cancelDocument() throws FrException
	{
		if (_writeLog) Common.log("CancelDocument");

		int error=0;
        Common.log("CancelCheck");
        if (error==0) error=_shtrihNativeObject.nativeCancelCheck();
        Common.log("Error = "+ error);

        if (error==0) error=getEndOfPrinting();

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

        Common.log("Xreport");
        if (error==0) error=_shtrihNativeObject.nativeXreport();
        Common.log("Error = "+ error);

        if (error==0) error=getEndOfPrinting();

		if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
		return error;

	}

	public int zReport(String text) throws FrException
	{
		if (_writeLog) Common.log("Zreport");

        int error=0;

        Common.log("Zreport");
        if (error==0) error=_shtrihNativeObject.nativeZreport();
        Common.log("Error = "+ error);

        if (error==0) error=getEndOfPrinting();

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
        if (error==0) error=_shtrihNativeObject.nativePrintEklzReportFullByDate(strFrom, strTo);
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
        if (error==0) error=_shtrihNativeObject.nativePrintEklzReportShortByDate(strFrom, strTo);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();


        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportFullByShift(int from, int to) throws FrException{
        if (_writeLog) Common.log("printEklzReportFullByShift");
        int error=0;

        Common.log("PrintEklzReportFullByShift");
        if (error==0) error=_shtrihNativeObject.nativePrintEklzReportFullByShift(from, to);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportShortByShift(int from, int to) throws FrException{
        if (_writeLog) Common.log("printEklzReportShortByShift");
        int error=0;

        Common.log("PrintEklzReportShortByShift");
        if (error==0) error=_shtrihNativeObject.nativePrintEklzReportShortByShift(from, to);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }
    public int printEklzReportControlTape(int shift) throws FrException{
        if (_writeLog) Common.log("printEklzReportControlTape");
        int error=0;

        Common.log("PrintEklzReportControlTape");
        if (error==0) error=_shtrihNativeObject.nativePrintEklzReportControlTape(shift);
        Common.log("Error = "+ error);
        if (error==0) error=getEndOfPrinting();

        if (error!=0) throw new FrException(Integer.toString(error), getErrorDetails(error));
        return error;
    }


}