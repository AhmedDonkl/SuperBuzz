package com.ahmeddonkl.superbuzz.Adpaters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
import com.ahmeddonkl.superbuzz.R;

import java.util.ArrayList;

/**
 * Created by Ahmed Dongl on 8/17/2015.
 */

public class Wish_List_adapter extends BaseAdapter {

    Activity activity;
    ArrayList<String> remind = new ArrayList<String>();
    ArrayList<String> category = new ArrayList<String>();
    public Wish_List_adapter(Activity activity, ArrayList<String> category, ArrayList<String> remind) {
        this.activity = activity;
        this.remind=remind;
        this.category=category;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return remind.size();
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

        Wish_List_ViewHolder holder = null;

        if (convertView == null)
        {
            // inflate the layout
            LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.wish_list_item,null,true);

            // well set up the ViewHolder
            holder = new Wish_List_ViewHolder();
            holder.Wish_Item_Image = (ImageView) convertView.findViewById(R.id.wish_item_image);
            holder.Wish_Item_Category = (TextView) convertView.findViewById(R.id.wish_item_category);
            holder.Wish_Item_Remind = (TextView) convertView.findViewById(R.id.wish_item_remind);
            // store the holder with the view.
            convertView.setTag(holder);
        }
        else
        {
            // we've just avoided calling findViewById() on resource every time
            // just use the viewHolder
            holder = (Wish_List_ViewHolder) convertView.getTag();
        }

          //publish Category text
          holder.Wish_Item_Category.setText(category.get(position));

        //set text color of category depend on it
//       holder.Wish_Item_Category.setTextColor(Wish_List.Category_Colors.get(category.get(position)));

        //set remind text
        holder.Wish_Item_Remind.setText(remind.get(position));

        //make image as circle
        Bitmap bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.silver_wish_item);
        CircleTransform circleTransform = new CircleTransform();
        holder.Wish_Item_Image.setImageBitmap(circleTransform.transform(bm));

        //set Done image as circle
        bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.disabled_checkbox_buzz);
        ((ImageView)convertView.findViewById(R.id.done_image)).setImageBitmap(circleTransform.transform(bm));

        return convertView;
    }

}

    // our ViewHolder.
    // caches our views
    class Wish_List_ViewHolder
    {
        ImageView Wish_Item_Image ;
        TextView Wish_Item_Category ;
        TextView Wish_Item_Remind ;
    }

