package com.leagoo.ffmpegvedioplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化当前的View
     */
    private void initView() {
        findViewById(R.id.avCodecInfo).setOnClickListener(this);
        findViewById(R.id.urlProtocolInfo).setOnClickListener(this);
        findViewById(R.id.avFilterInfo).setOnClickListener(this);
        findViewById(R.id.avFormatInfo).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textinfo);
    }

    @Override
    public void onClick(View view) {
        FFmpegUtils instances = FFmpegUtils.getInstances();
        switch (view.getId()) {
            case R.id.urlProtocolInfo:
                mTextView.setText(String.valueOf(instances.urlProtocolInfo()));
                break;
            case R.id.avCodecInfo:
                mTextView.setText(String.valueOf(instances.avCodecInfo()));
                break;
            case R.id.avFilterInfo:
                mTextView.setText(String.valueOf(instances.avFilterInfo()));
                break;
            case R.id.avFormatInfo:
                mTextView.setText(String.valueOf(instances.avFormatInfo()));
                break;
        }
    }
}
