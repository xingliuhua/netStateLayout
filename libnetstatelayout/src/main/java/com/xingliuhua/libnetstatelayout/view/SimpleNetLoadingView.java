package com.xingliuhua.libnetstatelayout.view;

import android.content.Context;
import android.view.View;

import com.xingliuhua.libnetstatelayout.R;

/**
 * Created by xingliuhua on 2018/7/31.
 */

public class SimpleNetLoadingView implements INetLoadingView {
    private View mView;

    @Override
    public View getView(Context context) {
        if (mView == null) {
            mView = View.inflate(context, R.layout.common_shade_loading, null);
        }
        return mView;
    }

    @Override
    public void hide() {
        if (mView != null) {
            mView.setVisibility(View.GONE);
        }
    }

    @Override
    public void show() {
        if (mView != null) {
            mView.setVisibility(View.VISIBLE);
        }
    }
}
