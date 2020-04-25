package com.wust.mymusic.view;

import com.wust.mymusic.response.login.LoginResponse;

public interface LoginView extends BaseView{
    void showProgress();

    void hideProgress();

    void usernameError(String errorMsg);

    void passwordError(String errorMsg);

    void loginSuccess(LoginResponse loginResponse);

    void hideKeyboard();
}
