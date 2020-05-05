package com.wust.mymusic.networking;

import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetEasyMusicService {

    private NetEasyMusicAPI netEasyMusicAPI;

    public NetEasyMusicService(NetEasyMusicAPI netEaseMusicAPI) {
        this.netEasyMusicAPI = netEaseMusicAPI;
    }

    public void login(String phone, String password, final Callback callback) {

        netEasyMusicAPI.loginByPhone(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        callback.onSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onError(Throwable e);
    }
}
