package com.xingliuhua.libnetstatelayout.view;

import android.content.Context;
import android.view.View;

/**
 * INetErrorView is a interface for network error.
 * You can implement it to customize the UI.
 * SimpleNetErrorView has implemented it <p/>
 * Created by xingliuhua on 2018/7/31.
 */

public interface INetErrorView {

    void setRetryClickListener(OnRetryClickListener retryClickListener);

    View getView(Context context);

    void hide();

    void show();

    interface OnRetryClickListener {
        void onRetryClicked();
    }
}
