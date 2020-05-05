package com.wust.mymusic.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wust.mymusic.R;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.presenter.impl.SplashPresenterImpl;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.view.SplashView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends Activity implements SplashView {


    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否显示状态栏目
        ActivityUtils.requestFullScreen(this, false);
        setContentView(R.layout.activity_splash);
        setUpMVP();

        //延迟半秒，加载广告
        new Handler().postDelayed(new Runnable() {
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
