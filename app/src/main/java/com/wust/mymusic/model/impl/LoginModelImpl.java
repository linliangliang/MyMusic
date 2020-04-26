package com.wust.mymusic.model.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.wust.mymusic.R;
import com.wust.mymusic.model.LoginModel;
import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.util.AccountUtils;
import com.wust.mymusic.util.ConstantUtils;

public class LoginModelImpl implements LoginModel {
    NetEasyMusicService netEasyMusicService;

    public LoginModelImpl(NetEasyMusicService netEasyMusicService) {
        this.netEasyMusicService = netEasyMusicService;
    }

    @Override
    public void login(final User user, final LoginCallback loginCallback) {
        if (user == null || loginCallback == null) {
            return;
        }
        if (TextUtils.isEmpty(user.getUsername())) {
            loginCallback.onUsernameError(Utils.getApp().getResources().getString(R.string.mobile_number));
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            loginCallback.onUsernameError(Utils.getApp().getResources().getString(R.string.password));
        }
        netEasyMusicService.login(user.getUsername(), user.getPassword(), new NetEasyMusicService.Callback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                loginCallback.onSuccess(response);
                //save user info
                user.setUid(response.getProfile().getUserId() + "");
                AccountUtils.store(user);
            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                ToastUtils.showLong(message);
                if (message.contains(ConstantUtils.INCORRECT_PASSWORD + "")) {
                    loginCallback.onPasswordError(Utils.getApp().getResources().getString(R.string.incorrect_password));
                } else if (message.contains(ConstantUtils.TRY_PASSWORD_LIMIT + "")) {
                    loginCallback.onPasswordError(Utils.getApp().getResources().getString(R.string.try_password_limit));
                } else if (message.contains(ConstantUtils.ACCOUNT_NOT_EXISTS + "")) {
                    loginCallback.onUsernameError(Utils.getApp().getString(R.string.account_not_exists));
                } else {
                    loginCallback.onUsernameError("login error");
                }

            }
        });
    }
}
