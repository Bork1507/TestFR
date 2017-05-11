/* ===========================================================
 * Fiscal registrator SP101FR-K interface library
 * Version 1.28.2.2
 * Copyright (c) 2003-2007 Service Plus AT.  All rights
 * reserved.
 * ===========================================================
 */

#ifndef SP101FR_H
#define SP101FR_H
#endif

#ifdef SP101FR_EXPORTS
#define SP101FR_API __declspec(dllexport) 
#define SPFR_API __declspec(dllexport) 
#else
#define SP101FR_API __declspec(dllimport) 
#define SPFR_API __declspec(dllimport) 
#endif

#define SPFRE_OK                     0x00   //no error

//------------------------------IO errors--------------------------------------//
#define SPFRE_STATUS                 0x01   //couldn't execute function, invalid status 
#define SPFRE_INVALID_FUNCTION       0x02   //invalid function code
#define SPFRE_INVALID_PARAMETER      0x03   //invalid parameter

//------------------------------protocol errors--------------------------------//
#define SPFRE_UART_OVER              0x04   //port buffer overflow
#define SPFRE_INTERCHAR_TIMEOUT      0x05   //data sending timeout
#define SPFRE_INVALID_PASSWORD       0x06   //invalid password in command
#define SPFRE_INVALID_CHECKSUM       0x07   //CRC error

//------------------------------printer errors---------------------------------//
#define SPFRE_PAPER_END              0x08   //paper end
#define SPFRE_PRINTER_NOTREADY       0x09   //printer not ready

//------------------------------date & time errors-----------------------------//
#define SPFRE_SHIFT_TIME_OVER        0x0A   //current shift is longer than 24h
#define SPFRE_SYNC_TIME_OVER         0X0B   //time sync error
#define SPFRE_TIME_LOW               0X0C   //
#define SPFRE_NO_HEADER              0x0D   //document header not found
#define SPFRE_FALSE                  0x0E   //erroneous result

//------------------------------customer display error-------------------------//
#define SPFRE_DISPLAY_NOT_READY      0x0F   //customer display is not ready

//------------------------------COM-errors-------------------------------------//
#define SPFRE_DATA_SEND              0x10   //couldn't send data
#define	SPFRE_NODATA                 0x11   //no data from FR
#define	SPFRE_PORT_INACCESSIBLE      0x12   //port inaccessible
#define	SPFRE_NOREGISTRATOR          0x13   //FR not found

//------------------------------fatal errors-----------------------------------//
#define SPFRE_FATAL                  0x20   //fatal error
#define SPFRE_NO_FREE_SPACE          0x21   //memory low
#define SPFRE_DEVICE_TIMEOUT         0x30   //device timeout
#define SPFRE_BUSY                   0x40   //device busy

//------------------------------Journal errors-------------------------------------//
#define SPFRE_JOURNAL_NO_DATA        0x24   //no data in journal

//------------------------------EEJ errors-------------------------------------//
#define SPFRE_EEJ1                   0x41   //incorrect command or parameter
#define SPFRE_EEJ2                   0x42   //invalid EEJ state
#define SPFRE_EEJ3                   0x43   //EEJ crash
#define SPFRE_EEJ4                   0x44   //EEJ crypto coprocessor crash
#define SPFRE_EEJ5                   0x45   //EEJ using time deadline
#define SPFRE_EEJ6                   0x46   //EEJ overflowed
#define SPFRE_EEJ7                   0x47   //invalid date or time
#define SPFRE_EEJ8                   0x48   //requested data not found
#define SPFRE_EEJ9                   0x49   //overflow
#define SPFRE_EEJA                   0x4A   //no response
#define SPFRE_EEJB                   0x4B   //EEJ data eschange error
#define SPFRE_EEJ_END                0x4C   //EEJ конец отчета

//-----------------------------SKNO errors-------------------------------------//
#define SPFRE_SKNO_INCORRECT_STATE   0x41    //SKNO incorrect state 
#define SPFRE_SKNO_LONG              0x42    //an opened receipt is too big
#define SPFRE_SKNO_NONE              0x4A    //no response
#define SPFRE_SKNO_IO                0x4B    //data exchange error

typedef unsigned short USHORT;
typedef unsigned long  ULONG;
typedef unsigned char  BYTE;
typedef LONGLONG FRCURRENCY;

// Memory type for direct memory I/O
enum  SPRF_DATA_AREA {
	// CPU firmware area
	arBIOS,
	// Fiscal memory area
	arDATA,
	// RAM area
	arRAM,
	// EEPROM area
	arEEPROM
};


enum  BARCODE_TYPE {
	UPCA = 0,       //UPC-A
	UPCE,           //UPC-E
	EAN13,          //EAN-13
	EAN8,           //EAN-8
	Code39,         //Code-39
	Interleaved,    //Interleaved 2 of 5
	Codabar         //Codabar
};

enum BARCODE_WIDTH
{
	bw2 = 0,        //width = 2
	bw3 = 1,        //width = 3
	bw4 = 2         //width = 4
};

enum BARCODE_ASDIGIT
{
	NO_DIGIT = 0,   //do dot print digits in barcode
	DIGIT_UP,       //print digits at the top
	DIGIT_DOWN,     //print digits at the bottom
	DIGIT_UPDOWN    //print digits at the top and at the bottom
};

enum QRCODE_ERR_CORRECTION_LEVEL   // how much damage the QR code is expected to suffer
{
	LEVEL_L = 0,     // level L up to 7% damage
	LEVEL_M = 1,     // level M up to 15% damage
	LEVEL_Q = 2,     // level Q up to 25% damage
	LEVEL_H = 3      // level H up to 30% damage
};

#pragma pack(1)

typedef struct SPFR_EEJRECORD {	
	char                s[41];	
}SPFR_EEJRECORD;

typedef struct SPFR_SUMS {
	FRCURRENCY  sums[16];
} SPFR_SUMS;

typedef struct SPFR_CURRENTRECEIPTSUMS {
	FRCURRENCY  ReceiptSum;
	FRCURRENCY  ReceiptDiscountSum;
	FRCURRENCY  ReceiptExtraSum;
} SPFR_CURRENTRECEIPTSUMS;


typedef struct SPFR_RECEIPTCOUNTERS {
	long SellReceipts;
	long RebookReceipts;
	long CancelledReceipts;
	long StoredReceipts;
	long DepositReceipts;
	long TakingReceipts;
} SPFR_RECEIPTCOUNTERS;


typedef struct SPFR_RECEIPTSSUMS {
	FRCURRENCY  CancelledReceiptsSum;  
	FRCURRENCY  StoredReceiptsSum;
	FRCURRENCY  DepositReceiptsSum;
	FRCURRENCY  TakingReceiptsSum;
} SPFR_RECEIPTSSUMS;

typedef struct SPFR_DISCOUNTANDEXTRASUMS {
	FRCURRENCY  SellDiscountSum;  
	FRCURRENCY  SellExtraSum;
	FRCURRENCY  RebookDiscountSum;
	FRCURRENCY  RebookExtraSum;
} SPFR_DISCOUNTANDEXTRASUMS;


typedef struct SPFR_TAXSUMS {
	FRCURRENCY  sums[6];  
} SPFR_TAXSUMS;


