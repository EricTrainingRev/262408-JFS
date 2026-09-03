package com.revature.oop.polymorphism;

public class Playground {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        parent.parentMethod();
        child.parentMethod();
        child.childMethod(10);
        child.childMethod("ten");

        // Note the output is the overridden version, even though the Parent
        // type is declared
        Parent temp = new Child();
        temp.parentMethod();
    }
}
