package com.vcolofati.convidados.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vcolofati.convidados.enums.GuestFormEnum;

@Entity(tableName = "guest")
public class Guest {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String confirmation;

    public Guest(){
    }

    public Guest(int id, String name, GuestFormEnum confirmation) {
        this.id = id;
        this.name = name;
        this.confirmation = confirmation.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmation() {
        return this.confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
