package com.vcolofati.convidados.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vcolofati.convidados.models.Guest;

import java.util.List;

@Dao
public interface GuestDAO {

    @Insert
    long insert(Guest guest);

    @Update
    int update(Guest guest);

    @Delete
    int delete(Guest guest);

    @Query("SELECT * FROM GUEST WHERE ID = :id")
    Guest getGuest(int id);

    @Query("SELECT * FROM GUEST")
    List<Guest> getGuestList();

    @Query("SELECT * FROM GUEST WHERE confirmation = :confirmation")
    List<Guest> getGuestListByPresence(String confirmation);
}
