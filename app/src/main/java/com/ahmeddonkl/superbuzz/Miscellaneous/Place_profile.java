package com.ahmeddonkl.superbuzz.Miscellaneous;

import java.io.Serializable;

/**
 * Created by Ahmed Dongl on 8/23/2015.
 */


public class Place_profile implements Serializable {

    public String name = "";
    public String image="";
    public String category="";

    public Place_profile(String name , String image ,String category) {
        super();
        this.name=name;
        this.image=image;
        this.category=category;

    }

}