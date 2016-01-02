package com.ahmeddonkl.superbuzz.Place_Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ahmeddonkl.superbuzz.Adpaters.Place_Photo_adapter;
import com.ahmeddonkl.superbuzz.R;

import java.util.ArrayList;

/**
 * Created by Ahmed Dongl on 8/22/2015.
 */
public class Photos_Tab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.photo_tab,container,false);

        GridView gridView=(GridView)v.findViewById(R.id.place_photos_grid);

        //get the photo urls from DB and put it this list
        ArrayList<String> photos = new ArrayList<String>();
        photos.add("");photos.add("");photos.add("");
        Place_Photo_adapter obj=new Place_Photo_adapter(getActivity(),photos);
        gridView.setAdapter(obj);


        return v;
    }
}
