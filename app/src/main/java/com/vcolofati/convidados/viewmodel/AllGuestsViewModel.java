package com.vcolofati.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vcolofati.convidados.enums.GuestFormEnum;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

import java.util.List;

public class AllGuestsViewModel extends AndroidViewModel {

    private final GuestRepository mRepository;

    private final MutableLiveData<List<Guest>> mGuestLiveData = new MutableLiveData<>();
    public LiveData<List<Guest>> guestLiveData = this.mGuestLiveData;

    public AllGuestsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = GuestRepository.getInstance(application.getApplicationContext());
    }


    public void getList(int mFilter) {
        if (mFilter == GuestFormEnum.NOT_CONFIRMED.ordinal()) {
            this.mGuestLiveData.setValue(this.mRepository.getAllGuestList());
        } else if (mFilter == GuestFormEnum.PRESENT.ordinal()) {
            this.mGuestLiveData.setValue(this.mRepository.getPresentGuestList());
        } else if (mFilter == GuestFormEnum.ABSENT.ordinal()) {
            this.mGuestLiveData.setValue(this.mRepository.getAbsentGuestList());
        }
    }

    public void delete(int id) {
        this.mRepository.delete(id);
    }
}