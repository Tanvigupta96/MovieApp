package com.example.tanvigupta.movieapp.network;

import com.example.tanvigupta.movieapp.model.BothCast.CastResponse;
import com.example.tanvigupta.movieapp.model.MovieDetail.MovieDetailResponse;
import com.example.tanvigupta.movieapp.model.Movies.MovieResponse;
import com.example.tanvigupta.movieapp.model.Reviews.ReviewResponse;
import com.example.tanvigupta.movieapp.model.SimilarMovie.SimilarMovieResponse;
import com.example.tanvigupta.movieapp.model.Videos.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("popular")
    Call<MovieResponse> getpopularmovies(@Query("api_key") String apiKey, @Query("page") int pageNo);

    @GET("top_rated")
    Call<MovieResponse> gettopratedmovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("upcoming")
    Call<MovieResponse> getupcoming(@Query("api_key") String apiKey,@Query("page") int page);


    @GET("{id}")
    Call<MovieDetailResponse> getDetailofmovie(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("{id}/similar")
    Call<SimilarMovieResponse> getsimilarmovies(@Path("id") long id, @Query("api_key") String apiKey, @Query("page") int page);

    @GET("{id}/credits")
    Call<CastResponse> getcast(@Path("id")long id, @Query("api_key") String apiKey);


    @GET("{id}/reviews")
    Call<ReviewResponse> getreviews(@Path("id") long id, @Query("api_key") String apiKey);


    @GET("{id}/videos")
    Call<VideoResponse> getvideos(@Path("id") long id, @Query("api_key") String apiKey);



}

