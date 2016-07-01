// Файл drvfr.h собран вручную из файла DrvFRLib_TLB.h от примера Borland C++
#ifndef DRVFR_H
#define DRVFR_H

#include <windows.h>

extern "C" const __declspec(selectany) GUID LIBID_DrvFRLib = {0x332C8050, 0x469C, 0x4B4D,{ 0xA1, 0x92, 0x68,0xCD, 0x3C, 0xB2,0x52, 0xBA} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR = {0x2951939A, 0xB915, 0x4CC3,{ 0x8F, 0x87, 0x08,0x03, 0xB8, 0xEB,0x89, 0x4F} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR2 = {0x63097449, 0xEC60, 0x4E93,{ 0x80, 0x52, 0x60,0xCB, 0x8E, 0xC8,0xC9, 0x93} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR3 = {0xEFA76EB3, 0xA227, 0x43C5,{ 0x8A, 0xB0, 0xA8,0xF5, 0x69, 0x79,0x78, 0xA8} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR4 = {0xD0C91335, 0xA09D, 0x4A50,{ 0x95, 0x45, 0xD3,0x91, 0x90, 0xFF,0xD3, 0x64} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR6 = {0xAB5092AF, 0xAC33, 0x47B4,{ 0x9F, 0x63, 0xB6,0xB6, 0xA2, 0x14,0xBC, 0x82} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR7 = {0xD53A7F63, 0xEA3B, 0x4060,{ 0xA9, 0xEE, 0xC2,0x48, 0xFA, 0xAD,0x60, 0x88} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR8 = {0x112C3B2C, 0x94E4, 0x4405,{ 0xBF, 0x42, 0x59,0xEE, 0x54, 0xB5,0x3D, 0xBB} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR9 = {0xB5CE4922, 0xE17E, 0x41B8,{ 0xAB, 0xE0, 0xB0,0xC1, 0xF9, 0x41,0xAE, 0x0D} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR10 = {0xBC2E30D0, 0xDB1C, 0x4A16,{ 0x89, 0x56, 0x98,0x68, 0xC4, 0x68,0x64, 0x2C} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR11 = {0xE97A2450, 0x50A2, 0x4A9C,{ 0xAF, 0xA3, 0x14,0xA5, 0x50, 0x67,0xB2, 0xB0} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR12 = {0x3C9DA2CB, 0x0DBA, 0x4984,{ 0x90, 0x95, 0xBB,0x50, 0x68, 0xA0,0x25, 0x6F} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR13 = {0xAA64E75D, 0xA37F, 0x46CA,{ 0x93, 0xB1, 0x26,0x82, 0xB7, 0x0A,0xDE, 0x3C} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR14 = {0x634A3C3E, 0x8D9B, 0x415A,{ 0x82, 0x41, 0x34,0xAF, 0x9E, 0x86,0xDD, 0xF0} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR44 = {0xD3F9EDA6, 0xA5EA, 0x492A,{ 0x86, 0x1F, 0xAC,0x90, 0x09, 0xB4,0x80, 0xD9} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR45 = {0x2831E8FE, 0x1486, 0x4CAA,{ 0x89, 0x68, 0x5D,0xBE, 0x4D, 0x84,0x32, 0x6B} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR46 = {0x570BB7D4, 0xA58D, 0x47CB,{ 0x8D, 0xED, 0x04,0x51, 0x70, 0x68,0x7A, 0xB1} };
extern "C" const __declspec(selectany) GUID IID_IDrvFR47 = {0x6D5AE171, 0x6E17, 0x4E72,{ 0xB5, 0x12, 0x5F,0xE8, 0x9F, 0x5D,0x8E, 0x3B} };
extern "C" const __declspec(selectany) GUID CLSID_DrvFR = {0xE187099F, 0x8C5C, 0x4723,{ 0x88, 0x66, 0xD8,0xDB, 0xB6, 0x35,0x3A, 0xDE} };
extern "C" const __declspec(selectany) GUID GUID_TCashControlProtocol = {0x38E19061, 0x9C41, 0x401D,{ 0x9B, 0x07, 0xCB,0x22, 0x2C, 0xC8,0x60, 0xE7} };
extern "C" const __declspec(selectany) GUID GUID_TBarcodePrintType = {0xB7CC6477, 0x96ED, 0x4837,{ 0xA6, 0x23, 0xD6,0x8A, 0xCE, 0x01,0xEB, 0x56} };
extern "C" const __declspec(selectany) GUID GUID_TBarcodeAlignment = {0x397BDFE7, 0xA719, 0x4B99,{ 0xAE, 0x4F, 0x26,0x58, 0xD5, 0x14,0x28, 0xF9} };


interface IDrvFR  : public IDispatch
{
public:
  // [1] ДобавитьЛУ
  virtual HRESULT STDMETHODCALLTYPE AddLD(long* Res/*[out,retval]*/) = 0;
  // [2] Гудок
  virtual HRESULT STDMETHODCALLTYPE Beep(long* Res/*[out,retval]*/) = 0;
  // [3] Покупка
  virtual HRESULT STDMETHODCALLTYPE Buy(long* Res/*[out,retval]*/) = 0;
  // [4] ПокупкаТочно
  virtual HRESULT STDMETHODCALLTYPE BuyEx(long* Res/*[out,retval]*/) = 0;
  // [5] АннулироватьЧек
  virtual HRESULT STDMETHODCALLTYPE CancelCheck(long* Res/*[out,retval]*/) = 0;
  // [6] Внесение
  virtual HRESULT STDMETHODCALLTYPE CashIncome(long* Res/*[out,retval]*/) = 0;
  // [7] Выплата
  virtual HRESULT STDMETHODCALLTYPE CashOutcome(long* Res/*[out,retval]*/) = 0;
  // [8] Надбавка
  virtual HRESULT STDMETHODCALLTYPE Charge(long* Res/*[out,retval]*/) = 0;
  // [9] ПодытогЧека
  virtual HRESULT STDMETHODCALLTYPE CheckSubTotal(long* Res/*[out,retval]*/) = 0;
  // [10] ЗакрытьЧек
  virtual HRESULT STDMETHODCALLTYPE CloseCheck(long* Res/*[out,retval]*/) = 0;
  // [12] ПодтвердитьДату
  virtual HRESULT STDMETHODCALLTYPE ConfirmDate(long* Res/*[out,retval]*/) = 0;
  // [13] УстановитьСвязь
  virtual HRESULT STDMETHODCALLTYPE Connect(long* Res/*[out,retval]*/) = 0;
  // [14] ПродолжитьПечать
  virtual HRESULT STDMETHODCALLTYPE ContinuePrint(long* Res/*[out,retval]*/) = 0;
  // [15] НефтянойЧекКоррекции
  virtual HRESULT STDMETHODCALLTYPE Correction(long* Res/*[out,retval]*/) = 0;
  // [16] ОтрезатьЧек
  virtual HRESULT STDMETHODCALLTYPE CutCheck(long* Res/*[out,retval]*/) = 0;
  // [17] ЗапросДампа
  virtual HRESULT STDMETHODCALLTYPE DampRequest(long* Res/*[out,retval]*/) = 0;
  // [18] УдалитьЛУ
  virtual HRESULT STDMETHODCALLTYPE DeleteLD(long* Res/*[out,retval]*/) = 0;
  // [19] РазорватьСвязь
  virtual HRESULT STDMETHODCALLTYPE Disconnect(long* Res/*[out,retval]*/) = 0;
  // [20] Скидка
  virtual HRESULT STDMETHODCALLTYPE Discount(long* Res/*[out,retval]*/) = 0;
  // [21] НефтянойЧекНаДозу
  virtual HRESULT STDMETHODCALLTYPE DozeOilCheck(long* Res/*[out,retval]*/) = 0;
  // [22] ПечатьКартинки
  virtual HRESULT STDMETHODCALLTYPE Draw(long* Res/*[out,retval]*/) = 0;
  // [23] ОтчетЭКЛЗПоОтделамВДиапазонеДат
  virtual HRESULT STDMETHODCALLTYPE EKLZDepartmentReportInDatesRange(long* Res/*[out,retval]*/) = 0;
  // [24] ОтчетЭКЛЗПоОтделамВДиапазонеСмен
  virtual HRESULT STDMETHODCALLTYPE EKLZDepartmentReportInSessionsRange(long* Res/*[out,retval]*/) = 0;
  // [25] КонтрольнаяЛентаЭКЛЗПоСмене
  virtual HRESULT STDMETHODCALLTYPE EKLZJournalOnSessionNumber(long* Res/*[out,retval]*/) = 0;
  // [26] ОтчетЭКЛЗПоСменамВДиапазонеДат
  virtual HRESULT STDMETHODCALLTYPE EKLZSessionReportInDatesRange(long* Res/*[out,retval]*/) = 0;
  // [27] ОтчетЭКЛЗПоСменамВДиапазонеСмен
  virtual HRESULT STDMETHODCALLTYPE EKLZSessionReportInSessionsRange(long* Res/*[out,retval]*/) = 0;
  // [28] ПослатьБайты
  virtual HRESULT STDMETHODCALLTYPE ExchangeBytes(long* Res/*[out,retval]*/) = 0;
  // [29] ПродвинутьДокумент
  virtual HRESULT STDMETHODCALLTYPE FeedDocument(long* Res/*[out,retval]*/) = 0;
  // [30] Фискализация
  virtual HRESULT STDMETHODCALLTYPE Fiscalization(long* Res/*[out,retval]*/) = 0;
  // [31] ФискальныйОтчётПоДиапазонуДат
  virtual HRESULT STDMETHODCALLTYPE FiscalReportForDatesRange(long* Res/*[out,retval]*/) = 0;
  // [32] ФискальныйОтчётПоДиапазонуСмен
  virtual HRESULT STDMETHODCALLTYPE FiscalReportForSessionRange(long* Res/*[out,retval]*/) = 0;
  // [33] ПолучитьАктивноеЛУ
  virtual HRESULT STDMETHODCALLTYPE GetActiveLD(long* Res/*[out,retval]*/) = 0;
  // [34] ПеречислитьЛУ
  virtual HRESULT STDMETHODCALLTYPE EnumLD(long* Res/*[out,retval]*/) = 0;
  // [35] ПолучитьДенежныйРегистр
  virtual HRESULT STDMETHODCALLTYPE GetCashReg(long* Res/*[out,retval]*/) = 0;
  // [36] ПолучитьКоличествоЛУ
  virtual HRESULT STDMETHODCALLTYPE GetCountLD(long* Res/*[out,retval]*/) = 0;
  // [37] ПолучитьДанные
  virtual HRESULT STDMETHODCALLTYPE GetData(long* Res/*[out,retval]*/) = 0;
  // [38] ПолучитьПараметрыУстройства
  virtual HRESULT STDMETHODCALLTYPE GetDeviceMetrics(long* Res/*[out,retval]*/) = 0;
  // [39] ПолучитьСостояниеККМ
  virtual HRESULT STDMETHODCALLTYPE GetECRStatus(long* Res/*[out,retval]*/) = 0;
  // [40] ПолучитьКороткийЗапросСостоянияККМ
  virtual HRESULT STDMETHODCALLTYPE GetShortECRStatus(long* Res/*[out,retval]*/) = 0;
  // [41] ПолучитьПараметрыОбмена
  virtual HRESULT STDMETHODCALLTYPE GetExchangeParam(long* Res/*[out,retval]*/) = 0;
  // [42] ПолучитьСтруктуруПоля
  virtual HRESULT STDMETHODCALLTYPE GetFieldStruct(long* Res/*[out,retval]*/) = 0;
  // [43] ПолучитьПараметрыФискализации
  virtual HRESULT STDMETHODCALLTYPE GetFiscalizationParameters(long* Res/*[out,retval]*/) = 0;
  // [44] ПолучитьСуммуЗаписейФП
  virtual HRESULT STDMETHODCALLTYPE GetFMRecordsSum(long* Res/*[out,retval]*/) = 0;
  // [45] ПолучитьДатуПоследнейЗаписиВФП
  virtual HRESULT STDMETHODCALLTYPE GetLastFMRecordDate(long* Res/*[out,retval]*/) = 0;
  // [46] ПолучитьЛитровыйСуммарныйСчётчик
  virtual HRESULT STDMETHODCALLTYPE GetLiterSumCounter(long* Res/*[out,retval]*/) = 0;
  // [47] ПолучитьОперационныйРегистр
  virtual HRESULT STDMETHODCALLTYPE GetOperationReg(long* Res/*[out,retval]*/) = 0;
  // [48] ПолучитьПараметрыЛУ
  virtual HRESULT STDMETHODCALLTYPE GetParamLD(long* Res/*[out,retval]*/) = 0;
  // [49] ПолучитьДиапазонДатИСмен
  virtual HRESULT STDMETHODCALLTYPE GetRangeDatesAndSessions(long* Res/*[out,retval]*/) = 0;
  // [50] ПолучитьСотояниеРК
  virtual HRESULT STDMETHODCALLTYPE GetRKStatus(long* Res/*[out,retval]*/) = 0;
  // [51] ПолучитьСтруктуруТаблицы
  virtual HRESULT STDMETHODCALLTYPE GetTableStruct(long* Res/*[out,retval]*/) = 0;
  // [52] ИнициализироватьФП
  virtual HRESULT STDMETHODCALLTYPE InitFM(long* Res/*[out,retval]*/) = 0;
  // [53] ИнициализироватьТаблицы
  virtual HRESULT STDMETHODCALLTYPE InitTable(long* Res/*[out,retval]*/) = 0;
  // [54] ПрерватьВыдачуДанных
  virtual HRESULT STDMETHODCALLTYPE InterruptDataStream(long* Res/*[out,retval]*/) = 0;
  // [55] ПрерватьПолныйОтчёт
  virtual HRESULT STDMETHODCALLTYPE InterruptFullReport(long* Res/*[out,retval]*/) = 0;
  // [56] ПрерватьТестовыйПрогон
  virtual HRESULT STDMETHODCALLTYPE InterruptTest(long* Res/*[out,retval]*/) = 0;
  // [57] ЗапуститьРК
  virtual HRESULT STDMETHODCALLTYPE LaunchRK(long* Res/*[out,retval]*/) = 0;
  // [58] ЗагрузкаГрафики
  virtual HRESULT STDMETHODCALLTYPE LoadLineData(long* Res/*[out,retval]*/) = 0;
  // [59] ПродажаНефтепродуктов
  virtual HRESULT STDMETHODCALLTYPE OilSale(long* Res/*[out,retval]*/) = 0;
  // [60] ОткрытьЧек
  virtual HRESULT STDMETHODCALLTYPE OpenCheck(long* Res/*[out,retval]*/) = 0;
  // [61] ОткрытьДенежныйЯщик
  virtual HRESULT STDMETHODCALLTYPE OpenDrawer(long* Res/*[out,retval]*/) = 0;
  // [62] ПечатьШтрихКода
  virtual HRESULT STDMETHODCALLTYPE PrintBarCode(long* Res/*[out,retval]*/) = 0;
  // [63] СнятьОтчётПоОтделам
  virtual HRESULT STDMETHODCALLTYPE PrintDepartmentReport(long* Res/*[out,retval]*/) = 0;
  // [64] ПечатьЗаголовкаДокумента
  virtual HRESULT STDMETHODCALLTYPE PrintDocumentTitle(long* Res/*[out,retval]*/) = 0;
  // [65] ПечатьОперационныхРегистров
  virtual HRESULT STDMETHODCALLTYPE PrintOperationReg(long* Res/*[out,retval]*/) = 0;
  // [66] СнятьОтчётСГашением
  virtual HRESULT STDMETHODCALLTYPE PrintReportWithCleaning(long* Res/*[out,retval]*/) = 0;
  // [67] СнятьОтчётБезГашения
  virtual HRESULT STDMETHODCALLTYPE PrintReportWithoutCleaning(long* Res/*[out,retval]*/) = 0;
  // [68] ПечатьСтроки
  virtual HRESULT STDMETHODCALLTYPE PrintString(long* Res/*[out,retval]*/) = 0;
  // [69] ПечатьЖирнойСтроки
  virtual HRESULT STDMETHODCALLTYPE PrintWideString(long* Res/*[out,retval]*/) = 0;
  // [70] ПрочитатьДокументЭКЛЗПоКПК
  virtual HRESULT STDMETHODCALLTYPE ReadEKLZDocumentOnKPK(long* Res/*[out,retval]*/) = 0;
  // [71] ПрочитатьИтогСменыЭКЛЗПоСмене
  virtual HRESULT STDMETHODCALLTYPE ReadEKLZSessionTotal(long* Res/*[out,retval]*/) = 0;
  // [72] ПрочитатьЛицензию
  virtual HRESULT STDMETHODCALLTYPE ReadLicense(long* Res/*[out,retval]*/) = 0;
  // [73] ПрочитатьТаблицу
  virtual HRESULT STDMETHODCALLTYPE ReadTable(long* Res/*[out,retval]*/) = 0;
  // [74] ПовторДокумента
  virtual HRESULT STDMETHODCALLTYPE RepeatDocument(long* Res/*[out,retval]*/) = 0;
  // [75] СброситьВсеТРК
  virtual HRESULT STDMETHODCALLTYPE ResetAllTRK(long* Res/*[out,retval]*/) = 0;
  // [76] СброситьРК
  virtual HRESULT STDMETHODCALLTYPE ResetRK(long* Res/*[out,retval]*/) = 0;
  // [77] ТехнологическоеОбнуление
  virtual HRESULT STDMETHODCALLTYPE ResetSettings(long* Res/*[out,retval]*/) = 0;
  // [78] ОбщееГашение
  virtual HRESULT STDMETHODCALLTYPE ResetSummary(long* Res/*[out,retval]*/) = 0;
  // [79] ВозвратПокупки
  virtual HRESULT STDMETHODCALLTYPE ReturnBuy(long* Res/*[out,retval]*/) = 0;
  // [80] ВозвратПокупкиТочно
  virtual HRESULT STDMETHODCALLTYPE ReturnBuyEx(long* Res/*[out,retval]*/) = 0;
  // [81] ВозвратПродажи
  virtual HRESULT STDMETHODCALLTYPE ReturnSale(long* Res/*[out,retval]*/) = 0;
  // [82] ВозвратПродажиТочно
  virtual HRESULT STDMETHODCALLTYPE ReturnSaleEx(long* Res/*[out,retval]*/) = 0;
  // [83] Продажа
  virtual HRESULT STDMETHODCALLTYPE Sale(long* Res/*[out,retval]*/) = 0;
  // [84] ПродажаТочно
  virtual HRESULT STDMETHODCALLTYPE SaleEx(long* Res/*[out,retval]*/) = 0;
  // [85] УстановитьАктивноеЛУ
  virtual HRESULT STDMETHODCALLTYPE SetActiveLD(long* Res/*[out,retval]*/) = 0;
  // [86] УстановитьДату
  virtual HRESULT STDMETHODCALLTYPE SetDate(long* Res/*[out,retval]*/) = 0;
  // [87] УстановитьДозуВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE SetDozeInMilliliters(long* Res/*[out,retval]*/) = 0;
  // [88] УстановитьДозуВДенежныхЕдиницах
  virtual HRESULT STDMETHODCALLTYPE SetDozeInMoney(long* Res/*[out,retval]*/) = 0;
  // [89] УстановитьПараметрыОбмена
  virtual HRESULT STDMETHODCALLTYPE SetExchangeParam(long* Res/*[out,retval]*/) = 0;
  // [90] УстановитьПараметрыЛУ
  virtual HRESULT STDMETHODCALLTYPE SetParamLD(long* Res/*[out,retval]*/) = 0;
  // [91] УстановитьПоложенияТочки
  virtual HRESULT STDMETHODCALLTYPE SetPointPosition(long* Res/*[out,retval]*/) = 0;
  // [92] УстановитьПараметрыРК
  virtual HRESULT STDMETHODCALLTYPE SetRKParameters(long* Res/*[out,retval]*/) = 0;
  // [93] УстановитьЗаводскойНомер
  virtual HRESULT STDMETHODCALLTYPE SetSerialNumber(long* Res/*[out,retval]*/) = 0;
  // [94] УстановитьВремя
  virtual HRESULT STDMETHODCALLTYPE SetTime(long* Res/*[out,retval]*/) = 0;
  // [95] НастройкаСвойств
  virtual HRESULT STDMETHODCALLTYPE ShowProperties(long* Res/*[out,retval]*/) = 0;
  // [96] ПрерыватьПечатьДокументаЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE StopEKLZDocumentPrinting(long* Res/*[out,retval]*/) = 0;
  // [97] ОстановитьРК
  virtual HRESULT STDMETHODCALLTYPE StopRK(long* Res/*[out,retval]*/) = 0;
  // [98] Сторно
  virtual HRESULT STDMETHODCALLTYPE Storno(long* Res/*[out,retval]*/) = 0;
  // [99] СторноТочно
  virtual HRESULT STDMETHODCALLTYPE StornoEx(long* Res/*[out,retval]*/) = 0;
  // [100] СторноНадбавки
  virtual HRESULT STDMETHODCALLTYPE StornoCharge(long* Res/*[out,retval]*/) = 0;
  // [101] СторноСкидки
  virtual HRESULT STDMETHODCALLTYPE StornoDiscount(long* Res/*[out,retval]*/) = 0;
  // [102] НефтянойЧекНаСумму
  virtual HRESULT STDMETHODCALLTYPE SummOilCheck(long* Res/*[out,retval]*/) = 0;
  // [104] ОтменаЧекаСистАдминистратором
  virtual HRESULT STDMETHODCALLTYPE SysAdminCancelCheck(long* Res/*[out,retval]*/) = 0;
  // [105] ТестовыйПрогон
  virtual HRESULT STDMETHODCALLTYPE Test(long* Res/*[out,retval]*/) = 0;
  // [106] ЗаписатьЛицензию
  virtual HRESULT STDMETHODCALLTYPE WriteLicense(long* Res/*[out,retval]*/) = 0;
  // [107] ЗаписатьТаблицу
  virtual HRESULT STDMETHODCALLTYPE WriteTable(long* Res/*[out,retval]*/) = 0;
  // [103] ШтрихКод
  virtual HRESULT STDMETHODCALLTYPE get_BarCode(BSTR* Value/*[out,retval]*/) = 0;
  // [103] ШтрихКод
  virtual HRESULT STDMETHODCALLTYPE set_BarCode(BSTR Value/*[in]*/) = 0;
  // [108] СостояниеБатареи
  virtual HRESULT STDMETHODCALLTYPE get_BatteryCondition(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [109] НапряжениеНаБатарейке
  virtual HRESULT STDMETHODCALLTYPE get_BatteryVoltage(double* Value/*[out,retval]*/) = 0;
  // [110] СкоростьОбмена
  virtual HRESULT STDMETHODCALLTYPE get_BaudRate(long* Value/*[out,retval]*/) = 0;
  // [110] СкоростьОбмена
  virtual HRESULT STDMETHODCALLTYPE set_BaudRate(long Value/*[in]*/) = 0;
  // [111] Сдача
  virtual HRESULT STDMETHODCALLTYPE get_Change(CURRENCY* Value/*[out,retval]*/) = 0;
  // [112] ИтогЧека
  virtual HRESULT STDMETHODCALLTYPE get_CheckResult(CURRENCY* Value/*[out,retval]*/) = 0;
  // [112] ИтогЧека
  virtual HRESULT STDMETHODCALLTYPE set_CheckResult(CURRENCY Value/*[in]*/) = 0;
  // [113] ТипЧека
  virtual HRESULT STDMETHODCALLTYPE get_CheckType(long* Value/*[out,retval]*/) = 0;
  // [113] ТипЧека
  virtual HRESULT STDMETHODCALLTYPE set_CheckType(long Value/*[in]*/) = 0;
  // [114] НомерCOMпорта
  virtual HRESULT STDMETHODCALLTYPE get_ComNumber(long* Value/*[out,retval]*/) = 0;
  // [114] НомерCOMпорта
  virtual HRESULT STDMETHODCALLTYPE set_ComNumber(long Value/*[in]*/) = 0;
  // [115] СодержимоеДенежногоРегистра
  virtual HRESULT STDMETHODCALLTYPE get_ContentsOfCashRegister(CURRENCY* Value/*[out,retval]*/) = 0;
  // [116] СодержимоеОперационногоРегистра
  virtual HRESULT STDMETHODCALLTYPE get_ContentsOfOperationRegister(long* Value/*[out,retval]*/) = 0;
  // [117] ТекущаяДозаВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE get_CurrentDozeInMilliliters(long* Value/*[out,retval]*/) = 0;
  // [117] ТекущаяДозаВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE set_CurrentDozeInMilliliters(long Value/*[in]*/) = 0;
  // [118] ТекущаяДозаВДенежныхЕдиницах
  virtual HRESULT STDMETHODCALLTYPE get_CurrentDozeInMoney(CURRENCY* Value/*[out,retval]*/) = 0;
  // [118] ТекущаяДозаВДенежныхЕдиницах
  virtual HRESULT STDMETHODCALLTYPE set_CurrentDozeInMoney(CURRENCY Value/*[in]*/) = 0;
  // [119] ТипОтрезки
  virtual HRESULT STDMETHODCALLTYPE get_CutType(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [119] ТипОтрезки
  virtual HRESULT STDMETHODCALLTYPE set_CutType(VARIANT_BOOL Value/*[in]*/) = 0;
  // [120] БлокДанных
  virtual HRESULT STDMETHODCALLTYPE get_DataBlock(BSTR* Value/*[out,retval]*/) = 0;
  // [121] НомерБлокаДанных
  virtual HRESULT STDMETHODCALLTYPE get_DataBlockNumber(long* Value/*[out,retval]*/) = 0;
  // [122] Дата
  virtual HRESULT STDMETHODCALLTYPE get_Date(DATE* Value/*[out,retval]*/) = 0;
  // [122] Дата
  virtual HRESULT STDMETHODCALLTYPE set_Date(DATE Value/*[in]*/) = 0;
  //virtual HRESULT STDMETHODCALLTYPE set_Date(char* Value/*[in]*/) = 0;
  // [123] Отдел
  virtual HRESULT STDMETHODCALLTYPE get_Department(long* Value/*[out,retval]*/) = 0;
  // [123] Отдел
  virtual HRESULT STDMETHODCALLTYPE set_Department(long Value/*[in]*/) = 0;
  // [124] КодУстройства
  virtual HRESULT STDMETHODCALLTYPE get_DeviceCode(long* Value/*[out,retval]*/) = 0;
  // [124] КодУстройства
  virtual HRESULT STDMETHODCALLTYPE set_DeviceCode(long Value/*[in]*/) = 0;
  // [125] ОписаниеУстройства
  virtual HRESULT STDMETHODCALLTYPE get_DeviceCodeDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [126] СкидкаНаЧек
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheck(double* Value/*[out,retval]*/) = 0;
  // [126] СкидкаНаЧек
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheck(double Value/*[in]*/) = 0;
  // [127] НаименованиеДокумента
  virtual HRESULT STDMETHODCALLTYPE get_DocumentName(BSTR* Value/*[out,retval]*/) = 0;
  // [127] НаименованиеДокумента
  virtual HRESULT STDMETHODCALLTYPE set_DocumentName(BSTR Value/*[in]*/) = 0;
  // [128] НомерДокумента
  virtual HRESULT STDMETHODCALLTYPE get_DocumentNumber(long* Value/*[out,retval]*/) = 0;
  // [128] НомерДокумента
  virtual HRESULT STDMETHODCALLTYPE set_DocumentNumber(long Value/*[in]*/) = 0;
  // [129] ДозаВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE get_DozeInMilliliters(long* Value/*[out,retval]*/) = 0;
  // [129] ДозаВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE set_DozeInMilliliters(long Value/*[in]*/) = 0;
  // [130] ДозаВДенежныхЕдиницах
  virtual HRESULT STDMETHODCALLTYPE get_DozeInMoney(CURRENCY* Value/*[out,retval]*/) = 0;
  // [130] ДозаВДенежныхЕдиницах
  virtual HRESULT STDMETHODCALLTYPE set_DozeInMoney(CURRENCY Value/*[in]*/) = 0;
  // [131] НомерДенежногоЯщика
  virtual HRESULT STDMETHODCALLTYPE get_DrawerNumber(long* Value/*[out,retval]*/) = 0;
  // [131] НомерДенежногоЯщика
  virtual HRESULT STDMETHODCALLTYPE set_DrawerNumber(long Value/*[in]*/) = 0;
  // [132] ПодрежимККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRAdvancedMode(long* Value/*[out,retval]*/) = 0;
  // [133] ОписаниеПодрежимаККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRAdvancedModeDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [134] СборкаККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRBuild(long* Value/*[out,retval]*/) = 0;
  // [135] ФлагиККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRFlags(long* Value/*[out,retval]*/) = 0;
  // [136] ВводВККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRInput(BSTR* Value/*[out,retval]*/) = 0;
  // [137] РежимККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRMode(long* Value/*[out,retval]*/) = 0;
  // [138] Статус8Режима
  virtual HRESULT STDMETHODCALLTYPE get_ECRMode8Status(long* Value/*[out,retval]*/) = 0;
  // [139] ОписаниеРежимаККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRModeDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [140] ВыводИзККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECROutput(BSTR* Value/*[out,retval]*/) = 0;
  // [141] ДатаПОККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRSoftDate(DATE* Value/*[out,retval]*/) = 0;
  // [142] ВерсияПОККМ
  virtual HRESULT STDMETHODCALLTYPE get_ECRSoftVersion(BSTR* Value/*[out,retval]*/) = 0;
  // [143] ЭКЛЗЕсть
  virtual HRESULT STDMETHODCALLTYPE get_EKLZIsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [144] КодАврийнойОстановки
  virtual HRESULT STDMETHODCALLTYPE get_EmergencyStopCode(long* Value/*[out,retval]*/) = 0;
  // [145] ОписаниеКодаАварийонйОстановки
  virtual HRESULT STDMETHODCALLTYPE get_EmergencyStopCodeDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [146] НазваниеПоля
  virtual HRESULT STDMETHODCALLTYPE get_FieldName(BSTR* Value/*[out,retval]*/) = 0;
  // [147] НомерПоля
  virtual HRESULT STDMETHODCALLTYPE get_FieldNumber(long* Value/*[out,retval]*/) = 0;
  // [147] НомерПоля
  virtual HRESULT STDMETHODCALLTYPE set_FieldNumber(long Value/*[in]*/) = 0;
  // [148] РазмерПоля
  virtual HRESULT STDMETHODCALLTYPE get_FieldSize(long* Value/*[out,retval]*/) = 0;
  // [149] ТипПоля
  virtual HRESULT STDMETHODCALLTYPE get_FieldType(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [150] НомерПервойЛинии
  virtual HRESULT STDMETHODCALLTYPE get_FirstLineNumber(long* Value/*[out,retval]*/) = 0;
  // [150] НомерПервойЛинии
  virtual HRESULT STDMETHODCALLTYPE set_FirstLineNumber(long Value/*[in]*/) = 0;
  // [151] ДатаПервойСмены
  virtual HRESULT STDMETHODCALLTYPE get_FirstSessionDate(DATE* Value/*[out,retval]*/) = 0;
  // [151] ДатаПервойСмены
  virtual HRESULT STDMETHODCALLTYPE set_FirstSessionDate(DATE Value/*[in]*/) = 0;
  // [152] НомерПервойСмены
  virtual HRESULT STDMETHODCALLTYPE get_FirstSessionNumber(long* Value/*[out,retval]*/) = 0;
  // [152] НомерПервойСмены
  virtual HRESULT STDMETHODCALLTYPE set_FirstSessionNumber(long Value/*[in]*/) = 0;
  // [153] ФП1Есть
  virtual HRESULT STDMETHODCALLTYPE get_FM1IsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [154] ФП2Есть
  virtual HRESULT STDMETHODCALLTYPE get_FM2IsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [155] СборкаФП
  virtual HRESULT STDMETHODCALLTYPE get_FMBuild(long* Value/*[out,retval]*/) = 0;
  // [156] ФлагиФП
  virtual HRESULT STDMETHODCALLTYPE get_FMFlags(long* Value/*[out,retval]*/) = 0;
  // [157] ПереполнениеФП
  virtual HRESULT STDMETHODCALLTYPE get_FMOverflow(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [158] ДатаПОФП
  virtual HRESULT STDMETHODCALLTYPE get_FMSoftDate(DATE* Value/*[out,retval]*/) = 0;
  // [159] ВерсияПОФП
  virtual HRESULT STDMETHODCALLTYPE get_FMSoftVersion(BSTR* Value/*[out,retval]*/) = 0;
  // [160] СвободныхЗаписейВФП
  virtual HRESULT STDMETHODCALLTYPE get_FreeRecordInFM(long* Value/*[out,retval]*/) = 0;
  // [161] ОсталосьПеререгистраций
  virtual HRESULT STDMETHODCALLTYPE get_FreeRegistration(long* Value/*[out,retval]*/) = 0;
  // [162] ИНН
  virtual HRESULT STDMETHODCALLTYPE get_INN(BSTR* Value/*[out,retval]*/) = 0;
  // [162] ИНН
  virtual HRESULT STDMETHODCALLTYPE set_INN(BSTR Value/*[in]*/) = 0;
  // [163] ЧекЗакрыт
  virtual HRESULT STDMETHODCALLTYPE get_IsCheckClosed(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [164] ЧекОформлен
  virtual HRESULT STDMETHODCALLTYPE get_IsCheckMadeOut(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [165] ДенежныйЯщикОткрыт
  virtual HRESULT STDMETHODCALLTYPE get_IsDrawerOpen(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [166] РулонОперационногоЖурналаЕсть
  virtual HRESULT STDMETHODCALLTYPE get_JournalRibbonIsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [167] РычагТермоголовкиОперационногоЖурнала
  virtual HRESULT STDMETHODCALLTYPE get_JournalRibbonLever(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [168] ОптичДатчикОперационогоЖурнала
  virtual HRESULT STDMETHODCALLTYPE get_JournalRibbonOpticalSensor(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [170] НомерКПК
  virtual HRESULT STDMETHODCALLTYPE get_KPKNumber(long* Value/*[out,retval]*/) = 0;
  // [170] НомерКПК
  virtual HRESULT STDMETHODCALLTYPE set_KPKNumber(long Value/*[in]*/) = 0;
  // [171] НомерПоследнейЛинии
  virtual HRESULT STDMETHODCALLTYPE get_LastLineNumber(long* Value/*[out,retval]*/) = 0;
  // [171] НомерПоследнейЛинии
  virtual HRESULT STDMETHODCALLTYPE set_LastLineNumber(long Value/*[in]*/) = 0;
  // [172] ДатаПоследнейСмены
  virtual HRESULT STDMETHODCALLTYPE get_LastSessionDate(DATE* Value/*[out,retval]*/) = 0;
  // [172] ДатаПоследнейСмены
  virtual HRESULT STDMETHODCALLTYPE set_LastSessionDate(DATE Value/*[in]*/) = 0;
  // [173] НомерПоследнейСмены
  virtual HRESULT STDMETHODCALLTYPE get_LastSessionNumber(long* Value/*[out,retval]*/) = 0;
  // [173] НомерПоследнейСмены
  virtual HRESULT STDMETHODCALLTYPE set_LastSessionNumber(long Value/*[in]*/) = 0;
  // [174] Лицензия
  virtual HRESULT STDMETHODCALLTYPE get_License(BSTR* Value/*[out,retval]*/) = 0;
  // [174] Лицензия
  virtual HRESULT STDMETHODCALLTYPE set_License(BSTR Value/*[in]*/) = 0;
  // [175] ЛицензияЕсть
  virtual HRESULT STDMETHODCALLTYPE get_LicenseIsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [176] ДатчикКрышкиКорпуса
  virtual HRESULT STDMETHODCALLTYPE get_LidPositionSensor(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [177] ГрафическаяИнформация
  virtual HRESULT STDMETHODCALLTYPE get_LineData(BSTR* Value/*[out,retval]*/) = 0;
  // [177] ГрафическаяИнформация
  virtual HRESULT STDMETHODCALLTYPE set_LineData(BSTR Value/*[in]*/) = 0;
  // [178] НомерЛинии
  virtual HRESULT STDMETHODCALLTYPE get_LineNumber(long* Value/*[out,retval]*/) = 0;
  // [178] НомерЛинии
  virtual HRESULT STDMETHODCALLTYPE set_LineNumber(long Value/*[in]*/) = 0;
  // [179] НомерВЗале
  virtual HRESULT STDMETHODCALLTYPE get_LogicalNumber(long* Value/*[out,retval]*/) = 0;
  // [180] МаксимальноеЗначениеПоля
  virtual HRESULT STDMETHODCALLTYPE get_MAXValueOfField(long* Value/*[out,retval]*/) = 0;
  // [181] МинимальноеЗначениеПоля
  virtual HRESULT STDMETHODCALLTYPE get_MINValueOfField(long* Value/*[out,retval]*/) = 0;
  // [182] Мотор
  virtual HRESULT STDMETHODCALLTYPE get_Motor(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [183] НазваниеДенежногоРегистра
  virtual HRESULT STDMETHODCALLTYPE get_NameCashReg(BSTR* Value/*[out,retval]*/) = 0;
  // [184] НазваниеОперационногоРегистра
  virtual HRESULT STDMETHODCALLTYPE get_NameOperationReg(BSTR* Value/*[out,retval]*/) = 0;
  // [185] НовыйПарольНИ
  virtual HRESULT STDMETHODCALLTYPE get_NewPasswordTI(long* Value/*[out,retval]*/) = 0;
  // [185] НовыйПарольНИ
  virtual HRESULT STDMETHODCALLTYPE set_NewPasswordTI(long Value/*[in]*/) = 0;
  // [186] СквознойНомерДокумента
  virtual HRESULT STDMETHODCALLTYPE get_OpenDocumentNumber(long* Value/*[out,retval]*/) = 0;
  // [187] НомерОператора
  virtual HRESULT STDMETHODCALLTYPE get_OperatorNumber(long* Value/*[out,retval]*/) = 0;
  // [188] Пароль
  virtual HRESULT STDMETHODCALLTYPE get_Password(long* Value/*[out,retval]*/) = 0;
  // [188] Пароль
  virtual HRESULT STDMETHODCALLTYPE set_Password(long Value/*[in]*/) = 0;
  // [189] Пистолет
  virtual HRESULT STDMETHODCALLTYPE get_Pistol(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [190] ПоложениеТочки
  virtual HRESULT STDMETHODCALLTYPE get_PointPosition(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [190] ПоложениеТочки
  virtual HRESULT STDMETHODCALLTYPE set_PointPosition(VARIANT_BOOL Value/*[in]*/) = 0;
  // [191] НомерПорта
  virtual HRESULT STDMETHODCALLTYPE get_PortNumber(long* Value/*[out,retval]*/) = 0;
  // [191] НомерПорта
  virtual HRESULT STDMETHODCALLTYPE set_PortNumber(long Value/*[in]*/) = 0;
  // [192] Цена
  virtual HRESULT STDMETHODCALLTYPE get_Price(CURRENCY* Value/*[out,retval]*/) = 0;
  // [192] Цена
  virtual HRESULT STDMETHODCALLTYPE set_Price(CURRENCY Value/*[in]*/) = 0;
  // [193] Количество
  virtual HRESULT STDMETHODCALLTYPE get_Quantity(double* Value/*[out,retval]*/) = 0;
  // [193] Количество
  virtual HRESULT STDMETHODCALLTYPE set_Quantity(double Value/*[in]*/) = 0;
  // [194] КоличествоОпераций
  virtual HRESULT STDMETHODCALLTYPE get_QuantityOfOperations(long* Value/*[out,retval]*/) = 0;
  // [195] РулонЧековойЛентыЕсть
  virtual HRESULT STDMETHODCALLTYPE get_ReceiptRibbonIsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [196] РычагТермоголовкиЧекЛенты
  virtual HRESULT STDMETHODCALLTYPE get_ReceiptRibbonLever(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [197] ОптичДатчикЧековойЛенты
  virtual HRESULT STDMETHODCALLTYPE get_ReceiptRibbonOpticalSensor(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [198] НомерРегистра
  virtual HRESULT STDMETHODCALLTYPE get_RegisterNumber(long* Value/*[out,retval]*/) = 0;
  // [198] НомерРегистра
  virtual HRESULT STDMETHODCALLTYPE set_RegisterNumber(long Value/*[in]*/) = 0;
  // [199] КоличествоПеререгистраций
  virtual HRESULT STDMETHODCALLTYPE get_RegistrationNumber(long* Value/*[out,retval]*/) = 0;
  // [199] КоличествоПеререгистраций
  virtual HRESULT STDMETHODCALLTYPE set_RegistrationNumber(long Value/*[in]*/) = 0;
  // [200] ТипОчета
  virtual HRESULT STDMETHODCALLTYPE get_ReportType(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [200] ТипОчета
  virtual HRESULT STDMETHODCALLTYPE set_ReportType(VARIANT_BOOL Value/*[in]*/) = 0;
  // [201] Результат
  virtual HRESULT STDMETHODCALLTYPE get_ResultCode(long* Value/*[out,retval]*/) = 0;
  // [202] ОписаниеРезультата
  virtual HRESULT STDMETHODCALLTYPE get_ResultCodeDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [203] НомерРК
  virtual HRESULT STDMETHODCALLTYPE get_RKNumber(long* Value/*[out,retval]*/) = 0;
  // [203] НомерРК
  virtual HRESULT STDMETHODCALLTYPE set_RKNumber(long Value/*[in]*/) = 0;
  // [204] РНМ
  virtual HRESULT STDMETHODCALLTYPE get_RNM(BSTR* Value/*[out,retval]*/) = 0;
  // [204] РНМ
  virtual HRESULT STDMETHODCALLTYPE set_RNM(BSTR Value/*[in]*/) = 0;
  // [205] ГрубыйКлапан
  virtual HRESULT STDMETHODCALLTYPE get_RoughValve(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [206] НомерРяда
  virtual HRESULT STDMETHODCALLTYPE get_RowNumber(long* Value/*[out,retval]*/) = 0;
  // [206] НомерРяда
  virtual HRESULT STDMETHODCALLTYPE set_RowNumber(long Value/*[in]*/) = 0;
  // [207] ПериодПрогона
  virtual HRESULT STDMETHODCALLTYPE get_RunningPeriod(long* Value/*[out,retval]*/) = 0;
  // [207] ПериодПрогона
  virtual HRESULT STDMETHODCALLTYPE set_RunningPeriod(long Value/*[in]*/) = 0;
  // [208] ЗаводскойНомер
  virtual HRESULT STDMETHODCALLTYPE get_SerialNumber(BSTR* Value/*[out,retval]*/) = 0;
  // [208] ЗаводскойНомер
  virtual HRESULT STDMETHODCALLTYPE set_SerialNumber(BSTR Value/*[in]*/) = 0;
  // [209] НомерСмены
  virtual HRESULT STDMETHODCALLTYPE get_SessionNumber(long* Value/*[out,retval]*/) = 0;
  // [209] НомерСмены
  virtual HRESULT STDMETHODCALLTYPE set_SessionNumber(long Value/*[in]*/) = 0;
  // [210] ПодкладнойДокументПроходит
  virtual HRESULT STDMETHODCALLTYPE get_SlipDocumentIsMoving(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [211] ПодкладнойДокументЕсть
  virtual HRESULT STDMETHODCALLTYPE get_SlipDocumentIsPresent(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [212] ЗамедлениеВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE get_SlowingInMilliliters(long* Value/*[out,retval]*/) = 0;
  // [212] ЗамедлениеВМиллилитрах
  virtual HRESULT STDMETHODCALLTYPE set_SlowingInMilliliters(long Value/*[in]*/) = 0;
  // [213] ЗамедляющийКлапан
  virtual HRESULT STDMETHODCALLTYPE get_SlowingValve(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [214] СтатусРК
  virtual HRESULT STDMETHODCALLTYPE get_StatusRK(long* Value/*[out,retval]*/) = 0;
  // [215] ОписаниеСтатусаРК
  virtual HRESULT STDMETHODCALLTYPE get_StatusRKDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [216] СтрокаДляПечати
  virtual HRESULT STDMETHODCALLTYPE get_StringForPrinting(BSTR* Value/*[out,retval]*/) = 0;
  // [216] СтрокаДляПечати
  virtual HRESULT STDMETHODCALLTYPE set_StringForPrinting(BSTR Value/*[in]*/) = 0;
  // [217] КоличествоСтрок
  virtual HRESULT STDMETHODCALLTYPE get_StringQuantity(long* Value/*[out,retval]*/) = 0;
  // [217] КоличествоСтрок
  virtual HRESULT STDMETHODCALLTYPE set_StringQuantity(long Value/*[in]*/) = 0;
  // [218] Сумма1
  virtual HRESULT STDMETHODCALLTYPE get_Summ1(CURRENCY* Value/*[out,retval]*/) = 0;
  // [218] Сумма1
  virtual HRESULT STDMETHODCALLTYPE set_Summ1(CURRENCY Value/*[in]*/) = 0;
  // [219] Сумма2
  virtual HRESULT STDMETHODCALLTYPE get_Summ2(CURRENCY* Value/*[out,retval]*/) = 0;
  // [219] Сумма2
  virtual HRESULT STDMETHODCALLTYPE set_Summ2(CURRENCY Value/*[in]*/) = 0;
  // [220] Сумма3
  virtual HRESULT STDMETHODCALLTYPE get_Summ3(CURRENCY* Value/*[out,retval]*/) = 0;
  // [220] Сумма3
  virtual HRESULT STDMETHODCALLTYPE set_Summ3(CURRENCY Value/*[in]*/) = 0;
  // [221] Сумма4
  virtual HRESULT STDMETHODCALLTYPE get_Summ4(CURRENCY* Value/*[out,retval]*/) = 0;
  // [221] Сумма4
  virtual HRESULT STDMETHODCALLTYPE set_Summ4(CURRENCY Value/*[in]*/) = 0;
  // [222] НазваниеТаблицы
  virtual HRESULT STDMETHODCALLTYPE get_TableName(BSTR* Value/*[out,retval]*/) = 0;
  // [223] НомерТаблицы
  virtual HRESULT STDMETHODCALLTYPE get_TableNumber(long* Value/*[out,retval]*/) = 0;
  // [223] НомерТаблицы
  virtual HRESULT STDMETHODCALLTYPE set_TableNumber(long Value/*[in]*/) = 0;
  // [224] Налог1
  virtual HRESULT STDMETHODCALLTYPE get_Tax1(long* Value/*[out,retval]*/) = 0;
  // [224] Налог1
  virtual HRESULT STDMETHODCALLTYPE set_Tax1(long Value/*[in]*/) = 0;
  // [225] Налог2
  virtual HRESULT STDMETHODCALLTYPE get_Tax2(long* Value/*[out,retval]*/) = 0;
  // [225] Налог2
  virtual HRESULT STDMETHODCALLTYPE set_Tax2(long Value/*[in]*/) = 0;
  // [226] Налог3
  virtual HRESULT STDMETHODCALLTYPE get_Tax3(long* Value/*[out,retval]*/) = 0;
  // [226] Налог3
  virtual HRESULT STDMETHODCALLTYPE set_Tax3(long Value/*[in]*/) = 0;
  // [227] Налог4
  virtual HRESULT STDMETHODCALLTYPE get_Tax4(long* Value/*[out,retval]*/) = 0;
  // [227] Налог4
  virtual HRESULT STDMETHODCALLTYPE set_Tax4(long Value/*[in]*/) = 0;
  // [228] Время
  virtual HRESULT STDMETHODCALLTYPE get_Time(DATE* Value/*[out,retval]*/) = 0;
  // [228] Время
  virtual HRESULT STDMETHODCALLTYPE set_Time(DATE Value/*[in]*/) = 0;
  // [229] ТаймаутПриемаБайта
  virtual HRESULT STDMETHODCALLTYPE get_Timeout(long* Value/*[out,retval]*/) = 0;
  // [229] ТаймаутПриемаБайта
  virtual HRESULT STDMETHODCALLTYPE set_Timeout(long Value/*[in]*/) = 0;
  // [230] ВремяСтрока
  virtual HRESULT STDMETHODCALLTYPE get_TimeStr(BSTR* Value/*[out,retval]*/) = 0;
  // [230] ВремяСтрока
  virtual HRESULT STDMETHODCALLTYPE set_TimeStr(BSTR Value/*[in]*/) = 0;
  // [231] ПосылаемыеБайты
  virtual HRESULT STDMETHODCALLTYPE get_TransferBytes(BSTR* Value/*[out,retval]*/) = 0;
  // [231] ПосылаемыеБайты
  virtual HRESULT STDMETHODCALLTYPE set_TransferBytes(BSTR Value/*[in]*/) = 0;
  // [232] НомерТРК
  virtual HRESULT STDMETHODCALLTYPE get_TRKNumber(long* Value/*[out,retval]*/) = 0;
  // [232] НомерТРК
  virtual HRESULT STDMETHODCALLTYPE set_TRKNumber(long Value/*[in]*/) = 0;
  // [233] ТипПоследнейЗаписиФП
  virtual HRESULT STDMETHODCALLTYPE get_TypeOfLastEntryFM(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [234] ТипСуммыЗаписейФП
  virtual HRESULT STDMETHODCALLTYPE get_TypeOfSumOfEntriesFM(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [234] ТипСуммыЗаписейФП
  virtual HRESULT STDMETHODCALLTYPE set_TypeOfSumOfEntriesFM(VARIANT_BOOL Value/*[in]*/) = 0;
  // [235] УКодоваяСтраница
  virtual HRESULT STDMETHODCALLTYPE get_UCodePage(long* Value/*[out,retval]*/) = 0;
  // [236] УОписаниеУстройства
  virtual HRESULT STDMETHODCALLTYPE get_UDescription(BSTR* Value/*[out,retval]*/) = 0;
  // [237] УВерсияПротокола
  virtual HRESULT STDMETHODCALLTYPE get_UMajorProtocolVersion(long* Value/*[out,retval]*/) = 0;
  // [238] УТипУстройства
  virtual HRESULT STDMETHODCALLTYPE get_UMajorType(long* Value/*[out,retval]*/) = 0;
  // [239] УПодверсияПротокола
  virtual HRESULT STDMETHODCALLTYPE get_UMinorProtocolVersion(long* Value/*[out,retval]*/) = 0;
  // [240] УПодтипУстройства
  virtual HRESULT STDMETHODCALLTYPE get_UMinorType(long* Value/*[out,retval]*/) = 0;
  // [241] УМодельУстройства
  virtual HRESULT STDMETHODCALLTYPE get_UModel(long* Value/*[out,retval]*/) = 0;
  // [242] ИспользоватьОперационныйЖурнал
  virtual HRESULT STDMETHODCALLTYPE get_UseJournalRibbon(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [242] ИспользоватьОперационныйЖурнал
  virtual HRESULT STDMETHODCALLTYPE set_UseJournalRibbon(VARIANT_BOOL Value/*[in]*/) = 0;
  // [243] ИспользоватьЧековуюЛенту
  virtual HRESULT STDMETHODCALLTYPE get_UseReceiptRibbon(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [243] ИспользоватьЧековуюЛенту
  virtual HRESULT STDMETHODCALLTYPE set_UseReceiptRibbon(VARIANT_BOOL Value/*[in]*/) = 0;
  // [244] ИспользоватьПодкладнойДокумент
  virtual HRESULT STDMETHODCALLTYPE get_UseSlipDocument(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [244] ИспользоватьПодкладнойДокумент
  virtual HRESULT STDMETHODCALLTYPE set_UseSlipDocument(VARIANT_BOOL Value/*[in]*/) = 0;
  // [245] ЗначениеПоляЦелое
  virtual HRESULT STDMETHODCALLTYPE get_ValueOfFieldInteger(long* Value/*[out,retval]*/) = 0;
  // [245] ЗначениеПоляЦелое
  virtual HRESULT STDMETHODCALLTYPE set_ValueOfFieldInteger(long Value/*[in]*/) = 0;
  // [246] ЗначениеПоляСтрока
  virtual HRESULT STDMETHODCALLTYPE get_ValueOfFieldString(BSTR* Value/*[out,retval]*/) = 0;
  // [246] ЗначениеПоляСтрока
  virtual HRESULT STDMETHODCALLTYPE set_ValueOfFieldString(BSTR Value/*[in]*/) = 0;
  // [169] ПечатьСтрокиДаннымШрифтом
  virtual HRESULT STDMETHODCALLTYPE PrintStringWithFont(long* Res/*[out,retval]*/) = 0;
  // [247] ТипШрифта
  virtual HRESULT STDMETHODCALLTYPE get_FontType(long* Value/*[out,retval]*/) = 0;
  // [247] ТипШрифта
  virtual HRESULT STDMETHODCALLTYPE set_FontType(long Value/*[in]*/) = 0;
  // [248] СкоростьОбменаЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDBaudrate(long* Value/*[out,retval]*/) = 0;
  // [248] СкоростьОбменаЛУ
  virtual HRESULT STDMETHODCALLTYPE set_LDBaudrate(long Value/*[in]*/) = 0;
  // [249] COMпортЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDComNumber(long* Value/*[out,retval]*/) = 0;
  // [249] COMпортЛУ
  virtual HRESULT STDMETHODCALLTYPE set_LDComNumber(long Value/*[in]*/) = 0;
  // [250] КоличествоЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDCount(long* Value/*[out,retval]*/) = 0;
  // [251] ИндексЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDIndex(long* Value/*[out,retval]*/) = 0;
  // [251] ИндексЛУ
  virtual HRESULT STDMETHODCALLTYPE set_LDIndex(long Value/*[in]*/) = 0;
  // [252] ИмяЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDName(BSTR* Value/*[out,retval]*/) = 0;
  // [252] ИмяЛУ
  virtual HRESULT STDMETHODCALLTYPE set_LDName(BSTR Value/*[in]*/) = 0;
  // [253] НомерЛУ
  virtual HRESULT STDMETHODCALLTYPE get_LDNumber(long* Value/*[out,retval]*/) = 0;
  // [253] НомерЛУ
  virtual HRESULT STDMETHODCALLTYPE set_LDNumber(long Value/*[in]*/) = 0;
  // [254] WaitPrintingTime
  virtual HRESULT STDMETHODCALLTYPE get_WaitPrintingTime(long* Value/*[out,retval]*/) = 0;
  // [255] ОтказЛевогоДатчикаПечМех
  virtual HRESULT STDMETHODCALLTYPE get_IsPrinterLeftSensorFailure(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [256] ОтказПравогоДатчикаПечМех
  virtual HRESULT STDMETHODCALLTYPE get_IsPrinterRightSensorFailure(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [257] ИтогАктивизацииЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE EKLZActivizationResult(long* Res/*[out,retval]*/) = 0;
  // [258] АктивизацияЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE EKLZActivization(long* Res/*[out,retval]*/) = 0;
  // [259] ЗакрытьАрхивЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE CloseEKLZArchive(long* Res/*[out,retval]*/) = 0;
  // [260] ПолучитьРегНомерЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZSerialNumber(long* Res/*[out,retval]*/) = 0;
  // [261] НомерЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_EKLZNumber(BSTR* Value/*[out,retval]*/) = 0;
  // [262] ПрекращениеЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE EKLZInterrupt(long* Res/*[out,retval]*/) = 0;
  // [263] ПолучитьСостояниеЭКЛЗКод1
  virtual HRESULT STDMETHODCALLTYPE GetEKLZCode1Report(long* Res/*[out,retval]*/) = 0;
  // [264] ИтогДокументаПоследнегоКПК
  virtual HRESULT STDMETHODCALLTYPE get_LastKPKDocumentResult(CURRENCY* Value/*[out,retval]*/) = 0;
  // [265] ДатаПоследнегоКПК
  virtual HRESULT STDMETHODCALLTYPE get_LastKPKDate(DATE* Value/*[out,retval]*/) = 0;
  // [266] ВремяПоследнегоКПК
  virtual HRESULT STDMETHODCALLTYPE get_LastKPKTime(DATE* Value/*[out,retval]*/) = 0;
  // [267] НомерПоследнегоКПК
  virtual HRESULT STDMETHODCALLTYPE get_LastKPKNumber(long* Value/*[out,retval]*/) = 0;
  // [268] ФлагиЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_EKLZFlags(long* Value/*[out,retval]*/) = 0;
  // [269] ПолучитьСостояниеЭКЛЗКод2
  virtual HRESULT STDMETHODCALLTYPE GetEKLZCode2Report(long* Res/*[out,retval]*/) = 0;
  // [270] ТестЦелостностиАрхиваЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE TestEKLZArchiveIntegrity(long* Res/*[out,retval]*/) = 0;
  // [271] НомерТеста
  virtual HRESULT STDMETHODCALLTYPE get_TestNumber(long* Value/*[out,retval]*/) = 0;
  // [271] НомерТеста
  virtual HRESULT STDMETHODCALLTYPE set_TestNumber(long Value/*[in]*/) = 0;
  // [272] ВерсияЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_EKLZVersion(BSTR* Value/*[out,retval]*/) = 0;
  // [273] ДанныеОтчётаЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_EKLZData(BSTR* Value/*[out,retval]*/) = 0;
  // [274] ПолучитьВерсиюЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZVersion(long* Res/*[out,retval]*/) = 0;
  // [275] ИнициализироватьАрхивЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE InitEKLZArchive(long* Res/*[out,retval]*/) = 0;
  // [276] ПолучитьДанныеОтчётаЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZData(long* Res/*[out,retval]*/) = 0;
  // [277] ПолучитьКонтрольнуюЛентуЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZJournal(long* Res/*[out,retval]*/) = 0;
  // [278] ПолучитьДокументЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZDocument(long* Res/*[out,retval]*/) = 0;
  // [279] ПолучитьОтчетЭКЛЗПоОтделамВДиапазонеДат
  virtual HRESULT STDMETHODCALLTYPE GetEKLZDepartmentReportInDatesRange(long* Res/*[out,retval]*/) = 0;
  // [280] ПолучитьОтчетЭКЛЗПоОтделамВДиапазонеСмен
  virtual HRESULT STDMETHODCALLTYPE GetEKLZDepartmentReportInSessionsRange(long* Res/*[out,retval]*/) = 0;
  // [281] ПолучитьОтчетЭКЛЗПоСменамВДиапазонеДат
  virtual HRESULT STDMETHODCALLTYPE GetEKLZSessionReportInDatesRange(long* Res/*[out,retval]*/) = 0;
  // [282] ПолучитьОтчетЭКЛЗПоСменамВДиапазонеСмен
  virtual HRESULT STDMETHODCALLTYPE GetEKLZSessionReportInSessionsRange(long* Res/*[out,retval]*/) = 0;
  // [283] ПолучитьИтогиСменыПоНомеру
  virtual HRESULT STDMETHODCALLTYPE GetEKLZSessionTotal(long* Res/*[out,retval]*/) = 0;
  // [284] ПолучитьИтогАктивизацииЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE GetEKLZActivizationResult(long* Res/*[out,retval]*/) = 0;
  // [285] УстановитьОшибкуЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE SetEKLZResultCode(long* Res/*[out,retval]*/) = 0;
  // [286] КодОшибкиЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_EKLZResultCode(long* Value/*[out,retval]*/) = 0;
  // [286] КодОшибкиЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE set_EKLZResultCode(long Value/*[in]*/) = 0;
  // [287] КодОшибкиФП
  virtual HRESULT STDMETHODCALLTYPE get_FMResultCode(long* Value/*[out,retval]*/) = 0;
  // [288] НапряжениеИсточникаПитания
  virtual HRESULT STDMETHODCALLTYPE get_PowerSourceVoltage(double* Value/*[out,retval]*/) = 0;
  // [289] ПереполнениеЭКЛЗ
  virtual HRESULT STDMETHODCALLTYPE get_IsEKLZOverflow(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [291] ОткрытьФискПД
  virtual HRESULT STDMETHODCALLTYPE OpenFiscalSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [292] ОткрытьСтандартныйФискПД
  virtual HRESULT STDMETHODCALLTYPE OpenStandardFiscalSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [293] ФормированиеОперацииНаПД
  virtual HRESULT STDMETHODCALLTYPE RegistrationOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [294] ФормированиеСтандартнойОперацииНаПД
  virtual HRESULT STDMETHODCALLTYPE StandardRegistrationOnSlipDocument(long* Reg/*[out,retval]*/) = 0;
  // [295] ФормированиеНадбавкиНаПД
  virtual HRESULT STDMETHODCALLTYPE ChargeOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [296] ФормированиеСтандартнойНадбавкиНаПД
  virtual HRESULT STDMETHODCALLTYPE StandardChargeOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [297] ФормированиеЗакрытияЧекаНаПД
  virtual HRESULT STDMETHODCALLTYPE CloseCheckOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [298] ФормированиеСтандартногоЗакрытияЧекаНаПД
  virtual HRESULT STDMETHODCALLTYPE StandardCloseCheckOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [299] КонфигурироватьПД
  virtual HRESULT STDMETHODCALLTYPE ConfigureSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [300] СтандартнаяКонфигурацияПД
  virtual HRESULT STDMETHODCALLTYPE ConfigureStandardSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [301] ЗаполнитьБуферПДФиксИнформацией
  virtual HRESULT STDMETHODCALLTYPE FillSlipDocumentWithUnfiscalInfo(long* Res/*[out,retval]*/) = 0;
  // [302] ОчиститьСтрокуБуфераПД
  virtual HRESULT STDMETHODCALLTYPE ClearSlipDocumentBufferString(long* Res/*[out,retval]*/) = 0;
  // [303] ОчиститьБуферПД
  virtual HRESULT STDMETHODCALLTYPE ClearSlipDocumentBuffer(long* Res/*[out,retval]*/) = 0;
  // [304] ПечатьПД
  virtual HRESULT STDMETHODCALLTYPE PrintSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [305] ТипДубляПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyType(long* Value/*[out,retval]*/) = 0;
  // [305] ТипДубляПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyType(long Value/*[in]*/) = 0;
  // [307] КоличествоДублей
  virtual HRESULT STDMETHODCALLTYPE get_NumberOfCopies(long* Value/*[out,retval]*/) = 0;
  // [307] КоличествоДублей
  virtual HRESULT STDMETHODCALLTYPE set_NumberOfCopies(long Value/*[in]*/) = 0;
  // [308] СмещениеДубля1ПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyOffset1(long* Value/*[out,retval]*/) = 0;
  // [308] СмещениеДубля1ПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyOffset1(long Value/*[in]*/) = 0;
  // [309] СмещениеДубля2ПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyOffset2(long* Value/*[out,retval]*/) = 0;
  // [309] СмещениеДубля2ПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyOffset2(long Value/*[in]*/) = 0;
  // [310] СмещениеДубля3ПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyOffset3(long* Value/*[out,retval]*/) = 0;
  // [310] СмещениеДубля3ПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyOffset3(long Value/*[in]*/) = 0;
  // [311] СмещениеДубля4ПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyOffset4(long* Value/*[out,retval]*/) = 0;
  // [311] СмещениеДубля4ПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyOffset4(long Value/*[in]*/) = 0;
  // [312] СмещениеДубля5ПД
  virtual HRESULT STDMETHODCALLTYPE get_CopyOffset5(long* Value/*[out,retval]*/) = 0;
  // [312] СмещениеДубля5ПД
  virtual HRESULT STDMETHODCALLTYPE set_CopyOffset5(long Value/*[in]*/) = 0;
  // [313] ШрифтКлишеПД
  virtual HRESULT STDMETHODCALLTYPE get_ClicheFont(long* Value/*[out,retval]*/) = 0;
  // [313] ШрифтКлишеПД
  virtual HRESULT STDMETHODCALLTYPE set_ClicheFont(long Value/*[in]*/) = 0;
  // [314] ШрифтЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE get_HeaderFont(long* Value/*[out,retval]*/) = 0;
  // [314] ШрифтЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE set_HeaderFont(long Value/*[in]*/) = 0;
  // [315] ШрифтЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE get_EKLZFont(long* Value/*[out,retval]*/) = 0;
  // [315] ШрифтЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE set_EKLZFont(long Value/*[in]*/) = 0;
  // [316] НомерСтрокиКлишеПД
  virtual HRESULT STDMETHODCALLTYPE get_ClicheStringNumber(long* Value/*[out,retval]*/) = 0;
  // [316] НомерСтрокиКлишеПД
  virtual HRESULT STDMETHODCALLTYPE set_ClicheStringNumber(long Value/*[in]*/) = 0;
  // [317] НомерСтрокиЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE get_HeaderStringNumber(long* Value/*[out,retval]*/) = 0;
  // [317] НомерСтрокиЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE set_HeaderStringNumber(long Value/*[in]*/) = 0;
  // [318] НомерСтрокиЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE get_EKLZStringNumber(long* Value/*[out,retval]*/) = 0;
  // [318] НомерСтрокиЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE set_EKLZStringNumber(long Value/*[in]*/) = 0;
  // [319] НомерСтрокиФискЛоготипаПД
  virtual HRESULT STDMETHODCALLTYPE get_FMStringNumber(long* Value/*[out,retval]*/) = 0;
  // [319] НомерСтрокиФискЛоготипаПД
  virtual HRESULT STDMETHODCALLTYPE set_FMStringNumber(long Value/*[in]*/) = 0;
  // [320] СмещениеКлишеПД
  virtual HRESULT STDMETHODCALLTYPE get_ClicheOffset(long* Value/*[out,retval]*/) = 0;
  // [320] СмещениеКлишеПД
  virtual HRESULT STDMETHODCALLTYPE set_ClicheOffset(long Value/*[in]*/) = 0;
  // [321] СмещениеЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE get_HeaderOffset(long* Value/*[out,retval]*/) = 0;
  // [321] СмещениеЗаголовкаПД
  virtual HRESULT STDMETHODCALLTYPE set_HeaderOffset(long Value/*[in]*/) = 0;
  // [322] СмещениеЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE get_EKLZOffset(long* Value/*[out,retval]*/) = 0;
  // [322] СмещениеЭКЛЗПД
  virtual HRESULT STDMETHODCALLTYPE set_EKLZOffset(long Value/*[in]*/) = 0;
  // [323] СмещениеКПКПД
  virtual HRESULT STDMETHODCALLTYPE get_KPKOffset(long* Value/*[out,retval]*/) = 0;
  // [323] СмещениеКПКПД
  virtual HRESULT STDMETHODCALLTYPE set_KPKOffset(long Value/*[in]*/) = 0;
  // [324] СмещениеФискЛоготипаПД
  virtual HRESULT STDMETHODCALLTYPE get_FMOffset(long* Value/*[out,retval]*/) = 0;
  // [324] СмещениеФискЛоготипаПД
  virtual HRESULT STDMETHODCALLTYPE set_FMOffset(long Value/*[in]*/) = 0;
  // [325] ПерваяСтрокаБлокаОперацииПД
  virtual HRESULT STDMETHODCALLTYPE get_OperationBlockFirstString(long* Value/*[out,retval]*/) = 0;
  // [325] ПерваяСтрокаБлокаОперацииПД
  virtual HRESULT STDMETHODCALLTYPE set_OperationBlockFirstString(long Value/*[in]*/) = 0;
  // [326] ФорматЦелогоКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE get_QuantityFormat(long* Value/*[out,retval]*/) = 0;
  // [326] ФорматЦелогоКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE set_QuantityFormat(long Value/*[in]*/) = 0;
  // [327] КоличествоСтрокВОперацииПД
  virtual HRESULT STDMETHODCALLTYPE get_StringQuantityInOperation(long* Value/*[out,retval]*/) = 0;
  // [327] КоличествоСтрокВОперацииПД
  virtual HRESULT STDMETHODCALLTYPE set_StringQuantityInOperation(long Value/*[in]*/) = 0;
  // [328] НомерТекстовойСтрокиПД
  virtual HRESULT STDMETHODCALLTYPE get_TextStringNumber(long* Value/*[out,retval]*/) = 0;
  // [328] НомерТекстовойСтрокиПД
  virtual HRESULT STDMETHODCALLTYPE set_TextStringNumber(long Value/*[in]*/) = 0;
  // [329] НомерСтрокиКоличестваНаЦенуПД
  virtual HRESULT STDMETHODCALLTYPE get_QuantityStringNumber(long* Value/*[out,retval]*/) = 0;
  // [329] НомерСтрокиКоличестваНаЦенуПД
  virtual HRESULT STDMETHODCALLTYPE set_QuantityStringNumber(long Value/*[in]*/) = 0;
  // [330] НомерСтрокиСуммыПД
  virtual HRESULT STDMETHODCALLTYPE get_SummStringNumber(long* Value/*[out,retval]*/) = 0;
  // [330] НомерСтрокиСуммыПД
  virtual HRESULT STDMETHODCALLTYPE set_SummStringNumber(long Value/*[in]*/) = 0;
  // [331] НомерСтрокиОтделаПД
  virtual HRESULT STDMETHODCALLTYPE get_DepartmentStringNumber(long* Value/*[out,retval]*/) = 0;
  // [331] НомерСтрокиОтделаПД
  virtual HRESULT STDMETHODCALLTYPE set_DepartmentStringNumber(long Value/*[in]*/) = 0;
  // [332] ШрифтТекстаПД
  virtual HRESULT STDMETHODCALLTYPE get_TextFont(long* Value/*[out,retval]*/) = 0;
  // [332] ШрифтТекстаПД
  virtual HRESULT STDMETHODCALLTYPE set_TextFont(long Value/*[in]*/) = 0;
  // [333] ШрифтКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE get_QuantityFont(long* Value/*[out,retval]*/) = 0;
  // [333] ШрифтКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE set_QuantityFont(long Value/*[in]*/) = 0;
  // [334] ШрифтЗнакаУмноженияПД
  virtual HRESULT STDMETHODCALLTYPE get_MultiplicationFont(long* Value/*[out,retval]*/) = 0;
  // [334] ШрифтЗнакаУмноженияПД
  virtual HRESULT STDMETHODCALLTYPE set_MultiplicationFont(long Value/*[in]*/) = 0;
  // [335] ШрифтЦеныПД
  virtual HRESULT STDMETHODCALLTYPE get_PriceFont(long* Value/*[out,retval]*/) = 0;
  // [335] ШрифтЦеныПД
  virtual HRESULT STDMETHODCALLTYPE set_PriceFont(long Value/*[in]*/) = 0;
  // [336] ШрифтСуммыПД
  virtual HRESULT STDMETHODCALLTYPE get_SummFont(long* Value/*[out,retval]*/) = 0;
  // [336] ШрифтСуммыПД
  virtual HRESULT STDMETHODCALLTYPE set_SummFont(long Value/*[in]*/) = 0;
  // [337] ШрифтОтделаПД
  virtual HRESULT STDMETHODCALLTYPE get_DepartmentFont(long* Value/*[out,retval]*/) = 0;
  // [337] ШрифтОтделаПД
  virtual HRESULT STDMETHODCALLTYPE set_DepartmentFont(long Value/*[in]*/) = 0;
  // [338] КоличествоСимволовВТекстСтрокеПД
  virtual HRESULT STDMETHODCALLTYPE get_TextSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [338] КоличествоСимволовВТекстСтрокеПД
  virtual HRESULT STDMETHODCALLTYPE set_TextSymbolNumber(long Value/*[in]*/) = 0;
  // [339] ЧислоСимволовВПолеКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE get_QuantitySymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [339] ЧислоСимволовВПолеКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE set_QuantitySymbolNumber(long Value/*[in]*/) = 0;
  // [340] ЧислоСимволовВПолеЦеныПД
  virtual HRESULT STDMETHODCALLTYPE get_PriceSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [340] ЧислоСимволовВПолеЦеныПД
  virtual HRESULT STDMETHODCALLTYPE set_PriceSymbolNumber(long Value/*[in]*/) = 0;
  // [341] ЧислоСимволовВПолеСуммыПД
  virtual HRESULT STDMETHODCALLTYPE get_SummSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [341] ЧислоСимволовВПолеСуммыПД
  virtual HRESULT STDMETHODCALLTYPE set_SummSymbolNumber(long Value/*[in]*/) = 0;
  // [342] ЧислоСимволовВПолеОтделаПД
  virtual HRESULT STDMETHODCALLTYPE get_DepartmentSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [342] ЧислоСимволовВПолеОтделаПД
  virtual HRESULT STDMETHODCALLTYPE set_DepartmentSymbolNumber(long Value/*[in]*/) = 0;
  // [343] СмещениеТекстПоляПД
  virtual HRESULT STDMETHODCALLTYPE get_TextOffset(long* Value/*[out,retval]*/) = 0;
  // [343] СмещениеТекстПоляПД
  virtual HRESULT STDMETHODCALLTYPE set_TextOffset(long Value/*[in]*/) = 0;
  // [344] СмещениеПоляКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE get_QuantityOffset(long* Value/*[out,retval]*/) = 0;
  // [344] СмещениеПоляКоличестваПД
  virtual HRESULT STDMETHODCALLTYPE set_QuantityOffset(long Value/*[in]*/) = 0;
  // [345] СмещениеПоляСуммыПД
  virtual HRESULT STDMETHODCALLTYPE get_SummOffset(long* Value/*[out,retval]*/) = 0;
  // [345] СмещениеПоляСуммыПД
  virtual HRESULT STDMETHODCALLTYPE set_SummOffset(long Value/*[in]*/) = 0;
  // [346] СмещениеПоляОтделаПД
  virtual HRESULT STDMETHODCALLTYPE get_DepartmentOffset(long* Value/*[out,retval]*/) = 0;
  // [346] СмещениеПоляОтделаПД
  virtual HRESULT STDMETHODCALLTYPE set_DepartmentOffset(long Value/*[in]*/) = 0;
  // [347] ФормированиеСкидкиНаПД
  virtual HRESULT STDMETHODCALLTYPE DiscountOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [348] ФормированиеСтандартнойСкидкиНаПД
  virtual HRESULT STDMETHODCALLTYPE StandardDiscountOnSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [349] УдалитьНефискИнфоПД
  virtual HRESULT STDMETHODCALLTYPE get_IsClearUnfiscalInfo(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [349] УдалитьНефискИнфоПД
  virtual HRESULT STDMETHODCALLTYPE set_IsClearUnfiscalInfo(VARIANT_BOOL Value/*[in]*/) = 0;
  // [350] ТипИнфоПД
  virtual HRESULT STDMETHODCALLTYPE get_InfoType(long* Value/*[out,retval]*/) = 0;
  // [350] ТипИнфоПД
  virtual HRESULT STDMETHODCALLTYPE set_InfoType(long Value/*[in]*/) = 0;
  // [11] НомерСтрокиБуфераПД
  virtual HRESULT STDMETHODCALLTYPE get_StringNumber(long* Value/*[out,retval]*/) = 0;
  // [11] НомерСтрокиБуфераПД
  virtual HRESULT STDMETHODCALLTYPE set_StringNumber(long Value/*[in]*/) = 0;
  // [290] ВыброситьПД
  virtual HRESULT STDMETHODCALLTYPE EjectSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [306] НаправлениеВыбросаПД
  virtual HRESULT STDMETHODCALLTYPE get_EjectDirection(long* Value/*[out,retval]*/) = 0;
  // [306] НаправлениеВыбросаПД
  virtual HRESULT STDMETHODCALLTYPE set_EjectDirection(long Value/*[in]*/) = 0;
  // [351] РасширеннаяЗагрузкаГрафики
  virtual HRESULT STDMETHODCALLTYPE LoadLineDataEx(long* Res/*[out,retval]*/) = 0;
  // [352] РасширеннаяПечатьКартинки
  virtual HRESULT STDMETHODCALLTYPE DrawEx(long* Res/*[out,retval]*/) = 0;
  // [353] ОбщаяКонфигурацияПД
  virtual HRESULT STDMETHODCALLTYPE ConfigureGeneralSlipDocument(long* Res/*[out,retval]*/) = 0;
  // [355] НомерСтрокиНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE get_OperationNameStringNumber(long* Value/*[out,retval]*/) = 0;
  // [355] НомерСтрокиНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE set_OperationNameStringNumber(long Value/*[in]*/) = 0;
  // [356] ШрифтНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE get_OperationNameFont(long* Value/*[out,retval]*/) = 0;
  // [356] ШрифтНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE set_OperationNameFont(long Value/*[in]*/) = 0;
  // [357] СмещениеНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE get_OperationNameOffset(long* Value/*[out,retval]*/) = 0;
  // [357] СмещениеНазванияОперацииПД
  virtual HRESULT STDMETHODCALLTYPE set_OperationNameOffset(long Value/*[in]*/) = 0;
  // [358] НомерСтрокиИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalStringNumber(long* Value/*[out,retval]*/) = 0;
  // [358] НомерСтрокиИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalStringNumber(long Value/*[in]*/) = 0;
  // [359] НомерСтрокиНаличныеПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1StringNumber(long* Value/*[out,retval]*/) = 0;
  // [359] НомерСтрокиНаличныеПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1StringNumber(long Value/*[in]*/) = 0;
  // [360] НомерСтрокиТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2StringNumber(long* Value/*[out,retval]*/) = 0;
  // [360] НомерСтрокиТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2StringNumber(long Value/*[in]*/) = 0;
  // [361] НомерСтрокиТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3StringNumber(long* Value/*[out,retval]*/) = 0;
  // [361] НомерСтрокиТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3StringNumber(long Value/*[in]*/) = 0;
  // [362] НомерСтрокиТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4StringNumber(long* Value/*[out,retval]*/) = 0;
  // [362] НомерСтрокиТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4StringNumber(long Value/*[in]*/) = 0;
  // [363] НомерСтрокиСдачиПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeStringNumber(long* Value/*[out,retval]*/) = 0;
  // [363] НомерСтрокиСдачиПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeStringNumber(long Value/*[in]*/) = 0;
  // [364] НомерСтрокиОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1TurnOverStringNumber(long* Value/*[out,retval]*/) = 0;
  // [364] НомерСтрокиОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1TurnOverStringNumber(long Value/*[in]*/) = 0;
  // [365] НомерСтрокиОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2TurnOverStringNumber(long* Value/*[out,retval]*/) = 0;
  // [365] НомерСтрокиОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2TurnOverStringNumber(long Value/*[in]*/) = 0;
  // [366] НомерСтрокиОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3TurnOverStringNumber(long* Value/*[out,retval]*/) = 0;
  // [366] НомерСтрокиОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3TurnOverStringNumber(long Value/*[in]*/) = 0;
  // [367] НомерСтрокиОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4TurnOverStringNumber(long* Value/*[out,retval]*/) = 0;
  // [367] НомерСтрокиОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4TurnOverStringNumber(long Value/*[in]*/) = 0;
  // [368] НомерСтрокиСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1SumStringNumber(long* Value/*[out,retval]*/) = 0;
  // [368] НомерСтрокиСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1SumStringNumber(long Value/*[in]*/) = 0;
  // [369] НомерСтрокиСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2SumStringNumber(long* Value/*[out,retval]*/) = 0;
  // [369] НомерСтрокиСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2SumStringNumber(long Value/*[in]*/) = 0;
  // [370] НомерСтрокиСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3SumStringNumber(long* Value/*[out,retval]*/) = 0;
  // [370] НомерСтрокиСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3SumStringNumber(long Value/*[in]*/) = 0;
  // [371] НомерСтрокиСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4SumStringNumber(long* Value/*[out,retval]*/) = 0;
  // [371] НомерСтрокиСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4SumStringNumber(long Value/*[in]*/) = 0;
  // [372] НомерСтрокиВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalStringNumber(long* Value/*[out,retval]*/) = 0;
  // [372] НомерСтрокиВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalStringNumber(long Value/*[in]*/) = 0;
  // [373] НомерСтрокиСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckStringNumber(long* Value/*[out,retval]*/) = 0;
  // [373] НомерСтрокиСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckStringNumber(long Value/*[in]*/) = 0;
  // [374] ШрифтИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalFont(long* Value/*[out,retval]*/) = 0;
  // [374] ШрифтИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalFont(long Value/*[in]*/) = 0;
  // [375] ШрифтСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalSumFont(long* Value/*[out,retval]*/) = 0;
  // [375] ШрифтСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalSumFont(long Value/*[in]*/) = 0;
  // [376] ШрифтСуммыНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1Font(long* Value/*[out,retval]*/) = 0;
  // [376] ШрифтСуммыНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1Font(long Value/*[in]*/) = 0;
  // [377] ШрифтНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1NameFont(long* Value/*[out,retval]*/) = 0;
  // [377] ШрифтНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1NameFont(long Value/*[in]*/) = 0;
  // [378] ШрифтНазванияТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2NameFont(long* Value/*[out,retval]*/) = 0;
  // [378] ШрифтНазванияТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2NameFont(long Value/*[in]*/) = 0;
  // [379] ШрифтНазванияТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3NameFont(long* Value/*[out,retval]*/) = 0;
  // [379] ШрифтНазванияТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3NameFont(long Value/*[in]*/) = 0;
  // [380] ШрифтНазванияТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4NameFont(long* Value/*[out,retval]*/) = 0;
  // [380] ШрифтНазванияТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4NameFont(long Value/*[in]*/) = 0;
  // [381] ШрифтСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2Font(long* Value/*[out,retval]*/) = 0;
  // [381] ШрифтСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2Font(long Value/*[in]*/) = 0;
  // [382] ШрифтСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3Font(long* Value/*[out,retval]*/) = 0;
  // [382] ШрифтСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3Font(long Value/*[in]*/) = 0;
  // [383] ШрифтСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4Font(long* Value/*[out,retval]*/) = 0;
  // [383] ШрифтСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4Font(long Value/*[in]*/) = 0;
  // [384] ШрифтСдачаПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeFont(long* Value/*[out,retval]*/) = 0;
  // [384] ШрифтСдачаПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeFont(long Value/*[in]*/) = 0;
  // [385] ШрифтСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeSumFont(long* Value/*[out,retval]*/) = 0;
  // [385] ШрифтСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeSumFont(long Value/*[in]*/) = 0;
  // [386] ШрифтНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1NameFont(long* Value/*[out,retval]*/) = 0;
  // [386] ШрифтНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1NameFont(long Value/*[in]*/) = 0;
  // [387] ШрифтНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2NameFont(long* Value/*[out,retval]*/) = 0;
  // [387] ШрифтНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2NameFont(long Value/*[in]*/) = 0;
  // [388] ШрифтНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3NameFont(long* Value/*[out,retval]*/) = 0;
  // [388] ШрифтНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3NameFont(long Value/*[in]*/) = 0;
  // [389] ШрифтНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4NameFont(long* Value/*[out,retval]*/) = 0;
  // [389] ШрифтНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4NameFont(long Value/*[in]*/) = 0;
  // [390] ШрифтОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1TurnOverFont(long* Value/*[out,retval]*/) = 0;
  // [390] ШрифтОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1TurnOverFont(long Value/*[in]*/) = 0;
  // [391] ШрифтОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2TurnOverFont(long* Value/*[out,retval]*/) = 0;
  // [391] ШрифтОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2TurnOverFont(long Value/*[in]*/) = 0;
  // [392] ШрифтОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3TurnOverFont(long* Value/*[out,retval]*/) = 0;
  // [392] ШрифтОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3TurnOverFont(long Value/*[in]*/) = 0;
  // [393] ШрифтОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4TurnOverFont(long* Value/*[out,retval]*/) = 0;
  // [393] ШрифтОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4TurnOverFont(long Value/*[in]*/) = 0;
  // [394] ШрифтСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1RateFont(long* Value/*[out,retval]*/) = 0;
  // [394] ШрифтСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1RateFont(long Value/*[in]*/) = 0;
  // [395] ШрифтСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2RateFont(long* Value/*[out,retval]*/) = 0;
  // [395] ШрифтСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2RateFont(long Value/*[in]*/) = 0;
  // [396] ШрифтСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3RateFont(long* Value/*[out,retval]*/) = 0;
  // [396] ШрифтСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3RateFont(long Value/*[in]*/) = 0;
  // [397] ШрифтСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4RateFont(long* Value/*[out,retval]*/) = 0;
  // [397] ШрифтСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4RateFont(long Value/*[in]*/) = 0;
  // [398] ШрифтСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1SumFont(long* Value/*[out,retval]*/) = 0;
  // [398] ШрифтСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1SumFont(long Value/*[in]*/) = 0;
  // [399] ШрифтСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2SumFont(long* Value/*[out,retval]*/) = 0;
  // [399] ШрифтСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2SumFont(long Value/*[in]*/) = 0;
  // [400] ШрифтСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3SumFont(long* Value/*[out,retval]*/) = 0;
  // [400] ШрифтСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3SumFont(long Value/*[in]*/) = 0;
  // [401] ШрифтСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4SumFont(long* Value/*[out,retval]*/) = 0;
  // [401] ШрифтСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4SumFont(long Value/*[in]*/) = 0;
  // [402] ШрифтВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalFont(long* Value/*[out,retval]*/) = 0;
  // [402] ШрифтВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalFont(long Value/*[in]*/) = 0;
  // [403] ШрифтСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalSumFont(long* Value/*[out,retval]*/) = 0;
  // [403] ШрифтСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalSumFont(long Value/*[in]*/) = 0;
  // [404] ШрифтСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckFont(long* Value/*[out,retval]*/) = 0;
  // [404] ШрифтСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckFont(long Value/*[in]*/) = 0;
  // [405] ШрифтСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckSumFont(long* Value/*[out,retval]*/) = 0;
  // [405] ШрифтСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckSumFont(long Value/*[in]*/) = 0;
  // [406] КоличествоСимволовСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [406] КоличествоСимволовСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalSymbolNumber(long Value/*[in]*/) = 0;
  // [407] КоличествоСимволовСуммыНаличныхПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1SymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [407] КоличествоСимволовСуммыНаличныхПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1SymbolNumber(long Value/*[in]*/) = 0;
  // [408] КоличСимвСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2SymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [408] КоличСимвСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2SymbolNumber(long Value/*[in]*/) = 0;
  // [409] КоличСимвСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3SymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [409] КоличСимвСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3SymbolNumber(long Value/*[in]*/) = 0;
  // [410] КоличСимвСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4SymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [410] КоличСимвСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4SymbolNumber(long Value/*[in]*/) = 0;
  // [411] КоличествоСимволовСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [411] КоличествоСимволовСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeSymbolNumber(long Value/*[in]*/) = 0;
  // [412] КоличСимвНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1NameSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [412] КоличСимвНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1NameSymbolNumber(long Value/*[in]*/) = 0;
  // [413] КоличСимвОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1TurnOverSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [413] КоличСимвОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1TurnOverSymbolNumber(long Value/*[in]*/) = 0;
  // [414] КоличСимвСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1RateSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [414] КоличСимвСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1RateSymbolNumber(long Value/*[in]*/) = 0;
  // [415] КоличСимвСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1SumSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [415] КоличСимвСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1SumSymbolNumber(long Value/*[in]*/) = 0;
  // [416] КоличСимвНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2NameSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [416] КоличСимвНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2NameSymbolNumber(long Value/*[in]*/) = 0;
  // [417] КоличСимвОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2TurnOverSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [417] КоличСимвОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2TurnOverSymbolNumber(long Value/*[in]*/) = 0;
  // [418] КоличСимвСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2RateSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [418] КоличСимвСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2RateSymbolNumber(long Value/*[in]*/) = 0;
  // [419] КоличСимвСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2SumSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [419] КоличСимвСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2SumSymbolNumber(long Value/*[in]*/) = 0;
  // [420] КоличСимвНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3NameSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [420] КоличСимвНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3NameSymbolNumber(long Value/*[in]*/) = 0;
  // [421] КоличСимвОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3TurnOverSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [421] КоличСимвОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3TurnOverSymbolNumber(long Value/*[in]*/) = 0;
  // [422] КоличСимвСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3RateSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [422] КоличСимвСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3RateSymbolNumber(long Value/*[in]*/) = 0;
  // [423] КоличСимвСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3SumSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [423] КоличСимвСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3SumSymbolNumber(long Value/*[in]*/) = 0;
  // [424] КоличСимвНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4NameSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [424] КоличСимвНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4NameSymbolNumber(long Value/*[in]*/) = 0;
  // [425] КоличСимвОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4TurnOverSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [425] КоличСимвОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4TurnOverSymbolNumber(long Value/*[in]*/) = 0;
  // [426] КоличСимвСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4RateSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [426] КоличСимвСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4RateSymbolNumber(long Value/*[in]*/) = 0;
  // [427] КоличСимвСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4SumSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [427] КоличСимвСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4SumSymbolNumber(long Value/*[in]*/) = 0;
  // [428] КоличСимвСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [428] КоличСимвСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalSymbolNumber(long Value/*[in]*/) = 0;
  // [429] КоличСимвСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [429] КоличСимвСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckSymbolNumber(long Value/*[in]*/) = 0;
  // [430] КоличСимвСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckSumSymbolNumber(long* Value/*[out,retval]*/) = 0;
  // [430] КоличСимвСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckSumSymbolNumber(long Value/*[in]*/) = 0;
  // [431] СмещениеИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalOffset(long* Value/*[out,retval]*/) = 0;
  // [431] СмещениеИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalOffset(long Value/*[in]*/) = 0;
  // [432] СмещениеСуммыНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1Offset(long* Value/*[out,retval]*/) = 0;
  // [432] СмещениеСуммыНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1Offset(long Value/*[in]*/) = 0;
  // [433] СмещениеСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE get_TotalSumOffset(long* Value/*[out,retval]*/) = 0;
  // [433] СмещениеСуммыИтогаПД
  virtual HRESULT STDMETHODCALLTYPE set_TotalSumOffset(long Value/*[in]*/) = 0;
  // [434] СмещениеНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ1NameOffset(long* Value/*[out,retval]*/) = 0;
  // [434] СмещениеНаличнымиПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ1NameOffset(long Value/*[in]*/) = 0;
  // [435] СмещениеСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2Offset(long* Value/*[out,retval]*/) = 0;
  // [435] СмещениеСуммыТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2Offset(long Value/*[in]*/) = 0;
  // [436] СмещениеНазванияТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ2NameOffset(long* Value/*[out,retval]*/) = 0;
  // [436] СмещениеНазванияТипаОплаты2ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ2NameOffset(long Value/*[in]*/) = 0;
  // [437] СмещениеСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3Offset(long* Value/*[out,retval]*/) = 0;
  // [437] СмещениеСуммыТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3Offset(long Value/*[in]*/) = 0;
  // [438] СмещениеНазванияТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ3NameOffset(long* Value/*[out,retval]*/) = 0;
  // [438] СмещениеНазванияТипаОплаты3ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ3NameOffset(long Value/*[in]*/) = 0;
  // [439] СмещениеСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4Offset(long* Value/*[out,retval]*/) = 0;
  // [439] СмещениеСуммыТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4Offset(long Value/*[in]*/) = 0;
  // [440] СмещениеНазванияТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE get_Summ4NameOffset(long* Value/*[out,retval]*/) = 0;
  // [440] СмещениеНазванияТипаОплаты4ПД
  virtual HRESULT STDMETHODCALLTYPE set_Summ4NameOffset(long Value/*[in]*/) = 0;
  // [441] СмещениеСдачаПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeOffset(long* Value/*[out,retval]*/) = 0;
  // [441] СмещениеСдачаПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeOffset(long Value/*[in]*/) = 0;
  // [442] СмещениеСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE get_ChangeSumOffset(long* Value/*[out,retval]*/) = 0;
  // [442] СмещениеСуммыСдачиПД
  virtual HRESULT STDMETHODCALLTYPE set_ChangeSumOffset(long Value/*[in]*/) = 0;
  // [443] СмещениеНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1NameOffset(long* Value/*[out,retval]*/) = 0;
  // [443] СмещениеНазванияНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1NameOffset(long Value/*[in]*/) = 0;
  // [444] СмещениеОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1TurnOverOffset(long* Value/*[out,retval]*/) = 0;
  // [444] СмещениеОборотаНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1TurnOverOffset(long Value/*[in]*/) = 0;
  // [445] СмещениеСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1RateOffset(long* Value/*[out,retval]*/) = 0;
  // [445] СмещениеСтавкиНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1RateOffset(long Value/*[in]*/) = 0;
  // [446] СмещениеСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax1SumOffset(long* Value/*[out,retval]*/) = 0;
  // [446] СмещениеСуммыНалогаАПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax1SumOffset(long Value/*[in]*/) = 0;
  // [447] СмещениеНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2NameOffset(long* Value/*[out,retval]*/) = 0;
  // [447] СмещениеНазванияНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2NameOffset(long Value/*[in]*/) = 0;
  // [448] СмещениеОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2TurnOverOffset(long* Value/*[out,retval]*/) = 0;
  // [448] СмещениеОборотаНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2TurnOverOffset(long Value/*[in]*/) = 0;
  // [449] СмещениеСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2RateOffset(long* Value/*[out,retval]*/) = 0;
  // [449] СмещениеСтавкиНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2RateOffset(long Value/*[in]*/) = 0;
  // [450] СмещениеСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax2SumOffset(long* Value/*[out,retval]*/) = 0;
  // [450] СмещениеСуммыНалогаБПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax2SumOffset(long Value/*[in]*/) = 0;
  // [451] СмещениеНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3NameOffset(long* Value/*[out,retval]*/) = 0;
  // [451] СмещениеНазванияНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3NameOffset(long Value/*[in]*/) = 0;
  // [452] СмещениеОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3TurnOverOffset(long* Value/*[out,retval]*/) = 0;
  // [452] СмещениеОборотаНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3TurnOverOffset(long Value/*[in]*/) = 0;
  // [453] СмещениеСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3RateOffset(long* Value/*[out,retval]*/) = 0;
  // [453] СмещениеСтавкиНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3RateOffset(long Value/*[in]*/) = 0;
  // [454] СмещениеСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax3SumOffset(long* Value/*[out,retval]*/) = 0;
  // [454] СмещениеСуммыНалогаВПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax3SumOffset(long Value/*[in]*/) = 0;
  // [455] СмещениеНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4NameOffset(long* Value/*[out,retval]*/) = 0;
  // [455] СмещениеНазванияНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4NameOffset(long Value/*[in]*/) = 0;
  // [456] СмещениеОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4TurnOverOffset(long* Value/*[out,retval]*/) = 0;
  // [456] СмещениеОборотаНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4TurnOverOffset(long Value/*[in]*/) = 0;
  // [457] СмещениеСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4RateOffset(long* Value/*[out,retval]*/) = 0;
  // [457] СмещениеСтавкиНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4RateOffset(long Value/*[in]*/) = 0;
  // [458] СмещениеСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE get_Tax4SumOffset(long* Value/*[out,retval]*/) = 0;
  // [458] СмещениеСуммыНалогаГПД
  virtual HRESULT STDMETHODCALLTYPE set_Tax4SumOffset(long Value/*[in]*/) = 0;
  // [459] СмещениеВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalOffset(long* Value/*[out,retval]*/) = 0;
  // [459] СмещениеВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalOffset(long Value/*[in]*/) = 0;
  // [460] СмещениеСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE get_SubTotalSumOffset(long* Value/*[out,retval]*/) = 0;
  // [460] СмещениеСуммыВсегоПД
  virtual HRESULT STDMETHODCALLTYPE set_SubTotalSumOffset(long Value/*[in]*/) = 0;
  // [461] ШиринаПодкладногоДокумента
  virtual HRESULT STDMETHODCALLTYPE get_SlipDocumentWidth(long* Value/*[out,retval]*/) = 0;
  // [461] ШиринаПодкладногоДокумента
  virtual HRESULT STDMETHODCALLTYPE set_SlipDocumentWidth(long Value/*[in]*/) = 0;
  // [462] ДлинаПодкладногоДокумента
  virtual HRESULT STDMETHODCALLTYPE get_SlipDocumentLength(long* Value/*[out,retval]*/) = 0;
  // [462] ДлинаПодкладногоДокумента
  virtual HRESULT STDMETHODCALLTYPE set_SlipDocumentLength(long Value/*[in]*/) = 0;
  // [463] ОриентацияПечати
  virtual HRESULT STDMETHODCALLTYPE get_PrintingAlignment(long* Value/*[out,retval]*/) = 0;
  // [463] ОриентацияПечати
  virtual HRESULT STDMETHODCALLTYPE set_PrintingAlignment(long Value/*[in]*/) = 0;
  // [464] МежстрочныеИнтервалыПД
  virtual HRESULT STDMETHODCALLTYPE get_SlipStringIntervals(BSTR* Value/*[out,retval]*/) = 0;
  // [464] МежстрочныеИнтервалыПД
  virtual HRESULT STDMETHODCALLTYPE set_SlipStringIntervals(BSTR Value/*[in]*/) = 0;
  // [465] РавныеМежстрочныеИнтервалыПД
  virtual HRESULT STDMETHODCALLTYPE get_SlipEqualStringIntervals(long* Value/*[out,retval]*/) = 0;
  // [465] РавныеМежстрочныеИнтервалыПД
  virtual HRESULT STDMETHODCALLTYPE set_SlipEqualStringIntervals(long Value/*[in]*/) = 0;
  // [354] ШрифтКПКПД
  virtual HRESULT STDMETHODCALLTYPE get_KPKFont(long* Value/*[out,retval]*/) = 0;
  // [354] ШрифтКПКПД
  virtual HRESULT STDMETHODCALLTYPE set_KPKFont(long Value/*[in]*/) = 0;
  // [466] СмещениеСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckOffset(long* Value/*[out,retval]*/) = 0;
  // [466] СмещениеСкидкаНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckOffset(long Value/*[in]*/) = 0;
  // [467] СмещениеСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE get_DiscountOnCheckSumOffset(long* Value/*[out,retval]*/) = 0;
  // [467] СмещениеСуммыСкидкиНаЧекПД
  virtual HRESULT STDMETHODCALLTYPE set_DiscountOnCheckSumOffset(long Value/*[in]*/) = 0;
  // [468] ЗагрузкаГрафикиОднойКомандой
  virtual HRESULT STDMETHODCALLTYPE WideLoadLineData(long* Res/*[out,retval]*/) = 0;
  // [469] СнятьОтчётПоНалогам
  virtual HRESULT STDMETHODCALLTYPE PrintTaxReport(long* Res/*[out,retval]*/) = 0;
  // [470] ПоложениеТочкиВКоличестве
  virtual HRESULT STDMETHODCALLTYPE get_QuantityPointPosition(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [471] FileVersionMS
  virtual HRESULT STDMETHODCALLTYPE get_FileVersionMS(unsigned long* Value/*[out,retval]*/) = 0;
  // [472] FileVersionLS
  virtual HRESULT STDMETHODCALLTYPE get_FileVersionLS(unsigned long* Value/*[out,retval]*/) = 0;
  // [473] ПолучитьДлинныеЗаводскойНомерИРНМ
  virtual HRESULT STDMETHODCALLTYPE GetLongSerialNumberAndLongRNM(long* Res/*[out,retval]*/) = 0;
  // [474] УстановитьДлинныйЗаводскойНомер
  virtual HRESULT STDMETHODCALLTYPE SetLongSerialNumber(long* Res/*[out,retval]*/) = 0;
  // [475] ФискализацияСДлиннымРНМ
  virtual HRESULT STDMETHODCALLTYPE FiscalizationWithLongRNM(long* Res/*[out,retval]*/) = 0;
  // [476] НизкоеНапряжениеНаБатарее
  virtual HRESULT STDMETHODCALLTYPE get_IsBatteryLow(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [477] ПоследняяЗаписьВФПИспорчена
  virtual HRESULT STDMETHODCALLTYPE get_IsLastFMRecordCorrupted(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [478] СменаВФПОткрыта
  virtual HRESULT STDMETHODCALLTYPE get_IsFMSessionOpen(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [479] 24ЧасаВФПКончились
  virtual HRESULT STDMETHODCALLTYPE get_IsFM24HoursOver(VARIANT_BOOL* Value/*[out,retval]*/) = 0;
  // [480] УстановитьСвязь2
  virtual HRESULT STDMETHODCALLTYPE Connect2(long* Res/*[out,retval]*/) = 0;
  // [481] СтатусРежима
  virtual HRESULT STDMETHODCALLTYPE get_ECRModeStatus(long* Value/*[out,retval]*/) = 0;
  // [482] GetECRPrinterStatus
  virtual HRESULT STDMETHODCALLTYPE GetECRPrinterStatus(long* Res/*[out,retval]*/) = 0;
  // [483] PrinterStatus
  virtual HRESULT STDMETHODCALLTYPE get_PrinterStatus(long* Value/*[out,retval]*/) = 0;

//#if !defined(__TLB_NO_INTERFACE_WRAPPERS)

  long __fastcall AddLD(void)
  {
    long Res;
    (this->AddLD((long*)&Res));
    return Res;
  }

  long __fastcall Beep(void)
  {
    long Res;
    (this->Beep((long*)&Res));
    return Res;
  }

  long __fastcall Buy(void)
  {
    long Res;
    (this->Buy((long*)&Res));
    return Res;
  }

  long __fastcall BuyEx(void)
  {
    long Res;
    (this->BuyEx((long*)&Res));
    return Res;
  }

  long __fastcall CancelCheck(void)
  {
    long Res;
    (this->CancelCheck((long*)&Res));
    return Res;
  }

  long __fastcall CashIncome(void)
  {
    long Res;
    (this->CashIncome((long*)&Res));
    return Res;
  }

  long __fastcall CashOutcome(void)
  {
    long Res;
    (this->CashOutcome((long*)&Res));
    return Res;
  }

  long __fastcall Charge(void)
  {
    long Res;
    (this->Charge((long*)&Res));
    return Res;
  }

  long __fastcall CheckSubTotal(void)
  {
    long Res;
    (this->CheckSubTotal((long*)&Res));
    return Res;
  }

  long __fastcall CloseCheck(void)
  {
    long Res;
    (this->CloseCheck((long*)&Res));
    return Res;
  }

  long __fastcall ConfirmDate(void)
  {
    long Res;
    (this->ConfirmDate((long*)&Res));
    return Res;
  }

  long __fastcall Connect(void)
  {
    long Res;
    (this->Connect((long*)&Res));
    return Res;
  }

  long __fastcall ContinuePrint(void)
  {
    long Res;
    (this->ContinuePrint((long*)&Res));
    return Res;
  }

  long __fastcall Correction(void)
  {
    long Res;
    (this->Correction((long*)&Res));
    return Res;
  }

  long __fastcall CutCheck(void)
  {
    long Res;
    (this->CutCheck((long*)&Res));
    return Res;
  }

  long __fastcall DampRequest(void)
  {
    long Res;
    (this->DampRequest((long*)&Res));
    return Res;
  }

  long __fastcall DeleteLD(void)
  {
    long Res;
    (this->DeleteLD((long*)&Res));
    return Res;
  }

  long __fastcall Disconnect(void)
  {
    long Res;
    (this->Disconnect((long*)&Res));
    return Res;
  }

  long __fastcall Discount(void)
  {
    long Res;
    (this->Discount((long*)&Res));
    return Res;
  }

  long __fastcall DozeOilCheck(void)
  {
    long Res;
    (this->DozeOilCheck((long*)&Res));
    return Res;
  }

  long __fastcall Draw(void)
  {
    long Res;
    (this->Draw((long*)&Res));
    return Res;
  }

  long __fastcall EKLZDepartmentReportInDatesRange(void)
  {
    long Res;
    (this->EKLZDepartmentReportInDatesRange((long*)&Res));
    return Res;
  }

  long __fastcall EKLZDepartmentReportInSessionsRange(void)
  {
    long Res;
    (this->EKLZDepartmentReportInSessionsRange((long*)&Res));
    return Res;
  }

  long __fastcall EKLZJournalOnSessionNumber(void)
  {
    long Res;
    (this->EKLZJournalOnSessionNumber((long*)&Res));
    return Res;
  }

  long __fastcall EKLZSessionReportInDatesRange(void)
  {
    long Res;
    (this->EKLZSessionReportInDatesRange((long*)&Res));
    return Res;
  }

  long __fastcall EKLZSessionReportInSessionsRange(void)
  {
    long Res;
    (this->EKLZSessionReportInSessionsRange((long*)&Res));
    return Res;
  }

  long __fastcall ExchangeBytes(void)
  {
    long Res;
    (this->ExchangeBytes((long*)&Res));
    return Res;
  }

  long __fastcall FeedDocument(void)
  {
    long Res;
    (this->FeedDocument((long*)&Res));
    return Res;
  }

  long __fastcall Fiscalization(void)
  {
    long Res;
    (this->Fiscalization((long*)&Res));
    return Res;
  }

  long __fastcall FiscalReportForDatesRange(void)
  {
    long Res;
    (this->FiscalReportForDatesRange((long*)&Res));
    return Res;
  }

  long __fastcall FiscalReportForSessionRange(void)
  {
    long Res;
    (this->FiscalReportForSessionRange((long*)&Res));
    return Res;
  }

  long __fastcall GetActiveLD(void)
  {
    long Res;
    (this->GetActiveLD((long*)&Res));
    return Res;
  }

  long __fastcall EnumLD(void)
  {
    long Res;
    (this->EnumLD((long*)&Res));
    return Res;
  }

  long __fastcall GetCashReg(void)
  {
    long Res;
    (this->GetCashReg((long*)&Res));
    return Res;
  }

  long __fastcall GetCountLD(void)
  {
    long Res;
    (this->GetCountLD((long*)&Res));
    return Res;
  }

  long __fastcall GetData(void)
  {
    long Res;
    (this->GetData((long*)&Res));
    return Res;
  }

  long __fastcall GetDeviceMetrics(void)
  {
    long Res;
    (this->GetDeviceMetrics((long*)&Res));
    return Res;
  }

  long __fastcall GetECRStatus(void)
  {
    long Res;
    (this->GetECRStatus((long*)&Res));
    return Res;
  }

  long __fastcall GetShortECRStatus(void)
  {
    long Res;
    (this->GetShortECRStatus((long*)&Res));
    return Res;
  }

  long __fastcall GetExchangeParam(void)
  {
    long Res;
    (this->GetExchangeParam((long*)&Res));
    return Res;
  }

  long __fastcall GetFieldStruct(void)
  {
    long Res;
    (this->GetFieldStruct((long*)&Res));
    return Res;
  }

  long __fastcall GetFiscalizationParameters(void)
  {
    long Res;
    (this->GetFiscalizationParameters((long*)&Res));
    return Res;
  }

  long __fastcall GetFMRecordsSum(void)
  {
    long Res;
    (this->GetFMRecordsSum((long*)&Res));
    return Res;
  }

  long __fastcall GetLastFMRecordDate(void)
  {
    long Res;
    (this->GetLastFMRecordDate((long*)&Res));
    return Res;
  }

  long __fastcall GetLiterSumCounter(void)
  {
    long Res;
    (this->GetLiterSumCounter((long*)&Res));
    return Res;
  }

  long __fastcall GetOperationReg(void)
  {
    long Res;
    (this->GetOperationReg((long*)&Res));
    return Res;
  }

  long __fastcall GetParamLD(void)
  {
    long Res;
    (this->GetParamLD((long*)&Res));
    return Res;
  }

  long __fastcall GetRangeDatesAndSessions(void)
  {
    long Res;
    (this->GetRangeDatesAndSessions((long*)&Res));
    return Res;
  }

  long __fastcall GetRKStatus(void)
  {
    long Res;
    (this->GetRKStatus((long*)&Res));
    return Res;
  }

  long __fastcall GetTableStruct(void)
  {
    long Res;
    (this->GetTableStruct((long*)&Res));
    return Res;
  }

  long __fastcall InitFM(void)
  {
    long Res;
    (this->InitFM((long*)&Res));
    return Res;
  }

  long __fastcall InitTable(void)
  {
    long Res;
    (this->InitTable((long*)&Res));
    return Res;
  }

  long __fastcall InterruptDataStream(void)
  {
    long Res;
    (this->InterruptDataStream((long*)&Res));
    return Res;
  }

  long __fastcall InterruptFullReport(void)
  {
    long Res;
    (this->InterruptFullReport((long*)&Res));
    return Res;
  }

  long __fastcall InterruptTest(void)
  {
    long Res;
    (this->InterruptTest((long*)&Res));
    return Res;
  }

  long __fastcall LaunchRK(void)
  {
    long Res;
    (this->LaunchRK((long*)&Res));
    return Res;
  }

  long __fastcall LoadLineData(void)
  {
    long Res;
    (this->LoadLineData((long*)&Res));
    return Res;
  }

  long __fastcall OilSale(void)
  {
    long Res;
    (this->OilSale((long*)&Res));
    return Res;
  }

  long __fastcall OpenCheck(void)
  {
    long Res;
    (this->OpenCheck((long*)&Res));
    return Res;
  }

  long __fastcall OpenDrawer(void)
  {
    long Res;
    (this->OpenDrawer((long*)&Res));
    return Res;
  }

  long __fastcall PrintBarCode(void)
  {
    long Res;
    (this->PrintBarCode((long*)&Res));
    return Res;
  }

  long __fastcall PrintDepartmentReport(void)
  {
    long Res;
    (this->PrintDepartmentReport((long*)&Res));
    return Res;
  }

  long __fastcall PrintDocumentTitle(void)
  {
    long Res;
    (this->PrintDocumentTitle((long*)&Res));
    return Res;
  }

  long __fastcall PrintOperationReg(void)
  {
    long Res;
    (this->PrintOperationReg((long*)&Res));
    return Res;
  }

  long __fastcall PrintReportWithCleaning(void)
  {
    long Res;
    (this->PrintReportWithCleaning((long*)&Res));
    return Res;
  }

  long __fastcall PrintReportWithoutCleaning(void)
  {
    long Res;
    (this->PrintReportWithoutCleaning((long*)&Res));
    return Res;
  }

  long __fastcall PrintString(void)
  {
    long Res;
    (this->PrintString((long*)&Res));
    return Res;
  }

  long __fastcall PrintWideString(void)
  {
    long Res;
    (this->PrintWideString((long*)&Res));
    return Res;
  }

  long __fastcall ReadEKLZDocumentOnKPK(void)
  {
    long Res;
    (this->ReadEKLZDocumentOnKPK((long*)&Res));
    return Res;
  }

  long __fastcall ReadEKLZSessionTotal(void)
  {
    long Res;
    (this->ReadEKLZSessionTotal((long*)&Res));
    return Res;
  }

  long __fastcall ReadLicense(void)
  {
    long Res;
    (this->ReadLicense((long*)&Res));
    return Res;
  }

  long __fastcall ReadTable(void)
  {
    long Res;
    (this->ReadTable((long*)&Res));
    return Res;
  }

  long __fastcall RepeatDocument(void)
  {
    long Res;
    (this->RepeatDocument((long*)&Res));
    return Res;
  }

  long __fastcall ResetAllTRK(void)
  {
    long Res;
    (this->ResetAllTRK((long*)&Res));
    return Res;
  }

  long __fastcall ResetRK(void)
  {
    long Res;
    (this->ResetRK((long*)&Res));
    return Res;
  }

  long __fastcall ResetSettings(void)
  {
    long Res;
    (this->ResetSettings((long*)&Res));
    return Res;
  }

  long __fastcall ResetSummary(void)
  {
    long Res;
    (this->ResetSummary((long*)&Res));
    return Res;
  }

  long __fastcall ReturnBuy(void)
  {
    long Res;
    (this->ReturnBuy((long*)&Res));
    return Res;
  }

  long __fastcall ReturnBuyEx(void)
  {
    long Res;
    (this->ReturnBuyEx((long*)&Res));
    return Res;
  }

  long __fastcall ReturnSale(void)
  {
    long Res;
    (this->ReturnSale((long*)&Res));
    return Res;
  }

  long __fastcall ReturnSaleEx(void)
  {
    long Res;
    (this->ReturnSaleEx((long*)&Res));
    return Res;
  }

  long __fastcall Sale(void)
  {
    long Res;
    (this->Sale((long*)&Res));
    return Res;
  }

  long __fastcall SaleEx(void)
  {
    long Res;
    (this->SaleEx((long*)&Res));
    return Res;
  }

  long __fastcall SetActiveLD(void)
  {
    long Res;
    (this->SetActiveLD((long*)&Res));
    return Res;
  }

  long __fastcall SetDate(void)
  {
    long Res;
    (this->SetDate((long*)&Res));
    return Res;
  }

  long __fastcall SetDozeInMilliliters(void)
  {
    long Res;
    (this->SetDozeInMilliliters((long*)&Res));
    return Res;
  }

  long __fastcall SetDozeInMoney(void)
  {
    long Res;
    (this->SetDozeInMoney((long*)&Res));
    return Res;
  }

  long __fastcall SetExchangeParam(void)
  {
    long Res;
    (this->SetExchangeParam((long*)&Res));
    return Res;
  }

  long __fastcall SetParamLD(void)
  {
    long Res;
    (this->SetParamLD((long*)&Res));
    return Res;
  }

  long __fastcall SetPointPosition(void)
  {
    long Res;
    (this->SetPointPosition((long*)&Res));
    return Res;
  }

  long __fastcall SetRKParameters(void)
  {
    long Res;
    (this->SetRKParameters((long*)&Res));
    return Res;
  }

  long __fastcall SetSerialNumber(void)
  {
    long Res;
    (this->SetSerialNumber((long*)&Res));
    return Res;
  }

  long __fastcall SetTime(void)
  {
    long Res;
    (this->SetTime((long*)&Res));
    return Res;
  }

  long __fastcall ShowProperties(void)
  {
    long Res;
    (this->ShowProperties((long*)&Res));
    return Res;
  }

  long __fastcall StopEKLZDocumentPrinting(void)
  {
    long Res;
    (this->StopEKLZDocumentPrinting((long*)&Res));
    return Res;
  }

  long __fastcall StopRK(void)
  {
    long Res;
    (this->StopRK((long*)&Res));
    return Res;
  }

  long __fastcall Storno(void)
  {
    long Res;
    (this->Storno((long*)&Res));
    return Res;
  }

  long __fastcall StornoEx(void)
  {
    long Res;
    (this->StornoEx((long*)&Res));
    return Res;
  }

  long __fastcall StornoCharge(void)
  {
    long Res;
    (this->StornoCharge((long*)&Res));
    return Res;
  }

  long __fastcall StornoDiscount(void)
  {
    long Res;
    (this->StornoDiscount((long*)&Res));
    return Res;
  }

  long __fastcall SummOilCheck(void)
  {
    long Res;
    (this->SummOilCheck((long*)&Res));
    return Res;
  }

  long __fastcall SysAdminCancelCheck(void)
  {
    long Res;
    (this->SysAdminCancelCheck((long*)&Res));
    return Res;
  }

  long __fastcall Test(void)
  {
    long Res;
    (this->Test((long*)&Res));
    return Res;
  }

  long __fastcall WriteLicense(void)
  {
    long Res;
    (this->WriteLicense((long*)&Res));
    return Res;
  }

  long __fastcall WriteTable(void)
  {
    long Res;
    (this->WriteTable((long*)&Res));
    return Res;
  }

  BSTR __fastcall get_BarCode(void)
  {
    BSTR Value = 0;
    (this->get_BarCode((BSTR*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_BatteryCondition(void)
  {
    VARIANT_BOOL Value;
    (this->get_BatteryCondition((VARIANT_BOOL*)&Value));
    return Value;
  }

  double __fastcall get_BatteryVoltage(void)
  {
    double Value;
    (this->get_BatteryVoltage((double*)&Value));
    return Value;
  }

  long __fastcall get_BaudRate(void)
  {
    long Value;
    (this->get_BaudRate((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Change(void)
  {
    CURRENCY Value;
    (this->get_Change((CURRENCY*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_CheckResult(void)
  {
    CURRENCY Value;
    (this->get_CheckResult((CURRENCY*)&Value));
    return Value;
  }

  long __fastcall get_CheckType(void)
  {
    long Value;
    (this->get_CheckType((long*)&Value));
    return Value;
  }

  long __fastcall get_ComNumber(void)
  {
    long Value;
    (this->get_ComNumber((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_ContentsOfCashRegister(void)
  {
    CURRENCY Value;
    (this->get_ContentsOfCashRegister((CURRENCY*)&Value));
    return Value;
  }

  long __fastcall get_ContentsOfOperationRegister(void)
  {
    long Value;
    (this->get_ContentsOfOperationRegister((long*)&Value));
    return Value;
  }

  long __fastcall get_CurrentDozeInMilliliters(void)
  {
    long Value;
    (this->get_CurrentDozeInMilliliters((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_CurrentDozeInMoney(void)
  {
    CURRENCY Value;
    (this->get_CurrentDozeInMoney((CURRENCY*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_CutType(void)
  {
    VARIANT_BOOL Value;
    (this->get_CutType((VARIANT_BOOL*)&Value));
    return Value;
  }

  BSTR __fastcall get_DataBlock(void)
  {
    BSTR Value = 0;
    (this->get_DataBlock((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_DataBlockNumber(void)
  {
    long Value;
    (this->get_DataBlockNumber((long*)&Value));
    return Value;
  }

  DATE __fastcall get_Date(void)
  {
    DATE Value;
    (this->get_Date((DATE*)&Value));
    return Value;
  }

  long __fastcall get_Department(void)
  {
    long Value;
    (this->get_Department((long*)&Value));
    return Value;
  }

  long __fastcall get_DeviceCode(void)
  {
    long Value;
    (this->get_DeviceCode((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_DeviceCodeDescription(void)
  {
    BSTR Value = 0;
    (this->get_DeviceCodeDescription((BSTR*)&Value));
    return Value;
  }

  double __fastcall get_DiscountOnCheck(void)
  {
    double Value;
    (this->get_DiscountOnCheck((double*)&Value));
    return Value;
  }

  BSTR __fastcall get_DocumentName(void)
  {
    BSTR Value = 0;
    (this->get_DocumentName((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_DocumentNumber(void)
  {
    long Value;
    (this->get_DocumentNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DozeInMilliliters(void)
  {
    long Value;
    (this->get_DozeInMilliliters((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_DozeInMoney(void)
  {
    CURRENCY Value;
    (this->get_DozeInMoney((CURRENCY*)&Value));
    return Value;
  }

  long __fastcall get_DrawerNumber(void)
  {
    long Value;
    (this->get_DrawerNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_ECRAdvancedMode(void)
  {
    long Value;
    (this->get_ECRAdvancedMode((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_ECRAdvancedModeDescription(void)
  {
    BSTR Value = 0;
    (this->get_ECRAdvancedModeDescription((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_ECRBuild(void)
  {
    long Value;
    (this->get_ECRBuild((long*)&Value));
    return Value;
  }

  long __fastcall get_ECRFlags(void)
  {
    long Value;
    (this->get_ECRFlags((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_ECRInput(void)
  {
    BSTR Value = 0;
    (this->get_ECRInput((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_ECRMode(void)
  {
    long Value;
    (this->get_ECRMode((long*)&Value));
    return Value;
  }

  long __fastcall get_ECRMode8Status(void)
  {
    long Value;
    (this->get_ECRMode8Status((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_ECRModeDescription(void)
  {
    BSTR Value = 0;
    (this->get_ECRModeDescription((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_ECROutput(void)
  {
    BSTR Value = 0;
    (this->get_ECROutput((BSTR*)&Value));
    return Value;
  }

  DATE __fastcall get_ECRSoftDate(void)
  {
    DATE Value;
    (this->get_ECRSoftDate((DATE*)&Value));
    return Value;
  }

  BSTR __fastcall get_ECRSoftVersion(void)
  {
    BSTR Value = 0;
    (this->get_ECRSoftVersion((BSTR*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_EKLZIsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_EKLZIsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_EmergencyStopCode(void)
  {
    long Value;
    (this->get_EmergencyStopCode((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_EmergencyStopCodeDescription(void)
  {
    BSTR Value = 0;
    (this->get_EmergencyStopCodeDescription((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_FieldName(void)
  {
    BSTR Value = 0;
    (this->get_FieldName((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_FieldNumber(void)
  {
    long Value;
    (this->get_FieldNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_FieldSize(void)
  {
    long Value;
    (this->get_FieldSize((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_FieldType(void)
  {
    VARIANT_BOOL Value;
    (this->get_FieldType((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_FirstLineNumber(void)
  {
    long Value;
    (this->get_FirstLineNumber((long*)&Value));
    return Value;
  }

  DATE __fastcall get_FirstSessionDate(void)
  {
    DATE Value;
    (this->get_FirstSessionDate((DATE*)&Value));
    return Value;
  }

  long __fastcall get_FirstSessionNumber(void)
  {
    long Value;
    (this->get_FirstSessionNumber((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_FM1IsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_FM1IsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_FM2IsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_FM2IsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_FMBuild(void)
  {
    long Value;
    (this->get_FMBuild((long*)&Value));
    return Value;
  }

  long __fastcall get_FMFlags(void)
  {
    long Value;
    (this->get_FMFlags((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_FMOverflow(void)
  {
    VARIANT_BOOL Value;
    (this->get_FMOverflow((VARIANT_BOOL*)&Value));
    return Value;
  }

  DATE __fastcall get_FMSoftDate(void)
  {
    DATE Value;
    (this->get_FMSoftDate((DATE*)&Value));
    return Value;
  }

  BSTR __fastcall get_FMSoftVersion(void)
  {
    BSTR Value = 0;
    (this->get_FMSoftVersion((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_FreeRecordInFM(void)
  {
    long Value;
    (this->get_FreeRecordInFM((long*)&Value));
    return Value;
  }

  long __fastcall get_FreeRegistration(void)
  {
    long Value;
    (this->get_FreeRegistration((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_INN(void)
  {
    BSTR Value = 0;
    (this->get_INN((BSTR*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsCheckClosed(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsCheckClosed((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsCheckMadeOut(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsCheckMadeOut((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsDrawerOpen(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsDrawerOpen((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_JournalRibbonIsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_JournalRibbonIsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_JournalRibbonLever(void)
  {
    VARIANT_BOOL Value;
    (this->get_JournalRibbonLever((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_JournalRibbonOpticalSensor(void)
  {
    VARIANT_BOOL Value;
    (this->get_JournalRibbonOpticalSensor((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_KPKNumber(void)
  {
    long Value;
    (this->get_KPKNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_LastLineNumber(void)
  {
    long Value;
    (this->get_LastLineNumber((long*)&Value));
    return Value;
  }

  DATE __fastcall get_LastSessionDate(void)
  {
    DATE Value;
    (this->get_LastSessionDate((DATE*)&Value));
    return Value;
  }

  long __fastcall get_LastSessionNumber(void)
  {
    long Value;
    (this->get_LastSessionNumber((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_License(void)
  {
    BSTR Value = 0;
    (this->get_License((BSTR*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_LicenseIsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_LicenseIsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_LidPositionSensor(void)
  {
    VARIANT_BOOL Value;
    (this->get_LidPositionSensor((VARIANT_BOOL*)&Value));
    return Value;
  }

  BSTR __fastcall get_LineData(void)
  {
    BSTR Value = 0;
    (this->get_LineData((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_LineNumber(void)
  {
    long Value;
    (this->get_LineNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_LogicalNumber(void)
  {
    long Value;
    (this->get_LogicalNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_MAXValueOfField(void)
  {
    long Value;
    (this->get_MAXValueOfField((long*)&Value));
    return Value;
  }

  long __fastcall get_MINValueOfField(void)
  {
    long Value;
    (this->get_MINValueOfField((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_Motor(void)
  {
    VARIANT_BOOL Value;
    (this->get_Motor((VARIANT_BOOL*)&Value));
    return Value;
  }

  BSTR __fastcall get_NameCashReg(void)
  {
    BSTR Value = 0;
    (this->get_NameCashReg((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_NameOperationReg(void)
  {
    BSTR Value = 0;
    (this->get_NameOperationReg((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_NewPasswordTI(void)
  {
    long Value;
    (this->get_NewPasswordTI((long*)&Value));
    return Value;
  }

  long __fastcall get_OpenDocumentNumber(void)
  {
    long Value;
    (this->get_OpenDocumentNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_OperatorNumber(void)
  {
    long Value;
    (this->get_OperatorNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Password(void)
  {
    long Value;
    (this->get_Password((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_Pistol(void)
  {
    VARIANT_BOOL Value;
    (this->get_Pistol((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_PointPosition(void)
  {
    VARIANT_BOOL Value;
    (this->get_PointPosition((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_PortNumber(void)
  {
    long Value;
    (this->get_PortNumber((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Price(void)
  {
    CURRENCY Value;
    (this->get_Price((CURRENCY*)&Value));
    return Value;
  }

  double __fastcall get_Quantity(void)
  {
    double Value;
    (this->get_Quantity((double*)&Value));
    return Value;
  }

  long __fastcall get_QuantityOfOperations(void)
  {
    long Value;
    (this->get_QuantityOfOperations((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_ReceiptRibbonIsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_ReceiptRibbonIsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_ReceiptRibbonLever(void)
  {
    VARIANT_BOOL Value;
    (this->get_ReceiptRibbonLever((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_ReceiptRibbonOpticalSensor(void)
  {
    VARIANT_BOOL Value;
    (this->get_ReceiptRibbonOpticalSensor((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_RegisterNumber(void)
  {
    long Value;
    (this->get_RegisterNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_RegistrationNumber(void)
  {
    long Value;
    (this->get_RegistrationNumber((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_ReportType(void)
  {
    VARIANT_BOOL Value;
    (this->get_ReportType((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_ResultCode(void)
  {
    long Value;
    (this->get_ResultCode((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_ResultCodeDescription(void)
  {
    BSTR Value = 0;
    (this->get_ResultCodeDescription((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_RKNumber(void)
  {
    long Value;
    (this->get_RKNumber((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_RNM(void)
  {
    BSTR Value = 0;
    (this->get_RNM((BSTR*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_RoughValve(void)
  {
    VARIANT_BOOL Value;
    (this->get_RoughValve((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_RowNumber(void)
  {
    long Value;
    (this->get_RowNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_RunningPeriod(void)
  {
    long Value;
    (this->get_RunningPeriod((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_SerialNumber(void)
  {
    BSTR Value = 0;
    (this->get_SerialNumber((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_SessionNumber(void)
  {
    long Value;
    (this->get_SessionNumber((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_SlipDocumentIsMoving(void)
  {
    VARIANT_BOOL Value;
    (this->get_SlipDocumentIsMoving((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_SlipDocumentIsPresent(void)
  {
    VARIANT_BOOL Value;
    (this->get_SlipDocumentIsPresent((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_SlowingInMilliliters(void)
  {
    long Value;
    (this->get_SlowingInMilliliters((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_SlowingValve(void)
  {
    VARIANT_BOOL Value;
    (this->get_SlowingValve((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_StatusRK(void)
  {
    long Value;
    (this->get_StatusRK((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_StatusRKDescription(void)
  {
    BSTR Value = 0;
    (this->get_StatusRKDescription((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_StringForPrinting(void)
  {
    BSTR Value = 0;
    (this->get_StringForPrinting((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_StringQuantity(void)
  {
    long Value;
    (this->get_StringQuantity((long*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Summ1(void)
  {
    CURRENCY Value;
    (this->get_Summ1((CURRENCY*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Summ2(void)
  {
    CURRENCY Value;
    (this->get_Summ2((CURRENCY*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Summ3(void)
  {
    CURRENCY Value;
    (this->get_Summ3((CURRENCY*)&Value));
    return Value;
  }

  CURRENCY __fastcall get_Summ4(void)
  {
    CURRENCY Value;
    (this->get_Summ4((CURRENCY*)&Value));
    return Value;
  }

  BSTR __fastcall get_TableName(void)
  {
    BSTR Value = 0;
    (this->get_TableName((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_TableNumber(void)
  {
    long Value;
    (this->get_TableNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1(void)
  {
    long Value;
    (this->get_Tax1((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2(void)
  {
    long Value;
    (this->get_Tax2((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3(void)
  {
    long Value;
    (this->get_Tax3((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4(void)
  {
    long Value;
    (this->get_Tax4((long*)&Value));
    return Value;
  }

  DATE __fastcall get_Time(void)
  {
    DATE Value;
    (this->get_Time((DATE*)&Value));
    return Value;
  }

  long __fastcall get_Timeout(void)
  {
    long Value;
    (this->get_Timeout((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_TimeStr(void)
  {
    BSTR Value = 0;
    (this->get_TimeStr((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_TransferBytes(void)
  {
    BSTR Value = 0;
    (this->get_TransferBytes((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_TRKNumber(void)
  {
    long Value;
    (this->get_TRKNumber((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_TypeOfLastEntryFM(void)
  {
    VARIANT_BOOL Value;
    (this->get_TypeOfLastEntryFM((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_TypeOfSumOfEntriesFM(void)
  {
    VARIANT_BOOL Value;
    (this->get_TypeOfSumOfEntriesFM((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_UCodePage(void)
  {
    long Value;
    (this->get_UCodePage((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_UDescription(void)
  {
    BSTR Value = 0;
    (this->get_UDescription((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_UMajorProtocolVersion(void)
  {
    long Value;
    (this->get_UMajorProtocolVersion((long*)&Value));
    return Value;
  }

  long __fastcall get_UMajorType(void)
  {
    long Value;
    (this->get_UMajorType((long*)&Value));
    return Value;
  }

  long __fastcall get_UMinorProtocolVersion(void)
  {
    long Value;
    (this->get_UMinorProtocolVersion((long*)&Value));
    return Value;
  }

  long __fastcall get_UMinorType(void)
  {
    long Value;
    (this->get_UMinorType((long*)&Value));
    return Value;
  }

  long __fastcall get_UModel(void)
  {
    long Value;
    (this->get_UModel((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_UseJournalRibbon(void)
  {
    VARIANT_BOOL Value;
    (this->get_UseJournalRibbon((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_UseReceiptRibbon(void)
  {
    VARIANT_BOOL Value;
    (this->get_UseReceiptRibbon((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_UseSlipDocument(void)
  {
    VARIANT_BOOL Value;
    (this->get_UseSlipDocument((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_ValueOfFieldInteger(void)
  {
    long Value;
    (this->get_ValueOfFieldInteger((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_ValueOfFieldString(void)
  {
    BSTR Value = 0;
    (this->get_ValueOfFieldString((BSTR*)&Value));
    return Value;
  }

  long __fastcall PrintStringWithFont(void)
  {
    long Res;
    (this->PrintStringWithFont((long*)&Res));
    return Res;
  }

  long __fastcall get_FontType(void)
  {
    long Value;
    (this->get_FontType((long*)&Value));
    return Value;
  }

  long __fastcall get_LDBaudrate(void)
  {
    long Value;
    (this->get_LDBaudrate((long*)&Value));
    return Value;
  }

  long __fastcall get_LDComNumber(void)
  {
    long Value;
    (this->get_LDComNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_LDCount(void)
  {
    long Value;
    (this->get_LDCount((long*)&Value));
    return Value;
  }

  long __fastcall get_LDIndex(void)
  {
    long Value;
    (this->get_LDIndex((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_LDName(void)
  {
    BSTR Value = 0;
    (this->get_LDName((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_LDNumber(void)
  {
    long Value;
    (this->get_LDNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_WaitPrintingTime(void)
  {
    long Value;
    (this->get_WaitPrintingTime((long*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsPrinterLeftSensorFailure(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsPrinterLeftSensorFailure((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsPrinterRightSensorFailure(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsPrinterRightSensorFailure((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall EKLZActivizationResult(void)
  {
    long Res;
    (this->EKLZActivizationResult((long*)&Res));
    return Res;
  }

  long __fastcall EKLZActivization(void)
  {
    long Res;
    (this->EKLZActivization((long*)&Res));
    return Res;
  }

  long __fastcall CloseEKLZArchive(void)
  {
    long Res;
    (this->CloseEKLZArchive((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZSerialNumber(void)
  {
    long Res;
    (this->GetEKLZSerialNumber((long*)&Res));
    return Res;
  }

  BSTR __fastcall get_EKLZNumber(void)
  {
    BSTR Value = 0;
    (this->get_EKLZNumber((BSTR*)&Value));
    return Value;
  }

  long __fastcall EKLZInterrupt(void)
  {
    long Res;
    (this->EKLZInterrupt((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZCode1Report(void)
  {
    long Res;
    (this->GetEKLZCode1Report((long*)&Res));
    return Res;
  }

  CURRENCY __fastcall get_LastKPKDocumentResult(void)
  {
    CURRENCY Value;
    (this->get_LastKPKDocumentResult((CURRENCY*)&Value));
    return Value;
  }

  DATE __fastcall get_LastKPKDate(void)
  {
    DATE Value;
    (this->get_LastKPKDate((DATE*)&Value));
    return Value;
  }

  DATE __fastcall get_LastKPKTime(void)
  {
    DATE Value;
    (this->get_LastKPKTime((DATE*)&Value));
    return Value;
  }

  long __fastcall get_LastKPKNumber(void)
  {
    long Value;
    (this->get_LastKPKNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_EKLZFlags(void)
  {
    long Value;
    (this->get_EKLZFlags((long*)&Value));
    return Value;
  }

  long __fastcall GetEKLZCode2Report(void)
  {
    long Res;
    (this->GetEKLZCode2Report((long*)&Res));
    return Res;
  }

  long __fastcall TestEKLZArchiveIntegrity(void)
  {
    long Res;
    (this->TestEKLZArchiveIntegrity((long*)&Res));
    return Res;
  }

  long __fastcall get_TestNumber(void)
  {
    long Value;
    (this->get_TestNumber((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_EKLZVersion(void)
  {
    BSTR Value = 0;
    (this->get_EKLZVersion((BSTR*)&Value));
    return Value;
  }

  BSTR __fastcall get_EKLZData(void)
  {
    BSTR Value = 0;
    (this->get_EKLZData((BSTR*)&Value));
    return Value;
  }

  long __fastcall GetEKLZVersion(void)
  {
    long Res;
    (this->GetEKLZVersion((long*)&Res));
    return Res;
  }

  long __fastcall InitEKLZArchive(void)
  {
    long Res;
    (this->InitEKLZArchive((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZData(void)
  {
    long Res;
    (this->GetEKLZData((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZJournal(void)
  {
    long Res;
    (this->GetEKLZJournal((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZDocument(void)
  {
    long Res;
    (this->GetEKLZDocument((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZDepartmentReportInDatesRange(void)
  {
    long Res;
    (this->GetEKLZDepartmentReportInDatesRange((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZDepartmentReportInSessionsRange(void)
  {
    long Res;
    (this->GetEKLZDepartmentReportInSessionsRange((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZSessionReportInDatesRange(void)
  {
    long Res;
    (this->GetEKLZSessionReportInDatesRange((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZSessionReportInSessionsRange(void)
  {
    long Res;
    (this->GetEKLZSessionReportInSessionsRange((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZSessionTotal(void)
  {
    long Res;
    (this->GetEKLZSessionTotal((long*)&Res));
    return Res;
  }

  long __fastcall GetEKLZActivizationResult(void)
  {
    long Res;
    (this->GetEKLZActivizationResult((long*)&Res));
    return Res;
  }

  long __fastcall SetEKLZResultCode(void)
  {
    long Res;
    (this->SetEKLZResultCode((long*)&Res));
    return Res;
  }

  long __fastcall get_EKLZResultCode(void)
  {
    long Value;
    (this->get_EKLZResultCode((long*)&Value));
    return Value;
  }

  long __fastcall get_FMResultCode(void)
  {
    long Value;
    (this->get_FMResultCode((long*)&Value));
    return Value;
  }

  double __fastcall get_PowerSourceVoltage(void)
  {
    double Value;
    (this->get_PowerSourceVoltage((double*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsEKLZOverflow(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsEKLZOverflow((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall OpenFiscalSlipDocument(void)
  {
    long Res;
    (this->OpenFiscalSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall OpenStandardFiscalSlipDocument(void)
  {
    long Res;
    (this->OpenStandardFiscalSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall RegistrationOnSlipDocument(void)
  {
    long Res;
    (this->RegistrationOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall StandardRegistrationOnSlipDocument(void)
  {
    long Reg;
    (this->StandardRegistrationOnSlipDocument((long*)&Reg));
    return Reg;
  }

  long __fastcall ChargeOnSlipDocument(void)
  {
    long Res;
    (this->ChargeOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall StandardChargeOnSlipDocument(void)
  {
    long Res;
    (this->StandardChargeOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall CloseCheckOnSlipDocument(void)
  {
    long Res;
    (this->CloseCheckOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall StandardCloseCheckOnSlipDocument(void)
  {
    long Res;
    (this->StandardCloseCheckOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall ConfigureSlipDocument(void)
  {
    long Res;
    (this->ConfigureSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall ConfigureStandardSlipDocument(void)
  {
    long Res;
    (this->ConfigureStandardSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall FillSlipDocumentWithUnfiscalInfo(void)
  {
    long Res;
    (this->FillSlipDocumentWithUnfiscalInfo((long*)&Res));
    return Res;
  }

  long __fastcall ClearSlipDocumentBufferString(void)
  {
    long Res;
    (this->ClearSlipDocumentBufferString((long*)&Res));
    return Res;
  }

  long __fastcall ClearSlipDocumentBuffer(void)
  {
    long Res;
    (this->ClearSlipDocumentBuffer((long*)&Res));
    return Res;
  }

  long __fastcall PrintSlipDocument(void)
  {
    long Res;
    (this->PrintSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall get_CopyType(void)
  {
    long Value;
    (this->get_CopyType((long*)&Value));
    return Value;
  }

  long __fastcall get_NumberOfCopies(void)
  {
    long Value;
    (this->get_NumberOfCopies((long*)&Value));
    return Value;
  }

  long __fastcall get_CopyOffset1(void)
  {
    long Value;
    (this->get_CopyOffset1((long*)&Value));
    return Value;
  }

  long __fastcall get_CopyOffset2(void)
  {
    long Value;
    (this->get_CopyOffset2((long*)&Value));
    return Value;
  }

  long __fastcall get_CopyOffset3(void)
  {
    long Value;
    (this->get_CopyOffset3((long*)&Value));
    return Value;
  }

  long __fastcall get_CopyOffset4(void)
  {
    long Value;
    (this->get_CopyOffset4((long*)&Value));
    return Value;
  }

  long __fastcall get_CopyOffset5(void)
  {
    long Value;
    (this->get_CopyOffset5((long*)&Value));
    return Value;
  }

  long __fastcall get_ClicheFont(void)
  {
    long Value;
    (this->get_ClicheFont((long*)&Value));
    return Value;
  }

  long __fastcall get_HeaderFont(void)
  {
    long Value;
    (this->get_HeaderFont((long*)&Value));
    return Value;
  }

  long __fastcall get_EKLZFont(void)
  {
    long Value;
    (this->get_EKLZFont((long*)&Value));
    return Value;
  }

  long __fastcall get_ClicheStringNumber(void)
  {
    long Value;
    (this->get_ClicheStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_HeaderStringNumber(void)
  {
    long Value;
    (this->get_HeaderStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_EKLZStringNumber(void)
  {
    long Value;
    (this->get_EKLZStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_FMStringNumber(void)
  {
    long Value;
    (this->get_FMStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_ClicheOffset(void)
  {
    long Value;
    (this->get_ClicheOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_HeaderOffset(void)
  {
    long Value;
    (this->get_HeaderOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_EKLZOffset(void)
  {
    long Value;
    (this->get_EKLZOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_KPKOffset(void)
  {
    long Value;
    (this->get_KPKOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_FMOffset(void)
  {
    long Value;
    (this->get_FMOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_OperationBlockFirstString(void)
  {
    long Value;
    (this->get_OperationBlockFirstString((long*)&Value));
    return Value;
  }

  long __fastcall get_QuantityFormat(void)
  {
    long Value;
    (this->get_QuantityFormat((long*)&Value));
    return Value;
  }

  long __fastcall get_StringQuantityInOperation(void)
  {
    long Value;
    (this->get_StringQuantityInOperation((long*)&Value));
    return Value;
  }

  long __fastcall get_TextStringNumber(void)
  {
    long Value;
    (this->get_TextStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_QuantityStringNumber(void)
  {
    long Value;
    (this->get_QuantityStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_SummStringNumber(void)
  {
    long Value;
    (this->get_SummStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DepartmentStringNumber(void)
  {
    long Value;
    (this->get_DepartmentStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_TextFont(void)
  {
    long Value;
    (this->get_TextFont((long*)&Value));
    return Value;
  }

  long __fastcall get_QuantityFont(void)
  {
    long Value;
    (this->get_QuantityFont((long*)&Value));
    return Value;
  }

  long __fastcall get_MultiplicationFont(void)
  {
    long Value;
    (this->get_MultiplicationFont((long*)&Value));
    return Value;
  }

  long __fastcall get_PriceFont(void)
  {
    long Value;
    (this->get_PriceFont((long*)&Value));
    return Value;
  }

  long __fastcall get_SummFont(void)
  {
    long Value;
    (this->get_SummFont((long*)&Value));
    return Value;
  }

  long __fastcall get_DepartmentFont(void)
  {
    long Value;
    (this->get_DepartmentFont((long*)&Value));
    return Value;
  }

  long __fastcall get_TextSymbolNumber(void)
  {
    long Value;
    (this->get_TextSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_QuantitySymbolNumber(void)
  {
    long Value;
    (this->get_QuantitySymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_PriceSymbolNumber(void)
  {
    long Value;
    (this->get_PriceSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_SummSymbolNumber(void)
  {
    long Value;
    (this->get_SummSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DepartmentSymbolNumber(void)
  {
    long Value;
    (this->get_DepartmentSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_TextOffset(void)
  {
    long Value;
    (this->get_TextOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_QuantityOffset(void)
  {
    long Value;
    (this->get_QuantityOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_SummOffset(void)
  {
    long Value;
    (this->get_SummOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_DepartmentOffset(void)
  {
    long Value;
    (this->get_DepartmentOffset((long*)&Value));
    return Value;
  }

  long __fastcall DiscountOnSlipDocument(void)
  {
    long Res;
    (this->DiscountOnSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall StandardDiscountOnSlipDocument(void)
  {
    long Res;
    (this->StandardDiscountOnSlipDocument((long*)&Res));
    return Res;
  }

  VARIANT_BOOL __fastcall get_IsClearUnfiscalInfo(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsClearUnfiscalInfo((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall get_InfoType(void)
  {
    long Value;
    (this->get_InfoType((long*)&Value));
    return Value;
  }

  long __fastcall get_StringNumber(void)
  {
    long Value;
    (this->get_StringNumber((long*)&Value));
    return Value;
  }

  long __fastcall EjectSlipDocument(void)
  {
    long Res;
    (this->EjectSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall get_EjectDirection(void)
  {
    long Value;
    (this->get_EjectDirection((long*)&Value));
    return Value;
  }

  long __fastcall LoadLineDataEx(void)
  {
    long Res;
    (this->LoadLineDataEx((long*)&Res));
    return Res;
  }

  long __fastcall DrawEx(void)
  {
    long Res;
    (this->DrawEx((long*)&Res));
    return Res;
  }

  long __fastcall ConfigureGeneralSlipDocument(void)
  {
    long Res;
    (this->ConfigureGeneralSlipDocument((long*)&Res));
    return Res;
  }

  long __fastcall get_OperationNameStringNumber(void)
  {
    long Value;
    (this->get_OperationNameStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_OperationNameFont(void)
  {
    long Value;
    (this->get_OperationNameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_OperationNameOffset(void)
  {
    long Value;
    (this->get_OperationNameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalStringNumber(void)
  {
    long Value;
    (this->get_TotalStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1StringNumber(void)
  {
    long Value;
    (this->get_Summ1StringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2StringNumber(void)
  {
    long Value;
    (this->get_Summ2StringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3StringNumber(void)
  {
    long Value;
    (this->get_Summ3StringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4StringNumber(void)
  {
    long Value;
    (this->get_Summ4StringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeStringNumber(void)
  {
    long Value;
    (this->get_ChangeStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1TurnOverStringNumber(void)
  {
    long Value;
    (this->get_Tax1TurnOverStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2TurnOverStringNumber(void)
  {
    long Value;
    (this->get_Tax2TurnOverStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3TurnOverStringNumber(void)
  {
    long Value;
    (this->get_Tax3TurnOverStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4TurnOverStringNumber(void)
  {
    long Value;
    (this->get_Tax4TurnOverStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1SumStringNumber(void)
  {
    long Value;
    (this->get_Tax1SumStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2SumStringNumber(void)
  {
    long Value;
    (this->get_Tax2SumStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3SumStringNumber(void)
  {
    long Value;
    (this->get_Tax3SumStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4SumStringNumber(void)
  {
    long Value;
    (this->get_Tax4SumStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalStringNumber(void)
  {
    long Value;
    (this->get_SubTotalStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckStringNumber(void)
  {
    long Value;
    (this->get_DiscountOnCheckStringNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalFont(void)
  {
    long Value;
    (this->get_TotalFont((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalSumFont(void)
  {
    long Value;
    (this->get_TotalSumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1Font(void)
  {
    long Value;
    (this->get_Summ1Font((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1NameFont(void)
  {
    long Value;
    (this->get_Summ1NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2NameFont(void)
  {
    long Value;
    (this->get_Summ2NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3NameFont(void)
  {
    long Value;
    (this->get_Summ3NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4NameFont(void)
  {
    long Value;
    (this->get_Summ4NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2Font(void)
  {
    long Value;
    (this->get_Summ2Font((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3Font(void)
  {
    long Value;
    (this->get_Summ3Font((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4Font(void)
  {
    long Value;
    (this->get_Summ4Font((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeFont(void)
  {
    long Value;
    (this->get_ChangeFont((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeSumFont(void)
  {
    long Value;
    (this->get_ChangeSumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1NameFont(void)
  {
    long Value;
    (this->get_Tax1NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2NameFont(void)
  {
    long Value;
    (this->get_Tax2NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3NameFont(void)
  {
    long Value;
    (this->get_Tax3NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4NameFont(void)
  {
    long Value;
    (this->get_Tax4NameFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1TurnOverFont(void)
  {
    long Value;
    (this->get_Tax1TurnOverFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2TurnOverFont(void)
  {
    long Value;
    (this->get_Tax2TurnOverFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3TurnOverFont(void)
  {
    long Value;
    (this->get_Tax3TurnOverFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4TurnOverFont(void)
  {
    long Value;
    (this->get_Tax4TurnOverFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1RateFont(void)
  {
    long Value;
    (this->get_Tax1RateFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2RateFont(void)
  {
    long Value;
    (this->get_Tax2RateFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3RateFont(void)
  {
    long Value;
    (this->get_Tax3RateFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4RateFont(void)
  {
    long Value;
    (this->get_Tax4RateFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1SumFont(void)
  {
    long Value;
    (this->get_Tax1SumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2SumFont(void)
  {
    long Value;
    (this->get_Tax2SumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3SumFont(void)
  {
    long Value;
    (this->get_Tax3SumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4SumFont(void)
  {
    long Value;
    (this->get_Tax4SumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalFont(void)
  {
    long Value;
    (this->get_SubTotalFont((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalSumFont(void)
  {
    long Value;
    (this->get_SubTotalSumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckFont(void)
  {
    long Value;
    (this->get_DiscountOnCheckFont((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckSumFont(void)
  {
    long Value;
    (this->get_DiscountOnCheckSumFont((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalSymbolNumber(void)
  {
    long Value;
    (this->get_TotalSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1SymbolNumber(void)
  {
    long Value;
    (this->get_Summ1SymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2SymbolNumber(void)
  {
    long Value;
    (this->get_Summ2SymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3SymbolNumber(void)
  {
    long Value;
    (this->get_Summ3SymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4SymbolNumber(void)
  {
    long Value;
    (this->get_Summ4SymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeSymbolNumber(void)
  {
    long Value;
    (this->get_ChangeSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1NameSymbolNumber(void)
  {
    long Value;
    (this->get_Tax1NameSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1TurnOverSymbolNumber(void)
  {
    long Value;
    (this->get_Tax1TurnOverSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1RateSymbolNumber(void)
  {
    long Value;
    (this->get_Tax1RateSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1SumSymbolNumber(void)
  {
    long Value;
    (this->get_Tax1SumSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2NameSymbolNumber(void)
  {
    long Value;
    (this->get_Tax2NameSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2TurnOverSymbolNumber(void)
  {
    long Value;
    (this->get_Tax2TurnOverSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2RateSymbolNumber(void)
  {
    long Value;
    (this->get_Tax2RateSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2SumSymbolNumber(void)
  {
    long Value;
    (this->get_Tax2SumSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3NameSymbolNumber(void)
  {
    long Value;
    (this->get_Tax3NameSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3TurnOverSymbolNumber(void)
  {
    long Value;
    (this->get_Tax3TurnOverSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3RateSymbolNumber(void)
  {
    long Value;
    (this->get_Tax3RateSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3SumSymbolNumber(void)
  {
    long Value;
    (this->get_Tax3SumSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4NameSymbolNumber(void)
  {
    long Value;
    (this->get_Tax4NameSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4TurnOverSymbolNumber(void)
  {
    long Value;
    (this->get_Tax4TurnOverSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4RateSymbolNumber(void)
  {
    long Value;
    (this->get_Tax4RateSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4SumSymbolNumber(void)
  {
    long Value;
    (this->get_Tax4SumSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalSymbolNumber(void)
  {
    long Value;
    (this->get_SubTotalSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckSymbolNumber(void)
  {
    long Value;
    (this->get_DiscountOnCheckSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckSumSymbolNumber(void)
  {
    long Value;
    (this->get_DiscountOnCheckSumSymbolNumber((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalOffset(void)
  {
    long Value;
    (this->get_TotalOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1Offset(void)
  {
    long Value;
    (this->get_Summ1Offset((long*)&Value));
    return Value;
  }

  long __fastcall get_TotalSumOffset(void)
  {
    long Value;
    (this->get_TotalSumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ1NameOffset(void)
  {
    long Value;
    (this->get_Summ1NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2Offset(void)
  {
    long Value;
    (this->get_Summ2Offset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ2NameOffset(void)
  {
    long Value;
    (this->get_Summ2NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3Offset(void)
  {
    long Value;
    (this->get_Summ3Offset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ3NameOffset(void)
  {
    long Value;
    (this->get_Summ3NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4Offset(void)
  {
    long Value;
    (this->get_Summ4Offset((long*)&Value));
    return Value;
  }

  long __fastcall get_Summ4NameOffset(void)
  {
    long Value;
    (this->get_Summ4NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeOffset(void)
  {
    long Value;
    (this->get_ChangeOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_ChangeSumOffset(void)
  {
    long Value;
    (this->get_ChangeSumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1NameOffset(void)
  {
    long Value;
    (this->get_Tax1NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1TurnOverOffset(void)
  {
    long Value;
    (this->get_Tax1TurnOverOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1RateOffset(void)
  {
    long Value;
    (this->get_Tax1RateOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax1SumOffset(void)
  {
    long Value;
    (this->get_Tax1SumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2NameOffset(void)
  {
    long Value;
    (this->get_Tax2NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2TurnOverOffset(void)
  {
    long Value;
    (this->get_Tax2TurnOverOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2RateOffset(void)
  {
    long Value;
    (this->get_Tax2RateOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax2SumOffset(void)
  {
    long Value;
    (this->get_Tax2SumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3NameOffset(void)
  {
    long Value;
    (this->get_Tax3NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3TurnOverOffset(void)
  {
    long Value;
    (this->get_Tax3TurnOverOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3RateOffset(void)
  {
    long Value;
    (this->get_Tax3RateOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax3SumOffset(void)
  {
    long Value;
    (this->get_Tax3SumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4NameOffset(void)
  {
    long Value;
    (this->get_Tax4NameOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4TurnOverOffset(void)
  {
    long Value;
    (this->get_Tax4TurnOverOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4RateOffset(void)
  {
    long Value;
    (this->get_Tax4RateOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_Tax4SumOffset(void)
  {
    long Value;
    (this->get_Tax4SumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalOffset(void)
  {
    long Value;
    (this->get_SubTotalOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_SubTotalSumOffset(void)
  {
    long Value;
    (this->get_SubTotalSumOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_SlipDocumentWidth(void)
  {
    long Value;
    (this->get_SlipDocumentWidth((long*)&Value));
    return Value;
  }

  long __fastcall get_SlipDocumentLength(void)
  {
    long Value;
    (this->get_SlipDocumentLength((long*)&Value));
    return Value;
  }

  long __fastcall get_PrintingAlignment(void)
  {
    long Value;
    (this->get_PrintingAlignment((long*)&Value));
    return Value;
  }

  BSTR __fastcall get_SlipStringIntervals(void)
  {
    BSTR Value = 0;
    (this->get_SlipStringIntervals((BSTR*)&Value));
    return Value;
  }

  long __fastcall get_SlipEqualStringIntervals(void)
  {
    long Value;
    (this->get_SlipEqualStringIntervals((long*)&Value));
    return Value;
  }

  long __fastcall get_KPKFont(void)
  {
    long Value;
    (this->get_KPKFont((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckOffset(void)
  {
    long Value;
    (this->get_DiscountOnCheckOffset((long*)&Value));
    return Value;
  }

  long __fastcall get_DiscountOnCheckSumOffset(void)
  {
    long Value;
    (this->get_DiscountOnCheckSumOffset((long*)&Value));
    return Value;
  }

  long __fastcall WideLoadLineData(void)
  {
    long Res;
    (this->WideLoadLineData((long*)&Res));
    return Res;
  }

  long __fastcall PrintTaxReport(void)
  {
    long Res;
    (this->PrintTaxReport((long*)&Res));
    return Res;
  }

  VARIANT_BOOL __fastcall get_QuantityPointPosition(void)
  {
    VARIANT_BOOL Value;
    (this->get_QuantityPointPosition((VARIANT_BOOL*)&Value));
    return Value;
  }

  unsigned long __fastcall get_FileVersionMS(void)
  {
    unsigned long Value;
    (this->get_FileVersionMS((unsigned long*)&Value));
    return Value;
  }

  unsigned long __fastcall get_FileVersionLS(void)
  {
    unsigned long Value;
    (this->get_FileVersionLS((unsigned long*)&Value));
    return Value;
  }

  long __fastcall GetLongSerialNumberAndLongRNM(void)
  {
    long Res;
    (this->GetLongSerialNumberAndLongRNM((long*)&Res));
    return Res;
  }

  long __fastcall SetLongSerialNumber(void)
  {
    long Res;
    (this->SetLongSerialNumber((long*)&Res));
    return Res;
  }

  long __fastcall FiscalizationWithLongRNM(void)
  {
    long Res;
    (this->FiscalizationWithLongRNM((long*)&Res));
    return Res;
  }

  VARIANT_BOOL __fastcall get_IsBatteryLow(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsBatteryLow((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsLastFMRecordCorrupted(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsLastFMRecordCorrupted((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsFMSessionOpen(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsFMSessionOpen((VARIANT_BOOL*)&Value));
    return Value;
  }

  VARIANT_BOOL __fastcall get_IsFM24HoursOver(void)
  {
    VARIANT_BOOL Value;
    (this->get_IsFM24HoursOver((VARIANT_BOOL*)&Value));
    return Value;
  }

  long __fastcall Connect2(void)
  {
    long Res;
    (this->Connect2((long*)&Res));
    return Res;
  }

  long __fastcall get_ECRModeStatus(void)
  {
    long Value;
    (this->get_ECRModeStatus((long*)&Value));
    return Value;
  }

  long __fastcall GetECRPrinterStatus(void)
  {
    long Res;
    (this->GetECRPrinterStatus((long*)&Res));
    return Res;
  }

  long __fastcall get_PrinterStatus(void)
  {
    long Value;
    (this->get_PrinterStatus((long*)&Value));
    return Value;
  }


//    BSTR            BarCode;// = {read = get_BarCode};
//    VARIANT_BOOL    BatteryCondition;// = {read = get_BatteryCondition};
//    double          BatteryVoltage;// = {read = get_BatteryVoltage};
//    long            BaudRate;// = {read = get_BaudRate, write = set_BaudRate};
//    CURRENCY        Change;// = {read = get_Change};
//    CURRENCY        CheckResult;// = {read = get_CheckResult, write = set_CheckResult};
//    long            CheckType;// = {read = get_CheckType, write = set_CheckType};
//    long            ComNumber;// = {read = get_ComNumber, write = set_ComNumber};
//    CURRENCY        ContentsOfCashRegister;// = {read = get_ContentsOfCashRegister};
//    long            ContentsOfOperationRegister;// = {read = get_ContentsOfOperationRegister};
//    long            CurrentDozeInMilliliters;// = {read = get_CurrentDozeInMilliliters, write = set_CurrentDozeInMilliliters};
//    CURRENCY        CurrentDozeInMoney;// = {read = get_CurrentDozeInMoney, write = set_CurrentDozeInMoney};
//    VARIANT_BOOL    CutType;// = {read = get_CutType, write = set_CutType};
//    BSTR            DataBlock;// = {read = get_DataBlock};
//    long            DataBlockNumber;// = {read = get_DataBlockNumber};
//    DATE            Date;// = {read = get_Date, write = set_Date};
//    long            Department;// = {read = get_Department, write = set_Department};
//    long            DeviceCode;// = {read = get_DeviceCode, write = set_DeviceCode};
//    BSTR            DeviceCodeDescription;// = {read = get_DeviceCodeDescription};
//    double          DiscountOnCheck;// = {read = get_DiscountOnCheck, write = set_DiscountOnCheck};
//    BSTR            DocumentName;// = {read = get_DocumentName};
//    long            DocumentNumber;// = {read = get_DocumentNumber, write = set_DocumentNumber};
//    long            DozeInMilliliters;// = {read = get_DozeInMilliliters, write = set_DozeInMilliliters};
//    CURRENCY        DozeInMoney;// = {read = get_DozeInMoney, write = set_DozeInMoney};
//    long            DrawerNumber;// = {read = get_DrawerNumber, write = set_DrawerNumber};
//    long            ECRAdvancedMode;// = {read = get_ECRAdvancedMode};
//    BSTR            ECRAdvancedModeDescription;// = {read = get_ECRAdvancedModeDescription};
//    long            ECRBuild;// = {read = get_ECRBuild};
//    long            ECRFlags;// = {read = get_ECRFlags};
//    BSTR            ECRInput;// = {read = get_ECRInput};
//    long            ECRMode;// = {read = get_ECRMode};
//    long            ECRMode8Status;// = {read = get_ECRMode8Status};
//    BSTR            ECRModeDescription;// = {read = get_ECRModeDescription};
//    BSTR            ECROutput;// = {read = get_ECROutput};
//    DATE            ECRSoftDate;// = {read = get_ECRSoftDate};
//    BSTR            ECRSoftVersion;// = {read = get_ECRSoftVersion};
//    VARIANT_BOOL    EKLZIsPresent;// = {read = get_EKLZIsPresent};
//    long            EmergencyStopCode;// = {read = get_EmergencyStopCode};
//    BSTR            EmergencyStopCodeDescription;// = {read = get_EmergencyStopCodeDescription};
//    BSTR            FieldName;// = {read = get_FieldName};
//    long            FieldNumber;// = {read = get_FieldNumber, write = set_FieldNumber};
//    long            FieldSize;// = {read = get_FieldSize};
//    VARIANT_BOOL    FieldType;// = {read = get_FieldType};
//    long            FirstLineNumber;// = {read = get_FirstLineNumber, write = set_FirstLineNumber};
//    DATE            FirstSessionDate;// = {read = get_FirstSessionDate, write = set_FirstSessionDate};
//    long            FirstSessionNumber;// = {read = get_FirstSessionNumber, write = set_FirstSessionNumber};
//    VARIANT_BOOL    FM1IsPresent;// = {read = get_FM1IsPresent};
//    VARIANT_BOOL    FM2IsPresent;// = {read = get_FM2IsPresent};
//    long            FMBuild;// = {read = get_FMBuild};
//    long            FMFlags;// = {read = get_FMFlags};
//    VARIANT_BOOL    FMOverflow;// = {read = get_FMOverflow};
//    DATE            FMSoftDate;// = {read = get_FMSoftDate};
//    BSTR            FMSoftVersion;// = {read = get_FMSoftVersion};
//    long            FreeRecordInFM;// = {read = get_FreeRecordInFM};
//    long            FreeRegistration;// = {read = get_FreeRegistration};
//    BSTR            INN;// = {read = get_INN};
//    VARIANT_BOOL    IsCheckClosed;// = {read = get_IsCheckClosed};
//    VARIANT_BOOL    IsCheckMadeOut;// = {read = get_IsCheckMadeOut};
//    VARIANT_BOOL    IsDrawerOpen;// = {read = get_IsDrawerOpen};
//    VARIANT_BOOL    JournalRibbonIsPresent;// = {read = get_JournalRibbonIsPresent};
//    VARIANT_BOOL    JournalRibbonLever;// = {read = get_JournalRibbonLever};
//    VARIANT_BOOL    JournalRibbonOpticalSensor;// = {read = get_JournalRibbonOpticalSensor};
//    long            KPKNumber;// = {read = get_KPKNumber, write = set_KPKNumber};
//    long            LastLineNumber;// = {read = get_LastLineNumber, write = set_LastLineNumber};
//    DATE            LastSessionDate;// = {read = get_LastSessionDate, write = set_LastSessionDate};
//    long            LastSessionNumber;// = {read = get_LastSessionNumber, write = set_LastSessionNumber};
//    BSTR            License;// = {read = get_License};
//    VARIANT_BOOL    LicenseIsPresent;// = {read = get_LicenseIsPresent};
//    VARIANT_BOOL    LidPositionSensor;// = {read = get_LidPositionSensor};
//    BSTR            LineData;// = {read = get_LineData};
//    long            LineNumber;// = {read = get_LineNumber, write = set_LineNumber};
//    long            LogicalNumber;// = {read = get_LogicalNumber};
//    long            MAXValueOfField;// = {read = get_MAXValueOfField};
//    long            MINValueOfField;// = {read = get_MINValueOfField};
//    VARIANT_BOOL    Motor;// = {read = get_Motor};
//    BSTR            NameCashReg;// = {read = get_NameCashReg};
//    BSTR            NameOperationReg;// = {read = get_NameOperationReg};
//    long            NewPasswordTI;// = {read = get_NewPasswordTI, write = set_NewPasswordTI};
//    long            OpenDocumentNumber;// = {read = get_OpenDocumentNumber};
//    long            OperatorNumber;// = {read = get_OperatorNumber};
//    long            Password;// = {read = get_Password, write = set_Password};
//    VARIANT_BOOL    Pistol;// = {read = get_Pistol};
//    VARIANT_BOOL    PointPosition;// = {read = get_PointPosition, write = set_PointPosition};
//    long            PortNumber;// = {read = get_PortNumber, write = set_PortNumber};
//    CURRENCY        Price;// = {read = get_Price, write = set_Price};
//    double          Quantity;// = {read = get_Quantity, write = set_Quantity};
//    long            QuantityOfOperations;// = {read = get_QuantityOfOperations};
//    VARIANT_BOOL    ReceiptRibbonIsPresent;// = {read = get_ReceiptRibbonIsPresent};
//    VARIANT_BOOL    ReceiptRibbonLever;// = {read = get_ReceiptRibbonLever};
//    VARIANT_BOOL    ReceiptRibbonOpticalSensor;// = {read = get_ReceiptRibbonOpticalSensor};
//    long            RegisterNumber;// = {read = get_RegisterNumber, write = set_RegisterNumber};
//    long            RegistrationNumber;// = {read = get_RegistrationNumber, write = set_RegistrationNumber};
//    VARIANT_BOOL    ReportType;// = {read = get_ReportType, write = set_ReportType};
//    long            ResultCode;// = {read = get_ResultCode};
//    BSTR            ResultCodeDescription;// = {read = get_ResultCodeDescription};
//    long            RKNumber;// = {read = get_RKNumber, write = set_RKNumber};
//    BSTR            RNM;// = {read = get_RNM};
//    VARIANT_BOOL    RoughValve;// = {read = get_RoughValve};
//    long            RowNumber;// = {read = get_RowNumber, write = set_RowNumber};
//    long            RunningPeriod;// = {read = get_RunningPeriod, write = set_RunningPeriod};
//    BSTR            SerialNumber;// = {read = get_SerialNumber};
//    long            SessionNumber;// = {read = get_SessionNumber, write = set_SessionNumber};
//    VARIANT_BOOL    SlipDocumentIsMoving;// = {read = get_SlipDocumentIsMoving};
//    VARIANT_BOOL    SlipDocumentIsPresent;// = {read = get_SlipDocumentIsPresent};
//    long            SlowingInMilliliters;// = {read = get_SlowingInMilliliters, write = set_SlowingInMilliliters};
//    VARIANT_BOOL    SlowingValve;// = {read = get_SlowingValve};
//    long            StatusRK;// = {read = get_StatusRK};
//    BSTR            StatusRKDescription;// = {read = get_StatusRKDescription};
//    BSTR            StringForPrinting;// = {read = get_StringForPrinting};
//    long            StringQuantity;// = {read = get_StringQuantity, write = set_StringQuantity};
//    CURRENCY        Summ1;// = {read = get_Summ1, write = set_Summ1};
//    CURRENCY        Summ2;// = {read = get_Summ2, write = set_Summ2};
//    CURRENCY        Summ3;// = {read = get_Summ3, write = set_Summ3};
//    CURRENCY        Summ4;// = {read = get_Summ4, write = set_Summ4};
//    BSTR            TableName;// = {read = get_TableName};
//    long            TableNumber;// = {read = get_TableNumber, write = set_TableNumber};
//    long            Tax1;// = {read = get_Tax1, write = set_Tax1};
//    long            Tax2;// = {read = get_Tax2, write = set_Tax2};
//    long            Tax3;// = {read = get_Tax3, write = set_Tax3};
//    long            Tax4;// = {read = get_Tax4, write = set_Tax4};
//    DATE            Time;// = {read = get_Time, write = set_Time};
//    long            Timeout;// = {read = get_Timeout, write = set_Timeout};
//    BSTR            TimeStr;// = {read = get_TimeStr};
//    BSTR            TransferBytes;// = {read = get_TransferBytes};
//    long            TRKNumber;// = {read = get_TRKNumber, write = set_TRKNumber};
//    VARIANT_BOOL    TypeOfLastEntryFM;// = {read = get_TypeOfLastEntryFM};
//    VARIANT_BOOL    TypeOfSumOfEntriesFM;// = {read = get_TypeOfSumOfEntriesFM, write = set_TypeOfSumOfEntriesFM};
//    long            UCodePage;// = {read = get_UCodePage};
//    BSTR            UDescription;// = {read = get_UDescription};
//    long            UMajorProtocolVersion;// = {read = get_UMajorProtocolVersion};
//    long            UMajorType;// = {read = get_UMajorType};
//    long            UMinorProtocolVersion;// = {read = get_UMinorProtocolVersion};
//    long            UMinorType;// = {read = get_UMinorType};
//    long            UModel;// = {read = get_UModel};
//    VARIANT_BOOL    UseJournalRibbon;// = {read = get_UseJournalRibbon, write = set_UseJournalRibbon};
//    VARIANT_BOOL    UseReceiptRibbon;// = {read = get_UseReceiptRibbon, write = set_UseReceiptRibbon};
//    VARIANT_BOOL    UseSlipDocument;// = {read = get_UseSlipDocument, write = set_UseSlipDocument};
//    long            ValueOfFieldInteger;// = {read = get_ValueOfFieldInteger, write = set_ValueOfFieldInteger};
//    BSTR            ValueOfFieldString;// = {read = get_ValueOfFieldString};
//    long            FontType;// = {read = get_FontType, write = set_FontType};
//    long            LDBaudrate;// = {read = get_LDBaudrate, write = set_LDBaudrate};
//    long            LDComNumber;// = {read = get_LDComNumber, write = set_LDComNumber};
//    long            LDCount;// = {read = get_LDCount};
//    long            LDIndex;// = {read = get_LDIndex, write = set_LDIndex};
//    BSTR            LDName;// = {read = get_LDName};
//    long            LDNumber;// = {read = get_LDNumber, write = set_LDNumber};
//    long            WaitPrintingTime;// = {read = get_WaitPrintingTime};
//    VARIANT_BOOL    IsPrinterLeftSensorFailure;// = {read = get_IsPrinterLeftSensorFailure};
//    VARIANT_BOOL    IsPrinterRightSensorFailure;// = {read = get_IsPrinterRightSensorFailure};
//    BSTR            EKLZNumber;// = {read = get_EKLZNumber};
//    CURRENCY        LastKPKDocumentResult;// = {read = get_LastKPKDocumentResult};
//    DATE            LastKPKDate;// = {read = get_LastKPKDate};
//    DATE            LastKPKTime;// = {read = get_LastKPKTime};
//    long            LastKPKNumber;// = {read = get_LastKPKNumber};
//    long            EKLZFlags;// = {read = get_EKLZFlags};
//    long            TestNumber;// = {read = get_TestNumber, write = set_TestNumber};
//    BSTR            EKLZVersion;// = {read = get_EKLZVersion};
//    BSTR            EKLZData;// = {read = get_EKLZData};
//    long            EKLZResultCode;// = {read = get_EKLZResultCode, write = set_EKLZResultCode};
//    long            FMResultCode;// = {read = get_FMResultCode};
//    double          PowerSourceVoltage;// = {read = get_PowerSourceVoltage};
//    VARIANT_BOOL    IsEKLZOverflow;// = {read = get_IsEKLZOverflow};
//    long            CopyType;// = {read = get_CopyType, write = set_CopyType};
//    long            NumberOfCopies;// = {read = get_NumberOfCopies, write = set_NumberOfCopies};
//    long            CopyOffset1;// = {read = get_CopyOffset1, write = set_CopyOffset1};
//    long            CopyOffset2;// = {read = get_CopyOffset2, write = set_CopyOffset2};
//    long            CopyOffset3;// = {read = get_CopyOffset3, write = set_CopyOffset3};
//    long            CopyOffset4;// = {read = get_CopyOffset4, write = set_CopyOffset4};
//    long            CopyOffset5;// = {read = get_CopyOffset5, write = set_CopyOffset5};
//    long            ClicheFont;// = {read = get_ClicheFont, write = set_ClicheFont};
//    long            HeaderFont;// = {read = get_HeaderFont, write = set_HeaderFont};
//    long            EKLZFont;// = {read = get_EKLZFont, write = set_EKLZFont};
//    long            ClicheStringNumber;// = {read = get_ClicheStringNumber, write = set_ClicheStringNumber};
//    long            HeaderStringNumber;// = {read = get_HeaderStringNumber, write = set_HeaderStringNumber};
//    long            EKLZStringNumber;// = {read = get_EKLZStringNumber, write = set_EKLZStringNumber};
//    long            FMStringNumber;// = {read = get_FMStringNumber, write = set_FMStringNumber};
//    long            ClicheOffset;// = {read = get_ClicheOffset, write = set_ClicheOffset};
//    long            HeaderOffset;// = {read = get_HeaderOffset, write = set_HeaderOffset};
//    long            EKLZOffset;// = {read = get_EKLZOffset, write = set_EKLZOffset};
//    long            KPKOffset;// = {read = get_KPKOffset, write = set_KPKOffset};
//    long            FMOffset;// = {read = get_FMOffset, write = set_FMOffset};
//    long            OperationBlockFirstString;// = {read = get_OperationBlockFirstString, write = set_OperationBlockFirstString};
//    long            QuantityFormat;// = {read = get_QuantityFormat, write = set_QuantityFormat};
//    long            StringQuantityInOperation;// = {read = get_StringQuantityInOperation, write = set_StringQuantityInOperation};
//    long            TextStringNumber;// = {read = get_TextStringNumber, write = set_TextStringNumber};
//    long            QuantityStringNumber;// = {read = get_QuantityStringNumber, write = set_QuantityStringNumber};
//    long            SummStringNumber;// = {read = get_SummStringNumber, write = set_SummStringNumber};
//    long            DepartmentStringNumber;// = {read = get_DepartmentStringNumber, write = set_DepartmentStringNumber};
//    long            TextFont;// = {read = get_TextFont, write = set_TextFont};
//    long            QuantityFont;// = {read = get_QuantityFont, write = set_QuantityFont};
//    long            MultiplicationFont;// = {read = get_MultiplicationFont, write = set_MultiplicationFont};
//    long            PriceFont;// = {read = get_PriceFont, write = set_PriceFont};
//    long            SummFont;// = {read = get_SummFont, write = set_SummFont};
//    long            DepartmentFont;// = {read = get_DepartmentFont, write = set_DepartmentFont};
//    long            TextSymbolNumber;// = {read = get_TextSymbolNumber, write = set_TextSymbolNumber};
//    long            QuantitySymbolNumber;// = {read = get_QuantitySymbolNumber, write = set_QuantitySymbolNumber};
//    long            PriceSymbolNumber;// = {read = get_PriceSymbolNumber, write = set_PriceSymbolNumber};
//    long            SummSymbolNumber;// = {read = get_SummSymbolNumber, write = set_SummSymbolNumber};
//    long            DepartmentSymbolNumber;// = {read = get_DepartmentSymbolNumber, write = set_DepartmentSymbolNumber};
//    long            TextOffset;// = {read = get_TextOffset, write = set_TextOffset};
//    long            QuantityOffset;// = {read = get_QuantityOffset, write = set_QuantityOffset};
//    long            SummOffset;// = {read = get_SummOffset, write = set_SummOffset};
//    long            DepartmentOffset;// = {read = get_DepartmentOffset, write = set_DepartmentOffset};
//    VARIANT_BOOL    IsClearUnfiscalInfo;// = {read = get_IsClearUnfiscalInfo, write = set_IsClearUnfiscalInfo};
//    long            InfoType;// = {read = get_InfoType, write = set_InfoType};
//    long            StringNumber;// = {read = get_StringNumber, write = set_StringNumber};
//    long            EjectDirection;// = {read = get_EjectDirection, write = set_EjectDirection};
//    long            OperationNameStringNumber;// = {read = get_OperationNameStringNumber, write = set_OperationNameStringNumber};
//    long            OperationNameFont;// = {read = get_OperationNameFont, write = set_OperationNameFont};
//    long            OperationNameOffset;// = {read = get_OperationNameOffset, write = set_OperationNameOffset};
//    long            TotalStringNumber;// = {read = get_TotalStringNumber, write = set_TotalStringNumber};
//    long            Summ1StringNumber;// = {read = get_Summ1StringNumber, write = set_Summ1StringNumber};
//    long            Summ2StringNumber;// = {read = get_Summ2StringNumber, write = set_Summ2StringNumber};
//    long            Summ3StringNumber;// = {read = get_Summ3StringNumber, write = set_Summ3StringNumber};
//    long            Summ4StringNumber;// = {read = get_Summ4StringNumber, write = set_Summ4StringNumber};
//    long            ChangeStringNumber;// = {read = get_ChangeStringNumber, write = set_ChangeStringNumber};
//    long            Tax1TurnOverStringNumber;// = {read = get_Tax1TurnOverStringNumber, write = set_Tax1TurnOverStringNumber};
//    long            Tax2TurnOverStringNumber;// = {read = get_Tax2TurnOverStringNumber, write = set_Tax2TurnOverStringNumber};
//    long            Tax3TurnOverStringNumber;// = {read = get_Tax3TurnOverStringNumber, write = set_Tax3TurnOverStringNumber};
//    long            Tax4TurnOverStringNumber;// = {read = get_Tax4TurnOverStringNumber, write = set_Tax4TurnOverStringNumber};
//    long            Tax1SumStringNumber;// = {read = get_Tax1SumStringNumber, write = set_Tax1SumStringNumber};
//    long            Tax2SumStringNumber;// = {read = get_Tax2SumStringNumber, write = set_Tax2SumStringNumber};
//    long            Tax3SumStringNumber;// = {read = get_Tax3SumStringNumber, write = set_Tax3SumStringNumber};
//    long            Tax4SumStringNumber;// = {read = get_Tax4SumStringNumber, write = set_Tax4SumStringNumber};
//    long            SubTotalStringNumber;// = {read = get_SubTotalStringNumber, write = set_SubTotalStringNumber};
//    long            DiscountOnCheckStringNumber;// = {read = get_DiscountOnCheckStringNumber, write = set_DiscountOnCheckStringNumber};
//    long            TotalFont;// = {read = get_TotalFont, write = set_TotalFont};
//    long            TotalSumFont;// = {read = get_TotalSumFont, write = set_TotalSumFont};
//    long            Summ1Font;// = {read = get_Summ1Font, write = set_Summ1Font};
//    long            Summ1NameFont;// = {read = get_Summ1NameFont, write = set_Summ1NameFont};
//    long            Summ2NameFont;// = {read = get_Summ2NameFont, write = set_Summ2NameFont};
//    long            Summ3NameFont;// = {read = get_Summ3NameFont, write = set_Summ3NameFont};
//    long            Summ4NameFont;// = {read = get_Summ4NameFont, write = set_Summ4NameFont};
//    long            Summ2Font;// = {read = get_Summ2Font, write = set_Summ2Font};
//    long            Summ3Font;// = {read = get_Summ3Font, write = set_Summ3Font};
//    long            Summ4Font;// = {read = get_Summ4Font, write = set_Summ4Font};
//    long            ChangeFont;// = {read = get_ChangeFont, write = set_ChangeFont};
//    long            ChangeSumFont;// = {read = get_ChangeSumFont, write = set_ChangeSumFont};
//    long            Tax1NameFont;// = {read = get_Tax1NameFont, write = set_Tax1NameFont};
//    long            Tax2NameFont;// = {read = get_Tax2NameFont, write = set_Tax2NameFont};
//    long            Tax3NameFont;// = {read = get_Tax3NameFont, write = set_Tax3NameFont};
//    long            Tax4NameFont;// = {read = get_Tax4NameFont, write = set_Tax4NameFont};
//    long            Tax1TurnOverFont;// = {read = get_Tax1TurnOverFont, write = set_Tax1TurnOverFont};
//    long            Tax2TurnOverFont;// = {read = get_Tax2TurnOverFont, write = set_Tax2TurnOverFont};
//    long            Tax3TurnOverFont;// = {read = get_Tax3TurnOverFont, write = set_Tax3TurnOverFont};
//    long            Tax4TurnOverFont;// = {read = get_Tax4TurnOverFont, write = set_Tax4TurnOverFont};
//    long            Tax1RateFont;// = {read = get_Tax1RateFont, write = set_Tax1RateFont};
//    long            Tax2RateFont;// = {read = get_Tax2RateFont, write = set_Tax2RateFont};
//    long            Tax3RateFont;// = {read = get_Tax3RateFont, write = set_Tax3RateFont};
//    long            Tax4RateFont;// = {read = get_Tax4RateFont, write = set_Tax4RateFont};
//    long            Tax1SumFont;// = {read = get_Tax1SumFont, write = set_Tax1SumFont};
//    long            Tax2SumFont;// = {read = get_Tax2SumFont, write = set_Tax2SumFont};
//    long            Tax3SumFont;// = {read = get_Tax3SumFont, write = set_Tax3SumFont};
//    long            Tax4SumFont;// = {read = get_Tax4SumFont, write = set_Tax4SumFont};
//    long            SubTotalFont;// = {read = get_SubTotalFont, write = set_SubTotalFont};
//    long            SubTotalSumFont;// = {read = get_SubTotalSumFont, write = set_SubTotalSumFont};
//    long            DiscountOnCheckFont;// = {read = get_DiscountOnCheckFont, write = set_DiscountOnCheckFont};
//    long            DiscountOnCheckSumFont;// = {read = get_DiscountOnCheckSumFont, write = set_DiscountOnCheckSumFont};
//    long            TotalSymbolNumber;// = {read = get_TotalSymbolNumber, write = set_TotalSymbolNumber};
//    long            Summ1SymbolNumber;// = {read = get_Summ1SymbolNumber, write = set_Summ1SymbolNumber};
//    long            Summ2SymbolNumber;// = {read = get_Summ2SymbolNumber, write = set_Summ2SymbolNumber};
//    long            Summ3SymbolNumber;// = {read = get_Summ3SymbolNumber, write = set_Summ3SymbolNumber};
//    long            Summ4SymbolNumber;// = {read = get_Summ4SymbolNumber, write = set_Summ4SymbolNumber};
//    long            ChangeSymbolNumber;// = {read = get_ChangeSymbolNumber, write = set_ChangeSymbolNumber};
//    long            Tax1NameSymbolNumber;// = {read = get_Tax1NameSymbolNumber, write = set_Tax1NameSymbolNumber};
//    long            Tax1TurnOverSymbolNumber;// = {read = get_Tax1TurnOverSymbolNumber, write = set_Tax1TurnOverSymbolNumber};
//    long            Tax1RateSymbolNumber;// = {read = get_Tax1RateSymbolNumber, write = set_Tax1RateSymbolNumber};
//    long            Tax1SumSymbolNumber;// = {read = get_Tax1SumSymbolNumber, write = set_Tax1SumSymbolNumber};
//    long            Tax2NameSymbolNumber;// = {read = get_Tax2NameSymbolNumber, write = set_Tax2NameSymbolNumber};
//    long            Tax2TurnOverSymbolNumber;// = {read = get_Tax2TurnOverSymbolNumber, write = set_Tax2TurnOverSymbolNumber};
//    long            Tax2RateSymbolNumber;// = {read = get_Tax2RateSymbolNumber, write = set_Tax2RateSymbolNumber};
//    long            Tax2SumSymbolNumber;// = {read = get_Tax2SumSymbolNumber, write = set_Tax2SumSymbolNumber};
//    long            Tax3NameSymbolNumber;// = {read = get_Tax3NameSymbolNumber, write = set_Tax3NameSymbolNumber};
//    long            Tax3TurnOverSymbolNumber;// = {read = get_Tax3TurnOverSymbolNumber, write = set_Tax3TurnOverSymbolNumber};
//    long            Tax3RateSymbolNumber;// = {read = get_Tax3RateSymbolNumber, write = set_Tax3RateSymbolNumber};
//    long            Tax3SumSymbolNumber;// = {read = get_Tax3SumSymbolNumber, write = set_Tax3SumSymbolNumber};
//    long            Tax4NameSymbolNumber;// = {read = get_Tax4NameSymbolNumber, write = set_Tax4NameSymbolNumber};
//    long            Tax4TurnOverSymbolNumber;// = {read = get_Tax4TurnOverSymbolNumber, write = set_Tax4TurnOverSymbolNumber};
//    long            Tax4RateSymbolNumber;// = {read = get_Tax4RateSymbolNumber, write = set_Tax4RateSymbolNumber};
//    long            Tax4SumSymbolNumber;// = {read = get_Tax4SumSymbolNumber, write = set_Tax4SumSymbolNumber};
//    long            SubTotalSymbolNumber;// = {read = get_SubTotalSymbolNumber, write = set_SubTotalSymbolNumber};
//    long            DiscountOnCheckSymbolNumber;// = {read = get_DiscountOnCheckSymbolNumber, write = set_DiscountOnCheckSymbolNumber};
//    long            DiscountOnCheckSumSymbolNumber;// = {read = get_DiscountOnCheckSumSymbolNumber, write = set_DiscountOnCheckSumSymbolNumber};
//    long            TotalOffset;// = {read = get_TotalOffset, write = set_TotalOffset};
//    long            Summ1Offset;// = {read = get_Summ1Offset, write = set_Summ1Offset};
//    long            TotalSumOffset;// = {read = get_TotalSumOffset, write = set_TotalSumOffset};
//    long            Summ1NameOffset;// = {read = get_Summ1NameOffset, write = set_Summ1NameOffset};
//    long            Summ2Offset;// = {read = get_Summ2Offset, write = set_Summ2Offset};
//    long            Summ2NameOffset;// = {read = get_Summ2NameOffset, write = set_Summ2NameOffset};
//    long            Summ3Offset;// = {read = get_Summ3Offset, write = set_Summ3Offset};
//    long            Summ3NameOffset;// = {read = get_Summ3NameOffset, write = set_Summ3NameOffset};
//    long            Summ4Offset;// = {read = get_Summ4Offset, write = set_Summ4Offset};
//    long            Summ4NameOffset;// = {read = get_Summ4NameOffset, write = set_Summ4NameOffset};
//    long            ChangeOffset;// = {read = get_ChangeOffset, write = set_ChangeOffset};
//    long            ChangeSumOffset;// = {read = get_ChangeSumOffset, write = set_ChangeSumOffset};
//    long            Tax1NameOffset;// = {read = get_Tax1NameOffset, write = set_Tax1NameOffset};
//    long            Tax1TurnOverOffset;// = {read = get_Tax1TurnOverOffset, write = set_Tax1TurnOverOffset};
//    long            Tax1RateOffset;// = {read = get_Tax1RateOffset, write = set_Tax1RateOffset};
//    long            Tax1SumOffset;// = {read = get_Tax1SumOffset, write = set_Tax1SumOffset};
//    long            Tax2NameOffset;// = {read = get_Tax2NameOffset, write = set_Tax2NameOffset};
//    long            Tax2TurnOverOffset;// = {read = get_Tax2TurnOverOffset, write = set_Tax2TurnOverOffset};
//    long            Tax2RateOffset;// = {read = get_Tax2RateOffset, write = set_Tax2RateOffset};
//    long            Tax2SumOffset;// = {read = get_Tax2SumOffset, write = set_Tax2SumOffset};
//    long            Tax3NameOffset;// = {read = get_Tax3NameOffset, write = set_Tax3NameOffset};
//    long            Tax3TurnOverOffset;// = {read = get_Tax3TurnOverOffset, write = set_Tax3TurnOverOffset};
//    long            Tax3RateOffset;// = {read = get_Tax3RateOffset, write = set_Tax3RateOffset};
//    long            Tax3SumOffset;// = {read = get_Tax3SumOffset, write = set_Tax3SumOffset};
//    long            Tax4NameOffset;// = {read = get_Tax4NameOffset, write = set_Tax4NameOffset};
//    long            Tax4TurnOverOffset;// = {read = get_Tax4TurnOverOffset, write = set_Tax4TurnOverOffset};
//    long            Tax4RateOffset;// = {read = get_Tax4RateOffset, write = set_Tax4RateOffset};
//    long            Tax4SumOffset;// = {read = get_Tax4SumOffset, write = set_Tax4SumOffset};
//    long            SubTotalOffset;// = {read = get_SubTotalOffset, write = set_SubTotalOffset};
//    long            SubTotalSumOffset;// = {read = get_SubTotalSumOffset, write = set_SubTotalSumOffset};
//    long            SlipDocumentWidth;// = {read = get_SlipDocumentWidth, write = set_SlipDocumentWidth};
//    long            SlipDocumentLength;// = {read = get_SlipDocumentLength, write = set_SlipDocumentLength};
//    long            PrintingAlignment;// = {read = get_PrintingAlignment, write = set_PrintingAlignment};
//    BSTR            SlipStringIntervals;// = {read = get_SlipStringIntervals};
//    long            SlipEqualStringIntervals;// = {read = get_SlipEqualStringIntervals, write = set_SlipEqualStringIntervals};
//    long            KPKFont;// = {read = get_KPKFont, write = set_KPKFont};
//    long            DiscountOnCheckOffset;// = {read = get_DiscountOnCheckOffset, write = set_DiscountOnCheckOffset};
//    long            DiscountOnCheckSumOffset;// = {read = get_DiscountOnCheckSumOffset, write = set_DiscountOnCheckSumOffset};
//    VARIANT_BOOL    QuantityPointPosition;// = {read = get_QuantityPointPosition};
//    unsigned long   FileVersionMS;// = {read = get_FileVersionMS};
//    unsigned long   FileVersionLS;// = {read = get_FileVersionLS};
//    VARIANT_BOOL    IsBatteryLow;// = {read = get_IsBatteryLow};
//    VARIANT_BOOL    IsLastFMRecordCorrupted;// = {read = get_IsLastFMRecordCorrupted};
//    VARIANT_BOOL    IsFMSessionOpen;// = {read = get_IsFMSessionOpen};
//    VARIANT_BOOL    IsFM24HoursOver;// = {read = get_IsFM24HoursOver};
//    long            ECRModeStatus;// = {read = get_ECRModeStatus};
//    long            PrinterStatus;// = {read = get_PrinterStatus};

};

#endif // DRVFR_H

