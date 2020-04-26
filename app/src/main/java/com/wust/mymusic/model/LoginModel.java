package com.wust.mymusic.model;

import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.response.login.LoginResponse;

public interface LoginModel {
    void login(User user, LoginCallback loginCallback);

    interface LoginCallback {
        void onUsernameError(String errorMsg);

        void onPasswordError(String errorMsg);

        void onSuccess(LoginResponse response);

    }
}
