#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" {


#include "ffmpeg.h"

#define LOG_TAG "FFmpegBox"




JNIEXPORT jint JNICALL
Java_com_leagoo_ffmpegvedioplayer_FFmpegUtils_execute(
        JNIEnv *env,
        jobject /* this */, jobjectArray commands) {

//    av_log_set_callback(my_logcat);

    int argc = env->GetArrayLength(commands);
    char *argv[argc];
    int i;
    for (i = 0; i < argc; i++) {
        jstring js = (jstring) env->GetObjectArrayElement(commands, i);
        argv[i] = (char *) env->GetStringUTFChars(js, 0);
    }
    return execute(argc, argv);
}

}
