package com.revature.exceptions;

public class CreateConnectionFail extends RuntimeException {
    public CreateConnectionFail(String message) {
        super(message);
    }
}
