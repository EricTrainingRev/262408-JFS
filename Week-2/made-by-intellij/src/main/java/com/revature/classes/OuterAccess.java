package com.revature.classes;

public class OuterAccess {
    public static void main(String[] args) {
        AccessModifiers obj = new AccessModifiers();
        System.out.println(obj.publicField);
        System.out.println(obj.protectedField);
        System.out.println(obj.defaultField);
        // if we were to uncomment the code below our app would not compile
//        System.out.println(obj.privateField);
    }
}
