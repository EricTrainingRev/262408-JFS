package com.revature;

/*
    Methods are the tools we use to actually accomplish work within our java code. Methods
    belong to classes and their objects, in the same way how fields of a class belong
    to either the class or objects depending on whether or not they are static
 */
public class Methods {

    String objectName;

    // special method that acts as the entrypoint for our application
    public static void main(String[] args) {
        Methods obj = new Methods();
        Methods obj2 = new Methods("Billy");

        obj.changeObjectName("Slagathor");
        obj.changeObjectName("Sally");
    }

    // Constructors are special resources used to create objects of your classes
    public Methods(){
        Methods.printCreationMessage("This gets called whenever the no args constructor is used");
    }
    // Multiple constructors can be declared. A common pattern is to have a "no args"
    // and a "full args" constructor
    public Methods(String objectName){
        Methods.printCreationMessage("This gets called whenever the full args constructor is used");
        this.changeObjectName(objectName);
    }

    /*
        public                  -> can be accessed anywhere in our code
        static                  -> belongs to the class
        void                    -> the method does not "return" any data
        printCreationMessage    -> name of the method
        (String message)        -> the parameter/s of the method
     */
    public static void printCreationMessage(String message){
        System.out.println(message);
    }

    /*
        This changeObjectName method was broken into two separate methods: one handles the validation of the newName, the
        other handles changing the field and calling the validation method. It could be broken down even further, but
        whether to do so comes down to have much time you have before your deadlines and how much other work
        you have. Working code is always better than theoretical "clean" code
     */
    public void changeObjectName(String newName){
        if(!newNameValid(newName)){
            System.out.println("Slagathor is not allowed! Get out of here!");
        } else {
            this.objectName = newName;
            System.out.println("Your new name is now " + this.objectName);
        }


    }

    private boolean newNameValid(String newName){
        if(newName == null || newName.equals("Slagathor")) {
            return false;
        }
        return true;
    }



}
