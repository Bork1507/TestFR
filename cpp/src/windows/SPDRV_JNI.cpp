/**
 * Created by Bork on 29.11.2016.
 */

/*
ДИНАМИЧЕСКОЕ ПОДКЛЮЧЕНИЕ БИБЛИОТЕКИ sp101fr.dll В ПРОГРАММЕ (БИБЛИОТЕКЕ) НА C++
*/


#include "SPDRV_JNI.h"
#include <iostream>
#include <time.h>
#include "windows.h"
#include "sp101fr.h"

using namespace std;


//IDrvFR* pDrvFR = NULL;
// объявляем переменную для идентификатоpа экземпляpа модуля библиотеки
HINSTANCE hsp101fr=NULL;
HANDLE comport_fr;

int g_error = 0;

unsigned int TblUtf8[] = { /* UTF-8 */
	0xD090, 0xD091, 0xD092, 0xD093, 0xD094, 0xD095, 0xD096, 0xD097, 0xD098, 0xD099, 0xD09A, 0xD09B, 0xD09C, 0xD09D, 0xD09E, 0xD09F,
	0xD0A0, 0xD0A1, 0xD0A2, 0xD0A3, 0xD0A4, 0xD0A5, 0xD0A6, 0xD0A7, 0xD0A8, 0xD0A9, 0xD0AA, 0xD0AB, 0xD0AC, 0xD0AD, 0xD0AE, 0xD0AF,
	0xD0B0, 0xD0B1, 0xD0B2, 0xD0B3, 0xD0B4, 0xD0B5, 0xD0B6, 0xD0B7, 0xD0B8, 0xD0B9, 0xD0BA, 0xD0BB, 0xD0BC, 0xD0BD, 0xD0BE, 0xD0BF,
	0xD180, 0xD181, 0xD182, 0xD183, 0xD184, 0xD185, 0xD186, 0xD187, 0xD188, 0xD189, 0xD18A, 0xD18B, 0xD18C, 0xD18D, 0xD18E, 0xD18F,
	0xD081, 0xD191, 0xE28496
};

// _CODE_PAGE == 866
char Tbl866[] = { /*  CP866 */
	0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x8D, 0x8E, 0x8F,
	0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98, 0x99, 0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 0x9F,
	0xA0, 0xA1, 0xA2, 0xA3, 0xA4, 0xA5, 0xA6, 0xA7, 0xA8, 0xA9, 0xAA, 0xAB, 0xAC, 0xAD, 0xAE, 0xAF,
	0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7, 0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF,
	0xF0, 0xF1, 0xFC
};
// _CODE_PAGE == 1251
char Tbl1251[] = { /*  CP1251 */
	0xC0, 0xC1, 0xC2, 0xC3, 0xC4, 0xC5, 0xC6, 0xC7, 0xC8, 0xC9, 0xCA, 0xCB, 0xCC, 0xCD, 0xCE, 0xCF,
	0xD0, 0xD1, 0xD2, 0xD3, 0xD4, 0xD5, 0xD6, 0xD7, 0xD8, 0xD9, 0xDA, 0xDB, 0xDC, 0xDD, 0xDE, 0xDF,
	0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7, 0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF,
	0xF0, 0xF1, 0xF2, 0xF3, 0xF4, 0xF5, 0xF6, 0xF7, 0xF8, 0xF9, 0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF,
	0xA8, 0xB8, 0xB9
};

char convertUtf8To866( unsigned int chr /* Character code to be converted */ )
{
	char c;
	if (chr < 0x80)
	{ /* ASCII */
		c = chr;
	}
	else
	{
		for (c = 0; c < 0x67; c++)
		{
			if (chr == TblUtf8[c]) break;
		}
		c = Tbl866[c];
	}

	return c;
}
char convertUtf8To1251( unsigned int chr /* Character code to be converted */ )
{
	char c;
	if (chr < 0x80)
	{ /* ASCII */
		c = chr;
	}
	else
	{
		for (c = 0; c < 0x67; c++)
		{
			if (chr == TblUtf8[c]) break;
		}
		c = Tbl1251[c];
	}

	return c;
}

