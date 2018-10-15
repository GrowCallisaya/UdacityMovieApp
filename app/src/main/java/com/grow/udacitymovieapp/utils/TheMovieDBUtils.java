package com.grow.udacitymovieapp.utils;

import android.content.Context;

import com.grow.udacitymovieapp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by growcallisaya on 10/15/18.
 */

public final class TheMovieDBUtils {



    public static List<Movie> getSimpleMovieDataStringsFromJson(Context context, String jsonMovieStr) throws JSONException {

        final String OWN_TITLE = "title";
        final String OWN_THUMBNAIL = "poster_path";
        final String OWN_OVERVIEW= "overview";
        final String OWN_VOTE_AVERAGE = "vote_average";
        final String OWN_RELEASE_DATE = "release_date";

        JSONObject movieJson = new JSONObject(jsonMovieStr);
        JSONArray moviesArray = movieJson.getJSONArray("results");


        List<Movie> parseMovieData = new ArrayList<>();

        for (int i = 0; i< moviesArray.length(); i ++){

            JSONObject movie = moviesArray.getJSONObject(i);

            String movie_title = movie.getString(OWN_TITLE);

            String imageJPG =movie.getString(OWN_THUMBNAIL);
            String movie_thumbnail = createPathfromJPG(imageJPG);

            String movie_overview = movie.getString(OWN_OVERVIEW);
            String movie_vote_average = movie.getString(OWN_VOTE_AVERAGE);
            String movie_release_date= movie.getString(OWN_RELEASE_DATE);

            parseMovieData.add(new Movie(movie_title,
                                        movie_thumbnail,
                                        movie_overview,
                                        movie_vote_average,
                                        movie_release_date ));

        }
        return parseMovieData;
    }

    public static String createPathfromJPG(String imgJPG) {

        final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
        final String IMAGE_SIZE = "w185";

        String urlImage = IMAGE_BASE_URL + IMAGE_SIZE + imgJPG;
        return urlImage;
    }
}
