package com.revature.exceptions;

/*
   By extending Exception we make our custom exception a checked exception: we will
   get error messages if we do not handle it being thrown, and our code will not
   compile if we do not handle it
 */
public class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}
