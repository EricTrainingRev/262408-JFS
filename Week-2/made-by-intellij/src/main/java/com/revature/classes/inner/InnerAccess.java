package com.revature.classes.inner;

import com.revature.classes.AccessModifiers;

/*
    here we can see how protected works: we inherit the code from AccessModifiers
    in this InnerAccess class, which allows the class as an inheriting resource
    to directly interact with the protected field, but not the default or
    private field
 */
public class InnerAccess extends AccessModifiers {
    public static void main(String[] args) {
        InnerAccess obj = new InnerAccess();
        System.out.println(obj.publicField);
        System.out.println(obj.protectedField);
        // if we uncomment the code below we will not be able to compile the app
//        System.out.println(obj.defaultField);
//        System.out.println(obj.privateField);
    }
}
