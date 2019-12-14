package com.vladstudios.weatherapp;
import com.vladstudios.weatherapp.POJO.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface openWeatherMapApi {

    @GET("weather?id=6452021&APPID=a46bbb1386a9256e516e998243247570 ")
    Call<List<CurrentWeatherData>> getWeatherData();
}
