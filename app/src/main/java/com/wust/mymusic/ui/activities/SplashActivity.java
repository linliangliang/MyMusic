package com.wust.mymusic.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.presenter.SplashPresenter;
import com.wust.mymusic.presenter.impl.SplashPresenterImpl;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.view.SplashView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;

public class SplashActivity extends BaseApp implements SplashView, View.OnClickListener{

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
    private boolean naviagte_flag = false;//记录是否跳转到其他页面了
    private int adCount = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否显示状态栏目
        ActivityUtils.requestFullScreen(this, false);
        setContentView(R.layout.activity_splash);
        init();
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
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //mSplashPresenter.navigate();
            }
        }, 1000);*/

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
        if("".equals(ADUrl) || null == ADUrl){
            Glide.with(this).load(R.drawable.ad).into(mAD);
        } else {
            Glide.with(this).load(ADUrl).into(mAD);
        }
    }

    @Override
    public void navigateMain() {
        if(naviagte_flag == false){
            naviagte_flag = true;
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
        if(naviagte_flag == false){
            naviagte_flag = true;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_ad:
                goShop();
                break;
            case R.id.btn_ad_count:
                navigateMain();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == requestadCode){
            navigateMain();
        }
    }

    private void goShop(){
        if (checkPackage("com.taobao.taobao")) {
            //测试商品url
            String url = "https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w13749380-17445896657.1.4277c9d6qNQCOD&id=559827840919&_u=tcg2dgree0c";
            Intent intent = new Intent();
            intent.setAction("Android.intent.action.VIEW");
            Uri uri = Uri.parse(url); // 商品地址
            intent.setData(uri);
            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            startActivityForResult(intent, requestadCode);
            // startActivity(intent);
        }else{
            /**
             * 可以使用webView进行打开
             */
            Toast.makeText(SplashActivity.this, "请下载淘宝app在进行商品的购买!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkPackage(String packageName)
    {
        if (packageName == null || "".equals(packageName))
            return false;
        try
        {
            this.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(adCount > 0){
                mBtnCount.setText(new StringBuilder().append(adCount).toString());
                adCount--;
                mHandler.sendEmptyMessageDelayed(1, 1000);
            } else if(adCount <= 0) {
                navigateMain();
            }

        }
    }

}
