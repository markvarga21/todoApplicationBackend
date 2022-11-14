package com.markvarga21.exception;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }
}
