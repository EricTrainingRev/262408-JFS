package com.revature.oop.inheritance;

import java.util.UUID;

public abstract class Parent {

    // UUID is a useful tool for generating random identifiers for your entities
    public UUID socialSecurityNumber;
    public int age;
    public String name;

    public Parent(int age, String name){
        this.socialSecurityNumber = UUID.randomUUID();
        this.age = age;
        this.name = name;
    }

    // this concrete method is available to all inheriting classes
    public void greetSomeone(){
        System.out.println("Hello! I am " + this.name + " and I am " + this.age + " years old");
    }

    // this abstract method MUST be implemented by any inheriting classes
    public abstract void sayCatchPhrase();

}
