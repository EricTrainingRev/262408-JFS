package com.revature.oop.abstraction;

/*
    Keep in mind when inherting another class you only can do this once: Java only
    supports single class inheritance. To inherit the code from another class use
    the extends keyword and then reference the class to be inherited
 */
public class API extends Implementation{

    public static void main(String[] args) {
        // if we uncomment the code below we will get an error, since we can not
        // create an object of an abstract class directly
        // Implementation obj = new Implementation();
        API obj = new API();
        // since lengthIsCorrect is private we can not access it directly
        // obj.lengthIsCorrect()
        // but we do get the benefit of through the public facing method
        System.out.println(obj.validatePassword("valid"));
        System.out.println(obj.validatePassword(""));

        // This is the benefit of abstraction: you hide complexity to make it easier
        // for yourself and for others to utilize your code

}

}
