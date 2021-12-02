package com.vcolofati.convidados.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PresentGuestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PresentGuestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Present guests fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}