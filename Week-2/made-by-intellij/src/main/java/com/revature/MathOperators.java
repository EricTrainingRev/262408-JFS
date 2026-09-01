package com.revature;

public class MathOperators {
    public static void main(String[] args) {
        /*
            You can perform all your basic mathematical operations in Java. There
            are also some shortcuts you can take to make your math operations a
            bit cleaner to read
         */

        int add = 5+5;
        int subtract = 5 - 5;
        int multiplication = 5 * 5;
        int division = 5 / 5;

        // you can combine these operators with the assignment operator (=) to
        // perform your mathematical operation and assign the new value to the
        // previously made variable

        int num = 5;
        num = num + 5; // sets the value of num to what it was + 5
        num += 5; // this does the same as the line above, but is a bit cleaner to read

        num++; // this increments the value by 1
        num--; //this decrements the value by 1

        int divisionOne = 10/3;
        System.out.println(divisionOne);
        int divisionTwo = 10 % 3;
        System.out.println(divisionTwo);
    }
}
