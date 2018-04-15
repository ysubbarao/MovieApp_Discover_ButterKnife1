package com.example.prem.movieapplication.rest;


import com.example.prem.movieapplication.model.GenreResponse;
import com.example.prem.movieapplication.model.MovieResponse;
import com.example.prem.movieapplication.model.PeopleResponse;
import com.example.prem.movieapplication.model.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


   // https://api.themoviedb.org/3/    movie/top_rated    ?api_key=<<api_key>>&language=en-US&page=1

    @GET("genre/movie/list")
    Call<GenreResponse> getGenreList(@Query("api_key") String apikey);

    @GET("discover/movie")
    Call<MovieResponse> getMovieDiscoverList(@Query("api_key") String apikey);

    @GET("discover/tv")
    Call<TvResponse> getTvDiscoverList(@Query("api_key") String apikey);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovieList(@Query("api_key") String apikey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovieList(@Query("api_key")String apikey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovieList(@Query("api_key") String apikey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovieList(@Query("api_key") String apikey);

    @GET("tv/popular")
    Call<TvResponse> getTvPopularList(@Query("api_key") String apikey);

    @GET("tv/top_rated")
    Call<TvResponse> getTvTopRatedList(@Query("api_key") String apikey);

    @GET("tv/airing_today")
    Call<TvResponse> getTvOnTheAirList(@Query("api_key") String apikey);

    @GET("person/popular")
    Call<PeopleResponse> getPopularPeopleList(@Query("api_key") String apikey);






//    @GET("/genre/{genre_id}/movies")
//    Call<GenreResponse> getGenreMovieList(@Path("genre_id") int id,
//                                  @Query("api_key") String apikey);


}
