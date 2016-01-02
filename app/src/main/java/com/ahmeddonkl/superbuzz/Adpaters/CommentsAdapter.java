package com.ahmeddonkl.superbuzz.Adpaters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
import com.ahmeddonkl.superbuzz.Miscellaneous.Comment;
import com.ahmeddonkl.superbuzz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ahmed Dongl on 8/25/2015.
 */

    /**
     * Created by Ahmed Dongl on 3/6/2015.
     */

    // our ViewHolder.
    // caches our views
    class View_Comment_Holder
    {
        TextView _User_name ;
        RatingBar _User_stars ;
        TextView _User_post ;
        ImageView _User_image ;
    }

    public class CommentsAdapter extends ArrayAdapter<Comment>
    {

        Activity activity;
        int layoutResourceId;
        List<Comment> comments_items = null;

        public CommentsAdapter(Activity activity,int layoutResourceId,List<Comment> comments_items)
        {
            super(activity,layoutResourceId,comments_items);
            this.activity = activity;
            this.layoutResourceId = layoutResourceId;
            this.comments_items=comments_items;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {


            View_Comment_Holder  holder = null;

            if (convertView == null)
            {

                // inflate the layout
                LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);

                // well set up the ViewHolder
                holder = new View_Comment_Holder();
                holder._User_name = (TextView) convertView.findViewById(R.id.user_name_comment);
                holder._User_post = (TextView) convertView.findViewById(R.id.feedback_comment);
                holder._User_image = (ImageView) convertView.findViewById(R.id.user_image_comment);
                holder._User_stars = (RatingBar) convertView.findViewById(R.id.place_rate_comment);

                // store the holder with the view.
                convertView.setTag(holder);
            }
            else
            {
                // we've just avoided calling findViewById() on resource every time
                // just use the viewHolder
                holder = (View_Comment_Holder) convertView.getTag();
            }

            Comment obj = comments_items.get(position);
            holder._User_name.setText(obj.name);
            holder._User_stars.setNumStars(obj.stars);
            holder._User_post.setText(obj.post);

            //set place image
            Picasso.with(activity).load(obj.image).transform(new CircleTransform()).into(holder._User_image);

            return convertView;
        }
    }

