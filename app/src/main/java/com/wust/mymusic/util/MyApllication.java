package com.wust.mymusic.util;

import android.app.Application;

import androidx.multidex.MultiDex;
import androidx.room.Room;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.wust.mymusic.database.AppDatabase;
import com.wust.mymusic.networking.NetEasyMusicService;

public class MyApllication extends Application {

    private static final String TAG = MyApllication.class.getSimpleName();
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "MyMusic.db";
    private static AppDatabase mAppDatabase = null;

    //全局的网络访问的接口
    private static NetEasyMusicService netEasyMusicService;

    public static NetEasyMusicService getNetEasyMusicService() {
        return netEasyMusicService;
    }

    //mainActivity中设置，也可以在splashActivity中设置
    public static void setNetEasyMusicService(NetEasyMusicService netEasyMusicService) {
        MyApllication.netEasyMusicService = netEasyMusicService;
    }

    public static AppDatabase getDBInstance(){
        return mAppDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化MultiDex
        MultiDex.install(this);

        //1、启动唱歌的service
        //2、启动线程创建数据库 可以创建一个全局的线程池
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAppDatabase = Room.databaseBuilder(Utils.getApp(), AppDatabase.class, DB_NAME).build();
            }
        }).start();

    }
}
