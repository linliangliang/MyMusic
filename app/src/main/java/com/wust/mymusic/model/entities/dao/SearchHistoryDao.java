package com.wust.mymusic.model.entities.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wust.mymusic.model.entities.SearchHistory;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface SearchHistoryDao {
    /**
     * 查询所有历史记录
     * @return
     */
    @Query("select * from search_history")
    Observable<List<SearchHistory>> getAll();

    /**
     * 查询全部关键词
     * @return
     */
    @Query("select keyword from search_history")
    List<String> getAllKeyword();

    /**
     * 查询最新的一条记录
     * @return
     */
    @Query("SELECT * FROM search_history LIMIT 1")
    SearchHistory getTopRow();

    /**
     * 插入一组搜索记录
     * @param histories
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SearchHistory... histories);

    /**
     * 查询有多少天记录在数据库中
     * @return
     */
    @Query("SELECT COUNT(*) FROM search_history")
    int getRows();

    /**
     * 根据关键词keyword删除一条记录
     * @param keyword
     */
    @Query("DELETE FROM search_history WHERE keyword = :keyword")
    void delete(String keyword);

    /**
     * 删除全部数据
     */
    @Query("DELETE  FROM search_history")
    void deleteAll();
}
