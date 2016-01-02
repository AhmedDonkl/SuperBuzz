package com.ahmeddonkl.superbuzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmeddonkl.superbuzz.Miscellaneous.CircleTransform;
import com.ahmeddonkl.superbuzz.Miscellaneous.SlidingTabLayout;
import com.ahmeddonkl.superbuzz.Miscellaneous.ViewPagerAdapter;
import com.squareup.picasso.Picasso;

public class Profile extends ActionBarActivity {

    //profile View
    ImageView user_profile_image;
    TextView user_profile_name;

    //for taps
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"My Places","Favorite"};
    int Numboftabs =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Link Views
        user_profile_name = (TextView) findViewById(R.id.profile_user_name);
        user_profile_image = (ImageView )findViewById(R.id.profile_user_image);

        //Set Profile
        //You Should bring these data from DB not shared pref
        SharedPreferences sharedpreferences = this.getSharedPreferences("User_Data", Context.MODE_PRIVATE);

        //get data of user
        String id =  sharedpreferences.getString("User_id","");
        String name =  sharedpreferences.getString("User_name","");
        String image_url =  sharedpreferences.getString("user_image_url","");


        //set Profile Name
        user_profile_name.setText(name);

        //Set Profile Image
        if(! image_url.equals(""))
        Picasso.with(getApplicationContext()).load(image_url).transform(new CircleTransform()).into(user_profile_image);

        //Taps Handle
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.twitter);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_logout:
                Login_Fragment.socialNetwork.logout();
            case R.id.profile_home:
                Intent intent = new Intent(this,Home.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
