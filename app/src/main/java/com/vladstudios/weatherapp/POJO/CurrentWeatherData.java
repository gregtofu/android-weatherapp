package com.vladstudios.weatherapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherData {

    private Main main;
    private Sys sys;
    private List<Weather> weather = null;
    private Wind wind;

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}