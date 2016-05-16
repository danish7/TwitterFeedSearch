package com.example.danisharshad.twitterapp.api.models;

import java.util.List;

/**
 * Created by danisharshad on 5/15/16.
 */
public class Tweets {

    private List<Status> statuses;

    public List<Status> getStatuses() {
        return statuses;
    }


    @Override
    public String toString() {
        return "Tweets{" +
                "statuses=" + statuses +
                '}';
    }
}
