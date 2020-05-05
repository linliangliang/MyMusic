package com.wust.mymusic.model.impl;

import android.util.Log;
import android.view.View;

import com.wust.mymusic.R;
import com.wust.mymusic.model.AccountModul;
import com.wust.mymusic.util.TagUtils;

public class AccountModulImpl implements AccountModul {

    private final String TAG = TagUtils.getTag(this.getClass(), true);

    @Override
    public void navigate(View view, Callback callback) {
        Log.i(TAG, "navigate()");
        switch (view.getId()) {
            case R.id.btn_mobile_login:
                callback.onLogin();
                break;
            case R.id.btn_register:
                callback.onRegister();
                break;
            default:
                break;
        }
    }
}
