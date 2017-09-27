package com.leagoo.ffmpegvedioplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.VideoView;

public class DetailActivity extends BaseActivity {
    //请求读取权限的请求码
    public static final int CODE_WRITE_STORAGE = 100;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        path = extras.getString("path");
        if (!TextUtils.isEmpty(path)) {
            VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
            mVideoView.setVideoPath(path);
            mVideoView.start();
        }

        findViewById(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    gif();
                } else {
                    ActivityCompat.requestPermissions(DetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == CODE_WRITE_STORAGE) {
//                gif();
            }
        }
    }


    /**
     * 合成gif图片
     */
    public void gif() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String cmds ="ffmpeg -ss 13 -t 5 -i "+path+" -s 480*270 -f gif /storage/emulated/0/DCIM/Camera/VID_20160121_183434.mp4_1453458164383.gif";
                FFmpegUtils.getInstances().execute(cmds.split("\\s"));
            }
        }).start();
    }
}
