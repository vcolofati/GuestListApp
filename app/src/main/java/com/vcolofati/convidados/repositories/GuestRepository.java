package com.vcolofati.convidados.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vcolofati.convidados.constants.DatabaseConstants;
import com.vcolofati.convidados.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private static GuestRepository INSTANCE;
    private GuestDatabaseHelper mHelper;

    private GuestRepository(Context context) {
        this.mHelper = new GuestDatabaseHelper(context);
    }

    public static GuestRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GuestRepository(context);
        }
        return INSTANCE;
    }

    public List<Guest> getList() {
        return new ArrayList<>();
    }

    public void insert(Guest guest) {
        SQLiteDatabase db = this.mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.getName());
        values.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation().toString());
        db.insert(DatabaseConstants.GUEST.TABLE_NAME, null, values);
    }

    public void delete(Guest guest) {
    }

    public void update(Guest guest) {
    }
}
