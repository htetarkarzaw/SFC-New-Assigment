package com.padcmyanmar.sfc.datas.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.datas.vo.SentToVO;

import java.util.List;

@Dao
public abstract class SentToDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertSendTo(SentToVO sentTo);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertSendTos(List<SentToVO> sentTos);

    @Query("SELECT * FROM senttoaction")
    public abstract LiveData<List<SentToVO>> getAllSendTos();

    @Query("SELECT * FROM senttoaction WHERE senderUserId =:newsId")
    public abstract List<SentToVO> getSendToActionsByNewsId(String newsId);

    @Query("DELETE FROM senttoaction")
    public abstract void deleteAll();

    public void insertSendToById(String newsId, String senderId, String receiverId, SentToVO sentToVO) {
        sentToVO.setNewsId(newsId);
        sentToVO.setSenderUserId(senderId);
        sentToVO.setReceiverUserId(receiverId);
        insertSendTo(sentToVO);
    }
}
