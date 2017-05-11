// Файл SP101FRKLib собран вручную.
#ifndef SP101FRKLIB_H
#define SP101FRKLIB_H

#include <windows.h>

extern "C" const __declspec(selectany) GUID LIBID_SP101FRKLib = {0x685E9290, 0x6CB3, 0x4E73,{0xA7, 0x4A, 0x3D, 0x16, 0x10, 0x51, 0xD8, 0x93} };//: TGUID = '{685E9290-6CB3-4E73-A74A-3D161051D893}';

extern "C" const __declspec(selectany) GUID IID_ISP101FRObject = {0x1B15AF2D, 0xCD27, 0x4863,{0xAE, 0xC8, 0x7C, 0xF3, 0xD0, 0x78, 0x23, 0x3A} };//: TGUID = '{1B15AF2D-CD27-4863-AEC8-7CF3D078233A}';
extern "C" const __declspec(selectany) GUID CLASS_SP101FRObject = {0xC01711A3, 0x2958, 0x4074,{0x81, 0x2F, 0x26, 0xCB, 0x00, 0xA6, 0x55, 0xE3} };//: TGUID = '{C01711A3-2958-4074-812F-26CB00A655E3}';
extern "C" const __declspec(selectany) GUID IID_ISP101FRObject_1C = {0x3086A6BE, 0x0386, 0x4295,{0xB9, 0x1A, 0x43, 0x4E, 0x34, 0xB3, 0x30, 0x88} };//: TGUID = '{3086A6BE-0386-4295-B91A-434E34B33088}';
extern "C" const __declspec(selectany) GUID CLASS_SP101FRObject_1C = {0x097C92B6, 0x9F0F, 0x4774,{0x8A, 0xCD, 0xEF, 0x1D, 0xBB, 0xB0, 0xA4, 0x60} };//: TGUID = '{097C92B6-9F0F-4774-8ACD-EF1DBBB0A460}';


