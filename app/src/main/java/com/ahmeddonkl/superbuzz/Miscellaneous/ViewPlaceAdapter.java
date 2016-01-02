package com.ahmeddonkl.superbuzz.Miscellaneous;

/**
 * Created by Ahmed Dongl on 8/21/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ahmeddonkl.superbuzz.Place_Tabs.Comments_Tap;
import com.ahmeddonkl.superbuzz.Place_Tabs.Info_Tap;
import com.ahmeddonkl.superbuzz.Place_Tabs.Photos_Tab;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPlaceAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPlaceAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Comments_Tap tab1 = new Comments_Tap();
            return tab1;
        }
        else if(position == 1)            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Info_Tap tab2 = new Info_Tap();
            return tab2;
        }

        else            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Photos_Tab tab3 = new Photos_Tab();
            return tab3;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}