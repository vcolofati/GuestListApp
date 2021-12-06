package com.vcolofati.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

import java.util.List;

public class AllGuestsViewModel extends AndroidViewModel {

    private GuestRepository mRepository;

    private MutableLiveData<List<Guest>> mGuestLiveData = new MutableLiveData<>();
    public LiveData<List<Guest>> guestLiveData = this.mGuestLiveData;

    public AllGuestsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = GuestRepository.getInstance(application.getApplicationContext());
    }


    public void getList() {
        this.mGuestLiveData.setValue(this.mRepository.getGuestList());
    }
}