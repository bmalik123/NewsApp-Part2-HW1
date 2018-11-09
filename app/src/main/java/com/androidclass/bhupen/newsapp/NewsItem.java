package com.androidclass.bhupen.newsapp;

public class NewsItem {
    private String title, description, url, date;

    public NewsItem(String title, String description, String url, String date) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }


}
