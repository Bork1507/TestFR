/*
### This is the code - blind stopper!
*/
#include <iostream>
#include "SHTRIHDRV_JNI.h"

#define BLIND_STOPPER "This is the code - blind stopper!"

using namespace std;

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeConnect (JNIEnv *jenv, jobject jobj, jint portNumber, jint baud){
   long error = 0;

   cout << BLIND_STOPPER << endl;

   return error;
}

JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetShortECRStatus (JNIEnv *jenv, jobject jobj){
    long error = 0;

    jstring outShortECRStatus = jenv->NewStringUTF(BLIND_STOPPER);;

    return outShortECRStatus;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetResultCode (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetEndOfPrinting (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;

}

JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmType (JNIEnv *jenv, jobject jobj){
    long error = 0;

    jstring outKkmType = jenv->NewStringUTF(BLIND_STOPPER);;

    return outKkmType;
}

JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmVersion (JNIEnv *jenv, jobject jobj){
    long error = 0;

    jstring outKkmVersion = jenv->NewStringUTF(BLIND_STOPPER);;

    return outKkmVersion;
}

JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetLastShiftInFiscalMemory (JNIEnv *jenv, jobject jobj){
    long error = 0;

    jstring outKkmType = jenv->NewStringUTF(BLIND_STOPPER);

    return outKkmType;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBeep (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetDate (JNIEnv *jenv, jobject jobj, jstring inputDate){
    long error = 0;

    return error;

}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeConfirmDate (JNIEnv *jenv, jobject jobj, jstring inputDate){
    long error = 0;

    return error;

}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetTime (JNIEnv *jenv, jobject jobj, jstring inputTime){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintText (JNIEnv *jenv, jobject jobj, jstring text){
    long error = 0;

    return error;

}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBuy (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSale (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
    long error = 0;
    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeReturnSale (JNIEnv *jenv, jobject jobj, jstring itemName, jstring articul, jstring qantity, jstring cost, jstring depType, jstring taxType){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCheckSubTotal (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCloseCheck (JNIEnv *jenv, jobject jobj, jstring pay1, jstring pay2, jstring pay3, jstring pay4, jstring text){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCancelCheck (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeXreport (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeZreport (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByDate (JNIEnv *jenv, jobject jobj, jstring from, jstring to){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByDate (JNIEnv *jenv, jobject jobj, jstring from, jstring to){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByShift (JNIEnv *jenv, jobject jobj, jint from, jint to){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByShift (JNIEnv *jenv, jobject jobj, jint from, jint to){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportControlTape (JNIEnv *jenv, jobject jobj, jint shift){
    long error = 0;

    return error;
}

JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeClosePort (JNIEnv *jenv, jobject jobj){
    long error = 0;

    return error;
}
