package com.wust.mymusic.model;

import android.view.View;

public interface AccountModul {
    void navigate(View view, Callback callback);

    interface Callback {

        void onLogin();

        void onRegister();

        void onEmail();

        void onWechat();

        void onQQ();

        void onWeibo();
    }
}
