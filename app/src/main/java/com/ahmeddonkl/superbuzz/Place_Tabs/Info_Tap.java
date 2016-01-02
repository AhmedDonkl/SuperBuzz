package com.ahmeddonkl.superbuzz.Place_Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ahmeddonkl.superbuzz.R;

/**
 * Created by Ahmed Dongl on 8/22/2015.
 */
public class Info_Tap extends Fragment
{

    //Our Views
    TextView confirm_num;
    TextView report_num;
    TextView descreption;
    ImageButton like;
    ImageButton dislike;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_tab,container,false);

        //link views
        confirm_num = (TextView)v.findViewById(R.id.confirm_number);
        report_num = (TextView)v.findViewById(R.id.report_number);
        descreption = (TextView)v.findViewById(R.id.place_description);
        like = (ImageButton)v.findViewById(R.id.add_like_place);
        dislike = (ImageButton)v.findViewById(R.id.add_report_place);


        //when press button
        like.setOnClickListener(Info_TabClick);
        dislike.setOnClickListener(Info_TabClick);

        //get data about place from data base and publish it
        //publish data in the views

        return v;
    }

    private View.OnClickListener Info_TabClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.add_like_place:
                    //add like to this place in DB
                    //here
                    break;
                case R.id.add_report_place:
                    //add Report to this place in DB
                    //here
                    break;
            }


        }
    };
}
