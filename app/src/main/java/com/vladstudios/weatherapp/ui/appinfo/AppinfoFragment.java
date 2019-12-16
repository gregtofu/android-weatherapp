package com.vladstudios.weatherapp.ui.appinfo;

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

public class AppinfoFragment extends Fragment {

    private AppinfoViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AppinfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appinfo, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final TextView content = root.findViewById(R.id.Content);


        //Describe the app; duh.
        content.setText("APP DEVELOPED BY\n Théodore BLANC-TALON\n");
        content.append("Version 1.0\n\n\n\n");
        content.append("This weather app gives me access to the current weather conditions around Ivry-sur-Seine.\n");
        content.append("I use it to determine how terribly wet i'll be upon arrival in the morning");
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}