package com.guo.beans;

/**
 * Created by guo_w on 2019/5/16.
 */



import java.io.Serializable;

public class HeartNews implements Serializable {
    private String title;
    private String picurl;
    private String newsurl;

    public HeartNews() {
    }

    public HeartNews(String title, String picurl, String newsurl) {
        this.title = title;
        this.picurl = picurl;
        this.newsurl = newsurl;
    }

    @Override
    public String toString() {
        return "HeartNews{" +
                "title='" + title + '\'' +
                ", picurl='" + picurl + '\'' +
                ", newsurl='" + newsurl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl;
    }
}

