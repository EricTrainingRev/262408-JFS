package collections.list;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        /*
            Lists are going to be your most generic, universally useful data
            structure. Lists have a couple of features that make them so flexible:
            - lists are indexable: elements can be accessed by referencing their index position
            - lists maintain order of insertion
            - lists allow duplicate values
         */

        /*
            When creating Lists we need to specify the type that is stored within the list. If you look at the
            code for List you will see it declared as  List<E>. "E" is a generic that tells the compiler "this will
            have a concrete type at some point when it is used". We provide the concrete type when we make a List
            object of our choosing
         */
        List<String> myArrayList = new ArrayList<>();
        // the type must be a class, so for storing primitives reference the Class version and let Java Autobox the values for you
        List<Integer> myIntegers = new ArrayList<>();
        // You can make the generic the base Object class, be careful if you do so because it will require you to make more checks
        // on the data when you try to access it
        List<Object> myObjects = new ArrayList<>();
    }
}
