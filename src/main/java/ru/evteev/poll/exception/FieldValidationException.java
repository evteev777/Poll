package ru.evteev.poll.exception;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String message) {
        super(message);
    }
}
