package com.padcmyanmar.sfc.datas.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "NewsInImages" , foreignKeys = @ForeignKey(entity = NewsVO.class , parentColumns = "newsId" , childColumns = "newsId"))
public class NewsInImagesVO {

    @PrimaryKey
    @NotNull
    private int newInImageId;

    private String imageUrl;

    private String newsId;

    @NotNull
    public int getNewInImageId() {
        return newInImageId;
    }

    public void setNewInImageId(@NotNull int newInImageId) {
        this.newInImageId = newInImageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