typedef struct SPFR_PAYMENTCOUNTS {
	long  paymentcounts[16];  
} SPFR_PAYMENTCOUNTS;

// EEJ number structure 
typedef struct SPFR_EEJ_NUMBER {
	char s[17];
} SPFR_EEJ_NUMBER;

// Barcode structure
typedef struct SPFR_BARCODE {
	char                s[41];	
	BARCODE_TYPE        BarcodeType;    //Barcode type
	BARCODE_ASDIGIT     BarcodeAsDigit; //Digits in barcode
	BARCODE_WIDTH       BarcodeWidth;   //Width of barcode
	BYTE                BarcodeHeight;  //Height of barcode	
} SPFR_BARCODE;

// QR-code structure
typedef struct SPFR_QRCODE {
	char                         s[406];	
	USHORT                       QRCodeScale;              // QR code scale 1 to 10
	QRCODE_ERR_CORRECTION_LEVEL  QRCodeErrCorrectionLevel; // how much damage the QR code is expected to suffer
} SPFR_QRCODE;

// Text to print on client display structure 
typedef struct SPFR_CLIENTDISPLAYTEXT {
	char  s[21];                    //Text data
} SPFR_CLIENTDISPLAYTEXT;

// INN structure
typedef struct SPFR_INN {
	char  s[13];                    //INN data
} SPFR_INN;

// UNP structure
typedef struct SPFR_UNP {
	char  s[10];                    //UNP data
} SPFR_UNP;

// SKNO structure
typedef struct SPFR_SKNO {
	char  s[10];                    //SKNO data
} SPFR_SKNO;

// FR registration number structure
typedef struct SPFR_REGISTRATION_NUMBER {
	char  s[13];                    //Registration number and Serial number исспользуется при получении серийного номера, регистрационного номера, и при фискализации 
} SPFR_REGISTRATION_NUMBER;

// FR long serial number structure
typedef struct SPFR_SERIAL_NUMBER_EX {
	char  s[20];                    //Serial number исспользуется для получения длинного серийного номера и при инсталляции с длинным серийным номером
} SPFR_SERIAL_NUMBER_EX;


// FR serial number structure
typedef struct SPFR_SERIAL_NUMBER {
	char  s[13];                    //Serial number исспользуется при инсталляции с коротким серийным номером
} SPFR_SERIAL_NUMBER;

// Password structure
typedef struct SPFR_PASSWORD {
	char  s[11];                    //Password
} SPFR_PASSWORD;

// Article code structure
typedef struct SPFR_ARTICLE_CODE {
	char  s[14];                    //Article code data    18
} SPFR_ARTICLE_CODE;

typedef struct SPFR_ARTICLE_CODE2 {
	char  s[19];                    //Article code data    18
} SPFR_ARTICLE_CODE2;

// Article name structure
typedef struct SPFR_ARTICLE_NAME {
	char  s[35];                    //Article name
} SPFR_ARTICLE_NAME;

// Second article name structure
typedef struct SPFR_ARTICLE_NAME2 {
	char  s[41];                    //Article name
} SPFR_ARTICLE_NAME2;

// EAN
typedef struct SPFR_ARTICLE_EAN {
	char  s[14];                    //Article EAN
} SPFR_ARTICLE_EAN;

// Discount name structure
typedef struct SPFR_DISCOUNT_NAME {
	char  s[33];                    //Discount name
} SPFR_DISCOUNT_NAME;
typedef SPFR_DISCOUNT_NAME  SPFR_EXTRA_NAME;

// Discount name structure
typedef struct SPFR_DISCOUNT_NAME2 {
	char  s[41];                    //Discount name
} SPFR_DISCOUNT_NAME2;
typedef SPFR_DISCOUNT_NAME2  SPFR_EXTRA_NAME2;

// Operator name structure
typedef struct SPFR_OPERATOR_NAME {
	char s[21];                 //Operator name
} SPFR_OPERATOR_NAME;

// Payment name structure
typedef struct SPFR_PAYMENT_NAME {
	char  s[22];                    //Payment name
} SPFR_PAYMENT_NAME;

// Banknote name structure
typedef struct SPFR_BANKNOTE_NAME {
	char  s[23];                    //Banknote name
} SPFR_BANKNOTE_NAME;

typedef struct SPFR_STRING44 {
	char  s[45];                    //FR string
} SPFR_STRING45;

// FR string structure
typedef struct SPFR_STRING {
	char  s[41];                    //FR string
} SPFR_STRING;

typedef struct SPFR_STRINGEX {
	char  s[57];                    //FR string
} SPFR_STRINGEX;

// FR error message structure
typedef struct SPFR_ERROR_MESSAGE {
	char  s[512];                   //Error message
} SPFR_ERROR_MESSAGE;

// Name of logotype file structure
typedef struct SPFR_LOGO_NAME {
	char  s[256];                   //Logotype file name
} SPFR_LOGO_NAME;

// Name of file structure
typedef struct SPFR_FILE_NAME {
	char  s[256];                   //File name
} SPFR_FILE_NAME;

// FR date structure
typedef struct {
	unsigned char       day;        //day,   1-31
	unsigned char       month;      //month, 1-12
	unsigned short      year;       //year,  1980-2078
} SPFRDT;

// FR date structure
typedef struct {
	// Day of month
	unsigned char Day;
	// Month
	unsigned char Month;
	// Year (ranged 1980-2078)
	unsigned short Year;
} SPFR_DATE;


// FR time structure
typedef struct {
	unsigned char hour;             //hour,   0-23
	unsigned char minute;           //minute, 0-59
	unsigned char second;           //second, 0-59
} SPFRTM;


// FR flags status
typedef union {
	struct {
		unsigned no_init:1;         //not initialized
		unsigned no_fiscal:1;       //nonfiscal mode
		unsigned shift_opened:1;    //shift opened
		unsigned time_over:1;       //shift opened for more than 24h
		unsigned eej_arc_closed:1;  //EEJ archive closed
		unsigned eej_not_active:1;  //EEJ not active
		unsigned fm_full:1;         //No memory to close shift
		unsigned fm_errpass:1;      //Wrong password
	} flags;
	BYTE  status;                   //Flags as byte
} SPFR_STATUS;


// FR fatal state status
typedef union {
	struct {
		unsigned nvr_error:1;       //NVR checksum error
		unsigned config_crc_error:1;//Configuration checksum error
		unsigned spi_error:1;       //SPI error
		unsigned fm_crc_error:1;    //Fiscal memory checksum error
		unsigned fm_write_error:1;  //Fiscal memory writing error
		unsigned not_installed:1;   //Fiscal module not installed
		unsigned eej_error:1;       //EEJ fatal error
	} flags;
	BYTE  status;                   //fatal status as byte
} SPFR_FATAL_STATUS;

// Current document status
typedef union {
	struct {
		unsigned type:4;            /*Current document type
		                            0   Document closed
		                            1   Nonfiscal document
		                            2   Receipt (sale)
		                            3   Receipt (return)
		                            4   Cash in
		                            5   Cash out
		                            */
		unsigned mode:4;            /*Current document state
		                            0	Document closed
		                            1	New document command issued
		                            2	Subtotal command issued
		                            3	Payment happens
		                            4	Document completed - to be closed
		                            */
	}   flags;                      //document status as byte
	BYTE  status;
} SPFR_REC_STATUS;

