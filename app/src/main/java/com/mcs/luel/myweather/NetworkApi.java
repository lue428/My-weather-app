package com.mcs.luel.myweather;

import com.mcs.luel.myweather.PojoClasses.ForecastPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkApi {


    @GET("points/{lon},{lat}/forecast")
    Call<ForecastPojo> getDailyForecast(
            @Path("lon") String lon,
            @Path("lat") String lat
    );

    @GET("points/{lon},{lat}/forecast/hourly")
    Call<ForecastPojo> getHourlyForecast(
            @Path("lon") String lon,
            @Path("lat") String lat
        );

}
