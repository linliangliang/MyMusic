package com.wust.mymusic.model.impl;

import android.text.TextUtils;

import com.wust.mymusic.model.SplashModel;
import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.util.AccountUtils;

public class SplashModelImpl implements SplashModel {


    @Override
    public void checkLogin(SplashCallback callback) {
        User user = AccountUtils.restore();
        if (TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
            callback.navigateAccount();
        }else{
            callback.navigateMain();
        }
    }

    @Override
    public void getAD(SplashCallback callback) {
        callback.showAD();
    }
}
