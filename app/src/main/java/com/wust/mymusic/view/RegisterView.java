package com.wust.mymusic.view;

public interface RegisterView extends BaseView {
    void register(String username, String password);

    void onSuccess();

    void onFile();
}
