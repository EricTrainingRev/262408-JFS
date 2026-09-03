package com.revature.oop.inheritance;

public class Child extends Parent {

    public String grade;

    /*
        When inheriting from a parent class you need to make sure you call the parent constructor within the child
        constructor. This is especially important when you need to pass data into the parent constructor to intialize
        your object.
     */
    public Child(int age, String name, String grade) {
        super(age, name);
        this.grade = grade;
    }

    @Override
    public void sayCatchPhrase() {
        System.out.println("Gotta legally distinct collect them all!");
    }
}
