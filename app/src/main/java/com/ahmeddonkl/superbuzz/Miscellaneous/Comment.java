package com.ahmeddonkl.superbuzz.Miscellaneous;

import java.io.Serializable;

/**
 * Created by Ahmed Dongl on 8/25/2015.
 */
public class Comment implements Serializable {

    public String name = "";
    public String post = "";
    public String image = "";
    public int stars;

    public Comment(String name, String image, String post, int stars) {
        super();
        this.stars = stars;
        this.name = name;
        this.post = post;
        this.image = image;

    }
}