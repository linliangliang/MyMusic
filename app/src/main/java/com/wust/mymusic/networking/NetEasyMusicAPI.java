package com.wust.mymusic.networking;

import io.reactivex.Observable;

import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.util.ConstantUtils;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetEasyMusicAPI {
    @GET(ConstantUtils.CELLPHONE_API)
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone, @Query("password") String password);

}
