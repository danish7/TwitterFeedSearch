package com.example.danisharshad.twitterapp.api;

import com.example.danisharshad.twitterapp.api.models.AccessToken;
import com.example.danisharshad.twitterapp.api.models.Tweets;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by danisharshad on 5/14/16.
 */
public interface RestApi {

    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    @POST("oauth2/token")
    @FormUrlEncoded
    Call<AccessToken> createUser(@Field("grant_type") String grantType, @Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @GET("1.1/search/tweets.json")
    Call<Tweets> getTweets(@Header("Authorization") String authorization, @Query("q") String searchQuery);
}
