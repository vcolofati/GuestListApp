package com.vcolofati.convidados.errorHandling.exceptions;

public class DatabaseException extends Exception {
    private DatabaseException() {
        super();
    }

    public static class InsertException extends Exception {
        public InsertException() {
            super();
        }
    }

    public static class UpdateException extends Exception {
        public UpdateException() {
            super();
        }
    }

    public static class DeleteException extends Exception {
        public DeleteException() {
            super();
        }
    }

    public static class ReadListException extends Exception {
        public ReadListException() {
            super();
        }
    }

    public class ReadOneException extends Exception {
        public ReadOneException() {
            super();
        }
    }
}
