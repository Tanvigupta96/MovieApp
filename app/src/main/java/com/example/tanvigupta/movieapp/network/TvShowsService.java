package com.example.tanvigupta.movieapp.network;

import com.example.tanvigupta.movieapp.model.BothCast.Cast;
import com.example.tanvigupta.movieapp.model.EpisodeStills.EpisodeStillsResponse;
import com.example.tanvigupta.movieapp.model.Reviews.ReviewResponse;
import com.example.tanvigupta.movieapp.model.SeasonVideos.SeasonVideosResponse;
import com.example.tanvigupta.movieapp.model.SimilarTvShows.SimilarTvResponse;
import com.example.tanvigupta.movieapp.model.BothCast.CastResponse;
import com.example.tanvigupta.movieapp.model.TvEpisodesDetail.TvEpisodesResponse;
import com.example.tanvigupta.movieapp.model.TvShows.TvShowResponse;
import com.example.tanvigupta.movieapp.model.Videos.VideoResponse;
import com.example.tanvigupta.movieapp.model.Tvdetail.TvDetailResponse;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvShowsService {

    @GET("airing_today")
    Call<TvShowResponse> getAiringtodaytvshows(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("popular")
    Call<TvShowResponse> getpopulartvshows(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("top_rated")
    Call<TvShowResponse> gettopratedtvshows(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("on_the_air")
    Call<TvShowResponse> getontheairtvshows(@Query("api_key") String apiKey, @Query("page") int page);


    @GET("{id}")
    Call<TvDetailResponse> getDetailoftvshow(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("{id}/similar")
    Call<SimilarTvResponse> getsimilartvshows(@Path("id") long id, @Query("api_key") String apiKey, @Query("page") int page);

    @GET("{id}/credits")
    Call<CastResponse> getcast(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("{id}/videos")
    Call<VideoResponse> getvideos(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("{id}/reviews")
    Call<ReviewResponse> getreviews(@Path("id") long id, @Query("api_key") String apiKey);


    @GET("{id}/season/{seasonnumber}")
    Call<TvEpisodesResponse> getEpisodes(@Path("id") long id, @Path("seasonnumber") long seasonnumber, @Query("api_key")String apiKey);


    @GET("{id}/season/{seasonnumber}/episode/{episodenumber}/credits")
    Call<com.example.tanvigupta.movieapp.model.EpisodeWiseCast.CastResponse> getseasoncast(@Path("id") long id, @Path("seasonnumber") long seasonnumber, @Path("episodenumber") long episodenumber, @Query("api_key") String apiKey);


    @GET("{id}/season/{seasonnumber}/episode/{episodenumber}/images")
    Call<EpisodeStillsResponse> getstills(@Path("id") long id, @Path("seasonnumber") long seasonnumber, @Path("episodenumber") long episodenumber, @Query("api_key") String apiKey);

    @GET("{id}/season/{seasonnumber}/videos")
    Call<SeasonVideosResponse> getseasonvideos(@Path("id") long id,@Path("seasonnumber") long seasonnumber,@Query("api_key") String apiKey);

}
