package com.padcmyanmar.sfc.datas.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.datas.vo.PublicationVO;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertNew(NewsVO newsVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insertNews(NewsVO... newsVOSs);

    @Query("SELECT * FROM news")
    public abstract LiveData<List<NewsVO>> getAllNews();

    @Query("DELETE FROM news")
    public abstract void deleteAllNews();

    @Query("SELECT * FROM News WHERE newsId = :newsId")
    public abstract NewsVO getNew(String newsId);

}
