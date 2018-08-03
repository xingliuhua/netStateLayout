package com.xingliuhua.libnetstatelayout.view;

import android.content.Context;
import android.view.View;

/**
 * INetLoadingView is a interface for  loading.
 * You can implement it to customize the UI.
 * SimpleNetLoadingView has implemented it <p/>
 * Created by xingliuhua on 2018/7/31.
 */

public interface INetLoadingView {
    View getView(Context context);

    void hide();

    void show();
}
