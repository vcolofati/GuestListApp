package com.vcolofati.convidados.constants;

public class DatabaseConstants {

    private DatabaseConstants() {}

    public static class GUEST {
        public static final String TABLE_NAME = "Guest";

        public static class COLUMNS {
            public static final String
                    ID = "id",
                    NAME = "name",
                    PRESENCE = "presence";
        }
    }
}