interface SPOleFR  : public IDispatch
{
public:
    virtual HRESULT _stdcall Connect( /*[in]*/ unsigned char PortNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Disconnect( void ) = 0;
    virtual HRESULT _stdcall GetObjectState(/*[out]*/ VARIANT_BOOL * WasConnected ) = 0;
    virtual HRESULT _stdcall GetErrorMesageRU( /*[in]*/ short ErrorCode, /*[out]*/ BSTR * ErrorDescription ) = 0;
    virtual HRESULT _stdcall GetStatus(/*[out]*/ short * FatalStatus, /*[out]*/ short * Status, /*[in, out]*/ short * ReceiptStatus, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CheckActive(/*[out]*/ VARIANT_BOOL * IsActive, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Init( /*[in]*/ DATE CheckDateTime, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Register( /*[in]*/ BSTR OldPassword,  /*[in]*/ BSTR RegNumber,  /*[in]*/ BSTR INN,  /*[in]*/ BSTR NewPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ActivateEJJ(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseEEJArchive(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenReceipt( /*[in]*/ short ReceiptType,  /*[in]*/ short SectionNumber,  /*[in]*/ BSTR OperatorName,  /*[in]*/ unsigned long ReceiptNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintString( /*[in]*/ BSTR Buffer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall BreakReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddArticle( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ CURRENCY ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall StornoArticle( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ CURRENCY ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall DiscountPercent( /*[in]*/ BSTR DiscountName,  /*[in]*/ CURRENCY Percent, /*[out]*/ CURRENCY * ArticleTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall DiscountTotal( /*[in]*/ BSTR DiscountName,  /*[in]*/ CURRENCY Total, /*[out]*/ CURRENCY * ArticleTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ExtraPercent( /*[in]*/ BSTR ExtraName,  /*[in]*/ CURRENCY Percent, /*[out]*/ CURRENCY * ArticleTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ExtraTotal( /*[in]*/ BSTR ExtraName,  /*[in]*/ CURRENCY Total, /*[out]*/ CURRENCY * ArticleTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SubTotal(/*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Payment( /*[in]*/ short PaymentType,  /*[in]*/ CURRENCY PaymentTotal, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CashInOut( /*[in]*/ BSTR BanknoteName,  /*[in]*/ CURRENCY Total, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall XReport( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZReport( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall FiscShiftReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ short FirstShift,  /*[in]*/ short LastShift,  /*[in]*/ BSTR FiscPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall FiscDateReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ DATE FirstDate,  /*[in]*/ DATE LastDate,  /*[in]*/ BSTR FiscPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJJournal( /*[in]*/ short ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceipt( /*[in]*/ unsigned long KPKNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJShiftReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ short FirstShift,  /*[in]*/ short LastShift, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJDateReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ DATE FirstDate,  /*[in]*/ DATE LastDate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJActivationReport(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport( /*[in]*/ short ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReadConfig( /*[in]*/ short i,  /*[in]*/ short j, /*[out]*/ BSTR * Param, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteConfig( /*[in]*/ short i,  /*[in]*/ short j,  /*[in]*/ BSTR Param, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetDate( /*[in]*/ DATE NewDateTime, /*[out, retval]*/ short * Resulr ) = 0;
    virtual HRESULT _stdcall GetEEJStatus(/*[out]*/ short * aStatus, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentShift(/*[out]*/ short * ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetLastRegShift(/*[out]*/ short * ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRegNumber(/*[out]*/ BSTR * RegNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenCashDrawer(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Install( /*[in]*/ DATE NewDateTime,  /*[in]*/ BSTR SerialNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReadMemBlock( /*[in]*/ short DataAreaType,  /*[in]*/ short ByteOffset,  /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall LoadLogo( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall LoadLogoFromFile( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CancelReport(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddBarcode( /*[in]*/ short BarcodeType,  /*[in]*/ short BarcodeAsDigit,  /*[in]*/ short BarcodeHeight,  /*[in]*/ short BarcodeWidth,  /*[in]*/ BSTR BarcodeData, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CutAndPrint(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJEmergencyShiftClose(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJShowClientDisplayText( /*[in]*/ BSTR Row1,  /*[in]*/ BSTR Row2, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ClearLogo(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Init2(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Install2( /*[in]*/ BSTR SerialNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentDocNum(/*[out]*/ short * DocNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReceiptNum(/*[out]*/ short * ReceiptNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReportNum(/*[out]*/ short * ReportNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCashInDrawer(/*[out]*/ CURRENCY * CashSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSumByCashtype( /*[in]*/ short CashType, /*[out]*/ CURRENCY * CashSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookSumByCashtype( /*[in]*/ short CashType, /*[out]*/ CURRENCY * RebookSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintStringEx( /*[in]*/ BSTR Buffer,  /*[in]*/ VARIANT_BOOL DoubleHeight,  /*[in]*/ VARIANT_BOOL DoubleWidth,  /*[in]*/ VARIANT_BOOL Underlined, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentDateTime(/*[out]*/ short * day, /*[out]*/ short * month, /*[out]*/ short * year, /*[out]*/ short * hour, /*[out]*/ short * minute, /*[out]*/ short * second, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddArticleEx( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ double ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department,  /*[in]*/ short TaxNumber, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReprintReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetTaxAmount( /*[in]*/ short TaxNumber,  /*[in]*/ CURRENCY Amount, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetINN(/*[out]*/ BSTR * INN, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetFiscNumber(/*[out]*/ BSTR * FiscNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetEEJNumber(/*[out]*/ BSTR * EEJNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall StornoArticleEx( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ double ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department,  /*[in]*/ short TaxNumber, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ConnectEx( /*[in]*/ unsigned char PortNumber,  /*[in]*/ unsigned long BaudRate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintStringExWide( /*[in]*/ BSTR Buffer,  /*[in]*/ VARIANT_BOOL DoubleHeight,  /*[in]*/ VARIANT_BOOL DoubleWidth,  /*[in]*/ VARIANT_BOOL Underlined,  /*[in]*/ VARIANT_BOOL Compact, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenReceiptSigned( /*[in]*/ short ReceiptType,  /*[in]*/ short SectionNumber,  /*[in]*/ BSTR OperatorName,  /*[in]*/ long ReceiptNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceiptSigned( /*[in]*/ long KPKNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ConnectExSigned( /*[in]*/ long PortNumber,  /*[in]*/ long BaudRate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSumForAllPayments(/*[out]*/ CURRENCY * CashSum0, /*[out]*/ CURRENCY * CashSum1, /*[out]*/ CURRENCY * CashSum2, /*[out]*/ CURRENCY * CashSum3, /*[out]*/ CURRENCY * CashSum4, /*[out]*/ CURRENCY * CashSum5, /*[out]*/ CURRENCY * CashSum6, /*[out]*/ CURRENCY * CashSum7, /*[out]*/ CURRENCY * CashSum8, /*[out]*/ CURRENCY * CashSum9, /*[out]*/ CURRENCY * CashSum10, /*[out]*/ CURRENCY * CashSum11, /*[out]*/ CURRENCY * CashSum12, /*[out]*/ CURRENCY * CashSum13, /*[out]*/ CURRENCY * CashSum14, /*[out]*/ CURRENCY * CashSum15, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookSumForAllPayments(/*[out]*/ CURRENCY * CashSum0, /*[out]*/ CURRENCY * CashSum1, /*[out]*/ CURRENCY * CashSum2, /*[out]*/ CURRENCY * CashSum3, /*[out]*/ CURRENCY * CashSum4, /*[out]*/ CURRENCY * CashSum5, /*[out]*/ CURRENCY * CashSum6, /*[out]*/ CURRENCY * CashSum7, /*[out]*/ CURRENCY * CashSum8, /*[out]*/ CURRENCY * CashSum9, /*[out]*/ CURRENCY * CashSum10, /*[out]*/ CURRENCY * CashSum11, /*[out]*/ CURRENCY * CashSum12, /*[out]*/ CURRENCY * CashSum13, /*[out]*/ CURRENCY * CashSum14, /*[out]*/ CURRENCY * CashSum15, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetTriesNumber( /*[in]*/ short TriesNumber ) = 0;
    virtual HRESULT _stdcall PaymentWithComment( /*[in]*/ short PaymentType,  /*[in]*/ CURRENCY PaymentTotal,  /*[in]*/ BSTR Comment, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseReceiptEx( /*[in]*/ VARIANT_BOOL DoCut, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetBaudrate( /*[in]*/ long BaudRate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall StoreReceipt( /*[in]*/ BSTR Text, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReceiptSums(/*[out]*/ CURRENCY * ReceiptSum, /*[out]*/ CURRENCY * ReceiptDiscountSum, /*[out]*/ CURRENCY * ReceiptExtraSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceiptCounters(/*[out]*/ long * SellReceipts, /*[out]*/ long * RebookReceipts, /*[out]*/ long * CancelledReceipts, /*[out]*/ long * StoredReceipts, /*[out]*/ long * DepositReceipts, /*[out]*/ long * TakingReceipts, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceiptsSums(/*[out]*/ CURRENCY * CancelledReceiptsSum, /*[out]*/ CURRENCY * StoredReceiptsSum, /*[out]*/ CURRENCY * DepositReceiptsSum, /*[out]*/ CURRENCY * TakingReceiptsSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetDiscountAndExtraSums(/*[out]*/ CURRENCY * SellDiscountSum, /*[out]*/ CURRENCY * SellExtraSum, /*[out]*/ CURRENCY * RebookDiscountSum, /*[out]*/ CURRENCY * RebookExtraSum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSellTaxSums(/*[out]*/ CURRENCY * Sum0, /*[out]*/ CURRENCY * Sum1, /*[out]*/ CURRENCY * Sum2, /*[out]*/ CURRENCY * Sum3, /*[out]*/ CURRENCY * Sum4, /*[out]*/ CURRENCY * Sum5, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookTaxSums(/*[out]*/ CURRENCY * Sum0, /*[out]*/ CURRENCY * Sum1, /*[out]*/ CURRENCY * Sum2, /*[out]*/ CURRENCY * Sum3, /*[out]*/ CURRENCY * Sum4, /*[out]*/ CURRENCY * Sum5, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSellPaymentsCount(/*[out]*/ long * Count0, /*[out]*/ long * Count1, /*[out]*/ long * Count2, /*[out]*/ long * Count3, /*[out]*/ long * Count4, /*[out]*/ long * Count5, /*[out]*/ long * Count6, /*[out]*/ long * Count7, /*[out]*/ long * Count8, /*[out]*/ long * Count9, /*[out]*/ long * Count10, /*[out]*/ long * Count11, /*[out]*/ long * Count12, /*[out]*/ long * Count13, /*[out]*/ long * Count14, /*[out]*/ long * Count15, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookPaymentsCount(/*[out]*/ long * Count0, /*[out]*/ long * Count1, /*[out]*/ long * Count2, /*[out]*/ long * Count3, /*[out]*/ long * Count4, /*[out]*/ long * Count5, /*[out]*/ long * Count6, /*[out]*/ long * Count7, /*[out]*/ long * Count8, /*[out]*/ long * Count9, /*[out]*/ long * Count10, /*[out]*/ long * Count11, /*[out]*/ long * Count12, /*[out]*/ long * Count13, /*[out]*/ long * Count14, /*[out]*/ long * Count15, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetDrawerOpened(/*[out]*/ VARIANT_BOOL * IsOpened, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetDateTime(/*[out]*/ short * day, /*[out]*/ short * month, /*[out]*/ short * year, /*[out]*/ short * hour, /*[out]*/ short * minute, /*[out]*/ short * second, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetVersion(/*[out]*/ short * Version, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetAccumulatedTotal(/*[out]*/ CURRENCY * AccumulatedTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteHeader( /*[in]*/ short iRow,  /*[in]*/ BSTR sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteFooter( /*[in]*/ short iRow,  /*[in]*/ BSTR sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseNonFiscalEx(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport_Text_Close(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport_Text_Next(/*[out]*/ BSTR * sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJJournal_Text( /*[in]*/ short wShift, /*[out]*/ BSTR * sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJShiftReport_Text( /*[in]*/ VARIANT_BOOL bIsFull,  /*[in]*/ short wFirstShift,  /*[in]*/ short wLastShift, /*[out]*/ BSTR * sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJDateReport_Text( /*[in]*/ VARIANT_BOOL bIsFull,  /*[in]*/ DATE dFirstDate,  /*[in]*/ DATE dLastDate, /*[out]*/ BSTR * sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJGetReceipt_Text( /*[in]*/ long dwKPKNumber, /*[out]*/ BSTR * sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetAccumulatedTotalEx(/*[out]*/ CURRENCY * AccumulatedTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReceiptNumEx(/*[out]*/ long * ReceiptNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentDocNumEx(/*[out]*/ long * DocNum, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphics( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsFromFile( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddQRCode( /*[in]*/ short QRCodeScale,  /*[in]*/ short QRCodeErrCorrectionLevel,  /*[in]*/ BSTR QRCodeData, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsEx( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsFromFileEx( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall RegisterSKNO( /*[in]*/ BSTR OldPassword,  /*[in]*/ BSTR RegNumber,  /*[in]*/ BSTR UNP,  /*[in]*/ BSTR SKNO,  /*[in]*/ BSTR NewPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddArticleEx3( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ double ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department,  /*[in]*/ short TaxNumber,  /*[in]*/ BSTR EAN, /*[out]*/ CURRENCY * ReceiptTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSKNOStatus(/*[out]*/ short * SKNOStatus, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenStornoReceipt( /*[in]*/ short SectionNumber,  /*[in]*/ BSTR OperatorName,  /*[in]*/ long ReceiptToStornoNumber,  /*[in]*/ long ReceiptNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddStornoAmount( /*[in]*/ short PaymentType,  /*[in]*/ CURRENCY dAmountToStorno, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall JournalPrint( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall JournalRead( /*[in]*/ short Operation,  /*[in]*/ unsigned long Parameter, /*[out]*/ BSTR * Data, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalNumber( /*[out]*/ short * JournalNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalRecordCount( /*[out]*/ short * RecordCount,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalRecord( /*[in]*/ short RecordNumber,  /*[out]*/ BSTR * RecordData,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalReceiptByIndex( /*[in]*/ unsigned long ReceiptIndex,  /*[out]*/ short * RecordNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalReceiptByNumber( /*[in]*/ unsigned long ReceiptNumber,  /*[out]*/ short * RecordNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRegNumberEx( /*[out]*/ BSTR * SerialNumberEx, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall InstallEx(/*[in]*/ DATE NewDateTime, /*[in]*/ BSTR SerialNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintJournal( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetPrinterStatus(/*[out]*/ long * PrinterStatus, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopy( /*[in]*/ short Operation,  /*[in]*/ unsigned long Parameter, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyClear(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintRegistration(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintAll(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintByOrderNumber(/*[in]*/ short OrderNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintByShiftNumber(/*[in]*/ short ShiftNumber, /*[out, retval]*/ short * Result ) = 0;

    short _stdcall Connect(unsigned char PortNumber)
    {
      short Result;
      (this->Connect(PortNumber, (short*)&Result));
      return Result;
    }
//    short _stdcall Disconnect(void)
//    {
//      //(this->Disconnect(void));
//    }
    short _stdcall Init(DATE CheckDateTime)
    {
      short Result;
      (this->Init(CheckDateTime, (short*)&Result));
      return Result;
    }
    short _stdcall XReport(BSTR OperatorName)
    {
      short Result;
      (this->XReport(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall ZReport(BSTR OperatorName)
    {
      short Result;
      (this->ZReport(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall GetRegNumber(BSTR * RegNumber)
    {
      short Result;
      (this->GetRegNumber(RegNumber, (short*)&Result));
      return Result;
    }
    short _stdcall GetRegNumberEx(BSTR * SerialNumberEx)
    {
      short Result;
      (this->GetRegNumberEx(SerialNumberEx, (short*)&Result));
      return Result;
    }
    short _stdcall Install(DATE CheckDateTime, BSTR SerialNumber)
    {
      short Result;
      (this->Install(CheckDateTime, SerialNumber, (short*)&Result));
      return Result;
    }
    short _stdcall InstallEx(DATE CheckDateTime, BSTR SerialNumber)
    {
      short Result;
      (this->InstallEx(CheckDateTime, SerialNumber, (short*)&Result));
      return Result;
    }
    short _stdcall CutAndPrint(void)
    {
      short Result;
      (this->CutAndPrint((short*)&Result));
      return Result;
    }
    short _stdcall GetFiscNumber(BSTR * FiscNum)
    {
      short Result;
      (this->GetFiscNumber(FiscNum, (short*)&Result));
      return Result;
    }
    short _stdcall ConnectEx(unsigned char PortNumber, unsigned long BaudRate)
    {
      short Result;
      (this->ConnectEx(PortNumber, BaudRate, (short*)&Result));
      return Result;
    }
    short _stdcall GetVersion(short * Version)
    {
      short Result;
      (this->GetVersion((short*)Version, (short*)&Result));
      return Result;
    }
    short _stdcall JournalPrint(BSTR OperatorName)
    {
      short Result;
      (this->JournalPrint(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall PrintJournal(BSTR OperatorName)
    {
      short Result;
      (this->PrintJournal(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall JournalRead(short Operation, unsigned long Parameter, BSTR * Data)
    {
      short Result;
      (this->JournalRead(Operation, Parameter, Data, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalNumber(short * JournalNumber)
    {
      short Result;
      (this->GetJournalNumber((short*)JournalNumber, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalRecordCount(short * RecordCount)
    {
      short Result;
      (this->GetJournalRecordCount((short*)RecordCount, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalRecord(short RecordNumber, BSTR * RecordData)
    {
      short Result;
      (this->GetJournalRecord(RecordNumber, RecordData, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalReceiptByIndex(unsigned long ReceiptIndex, short * RecordNumber)
    {
      short Result;
      (this->GetJournalReceiptByIndex(ReceiptIndex, RecordNumber, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalReceiptByNumber(unsigned long ReceiptNumber, short * RecordNumber)
    {
      short Result;
      (this->GetJournalReceiptByNumber(ReceiptNumber, RecordNumber, (short*)&Result));
      return Result;
    }
    short _stdcall GetPrinterStatus(long * PrinterStatus)
    {
      short Result;
      (this->GetPrinterStatus(PrinterStatus, (short*)&Result));
      return Result;
    }
    short _stdcall ZCopy(short Operation, unsigned long Parameter)
    {
      short Result;
      (this->ZCopy(Operation, Parameter, (short*)&Result));
      return Result;
    }
    short _stdcall ZCopyClear(void)
    {
      short Result;
      (this->ZCopyClear((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintRegistration(void)
    {
      short Result;
      (this->ZCopyPrintRegistration((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintAll(void)
    {
      short Result;
      (this->ZCopyPrintAll((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintByOrderNumber(short OrderNumber)
    {
      short Result;
      (this->ZCopyPrintByOrderNumber(OrderNumber, (short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintByShiftNumber(short ShiftNumber)
    {
      short Result;
      (this->ZCopyPrintByShiftNumber(ShiftNumber, (short*)&Result));
      return Result;
    }


};


interface SPOleFR_1C  : public IDispatch
{
    virtual HRESULT _stdcall Connect( /*[in]*/ unsigned char PortNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Disconnect( void ) = 0;
    virtual HRESULT _stdcall GetObjectState( void ) = 0;
    virtual HRESULT _stdcall GetErrorMesageRU( /*[in]*/ short ErrorCode ) = 0;
    virtual HRESULT _stdcall GetStatus(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CheckActive(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Init( /*[in]*/ BSTR CurrentDate,  /*[in]*/ BSTR CurrentTime, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Register( /*[in]*/ BSTR OldPassword,  /*[in]*/ BSTR RegNumber,  /*[in]*/ BSTR INN,  /*[in]*/ BSTR NewPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ActivateEJJ(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseEEJArchive(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenReceipt( /*[in]*/ short ReceiptType,  /*[in]*/ short SectionNumber,  /*[in]*/ BSTR OperatorName,  /*[in]*/ unsigned long ReceiptNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintString( /*[in]*/ BSTR Buffer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall BreakReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddArticle( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ CURRENCY ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall StornoArticle( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ CURRENCY ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall DiscountPercent( /*[in]*/ BSTR DiscountName,  /*[in]*/ CURRENCY Percent, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall DiscountTotal( /*[in]*/ BSTR DiscountName,  /*[in]*/ CURRENCY Total, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ExtraPercent( /*[in]*/ BSTR ExtraName,  /*[in]*/ CURRENCY Percent, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ExtraTotal( /*[in]*/ BSTR ExtraName,  /*[in]*/ CURRENCY Total, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SubTotal(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Payment( /*[in]*/ short PaymentType,  /*[in]*/ CURRENCY PaymentTotal, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CashInOut( /*[in]*/ BSTR BanknoteName,  /*[in]*/ CURRENCY Total, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall XReport( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZReport( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall FiscShiftReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ short FirstShift,  /*[in]*/ short LastShift,  /*[in]*/ BSTR FiscPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall FiscDateReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ long SinceDay,  /*[in]*/ long SinceMonth,  /*[in]*/ long SinceYear,  /*[in]*/ long TillDay,  /*[in]*/ long TillMonth,  /*[in]*/ long TillYear,  /*[in]*/ BSTR FiscPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJJournal( /*[in]*/ short ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceipt( /*[in]*/ unsigned long KPKNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJShiftReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ short FirstShift,  /*[in]*/ short LastShift, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJDateReport( /*[in]*/ VARIANT_BOOL IsFull,  /*[in]*/ long SinceDay,  /*[in]*/ long SinceMonth,  /*[in]*/ long SinceYear,  /*[in]*/ long TillDay,  /*[in]*/ long TillMonth,  /*[in]*/ long TillYear,  /*[in]*/ BSTR FiscPassword, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJActivationReport(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport( /*[in]*/ short ShiftNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReadConfig( /*[in]*/ short i,  /*[in]*/ short j, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteConfig( /*[in]*/ short i,  /*[in]*/ short j,  /*[in]*/ BSTR Param, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetDate( /*[in]*/ BSTR NewDate,  /*[in]*/ BSTR NewTime, /*[out, retval]*/ short * Resulr ) = 0;
    virtual HRESULT _stdcall GetEEJStatus(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentShift(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetLastRegShift(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRegNumber(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall OpenCashDrawer(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall LoadLogo( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall LoadLogoFromFile( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CancelReport(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddBarcode( /*[in]*/ short BarcodeType,  /*[in]*/ short BarcodeAsDigit,  /*[in]*/ short BarcodeHeight,  /*[in]*/ short BarcodeWidth,  /*[in]*/ BSTR BarcodeData, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CutAndPrint(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJEmergencyShiftClose(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ShowClientDisplayText( /*[in]*/ BSTR Row1,  /*[in]*/ BSTR Row2, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ClearLogo(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentDocNum(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReceiptNum(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReportNum(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCashInDrawer(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSumByCashtype( /*[in]*/ short CashType, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookSumByCashtype( /*[in]*/ short CashType, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintStringEx( /*[in]*/ BSTR Buffer,  /*[in]*/ VARIANT_BOOL DoubleHeight,  /*[in]*/ VARIANT_BOOL DoubleWidth,  /*[in]*/ VARIANT_BOOL Underlined, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall IsActive(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall ArticleTotal(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall CashInDrawer(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall DocumentNumber(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall ReceiptNumber(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall ReportNumber(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall ShiftNumber(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall LastRegShift(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall RebookSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall RegNumber(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall CashSumm(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall ConfigParam(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall ErrorDescription(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall WasConnected(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall ReceiptTotal(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall FatalNVRBad(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalConfigCRC(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalSPIError(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalFiscMemCRC(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalFiscalWrite(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalFiscalNotFound(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalEEJError(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall FatalFiscalEEJNoMatch(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall NoZeroFunction(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall NonFiscal(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall ShiftOpened(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall Shift24H(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJArchiveCLosed(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJNotActivated(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall NoMemory(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall InvalidPasswd(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall DocumentState(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall DocumentType(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall CurrentDate(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall CurrentTime(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall GetCurrentDateTime(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJArchiveOpened(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJActivated(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJDocumentOpened(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJDocType(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall EEJReportState(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJShiftOpened(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall EEJFatalError(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall GetSumForAllPayments(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookSumForAllPayments(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SeekPaymentSum( /*[in]*/ long aIndex, /*[out, retval]*/ CURRENCY * Result ) = 0;
    virtual HRESULT _stdcall SeekRebookSum( /*[in]*/ long aIndex, /*[out, retval]*/ CURRENCY * Result ) = 0;
    virtual HRESULT _stdcall AddArticleEx( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ double ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department,  /*[in]*/ short TaxNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReprintReceipt(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetTaxAmount( /*[in]*/ short TaxNumber,  /*[in]*/ CURRENCY Amount, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetINN(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetFiscNumber(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetEEJNumber(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall INN(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall EEJNumber(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall FiscalNumber(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall StornoArticleEx( /*[in]*/ BSTR ArticleName,  /*[in]*/ BSTR ArticleCode,  /*[in]*/ double ArticleQuantity,  /*[in]*/ CURRENCY ArticleTotal,  /*[in]*/ short Department,  /*[in]*/ short TaxNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ConnectEx( /*[in]*/ unsigned char PortNumber,  /*[in]*/ unsigned long BaudRate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintStringExWide( /*[in]*/ BSTR Buffer,  /*[in]*/ VARIANT_BOOL DoubleHeight,  /*[in]*/ VARIANT_BOOL DoubleWidth,  /*[in]*/ VARIANT_BOOL Underlined,  /*[in]*/ VARIANT_BOOL Compact, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetTriesNumber( /*[in]*/ short TriesNumber ) = 0;
    virtual HRESULT _stdcall PaymentWithComment( /*[in]*/ short PaymentType,  /*[in]*/ CURRENCY PaymentTotal,  /*[in]*/ BSTR Comment, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseReceiptEx( /*[in]*/ VARIANT_BOOL DoCut, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SetBaudrate( /*[in]*/ long BaudRate, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall StoreReceipt( /*[in]*/ BSTR Text, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetCurrentReceiptSums(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceiptCounters(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetReceiptsSums(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetDiscountAndExtraSums(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSellTaxSums(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookTaxSums(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetSellPaymentsCount(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetRebookPaymentsCount(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ReceiptSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall ReceiptDiscountSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall ReceiptExtraSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall SellDiscountSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall SellExtraSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall RebookDiscountSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall RebookExtraSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall SellReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall RebookReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall CancelledReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall StoredReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall DepositReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall TakingReceipts(/*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall StoredReceiptsSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall DepositReceiptsSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall CancelledReceiptsSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall TakingReceiptsSum(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall SeekSellPaymentsCount( /*[in]*/ long Index, /*[out, retval]*/ long * Result ) = 0;
    virtual HRESULT _stdcall SeekRebookPaymentsCount( /*[in]*/ long Index, /*[out, retval]*/ long * Result ) = 0;
    virtual HRESULT _stdcall SeekSellTaxSum( /*[in]*/ long Index, /*[out, retval]*/ CURRENCY * Result ) = 0;
    virtual HRESULT _stdcall SeekRebookTaxSum( /*[in]*/ long Index, /*[out, retval]*/ CURRENCY * Result ) = 0;
    virtual HRESULT _stdcall IsDrawerOpened(/*[out, retval]*/ VARIANT_BOOL * Value ) = 0;
    virtual HRESULT _stdcall GetDrawerOpened(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetDateTime(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall Version(/*[out, retval]*/ short * Value ) = 0;
    virtual HRESULT _stdcall GetVersion(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AccumulatedTotal(/*[out, retval]*/ CURRENCY * Value ) = 0;
    virtual HRESULT _stdcall GetAccumulatedTotal(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteHeader( /*[in]*/ short iRow,  /*[in]*/ BSTR sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall WriteFooter( /*[in]*/ short iRow,  /*[in]*/ BSTR sRow, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall CloseNonFiscalEx(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport_Text_Close(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReport_Text_Next(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJReportRow(/*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall EEJJournal_Text( /*[in]*/ short wShift, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJDateReport_Text( /*[in]*/ VARIANT_BOOL bIsFull,  /*[in]*/ long SinceDay,  /*[in]*/ long SinceMonth,  /*[in]*/ long SinceYear,  /*[in]*/ long TillDay,  /*[in]*/ long TillMonth,  /*[in]*/ long TillYear, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJShiftReport_Text( /*[in]*/ VARIANT_BOOL bIsFull,  /*[in]*/ short wFirstShift,  /*[in]*/ short wLastShift, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall EEJGetReceipt_Text( /*[in]*/ long dwKPKNumber, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetAccumulatedTotalEx(/*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphics( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsFromFile( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall AddQRCode( /*[in]*/ short QRCodeScale,  /*[in]*/ short QRCodeErrCorrectionLevel,  /*[in]*/ BSTR QRCodeData, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsEx( /*[in]*/ short ByteCount,  /*[in]*/ VARIANT DataBufferPointer, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrintGraphicsFromFileEx( /*[in]*/ BSTR FileName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall JournalPrint( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall JournalRead( /*[in]*/ short Operation,  /*[in]*/ unsigned long Parameter, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalNumber( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalRecordCount( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalRecord( /*[in]*/ short RecordNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalReceiptByIndex( /*[in]*/ unsigned long ReceiptIndex,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetJournalReceiptByNumber( /*[in]*/ unsigned long ReceiptNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall JournalRecordData( /*[out, retval]*/ BSTR * Value ) = 0;
    virtual HRESULT _stdcall JournalNumber( /*[out, retval]*/ short * Value ) = 0;
    virtual HRESULT _stdcall JournalRecordCount( /*[out, retval]*/ short * Value ) = 0;
    virtual HRESULT _stdcall JournalRecordNumber( /*[out, retval]*/ short * Value ) = 0;
    virtual HRESULT _stdcall GetRegNumberEx( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall SerialNumberEx( /*[out, retval]*/ BSTR * Value ) = 0;


    virtual HRESULT _stdcall PrintJournal( /*[in]*/ BSTR OperatorName, /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall GetPrinterStatus( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall PrinterStatus( /*[out, retval]*/ long * Value ) = 0;
    virtual HRESULT _stdcall ZCopy( /*[in]*/ short Operation,  /*[in]*/ unsigned long Parameter,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyClear( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintRegistration( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintAll( /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintByOrderNumber( /*[in]*/ short OrderNumber,  /*[out, retval]*/ short * Result ) = 0;
    virtual HRESULT _stdcall ZCopyPrintByShiftNumber( /*[in]*/ short ShiftNumber,  /*[out, retval]*/ short * Result ) = 0;







    short _stdcall Connect(unsigned char PortNumber)
    {
      short Result;
      (this->Connect(PortNumber, (short*)&Result));
      return Result;
    }
    short _stdcall XReport(BSTR OperatorName)
    {
      short Result;
      (this->XReport(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall ZReport(BSTR OperatorName)
    {
      short Result;
      (this->ZReport(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall CutAndPrint(void)
    {
      short Result;
      (this->CutAndPrint((short*)&Result));
      return Result;
    }
    short _stdcall GetRegNumber(void)
    {
      short Result;
      (this->GetRegNumber((short*)&Result));
      return Result;
    }
    BSTR _stdcall RegNumber(void)
    {
      BSTR Value = 0;
      (this->RegNumber((BSTR*)&Value));
      return Value;
    }
    short _stdcall GetFiscNumber(void)
    {
      short Result;
      (this->GetFiscNumber((short*)&Result));
      return Result;
    }
    BSTR _stdcall FiscalNumber(void)
    {
      BSTR Value = 0;
      (this->FiscalNumber((BSTR*)&Value));
      return Value;
    }
    short _stdcall ConnectEx(unsigned char PortNumber, unsigned long BaudRate)
    {
      short Result;
      (this->ConnectEx(PortNumber, BaudRate, (short*)&Result));
      return Result;
    }
    short _stdcall JournalPrint(BSTR OperatorName)
    {
      short Result;
      (this->JournalPrint(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall PrintJournal(BSTR OperatorName)
    {
      short Result;
      (this->PrintJournal(OperatorName, (short*)&Result));
      return Result;
    }
    short _stdcall JournalRead(short Operation, unsigned long Parameter)
    {
      short Result;
      (this->JournalRead(Operation, Parameter, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalNumber(void)
    {
      short Result;
      (this->GetJournalNumber((short*)&Result));
      return Result;
    }
    short _stdcall GetJournalRecordCount(void)
    {
      short Result;
      (this->GetJournalRecordCount((short*)&Result));
      return Result;
    }
    short _stdcall GetJournalRecord(short RecordNumber)
    {
      short Result;
      (this->GetJournalRecord(RecordNumber, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalReceiptByIndex(unsigned long ReceiptIndex)
    {
      short Result;
      (this->GetJournalReceiptByIndex(ReceiptIndex, (short*)&Result));
      return Result;
    }
    short _stdcall GetJournalReceiptByNumber(unsigned long ReceiptNumber)
    {
      short Result;
      (this->GetJournalReceiptByNumber(ReceiptNumber, (short*)&Result));
      return Result;
    }
    BSTR _stdcall JournalRecordData(void)
    {
      BSTR Value = 0;
      (this->JournalRecordData((BSTR*)&Value));
      return Value;
    }
    short _stdcall JournalNumber(void)
    {
      short Value;
      (this->JournalNumber(&Value));
      return Value;
    }
    short _stdcall JournalRecordCount(void)
    {
      short Value;
      (this->JournalRecordCount((short*)&Value));
      return Value;
    }
    short _stdcall JournalRecordNumber(void)
    {
      short Value;
      (this->JournalRecordNumber((short*)&Value));
      return Value;
    }
    short _stdcall GetRegNumberEx(void)
    {
      short Result;
      (this->GetRegNumberEx((short*)&Result));
      return Result;
    }
    BSTR _stdcall SerialNumberEx(void)
    {
      BSTR Value = 0;
      (this->SerialNumberEx((BSTR*)&Value));
      return Value;
    }
    short _stdcall GetPrinterStatus(void)
    {
      short Result;
      (this->GetPrinterStatus((short*)&Result));
      return Result;
    }
    long _stdcall PrinterStatus(void)
    {
      long Value = 0;
      (this->PrinterStatus(&Value));
      return Value;
    }
    short _stdcall ZCopy(short Operation, unsigned long Parameter)
    {
      short Result;
      (this->ZCopy(Operation, Parameter, (short*)&Result));
      return Result;
    }
    short _stdcall ZCopyClear(void)
    {
      short Result;
      (this->ZCopyClear((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintRegistration(void)
    {
      short Result;
      (this->ZCopyPrintRegistration((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintAll(void)
    {
      short Result;
      (this->ZCopyPrintAll((short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintByOrderNumber(short OrderNumber)
    {
      short Result;
      (this->ZCopyPrintByOrderNumber(OrderNumber, (short*)&Result));
      return Result;
    }
    short _stdcall ZCopyPrintByShiftNumber(short ShiftNumber)
    {
      short Result;
      (this->ZCopyPrintByShiftNumber(ShiftNumber, (short*)&Result));
      return Result;
    }

};






#endif // SP101FRKLIB_H
