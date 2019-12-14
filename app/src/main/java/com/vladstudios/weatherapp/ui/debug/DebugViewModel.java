package com.vladstudios.weatherapp.ui.debug;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DebugViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DebugViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("API RESPONSE TEST");
    }

    public LiveData<String> getText() {
        return mText;
    }
}