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
import com.example.prem.movieapplication.adapter.TvAdapter;
import com.example.prem.movieapplication.model.Tv;
import com.example.prem.movieapplication.model.TvResponse;
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
public class OnTvShowsFragment extends Fragment {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    Unbinder unbinder;

    public OnTvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_on_tv_shows, container, false);

        View view = inflater.inflate(R.layout.recycler_layout,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<TvResponse> call = apiService.getTvOnTheAirList(getResources().getString(R.string.api_key));



        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                List<Tv> discoverTvs = response.body().getResults();
                Log.d(TAG,"OnTvShowsFragment discoverTvs "+response);
                recyclerView.setAdapter(new TvAdapter(discoverTvs,R.layout.list_item,getActivity().getApplicationContext()));

                if (mProgressDialog.isShowing()){

                    mProgressDialog.dismiss();
                }
            }



            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });
    }
   /* @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }*/
}
