#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_udacity_asteroidradar_utils_NativeUtils_getNasaKey(JNIEnv *env, jobject instance) {
    return (*env)->  NewStringUTF(env, "dXVhVlA1cG5nOUk1clVveHY0OGJKaXVIaVBZV0owTzdNaE5KQ3hnOQ==");
}