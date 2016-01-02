package com.ahmeddonkl.superbuzz.Profile_Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ahmeddonkl.superbuzz.Adpaters.Place_Profile_Adapter;
import com.ahmeddonkl.superbuzz.Miscellaneous.Place_profile;
import com.ahmeddonkl.superbuzz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Dongl on 8/22/2015.
 */
public class Place_Tab extends Fragment {


    //list view  adapter
    ListView places_list;
    //list adapter
    public Place_Profile_Adapter place_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.places_tab,container,false);

        //link view
        places_list = (ListView) v.findViewById(R.id.user_places_list);

        //get user places
        //in here return your data from DB (user_places_name,user_places_image,user_places_category)
        //pass it to the adapter

        List<Place_profile> places_items = new ArrayList<Place_profile>();

        //Some Dummy Data

        places_items.add(new Place_profile("Cairo","http://image.tmdb.org/t/p/w185/sLbXneTErDvS3HIjqRWQJPiZ4Ci.jpg","restaurant"));
        places_items.add(new Place_profile("Alex","http://image.tmdb.org/t/p/w185/kqjL17yufvn9OVLyXYpvtyrFfak.jpg","Caffe"));
        //create object from custom list adapter and publish data
        place_adapter=new Place_Profile_Adapter(getActivity(),R.layout.profile_list_item,places_items);
        //set adapter
        places_list.setAdapter(place_adapter);
        return v;
    }
}
