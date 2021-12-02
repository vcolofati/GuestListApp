package com.vcolofati.convidados.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllGuestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllGuestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("All guests fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}