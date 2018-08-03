package com.xingliuhua.netstatelayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xingliuhua.libnetstatelayout.view.INetErrorView;
import com.xingliuhua.libnetstatelayout.view.NetStateLayout;

public class MainActivity extends AppCompatActivity {
    private NetStateLayout mNetStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        mNetStateLayout = (NetStateLayout) findViewById(R.id.net_state_layout);
        mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_LOADING);
        mNetStateLayout.setOnRetryClickListener(new INetErrorView.OnRetryClickListener() {
            @Override
            public void onRetryClicked() {
                mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_HIDE);
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mNetStateLayout.setContentState(NetStateLayout.CONTENT_STATE_SHOW_NET_ERROR);
        }
    };
}
