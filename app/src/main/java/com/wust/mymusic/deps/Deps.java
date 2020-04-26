package com.wust.mymusic.deps;


import com.wust.mymusic.ui.activities.LoginActivity;
import com.wust.mymusic.ui.activities.MainActivity;
import com.wust.mymusic.networking.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity mainActivity);

}
