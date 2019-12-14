package com.vladstudios.weatherapp.ui.debug;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.vladstudios.weatherapp.POJO.CurrentWeatherData;
import com.vladstudios.weatherapp.openWeatherMapApi;
import com.vladstudios.weatherapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DebugFragment extends Fragment {

    private DebugViewModel debugViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        debugViewModel =
                ViewModelProviders.of(this).get(DebugViewModel.class);
        View root = inflater.inflate(R.layout.fragment_debug, container, false);

        //Declare the TextView seen in the notificationsFragment
        final TextView textView = root.findViewById(R.id.APIResponseText);

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
                    textView.setText("Code : "+response.code());
                    return;
                }

                //put the API response in a CurrentWeatherData object
                CurrentWeatherData weatherData = response.body();

                //then put the API response in the debug textview
                    String content = "";
                    content += "Main : " + weatherData.getWeather().get(0).getMain()+ "\n";
                    content += "Temperature : " + weatherData.getMain().getTemp() + "\n";
                    content += "Humidity : " + weatherData.getMain().getHumidity() + "\n";
                    content += "Sunrise time" + weatherData.getSys().getSunrise() + "\n";
                    content += "Sunset time" + weatherData.getSys().getSunset() + "\n";

                    textView.append(content);
                }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                textView.setText("ERROR\n " + t.getMessage());
            }
        });
        return root;
    }
}