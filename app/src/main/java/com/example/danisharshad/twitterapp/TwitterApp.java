package com.example.danisharshad.twitterapp;

import android.app.Application;

import com.example.danisharshad.twitterapp.api.RestApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danisharshad on 5/14/16.
 */
public class TwitterApp extends Application {

    private static RestApi mApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initApi();
    }

    private void initApi () {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(RestApi.class);
    }

    public static RestApi getApi () {
        return mApi;
    }
}
