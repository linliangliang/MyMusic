package com.wust.mymusic.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.wust.mymusic.R;
import com.wust.mymusic.presenter.AccountPresenter;
import com.wust.mymusic.presenter.impl.AccountPresenterImpl;
import com.wust.mymusic.util.LogUtil;
import com.wust.mymusic.util.TagUtils;
import com.wust.mymusic.view.AccountView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity implements AccountView {
    private final String TAG = TagUtils.getTag(this.getClass(), true);
    @BindView(R.id.btn_mobile_login)
    Button btnMobileLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    private AccountPresenter mAccountPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        setUpMVP();
    }

    @OnClick({R.id.btn_register, R.id.btn_mobile_login})
    public void loginClick(View view) {
        mAccountPresenter.navigate(view);
    }

    @Override
    public void navigateLogin() {
        LogUtil.i(TAG, "Login");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    @Override
    public void navigateRegister() {
        LogUtil.i(TAG, "Register");
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void setUpMVP() {
        mAccountPresenter = new AccountPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
