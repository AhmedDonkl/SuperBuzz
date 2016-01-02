package com.ahmeddonkl.superbuzz.Place_Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.ahmeddonkl.superbuzz.Adpaters.CommentsAdapter;
import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
import com.ahmeddonkl.superbuzz.Miscellaneous.Comment;
import com.ahmeddonkl.superbuzz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Dongl on 8/22/2015.
 */
public class Comments_Tap extends Fragment {

    //tweets list
    private ListView list;

    //Comment Data
    private EditText comment_edit;

    //User Image
    private ImageView user_image;

    //list adapter
    public static CommentsAdapter commentsAdapter;

    //Add Comment
    ImageButton add_comment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comments_tab,container,false);

        //Link views
        comment_edit = (EditText)v.findViewById(R.id.user_place_comment);
        list=(ListView) v.findViewById(R.id.comments_place_list);
        user_image=(ImageView)v.findViewById(R.id.user_comment_image);

        //Get User Image and Name Which Saved on Shared Prefe
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("User_Data", Context.MODE_PRIVATE);

        //get data of user
        final String user_name =  sharedpreferences.getString("User_name"," ");
        final String user_image_url =  sharedpreferences.getString("User_image"," ");

        Log.d("name",user_name);
        Log.d("url",user_image_url);
        //add user image
        Picasso.with(getActivity()).load(user_image_url).transform(new CircleTransform()).into(user_image);

        //request previous data of places comments then add to this list

        //HERE

        List<Comment> comments_items = new ArrayList<Comment>();
        //create object from custom list adapter and publish data
        commentsAdapter=new CommentsAdapter(getActivity(),R.layout.comment_place_item,comments_items);
        list.setAdapter(commentsAdapter);

        //set on click to add comment
        //add when press button
        add_comment = (ImageButton) v.findViewById(R.id.add_comment);
        add_comment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arsg0)
            {

                //get comment
                String comment_text = comment_edit.getText().toString();

                //get stars
                //make dummy stars
                int stars=4;

                commentsAdapter.add(new Comment(user_name,user_image_url,comment_text,stars));
                commentsAdapter.notifyDataSetChanged();
            }
        });



        return v;
    }
}
