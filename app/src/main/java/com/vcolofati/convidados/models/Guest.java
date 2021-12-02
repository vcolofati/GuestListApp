package com.vcolofati.convidados.models;

import com.vcolofati.convidados.enums.GuestFormEnum;

public class Guest {
    private String name;
    private GuestFormEnum confirmation;

    public Guest(String name, GuestFormEnum confirmation) {
        this.name = name;
        this.confirmation = confirmation;
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
