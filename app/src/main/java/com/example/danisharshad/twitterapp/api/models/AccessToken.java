package com.example.danisharshad.twitterapp.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by danisharshad on 5/14/16.
 */
public class AccessToken {

    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("access_token")
    private String accessToken;


    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
