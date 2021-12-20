package com.vcolofati.convidados.constants;

public class Constants {

    private Constants() {}

    public static final String GUESTID = "guestId";

    public static final String FILTER = "FILTER";

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
