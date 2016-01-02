package com.ahmeddonkl.superbuzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login_Fragment extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener, OnRequestSocialPersonCompleteListener {
    public static SocialNetworkManager mSocialNetworkManager;
    /**
     * SocialNetwork Ids in ASNE:
     * 1 - Twitter
     * 4 - Facebook
     */
    private Button facebook;
    private Button twitter;

    //Data needed For User
    public String user_id;
    public String user_name;
    public String user_image_url;

    public static SocialNetwork socialNetwork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_login__fragment, container, false);
        ((Login)getActivity()).getSupportActionBar().setTitle(R.string.app_name);

        // init buttons and set Listener
        facebook = (Button) rootView.findViewById(R.id.facebook);
        facebook.setOnClickListener(loginClick);
        twitter = (Button) rootView.findViewById(R.id.twitter);
        twitter.setOnClickListener(loginClick);

        //Get Keys for initiate SocialNetworks
        String TWITTER_CONSUMER_KEY = getActivity().getString(R.string.twitter_consumer_key);
        String TWITTER_CONSUMER_SECRET = getActivity().getString(R.string.twitter_consumer_secret);
        String TWITTER_CALLBACK_URL = "https://superbuzz.com";

        //Choose permissions
        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email, user_friends"));

        //Use manager to manage SocialNetworks
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(Login.SOCIAL_NETWORK_TAG);

        //Check if manager exist
        if (mSocialNetworkManager == null)
        {
            mSocialNetworkManager = new SocialNetworkManager();

            //Init and add to manager FacebookSocialNetwork
            FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this,fbScope);
            mSocialNetworkManager.addSocialNetwork(fbNetwork);

            //Init and add to manager TwitterSocialNetwork
            TwitterSocialNetwork twNetwork = new TwitterSocialNetwork(this, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_CALLBACK_URL);
            mSocialNetworkManager.addSocialNetwork(twNetwork);


            //Initiate every network from mSocialNetworkManager
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, Login.SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        }
        else
        {
            //if manager exist - get and setup login only for initialized SocialNetworks
            if(!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty())
            {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks)
                {
                    socialNetwork.setOnLoginCompleteListener(this);
                    initSocialNetwork(socialNetwork);
                }
            }
        }

        return rootView;
    }

    private void initSocialNetwork(SocialNetwork socialNetwork)
    {
        if(socialNetwork.isConnected())
        {
            switch (socialNetwork.getID())
            {
                case FacebookSocialNetwork.ID:
                    facebook.setText("Show Facebook profile");
                    break;
                case TwitterSocialNetwork.ID:
                    twitter.setText("Show Twitter profile");
                    break;
            }
        }
    }

    @Override
    public void onSocialNetworkManagerInitialized()
    {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks())
        {
            socialNetwork.setOnLoginCompleteListener(this);
            initSocialNetwork(socialNetwork);
        }
    }

    //Login listener
    private View.OnClickListener loginClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            int networkId = 0;
            switch (view.getId())
            {
                case R.id.facebook:
                    networkId = FacebookSocialNetwork.ID;
                    break;
                case R.id.twitter:
                    networkId = TwitterSocialNetwork.ID;
                    break;
            }

            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if(!socialNetwork.isConnected())
            {
                if(networkId != 0)
                {
                    socialNetwork.requestLogin();
                    Login.showProgress("Loading social person");
                }
                else
                {
                    Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                }
            }

        }
    };

    @Override
    public void onLoginSuccess(int networkId)
    {
        Login.hideProgress();

        //Save Login Profile Network ID
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("User_Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("networkId", networkId);
        editor.commit();
        //load person data from facebook or twitter
        socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();

        Login.showProgress("Loading social person");

        //Load Profile
        Intent intent = new Intent(getActivity(),Profile.class);
        startActivity(intent);

    }

    //to get profile data and save it on shared pref
    @Override
    public void onRequestSocialPersonSuccess(int i, SocialPerson socialPerson)
    {
        Login.hideProgress();

        //get data of login person to save in our data base
        user_id=socialPerson.id;
        user_image_url=socialPerson.avatarURL;
        user_name=socialPerson.name;

        //Save User Data
        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences("User_Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("User_id", user_id);
        editor.putString("User_name", user_name);
        editor.putString("User_image", user_image_url);
        editor.commit();


        //You Should save These Data on our DB
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data)
    {
        Login.hideProgress();
        Toast.makeText(getActivity(), "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
    }

}
