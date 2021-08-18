package com.wust.mymusic.networking;


import android.widget.Button;

import com.wust.mymusic.interfaces.FileCallBack;
import com.wust.mymusic.response.file.ProgressResponseBody;
import com.wust.mymusic.response.login.LoginResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;

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

    public void load(String url, final FileCallBack<ResponseBody> callBack){
        netEasyMusicAPI.getFile(url)
                .subscribeOn(Schedulers.io())//请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) {
                        callBack.saveFile( body);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody progressResponseBody) {
                        if (callBack != null)
                            callBack.onSuccess(progressResponseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null)
                            callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        if (callBack != null)
                            callBack.onCompleted();
                    }
                });
    }


    public interface Callback<T> {
        void onSuccess(T data);

        void onError(Throwable e);
    }
}
