package com.revature.classes;

import java.util.Objects;

public class ClassBasics {
    /*
        Classes have attributes and behaviors: attributes are the "fields" of the
        class: these store the data your class needs to function. Behaviors are the
        methods of the class: these are actions you want performed that are associated
        with your class
     */
    // this field is in the "instance" scope: it belongs to objects of the class
    int identifier;
    static int count = 0;

    public ClassBasics(){
        ClassBasics.count++;
    }
    @Override
    public String toString() {
        return "ClassBasics{" +
                "identifier=" + identifier +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Class count = " + ClassBasics.count);
        ClassBasics myObject = new ClassBasics();
        // Note this syntax is technically valid, but it is more propper to reference
        // static resources via the Class instead of objects from the class
        System.out.println("Class count = " + ClassBasics.count);
        myObject.identifier = 1;
        ClassBasics anotherObject = new ClassBasics();
        System.out.println("Class count = " + ClassBasics.count);
        anotherObject.identifier = 2;
        // the objects have their own identifier fields that do not overlap
        System.out.println("myObject identifier = " + myObject.identifier);
        System.out.println("anotherObject identifier = " + anotherObject.identifier);

        System.out.println(myObject);
        System.out.println(anotherObject);

        String stringObject = "This is a string";
        Integer intergerObject = Integer.valueOf(100);
        intergerObject.equals(null);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassBasics that = (ClassBasics) o;
        return this.identifier == that.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }
}

