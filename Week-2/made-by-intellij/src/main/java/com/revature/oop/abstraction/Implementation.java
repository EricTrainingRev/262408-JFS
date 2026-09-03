package com.revature.oop.abstraction;

public abstract class Implementation {

    // this public method is what developers need to use our code, hence it is public
    public boolean validatePassword(String password){
        return lengthIsCorrect(password);
    }

    // users of our code do not need access to this directly, it is just a helper method
    // so we make it private so as not to expose necessary data from our class
    private boolean lengthIsCorrect(String password){
        return !password.isEmpty() && password.length() <= 15;
    }

}
