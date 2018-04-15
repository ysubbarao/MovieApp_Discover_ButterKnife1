package com.example.prem.movieapplication.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prem.movieapplication.R;
import com.example.prem.movieapplication.adapter.MovieAdapter;
import com.example.prem.movieapplication.model.Movie;
import com.example.prem.movieapplication.model.MovieResponse;
import com.example.prem.movieapplication.rest.ApiClient;
import com.example.prem.movieapplication.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDiscoverFragment extends Fragment {

    private static final String TAG = "MovieDiscoverFragment";

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    Unbinder unbinder;

    public MovieDiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_movie_discover, container, false);
        View view = inflater.inflate(R.layout.recycler_layout,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(this,view);
       // recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getMovieDiscoverList(getResources().getString(R.string.api_key));


        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();

                List<Movie> movies = response.body().getResults();
                Log.d(TAG,"movies"+movies.toString());

                recyclerView.setAdapter(new MovieAdapter(movies,R.layout.list_item,getActivity().getApplicationContext()));



                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });



    }
   /* public void onDestroy() {
        super.onDestroy();
        // unbind the view to free some memory
        unbinder.unbind();
    }*/
}
