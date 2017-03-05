package com.example.android.amity;

/**
 * Created by Marilyn on 3/5/2017.
 */

public class Post {
    private String title;
    private String author;
    private String content;

    public Post(String t, String a, String c)
    {
        title = t;
        author =a;
        content=c;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getContent()
    {
        return content;
    }
}