// Data in fiscal memory (for SP101 fiscal memory type)

// Serial number
typedef struct {
	// Serial number
	char SerialNumber[12];
} SPFR_FISCAL_DATA_SERIAL_101;

// Fiscalization / registration record
typedef struct {
	// Registration number (string)
	char RegNumber[12];
	// INN (string)
	char Inn[12];
	// Registration date
	SPFR_DATE Date;
	// Number of closed shifts
	unsigned short Shift;
	// Fiscal officer password (string)
	char Password[10];
	// Reserved
	char Reserved[24];
} SPFR_FISCAL_DATA_REGISTRATION_101;

// EKLZ activization record
typedef struct {
	// EKLZ number (string)
	char EKLZNumber[12];
	// Activization date
	SPFR_DATE Date;
	// Number of closed shifts
	unsigned short Shift;
	// Reserved
	char Reserved[14];
} SPFR_FISCAL_DATA_ACTIVIZATION_101;

// Shift record
typedef struct {
	// Shift number
	unsigned short Shift;
	// Shift date
	SPFR_DATE Date;
	// Sum of sales
	FRCURRENCY CashIn;
	// Sum of returns
	FRCURRENCY CashOut;
} SPFR_FISCAL_DATA_SHIFT_101;

// Data in fiscal memory (for SP402 fiscal memory type)

// Serial number
typedef struct {
	// Serial number
	char SerialNumber[12];
	// Record attributes
	unsigned char Crc;
	unsigned char Flag;
} SPFR_FISCAL_DATA_SERIAL_402;

// Fiscalization / registration record
typedef struct {
	// Registration number (string)
	char RegNumber[12];
	// INN (string)
	char Inn[12];
	// Registration date
	SPFR_DATE Date;
	// Number of closed shifts
	unsigned short Shift;
	// Fiscal officer password (string)
	char Password[10];
	// Record attributes
	unsigned char Crc;
	unsigned char Flag;
} SPFR_FISCAL_DATA_REGISTRATION_402;

// EKLZ activization record
typedef struct {
    // EKLZ number (string)
	char EKLZNumber[12];
	// Activization date
	SPFR_DATE Date;
	// Number of closed shifts
	unsigned short Shift;
	// Record attributes
	unsigned char Crc;
	unsigned char Flag;
} SPFR_FISCAL_DATA_ACTIVIZATION_402;

// Shift record
typedef struct {
	// Shift number
	unsigned short Shift;
	// Shift date
	SPFR_DATE Date;
	// Sum of sales
	FRCURRENCY CashIn;
	// Sum of returns
	FRCURRENCY CashOut;
	// Record attributes
	unsigned char Crc;
	unsigned char Flag;
} SPFR_FISCAL_DATA_SHIFT_402;


#pragma pack()

extern SP101FR_API USHORT SPFRTMOUT; //timeout

//--------------------------------------------------------------------------------------//
// FUNCTIONS
//--------------------------------------------------------------------------------------//


