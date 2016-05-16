package com.example.danisharshad.twitterapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danisharshad.twitterapp.R;
import com.example.danisharshad.twitterapp.api.models.Status;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danisharshad on 5/15/16.
 */
public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.TweetViewHolder> {

    private List<Status> tweets;

    public TweetsAdapter () {
        tweets = new ArrayList<>();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,
                parent, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        Status tweetStatus = tweets.get(position);
        holder.screenName.setText("@" + tweetStatus.getUser().getScreenName());
        holder.tweet.setText(tweetStatus.getText());
        holder.userName.setText(tweetStatus.getUser().getName());
        System.out.println("The profile image is " + tweetStatus.getUser().getProfileImageUrl());
        Picasso.with(holder.profileImage.getContext()).load(tweetStatus.getUser().getProfileImageUrl())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return (tweets == null) ? 0 : tweets.size();
    }

    static class TweetViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private TextView screenName;
        private TextView tweet;
        private ImageView profileImage;

        public TweetViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.username);
            screenName = (TextView) itemView.findViewById(R.id.screen_name);
            tweet = (TextView) itemView.findViewById(R.id.tweet);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_image);
        }

    }


    public void setTweets(List<Status> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }


}