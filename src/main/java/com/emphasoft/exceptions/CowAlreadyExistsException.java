package com.emphasoft.exceptions;

public class CowAlreadyExistsException extends IllegalArgumentException {
    public CowAlreadyExistsException(int id) {
        super("Cow with id " + id + " already exists");
    }
}
