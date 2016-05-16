package com.example.danisharshad.twitterapp.ui.activities;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danisharshad.twitterapp.R;
import com.example.danisharshad.twitterapp.api.models.Status;
import com.example.danisharshad.twitterapp.loaders.TweetLoader;
import com.example.danisharshad.twitterapp.adapters.TweetsAdapter;
import com.example.danisharshad.twitterapp.utils.UiUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Status>>,
                                                                View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int LOADER_ID = 1;

    public static final String SEARCH_QUERY = "SEARCH_QUERY";

    private TweetsAdapter mAdapter;
    private String mSearchQuery;
    private ProgressDialog mProgressDialog;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSearchButton = (Button) findViewById(R.id.search_button);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.tweet_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEditText = (EditText) findViewById(R.id.search_input);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_tweets));
        mAdapter = new TweetsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            mSearchQuery = savedInstanceState.getString(SEARCH_QUERY);
            if (!TextUtils.isEmpty(mSearchQuery)) {
                Bundle args = new Bundle();
                args.putString(SEARCH_QUERY, mSearchQuery);
                getLoaderManager().restartLoader(LOADER_ID, args, this);
                mProgressDialog.show();
            }
        }

        mSearchButton.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mSearchQuery != null) {
            outState.putString(SEARCH_QUERY, mSearchQuery);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<List<Status>> onCreateLoader(int id, Bundle args) {
        return new TweetLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<List<Status>> loader, List<Status> data) {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (data != null) {
            mAdapter.setTweets(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Status>> loader) {

    }

    @Override
    public void onClick(View v) {
        UiUtils.hideKeyBoard(v);
        Bundle args = new Bundle();
        Editable text = mEditText.getText();
        mSearchQuery = text != null ? text.toString() : "";
        args.putString(SEARCH_QUERY, mSearchQuery);
        mProgressDialog.show();
        getLoaderManager().restartLoader(LOADER_ID, args, MainActivity.this);
    }
}
