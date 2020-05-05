package com.wust.mymusic.model;

public interface SplashModel {

    void checkLogin(SplashCallback callback);

    interface SplashCallback {
        void navigateMain();

        void navigateLogin();

        void navigateAccount();
    }
}
