package com.vladstudios.weatherapp;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface OpenWeatherMapApi {

    @GET("weather")
    Call<List<currentWeatherData>> getWeatherData();
}
