package com.revature;

public class Primitives {

    // note: the parameter name can be whatever you want, but args is the norm
    public static void main(String[] whateverYouWant){
        /*
            The smallest bits of data you will work with in Java are primitives:
            these represent numbers, characters, and logical true/false
         */

        // you have multiple options for storing numeric data
        byte myByte = 0;
        short myShort = 10;
        int myInt = 100;
        long myLong = 1000;
        float myFloat = 100.50f;
        double myDouble = 1000.5001d;

        // individual characters can be referenced as the char type
        char myChar = 'a';

        // anytime you need to reference a true/false situation use booleans
        boolean myBoolean = true;

        /*
            While Java does not manually coerce your types you can do so yourself.
            For instance, if you want to add extra precision to an integer
            you can  "cast" it as a float
         */

        int impreciseNumber = 99;
        // (float) casts our int into a float
        float preciseNumber = (float) impreciseNumber;
        System.out.println(preciseNumber);

        preciseNumber = 99.999f;
        System.out.println(preciseNumber);

        int backToImpreciseNumber = (int) preciseNumber;
        System.out.println(backToImpreciseNumber);

        /*
            You can also cast types between the whole number and floating
            point types as well, but this gets a little tricky
         */
        System.out.println();


        // this is widening: you can do so safely
        int myAge = 56;
        long myLongAge = (int) myAge;
        System.out.println(myAge);
        System.out.println(myLongAge);

        System.out.println();

        // at the end we shorten the value and we lose the 200
        int notShortened = 200;
        short shortened = (short) notShortened;
        double doubledShort = (double) shortened;
        byte myShorterByte = (byte) shortened;

        System.out.println(notShortened);
        System.out.println(shortened);
        System.out.println(doubledShort);
        System.out.println(myShorterByte);

        /*
            Anytime you are doing string manipulation be aware you will be working
            with chars instead of strings if accessing individual characters
         */

        /*
            booleans are very common when writing the control flow of applications,
            we will see them in action then
         */

    }

}
