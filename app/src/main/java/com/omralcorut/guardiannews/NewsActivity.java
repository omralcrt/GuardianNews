package com.omralcorut.guardiannews;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Create fake news list
        ArrayList<News> news = new ArrayList<>();
        news.add(new News("The Nixon-Kennedy presidential debates: from the archive, 1960",
                "US news", "2016-09-26T15:57:34Z",
                "https://www.theguardian.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960"));
        news.add(new News("The next Doctor Who, a black Bond … the pop culture debates that never end",
                "Culture", "2017-02-06T07:00:20Z",
                "https://www.theguardian.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960"));
        news.add(new News("Get real-time reactions during the presidential debates | Guardian Mobile Innovation Lab",
                "Opinion", "2016-09-23T21:43:23Z",
                "https://www.theguardian.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960"));
        news.add(new News("Make or break: the defining moments of presidential debates",
                "US news", "2016-09-25T10:00:31Z",
                "https://www.theguardian.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960"));

        //Create new listView
        ListView newsListView = (ListView) findViewById(R.id.list);

        //Create custom array adapter called NewsAdapter
        final NewsAdapter adapter = new NewsAdapter(this, news);

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

    }
}
