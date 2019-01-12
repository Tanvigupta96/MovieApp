package com.example.tanvigupta.movieapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientForSearch {
    private static Retrofit retrofit;
    private static SearchService searchService;


    static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/search/").client(client)
                    .addConverterFactory(GsonConverterFactory.create());


            retrofit = builder.build();
        }
        return retrofit;

    }

    public static SearchService getSearchService() {
        if (searchService == null) {
            searchService= getInstance().create(SearchService.class);
        }
        return searchService;
    }
}
