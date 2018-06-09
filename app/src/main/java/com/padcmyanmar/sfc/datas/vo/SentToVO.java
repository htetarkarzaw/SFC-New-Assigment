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
@Entity(tableName = "SentToAction",
        foreignKeys = {@ForeignKey(entity = NewsVO.class, parentColumns = "newsId", childColumns = "newsId"),
                @ForeignKey(entity = ActedUserVO.class, parentColumns = "ActedUserId", childColumns = "senderUserId"),
                @ForeignKey(entity = ActedUserVO.class, parentColumns = "ActedUserId", childColumns = "receiverUserId")})
public class SentToVO {

    @PrimaryKey
    @NotNull
    @SerializedName("send-to-id")
    private String sendToId;

    @ColumnInfo(name = "sentDate")
    @SerializedName("sent-date")
    private String sentDate;

    private String newsId;

    private String senderUserId;

    private String receiverUserId;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO sender;

    @Ignore
    @SerializedName("received-user")
    private ActedUserVO receiver;

    @NotNull
    public String getSendToId() {
        return sendToId;
    }

    public void setSendToId(@NotNull String sendToId) {
        this.sendToId = sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public ActedUserVO getSender() {
        return sender;
    }

    public void setSender(ActedUserVO sender) {
        this.sender = sender;
    }

    public ActedUserVO getReceiver() {
        return receiver;
    }

    public void setReceiver(ActedUserVO receiver) {
        this.receiver = receiver;
    }
}
