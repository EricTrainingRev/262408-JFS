package com.revature.logging;

public class NoDividingByZero extends RuntimeException {
    public NoDividingByZero(String message) {
        super(message);
    }
}
