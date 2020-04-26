package com.wust.mymusic.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ToastUtils;
import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.model.entities.User;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.presenter.LoginPresenter;
import com.wust.mymusic.presenter.impl.LoginPresenterImpl;
import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.util.ActivityUtils;
import com.wust.mymusic.util.ConstantUtils;
import com.wust.mymusic.view.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseApp implements LoginView {

    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.pb_login)
    ProgressBar pbLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginPresenter loginPresenter = null;


    @Inject
    public NetEasyMusicService netEasyMusicService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getDeps().inject(this);
        ButterKnife.bind(this);

        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        loginPresenter = new LoginPresenterImpl(netEasyMusicService, this);
    }


    @OnClick(R.id.btn_login)
    private void loginCallback() {
        String phone = etLoginPhone.getText().toString();
        String password = etLoginPassword.getText().toString();
        loginPresenter.validateCredentials(new User(phone, password));
    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void usernameError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void passwordError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        hideProgress();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ConstantUtils.LOGIN_RESPONSE_KEY, loginResponse);
        startActivity(intent);
        finish();
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(this);
    }
}
