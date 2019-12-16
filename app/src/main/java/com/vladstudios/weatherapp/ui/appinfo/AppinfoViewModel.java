package com.vladstudios.weatherapp.ui.appinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppinfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppinfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("APPLICATION INFORMATION");
    }

    public LiveData<String> getText() {
        return mText;
    }
}