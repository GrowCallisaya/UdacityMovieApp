package com.grow.udacitymovieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie Class that receive the Main Object
 */

public class Movie implements Parcelable{

    private String title;
    private String thumbnail;
    private String overview;
    private String vote_average;
    private String release_date;

    public Movie(String title, String thumbnail, String overview, String vote_average, String release_date) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    /**
     * Read Movie from Parcel */

    public Movie(Parcel parcel) {
        title = parcel.readString();
        thumbnail = parcel.readString();
        overview = parcel.readString();
        vote_average = parcel.readString();
        release_date = parcel.readString();
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


    /**
     * Write write Movie values to parcel for storage */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeString(overview);
        dest.writeString(vote_average);
        dest.writeString(release_date);
    }


    /**
     * Create - used when un-parceling our parcel (creating the object)
     */
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    /**
     * Return Hashcode of Objects */
    @Override
    public int describeContents() {
        return hashCode();
    }



}
