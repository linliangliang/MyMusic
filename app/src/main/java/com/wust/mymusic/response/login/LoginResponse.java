package com.wust.mymusic.response.login;

import android.accounts.Account;
import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;
import com.wust.mymusic.response.common.BindingsItem;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class LoginResponse implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("loginType")
    private int loginType;

    @SerializedName("profile")
    private ContactsContract.Profile profile;

    @SerializedName("bindings")
    private List<BindingsItem> bindings;

    @SerializedName("account")
    private Account account;
}
