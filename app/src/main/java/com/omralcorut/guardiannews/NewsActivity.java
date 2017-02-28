package com.omralcorut.guardiannews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Create fake news list
        ArrayList<String> news = new ArrayList<>();
        news.add("Magazine");
        news.add("Teror");
        news.add("Sport");
        news.add("Social");
        news.add("Technology");
        news.add("Morning");
        news.add("Night");

        //Create new listView
        ListView newsListView = (ListView) findViewById(R.id.list);

        //Create new arrayAdapter with newsArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, news);

        //Connect listView with adapter
        newsListView.setAdapter(adapter);

    }
}
