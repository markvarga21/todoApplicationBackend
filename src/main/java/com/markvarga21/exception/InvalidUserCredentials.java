package com.markvarga21.exception;

public class InvalidUserCredentials extends RuntimeException {
    public InvalidUserCredentials(String message) {
        super(message);
    }
}
