package com.wust.mymusic.util;


import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.wust.mymusic.R;
import com.wust.mymusic.model.entities.User;

public class AccountUtils {
    //保存用户登录信息，判断用户是否处于登录状态

    public static boolean checkLogin(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort(R.string.mobile_number);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(R.string.password);
            return false;
        }
        if (phone.length() != ConstantUtils.PHONE_NUMBER_LENGTH) {
            ToastUtils.showLong("Fill in 11 digits cell phone");
            return false;
        }
        return true;
    }

    public static User restore() {
        SPUtils spUtils = SPUtils.getInstance(ConstantUtils.SP_LOGIN_USER, Context.MODE_PRIVATE);
        return new User(spUtils.getString(ConstantUtils.SP_LOGIN_USER_UID_KEY),
                spUtils.getString(ConstantUtils.SP_LOGIN_USER_USERNAME_KEY),
                spUtils.getString(ConstantUtils.SP_LOGIN_USER_PASSWORD_KEY));
    }

    public static void store(User user) {
        //保存之前可以先清除数据
        SPUtils spUtils = SPUtils.getInstance(ConstantUtils.SP_LOGIN_USER, Context.MODE_PRIVATE);
        spUtils.put(ConstantUtils.SP_LOGIN_USER_UID_KEY, user.getUid());
        spUtils.put(ConstantUtils.SP_LOGIN_USER_USERNAME_KEY, user.getUsername());
        spUtils.put(ConstantUtils.SP_LOGIN_USER_PASSWORD_KEY, user.getPassword());
    }
}

