package com.example.android.amity;

import static android.R.attr.name;

/**
 * Created by Marilyn on 3/5/2017.
 */

public class Post {
    private String title;
    private String author;
    private String content;
    private String date;
    private String country;
    private String city;

    public Post( String author, String title, String content, String date, String country, String city) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
        this.country = country;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }
    public  String getCity(){
        return city;
    }

    @Override
    public String toString() {
        return "Author: " + getAuthor() + " Title: " + getTitle() + " Content: " + getContent()
                + " Date: "+getDate() + " Country: " + getCountry()  +" City: " + getCity();
    }
}
