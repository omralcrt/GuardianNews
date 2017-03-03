package com.omralcorut.guardiannews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by omral on 1.03.2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    //Constructor
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    //Return listitem view of news
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        //Get current news
        News currentNews = getItem(position);

        //Initialize title of news
        TextView titleView = (TextView) listItemView.findViewById(R.id.article_title);
        titleView.setText(currentNews.getTitle());

        //Initialize publish date of news
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(formatDate(currentNews.getPublishDate()));

        //Initialize section name of news
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        sectionView.setText(currentNews.getSectionName());


        return listItemView;
    }

    //Format date of news 2016-09-26T15:57:34Z -> 2016-09-26
    private String formatDate(String newsDate) {
        return newsDate.split("T")[0];
    }
}
