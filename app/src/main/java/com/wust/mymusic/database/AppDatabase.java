package com.wust.mymusic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wust.mymusic.model.entities.SearchHistory;
import com.wust.mymusic.model.entities.dao.SearchHistoryDao;

@Database(entities = {SearchHistory.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * 抽象方法getDao()，这个是获取数据库操作接口的方法，不用我们实现，编译通过后，room会帮我们实现
     * @return
     */
    public abstract SearchHistoryDao searchHistoryDao();
}
