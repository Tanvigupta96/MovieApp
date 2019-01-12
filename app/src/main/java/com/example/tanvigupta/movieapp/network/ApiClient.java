package com.example.tanvigupta.movieapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static MovieService movieService;


    static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/movie/").client(client)
                    .addConverterFactory(GsonConverterFactory.create());


            retrofit = builder.build();
        }
        return retrofit;

    }

    public static MovieService getMovieService() {
        if (movieService == null) {
            movieService = getInstance().create(MovieService.class);
        }
        return movieService;
    }
}
