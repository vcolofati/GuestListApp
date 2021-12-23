package com.vcolofati.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vcolofati.convidados.errorHandling.Resource;
import com.vcolofati.convidados.errorHandling.exceptions.DatabaseException;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

public class GuestFormViewModel extends AndroidViewModel {

    private final GuestRepository repository;
    private final MutableLiveData<Resource<Guest>> mResourceGuest = new MutableLiveData<>();
    public final LiveData<Resource<Guest>> resourceGuest = this.mResourceGuest;

    public GuestFormViewModel(@NonNull Application application) {
        super(application);
        this.repository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(Guest guest) {
        try {
            if ("".equals(guest.getName())) {
                return;
            }

            if (guest.getId() == 0) {
                this.repository.insert(guest);
                this.mResourceGuest.postValue(Resource.success(null, "Insert success"));
            } else {
                this.repository.update(guest);
                this.mResourceGuest.postValue(Resource.success(null, "Update success"));
            }
        } catch (DatabaseException.InsertException insertException) {
            this.mResourceGuest.postValue(Resource.error("Insert Error", null));
        } catch (DatabaseException.UpdateException updateException ) {
            this.mResourceGuest.postValue(Resource.error("Update Error", null));
        }
    }

    public void getGuest(int id) {
        try {
            this.mResourceGuest.postValue(Resource.success(this.repository.getGuest(id), null));
        } catch (Exception e) {
            this.mResourceGuest.postValue(Resource.error(e.getMessage(), null));
        }

    }
}
