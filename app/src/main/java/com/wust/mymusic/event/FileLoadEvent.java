package com.wust.mymusic.event;

import lombok.Data;

/**
 * @author linliang
 * @data 2021/8/16 22:26
 */
@Data
public class FileLoadEvent {
    long total = -1;//文件总长度
    long bytesLoaded = -1;//已经下载的长度

    public FileLoadEvent(long total, long bytesLoaded) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
    }

}
