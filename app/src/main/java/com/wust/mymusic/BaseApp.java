package com.wust.mymusic;

import android.os.Bundle;

import com.wust.mymusic.deps.DaggerDeps;
import com.wust.mymusic.deps.Deps;
import com.wust.mymusic.networking.NetworkModule;

import java.io.File;

import javax.inject.Inject;

import lombok.Data;
import androidx.appcompat.app.AppCompatActivity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
