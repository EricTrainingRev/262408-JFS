package com.revature.classes;

public class AccessModifiers {
    /*
        In Java there are 4 levels of "access" you can assign to resources in your classes:
        public      -> the most accessible: these resources can be accessed anywhere in your application
        protected   -> can be accessed freely within the same package and also by any inheriting classes
        default     -> can be accessed within the same package
        private     -> can only be accessed within the class where the resource is declared
     */
    public String publicField = "This is public";
    protected String protectedField = "This is protected";
    String defaultField = "This is default";
    private String privateField = "This is private";

    public static void main(String[] args) {
        AccessModifiers obj = new AccessModifiers();
        System.out.println(obj.publicField);
        System.out.println(obj.protectedField);
        System.out.println(obj.defaultField);
        System.out.println(obj.privateField);
    }
}
