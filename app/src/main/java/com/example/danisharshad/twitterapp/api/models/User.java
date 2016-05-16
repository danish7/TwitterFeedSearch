package com.example.danisharshad.twitterapp.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by danisharshad on 5/14/16.
 */
public class User {

    private String id;
    private String name;
    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("screen_name")
    private String screenName;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", profileImageUrl - " + profileImageUrl +
                '}';
    }
}
