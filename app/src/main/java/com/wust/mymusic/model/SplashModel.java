package com.wust.mymusic.model;

public interface SplashModel {

    void checkLogin(SplashCallback callback);

    void getAD(SplashCallback callback);

    interface SplashCallback {

        void showAD();

        void navigateMain();

        void navigateLogin();

        void navigateAccount();
    }
}
