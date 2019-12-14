package com.vladstudios.weatherapp.ui.notifications;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

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
        Call<List<CurrentWeatherData>> call = data.getWeatherData();
        call.enqueue(new Callback<List<CurrentWeatherData>>() {
            @Override
            public void onResponse(Call<List<CurrentWeatherData>> call, Response<List<CurrentWeatherData>> response) {
                if(!response.isSuccessful()) {
                    textView.setText("Code : "+response.code());
                    return;
                }

                List<CurrentWeatherData> weatherData = response.body();
                for (CurrentWeatherData w : weatherData) {
                    String content = "";
                    content += "Main : " + w.getMain() + "\n";
                    content += "Temperature : " + w.getTemperature() + "\n";
                    content += "Humidity : " + w.getHumidity() + "\n";
                    content += "Sunrise time" + w.getSunrise() + "\n";
                    content += "Sunset time" + w.getSunset() + "\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<CurrentWeatherData>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
        return root;
    }
}