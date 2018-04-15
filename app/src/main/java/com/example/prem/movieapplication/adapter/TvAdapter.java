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
import com.example.prem.movieapplication.model.Tv;
import com.squareup.picasso.Picasso;

import java.util.List;



public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private List<Tv> discoverTvs;
    private Context context;
    private int rowLayout;
    private static final String TAG = "TvAdapter";

    public class TvViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,rating,releaseDate,overview;
        public TvViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.name);
            rating = (TextView) v.findViewById(R.id.rating);
            releaseDate = (TextView) v.findViewById(R.id.releaseDate);
            overview = (TextView) v.findViewById(R.id.overview);

            overview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", discoverTvs.get(getAdapterPosition()).getName());
                    bundle.putString("overview", discoverTvs.get(getAdapterPosition()).getOverview());
                    bundle.putString("releasedate", discoverTvs.get(getAdapterPosition()).getFirstAirDate());
                    bundle.putString("voteavarage", discoverTvs.get(getAdapterPosition()).getVoteAverage().toString() + "/10");
                    //bundle.putString("image", "http://image.tmdb.org/t/p/w185"+discoverTvs.get(getAdapterPosition()));
                    bundle.putString("image", discoverTvs.get(getAdapterPosition()).getPosterPath());

                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
    public TvAdapter(List<Tv> discoverTvs, int rowLayout, Context context){
        this.discoverTvs = discoverTvs;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);

        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TvAdapter.TvViewHolder holder, int position) {

        String overview = discoverTvs.get(position).getOverview().toString();
        String newString = overview.substring(0,20);

        holder.title.setText("Title: "+discoverTvs.get(position).getName());
        holder.releaseDate.setText("Release Date: "+discoverTvs.get(position).getFirstAirDate());
        holder.rating.setText("Rating: "+discoverTvs.get(position).getVoteAverage().toString()+"/10");
        holder.overview.setText("Overview: "+newString+"\n "+"Moreinfo ...");
        Log.d(TAG,"movie postion "+discoverTvs.get(position));
        /*holder.overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Moreinfo clicked ",Toast.LENGTH_LONG).show();
            }
        });*/
//https://image.tmdb.org/t/p/w780/
       /* Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+discoverTvs.get(position).
                getPosterPath())
                .resize(120,152).into(holder.imageView);*/
        Picasso.with(context).load("https://image.tmdb.org/t/p/w780"+discoverTvs.get(position).
                getPosterPath())
                .resize(120,152).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return discoverTvs.size();
    }
}
