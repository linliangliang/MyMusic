package com.wust.mymusic.networking;

import io.reactivex.Observable;

import com.wust.mymusic.response.file.FileResponse;
import com.wust.mymusic.response.file.ProgressResponseBody;
import com.wust.mymusic.response.login.LoginResponse;
import com.wust.mymusic.util.ConstantUtils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface NetEasyMusicAPI {
    @GET(ConstantUtils.CELLPHONE_API)
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone, @Query("password") String password);


    /**
     * 注意:
     * 1.如果方法的泛型指定的类不是ResonseBody,retrofit会将返回的string成用json转换器自动转换该类的一个对象,转换不成功就报错.
     *  如果不需要gson转换,那么就指定泛型为ResponseBody,
     *  只能是ResponseBody,子类都不行,同理,下载上传时,也必须指定泛型为ResponseBody
     * 2. map不能为null,否则该请求不会执行,但可以size为空.
     * 3.使用@url,而不是@Path注解,后者放到方法体上,会强制先urlencode,然后与baseurl拼接,请求无法成功.
     */
    @Streaming
    @GET
    Observable<ResponseBody> getFile(@Url String downloadURL);
}
