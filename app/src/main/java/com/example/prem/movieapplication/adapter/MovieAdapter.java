package com.example.prem.movieapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prem.movieapplication.R;
import com.example.prem.movieapplication.activity.DetailActivity;
import com.example.prem.movieapplication.activity.GenreDetailsActivity;
import com.example.prem.movieapplication.fragment.DetailFragment;
import com.example.prem.movieapplication.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {
    private static final String TAG = "MovieAdapter";

    private List<Movie> movies;
    private Context context;
    private int rowLayout;
    Intent intent;

    public class MoviewViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, releaseDate, rating, overview;

        public MoviewViewHolder(View v) {
            super(v);

            imageView = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.name);
            releaseDate = (TextView) v.findViewById(R.id.releaseDate);
            rating = (TextView) v.findViewById(R.id.rating);
            overview = (TextView) v.findViewById(R.id.overview);

            overview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", movies.get(getAdapterPosition()).getTitle());
                    bundle.putString("overview", movies.get(getAdapterPosition()).getOverview());
                    bundle.putString("releasedate", movies.get(getAdapterPosition()).getReleaseDate(""));
                    bundle.putString("voteavarage", movies.get(getAdapterPosition()).getVoteAverage().toString() + "/10");
                   // bundle.putString("image", "http://image.tmdb.org/t/p/w185"+movies.get(getAdapterPosition()));
                    bundle.putString("image", movies.get(getAdapterPosition()).getPosterPath());

                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            });

        }

    }

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.context = context;
        this.rowLayout = rowLayout;
    }


    @Override
    public MoviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviewViewHolder holder, int position) {

        String overview = movies.get(position).getOverview().toString();
        String newString = overview.substring(0, 20);
        holder.title.setText("Title: " + movies.get(position).getTitle());
        holder.releaseDate.setText("Release Date: " + movies.get(position).getReleaseDate(""));
        holder.rating.setText("Rating: " + movies.get(position).getVoteAverage().toString() + "/10");
        //holder.overview.setText("Overview "+movies.get(position).getOverview().toString()+"/n");
        holder.overview.setText("Overview: " + newString + "\n " + "Moreinfo...");
        Log.d(TAG, "movie postion " + movies.get(position));
        //https://image.tmdb.org/t/p/w780/

        /*Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + movies.get(position).
                getPosterPath())
                .resize(120, 152).into(holder.imageView);*/

        Picasso.with(context).load("https://image.tmdb.org/t/p/w780" + movies.get(position).
                getPosterPath())
                .resize(120, 152).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
