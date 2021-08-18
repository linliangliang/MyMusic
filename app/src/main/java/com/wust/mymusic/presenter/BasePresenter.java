package com.wust.mymusic.presenter;

import com.wust.mymusic.BaseApp;
import com.wust.mymusic.view.BaseView;

/**
 * @author linliang
 * @data 2021/8/17 0:31
 */
public interface BasePresenter<V extends BaseView> {
    public void attachView(V view);
    public void detachView();
}
