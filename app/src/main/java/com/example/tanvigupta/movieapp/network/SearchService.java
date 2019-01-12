package com.example.tanvigupta.movieapp.network;

import com.example.tanvigupta.movieapp.model.Search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("multi")
    Call<SearchResponse> getsearchedmovie(@Query("api_key") String apiKey,@Query("query")String query);
}
