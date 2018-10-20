package com.grow.udacitymovieapp.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Utils for Connection
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    // Server URL
    private static final String MOVIEDBAPI_URL = "http://api.themoviedb.org/3";

    private static final String POPULAR_URL = MOVIEDBAPI_URL + "/movie/popular";
    private static final String TOP_RATED_URL = MOVIEDBAPI_URL + "/movie/top_rated";

    // Params
    private static final String API_KEY_PARAM = "api_key";
    private static final String LANGUAGE_PARAM= "language";


    // Values
    private static final String language = "en-US";

    // TODO (1) SET YOUR API KEY HERE
    private static final String api_key= "[API_KEY]";



    /**
     * Method that Build your Url from the MovieDB API
     * Example:
     *      http://api.themoviedb.org/3/discover/movie?sort=popularity_desc.desc&api_key=[API_KEY]
     * @return  URL     Url Created
     * **/
    public static URL buildUrl(String typeOfMovie){
        Uri builtUri = null;

        if (typeOfMovie.equals(Constants.POPULAR))
            builtUri = Uri.parse(POPULAR_URL).buildUpon()
                    .appendQueryParameter(API_KEY_PARAM, api_key)
                    .appendQueryParameter(LANGUAGE_PARAM, language)
                    .build();

        if (typeOfMovie.equals(Constants.TOP_RATED))
            builtUri = Uri.parse(TOP_RATED_URL).buildUpon()
                    .appendQueryParameter(API_KEY_PARAM, api_key)
                    .appendQueryParameter(LANGUAGE_PARAM, language)
                    .build();

        URL url = null;

        try {
            if (builtUri != null)
                url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }


    /**
     * Method that get the response from Server with the URL as a parameter
     * @param   url       Url of the endpoint
     * @return  String    JsonResponse from Server
     * **/
    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
