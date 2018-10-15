package com.grow.udacitymovieapp.model;

/**
 * Movie Class that receive the Main Object
 */

public class Movie {

    String title;
    String thumbnail;
    String overview;
    String vote_average;
    String release_date;

    public Movie(String title, String thumbnail, String overview, String vote_average, String release_date) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
