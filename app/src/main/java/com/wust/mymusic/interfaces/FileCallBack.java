package com.wust.mymusic.interfaces;

import android.util.Log;

import com.wust.mymusic.event.FileLoadEvent;
import com.wust.mymusic.response.file.ProgressResponseBody;
import com.wust.mymusic.util.RxBus;
import com.wust.mymusic.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import rx.functions.Action1;

/**
 * @author linliang
 * @data 2021/8/16 22:46
 */

public abstract class FileCallBack<T> {

    private final static String TAG= "FileCallBack";

    private String destFileDir;
    private String destFileName;
    private CompositeDisposable mCompositeDisposable;

    public FileCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        mCompositeDisposable = new CompositeDisposable();
        subscribeLoadProgress();
    }

    public abstract void onSuccess(T t);

    public abstract void progress(long progress, long total);

    //public abstract void onStart();

    public abstract void onCompleted();

    public abstract void onError(Throwable e);

    public void saveFile(ResponseBody body) {
        StringBuilder sb = new StringBuilder();
        Log.i(TAG, sb.append("文件保存路径：").append(destFileDir).toString());
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            final long total = body.contentLength();
            long sum = 0;

            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
            }
            fos.flush();
            dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }

    /**
     * 注册事件处理进度条
     */
    public void subscribeLoadProgress() {
        Disposable disposable = RxBus.getInstance()
                .register(FileLoadEvent.class, new Consumer<FileLoadEvent>() {
                    @Override
                    public void accept(FileLoadEvent fileLoadEvent) throws Exception {
                        //RxBus事件处理 的文件传输的速度
                        progress(fileLoadEvent.getBytesLoaded(), fileLoadEvent.getTotal());
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable Throwable) throws Exception {
                        //TODO 对异常的处理
                        ToastUtils.showShort(Throwable.getMessage().toString());
                    }
                });
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
        //防止内存泄漏RxBus.getInstance().addSubscription(this, subscription);
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    public void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
        //RxBus.getInstance().unregister(disposable);
    }
}
