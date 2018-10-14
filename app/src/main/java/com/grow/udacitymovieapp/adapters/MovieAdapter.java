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

    private List<Movie> mListOfMovies;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public MovieAdapter(List<Movie> listOfMovies,OnItemClickListener listener, Context context){
        this.mListOfMovies = listOfMovies;
        this.onItemClickListener = listener;
        this.mContext = context;
    }

    public interface OnItemClickListener {
        void clickItemListener(int indexPosition);
    }


    @Override
    public int getItemCount() {
        return mListOfMovies.size();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutResouce = R.layout.movie_item;
        boolean attachInmediately = false;

        View view = inflater.inflate(layoutResouce ,viewGroup,attachInmediately);
        MovieViewHolder holder = new MovieViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie_cover);

            itemView.setOnClickListener(this);
        }


        public void bind(int position){
            Movie movie = mListOfMovies.get(position);
            Picasso.with(mContext).load(movie.getImage_url()).into(imageView);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onItemClickListener.clickItemListener(adapterPosition);
        }
    }
}
