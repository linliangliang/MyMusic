package com.wust.mymusic.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wust.mymusic.R;
import com.wust.mymusic.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {


    // RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //todo
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFile() {

    }

    @Override
    public void setUpMVP() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
