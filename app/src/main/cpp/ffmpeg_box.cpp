#include <jni.h>
#include <string>

extern "C" {

#include <libavformat/avformat.h>
#include <libavfilter/avfilter.h>

/*
 * Class:     com_leagoo_ffmpegvedioplayer_FFmpegUtils
 * Method:    urlProtocolInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_leagoo_ffmpegvedioplayer_FFmpegUtils_urlProtocolInfo
        (JNIEnv *env, jobject obj) {
    char info[40000] = {0};
    av_register_all();
    struct URLProtocol *pup = NULL;
    struct URLProtocol **p_temp = &pup;
    avio_enum_protocols((void **) p_temp, 0);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 0));
    }
    pup = NULL;
    avio_enum_protocols((void **) p_temp, 1);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 1));
    }
    return env->NewStringUTF(info);

}

/*
 * Class:     com_leagoo_ffmpegvedioplayer_FFmpegUtils
 * Method:    avCodecInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_leagoo_ffmpegvedioplayer_FFmpegUtils_avCodecInfo
        (JNIEnv *env, jobject obj) {
    char info[40000] = {0};
    av_register_all();
    AVCodec *c_temp = av_codec_next(NULL);
    while (c_temp != NULL) {
        if (c_temp->decode != NULL) {
            sprintf(info, "%sdecode:", info);
        } else {
            sprintf(info, "%sencode:", info);
        }
        switch (c_temp->type) {
            case AVMEDIA_TYPE_VIDEO:
                sprintf(info, "%s(video):", info);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(info, "%s(audio):", info);
                break;
            default:
                sprintf(info, "%s(other):", info);
                break;
        }
        sprintf(info, "%s[%10s]\n", info, c_temp->name);
        c_temp = c_temp->next;
    }
    return env->NewStringUTF(info);
}

/*
 * Class:     com_leagoo_ffmpegvedioplayer_FFmpegUtils
 * Method:    avFilterInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_leagoo_ffmpegvedioplayer_FFmpegUtils_avFilterInfo
        (JNIEnv *env, jobject obj) {
    char info[40000] = {0};
    avfilter_register_all();
    AVFilter *f_temp = (AVFilter *) avfilter_next(NULL);
    while (f_temp != NULL) {
        sprintf(info, "%s%s\n", info, f_temp->name);
        f_temp = f_temp->next;
    }
    return env->NewStringUTF(info);

}


JNIEXPORT jstring JNICALL Java_com_leagoo_ffmpegvedioplayer_FFmpegUtils_avFormatInfo
        (JNIEnv *env, jobject /* this */) {
    char info[40000] = {0};
    av_register_all();
    AVInputFormat *if_temp = av_iformat_next(NULL);
    AVOutputFormat *of_temp = av_oformat_next(NULL);
    while (if_temp != NULL) {
        sprintf(info, "%sInput: %s\n", info, if_temp->name);
        if_temp = if_temp->next;
    }
    while (of_temp != NULL) {
        sprintf(info, "%sOutput: %s\n", info, of_temp->name);
        of_temp = of_temp->next;
    }
    return env->NewStringUTF(info);
}

}
