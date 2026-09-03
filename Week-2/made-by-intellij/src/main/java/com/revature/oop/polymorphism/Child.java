package com.revature.oop.polymorphism;

public class Child extends Parent{
    /*
        This is compile-time polymorphism (overriding): we change the implementation of the
        parent method so that when it is called by the child some new action
        occurs, even though it is the same method that was provided by the
        parent. The implementation has been overridden.
     */
    @Override
    public void parentMethod() {
        System.out.println("This is the new implementation in the Child class");
    }

    /*
        Below we have an example of run-time polymorphism (overloading): at run-time,
        depending on what argument you provide, the childMethod used will change
     */

    public void childMethod(int num){
        System.out.println("You entered a number into the childMethod");
    }

    public void childMethod(String word){
        System.out.println("You entered a String into the childMethod");
    }


}
