package com.wust.mymusic.model.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.wust.mymusic.model.SplashModel;
import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.util.AccountUtils;
import com.wust.mymusic.util.ConstantUtils;

public class SplashModelImpl implements SplashModel {


    @Override
    public void checkLogin(SplashCallback callback) {
        User user = AccountUtils.restore();
        if (TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
            callback.navigateAccount();
        }else{
            callback.navigateMain();
        }
    }

    @Override
    public void getAD(SplashCallback callback) {
        //sp保存 ad Url
        SPUtils.getInstance().put(ConstantUtils.AD_IMAGE_KEY,"");
        SPUtils.getInstance().put(ConstantUtils.AD_URI_KEY,
                "https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w13749380-17445896657.1.4277c9d6qNQCOD&id=559827840919&_u=tcg2dgree0c");
        callback.showAD();
    }
}
