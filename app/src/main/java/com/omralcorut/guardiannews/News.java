package com.omralcorut.guardiannews;

/**
 * Created by omral on 1.03.2017.
 */

public class News {

    private String title;
    private String sectionName;
    private String publishDate;
    private String url;

    //Constructor of News
    public News(String title, String sectionName, String publishDate, String url) {
        this.title = title;
        this.sectionName = sectionName;
        this.publishDate = publishDate;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getUrl() {
        return url;
    }
}
