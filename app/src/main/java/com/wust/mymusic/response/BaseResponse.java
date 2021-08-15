package com.wust.mymusic.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private int code;
    private T data;
}
