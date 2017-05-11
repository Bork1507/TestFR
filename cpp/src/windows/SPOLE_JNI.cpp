/**
 * Created by Bork on 02.12.2016.
 */

#include "SPOLE_JNI.h"
#include <windows.h>
#include <tchar.h>
#include <iostream>
#include "time.h"
#include "SP101FRKLib.h"
#include "WTypes.h"


using namespace std;

SPOleFR* pOleFR = NULL;

int g_error = 0;

double time_tToDATE(struct tm inDateTime){

    time_t intputTime = mktime( &inDateTime );
    intputTime+=(3600*3); //I do not know why the number of seconds less than 3 hours


    unsigned int quantityOfDays = 25569 + intputTime/86400; //25569 days from 30.12.1899(DATE) to 01.01.1970(time_t)
    unsigned int secondsInDay = intputTime%86400; // 86400 seconds in day
    double resultPartOfDay = secondsInDay;
    resultPartOfDay=resultPartOfDay/86400;
    long double resultDate = quantityOfDays+resultPartOfDay;

//    cout << "intputTime = " << intputTime << " quantityOfDays = " << quantityOfDays << "  secondsInDay = " << secondsInDay << " resultPartOfDay = " << fixed << resultPartOfDay << " resultDate = " << fixed << resultDate << endl;

    return resultDate;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeConnect (JNIEnv *jenv, jobject jobj, jint portNumber, jint baud){
    if ( ( CoInitialize( NULL ))!=S_OK)
    {
       cout << "Unable to initialize COM" << endl;
       return -1;
    }

   char* szProgID = (char*)"SP101FRKLib.SP101FRObject";
   WCHAR  szWideProgID[128];
   CLSID  clsid;
   long lLen = MultiByteToWideChar( CP_ACP,
                        0,
                        szProgID,
                        strlen( szProgID ),
                        szWideProgID,
                        sizeof( szWideProgID ) );

   szWideProgID[ lLen ] = '\0';

   HRESULT hr = ::CLSIDFromProgID( szWideProgID, &clsid );
   if ( FAILED( hr ))
   {
      cout.setf( ios::hex, ios::basefield );
      cout << "Unable to get CLSID from ProgID. HR = " << hr << endl;
      return -1;
   }

   IClassFactory* pCF;
   // Получить фабрику классов для класса DrvFR
   hr = CoGetClassObject( clsid,
                          CLSCTX_INPROC,
                          NULL,
                          IID_IClassFactory,
                          (void**) &pCF );
   if ( FAILED( hr ))
   {
      cout.setf( ios::hex, ios::basefield );
      cout << "Failed to GetClassObject server instance. HR = " << hr << endl;
      return -1;
   }

   // с помощью фабрики классов создать экземпляр
   // компонента и получить интерфейс IUnknown.
   IUnknown* pUnk;
   hr = pCF->CreateInstance( NULL, IID_IUnknown, (void**) &pUnk );

   // Release the class factory
   pCF->Release();

   if ( FAILED( hr ))
   {
      cout.setf( ios::hex, ios::basefield );
      cout << "Failed to create server instance. HR = " << hr << endl;
      return -1;
   }

   hr = pUnk->QueryInterface( IID_ISP101FRObject, (LPVOID*)&pOleFR );
   pUnk->Release();
   if ( FAILED( hr ))
   {
      cout << "QueryInterface() for IMath failed" << endl;
      return -1;
   }

   short error = 0;

   error=pOleFR->ConnectEx(portNumber, baud);

   return error;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetResultCode (JNIEnv *jenv, jobject jobj){
    long error = 0;
    error=g_error;
    //cout << "Result GetResultCode - " << error << endl;
    return error;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeCutAndPrint (JNIEnv *jenv, jobject jobj){
    short error = 0;
    error=pOleFR->CutAndPrint();
    //cout << "Error CutAndPrint - " << error << endl;
    return error;
}

JNIEXPORT jstring JNICALL Java_SPOLE_1JNI_nativeGetKkmVersion (JNIEnv *jenv, jobject jobj){
    short error = 0;
    short nVersion;
    char sVersion[10];

    jstring outKkmType = jenv->NewStringUTF("");

    error=pOleFR->GetVersion(&nVersion);

    //cout << "nVersion - " << nVersion << endl;
    if (error == 0)
    {
        itoa(nVersion, sVersion, 10);
        outKkmType = jenv->NewStringUTF(sVersion);
        g_error = 0;
    }
    else
    {
        g_error = error;
    }

    return outKkmType;
}

JNIEXPORT jstring JNICALL Java_SPOLE_1JNI_nativeGetSerialNumber (JNIEnv *jenv, jobject jobj){
    short error = 0;
    BSTR bstrResult;
    char cResult[100];
    for (int i=0;i<100;i++)cResult[i]=0;

    //if (error == 0) error=pOleFR->GetFiscNumber(&bstrResult);
    //if (error == 0) error=pOleFR->GetRegNumber(&bstrResult);
    if (error == 0) error=pOleFR->GetRegNumberEx(&bstrResult);

    g_error = error;

    WideCharToMultiByte(CP_UTF8, 0, bstrResult, -1, cResult, 100, NULL, NULL);
    return jenv->NewStringUTF(cResult);
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetPrinterStatus (JNIEnv *jenv, jobject jobj){
    short error = 0;
    long result = 0;

    if (error == 0) error=pOleFR->GetPrinterStatus(&result);

    g_error = error;

    return result;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeInit (JNIEnv *jenv, jobject jobj, jstring inputDate, jstring inputTime){
    long error = 0;

    const char *nativeStringDate = jenv->GetStringUTFChars(inputDate, JNI_FALSE);
    const char *nativeStringTime = jenv->GetStringUTFChars(inputTime, JNI_FALSE);

    char chDay[3]={0,0,0};
    char chMonth[3]={0,0,0};
    char chYear[3]={0,0,0};
    char chHour[3]={0,0,0};
    char chMin[3]={0,0,0};
    char chSec[3]={0,0,0};
    strncat(chDay, nativeStringDate, 2);
    strncat(chMonth, nativeStringDate+2, 2);
    strncat(chYear, nativeStringDate+4, 2);
    strncat(chHour, nativeStringTime, 2);
    strncat(chMin, nativeStringTime+2, 2);
    strncat(chSec, nativeStringTime+4, 2);

    struct tm timeinfo;
    timeinfo.tm_sec=atoi(chSec);
    timeinfo.tm_min=atoi(chMin);
    timeinfo.tm_hour=atoi(chHour);
    timeinfo.tm_mday=atoi(chDay);
    timeinfo.tm_mon=atoi(chMonth)-1;
    timeinfo.tm_year=atoi(chYear)+100;

    if (error == 0) error=pOleFR->Init(time_tToDATE(timeinfo));
    //cout << "error SetDate - " << error << endl;
    return error;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeInstall (JNIEnv *jenv, jobject jobj, jstring inputDate, jstring inputTime, jstring serialNumber){
    long error = 0;

    const char *nativeStringDate = jenv->GetStringUTFChars(inputDate, JNI_FALSE);
    const char *nativeStringTime = jenv->GetStringUTFChars(inputTime, JNI_FALSE);

    char chDay[3]={0,0,0};
    char chMonth[3]={0,0,0};
    char chYear[3]={0,0,0};
    char chHour[3]={0,0,0};
    char chMin[3]={0,0,0};
    char chSec[3]={0,0,0};
    strncat(chDay, nativeStringDate, 2);
    strncat(chMonth, nativeStringDate+2, 2);
    strncat(chYear, nativeStringDate+4, 2);
    strncat(chHour, nativeStringTime, 2);
    strncat(chMin, nativeStringTime+2, 2);
    strncat(chSec, nativeStringTime+4, 2);

    struct tm timeinfo;
    timeinfo.tm_sec=atoi(chSec);
    timeinfo.tm_min=atoi(chMin);
    timeinfo.tm_hour=atoi(chHour);
    timeinfo.tm_mday=atoi(chDay);
    timeinfo.tm_mon=atoi(chMonth)-1;
    timeinfo.tm_year=atoi(chYear)+100;

    const char *nativeSerialNumber = jenv->GetStringUTFChars(serialNumber, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeSerialNumber, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeSerialNumber, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->Install(time_tToDATE(timeinfo), MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeInstallEx (JNIEnv *jenv, jobject jobj, jstring inputDate, jstring inputTime, jstring serialNumber){
    long error = 0;

    const char *nativeStringDate = jenv->GetStringUTFChars(inputDate, JNI_FALSE);
    const char *nativeStringTime = jenv->GetStringUTFChars(inputTime, JNI_FALSE);

    char chDay[3]={0,0,0};
    char chMonth[3]={0,0,0};
    char chYear[3]={0,0,0};
    char chHour[3]={0,0,0};
    char chMin[3]={0,0,0};
    char chSec[3]={0,0,0};
    strncat(chDay, nativeStringDate, 2);
    strncat(chMonth, nativeStringDate+2, 2);
    strncat(chYear, nativeStringDate+4, 2);
    strncat(chHour, nativeStringTime, 2);
    strncat(chMin, nativeStringTime+2, 2);
    strncat(chSec, nativeStringTime+4, 2);

    struct tm timeinfo;
    timeinfo.tm_sec=atoi(chSec);
    timeinfo.tm_min=atoi(chMin);
    timeinfo.tm_hour=atoi(chHour);
    timeinfo.tm_mday=atoi(chDay);
    timeinfo.tm_mon=atoi(chMonth)-1;
    timeinfo.tm_year=atoi(chYear)+100;

    const char *nativeSerialNumber = jenv->GetStringUTFChars(serialNumber, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeSerialNumber, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeSerialNumber, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->InstallEx(time_tToDATE(timeinfo), MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}

//JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetShortECRStatus (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//
//    long mode=999999999;
//    long submode=999999999;
//
//    if (error == 0) error=pDrvFR->GetShortECRStatus(); // Short Querry
//    if (error == 0) error=pDrvFR->get_ECRMode(&mode);
//    if (error == 0) error=pDrvFR->get_ECRAdvancedMode(&submode);
//
//    char cResult[100];
//    for (int i=0; i<100;i++) cResult[i]=0;
//
//
//    if (error == 0)  sprintf (cResult,  "mode - %d; submode - %d;", mode, submode);
//    jstring outShortECRStatus = jenv->NewStringUTF(cResult);
//
//    return outShortECRStatus;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetResultCode (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//    error=pDrvFR->get_ResultCode();
//    //cout << "Result GetResultCode - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetEndOfPrinting (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//
//    long mode=999999999;
//    long submode=999999999;
//    while (mode != 0) {
//        //error=pDrvFR->GetECRStatus(); Long Querry
//        error=pDrvFR->GetShortECRStatus(); // Short Querry
//        error=pDrvFR->get_ECRMode(&mode);
//        error=pDrvFR->get_ECRAdvancedMode(&submode);
//
//        if (error != 0) break;
//
//        cout << "mode - " << mode << "submode - " << submode << endl;
//
//        if (submode==0)
//        {
//          if ((mode!=11)&&(mode!=12)) break;
//        }
//        else if ((submode==1)||(submode==2)||(submode==3))
//        {
//          error=107;
//          break;
//        }
//    }
//
//    //cout << "error GetEndOfPrinting - " << error << endl;
//    return error;
//
//}
//
//JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmType (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//    BSTR bstrKKMType;//=SysAllocString(L"Проверка инициализации русскими символами");
//
//    char cResult[30];
//    for (int i=0; i<30;i++) cResult[i]=0;
//
//    error=pDrvFR->GetDeviceMetrics();
//    if (error == 0) error=pDrvFR->get_UDescription(&bstrKKMType);
//    //int rat = wcstombs(cResult, bstrKKMType, 30 ); // not support russian codepage
//    WideCharToMultiByte(CP_UTF8, 0, bstrKKMType, -1, cResult, 30, NULL, NULL);
//    jstring outKkmType = jenv->NewStringUTF(cResult);
//
//    //cout << "error GetKkmType - " << error << endl;
//    return outKkmType;
//}
//
//JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmVersion (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//
//    BSTR bstrECRSoftVersion;
//    BSTR bstrFMSoftVersion;
//
//    long ECRBuild=0;
//    long FMBuild=0;
//    double ECRSoftDate=0;
//    double FMSoftDate=0;
//
//    char cResult[100];
//    for (int i=0; i<100;i++) cResult[i]=0;
//    char cECRSoftVersion[10];
//    for (int i=0; i<10;i++) cECRSoftVersion[i]=0;
//    char cFMSoftVersion[10];
//    for (int i=0; i<10;i++) cFMSoftVersion[i]=0;
//
//    char cECRSoftDate[20];
//    for (int i=0; i<20;i++) cFMSoftVersion[i]=0;
//    char cFMSoftDate[20];
//    for (int i=0; i<20;i++) cFMSoftVersion[i]=0;
//
//    error=pDrvFR->GetECRStatus();
//    if (error == 0) error=pDrvFR->get_ECRSoftVersion(&bstrECRSoftVersion);
//    if (error == 0) pDrvFR->get_ECRBuild(&ECRBuild);
//    if (error == 0) pDrvFR->get_ECRSoftDate(&ECRSoftDate);
//    if (error == 0) error=pDrvFR->get_FMSoftVersion(&bstrFMSoftVersion);
//    if (error == 0) pDrvFR->get_FMBuild(&FMBuild);
//    if (error == 0) pDrvFR->get_FMSoftDate(&FMSoftDate);
//
//    struct tm *tmSoftDate;
//    time_t dateECRSoftDate=(((int)ECRSoftDate)-25569)*86400;
//    time_t dateFMSoftDate=(((int)FMSoftDate)-25569)*86400;
//
//    tmSoftDate = localtime(&dateECRSoftDate);
//    sprintf (cECRSoftDate, "%02d.%02d.%d", tmSoftDate->tm_mday, tmSoftDate->tm_mon+1, tmSoftDate->tm_year+1900);
//    tmSoftDate = localtime(&dateFMSoftDate);
//    sprintf (cFMSoftDate, "%02d.%02d.%d", tmSoftDate->tm_mday, tmSoftDate->tm_mon+1, tmSoftDate->tm_year+1900);
//
//
//    //wcstombs(cECRSoftVersion, bstrECRSoftVersion, 100 ); // not support russian codepage
//    //wcstombs(cFMSoftVersion, bstrFMSoftVersion, 100 ); // not support russian codepage
//    WideCharToMultiByte(CP_UTF8, 0, bstrECRSoftVersion, -1, cECRSoftVersion, 10, NULL, NULL);
//    WideCharToMultiByte(CP_UTF8, 0, bstrFMSoftVersion, -1, cFMSoftVersion, 10, NULL, NULL);
//
//    sprintf (cResult, "ПО_ФР %s-%d-%s /ПО_ФП %s-%d-%s",
//             cECRSoftVersion, ECRBuild, cECRSoftDate,
//             cFMSoftVersion, FMBuild, cFMSoftDate
//             );
//
//    jstring outKkmVersion = jenv->NewStringUTF(cResult);
//
//    return outKkmVersion;
//}
//
//JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetLastShiftInFiscalMemory (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//    long result;
//
//    char cResult[30];
//    for (int i=0; i<30;i++) cResult[i]=0;
//
//    error=pDrvFR->GetECRStatus();
//    if (error == 0) error=pDrvFR->get_SessionNumber(&result);
//
//    itoa(result, cResult, 10);
//    jstring outKkmType = jenv->NewStringUTF(cResult);
//
//    //cout << "error GetLastShiftInFiscalMemor - " << error << endl;
//    return outKkmType;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBeep (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//    error=pDrvFR->Beep();
//    //cout << "error - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetDate (JNIEnv *jenv, jobject jobj, jstring inputDate){
//    long error = 0;
//
//    const char *nativeStringDate = jenv->GetStringUTFChars(inputDate, JNI_FALSE);
//
//    char chDay[3]={0,0,0};
//    char chMonth[3]={0,0,0};
//    char chYear[3]={0,0,0};
//    strncat(chDay, nativeStringDate, 2);
//    strncat(chMonth, nativeStringDate+2, 2);
//    strncat(chYear, nativeStringDate+4, 2);
//
//    struct tm timeinfo;
//    timeinfo.tm_sec=0;
//    timeinfo.tm_min=0;
//    timeinfo.tm_hour=0;
//    timeinfo.tm_mday=atoi(chDay);
//    timeinfo.tm_mon=atoi(chMonth)-1;
//    timeinfo.tm_year=atoi(chYear)+100;
//
//    if (error == 0) error=pDrvFR->set_Date(time_tToDATE(timeinfo));
//    if (error == 0) error=pDrvFR->SetDate();
//    //cout << "error SetDate - " << error << endl;
//    return error;
//
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeConfirmDate (JNIEnv *jenv, jobject jobj, jstring inputDate){
//    long error = 0;
//
//    if (error == 0) error=pDrvFR->ConfirmDate();
//    //cout << "error ConfirmDate - " << error << endl;
//    return error;
//
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetTime (JNIEnv *jenv, jobject jobj, jstring inputTime){
//    long error = 0;
//
//
//    const char *nativeStringTime = jenv->GetStringUTFChars(inputTime, JNI_FALSE);
//
//    char chHour[3]={0,0,0};
//    char chMin[3]={0,0,0};
//    char chSec[3]={0,0,0};
//    strncat(chHour, nativeStringTime, 2);
//    strncat(chMin, nativeStringTime+2, 2);
//    strncat(chSec, nativeStringTime+4, 2);
//
//    struct tm timeinfo;
//    timeinfo.tm_sec=atoi(chSec);
//    timeinfo.tm_min=atoi(chMin);
//    timeinfo.tm_hour=atoi(chHour);
//    timeinfo.tm_mday=0;
//    timeinfo.tm_mon=0;
//    timeinfo.tm_year=0+100;
//
//    if (error == 0) error=pDrvFR->set_Time(time_tToDATE(timeinfo));
//    if (error == 0) error=pDrvFR->SetTime();
//
//    //cout << "error SetTime - " << error << endl;
//
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintText (JNIEnv *jenv, jobject jobj, jstring text){
//    long error = 0;
//
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBuy (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
//    long error = 0;
//
//    //CURRENCY priceCur=static_cast<CURRENCY>(24234563800);
//    CURRENCY priceCur;
//    priceCur.Lo=atoi(jenv->GetStringUTFChars(cost, JNI_FALSE));
//    priceCur.Hi=0;
//
//    const char *nativeStringTime = jenv->GetStringUTFChars(itemName, JNI_FALSE);
//    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeStringTime, -1, NULL, 0);
//    wchar_t *pwText;
//    pwText = new wchar_t[dwNum];
//    if(!pwText) {
//        delete []pwText;
//    }
//    MultiByteToWideChar(CP_UTF8, 0, nativeStringTime, -1, pwText, dwNum);
//
//    BSTR MyBstr = SysAllocString(pwText);
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_Quantity(atof(jenv->GetStringUTFChars(qantity, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Price(priceCur);
//    if (error == 0) error=pDrvFR->set_Department(atoi(jenv->GetStringUTFChars(depType, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Tax1(0);
//    if (error == 0) error=pDrvFR->set_Tax2(0);
//    if (error == 0) error=pDrvFR->set_Tax3(0);
//    if (error == 0) error=pDrvFR->set_Tax4(0);
//    if (error == 0) error=pDrvFR->set_StringForPrinting(MyBstr);
//
//
//
//    if (error == 0) error=pDrvFR->Buy();
//    //cout << "error Buy - " << error << endl;
//
//
//    delete []pwText;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSale (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
//    long error = 0;
//
//    //CURRENCY priceCur=static_cast<CURRENCY>(24234563800);
//    CURRENCY priceCur;
//    priceCur.Lo=atoi(jenv->GetStringUTFChars(cost, JNI_FALSE));
//    priceCur.Hi=0;
//
//    const char *nativeStringTime = jenv->GetStringUTFChars(itemName, JNI_FALSE);
//    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeStringTime, -1, NULL, 0);
//    wchar_t *pwText;
//    pwText = new wchar_t[dwNum];
//    if(!pwText) {
//        delete []pwText;
//    }
//    MultiByteToWideChar(CP_UTF8, 0, nativeStringTime, -1, pwText, dwNum);
//
//    BSTR MyBstr = SysAllocString(pwText);
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_Quantity(atof(jenv->GetStringUTFChars(qantity, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Price(priceCur);
//    if (error == 0) error=pDrvFR->set_Department(atoi(jenv->GetStringUTFChars(depType, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Tax1(0);
//    if (error == 0) error=pDrvFR->set_Tax2(0);
//    if (error == 0) error=pDrvFR->set_Tax3(0);
//    if (error == 0) error=pDrvFR->set_Tax4(0);
//    if (error == 0) error=pDrvFR->set_StringForPrinting(MyBstr);
//
//
//
//    if (error == 0) error=pDrvFR->Sale();
//    //cout << "error Sale - " << error << endl;
//
//
//    delete []pwText;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeReturnSale (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
//    long error = 0;
//
//    //CURRENCY priceCur=static_cast<CURRENCY>(24234563800);
//    CURRENCY priceCur;
//    priceCur.Lo=atoi(jenv->GetStringUTFChars(cost, JNI_FALSE));
//    priceCur.Hi=0;
//
//    const char *nativeStringItemName = jenv->GetStringUTFChars(itemName, JNI_FALSE);
//    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeStringItemName, -1, NULL, 0);
//    wchar_t *pwText;
//    pwText = new wchar_t[dwNum];
//    if(!pwText) {
//        delete []pwText;
//    }
//    MultiByteToWideChar(CP_UTF8, 0, nativeStringItemName, -1, pwText, dwNum);
//    BSTR bstrItemName = SysAllocString(pwText);
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_Quantity(atof(jenv->GetStringUTFChars(qantity, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Price(priceCur);
//    if (error == 0) error=pDrvFR->set_Department(atoi(jenv->GetStringUTFChars(depType, JNI_FALSE)));
//    if (error == 0) error=pDrvFR->set_Tax1(0);
//    if (error == 0) error=pDrvFR->set_Tax2(0);
//    if (error == 0) error=pDrvFR->set_Tax3(0);
//    if (error == 0) error=pDrvFR->set_Tax4(0);
//    if (error == 0) error=pDrvFR->set_StringForPrinting(bstrItemName);
//
//
//
//    if (error == 0) error=pDrvFR->ReturnSale();
//    //cout << "error ReturnSale - " << error << endl;
//
//
//    delete []pwText;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_CheckSubTotal (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//
//    error=pDrvFR->CheckSubTotal();
//    //cout << "error CheckSubTotal - " << error << endl;
//
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_CloseCheck (JNIEnv *jenv, jobject jobj, jstring pay1, jstring pay2, jstring pay3, jstring pay4, jstring text){
//    long error = 0;
//
//    CURRENCY payCur1;
//    payCur1.Lo=atoi(jenv->GetStringUTFChars(pay1, JNI_FALSE));
//    payCur1.Hi=0;
//    CURRENCY payCur2;
//    payCur2.Lo=atoi(jenv->GetStringUTFChars(pay2, JNI_FALSE));
//    payCur2.Hi=0;
//    CURRENCY payCur3;
//    payCur3.Lo=atoi(jenv->GetStringUTFChars(pay3, JNI_FALSE));
//    payCur3.Hi=0;
//    CURRENCY payCur4;
//    payCur4.Lo=atoi(jenv->GetStringUTFChars(pay4, JNI_FALSE));
//    payCur4.Hi=0;
//
//    const char *nativeStringText = jenv->GetStringUTFChars(text, JNI_FALSE);
//    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeStringText, -1, NULL, 0);
//    wchar_t *pwText;
//    pwText = new wchar_t[dwNum];
//    if(!pwText) {
//        delete []pwText;
//    }
//    MultiByteToWideChar(CP_UTF8, 0, nativeStringText, -1, pwText, dwNum);
//    BSTR bstrText = SysAllocString(pwText);
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_Summ1(payCur1);
//    if (error == 0) error=pDrvFR->set_Summ2(payCur2);
//    if (error == 0) error=pDrvFR->set_Summ3(payCur3);
//    if (error == 0) error=pDrvFR->set_Summ4(payCur4);
//    if (error == 0) error=pDrvFR->set_DiscountOnCheck(0);
//    if (error == 0) error=pDrvFR->set_Tax1(0);
//    if (error == 0) error=pDrvFR->set_Tax2(0);
//    if (error == 0) error=pDrvFR->set_Tax3(0);
//    if (error == 0) error=pDrvFR->set_Tax4(0);
//    if (error == 0) error=pDrvFR->set_StringForPrinting(bstrText);
//
//
//    error=pDrvFR->CloseCheck();
//    //cout << "error CloseCheck - " << error << endl;
//
//    delete []pwText;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_CancelCheck (JNIEnv *jenv, jobject jobj){
//    long error = 0;
//
//    error=pDrvFR->CancelCheck();
//    //cout << "error CancelCheck - " << error << endl;
//
//    return error;
//}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeXreport (JNIEnv *jenv, jobject jobj, jstring operatorName){
    short error = 0;

    const char *nativeOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->XReport(MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZreport (JNIEnv *jenv, jobject jobj, jstring operatorName){
    short error = 0;

    const char *nativeOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->ZReport(MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeJournalPrint (JNIEnv *jenv, jobject jobj, jstring operatorName){
    short error = 0;

    const char *nativeOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->JournalPrint(MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativePrintJournal (JNIEnv *jenv, jobject jobj, jstring operatorName){
    short error = 0;

    const char *nativeOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    DWORD dwNum = MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, NULL, 0);
    wchar_t *pwText;
    pwText = new wchar_t[dwNum];
    if(!pwText) {
        delete []pwText;
    }
    MultiByteToWideChar(CP_UTF8, 0, nativeOperatorName, -1, pwText, dwNum);

    BSTR MyBstr = SysAllocString(pwText);

    if (error == 0) error=pOleFR->PrintJournal(MyBstr);

    if (error == 0) g_error = 0;
    else g_error = error;

    delete []pwText;
    return error;
}
JNIEXPORT jstring JNICALL Java_SPOLE_1JNI_nativeJournalRead (JNIEnv *jenv, jobject jobj, jint operation, jint parameter){
    short error = 0;
    BSTR bstrResult;
    char cResult[100];
    for (int i=0;i<100;i++)cResult[i]=0;

    if (error == 0) error=pOleFR->JournalRead(operation, parameter, &bstrResult);

    if (error == 0) g_error = 0;
    else g_error = error;

    WideCharToMultiByte(CP_UTF8, 0, bstrResult, -1, cResult, 100, NULL, NULL);
    return jenv->NewStringUTF(cResult);;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetJournalNumber (JNIEnv *jenv, jobject jobj){
    short error = 0;
    short journalNumber = 0;

    if (error == 0) error=pOleFR->GetJournalNumber(&journalNumber);
    //cout << "journalNumber - " << journalNumber << endl;

    if (error == 0) g_error = 0;
    else g_error = error;

    return journalNumber;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetJournalRecordCount (JNIEnv *jenv, jobject jobj){
    short error = 0;
    short RecordCount = 0;

    if (error == 0) error=pOleFR->GetJournalRecordCount(&RecordCount);

    if (error == 0) g_error = 0;
    else g_error = error;

    return RecordCount;
}
JNIEXPORT jstring JNICALL Java_SPOLE_1JNI_nativeGetJournalRecord (JNIEnv *jenv, jobject jobj, jint RecordNumber){
    short error = 0;
    BSTR bstrResult;
    char cResult[100];
    for (int i=0;i<100;i++)cResult[i]=0;

    if (error == 0) error=pOleFR->GetJournalRecord(RecordNumber, &bstrResult);

    if (error == 0) g_error = 0;
    else g_error = error;

    WideCharToMultiByte(CP_UTF8, 0, bstrResult, -1, cResult, 100, NULL, NULL);
    return jenv->NewStringUTF(cResult);;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetJournalReceiptByIndex (JNIEnv *jenv, jobject jobj, jint ReceiptIndex){
    short error = 0;
    short RecordNumber = 0;

    if (error == 0) error=pOleFR->GetJournalReceiptByIndex(ReceiptIndex, &RecordNumber);

    if (error == 0) g_error = 0;
    else g_error = error;

    return RecordNumber;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeGetJournalReceiptByNumber (JNIEnv *jenv, jobject jobj, jint ReceiptNumber){
    short error = 0;
    short RecordNumber = 0;

    if (error == 0) error=pOleFR->GetJournalReceiptByNumber(ReceiptNumber, &RecordNumber);

    if (error == 0) g_error = 0;
    else g_error = error;

    return RecordNumber;
}


//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByDate (JNIEnv *jenv, jobject jobj, jstring from, jstring to){
//    long error = 0;
//
//    const char *nativeStringFromDate = jenv->GetStringUTFChars(from, JNI_FALSE);
//
//    char chFromDay[3]={0,0,0};
//    char chFromMonth[3]={0,0,0};
//    char chFromYear[3]={0,0,0};
//    strncat(chFromDay, nativeStringFromDate, 2);
//    strncat(chFromMonth, nativeStringFromDate+2, 2);
//    strncat(chFromYear, nativeStringFromDate+4, 2);
//
//    struct tm dateFrom;
//    dateFrom.tm_sec=0;
//    dateFrom.tm_min=0;
//    dateFrom.tm_hour=0;
//    dateFrom.tm_mday=atoi(chFromDay);
//    dateFrom.tm_mon=atoi(chFromMonth)-1;
//    dateFrom.tm_year=atoi(chFromYear)+100;
//
//
//    const char *nativeStringToDate = jenv->GetStringUTFChars(to, JNI_FALSE);
//
//    char chToDay[3]={0,0,0};
//    char chToMonth[3]={0,0,0};
//    char chToYear[3]={0,0,0};
//    strncat(chToDay, nativeStringToDate, 2);
//    strncat(chToMonth, nativeStringToDate+2, 2);
//    strncat(chToYear, nativeStringToDate+4, 2);
//
//    struct tm dateTo;
//    dateTo.tm_sec=0;
//    dateTo.tm_min=0;
//    dateTo.tm_hour=0;
//    dateTo.tm_mday=atoi(chToDay);
//    dateTo.tm_mon=atoi(chToMonth)-1;
//    dateTo.tm_year=atoi(chToYear)+100;
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_ReportType(TRUE);
//    if (error == 0) error=pDrvFR->set_FirstSessionDate(time_tToDATE(dateFrom));
//    if (error == 0) error=pDrvFR->set_LastSessionDate(time_tToDATE(dateTo));
//
//    if (error == 0) error=pDrvFR->EKLZSessionReportInDatesRange();
//    //cout << "error PrintEklzReportFullByShift - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByDate (JNIEnv *jenv, jobject jobj, jstring from, jstring to){
//    long error = 0;
//
//    const char *nativeStringFromDate = jenv->GetStringUTFChars(from, JNI_FALSE);
//
//    char chFromDay[3]={0,0,0};
//    char chFromMonth[3]={0,0,0};
//    char chFromYear[3]={0,0,0};
//    strncat(chFromDay, nativeStringFromDate, 2);
//    strncat(chFromMonth, nativeStringFromDate+2, 2);
//    strncat(chFromYear, nativeStringFromDate+4, 2);
//
//    struct tm dateFrom;
//    dateFrom.tm_sec=0;
//    dateFrom.tm_min=0;
//    dateFrom.tm_hour=0;
//    dateFrom.tm_mday=atoi(chFromDay);
//    dateFrom.tm_mon=atoi(chFromMonth)-1;
//    dateFrom.tm_year=atoi(chFromYear)+100;
//
//
//    const char *nativeStringToDate = jenv->GetStringUTFChars(to, JNI_FALSE);
//
//    char chToDay[3]={0,0,0};
//    char chToMonth[3]={0,0,0};
//    char chToYear[3]={0,0,0};
//    strncat(chToDay, nativeStringToDate, 2);
//    strncat(chToMonth, nativeStringToDate+2, 2);
//    strncat(chToYear, nativeStringToDate+4, 2);
//
//    struct tm dateTo;
//    dateTo.tm_sec=0;
//    dateTo.tm_min=0;
//    dateTo.tm_hour=0;
//    dateTo.tm_mday=atoi(chToDay);
//    dateTo.tm_mon=atoi(chToMonth)-1;
//    dateTo.tm_year=atoi(chToYear)+100;
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_ReportType(TRUE);
//    if (error == 0) error=pDrvFR->set_FirstSessionDate(time_tToDATE(dateFrom));
//    if (error == 0) error=pDrvFR->set_LastSessionDate(time_tToDATE(dateTo));
//
//    if (error == 0) error=pDrvFR->EKLZSessionReportInDatesRange();
//    //cout << "error PrintEklzReportFullByShift - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByShift (JNIEnv *jenv, jobject jobj, jint from, jint to){
//    long error = 0;
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_ReportType(TRUE);
//    if (error == 0) error=pDrvFR->set_FirstSessionNumber((int)from);
//    if (error == 0) error=pDrvFR->set_LastSessionNumber((int)to);
//
//    if (error == 0) error=pDrvFR->EKLZSessionReportInSessionsRange();
//    //cout << "error PrintEklzReportFullByShift - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByShift (JNIEnv *jenv, jobject jobj, jint from, jint to){
//    long error = 0;
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_ReportType(FALSE);
//    if (error == 0) error=pDrvFR->set_FirstSessionNumber((int)from);
//    if (error == 0) error=pDrvFR->set_LastSessionNumber((int)to);
//
//    if (error == 0) error=pDrvFR->EKLZSessionReportInSessionsRange();
//    //cout << "error PrintEklzReportShortByShift - " << error << endl;
//    return error;
//}
//
//JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportControlTape (JNIEnv *jenv, jobject jobj, jint shift){
//    long error = 0;
//
//    if (error == 0) error=pDrvFR->set_Password(30);
//    if (error == 0) error=pDrvFR->set_SessionNumber((int)shift);
//
//    if (error == 0) error=pDrvFR->EKLZJournalOnSessionNumber();
//    //cout << "error PrintEklzReportControlTape - " << error << endl;
//    return error;
//}
//

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopy (JNIEnv *jenv, jobject jobj, jint operation, jint parameter)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopy(operation, parameter);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopyClear (JNIEnv *jenv, jobject jobj)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopyClear();

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopyPrintRegistration (JNIEnv *jenv, jobject jobj)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopyPrintRegistration();

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopyPrintAll (JNIEnv *jenv, jobject jobj)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopyPrintAll();

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopyPrintByOrderNumber (JNIEnv *jenv, jobject jobj, jint OrderNumber)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopyPrintByOrderNumber(OrderNumber);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeZCopyPrintByShiftNumber (JNIEnv *jenv, jobject jobj, jint ShiftNumber)
{
    short error = 0;

    if (error == 0) error=pOleFR->ZCopyPrintByShiftNumber(ShiftNumber);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}

JNIEXPORT jint JNICALL Java_SPOLE_1JNI_nativeClosePort (JNIEnv *jenv, jobject jobj){
    long error = 0;

    //pOleFR->Disconnect(void);
    //cout << "error Disconnect - " << error << endl;

    CoUninitialize();

    return error;
}
