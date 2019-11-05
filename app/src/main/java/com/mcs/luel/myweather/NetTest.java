package com.mcs.luel.myweather;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface NetTest {
//    OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.weather.gov/")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build();
}
