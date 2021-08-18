package com.wust.mymusic.presenter.impl;

import android.os.Environment;
import android.util.Log;

import com.wust.mymusic.BaseApp;
import com.wust.mymusic.interfaces.FileCallBack;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.presenter.TestPresenter;
import com.wust.mymusic.response.file.ProgressResponseBody;
import com.wust.mymusic.ui.activities.TestActivity;
import com.wust.mymusic.util.ToastUtils;
import com.wust.mymusic.view.BaseView;
import com.wust.mymusic.view.TestView;

import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;

/**
 * @author linliang
 * @data 2021/8/17 0:18
 */
public class TestPresenterImpl implements TestPresenter {
    private WeakReference<BaseView> mViewWeakReference;

    NetEasyMusicService mNetEasyMusicService;

    public TestPresenterImpl(NetEasyMusicService netEasyMusicService) {
        mNetEasyMusicService = netEasyMusicService;
    }

    @Override
    public void attachView(BaseView view) {
        mViewWeakReference = new WeakReference<BaseView>(view);
    }

    @Override
    public void detachView() {
        if (mViewWeakReference.get() != null) {
            mViewWeakReference.clear();
            mViewWeakReference = null;
        }
    }

    @Override
    public void startDownloadFile(String url) {

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "demo.txt" + System.currentTimeMillis();
        mNetEasyMusicService.load(url, new FileCallBack<ResponseBody>(filePath, fileName) {

            @Override
            public void onSuccess(ResponseBody progressResponseBody) {
                //下载完成提示开始安装
                ToastUtils.showShort("保存完成");
                Log.i("linliang", "onSuccess");
            }

            /**
             *
             * @param progress 已经下载的字节数
             * @param total 总字节数
             */
            @Override
            public void progress(long progress, long total) {
                Log.i("linliang", "progress = " + progress + "total = " + total);
                TestActivity testActivity = (TestActivity) mViewWeakReference.get();
                if (testActivity != null) {
                    testActivity.setDownloadProgress((int) ((int) progress * 100 / total));
                }
            }

            @Override
            public void onCompleted() {
                ToastUtils.showShort("保存完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ToastUtils.showShort("保存失败");
            }
        });
    }
}
