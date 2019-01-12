package com.example.tanvigupta.movieapp.network;

import com.example.tanvigupta.movieapp.model.CastMovieDetail.CastMovieResponse;
import com.example.tanvigupta.movieapp.model.CastPhotos.CastPhotosResponse;
import com.example.tanvigupta.movieapp.model.CastTvDetail.Cast;
import com.example.tanvigupta.movieapp.model.CastTvDetail.CastTvResonse;
import com.example.tanvigupta.movieapp.model.PersonDetail.PersonResponse;
import com.example.tanvigupta.movieapp.model.PopularPeople.PopularPeopleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CreditService {


    @GET("{id}")
    Call<PersonResponse> getpersondetail(@Path("id") Long id, @Query("api_key") String api_Key);

    @GET("{id}/tv_credits")
    Call<CastTvResonse> getcasttv(@Path("id") Long id,@Query("api_key") String api_Key);

    @GET("{id}/movie_credits")
    Call<CastMovieResponse> getcastmovie(@Path("id") Long id,@Query("api_key") String api_Key);

    @GET("popular")
    Call<PopularPeopleResponse> getpopularppl(@Query("api_key") String api_Key,@Query("page") int page_no);

    @GET("{id}/images")
    Call<CastPhotosResponse> getcastphotos(@Path("id") Long id,@Query("api_key") String api_Key);


}
