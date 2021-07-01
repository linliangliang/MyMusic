package com.wust.mymusic;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wust.mymusic.deps.DaggerDeps;
import com.wust.mymusic.deps.Deps;
import com.wust.mymusic.networking.NetworkModule;

import java.io.File;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseApp extends AppCompatActivity {

    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }
}