int UtfToCp866(const unsigned char *utfStr, int utfStrLength, char *cp866Str)
{
    unsigned int currentSymbol=0;
    int cp866StrLength=0;
    for(int i=0; i<utfStrLength; cp866StrLength++)
    {
        if (utfStr[i]<0x80)
        {
            cp866Str[cp866StrLength]=convertUtf8To866(utfStr[i]);
            i+=1;
        }
        else if (utfStr[i]<0xE0)
        {
            currentSymbol=utfStr[i]<<8;
            currentSymbol+=utfStr[i+1];
            cp866Str[cp866StrLength]=convertUtf8To866(currentSymbol);
            i+=2;
        }
        else if (utfStr[i]<0xF0)
        {
            currentSymbol=utfStr[i]<<16;
            currentSymbol+=utfStr[i+1]<<8;
            currentSymbol+=utfStr[i+2];
            cp866Str[cp866StrLength]=convertUtf8To866(currentSymbol);
            i+=3;
        }
        //cout << (unsigned int)cp866Str[cp866StrLength] << endl;

    }
    cp866Str[cp866StrLength]=0;
    return cp866StrLength;
}
int UtfToCp1251(const unsigned char *utfStr, int utfStrLength, char *cp1251tr)
{
    unsigned int currentSymbol=0;
    int cp1251StrLength=0;
    for(int i=0; i<utfStrLength; cp1251StrLength++)
    {
        if (utfStr[i]<0x80)
        {
            cp1251tr[cp1251StrLength]=convertUtf8To1251(utfStr[i]);
            i+=1;
        }
        else if (utfStr[i]<0xE0)
        {
            currentSymbol=utfStr[i]<<8;
            currentSymbol+=utfStr[i+1];
            cp1251tr[cp1251StrLength]=convertUtf8To1251(currentSymbol);
            i+=2;
        }
        else if (utfStr[i]<0xF0)
        {
            currentSymbol=utfStr[i]<<16;
            currentSymbol+=utfStr[i+1]<<8;
            currentSymbol+=utfStr[i+2];
            cp1251tr[cp1251StrLength]=convertUtf8To1251(currentSymbol);
            i+=3;
        }
        //cout << (unsigned int)cp866Str[cp866StrLength] << endl;

    }
    cp1251tr[cp1251StrLength]=0;
    return cp1251StrLength;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeConnect (JNIEnv *jenv, jobject jobj, jint portNumber, jint baud){

   long error = 0;
   LPBYTE osh;

    //SetConsoleCP(1251);
    //SetConsoleOutputCP(1251);
    //setlocale(LC_ALL,"Russian");
    //setlocale(LC_CTYPE, "rus");

    //char pbuft[50] = "sp101fr.dll";
    char pbuft[50] = ".\\java\\lib\\windows\\sp101fr.dll";

    // подключаем библиотеку DLL (получаем идентификатоp экземпляpа модуля библиотеки)
    hsp101fr=LoadLibrary(pbuft);

    // проверяем подключение
    if(hsp101fr==NULL) {
    	cout<<"error loading dll\n";
    	return 1;
    }

    // в нашей DLL есть такая функция - HANDLE SPFR_CheckPort (int, LPBYTE*);
    // создаем прототип функции
    typedef HANDLE (WINAPI *SPFR_CheckPortEx)(int, UINT, LPBYTE *);  //SPFR_CheckPortEx и будет ее прототипом
    //Объявляем указатель на фукцию DLL
    SPFR_CheckPortEx pfnSPFR_CheckPortEx;
    //теперь получаем адрес функции в DLL
    pfnSPFR_CheckPortEx=(SPFR_CheckPortEx)GetProcAddress(hsp101fr,"SPFR_CheckPortEx");

//    typedef HANDLE (WINAPI *SPFR_CheckPort)(int, LPBYTE *);  //SPFR_CheckPort и будет ее прототипом
//    SPFR_CheckPort pfnSPFR_CheckPort;
//    pfnSPFR_CheckPort=(SPFR_CheckPort)GetProcAddress(hsp101fr,"SPFR_CheckPort");
//    comport_fr=(*pfnSPFR_CheckPort)(portNumber, &osh);

    //теперь вызываем функцию DLL как обычную функцию
    comport_fr=(*pfnSPFR_CheckPortEx)(portNumber, baud, &osh);

    return error;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeGetResultCode (JNIEnv *jenv, jobject jobj){
    long error = 0;
    error=g_error;
    //cout << "Result GetResultCode - " << error << endl;
    return error;
}


JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeGetKkmVersion (JNIEnv *jenv, jobject jobj){
    long error = 0;
    USHORT nVersion;
    char sVersion[10];

    jstring outKkmType = jenv->NewStringUTF("");

    typedef USHORT (WINAPI *SPFR_GetVersion)(HANDLE, USHORT*);
    SPFR_GetVersion pfnSPFR_GetVersion;
    pfnSPFR_GetVersion=(SPFR_GetVersion)GetProcAddress(hsp101fr,"SPFR_GetVersion");
    error=(*pfnSPFR_GetVersion)(comport_fr, &nVersion);

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

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeInit (JNIEnv *jenv, jobject jobj){
    long error = 0;

    // объявляем необходимые переменные
    SPFRDT dtCheckDate;
    SPFRTM tmCheckTime;
    SPFR_STRING str;


    typedef USHORT (WINAPI *SPFR_Init)(HANDLE, SPFRDT*, SPFRTM*);
    SPFR_Init pfnSPFR_Init;
    pfnSPFR_Init=(SPFR_Init)GetProcAddress(hsp101fr,"SPFR_Init");

        // ДАТА
        time_t data;
        struct tm *sdata;
        data=time(NULL);
        sdata=localtime(&data);
        dtCheckDate.day=sdata->tm_mday;
        dtCheckDate.month=sdata->tm_mon+1;
        dtCheckDate.year=sdata->tm_year+1900;
        tmCheckTime.hour=sdata->tm_hour;
        tmCheckTime.minute=sdata->tm_min;
        tmCheckTime.second=sdata->tm_sec;

    //теперь вызываем функцию DLL как обычную функцию
    (*pfnSPFR_Init)(comport_fr, &dtCheckDate, &tmCheckTime);

    cout << "Работа программы завершена.\n";


    //cout << "error - " << error << endl;
    return error;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeOpenDocument (JNIEnv *jenv, jobject jobj, jint docType, jint sectionNumber, jstring operatorName, jint docNumber){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    char cp1251OperatorName[128];

    const char *utfOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    int utfOperatorNameLength = jenv->GetStringUTFLength(operatorName);
    int cp1251OperatorNameLength = UtfToCp1251((const unsigned char *)utfOperatorName, utfOperatorNameLength, cp1251OperatorName);

    strcpy(nm.s, cp1251OperatorName);

    typedef USHORT (WINAPI *SPFR_OpenReceipt)(HANDLE, USHORT, USHORT, SPFR_OPERATOR_NAME *, ULONG);
    SPFR_OpenReceipt pfnSPFR_OpenReceipt;
    pfnSPFR_OpenReceipt=(SPFR_OpenReceipt)GetProcAddress(hsp101fr,"SPFR_OpenReceipt");
    error = (*pfnSPFR_OpenReceipt)(comport_fr, docType, sectionNumber, &nm, docNumber);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;

}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintText (JNIEnv *jenv, jobject jobj, jstring text){
    long error = 0;

    SPFR_STRING spfrText;
    char cp1251Text[128];

    const char *utfText = jenv->GetStringUTFChars(text, JNI_FALSE);
    int utfTextLength = jenv->GetStringUTFLength(text);
    int cp866TextLength = UtfToCp1251((const unsigned char *)utfText, utfTextLength, cp1251Text);

    strcpy(spfrText.s, cp1251Text);

    typedef USHORT (WINAPI *SPFR_PrintString)(HANDLE, SPFR_STRING*);
    SPFR_PrintString pfnSPFR_PrintString;
    pfnSPFR_PrintString=(SPFR_PrintString)GetProcAddress(hsp101fr,"SPFR_PrintString");
    error = (*pfnSPFR_PrintString)(comport_fr, &spfrText);
    //error = (*pfnSPFR_PrintString)(comport_fr, (SPFR_STRING*)cp1251Text);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeCancelDocument (JNIEnv *jenv, jobject jobj){
    long error = 0;

    typedef USHORT (WINAPI *SPFR_BreakReceipt)(HANDLE);
    SPFR_BreakReceipt pfnSPFR_BreakReceipt;
    pfnSPFR_BreakReceipt=(SPFR_BreakReceipt)GetProcAddress(hsp101fr,"SPFR_BreakReceipt");
    error = (*pfnSPFR_BreakReceipt)(comport_fr);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeXreport (JNIEnv *jenv, jobject jobj, jstring operatorName){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    char cp1251OperatorName[128];

    const char *utfOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    int utfOperatorNameLength = jenv->GetStringUTFLength(operatorName);
    int cp1251OperatorNameLength = UtfToCp1251((const unsigned char *)utfOperatorName, utfOperatorNameLength, cp1251OperatorName);

    strcpy(nm.s, cp1251OperatorName);

    typedef USHORT (WINAPI *SPFR_XReport)(HANDLE, SPFR_OPERATOR_NAME *);
    SPFR_XReport pfnSPFR_XReport;
    pfnSPFR_XReport=(SPFR_XReport)GetProcAddress(hsp101fr,"SPFR_XReport");
    error = (*pfnSPFR_XReport)(comport_fr, &nm);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeZreport (JNIEnv *jenv, jobject jobj, jstring operatorName){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    char cp1251OperatorName[128];

    const char *utfOperatorName = jenv->GetStringUTFChars(operatorName, JNI_FALSE);
    int utfOperatorNameLength = jenv->GetStringUTFLength(operatorName);
    int cp1251OperatorNameLength = UtfToCp1251((const unsigned char *)utfOperatorName, utfOperatorNameLength, cp1251OperatorName);

    strcpy(nm.s, cp1251OperatorName);

    typedef USHORT (WINAPI *SPFR_ZReport)(HANDLE, SPFR_OPERATOR_NAME *);
    SPFR_ZReport pfnSPFR_ZReport;
    pfnSPFR_ZReport=(SPFR_ZReport)GetProcAddress(hsp101fr,"SPFR_ZReport");
    error = (*pfnSPFR_ZReport)(comport_fr, &nm);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeJournalPrint (JNIEnv *jenv, jobject jobj, jstring operatorName){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    strcpy(nm.s, "Привет");
    //strcpy(nm.s, jenv->GetStringUTFChars(operatorName, JNI_FALSE));

    typedef USHORT (WINAPI *SPFR_JournalPrint)(HANDLE, SPFR_OPERATOR_NAME *);
    SPFR_JournalPrint pfnSPFR_JournalPrint;
    pfnSPFR_JournalPrint=(SPFR_JournalPrint)GetProcAddress(hsp101fr,"SPFR_JournalPrint");
    error = (*pfnSPFR_JournalPrint)(comport_fr, &nm);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeJournalRead (JNIEnv *jenv, jobject jobj, jint operation, jint parameter){
    long error = 0;
    SPFR_STRING sParam;
    //strcpy(nm.s, "Привет");
    //strcpy(nm.s, jenv->GetStringUTFChars(operatorName, JNI_FALSE));
    jstring outJournalText = jenv->NewStringUTF("");

    typedef USHORT (WINAPI *SPFR_JournalRead)(HANDLE, USHORT, ULONG, SPFR_STRING *);
    SPFR_JournalRead pfnSPFR_JournalRead;
    pfnSPFR_JournalRead=(SPFR_JournalRead)GetProcAddress(hsp101fr,"SPFR_JournalRead");
    error = (*pfnSPFR_JournalRead)(comport_fr, operation, parameter, &sParam);

    if (error == 0)
    {
        outJournalText = jenv->NewStringUTF(sParam.s);
        g_error = 0;
    }
    else
    {
        g_error = error;
    }

    return outJournalText;
}
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeClosePort (JNIEnv *jenv, jobject jobj){
    long error = 0;

    typedef void (WINAPI *SPFR_ReleasePort)(HANDLE);
    SPFR_ReleasePort pfnSPFR_ReleasePort;
    pfnSPFR_ReleasePort=(SPFR_ReleasePort)GetProcAddress(hsp101fr,"SPFR_ReleasePort");

    (*pfnSPFR_ReleasePort)(comport_fr);

    // отключаем библиотеку
    FreeLibrary(hsp101fr);

    return error;
}
