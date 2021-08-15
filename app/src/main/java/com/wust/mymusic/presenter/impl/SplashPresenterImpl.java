package com.wust.mymusic.presenter.impl;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.wust.mymusic.model.SplashModel;
import com.wust.mymusic.model.impl.SplashModelImpl;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.ui.activities.SplashActivity;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.util.ConstantUtils;
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
    public void getAD() {
        //如果没有网络直接 navigate();
        mSplashModel.getAD(this);
    }

    @Override
    public void goShop() {
        if (ActivityUtils.checkPackage(Utils.getApp(), "com.taobao.taobao")) {
            //测试商品url, 应该来自于数据库，或者网络返回的值
            String url = SPUtils.getInstance().getString(ConstantUtils.AD_URI_KEY);
            mSplashView.navigateAD(url);
        } else {
            /**
             * 可以使用webView进行打开
             */
            ToastUtils.showShort("请下载淘宝app在进行商品的购买!");
        }
    }

    @Override
    public void showAD() {
        //model从数据库中获取广告的Uri
        mSplashView.showAD(SPUtils.getInstance().getString(ConstantUtils.AD_IMAGE_KEY));
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
