package com.padcmyanmar.sfc.datas.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aung on 12/3/17.
 */

@Entity(tableName = "CommentAction",
        foreignKeys = {@ForeignKey(entity = NewsVO.class, parentColumns = "newsId", childColumns = "newsId"),
                @ForeignKey(entity = ActedUserVO.class, parentColumns = "ActedUserId", childColumns = "userId")})
public class CommentActionVO {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "commentActionId")
    @SerializedName("comment-id")
    private String commentId;

    @SerializedName("comment")
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    private String newsId;

    private String userId;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @NotNull
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(@NotNull String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }
}