//--------------------------------------------------------------------------------------//
// SPFR_ReleasePort
//         Releases FR handle
// [in]  
//         hFr                  FR handle
//--------------------------------------------------------------------------------------//
SP101FR_API void __stdcall SPFR_ReleasePort(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_CheckActive
//         Checks if any FR connected to com-port
// [in]    
//         hFr                  FR handle
// [out]   
//         IsActive             TRUE if FR presents, FALSE otherwise
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CheckActive(HANDLE hFR, LPBOOL IsActive);


//--------------------------------------------------------------------------------------//
// SPFR_GetStatus
//         Gets status bytes from FR
// [in]
//         hFr                  FR handle
// [out]   
//         FatalStatus          byte of fatal status
//         Status               flags status
//         ReceiptStatus        document status
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetStatus(HANDLE             hFR,
                                            SPFR_FATAL_STATUS* sFatalStatus,
                                            SPFR_STATUS*       sStatus,
                                            SPFR_REC_STATUS*   sReceiptStatus);

//--------------------------------------------------------------------------------------//
// SPFR_GetPrinterStatus
//         Gets status bytes from FR
// [in]
//         hFr                  FR handle
// [out]   
//         PrinterStatus        printer status
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetPrinterStatus(HANDLE             hFR,
												   ULONG*  pPrinterStatus);

SP101FR_API USHORT __stdcall SPFR_ZCopy(HANDLE  hFR,
										USHORT wOperation,
										ULONG  wParameter);

SP101FR_API USHORT __stdcall SPFR_GetPrinterVersion(HANDLE hFR, SPFR_STRING *psData);

//--------------------------------------------------------------------------------------//
// SPFR_GetVersion
//         Connects to FR
// [in]
//         hFr                  FR handle
// [out]
//         nVersion             FR version
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetVersion(HANDLE             hFR,
                                             USHORT*            nVersion);

//--------------------------------------------------------------------------------------//
// SPFR_CheckPort
//         Connects to FR
// [in]
//         PortNum              COM-port number
// [out]   
//         bError               error code
// [ret]   
//         handle of COM-port
//--------------------------------------------------------------------------------------//
SP101FR_API HANDLE __stdcall SPFR_CheckPort(int PortNum, LPBYTE bError);


//--------------------------------------------------------------------------------------//
// SPFR_CheckPortEx
//         Connects to FR using specified baud rate
// [in]
//         PortNum              COM-port number
//         BaudRate             baud rate of computer COM-port
// [out]   
//         bError               error code
// [ret]   
//         handle of COM-port
//--------------------------------------------------------------------------------------//
SP101FR_API HANDLE __stdcall SPFR_CheckPortEx(int     PortNum,
                                              UINT    BaudRate,
                                              LPBYTE  bError);

//--------------------------------------------------------------------------------------//
// SPFR_Init
//         Initializes FR
// [in]
//         hFr                  FR handle
//         dtCheckDate          Current date
//         tmCheckTime          Current time
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_Init(HANDLE hFR, SPFRDT* dtCheckDate, SPFRTM* tmCheckTime);


//--------------------------------------------------------------------------------------//
// SPFR_Register
//         FR fiscalization
// [in]
//         hFr                  FR handle
//         pOldPassword         Current password of tax official
//         rRegNumber           Registration number
//         iINN                 INN number
//         pNewPassword         New password of tax official
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_Register(HANDLE                    hFR,
                                           SPFR_PASSWORD*            pOldPassword,
                                           SPFR_REGISTRATION_NUMBER* rRegNumber,
                                           SPFR_INN*                 iINN,
                                           SPFR_PASSWORD*            pNewPassword);

//--------------------------------------------------------------------------------------//
// SPFR_RegisterSKNO       [BEL]
//         FR registration 
// [in]
//         hFr                  FR handle
//         pOldPassword         Current password of tax official
//         rRegNumber           Registration number
//         sUNP                 UNP number
//         sSKNO                SKNO number
//         pNewPassword         New password of tax official
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_RegisterSKNO(HANDLE                    hFR,
                                               SPFR_PASSWORD*            pOldPassword,
                                               SPFR_REGISTRATION_NUMBER* rRegNumber,
                                               SPFR_UNP*                 sUNP,
											   SPFR_SKNO*                sSKNO,
                                               SPFR_PASSWORD*            pNewPassword);

//--------------------------------------------------------------------------------------//
// SPFR_ActivateEEJ
//         Activates EJJ
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ActivateEEJ(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_CloseEEJArchive
//         Closes EJJ archive
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CloseEEJArchive(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_OpenReceipt
//         Opens new receipt and changes FR mode to input mode
// [in]
//         hFR                  FR handle
//         wReceiptType         Document type code
//         wSectionNumber       Section number
//         nOperatorName        Operator name or operator code (look up documentation)
//         lReceiptNumber       Document number or 0 if autonumeration enabled
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_OpenReceipt(HANDLE              hFR,
                                              USHORT              wReceiptType,
                                              USHORT              wSectionNumber,
                                              SPFR_OPERATOR_NAME* nOperatorName,
                                              ULONG               lReceiptNumber);

//--------------------------------------------------------------------------------------//
// SPFR_OpenStornoReceipt                          [414]
//         Opens new storno receipt and changes FR mode to input mode
// [in]
//         hFR                    FR handle
//         wSectionNumber         Section number
//         nOperatorName          Operator name or operator code (look up documentation)
//         lReceiptToStornoNumber Source document number to storno
//         lReceiptNumber         Document number or 0 if autonumeration enabled
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_OpenStornoReceipt(HANDLE              hFR,
                                                    USHORT              wSectionNumber,
                                                    SPFR_OPERATOR_NAME* nOperatorName,
                                                    ULONG               lReceiptToStornoNumber,
                                                    ULONG               lReceiptNumber);

//--------------------------------------------------------------------------------------//
// SPFR_AddStornoAmount                          [414]
//         Adds an amount into the opened storno document
// [in]
//         hFr                    FR handle
//         wPaymentType           Payment type code
//         dAmountToStorno        Amount to add into the storno document
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddStornoAmount(HANDLE          hFR,
                                                  USHORT          wPaymentType,
                                                  FRCURRENCY      dAmountToStorno);


//--------------------------------------------------------------------------------------//
// SPFR_PrintString
//         Prints supplementary text info
// [in]
//         hFr                  FR handle
//         sBuffer              Buffer containing string to print on
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PrintString(HANDLE          hFR,
                                              SPFR_STRING*    sBuffer);

//--------------------------------------------------------------------------------------//
// SPFR_PrintStringEx                       [OLD]
//         Prints string with extended attributes
// [in]
//         hFr                  FR handle
//         sBuffer              string to be printed
//         bAttr                Font attributes
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PrintStringEx(HANDLE        hFR,
                                                SPFR_STRING*  sBuffer,
                                                BYTE          bAttr);

//--------------------------------------------------------------------------------------//
// SPFR_PrintStringExWide                   [NEW]
//         Prints string with extended attributes      
// [in]
//         hFr                  FR handle
//         sBuffer              string to be printed
//         bAttr                Font attributes
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PrintStringExWide(HANDLE          hFR,
                                                    SPFR_STRINGEX*  sBuffer,
                                                    BYTE            bAttr);


//--------------------------------------------------------------------------------------//
// SPFR_CloseReceipt
//         Closes current receipt
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CloseReceipt(HANDLE hFR);

//--------------------------------------------------------------------------------------//
// SPFR_CloseReceiptEx
//         Closes current receipt with/without cutting
// [in]
//         hFr                  FR handle
//         aDoCut               cut/not cut
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CloseReceiptEx(HANDLE  hFR, 
                                                 bool    aDoCut);


//--------------------------------------------------------------------------------------//
// SPFR_BreakReceipt
//         Cancels current receipt
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_BreakReceipt(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_AddArticle                          [OLD]
//         Inserts new article into document
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
// [out]   
//         dpReceiptTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddArticle(HANDLE               hFR,
                                             SPFR_ARTICLE_NAME*   nArticleName,
                                             SPFR_ARTICLE_CODE*   cArticleCode,
                                             FRCURRENCY           dArticleQty,
                                             FRCURRENCY           dArticleTotal,
                                             USHORT               wDepartment,
                                             FRCURRENCY*          dpReceiptTotal);

//--------------------------------------------------------------------------------------//
// SPFR_AddArticleEx                         [OLD]
//         Inserts new article into document
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
//         wTaxNumber           ID of VAT
// [out]   
//         dpReceiptTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddArticleEx(HANDLE               hFR,
                                               SPFR_ARTICLE_NAME*   nArticleName,
                                               SPFR_ARTICLE_CODE*   cArticleCode,
                                               double               dArticleQty,
                                               FRCURRENCY           dArticleTotal,
                                               USHORT               wDepartment,
                                               USHORT               wTaxNumber,
                                               FRCURRENCY*          dpReceiptTotal);

//--------------------------------------------------------------------------------------//
// SPFR_AddArticleEx2                          [NEW]
//         Inserts new article into document
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
//         wTaxNumber           ID of VAT
// [out]   
//         dpReceiptTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddArticleEx2(HANDLE               hFR,
                                                SPFR_ARTICLE_NAME2*  nArticleName,
                                                SPFR_ARTICLE_CODE2*  cArticleCode,
                                                double               dArticleQty,
                                                FRCURRENCY           dArticleTotal,
                                                USHORT               wDepartment,
                                                USHORT               wTaxNumber,
                                                FRCURRENCY*          dpReceiptTotal);

//--------------------------------------------------------------------------------------//
// SPFR_AddArticleEx3                          [414]
//         Inserts new article into document
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
//         wTaxNumber           ID of VAT
//         sEAN                 EAN 
// [out]   
//         dpReceiptTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddArticleEx3(HANDLE               hFR,
                                                SPFR_ARTICLE_NAME2*  nArticleName,
                                                SPFR_ARTICLE_CODE2*  cArticleCode,
                                                double               dArticleQty,
                                                FRCURRENCY           dArticleTotal,
                                                USHORT               wDepartment,
                                                USHORT               wTaxNumber,
												SPFR_ARTICLE_EAN*    sEAN,
                                                FRCURRENCY*          dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_StornoArticle                       [OLD]
//         Reverses an article entry
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article or barcode
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_StornoArticle(HANDLE             hFR,
                                                SPFR_ARTICLE_NAME* nArticleName,
                                                SPFR_ARTICLE_CODE* cArticleCode,
                                                FRCURRENCY         dArticleQty,
                                                FRCURRENCY         dArticleTotal,
                                                USHORT             wDepartment,
                                                FRCURRENCY*        dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_StornoArticleEx                     [OLD]
//         Reverses an article entry
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article or barcode
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_StornoArticleEx(HANDLE               hFR,
                                                  SPFR_ARTICLE_NAME*   nArticleName,
                                                  SPFR_ARTICLE_CODE*   cArticleCode,
                                                  double               dArticleQty,
                                                  FRCURRENCY           dArticleTotal,
                                                  USHORT               wDepartment,
                                                  USHORT               wTaxNumber,
                                                  FRCURRENCY*          dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_StornoArticleEx2                    [NEW]
//         Reverses an article entry
// [in]
//         hFr                  FR handle
//         nArticleName         Commodity name
//         cArticleCode         Article or barcode
//         dArticleQty          Commodity quantity
//         dArticleTotal        Price of commodity
//         wDepartment          Department (section) number, 0 if not to print
//         wTaxNumber           ID of VAT
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_StornoArticleEx2(HANDLE               hFR,
                                                   SPFR_ARTICLE_NAME2*   nArticleName,
                                                   SPFR_ARTICLE_CODE2*   cArticleCode,
                                                   double                dArticleQty,
                                                   FRCURRENCY            dArticleTotal,
                                                   USHORT                wDepartment,
                                                   USHORT                wTaxNumber,
                                                   FRCURRENCY*           dpReceiptTotal);

//--------------------------------------------------------------------------------------//
// SPFR_DiscountPercent                     [OLD]
//         Makes a percent discount on article/receipt
// [in]
//         hFr                  FR handle
//         nDiscountName        Discount name
//         dPercent             Discount percent
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_DiscountPercent(HANDLE               hFR,
                                                  SPFR_DISCOUNT_NAME*  nDiscountName,
                                                  FRCURRENCY           dPercent,
                                                  FRCURRENCY*          dpArticleTotal);

//--------------------------------------------------------------------------------------//
// SPFR_DiscountPercent2                    [NEW]
//         Makes a percent discount on article/receipt
// [in]
//         hFr                  FR handle
//         nDiscountName        Discount name
//         dPercent             Discount percent
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_DiscountPercent2(HANDLE                 hFR,
                                                   SPFR_DISCOUNT_NAME2*   nDiscountName,
                                                   FRCURRENCY             dPercent,
                                                   FRCURRENCY*            dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_DiscountTotal                       [OLD]
//         Makes a total discount on article/receipt
// [in]
//         hFr                  FR handle
//         nDiscountName        Discount name
//         dTotal               Discount total
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_DiscountTotal(HANDLE               hFR,
                                                SPFR_DISCOUNT_NAME*  nDiscountName,
                                                FRCURRENCY           dTotal,
                                                FRCURRENCY*          dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_DiscountTotal2                      [NEW]
//         Makes a total discount on article/receipt
// [in]
//         hFr                  FR handle
//         nDiscountName        Discount name
//         dTotal               Discount total
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_DiscountTotal2(HANDLE                hFR,
                                                 SPFR_DISCOUNT_NAME2*  nDiscountName,
                                                 FRCURRENCY            dTotal,
                                                 FRCURRENCY*           dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_ExtraPercent                        [OLD]
//         Makes a percent extra charge on article/receipt
// [in]
//         hFr                  FR handle
//         nExtraName           charge name
//         dPercent             charge percent
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ExtraPercent(HANDLE            hFR,
                                               SPFR_EXTRA_NAME*  nExtraName,
                                               FRCURRENCY        dPercent,
                                               FRCURRENCY*       dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_ExtraPercent2                       [NEW]
//         Makes a percent extra charge on article/receipt
// [in]
//         hFr                  FR handle
//         nExtraName           charge name
//         dPercent             charge percent
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ExtraPercent2(HANDLE             hFR,
                                                SPFR_EXTRA_NAME2*  nExtraName,
                                                FRCURRENCY         dPercent,
                                                FRCURRENCY*        dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_ExtraTotal                          [OLD]
//         Makes a total extra charge on article/receipt
// [in]
//         hFr                  FR handle
//         nExtraName           charge name
//         dTotal               charge total
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ExtraTotal(HANDLE            hFR,
                                             SPFR_EXTRA_NAME*  nExtraName,
                                             FRCURRENCY        dTotal,
                                             FRCURRENCY*       dpArticleTotal);

//--------------------------------------------------------------------------------------//
// SPFR_ExtraTotal2                         [NEW]
//         Makes a total extra charge on article/receipt
// [in]
//         hFr                  FR handle
//         nExtraName           charge name
//         dTotal               charge total
// [out]   
//         dpArticleTotal       Total price of current article
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ExtraTotal2(HANDLE             hFR,
                                              SPFR_EXTRA_NAME2*  nExtraName,
                                              FRCURRENCY         dTotal,
                                              FRCURRENCY*        dpArticleTotal);


//--------------------------------------------------------------------------------------//
// SPFR_SubTotal
//         Subtotal of receipt
// [in]
//         hFr                  FR handle
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_SubTotal(HANDLE         hFR,
                                           FRCURRENCY*    dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_Payment
//         Makes payment 
// [in]
//         hFr                  FR handle
//         wPaymentType         Payment type code
//         dPaymentTotal        Total money taken
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_Payment(HANDLE          hFR,
                                          USHORT          wPaymentType,
                                          FRCURRENCY      dPaymentTotal,
                                          FRCURRENCY*     dpReceiptTotal);

//--------------------------------------------------------------------------------------//
// SPFR_PaymentWithComment
//         Makes payment with comment line previously
// [in]
//         hFr                  FR handle
//         wPaymentType         Payment type code
//         dPaymentTotal        Total money taken
//         sComment             String of comment
// [out]   
//         dpReceiptTotal       Total price of receipt
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PaymentWithComment(HANDLE       hFR,
                                                     USHORT       wPaymentType,
                                                     FRCURRENCY   dPaymentTotal,
                                                     SPFR_STRING* sComment,
                                                     FRCURRENCY*  dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_CashInOut
//         Cash in/out
// [in]
//         hFr                  FR handle
//         nBanknoteName        Banknote name
//         dTotal               Total sum of cash deposited/withdrawn
// [out]   
//         dpReceiptTotal       Total sum of cash deposited/withdrawn
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CashInOut(HANDLE                hFR,
                                            SPFR_BANKNOTE_NAME*   nBanknoteName,
                                            FRCURRENCY            dTotal,
                                            FRCURRENCY*           dpReceiptTotal);


//--------------------------------------------------------------------------------------//
// SPFR_XReport
//         Prints X-report
// [in]
//         hFr                  FR handle
//         nOperatorName        Operator's code
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_XReport(HANDLE              hFR,
                                          SPFR_OPERATOR_NAME* nOperatorName);


//--------------------------------------------------------------------------------------//
// SPFR_ZReport
//         Prints Z-report, closes a fiscal day
// [in]
//         hFr                  FR handle
//         nOperatorName        Operator's code
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ZReport(HANDLE              hFR,
                                          SPFR_OPERATOR_NAME* nOperatorName);


//--------------------------------------------------------------------------------------//
// SPFR_FiscShiftReport
//         Prints shift report
// [in]
//         hFr                  FR handle
//         bIsFull              True if full format false otherwise
//         wFirstShift          Shift to begin from
//         wLastShift           Shift to end at
//         pFiscPassword        Tax official password
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_FiscShiftReport(HANDLE          hFR,
                                                  BOOL            bIsFull,
                                                  USHORT          wFirstShift,
                                                  USHORT          wLastShift,
                                                  SPFR_PASSWORD*  pFiscPassword);


//--------------------------------------------------------------------------------------//
// SPFR_FiscDateReport
//         Prints date report
// [in]
//         hFr                  FR handle
//         bIsFull              True if full format, false otherwise
//         dtFirstDate          Beginning date
//         dtLastDate           End date
//         pFiscPassword        Tax official password
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_FiscDateReport(HANDLE           hFR,
                                                 BOOL             bIsFull,
                                                 SPFRDT*          dtFirstDate,
                                                 SPFRDT*          dtLastDate,
                                                 SPFR_PASSWORD*   pFiscPassword);


//--------------------------------------------------------------------------------------//
// SPFR_EEJJournal
//         Prints EEJ journal
// [in]
//         hFr                  FR handle
//         wShiftNumber         Fiscal shift number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJJournal(HANDLE   hFR,
                                             USHORT   wShiftNumber);


//--------------------------------------------------------------------------------------//
// SPFR_JournalRead
//         Read journal
// [in]
//         hFr                  FR handle
//         wOperation           Operation
//         dwParameter          Parametr
// [out]   
//         sParam               Journal text
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_JournalRead(HANDLE   hFR,
                                              USHORT   wOperation,
                                              ULONG    dwParameter,
                                              SPFR_STRING* sParam);


//--------------------------------------------------------------------------------------//
// SPFR_JournalPrint
//         Print journal
// [in]
//         hFr                  FR handle
//         nOperatorName        Operator's code
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_JournalPrint(HANDLE   hFR,
                                               SPFR_OPERATOR_NAME* nOperatorName);

//--------------------------------------------------------------------------------------//
// SPFR_GetReceipt
//         Prints EEJ document
// [in]
//         hFr                  FR handle
//         dwKPKNumber          Document number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetReceipt(HANDLE   hFR,
                                             ULONG    dwKPKNumber);


//--------------------------------------------------------------------------------------//
// SPFR_EEJShiftReport
//         Prints EEJ shift report
// [in]
//         hFr                  FR handle
//         bIsFull              True if full format false otherwise
//         wFirstShift          Shift to begin from
//         wLastShift           Shift to end at
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJShiftReport(HANDLE   hFR,
                                                 BOOL     bIsFull,
                                                 USHORT   wFirstShift,
                                                 USHORT   wLastShift);


//--------------------------------------------------------------------------------------//
// SPFR_EEJDateReport
//         Prints EEJ date report
// [in]
//         hFr                  FR handle
//         bIsFull              True if full format, false otherwise
//         dtFirstDate          Beginning date
//         dtLastDate           End date
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJDateReport(HANDLE    hFR,
                                                BOOL      bIsFull,
                                                SPFRDT*   dtFirstDate,
                                                SPFRDT*   dtLastDate);


//--------------------------------------------------------------------------------------//
// SPFR_EEJActivationReport
//         Prints EEJ activation report
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJActivationReport(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_EEJReport
//         Prints EEJ shift report
// [in]
//         hFr                  FR handle
//         wShiftNumber         Shift number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJReport(HANDLE    hFR,
                                            USHORT    wShiftNumber);


//--------------------------------------------------------------------------------------//
// SPFR_ReadConfig
//         Reads FR configuration parameter
// [in]
//         hFr                  FR handle
//         i                    Row index
//         j                    Column index
// [out]   
//         sParam               Parameter value
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ReadConfig(HANDLE       hFR,
                                             USHORT       i,
                                             USHORT       j,
                                             SPFR_STRING* sParam);


//--------------------------------------------------------------------------------------//
// SPFR_WriteConfig
//         Writes FR configuration parameter
// [in]
//         hFr                  FR handle
//         i                    Row index
//         j                    Column index
//         sParam               Parameter value
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_WriteConfig(HANDLE       hFR,
                                              USHORT       i,
                                              USHORT       j,
                                              SPFR_STRING* sParam);

//--------------------------------------------------------------------------------------//
// SPFR_SetDate
//         Sets date and time
// [in]
//         hFr                  FR handle
//         dtNewDate            Current date
//         tmNewTime            Current time 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_SetDate(HANDLE  hFR,
                                          SPFRDT* dtNewDate,
                                          SPFRTM* tmNewTime);


//--------------------------------------------------------------------------------------//
// SPFR_GetEEJStatus
//         Requests EEJ status
// [in]
//         hFr                  FR handle
// [out]   
//         EEJStatus            Status byte
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetEEJStatus(HANDLE hFR, 
                                               LPBYTE EEJStatus);

//--------------------------------------------------------------------------------------//
// SPFR_GetSKNOStatus
//         Requests SKNO status
// [in]
//         hFr                  FR handle
// [out]   
//         SKNOStatus            Status 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetSKNOStatus(HANDLE hFR, 
                                               USHORT* SKNOStatus);
//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentShift
//         Returns current shift number
// [in]
//         hFr                  FR handle
// [out]   
//         wShiftNumber         Current shift number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentShift(HANDLE   hFR,
                                                  USHORT*  wShiftNumber);


//--------------------------------------------------------------------------------------//
// SPFR_GetLastRegShift
//         Returns last registration shift number
// [in]
//         hFr                  FR handle
// [out]   
//         wShiftNumber         Shift number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetLastRegShift(HANDLE   hFR,
                                                  USHORT*  wShiftNumber);

//--------------------------------------------------------------------------------------//
// SPFR_GetRegNumber
//         Returns FR serial number
// [in]
//         hFr                  FR handle
// [out]   
//         nRegNumber           Serial number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRegNumber(HANDLE                    hFR,
                                               SPFR_REGISTRATION_NUMBER* nRegNumber);

//--------------------------------------------------------------------------------------//
// ПОЛУЧЕНИЕ СЕРИЙНОГО НОМЕРА ККМ
//
// SPFR_GetRegNumberEx
//         Returns FR serial number
// [in]
//         hFr                  FR handle
// [out]   
//         nRegNumber           Serial number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRegNumberEx(HANDLE                    hFR,
                                               SPFR_SERIAL_NUMBER_EX* nSerialNumber);

//--------------------------------------------------------------------------------------//
// SPFR_OpenCashDrawer
//         Opens cash drawer
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_OpenCashDrawer(HANDLE hFR);

//--------------------------------------------------------------------------------------//
// SPFR_Install
//         FR installing
// [in]
//         hFr                  FR handle
//         dtNewDate            Current date
//         tmNewTime            Current time 
//         nSerialNumber        Serial number 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_Install(HANDLE              hFR,
                                          SPFRDT*             dtNewDate,
                                          SPFRTM*             dtNewTime,
                                          SPFR_SERIAL_NUMBER* nSerialNumber);

//--------------------------------------------------------------------------------------//
// SPFR_InstallEx
//         FR installing
// [in]
//         hFr                  FR handle
//         dtNewDate            Current date
//         tmNewTime            Current time 
//         nSerialNumber        Serial number 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_InstallEx(HANDLE              hFR,
                                          SPFRDT*             dtNewDate,
                                          SPFRTM*             dtNewTime,
                                          SPFR_SERIAL_NUMBER_EX* nSerialNumber);

