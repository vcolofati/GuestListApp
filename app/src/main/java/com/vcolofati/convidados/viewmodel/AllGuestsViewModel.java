package com.vcolofati.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vcolofati.convidados.enums.GuestFormEnum;
import com.vcolofati.convidados.errorHandling.Resource;
import com.vcolofati.convidados.errorHandling.exceptions.DatabaseException;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.repositories.GuestRepository;

import java.util.List;

public class AllGuestsViewModel extends AndroidViewModel {

    private final GuestRepository mRepository;

    private final MutableLiveData<Resource<List<Guest>>> mResourceGuestListData = new MutableLiveData<>();
    public LiveData<Resource<List<Guest>>> ResourceGuestListData = this.mResourceGuestListData;

    public AllGuestsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new GuestRepository(application.getApplicationContext());
    }


    public void getList(int mFilter) {
        try {

        if (mFilter == GuestFormEnum.NOT_CONFIRMED.ordinal()) {
            this.mResourceGuestListData.postValue(Resource.success(this.mRepository.getAllGuestList(), null));
        } else if (mFilter == GuestFormEnum.PRESENT.ordinal()) {
            this.mResourceGuestListData.postValue(Resource.success(this.mRepository.getPresentGuestList(), null));
        } else if (mFilter == GuestFormEnum.ABSENT.ordinal()) {
            this.mResourceGuestListData.postValue(Resource.success(this.mRepository.getAbsentGuestList(), null));
        }
        } catch (DatabaseException.ReadListException readListException) {
            this.mResourceGuestListData.postValue(Resource.error("Read list error", null));
        }
    }

    public void delete(int id) {
        try {
            this.mRepository.delete(id);
        } catch (DatabaseException.DeleteException deleteException) {
            this.mResourceGuestListData.postValue(Resource.error("Delete error", null));
        }
    }
}