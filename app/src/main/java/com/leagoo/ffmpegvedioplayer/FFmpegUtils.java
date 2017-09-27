package com.leagoo.ffmpegvedioplayer;

/**
 * Created by cangck on 2017/9/26.
 */

public class FFmpegUtils {
    // Used to load the 'native-lib' library on application startup.
    private static FFmpegUtils mInstance;


    static {
        System.loadLibrary("ffmpeg_box");
        System.loadLibrary("ffmpeg");
    }

    private FFmpegUtils() {

    }

    /**
     * 获取FFmpegUtils工具实例
     * @return
     */
    public static FFmpegUtils getInstances() {
        if (mInstance == null) {
            synchronized (FFmpegUtils.class) {
                if (mInstance == null) {
                    mInstance = new FFmpegUtils();
                }

            }
        }
        return mInstance;
    }

    public native String urlProtocolInfo();
    public native String avCodecInfo();
    public native String avFilterInfo();
    public native String avFormatInfo();
}
