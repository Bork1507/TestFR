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

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeXreport (JNIEnv *jenv, jobject jobj){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    strcpy(nm.s, "Привет");

    typedef USHORT (WINAPI *SPFR_XReport)(HANDLE, SPFR_OPERATOR_NAME *);
    SPFR_XReport pfnSPFR_XReport;
    pfnSPFR_XReport=(SPFR_XReport)GetProcAddress(hsp101fr,"SPFR_XReport");
    error = (*pfnSPFR_XReport)(comport_fr, &nm);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
}

JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeZreport (JNIEnv *jenv, jobject jobj){
    long error = 0;
    SPFR_OPERATOR_NAME nm;
    strcpy(nm.s, "Привет");

    typedef USHORT (WINAPI *SPFR_ZReport)(HANDLE, SPFR_OPERATOR_NAME *);
    SPFR_ZReport pfnSPFR_ZReport;
    pfnSPFR_ZReport=(SPFR_ZReport)GetProcAddress(hsp101fr,"SPFR_ZReport");
    error = (*pfnSPFR_ZReport)(comport_fr, &nm);

    if (error == 0) g_error = 0;
    else g_error = error;

    return error;
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
