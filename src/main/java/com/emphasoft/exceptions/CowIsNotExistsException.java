package com.emphasoft.exceptions;

public class CowIsNotExistsException extends IllegalArgumentException {
    public CowIsNotExistsException(int id) {
        super("Cow with id " + id + " is not exists");
    }
}
