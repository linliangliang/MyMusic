package com.wust.mymusic.deps;


import com.wust.mymusic.ui.activities.LoginActivity;
import com.wust.mymusic.ui.activities.MainActivity;
import com.wust.mymusic.networking.NetworkModule;
import com.wust.mymusic.ui.activities.TestActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface Deps {
    void inject(LoginActivity loginActivity);

    void inject(TestActivity testActivity);

    void inject(MainActivity mainActivity);
}
