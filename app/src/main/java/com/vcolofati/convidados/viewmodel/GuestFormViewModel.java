package com.vcolofati.convidados.viewmodel;

import androidx.lifecycle.ViewModel;

import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

public class GuestFormViewModel extends ViewModel {

    private GuestRepository repository;

    public GuestFormViewModel(GuestRepository repository) {
        this.repository = repository;
    }

    public void save(Guest guest) {
        repository.insert(guest);
    }
}
