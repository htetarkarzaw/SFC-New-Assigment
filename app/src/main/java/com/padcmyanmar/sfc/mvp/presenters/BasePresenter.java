package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.mvp.views.BaseView;

public class BasePresenter <V extends BaseView>  {

    protected V mView;
    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void onStart() {
    }

    public void onCreate() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestory() {
    }
}
