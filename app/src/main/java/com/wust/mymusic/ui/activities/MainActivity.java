package com.wust.mymusic.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wust.mymusic.R;
import com.wust.mymusic.util.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        ActivityUtils.setStatusBarTransparent(this);
        setContentView(R.layout.activity_main);
    }


}
