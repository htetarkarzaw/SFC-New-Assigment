package com.padcmyanmar.sfc.datas.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.datas.vo.FavoriteActionVO;

import java.util.List;

@Dao
public abstract class FavroiteActionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertFavoriteAction(FavoriteActionVO favoriteAction);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertFavoriteActions(List<FavoriteActionVO> favoriteActions);

    @Query("SELECT * FROM favoriteaction")
    public abstract LiveData<List<FavoriteActionVO>> getAllFavoriteActions();

    @Query("SELECT * FROM FavoriteAction WHERE favoriteId =:newsId")
    public abstract List<FavoriteActionVO> getFavoriteActionsByNewsId(String newsId);

    @Query("DELETE FROM favoriteaction")
    public abstract void deleteAll();

    public void insertFavoriteById(String newsId, String actedUserId, FavoriteActionVO favoriteActionVO) {
        favoriteActionVO.setNewsId(newsId);
        favoriteActionVO.setUserId(actedUserId);
        insertFavoriteAction(favoriteActionVO);
    }
}
