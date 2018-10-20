package com.grow.udacitymovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.grow.udacitymovieapp.model.Movie;
import com.grow.udacitymovieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {


    private Movie mMovie;

    @BindView(R.id.iv_movie_thumbnail)      ImageView ivMovieThumbnail;
    @BindView(R.id.tv_movie_title)          TextView tvMovieTitle;
    @BindView(R.id.tv_movie_release_date)   TextView tvMovieReleaseDate;
    @BindView(R.id.tv_movie_vote_average)   TextView tvMovieAverage;
    @BindView(R.id.tv_movie_overview)       TextView tvMovieOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        // Back button
        setupBackButton();

        // Movie Data
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Constants.MOVIE)){
            mMovie = intentThatStartedThisActivity.getParcelableExtra(Constants.MOVIE);

            if (mMovie != null){
                setupMovieUI(mMovie);
            }
        }


    }

    /**
     * Setup BackButton
     * */
    private void setupBackButton() {
        if(getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * Set all Data from movie object received
     * */
    private void setupMovieUI(Movie movie) {
        if(!movie.getTitle().isEmpty())
            tvMovieTitle.setText(movie.getTitle());

        if(!movie.getRelease_date().isEmpty())
            tvMovieReleaseDate.setText(movie.getRelease_date().substring(0,4));

        if(!movie.getOverview().isEmpty())
            tvMovieOverview.setText(movie.getOverview());

        if(!movie.getVote_average().isEmpty()){
            String movie_average = String.format(getResources()
                    .getString(R.string.movie_average_formatted), movie.getVote_average());
            tvMovieAverage.setText(movie_average);
        }
        if(!movie.getThumbnail().isEmpty())
            Picasso.with(this).load(movie.getThumbnail()).into(ivMovieThumbnail);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
