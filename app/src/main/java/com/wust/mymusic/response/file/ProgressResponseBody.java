package com.wust.mymusic.response.file;

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

    private ResponseBody mResponseBody;
    private BufferedSource mBufferedSource;

    public ProgressResponseBody(ResponseBody responseBody) {
        super();
        mResponseBody = responseBody;
    }

    @android.support.annotation.Nullable
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
        if(mBufferedSource != null){
            mBufferedSource = Okio.buffer(getSource(mResponseBody.source()));
        }
        return null;
    }

    private Source getSource(Source source){
        return new ForwardingSource(source) {
            long bytesReaded = 0;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                //实时发送当前已读取(上传/下载)的字节
                //RxBus.getInstance().post(new FileLoadEvent(contentLength(), bytesReaded));
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
