package com.revature;

public class Strings {
    public static void main(String[] args) {
        /*
            Strings are one of the more common data structures you will use.
            They represent words, statements, written data in general. Because
            they are so commonly used Java implements them with some unique rules
            that other objects do not have

            The main difference between Strings and other objects is they are stored in the
            String Pool. This is a reserved memory space where only String objects are stored. Something
            unique about Strings is all variables that reference the same String (see below for example)
            actually reference the same object in memory. If for some reason you want to have two Strings
            that store the same text content you would need to call the String constructor (again see below)
            in order to do so, but it is not recommended
         */

        String name = "Billy";
        String nameAgain = new String("Billy");
        String name2 = "Billy";

        /*
            Note the different results returned below: keep in mind the equality
            operator (==) compares primitive data values, but for objects it
            compares the memory addresses of the objects
         */
        int firstNum = 10;
        int secondNum = 10;
        System.out.println(firstNum == secondNum); // returns true since 10 == 10
        System.out.println(name == nameAgain); // returns false since two objects are being compared
        System.out.println(name == name2); // returns true since the same object is being compared

        /*
            Anytime you need to compare strings you should use the "equals" method. This will check the
            text content of the Strings and use that content to determine whether the Strings are the
            "same" or not
         */

        // this is the propper way to check Strings: this compares their text content
        boolean stringsAreEqual = name.equals(nameAgain);
        System.out.println(stringsAreEqual); // this prints true since  Billy == Billy
        System.out.println(name); // Note here name is Billy
        name = name + " Billyson"; // Here we perform String concatenation (string + string) to make a new String
        System.out.println(name); // now name references "Billy Billyson" so the new name is displayed
        System.out.println(name2); // note the old "Billy" object has not been changed: Strings are immutable

        /*
            String manipulation, specifically changing a String value permanently, is not a thing since Strings are
            immutable, but there are plenty of ways we can alter the displaying of String data
         */

        System.out.println(name); // Shows the raw String data
        System.out.println(name.toLowerCase()); // shows the text content in all lowercase
        System.out.println(name.toUpperCase()); //shows the text content in uppercase
        System.out.println(name.substring(6)); // shows the text starting at index position 6
        System.out.println(name.substring(6,12)); // shows the text starting at index position 6 and up to but not including index 12

    }
}
