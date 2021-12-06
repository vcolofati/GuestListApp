package com.vcolofati.convidados.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vcolofati.convidados.constants.Constants;
import com.vcolofati.convidados.enums.GuestFormEnum;
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

    public Guest getGuest(int id) {
        Guest guest = new Guest(id, null, null);
        SQLiteDatabase db = this.mHelper.getReadableDatabase();
        String[] columns = {Constants.GUEST.COLUMNS.ID,
                Constants.GUEST.COLUMNS.NAME,
                Constants.GUEST.COLUMNS.PRESENCE};
        String selection = Constants.GUEST.COLUMNS.ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(Constants.GUEST.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME));
            @SuppressLint("Range") String presence = cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE));

            guest.setName(name);
            guest.setConfirmation(GuestFormEnum.valueOf(presence));
            cursor.close();
        }
        return guest;
    }

    public List<Guest> getGuestList() {
        List<Guest> list = new ArrayList<>();
        SQLiteDatabase db = this.mHelper.getReadableDatabase();
        String[] columns = {Constants.GUEST.COLUMNS.ID,
                Constants.GUEST.COLUMNS.NAME,
                Constants.GUEST.COLUMNS.PRESENCE};

        Cursor cursor = db.query(Constants.GUEST.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME));
                @SuppressLint("Range") String presence = cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE));
                list.add(new Guest(id, name, GuestFormEnum.valueOf(presence)));
            }
            cursor.close();
        }

        return list;
    }

    public void insert(Guest guest) {
        SQLiteDatabase db = this.mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.GUEST.COLUMNS.NAME, guest.getName());
        values.put(Constants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation().toString());
        db.insert(Constants.GUEST.TABLE_NAME, null, values);

    }

    public void delete(int id) {
        SQLiteDatabase db = this.mHelper.getWritableDatabase();
        String where = Constants.GUEST.COLUMNS.ID + " = ?";
        String[] args = {String.valueOf(id)};
        db.delete(Constants.GUEST.TABLE_NAME, where, args);

    }

    public void update(Guest guest) {
        SQLiteDatabase db = this.mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.GUEST.COLUMNS.NAME, guest.getName());
        values.put(Constants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation().toString());
        String where = Constants.GUEST.COLUMNS.ID + " = ?";
        String[] args = {String.valueOf(guest.getId())};
        db.update(Constants.GUEST.TABLE_NAME, values, where, args);

    }
}
