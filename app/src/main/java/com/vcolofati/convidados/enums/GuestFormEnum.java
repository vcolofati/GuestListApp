package com.vcolofati.convidados.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum GuestFormEnum {
    NOT_CONFIRMED("Not Confirmed", "Não confirmado"), PRESENT("Present", "Presente"), ABSENT("Absent", "Ausente");

    private final String localeUS, localeBR;
    private static final Map<String, GuestFormEnum> ENUM_MAP;

    GuestFormEnum(String localeUS, String localeBR) {
        this.localeUS = localeUS;
        this.localeBR = localeBR;
    }

    static {
        Map<String,GuestFormEnum> map = new ConcurrentHashMap<>();
        for (GuestFormEnum instance : GuestFormEnum.values()) {
            map.put(instance.localeUS.toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static GuestFormEnum get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
