package com.wust.mymusic.networking;

import com.wust.mymusic.BuildConfig;
import com.wust.mymusic.interceptor.ProgressInterceptor;
import com.wust.mymusic.util.ConstantUtils;
import com.wust.mymusic.util.LogUtil;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.wust.mymusic.util.ConstantUtils.isDebug;

@Module
public class NetworkModule {
    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    /*public A providerA(){
        return new A();
    }*/

    @Provides
    @Singleton
    public Retrofit providesCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(ConstantUtils.BASE_URL);

        builder.baseUrl(isDebug?ConstantUtils.DEBUG_BASE_URL:ConstantUtils.BASE_URL);

        return builder.client(getOkHttpClient(cache))
                //.addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    public NetEasyMusicAPI providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetEasyMusicAPI.class);
    }

    @Provides
    @Singleton
    public NetEasyMusicService providesService(NetEasyMusicAPI netEasyMusicAPI) {
        return new NetEasyMusicService(netEasyMusicAPI);
    }

    private OkHttpClient getOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                //.header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .header("Cache-Control", String.format("max-age=%d", 432000))
                                .build();
                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })*/
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(new ProgressInterceptor())//替换ResponseBody为ProgressResponseBody
                .cache(cache)
                .build();

        return okHttpClient;
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.d("linliang", "OkHttp====Message:" + message);
            }
        });
        return loggingInterceptor.setLevel(level);
    }
}
