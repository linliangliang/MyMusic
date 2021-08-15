package com.wust.mymusic.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.presenter.impl.SplashPresenterImpl;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.util.TextUtil;
import com.wust.mymusic.view.SplashView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;

public class SplashActivity extends BaseApp implements SplashView, View.OnClickListener {

    private SplashPresenter mSplashPresenter;

    private MyHandle mHandler;

    @BindView(R.id.fl_frame)
    public FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    //@BindView(R.id.im_ad)
    public ImageView mAD;
    //@BindView(R.id.btn_ad_count)
    public Button mBtnCount;

    private int requestadCode = 1001;
    private int adCount = 3;
    private boolean navigateFlag = false; //记录是否跳转到其他界面
    private boolean adFlag = false; //记录是否跳转过广告界面

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否显示状态栏目
        ActivityUtils.requestFullScreen(this, false);
        setContentView(R.layout.activity_splash);
        Intent intent = getIntent();
        init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); //must store the new intent unless getIntent() will return the old one
        //栈顶服用Activity的时候，不会调用OnCreate
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        initView();

        //初始化 MVP结构
        setUpMVP();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //延迟半秒，加载广告，广告界面完成 ， 判断是否登录，登录状态下，跳转主界面。
        mHandler = new MyHandle();
        mHandler.sendEmptyMessageDelayed(1, 1000);

    }

    private void initView() {
        mAD = findViewById(R.id.im_ad);
        mBtnCount = findViewById(R.id.btn_ad_count);
        mBtnCount.setText(new StringBuffer().append(adCount).toString());
        mAD.setOnClickListener(this);
        mBtnCount.setOnClickListener(this);
    }

    @Override
    public void showAD(String ADUrl) {
        if ("".equals(ADUrl) || null == ADUrl) {
            Glide.with(this).load(R.drawable.ad).into(mAD);
        } else {
            Glide.with(this).load(ADUrl).into(mAD);
        }
    }

    @Override
    public void navigate() {
        //进入下一个界面
        mSplashPresenter.navigate();
    }

    @Override
    public void navigateMain() {
        if (navigateFlag == false) {
            mHandler.removeCallbacksAndMessages(null);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void navigateLogin() {
        //todo
    }

    @Override
    public void navigateAccount() {
        if (navigateFlag == false) {
            navigateFlag = true;
            startActivity(new Intent(this, AccountActivity.class));
            finish();
        }
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
        mSplashPresenter.getAD();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_ad:
                mSplashPresenter.goShop();
                break;
            case R.id.btn_ad_count:
                navigate();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestadCode) {
            navigate();
        } else {
            navigate();
        }

    }

    @Override
    public void navigateAD(String uriStr) {//传入一个Ad实体类
        if (TextUtils.isEmpty(uriStr)) {
            return;
        }
        //清除handle 的 message
        mHandler.removeCallbacksAndMessages(null);
        adFlag = true;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(uriStr);
        intent.setData(uri);
        startActivityForResult(intent, requestadCode);
    }

    class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (adCount > 0) {
                mBtnCount.setText(new StringBuilder().append(adCount).toString());
                adCount--;
                mHandler.sendEmptyMessageDelayed(1, 1000);
            } else if (adCount <= 0) {
                navigate();
            }

        }
    }

}
