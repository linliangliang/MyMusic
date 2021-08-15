package com.wust.mymusic.response.file;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class FileResponse implements Serializable {

    @SerializedName("fileName")
    private String fileName = "";

    @SerializedName("size")
    private int size = -1;

    @SerializedName("MD5")
    private Long MD5 = -1l;
}
