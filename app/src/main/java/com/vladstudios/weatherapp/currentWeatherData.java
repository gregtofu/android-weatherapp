package com.vladstudios.weatherapp;

import com.google.gson.annotations.SerializedName;

public class currentWeatherData {

    @SerializedName("weather.main")
    private String main;
    @SerializedName("sys.sunrise")
    private int sunrise;
    @SerializedName("sys.sunset")
    private int sunset;
    @SerializedName("wind.speed")
    private double wind_speed;
    @SerializedName("main.temp")
    private double temperature;
    @SerializedName("main.humidity")
    private double humidity;

    public String getMain() {
        return main;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}