//--------------------------------------------------------------------------------------//
// SPFR_ReadMemBlock
//         Gets memory block dump from FR
// [in]
//         hFr                  FR handle
//         sdaDataType          Memory area to read data from
//         dwByteOffset         Dump offset 
//         wByteCount           Dump bytes count (64 max) 
// [out]   
//         pbData               Memory block data 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ReadMemBlock(HANDLE hFR, SPRF_DATA_AREA tDataType,
	DWORD dwByteOffset, WORD wByteCount, BYTE *pbData);

//--------------------------------------------------------------------------------------//
// SPFR_PrintGraphics        402
// SPFR_PrintGraphicsEx      101 
//         Printes graphics
// [in]
//         hFr                  FR handle
//         wByteCount           Picture data bytes count
//         pbData               Picture data 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PrintGraphics(HANDLE hFR,
                                           USHORT wByteCount,
                                           BYTE*  pbData);
SP101FR_API USHORT __stdcall SPFR_PrintGraphicsEx(HANDLE hFR,
                                           USHORT wByteCount,
										   USHORT wWidth, 
                                           BYTE*  pbData);
//--------------------------------------------------------------------------------------//
// SPFR_PrintGraphicsFromFile     402
// SPFR_PrintGraphicsFromFileEx   101
//         Printes graphics
// [in]
//         hFr                  FR handle
//         nFileName            Picture file name
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_PrintGraphicsFromFile(HANDLE             hFR,
                                                        SPFR_LOGO_NAME*    nFileName);
