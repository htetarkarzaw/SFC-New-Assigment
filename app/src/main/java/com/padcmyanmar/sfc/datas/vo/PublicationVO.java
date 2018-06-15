package com.padcmyanmar.sfc.datas.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "Publication")
public class PublicationVO {

    @PrimaryKey
    @NonNull
    @SerializedName("publication-id")
    private String publicationId;

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    private String title;

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @SerializedName("logo")
    private String logo;


    @NonNull
    public String getPublicationId() {
        if(publicationId!=null){
            return publicationId;
        }
        return null;
    }

    public void setPublicationId(@NotNull String publicationId) {
        this.publicationId = publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }
}
