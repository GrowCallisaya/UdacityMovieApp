package com.grow.udacitymovieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grow.udacitymovieapp.R;
import com.grow.udacitymovieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Movie Adapter
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movie> mMovieData;
    private MovieAdapterOnClickHandler mClickHandler;
    private Context mContext;

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler, Context context){
        this.mClickHandler = clickHandler;
        this.mContext = context;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movieClicked);
    }


    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;
            return mMovieData.size();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutResouce = R.layout.movie_item;

        View view = inflater.inflate(layoutResouce ,viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    public void setMovieData (List<Movie> movieData){
        this.mMovieData = movieData;
        notifyDataSetChanged();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie_cover);
            itemView.setOnClickListener(this);
        }


        public void bind(int position){
            Movie movie = mMovieData.get(position);
            Picasso.with(mContext).load(movie.getThumbnail()).into(imageView);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mMovieData.get(adapterPosition));
        }

    }
}
