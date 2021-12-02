package com.vcolofati.convidados.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AbsentGuestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AbsentGuestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Absent Guests Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}