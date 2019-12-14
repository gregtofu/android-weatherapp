package com.vladstudios.weatherapp.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vladstudios.weatherapp.POJO.CurrentWeatherData;
import com.vladstudios.weatherapp.R;
import com.vladstudios.weatherapp.openWeatherMapApi;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView homeTitle = root.findViewById(R.id.homeTitle);
        final TextView currentTemp = root.findViewById(R.id.currentTemp);
        final TextView sunsetText = root.findViewById(R.id.sunsetText);
        final TextView sunriseText = root.findViewById(R.id.sunriseText);
        final TextView humidityText = root.findViewById(R.id.humidityText);
        final TextView windForceText = root.findViewById(R.id.windForceText);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                homeTitle.setText(s);
            }
        });


        //Add a new Retrofit object that will allow us to call the openweathermap API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create an instance of CurrentWeatherData and fill it with the content from the API response
        openWeatherMapApi data = retrofit.create(openWeatherMapApi.class);

        //Then create a list containing the answer to API calls
        Call<CurrentWeatherData> call = data.getWeatherData();
        call.enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if(!response.isSuccessful()) {
                    homeTitle.setText("Code : "+response.code());
                    return;
                }

                //put the API response in a CurrentWeatherData object
                CurrentWeatherData weatherData = response.body();

                //then put the API response in the correct fields
                homeTitle.setText(weatherData.getWeather().get(0).getDescription());
                currentTemp.setText("" + weatherData.getMain().getTemp() + "°C");
                humidityText.setText("" + weatherData.getMain().getHumidity() + "%");
                sunriseText.setText("" + formatUnixDate(weatherData.getSys().getSunrise()));
                sunsetText.setText("" + formatUnixDate(weatherData.getSys().getSunset()));
                windForceText.setText("" + weatherData.getWind().getSpeed());
            }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                homeTitle.setText("ERROR\n " + t.getMessage());
            }
        });

        return root;
    }

    //simple date formatting
    private String formatUnixDate(long d){
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(tz);
        String time = sdf.format(new Date(d * 1000));
        return time;
    }
}