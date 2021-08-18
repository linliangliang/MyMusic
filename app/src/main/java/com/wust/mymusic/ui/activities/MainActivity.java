package com.wust.mymusic.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.util.LogUtil;
import com.wust.mymusic.util.MyApllication;
import com.wust.mymusic.util.TagUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseApp {

    private final String TAG = TagUtils.getTag(this.getClass());

    Toolbar toolbar;

    //由intent传入
    private Bundle receiveData;

    @Inject
    public NetEasyMusicService mNetEasyMusicService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        //透明状态栏
        ActivityUtils.setStatusBarTransparent(this);
        setContentView(R.layout.activity_main);
        initView();

        LogUtil.d(TagUtils.getTag(MainActivity.class), "loadMusicMenu" + Thread.currentThread().getId());
    }

    private void initData() {
        getDeps().inject(this);
        //保存一个去全局的 mNetEasyMusicService在Application中
        MyApllication.setNetEasyMusicService(mNetEasyMusicService);
    }

    private void initView() {
        ButterKnife.bind(this);
        //设置actionBar
        setSupportActionBar(toolbar);
        setTitle("");//actionBar的文字为空
        receiveData = getIntent().getExtras();
    }

}
