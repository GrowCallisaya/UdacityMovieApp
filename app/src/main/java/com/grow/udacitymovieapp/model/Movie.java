package com.grow.udacitymovieapp.model;

/**
 * Created by growcallisaya on 10/13/18.
 */

public class Movie {

    String title;
    String image_url;

    public Movie(String title, String image_url) {
        this.title = title;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
