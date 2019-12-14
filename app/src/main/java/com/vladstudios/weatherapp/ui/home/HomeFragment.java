package com.vladstudios.weatherapp.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vladstudios.weatherapp.R;

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
        return root;
    }
    /*class weatherTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API);
            return response;
        }
    }*/
}