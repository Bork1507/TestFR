/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class SPDRV_JNI */

#ifndef _Included_SPDRV_JNI
#define _Included_SPDRV_JNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     SPDRV_JNI
 * Method:    nativeConnect
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeConnect
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetShortECRStatus
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeGetShortECRStatus
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetResultCode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeGetResultCode
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetEndOfPrinting
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeGetEndOfPrinting
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetKkmType
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeGetKkmType
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetKkmVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeGetKkmVersion
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeGetLastShiftInFiscalMemory
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeGetLastShiftInFiscalMemory
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeInit
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeInit
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeSetDate
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeSetDate
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeConfirmDate
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeConfirmDate
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeSetTime
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeSetTime
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintText
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintText
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeOpenDocument
 * Signature: (IILjava/lang/String;I)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeOpenDocument
  (JNIEnv *, jobject, jint, jint, jstring, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeBuy
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeBuy
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeSale
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeSale
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeReturnSale
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeReturnSale
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeCheckSubTotal
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeCheckSubTotal
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeCloseCheck
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeCloseCheck
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeCancelDocument
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeCancelDocument
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeXreport
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeXreport
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeZreport
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeZreport
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeJournalPrint
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeJournalPrint
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeJournalRead
 * Signature: (II)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SPDRV_1JNI_nativeJournalRead
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintEklzReportFullByDate
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintEklzReportFullByDate
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintEklzReportShortByDate
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintEklzReportShortByDate
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintEklzReportFullByShift
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintEklzReportFullByShift
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintEklzReportShortByShift
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintEklzReportShortByShift
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativePrintEklzReportControlTape
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativePrintEklzReportControlTape
  (JNIEnv *, jobject, jint);

/*
 * Class:     SPDRV_JNI
 * Method:    nativeClosePort
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_nativeClosePort
  (JNIEnv *, jobject);

/*
 * Class:     SPDRV_JNI
 * Method:    fiscal54Fz
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SPDRV_1JNI_fiscal54Fz
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif