package com.wust.mymusic.networking;

import java.io.File;

import dagger.Module;

@Module
public class NetworkModule {
    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    /*public A providerA(){
        return new A();
    }*/
}
