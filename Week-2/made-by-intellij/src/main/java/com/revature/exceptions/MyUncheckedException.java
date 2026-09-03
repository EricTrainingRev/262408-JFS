package com.revature.exceptions;

/*
    This makes the exception an unchecked exception because it inherits from
    RuntimeException. We can throw this all we want in our code without handling
    it and our code will still compile
 */
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
