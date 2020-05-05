package com.wust.mymusic.presenter.impl;

import android.util.Log;
import android.view.View;

import com.wust.mymusic.model.AccountModul;
import com.wust.mymusic.model.impl.AccountModulImpl;
import com.wust.mymusic.presenter.AccountPresenter;
import com.wust.mymusic.util.TagUtils;
import com.wust.mymusic.view.AccountView;
import com.wust.mymusic.view.BaseView;

public class AccountPresenterImpl implements AccountPresenter, AccountModul.Callback {

    private final String TAG = TagUtils.getTag(this.getClass(), true);

    private AccountView mAccountView;
    private AccountModul mAccountModul;

    public AccountPresenterImpl(AccountView accountView) {
        this.mAccountView = accountView;
        mAccountModul = new AccountModulImpl();
    }

    @Override
    public void navigate(View view) {
        Log.i(TAG, "navigate()");
        mAccountModul.navigate(view, this);
    }

    @Override
    public void onLogin() {
        mAccountView.navigateLogin();
    }

    @Override
    public void onRegister() {
        mAccountView.navigateRegister();
    }

    @Override
    public void onEmail() {
        mAccountView.navigateRegister();
    }

    @Override
    public void onWechat() {

    }

    @Override
    public void onQQ() {

    }

    @Override
    public void onWeibo() {

    }
}
