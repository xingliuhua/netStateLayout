package com.xingliuhua.libnetstatelayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xingliuhua.libnetstatelayout.R;


/**
 * Created by xlh on 2017/8/16.
 */

public class NetStateLayout extends FrameLayout {
    private INetErrorView mNetErrorView;
    private INetLoadingView mNetLoadingView;
    private String netErrorClassName;
    private String netLoadingClassName;

    /**
     * show customized network error view
     */
    public static final int CONTENT_STATE_SHOW_NET_ERROR = 0x1;
    /**
     * show customized network loading view
     */
    public static final int CONTENT_STATE_SHOW_LOADING = 0x2;
    /**
     * hide customized view,show your data content
     */
    public static final int CONTENT_STATE_HIDE = 0x3;

    private int mContentState = CONTENT_STATE_HIDE;

    /**
     * set customized network error view.
     *
     * @param netErrorView
     * @see SimpleNetErrorView  SimpleNetErrorView
     */
    public void setNetErrorView(INetErrorView netErrorView) {
        if (netErrorView == null) {
            return;
        }
        if (mNetErrorView != null) {
            removeView(mNetErrorView.getView(getContext()));
        }
        this.mNetErrorView = netErrorView;
        addView(netErrorView.getView(getContext()));
    }

    /**
     * set customized network loading view.
     *
     * @param netLoadingView
     * @see SimpleNetLoadingView SimpleNetLoadingView
     */
    public void setNetLoadingView(INetLoadingView netLoadingView) {
        if (netLoadingView == null) {
            return;
        }
        if (mNetLoadingView != null) {
            removeView(mNetLoadingView.getView(getContext()));
        }
        this.mNetLoadingView = netLoadingView;
        addView(netLoadingView.getView(getContext()));
    }

    public NetStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.netStateLayout);

        netErrorClassName = ta.getString(R.styleable.netStateLayout_net_error);
        netLoadingClassName = ta.getString(R.styleable.netStateLayout_net_loading);

        ta.recycle();
    }


    /**
     * get current state
     *
     * @return {@link #CONTENT_STATE_SHOW_NET_ERROR} ,{@link #CONTENT_STATE_SHOW_LOADING},{@link #CONTENT_STATE_HIDE}
     */
    public int getContentState() {
        return mContentState;
    }

    /**
     *
     *
     * @param contentState {@linkplain #CONTENT_STATE_SHOW_NET_ERROR} ,{@link #CONTENT_STATE_SHOW_LOADING},{@link #CONTENT_STATE_HIDE}
     */
    public void setContentState(int contentState) {
        if (mNetErrorView == null) {
            try {
                Class<?> netErrorClass = Class.forName(netErrorClassName);
                mNetErrorView = (INetErrorView) netErrorClass.newInstance();
                addView(mNetErrorView.getView(getContext()), new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mNetLoadingView == null) {
            try {
                Class<?> netLoadingClass = Class.forName(netLoadingClassName);
                mNetLoadingView = (INetLoadingView) netLoadingClass.newInstance();
                addView(mNetLoadingView.getView(getContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mContentState = contentState;
        switch (contentState) {
            case CONTENT_STATE_SHOW_NET_ERROR:
                if (mNetErrorView != null) {
                    mNetErrorView.show();
                }
                if (mNetLoadingView != null) {
                    mNetLoadingView.hide();
                }
                break;
            case CONTENT_STATE_SHOW_LOADING:
                if (mNetErrorView != null) {
                    mNetErrorView.hide();
                }
                if (mNetLoadingView != null) {
                    mNetLoadingView.show();
                }
                break;
            case CONTENT_STATE_HIDE:
                if (mNetErrorView != null) {
                    mNetErrorView.hide();
                }
                if (mNetLoadingView != null) {
                    mNetLoadingView.hide();
                }
                break;
        }
    }


    public void setOnRetryClickListener(INetErrorView.OnRetryClickListener onRetryClickListener) {
        if (mNetErrorView == null) {
            try {
                Class<?> netErrorClass = Class.forName(netErrorClassName);
                mNetErrorView = (INetErrorView) netErrorClass.newInstance();
                addView(mNetErrorView.getView(getContext()), new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mNetErrorView != null) {
            mNetErrorView.setRetryClickListener(onRetryClickListener);
        }
    }
}
