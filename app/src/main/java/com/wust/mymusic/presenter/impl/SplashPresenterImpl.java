package com.wust.mymusic.presenter.impl;

import android.view.View;

import com.wust.mymusic.model.SplashModel;
import com.wust.mymusic.model.impl.SplashModelImpl;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.view.SplashView;

public class SplashPresenterImpl implements SplashPresenter, SplashModel.SplashCallback {

    private SplashView mSplashView;
    private SplashModel mSplashModel;

    public SplashPresenterImpl(SplashView mSplashView) {
        this.mSplashView = mSplashView;
        mSplashModel = new SplashModelImpl();
    }

    @Override
    public void navigate() {
        mSplashModel.checkLogin(this);
    }

    @Override
    public void navigateMain() {
        mSplashView.navigateMain();
    }

    @Override
    public void navigateLogin() {
        mSplashView.navigateLogin();
    }

    @Override
    public void navigateAccount() {
        mSplashView.navigateAccount();
    }
}
