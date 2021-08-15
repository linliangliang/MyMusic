package com.wust.mymusic.model.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "search_history")
public class SearchHistory {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * 搜索关键词
     */
    @ColumnInfo(name = "keyword")
    private String keyword;


    public SearchHistory() {
    }

    @Ignore
    public SearchHistory(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }
}
