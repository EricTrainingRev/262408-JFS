package com.revature.examples.reflections;

/**
 * A class with one public attribute and one private one, plus a behavior
 * that is private too.
 *
 * The compiler enforces these modifiers, so normal code can't touch the
 * socialSecurityNumber field or call innerThoughts() from outside -- which
 * is exactly why ReflectionsExample exists.
 */
public class Person {

    public String name;
    private String socialSecurityNumber;

    public Person(String name, String socialSecurityNumber) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /** Private on purpose: not callable from outside this class. */
    private void innerThoughts() {
        System.out.println("what makes cheese so tasty?");
    }
}
