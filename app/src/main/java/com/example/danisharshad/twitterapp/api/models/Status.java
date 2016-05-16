package com.example.danisharshad.twitterapp.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by danisharshad on 5/14/16.
 */
public class Status {

    @SerializedName("created_at")
    private String createdAt;

    private String id;
    private String text;
    private User user;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "Status{" +
                "createdAt='" + createdAt + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
