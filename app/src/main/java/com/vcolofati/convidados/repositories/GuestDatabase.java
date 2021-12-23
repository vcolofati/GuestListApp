package com.vcolofati.convidados.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vcolofati.convidados.models.Guest;
import com.vcolofati.convidados.models.dao.GuestDAO;

@Database(entities = {Guest.class}, version = 1)
public abstract class GuestDatabase extends RoomDatabase {

    public static GuestDatabase INSTANCE;

    public abstract GuestDAO guestDAO();

    public static GuestDatabase getGuestDatabase(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, GuestDatabase.class, "guests")
                    .allowMainThreadQueries()
//                    .addCallback(new Callback() {
//                        @Override
//                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                            super.onCreate(db);
//                        }
//                    })
//                    .addMigrations(MIGRATION_1_2)
                    .build();
        }

        return INSTANCE;
    }

    private static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };
}
