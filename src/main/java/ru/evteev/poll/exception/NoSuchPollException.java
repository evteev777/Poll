package ru.evteev.poll.exception;

public class NoSuchPollException extends RuntimeException {

    public NoSuchPollException(String message) {
        super(message);
    }
}
