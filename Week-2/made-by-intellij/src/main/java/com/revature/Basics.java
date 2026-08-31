package com.revature;

public class Basics {
    public static void main(String[] args) {
        // use two forward slashes to make a single line comment
        /*
            use a forward slash and star, followed by a star and forward slash
            in order to make a multi-line comment
         */

        /*
            Java is a Strongly and Statically typed language
            Static -> You must declare the type of your data
            Strong -> Java does not coerce our data types
         */

        /*
            Below we have a simple example of creating a variable that stores some
            numberic data. "int" is the type of the data, myNumber is the name of
            the variable, and "= 10" sets the value of the variable
         */
        int myNumber = 10;
        // myOtherNumber = 15; //this will prevent our code from compiling
        System.out.println(myNumber);

        /*
            Java is Strongly typed, so our references are set to a specific type
            once created. This means we can not take "myNumber" from above and
            assign it a String value
         */
        myNumber = 15; // this is fine: the type is not changing
        // myNumber = "20"; this will cause the code to fail since a String is not an int
        String myStringNumber = "20";
    }
}
