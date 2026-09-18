package com.revature.examples.records;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/** Simple examples of using the Cheese record. */
public class CheeseExample {

    public static void main(String[] args) {
//        creating();
        equality();
    }

    /** Construct it, then read it back through the accessors. */
    static void creating() {
        var cheese = new Cheese("Stinking Bishop", "cow", 8);

        System.out.println("-- creating --");
        System.out.println("toString()    : " + cheese);
        System.out.println("name()        : " + cheese.name());
        System.out.println("milk()        : " + cheese.milk());
        System.out.println("ageInMonths() : " + cheese.ageInMonths());
    }

    /** Same components means the same value, with no code written to make it so. */
    static void equality() {
        var a = new Cheese("Gouda", "cow", 4);
        var b = new Cheese("Gouda", "cow", 4);

        System.out.println("\n-- equality --");
        System.out.println("a.equals(b)   : " + a.equals(b));
        System.out.println("hashes match  : " + (a.hashCode() == b.hashCode()));
        System.out.println("as a map key  : " + Map.of(a, "tasty").get(b));
    }

}