SP101FR_API USHORT __stdcall SPFR_PrintGraphicsFromFileEx(HANDLE             hFR,
                                                        SPFR_FILE_NAME*    nFileName);
//--------------------------------------------------------------------------------------//
// SPFR_LoadLogo
//         Writes logotype into FR
// [in]
//         hFr                  FR handle
//         wByteCount           Picture data bytes count
//         pbData               Picture data 
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_LoadLogo(HANDLE hFR,
                                           USHORT wByteCount,
                                           BYTE*  pbData);


//--------------------------------------------------------------------------------------//
// SPFR_LoadLogoFromFile
//         Writes logotype into FR
// [in]
//         hFr                  FR handle
//         nFileName            Logotype file name
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_LoadLogoFromFile(HANDLE             hFR,
                                                   SPFR_LOGO_NAME*    nFileName);


//--------------------------------------------------------------------------------------//
// SPFR_CancelReport
//         Interrupts report execution
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CancelReport(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_AddBarcode
//         Inserts new barcode into document
// [in]
//         hFr                  FR handle
//         bBarcode             Barcode structure
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddBarcode(HANDLE           hFR,
                                             SPFR_BARCODE*    bBarcode);

//--------------------------------------------------------------------------------------//
// SPFR_AddQRCode
//         Inserts new QR code into document
// [in]
//         hFr                  FR handle
//         bQRCode              QRCode structure
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_AddQRCode(HANDLE           hFR,
                                            SPFR_QRCODE*     pQRCode);

//--------------------------------------------------------------------------------------//
// SPFR_CutAndPrint
//         Cuts receipt paper and prints next receiptheader
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_CutAndPrint(HANDLE    hFR);

//--------------------------------------------------------------------------------------//
// SPFR_EEJEmergencyShiftClose
//         Emergency EJJ shift closing
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJEmergencyShiftClose(HANDLE hFR);

//--------------------------------------------------------------------------------------//
// SPFR_EEJShowClientDisplayText
//         Shows 2 rows of text on client display
// [in]
//         hFr                  FR handle
//         tRow1                First row
//         tRow2                Second row
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_EEJShowClientDisplayText(HANDLE                  hFR,
                                                           SPFR_CLIENTDISPLAYTEXT* tRow1,
                                                           SPFR_CLIENTDISPLAYTEXT* tRow2);
//--------------------------------------------------------------------------------------//
// SPFR_ClearLogo
//         Clears logotype in FR
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ClearLogo(HANDLE hFR);


//--------------------------------------------------------------------------------------//
// SPFR_GetErrorMessageRU
//         Returns error description as a Russian language string
// [in]
//         hFr                  FR handle
//         wError               Error code
// [out]   
//         mErrorMessage        First row
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API BOOL __stdcall SPFR_GetErrorMessageRU(HANDLE              hFR,
                                                  USHORT              wError,
                                                  SPFR_ERROR_MESSAGE* mErrorMessage);


//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentDocNum
//         Returns current document number
// [in]
//         hFr                  FR handle
// [out]   
//         wDocNum              Current document number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentDocNum(HANDLE   hFR,
                                                   USHORT*  wDocNum);

