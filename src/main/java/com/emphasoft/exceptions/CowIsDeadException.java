package com.emphasoft.exceptions;

public class CowIsDeadException extends IllegalArgumentException {
    public CowIsDeadException(int id) {
        super("Cow with id " + id + " is dead");
    }
}
