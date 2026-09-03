package com.revature.oop.encapsulation;

import java.util.UUID;

/*
    Encapsulation is all about controlling access to your data. In Java a very common
    design pattern to implement this is the Java Bean design pattern. This is a way
    of designing your classes with all private fields, and public getter and setter
    methods that are used to control the access to those fields
 */
public class Person {
    private final UUID socialSecurityNumber;
    private int age;
    private String name;

    public Person(int age, String name){
        this.socialSecurityNumber = UUID.randomUUID();
        this.age = age;
        this.name = name;
    }

    // if we wanted we could limit access to our data, but these days this is
    // more commonly handled in the business layer of an application
    public UUID getSocialSecurityNumber(int pin) {
        if (pin == 1234){
            return socialSecurityNumber;
        }
        return null;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
