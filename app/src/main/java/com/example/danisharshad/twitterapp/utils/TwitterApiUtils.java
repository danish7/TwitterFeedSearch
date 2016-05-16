package com.example.danisharshad.twitterapp.utils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.example.danisharshad.twitterapp.R;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by danisharshad on 5/15/16.
 */
public class TwitterApiUtils {

    private static final String TAG = "TwitterApiUtils";

    public static String getBase64EncodedConsumerKeySecret (Context context) throws IOException {
        String urlApiKey = URLEncoder.encode(context.getString(R.string.api_consumer_key), "UTF-8");
        String urlApiSecret = URLEncoder.encode(context.getString(R.string.api_consumer_secret), "UTF-8");
        String combined = urlApiKey + ":" + urlApiSecret;
        Log.v(TAG, "The combined string is " + combined);

        return Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);
    }
}
