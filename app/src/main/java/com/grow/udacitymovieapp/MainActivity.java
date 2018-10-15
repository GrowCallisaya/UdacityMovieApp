package com.grow.udacitymovieapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.grow.udacitymovieapp.adapters.MovieAdapter;
import com.grow.udacitymovieapp.model.Movie;
import com.grow.udacitymovieapp.utils.NetworkUtils;
import com.grow.udacitymovieapp.utils.TheMovieDBUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        new MovieAsyncTask().execute();

        mAdapter = new MovieAdapter(this,this);
        mRecyclerView.setAdapter(mAdapter);




    }

    @Override
    public void onClick(int movieClicked) {
        Toast.makeText(this, "position " + movieClicked, Toast.LENGTH_SHORT).show();
        // TODO (2) Go to a DetailView Android
    }

    public class MovieAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            URL movieRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromUrl(movieRequestUrl);

                List<Movie> simpleJsonMovieData = TheMovieDBUtils.
                        getSimpleMovieDataStringsFromJson(MainActivity.this, jsonMovieResponse);

                return simpleJsonMovieData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            if (movies != null) {
                mAdapter.setMovieData(movies);
            } else {
                showErrorMessage();
            }


        }
    }

    private void showErrorMessage() {
    }
}
