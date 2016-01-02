package com.ahmeddonkl.superbuzz.Adpaters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
import com.ahmeddonkl.superbuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Dongl on 3/6/2015.
 */
public class Place_Photo_adapter extends BaseAdapter{

    Activity activity;
    ArrayList<String> photos = new ArrayList<String>();

    public Place_Photo_adapter(Activity activity, ArrayList<String> photos) {
        this.activity = activity;
        this.photos=photos;

    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.place_photo_item,null,true);
        }

        ImageView image=(ImageView) convertView.findViewById(R.id.place_photo);

        //set place image
        if(!photos.get(position).equals(""))
        Picasso.with(activity).load(photos.get(position)).transform(new CircleTransform()).into(image);

        return convertView;
    }
}

