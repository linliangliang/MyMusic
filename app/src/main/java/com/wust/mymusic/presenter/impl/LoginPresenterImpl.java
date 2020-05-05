package com.wust.mymusic.presenter.impl;

import com.wust.mymusic.model.LoginModel;
import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.model.impl.LoginModelImpl;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.presenter.LoginPresenter;
import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.ui.activities.LoginActivity;
import com.wust.mymusic.util.LogUtil;
import com.wust.mymusic.util.TagUtils;
import com.wust.mymusic.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginModel.LoginCallback {

    private String TAG = TagUtils.getTag(this.getClass());
    private LoginView loginView;
    private LoginModel loginModule;

    public LoginPresenterImpl(NetEasyMusicService netEasyMusicService, LoginActivity loginActivity) {
        this.loginView = loginActivity;
        this.loginModule = new LoginModelImpl(netEasyMusicService);
    }

    @Override
    public void validateCredentials(User user) {
        LogUtil.i(TAG, "validateCredentials");
        if (loginView != null) {
            loginView.showProgress();
            loginView.hideKeyboard();
        }
        loginModule.login(user, this);
    }

    @Override
    public void onUsernameError(String errorMsg) {
        loginView.hideProgress();
        loginView.usernameError(errorMsg);
    }

    @Override
    public void onPasswordError(String errorMsg) {
        loginView.hideProgress();
        loginView.passwordError(errorMsg);
    }

    @Override
    public void onSuccess(LoginResponse response) {
        loginView.hideProgress();
        loginView.loginSuccess(response);
    }
}
