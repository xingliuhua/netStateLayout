package com.xingliuhua.netstatelayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.xingliuhua.libnetstatelayout.view.INetErrorView;
import com.xingliuhua.libnetstatelayout.view.NetStateLayout;
import com.xingliuhua.libnetstatelayout.view.SimpleNetErrorView;
import com.xingliuhua.libnetstatelayout.view.SimpleNetLoadingView;

/**
 * use by code
 */
public class SecondActivity extends AppCompatActivity {
    private NetStateLayout mNetStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mNetStateLayout = (NetStateLayout) findViewById(R.id.net_state_layout);
        mNetStateLayout.setNetErrorView(new SimpleNetErrorView());
        mNetStateLayout.setNetLoadingView(new SimpleNetLoadingView());
        mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_LOADING);
        mHandler.sendEmptyMessageDelayed(0, 3000);
        mNetStateLayout.setOnRetryClickListener(new INetErrorView.OnRetryClickListener() {
            @Override
            public void onRetryClicked() {
                mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_HIDE);
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_NET_ERROR);
        }
    };
}
