package com.grow.udacitymovieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.grow.udacitymovieapp.adapters.MovieAdapter;
import com.grow.udacitymovieapp.model.Movie;
import com.grow.udacitymovieapp.utils.Constants;
import com.grow.udacitymovieapp.utils.NetworkUtils;
import com.grow.udacitymovieapp.utils.TheMovieDBUtils;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new MovieAsyncTask(Constants.POPULAR).execute();

        mAdapter = new MovieAdapter(this,this);
        mRecyclerView.setAdapter(mAdapter);



    }


    @Override
    public void onClick(Movie movieClicked) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.MOVIE, movieClicked);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == R.id.sort_popular) populatePopularMovies();
        if (itemSelected == R.id.sort_top_rated) populateTopMovies();
        return super.onOptionsItemSelected(item);
    }


    /**
     * Show all Top Rated Movies **/
    private void populatePopularMovies() {
        new MovieAsyncTask(Constants.POPULAR).execute();
    }

    /**
     * Show all Popular Movies **/
    private void populateTopMovies() {
        new MovieAsyncTask(Constants.TOP_RATED).execute();
    }


    public class MovieAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        public final String mTypeOfMovies;

        public MovieAsyncTask(String typeOfMovies) {
            mTypeOfMovies = typeOfMovies;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {

            URL movieRequestUrl = NetworkUtils.buildUrl(mTypeOfMovies);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromUrl(movieRequestUrl);

                return TheMovieDBUtils.
                        getSimpleMovieDataStringsFromJson(MainActivity.this, jsonMovieResponse);
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
        Toast.makeText(this, getString(R.string.no_data_loaded), Toast.LENGTH_SHORT).show();
    }
}
