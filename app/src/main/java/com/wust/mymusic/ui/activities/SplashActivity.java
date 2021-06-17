package com.wust.mymusic.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.presenter.impl.SplashPresenterImpl;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.view.SplashView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;

public class SplashActivity extends BaseApp implements SplashView {

    private SplashPresenter mSplashPresenter;
    private Handler mHandler;

    @BindView(R.id.fl_frame)
    private FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否显示状态栏目
        ActivityUtils.requestFullScreen(this, false);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {

        //初始化 MVP结构
        setUpMVP();


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //延迟半秒，加载广告，广告界面完成 ， 判断是否登录，登录状态下，跳转主界面。
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.navigate();
            }
        }, 500);
    }

    @Override
    public void navigateMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateLogin() {
        //todo
    }

    @Override
    public void navigateAccount() {
        startActivity(new Intent(this, AccountActivity.class));
        finish();
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
