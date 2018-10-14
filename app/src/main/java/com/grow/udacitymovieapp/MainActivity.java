package com.grow.udacitymovieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.grow.udacitymovieapp.adapters.MovieAdapter;
import com.grow.udacitymovieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // TODO (1) Change with Data Api
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Logan","http://www.joblo.com/assets/images/oldsite/posters/images/full/logan-poster-3.jpg"));
        movies.add(new Movie("Titanic","https://imgc.allpostersimages.com/img/print/u-g-F4S6CQ0.jpg?w=302&h=450"));
        movies.add(new Movie("The Greatest Showman","https://static1.showtimes.com/poster/660x980/the-greatest-showman-122540.jpg"));
        movies.add(new Movie("Moonlight","https://upload.wikimedia.org/wikipedia/en/8/84/Moonlight_%282016_film%29.png"));

        mAdapter = new MovieAdapter(movies, this,this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void clickItemListener(int indexPosition) {
        Toast.makeText(this, "position " + indexPosition, Toast.LENGTH_SHORT).show();
        // TODO (2) Go to a DetailView Android
    }
}
