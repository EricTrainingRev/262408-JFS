package com.revature.examples.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Reflection: reading and calling another class's attributes and behaviors
 * at runtime, including the ones marked private.
 *
 * Access modifiers are a compile-time contract. Reflection sidesteps them at
 * runtime via setAccessible(true) -- which is why it's powerful and why you
 * should reach for it sparingly. On the JDK's own modules that call is now
 * refused (InaccessibleObjectException) unless the module is opened.
 */
public class ReflectionsExample {

    public static void main(String[] args) throws Exception {
        publicAttribute();
        privateAttribute();
        publicBehavior();
        privateBehavior();
    }

    /** A public field. getField finds it because it's visible; no setAccessible needed. */
    static void publicAttribute() throws Exception {
        Person person = new Person("Billy", "123-45-6789");
        Field field = Person.class.getField("name");

        System.out.println("-- public attribute --");
        System.out.println("modifiers    : " + Modifier.toString(field.getModifiers()));
        System.out.println("get()        : " + field.get(person));
        field.set(person, "Sally"); // NOTE: type needs to match field
        System.out.println("after set()  : " + person.name);
    }

    /** A private field: getDeclaredField + setAccessible(true) to open it. */
    static void privateAttribute() throws Exception {
        Person person = new Person("Billy", "123-45-6789");
        Field field = Person.class.getDeclaredField("socialSecurityNumber");

        System.out.println("\n-- private attribute --");
        System.out.println("modifiers    : " + Modifier.toString(field.getModifiers()));
        field.setAccessible(true); // the line that bypasses private
        System.out.println("get()        : " + field.get(person));
        field.set(person, "000-00-0000");
        System.out.println("after set()  : " + field.get(person));
    }

    /** A public behavior (the constructor), called directly and via reflection. */
    static void publicBehavior() throws Exception {
        Person direct = new Person("Billy", "123-45-6789");
        Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class);
        Person reflected = constructor.newInstance("Sally", "987-65-4321");

        System.out.println("\n-- public behavior --");
        System.out.println("modifiers    : " + Modifier.toString(constructor.getModifiers()));
        System.out.println("direct       : " + direct.name);
        System.out.println("reflection   : " + reflected.name);
    }

    /** A private behavior: getDeclaredMethod, open it, then invoke it. */
    static void privateBehavior() throws Exception {
        Person person = new Person("Ada", "123-45-6789");
        Method method = Person.class.getDeclaredMethod("innerThoughts"); // no parameters

        System.out.println("\n-- private behavior --");
        System.out.println("modifiers    : " + Modifier.toString(method.getModifiers()));
        method.setAccessible(true);
        method.invoke(person);
    }
}
