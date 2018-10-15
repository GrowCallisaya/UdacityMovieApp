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
    private static final String MOVIEDBAPI_URL = "http://api.themoviedb.org/3/discover/movie";

    // Params
    private static final String API_KEY_PARAM = "api_key";
    private static final String SORT_PARAM= "sort";


    // Values
    private static final String popularity_desc = "popularity_desc.desc";
    // TODO (1) SET YOUR API KEY HERE
    private static final String api_key= "[API_KEY]";



    /**
     * Method that Build your Url from the MovieDB API
     * Example:
     *      http://api.themoviedb.org/3/discover/movie?sort=popularity_desc.desc&api_key=[API_KEY]
     * @return  URL     Url Created
     * **/
    public static URL buildUrl(){
        Uri builtUri = Uri.parse(MOVIEDBAPI_URL).buildUpon()
                .appendQueryParameter(SORT_PARAM, popularity_desc)
                .appendQueryParameter(API_KEY_PARAM, api_key)
                .build();

        URL url = null;

        try {
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
