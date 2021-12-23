package com.vcolofati.convidados.models;

import com.vcolofati.convidados.enums.GuestFormEnum;

public class Guest {
    private int id;
    private String name;
    private GuestFormEnum confirmation;

    public Guest(int id, String name, GuestFormEnum confirmation) {
        this.id = id;
        this.name = name;
        this.confirmation = confirmation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GuestFormEnum getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(GuestFormEnum confirmation) {
        this.confirmation = confirmation;
    }
}
