package com.omralcorut.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String NEWS_REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=b36b5eaf-651c-4d40-9a18-97d0acb68793";

    private static final int NEWS_LOADER_ID = 1;

    private NewsAdapter adapter;

    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Create new listView
        ListView newsListView = (ListView) findViewById(R.id.list);

        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyStateTextView);

        //Create custom array adapter called NewsAdapter
        adapter = new NewsAdapter(this, new ArrayList<News>());

        //Connect listView with adapter
        newsListView.setAdapter(adapter);


        //When click item, open webView about that news
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get current news
                News currentNews = adapter.getItem(position);

                //Convert string url to uri
                Uri newsUri = Uri.parse(currentNews.getUrl());

                //Create intent
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                //Open webView about news
                startActivity(websiteIntent);

            }
        });

        //Check internet connection
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    //Load Json parsing
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    //After loading, display result
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_news);

        adapter.clear();

        //Check data exist
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    //Reset loader
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}
