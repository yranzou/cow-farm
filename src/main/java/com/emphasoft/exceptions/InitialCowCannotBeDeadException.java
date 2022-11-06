package com.emphasoft.exceptions;

public class InitialCowCannotBeDeadException extends IllegalArgumentException {
    public InitialCowCannotBeDeadException(int cowId) {
        super("Unable to end lifespan of cow with id " + cowId + " - it should be always alive");
    }
}