//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentDocNumEx
//         Returns current document number
// [in]
//         hFr                  FR handle
// [out]   
//         pDocNumber           Current document number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentDocNumEx(HANDLE hFR, ULONG *pDocNumber);

//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentReceiptNum
//         Returns current receipt number
// [in]
//         hFr                  FR handle
// [out]   
//         wReceiptNum          Current receipt number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentReceiptNum(HANDLE   hFR,
                                                       USHORT*  wReceiptNum);

//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentReceiptNumEx
//         Returns current receipt number
// [in]
//         hFr                  FR handle
// [out]   
//         pReceiptNumber       Current receipt number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentReceiptNumEx(HANDLE hFR, ULONG *pReceiptNumber);


//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentReportNum
//         Returns current report number
// [in]
//         hFr                  FR handle
// [out]   
//         wReportNum           Current report number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentReportNum(HANDLE   hFR,
                                                      USHORT*  wReportNum);


//--------------------------------------------------------------------------------------//
// SPFR_GetCashInDrawer
//         Returns amount of cash in the cash drawer
// [in]
//         hFr                  FR handle
// [out]   
//         dCashSum             Cash in the cash drawer
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCashInDrawer(HANDLE       hFR,
                                                  FRCURRENCY*  dCashSum);


//--------------------------------------------------------------------------------------//
// SPFR_GetSumByCashtype
//         Returns amount of payment by payment type
// [in]
//         hFr                  FR handle
//         wCashType            Payment type
// [out]   
//         dCashSum             Cash by specified payment type
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetSumByCashtype(HANDLE       hFR,
                                                   USHORT       wCashType,
                                                   FRCURRENCY*  dCashSum);


//--------------------------------------------------------------------------------------//
// SPFR_GetCurrentDateTime                  [OLD]
//         Get current date time
// [in]
//         hFr                  FR handle
// [out]   
//         dtDate               date   (! year as YY)
//         dtTime               time
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentDateTime(HANDLE   hFR,
                                                     SPFRDT*  dtDate,
                                                     SPFRTM*  tmTime);


//--------------------------------------------------------------------------------------//
// SPFR_GetDateTime                         [NEW]
//         Get current date time
// [in]
//         hFr                  FR handle
// [out]   
//         dtDate               date   (year as YYYY)
//         dtTime               time
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetDateTime(HANDLE  hFR,
                                              SPFRDT* dtDate,
                                              SPFRTM* tmTime);

//--------------------------------------------------------------------------------------//
// SPFR_ReprintReceipt
//         reprints receipt
// [in]
//         hFr                  FR handle
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_ReprintReceipt(HANDLE  hFR);


//--------------------------------------------------------------------------------------//
// SPFR_SetTaxAmount
//         registers VAT amount
// [in]
//         hFr                  FR handle
//         wTaxNumber           ID of VAT
//         dTaxAmount           Amount of VAT
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_SetTaxAmount(HANDLE      hFR,
                                               USHORT      wTaxNumber,
                                               FRCURRENCY  dTaxAmount);


//--------------------------------------------------------------------------------------//
// SPFR_GetINN
//         returns number of taxes payer in Tax Office
// [in]
//         hFr                  FR handle
// [out]
//         nINN                 number of taxes payer
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetINN(HANDLE    hFR,
                                         SPFR_INN* nINN);


