package com.revature;

import java.util.ArrayList;
import java.util.List;

public class WrapperClasses {
    public static void main(String[] args) {
        /*
            All primitives have a Class to represent them. These are typically used
            when you need to declare that primitive data is stored in a data
            structure (like Lists and Sets) but they also provide a handful of
            helper methods
         */

        // Note we specify the List will store Integers (this is a class reference)
        List<Integer> myNumbers = new ArrayList<>();
        // However, when we add numbers to the list we provide primitives
        myNumbers.add(1);
        myNumbers.add(2);
        myNumbers.add(3);

        /*
            When the primitive is added to the List Java performs Autoboxing and
            converts the primitive into its Object form, and then when we access the
            value Java unboxes the object back into a primitive
         */

        System.out.println(myNumbers.getFirst());

        /*
            Primitive Classes also have a myriad of helper methods that can be used
            to interact with and manipulate primitives. Primitives are not objects,
            and therefore they do not have access to methods. The wrapper classes for
            the primtives do have access to helper methods
         */

        int myNumber = Integer.parseInt("23");
        System.out.println(myNumber);
    }
}
