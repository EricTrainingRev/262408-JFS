package com.revature.oop.inheritance;

public class Playground {
    public static void main(String[] args) {
        Child child = new Child(12, "Billy", "Fifth grade");
        child.greetSomeone();
        child.sayCatchPhrase();

        ChildTwo childTwo = new ChildTwo(21, "Sally", "Graduated");
        childTwo.greetSomeone();
        childTwo.sayCatchPhrase();
        childTwo.sing();

        Parent billy = new Child(12, "Billy", "Fifth Grade");
        billy.greetSomeone();
        billy.sayCatchPhrase();

        // the syntax below is valid, but only gives access to the attributes and
        // behaviors that are part of Parent
        Parent sally = new ChildTwo(21, "Sally", "Graduated");
        sally.greetSomeone();
        sally.sayCatchPhrase();
        // because the sally object is of type Parent it does not have access to
        // the sing method, which belpngs to the ChildTwo type,
        // so the code below will fail
        // sally.sing();
        // you can cast the type in order to get access to the method, but in this case
        // just change the type declared
        ((ChildTwo) sally).sing();

    }
}
