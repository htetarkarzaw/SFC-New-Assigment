package com.padcmyanmar.sfc.datas.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.datas.vo.NewsInImagesVO;
import com.padcmyanmar.sfc.datas.vo.NewsVO;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class NewsInImagesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertNewsInImage(NewsInImagesVO newsInImage);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertNewsInImages(List<NewsInImagesVO> newsInImage);

    @Query("SELECT * FROM newsinimages")
    public abstract LiveData<List<NewsInImagesVO>> getAllNewsInImages();

    @Query("SELECT * FROM newsinimages WHERE newsId =:newsId")
    public abstract List<NewsInImagesVO> getImagesByNewsId(String newsId);

    @Query("DELETE FROM newsinimages")
    public abstract void deleteAll();

    public void insertImageWithNews(NewsVO newsVO) {
        List<NewsInImagesVO> newsInImageVOs = new ArrayList<>();

        List<String> images = newsVO.getImages();
        for (int i = 0; i < images.size(); i++) {
            NewsInImagesVO newsInImage = new NewsInImagesVO();
            newsInImage.setNewsId(newsVO.getNewsId());
            newsInImage.setImageUrl(images.get(i));

            newsInImageVOs.add(newsInImage);
        }

        insertNewsInImages(newsInImageVOs);
    }

    public void bindImages(NewsVO newsVO) {
        List<String> images = new ArrayList<>();

        List<NewsInImagesVO> newsInImageVOs = getImagesByNewsId(newsVO.getNewsId());
        for (NewsInImagesVO newsInImage : newsInImageVOs) {
            images.add(newsInImage.getImageUrl());
        }

        newsVO.setImages(images);
    }
}
