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
    // this field is in the "static" scope: it belongs to the class
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

        // because we override the toString method we get a more human-friendly output of the object data
        System.out.println(myObject);
        System.out.println(anotherObject);

        // note how any type of object can be passsed to the equals method: this means we must check the type of data first
        String stringObject = "This is a string";
        Integer intergerObject = Integer.valueOf(100);
        intergerObject.equals(null);

    }

    /*
        When creating your own classes you will have to determine what makes objects "equal" to each other. With modern
        editors it is simple enough to compare all the fields of the objects
     */
    @Override
    public boolean equals(Object o) {
        // first check the object types are the same
        if (o == null || getClass() != o.getClass()) return false;
        // if the same cast the raw Object as your Class type
        ClassBasics that = (ClassBasics) o;
        // now you can check the fields match
        return this.identifier == that.identifier;
    }

    /*
        hash based collections use the hashCode method to store objects in a way that is quick to access and store. The
        rule of thumb you should follow is that any objects that are equal (as determined by the equals method) should
        return the same hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }
}

