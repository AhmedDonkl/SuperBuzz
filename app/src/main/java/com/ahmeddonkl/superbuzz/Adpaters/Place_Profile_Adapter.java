    package com.ahmeddonkl.superbuzz.Adpaters;

    import android.app.Activity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
    import com.ahmeddonkl.superbuzz.Miscellaneous.Place_profile;
    import com.ahmeddonkl.superbuzz.R;
    import com.squareup.picasso.Picasso;

    import java.util.List;

    /**
     * Created by Ahmed Dongl on 8/23/2015.
     */

    // our ViewHolder.
    // caches our views
    class ViewHolder
    {
        TextView _Place_name ;
        ImageView _Place_image;
        TextView _Place_category;
    }

    public class Place_Profile_Adapter extends ArrayAdapter<Place_profile> {

        Activity activity;
        int layoutResourceId;
        List<Place_profile> places_items = null;

        public Place_Profile_Adapter(Activity activity,int layoutResourceId,List<Place_profile> places_items) {
            super(activity,layoutResourceId,places_items);
            this.activity = activity;
            this.layoutResourceId = layoutResourceId;
            this.places_items=places_items;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder  holder = null;

            if (convertView == null) {

                // inflate the layout
                LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);

                // well set up the ViewHolder
                holder = new ViewHolder();
                holder._Place_name = (TextView) convertView.findViewById(R.id.place_name_profile);
                holder._Place_category = (TextView) convertView.findViewById(R.id.place_category_profile);
                holder._Place_image = (ImageView) convertView.findViewById(R.id.place_image_profile);

                // store the holder with the view.
                convertView.setTag(holder);
            }
            else
            {
                // we've just avoided calling findViewById() on resource every time
                // just use the viewHolder
                holder = (ViewHolder) convertView.getTag();
            }


            Place_profile obj = places_items.get(position);

            //set place name and category
            holder._Place_name.setText(obj.name);
            holder._Place_category.setText(obj.category);

            //set place image
            Picasso.with(activity).load(obj.image).transform(new CircleTransform()).into(holder._Place_image);

            return convertView;
        }
    }



