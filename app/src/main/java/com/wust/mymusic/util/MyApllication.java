package com.wust.mymusic.util;

import android.app.Application;

import com.wust.mymusic.networking.NetEasyMusicService;

public class MyApllication extends Application {

    private static final String TAG = MyApllication.class.getSimpleName();
    //数据库名称
    private static final String DB_NAME = "MyMusic.db";

    //全局的网络访问的接口
    private static NetEasyMusicService netEasyMusicService;

    public static NetEasyMusicService getNetEasyMusicService() {
        return netEasyMusicService;
    }

    //mainActivity中设置，也可以在splashActivity中设置
    public static void setNetEasyMusicService(NetEasyMusicService netEasyMusicService) {
        MyApllication.netEasyMusicService = netEasyMusicService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //1、启动唱歌的service
        //2、启动线程创建数据库
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
