package com.wust.mymusic.view;

public interface SplashView extends BaseView {

    void showAD(String ADUrl);

    void navigate();

    void navigateMain();

    void navigateLogin();

    void navigateAccount();

    void navigateAD(String uri);

}
