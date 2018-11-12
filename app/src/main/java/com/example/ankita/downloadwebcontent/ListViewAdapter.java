package com.example.ankita.downloadwebcontent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public  class ListViewAdapter extends ArrayAdapter{

    //the hero list that will be displayed
private List<Hero> heroList;

    //the context object
private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Hero> heroList, Context mCtx) {
        super(mCtx, R.layout.list_item, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
    View listViewItem = inflater.inflate(R.layout.list_item,null,true);

        //getting text views
        ImageView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.textViewImageUrl);

        //Getting the hero for the specified position
    Hero hero = heroList.get(position);

        //setting hero values to textviews
        Picasso.get().load(hero.getName()).into(textViewName);
//    textViewName.setText(hero.getName());
    textViewImageUrl.setText(hero.getImageUrl());

        //returning the listitem
return listViewItem;

    }
}
