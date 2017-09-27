package com.leagoo.ffmpegvedioplayer.media;

public class Video {

    private String mTitle;

    private String mPath;

    private long mDuration;

    public Video(String title, String path, long duration) {
        mTitle = title;
        mPath = path;
        mDuration = duration;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }
}