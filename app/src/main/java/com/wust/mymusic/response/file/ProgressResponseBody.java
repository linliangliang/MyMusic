package com.wust.mymusic.response.file;

import android.util.Log;

import com.wust.mymusic.event.FileLoadEvent;
import com.wust.mymusic.util.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class ProgressResponseBody extends ResponseBody {

    private final static String TAG= "ProgressResponseBody";

    private ResponseBody mResponseBody;
    private BufferedSource mBufferedSource;

    public ProgressResponseBody(ResponseBody responseBody) {
        super();
        mResponseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if(mBufferedSource == null){
            mBufferedSource = Okio.buffer(getSource(mResponseBody.source()));
        }
        return mBufferedSource;
    }

    private Source getSource(Source source){
        if(source == null){
            Log.i(TAG,"ResponseBody.source() == null");
        }
        return new ForwardingSource(source) {
            //总字节长度，避免多次调用contentLength()方法
            long totalSize = 0L;
            long bytesReaded = 0;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                //实时发送当前已读取(上传/下载)的字节
                Log.i(TAG, "contentLength()="+contentLength());
                Log.i(TAG, "bytesReaded()="+bytesReaded);
                RxBus.getInstance().post(new FileLoadEvent(contentLength(), bytesReaded));
                return bytesRead;
            }@Override
            public Timeout timeout() {
                return super.timeout();
            }

            @Override
            public void close() throws IOException {
                super.close();
            }

            @Override
            public String toString() {
                return super.toString();
            }
        };
    }
}
