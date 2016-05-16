package com.example.danisharshad.twitterapp.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.example.danisharshad.twitterapp.api.models.AccessToken;
import com.example.danisharshad.twitterapp.api.models.Status;
import com.example.danisharshad.twitterapp.api.models.Tweets;
import com.example.danisharshad.twitterapp.TwitterApp;
import com.example.danisharshad.twitterapp.ui.activities.MainActivity;
import com.example.danisharshad.twitterapp.utils.TwitterApiUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;

/**
 * Created by danisharshad on 5/15/16.
 */
public class TweetLoader extends AsyncTaskLoader<List<Status>> {

    private static final String TAG = "TweetLoader";

    private List<Status> tweetsData;
    private String searchQuery;

    public TweetLoader(Context context, Bundle args) {
        super(context);
        searchQuery = args.getString(MainActivity.SEARCH_QUERY);
    }

    @Override
    public List<Status> loadInBackground() {
        try {
            HashMap<String,String> bodyMap = new HashMap<>();
            bodyMap.put("grant_type", "client_credentials");

            Call<AccessToken> call = TwitterApp.getApi().
                    createUser("client_credentials", "Basic " +
                            TwitterApiUtils.getBase64EncodedConsumerKeySecret(getContext()));

            AccessToken accessToken;
            accessToken = call.execute().body();

            if (accessToken != null && !TextUtils.isEmpty(searchQuery)) {

                Call<Tweets> getTweets = TwitterApp.getApi().
                        getTweets("Bearer " + accessToken.getAccessToken(), searchQuery);
                Tweets statuses = getTweets.execute().body();
                tweetsData = statuses.getStatuses();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweetsData;
    }

    @Override
    public void deliverResult(List<Status> data) {
        if (isReset()) {
            return;
        }
        tweetsData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (tweetsData != null) {
            deliverResult(tweetsData);
        }

        if (takeContentChanged() || tweetsData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        // Ensure the loader has been stopped.
        onStopLoading();
        tweetsData = null;
    }

    @Override
    public void onCanceled(List<Status> data) {
        super.onCanceled(data);
    }

}
