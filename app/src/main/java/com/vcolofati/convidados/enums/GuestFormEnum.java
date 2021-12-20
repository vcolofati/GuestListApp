package com.vcolofati.convidados.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum GuestFormEnum {
    NOT_CONFIRMED(0), PRESENT(1), ABSENT(2);

    private final int id;
    private static final Map<Integer, GuestFormEnum> ENUM_MAP;

    GuestFormEnum(int id) {
        this.id = id;
    }

    static {
        Map<Integer,GuestFormEnum> map = new ConcurrentHashMap<>();
        for (GuestFormEnum instance : GuestFormEnum.values()) {
            map.put(instance.id,instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static GuestFormEnum get (int id) {
        return ENUM_MAP.get(id);
    }
}
