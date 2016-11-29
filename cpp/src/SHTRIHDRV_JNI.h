/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class SHTRIHDRV_JNI */

#ifndef _Included_SHTRIHDRV_JNI
#define _Included_SHTRIHDRV_JNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeConnect
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeConnect
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetShortECRStatus
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetShortECRStatus
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetResultCode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetResultCode
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetEndOfPrinting
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeGetEndOfPrinting
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetKkmType
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmType
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetKkmVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetKkmVersion
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeGetLastShiftInFiscalMemory
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_SHTRIHDRV_1JNI_nativeGetLastShiftInFiscalMemory
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeBeep
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBeep
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeSetDate
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetDate
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeConfirmDate
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeConfirmDate
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeSetTime
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSetTime
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintText
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintText
  (JNIEnv *, jobject, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeBuy
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeBuy
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeSale
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeSale
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeReturnSale
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeReturnSale
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeCheckSubTotal
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCheckSubTotal
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeCloseCheck
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCloseCheck
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeCancelCheck
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeCancelCheck
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeXreport
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeXreport
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeZreport
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeZreport
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintEklzReportFullByDate
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByDate
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintEklzReportShortByDate
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByDate
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintEklzReportFullByShift
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportFullByShift
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintEklzReportShortByShift
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportShortByShift
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativePrintEklzReportControlTape
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativePrintEklzReportControlTape
  (JNIEnv *, jobject, jint);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    nativeClosePort
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_nativeClosePort
  (JNIEnv *, jobject);

/*
 * Class:     SHTRIHDRV_JNI
 * Method:    fiscal54Fz
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_SHTRIHDRV_1JNI_fiscal54Fz
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
