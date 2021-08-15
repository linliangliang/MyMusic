package com.wust.mymusic.util;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author linliang
 * @data 2021/8/15 22:51
 */

public class RxBus {

    private static volatile RxBus mInstance;
    private final Relay<Object> mBus;

    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     * Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，
     * 需要将 Subject转换为一个 SerializedSubject ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
     */
    private RxBus() {
        this.mBus = PublishRelay.create().toSerialized();
    }

    /**
     * 单例 双重锁
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = IRxBusHolder.INSTANCE;
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送一个新的事件
     *
     * @param obj
     */
    public void post(Object obj) {
        mBus.accept(obj);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public <T> Disposable register(Class<T> evenType, Consumer<T> onNext) {
        return toObservable(evenType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }

    public <T> Disposable register(
            Class<T> eventType, Scheduler subScheduler, Scheduler obsScheduler, Consumer<T> onNext) {
        return toObservable(eventType)
                .subscribeOn(subScheduler)
                .observeOn(obsScheduler)
                .subscribe(onNext);
    }

    public <T> Disposable register(Class<T> eventType, Consumer<T> onNext, Consumer onError) {
        return toObservable(eventType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext) {
        return toObservable(eventType)
                .observeOn(scheduler)
                .subscribe(onNext);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext, Consumer onError,
                                   Action onComplete, Consumer onSubscribe) {
        return toObservable(eventType)
                .observeOn(scheduler)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext, Consumer onError,
                                   Action onComplete) {
        return toObservable(eventType)
                .observeOn(scheduler)
                .subscribe(onNext, onError, onComplete);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext, Consumer onError) {
        return toObservable(eventType)
                .observeOn(scheduler)
                .subscribe(onNext, onError);
    }

    public <T> Disposable register(Class<T> eventType, Consumer<T> onNext, Consumer onError,
                                   Action onComplete, Consumer onSubscribe) {
        return toObservable(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    public <T> Disposable register(Class<T> eventType, Consumer<T> onNext, Consumer onError,
                                   Action onComplete) {
        return toObservable(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onComplete);
    }

    public void unregister(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private static class IRxBusHolder {
        //静态变量提前初始化
        private final static RxBus INSTANCE = new RxBus();

    }
}

