package com.wust.mymusic.interceptor;

import com.wust.mymusic.response.file.ProgressResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 拦截请求的Response 替换成我们自定义的{@link ProgressResponseBody}
 * @author linliang
 * @data 2021/8/16 22:31
 */
public class ProgressInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body()))
                .build();
    }
}