//--------------------------------------------------------------------------------------//
// SPFR_GetFiscNumber
//         get registration number
// [in]
//         hFr                  FR handle
// [out]
//         nFiscNum             registration number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetFiscNumber(HANDLE                     hFR,
                                                SPFR_REGISTRATION_NUMBER*  nFiscNum);


//--------------------------------------------------------------------------------------//
// SPFR_GetEEJNumber
//         get number of encrypted electronic journal
// [in]
//         hFr                  FR handle
// [out]  
//         nEEJNum              EEJ number
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetEEJNumber(HANDLE            hFR,
                                               SPFR_EEJ_NUMBER*  nEEJNum);


//--------------------------------------------------------------------------------------//
// SPFR_SetTriesNumber
//         Sets number of retries on connection error. 
//         Retries are to be done in 100ms interval. Default = 1500
// [in]
//         aTruesNumber         number of retries
//--------------------------------------------------------------------------------------//
SP101FR_API VOID __stdcall SPFR_SetTriesNumber(USHORT  wTriesNumber);


//--------------------------------------------------------------------------------------//
// SPFR_SetBaudrate
//         Sets baud rate of Fiscal Printer port. 
// [in]
//         hFr                  FR handle
//         lBaudRate            baud rate
// [ret]
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_SetBaudrate(HANDLE    hFR, 
                                              ULONG     lBaudRate);


//--------------------------------------------------------------------------------------//
// SPFR_StoreReceipt
//         Stores receipt
// [in]
//         hFr                  FR handle
//         sText                comment string for storing receipt
// [ret]
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_StoreReceipt(HANDLE        hFR,
                                               SPFR_STRING*  sText);


//--------------------------------------------------------------------------------------//
// SPFR_GetDrawerOpened
//         Get Status of cash drawer - opened or not
// [in]
//         hFr                  FR handle
// [out]
//         IsOpened
// [ret]
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetDrawerOpened(HANDLE      hFR,
                                                  LPBOOL      IsOpened);


//--------------------------------------------------------------------------------------//
// SPFR_GetRebookSumByCashtype
//         Returns amount of rebook by payment type
// [in]
//         hFr                  FR handle
//         wCashType            Payment type
// [out]   
//         dRebookSum           Rebooked by specified payment type
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRebookSumByCashtype(HANDLE       hFR,
                                                         USHORT       wCashType,
                                                         FRCURRENCY*  dRebookSum);

//--------------------------------------------------------------------------------------//
// SPFR_GetFiscalDataSerial
//			Returns fiscal memory record with serial number
// [in]
//			hFr						FR handle
//			wVersion				FR version
// [out]   
//			pSerial					Record
// [ret]   
//			Error code
//--------------------------------------------------------------------------------------//
SPFR_API USHORT __stdcall SPFR_GetFiscalDataSerial(HANDLE hFR, USHORT wVersion, BYTE *pSerial);

//--------------------------------------------------------------------------------------//
// SPFR_GetFiscalGrandTotal
//			Returns accumulated grand totals for sales and returns
// [in]
//			hFr						FR handle
//			wVersion				FR version
// [out]   
//			pGrandTotalSales		Grand total of sales
//			pGrandTotalReturns		Grand total of returns
// [ret]   
//			Error code
//--------------------------------------------------------------------------------------//
SPFR_API USHORT __stdcall SPFR_GetFiscalGrandTotal(HANDLE hFR, USHORT wVersion, FRCURRENCY *pGrandTotalSales,
	FRCURRENCY *pGrandTotalReturns);


// SPFR_GetRebookSumForAllPayments
// SPFR_GetSumForAllPayments
// SPFR_GetCurrentReceiptSums
// SPFR_GetReceiptCounters
// SPFR_GetReceiptsSums
// SPFR_GetDiscountAndExtraSums
// SPFR_GetSellTaxSums
// SPFR_GetSellPaymentsCount
// SPFR_GetRebookPaymentsCount
//         Returns different counters and sums
// [in]
//         hFr                  FR handle
// [out]   
//         sCounters            Counters
//         sSums                Sums
// [ret]   
//         Error code
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRebookSumForAllPayments(HANDLE  hFR,
                                                             SPFR_SUMS*        sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetSumForAllPayments(HANDLE       hFR,
                                                       SPFR_SUMS*   sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetCurrentReceiptSums(HANDLE                    hFR,
                                                        SPFR_CURRENTRECEIPTSUMS*  sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetReceiptCounters(HANDLE                 hFR,
                                                     SPFR_RECEIPTCOUNTERS*  sCounters);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetReceiptsSums(HANDLE              hFR,
                                                  SPFR_RECEIPTSSUMS*  sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetDiscountAndExtraSums(HANDLE                      hFR,
                                                          SPFR_DISCOUNTANDEXTRASUMS*  sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetSellTaxSums(HANDLE           hFR,
                                                 SPFR_TAXSUMS*    sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRebookTaxSums(HANDLE           hFR,
                                                   SPFR_TAXSUMS*    sSums);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetSellPaymentsCount(HANDLE               hFR,
                                                       SPFR_PAYMENTCOUNTS*  sPaymentCounts);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetRebookPaymentsCount(HANDLE               hFR,
                                                         SPFR_PAYMENTCOUNTS*  sPaymentCounts);
//--------------------------------------------------------------------------------------//
SP101FR_API USHORT __stdcall SPFR_GetAccumulatedTotal(HANDLE hFR, FRCURRENCY* dAccumulatedTotal);
SP101FR_API USHORT __stdcall SPFR_GetAccumulatedTotalEx(HANDLE hFR, FRCURRENCY* dAccumulatedTotal);
//--------------------------------------------------------------------------------------//

//Канал ЭКЛЗ
SP101FR_API USHORT __stdcall SPFR_EEJJournal_Text(HANDLE hFR, USHORT wShift, SPFR_EEJRECORD* sRow );
SP101FR_API USHORT __stdcall SPFR_EEJGetReceipt_Text(HANDLE hFR, ULONG dwKPKNumber, SPFR_EEJRECORD* sRow);
SP101FR_API USHORT __stdcall SPFR_EEJShiftReport_Text(HANDLE   hFR,
													  BOOL     bIsFull,
													  USHORT   wFirstShift,
													  USHORT   wLastShift, 
													  SPFR_EEJRECORD* sRow);

SP101FR_API USHORT __stdcall SPFR_EEJDateReport_Text(HANDLE   hFR,
													 BOOL      bIsFull,
													 SPFRDT*   dtFirstDate,
													 SPFRDT*   dtLastDate, 
													 SPFR_EEJRECORD* sRow);



SP101FR_API USHORT __stdcall SPFR_EEJReport_Text_Close(HANDLE   hFR);
SP101FR_API USHORT __stdcall SPFR_EEJReport_Text_Next(HANDLE   hFR, SPFR_EEJRECORD* sRow);

//iRow - row number. Starts with 1.
SP101FR_API USHORT __stdcall SPFR_WriteHeader(HANDLE       hFR,
											  USHORT       iRow,											  
											  SPFR_STRING44* sRow);

SP101FR_API USHORT __stdcall SPFR_WriteFooter(HANDLE       hFR,
											  USHORT       iRow,											  
											  SPFR_STRING44* sRow);


SP101FR_API USHORT __stdcall SPFR_CloseNonFiscalEx(HANDLE hFR);