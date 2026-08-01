package com.sooumik.ledgerly.exceptions;

public class DuplicateResourceFoundException extends RuntimeException {
    public DuplicateResourceFoundException(String message) {
        super(message);
    }
}
