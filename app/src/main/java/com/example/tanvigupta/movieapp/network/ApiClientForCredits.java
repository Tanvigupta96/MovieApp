package com.example.tanvigupta.movieapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientForCredits {
    private static Retrofit retrofit;
    private static CreditService creditService;


    static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/person/").client(client)
                    .addConverterFactory(GsonConverterFactory.create());


            retrofit = builder.build();
        }
        return retrofit;

    }

    public static CreditService getCreditService() {
        if (creditService == null) {
            creditService = getInstance().create(CreditService.class);
        }
        return creditService;
    }
}
