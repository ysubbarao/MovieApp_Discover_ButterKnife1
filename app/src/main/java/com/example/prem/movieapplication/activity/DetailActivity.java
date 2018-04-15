package com.example.prem.movieapplication.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prem.movieapplication.R;
import com.example.prem.movieapplication.fragment.DetailFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    //@BindView(R.id.fragment)
    //FrameLayout fragment;

    //android.support.v4.app.FragmentManager framentManager;
    @BindView(R.id.image_view) ImageView image_view;
    @BindView(R.id.youtube) ImageView youtube;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.releaseDate)TextView releaseDate;
    @BindView(R.id.rating)TextView rating;
    @BindView(R.id.overview) TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_detail);
        setContentView(R.layout.fragment_detail);
        ButterKnife.bind(this);

        //initiateView();

        Bundle getBundle = null;
        getBundle = getIntent().getExtras();

        String title = getBundle.getString("title");
        String overview1 = getBundle.getString("overview");
        String releasedate = getBundle.getString("releasedate");
        String voteavarage = getBundle.getString("voteavarage");
        String posterPath = getBundle.getString("image");

        //Picasso.with(context).load(url).into(imageView);
       /* Picasso.with(this).load("http://image.tmdb.org/t/p/w185" + posterPath)
                .resize(120, 152).into(image_view);*/

        Picasso.with(this).load("https://image.tmdb.org/t/p/w780" + posterPath)
                .resize(120, 152).into(image_view);

                //resize(120, 152).into(holder.imageView);
        name.setText(title);
        releaseDate.setText(releasedate);
        overview.setText("Overview: \n"+overview1);
        rating.setText(voteavarage);
        youtube.setImageResource(R.drawable.youtube);

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivity.this,VideoPlayer.class);
                startActivity(intent);

            }
        });


        //Toast.makeText(this, "title name "+title, Toast.LENGTH_SHORT).show();


        /*DetailFragment detailFragment = new DetailFragment();

        framentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = framentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,detailFragment);
        fragmentTransaction.commit();*/
    }

   /* private void initiateView() {

        image_view = (ImageView) findViewById(R.id.image_view);
        name = (TextView) findViewById(R.id.name);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
        rating = (TextView) findViewById(R.id.rating);
        overview = (TextView) findViewById(R.id.overview);
    }*/
}
