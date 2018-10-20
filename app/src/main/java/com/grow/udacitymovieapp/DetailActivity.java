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

    @BindView(R.id.iv_movie_thumbnail)
    ImageView ivMovieThumbnail;
    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.tv_movie_release_date)
    TextView tvMovieReleaseDate;
    @BindView(R.id.tv_movie_vote_average)
    TextView tvMovieAverage;
    @BindView(R.id.tv_movie_overview)
    TextView tvMovieOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        Intent intentThatStartedThisActivity = getIntent();

        if(getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (intentThatStartedThisActivity.hasExtra(Constants.MOVIE_ITEM)){
            String movieStr = intentThatStartedThisActivity.getStringExtra(Constants.MOVIE_ITEM);
            mMovie = new Gson().fromJson(movieStr, Movie.class);

            tvMovieTitle.setText(mMovie.getTitle());
            tvMovieReleaseDate.setText(mMovie.getRelease_date().substring(0,4));
            tvMovieOverview.setText(mMovie.getOverview());

            String movie_average = String.format(getResources()
                    .getString(R.string.movie_average_formatted), mMovie.getVote_average());
            tvMovieAverage.setText(movie_average);

            Picasso.with(this).load(mMovie.getThumbnail()).into(ivMovieThumbnail);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
