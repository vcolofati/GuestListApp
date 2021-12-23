package com.vcolofati.convidados.repositories;

import android.content.Context;

import com.vcolofati.convidados.enums.GuestFormEnum;
import com.vcolofati.convidados.errorHandling.exceptions.DatabaseException;
import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.models.dao.GuestDAO;

import java.util.List;

public class GuestRepository {

    private GuestDAO mGuestDAO;

    public GuestRepository(Context context) {
        GuestDatabase db = GuestDatabase.getGuestDatabase(context);
        this.mGuestDAO = db.guestDAO();
    }

    public Guest getGuest(int id) {
        return this.mGuestDAO.getGuest(id);
    }

    public boolean insert(Guest guest) throws DatabaseException.InsertException {
        return this.mGuestDAO.insert(guest) > 0;
    }

    public boolean delete(int id) throws DatabaseException.DeleteException {
        Guest guest = this.getGuest(id);
        return this.mGuestDAO.delete(guest) > 0;
    }

    public boolean update(Guest guest) throws DatabaseException.UpdateException {
       return this.mGuestDAO.update(guest) > 0;
    }

    public List<Guest> getPresentGuestList() throws DatabaseException.ReadListException {
        return this.mGuestDAO.getGuestListByPresence(GuestFormEnum.PRESENT.toString());
    }

    public List<Guest> getAbsentGuestList() throws DatabaseException.ReadListException {
        return this.mGuestDAO.getGuestListByPresence(GuestFormEnum.ABSENT.toString());
    }

    public List<Guest> getAllGuestList() throws DatabaseException.ReadListException {
        return this.mGuestDAO.getGuestList();
    }
}
