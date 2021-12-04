package com.vcolofati.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

public class GuestFormViewModel extends AndroidViewModel {

    private GuestRepository repository;

    public GuestFormViewModel(@NonNull Application application) {
        super(application);
        this.repository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(Guest guest) {
        this.repository.insert(guest);
    }
}
