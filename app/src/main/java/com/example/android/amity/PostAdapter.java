package com.example.android.amity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marilyn on 3/5/2017.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Activity activity, ArrayList<Post> posts) {
        super(activity,0, posts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.template_post_item,parent,false);
        }

        Post currentPost = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentPost.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentPost.getAuthor());

        TextView content = (TextView) listItemView.findViewById(R.id.content);
       content.setText(currentPost.getContent());

        return listItemView;
    }
}
