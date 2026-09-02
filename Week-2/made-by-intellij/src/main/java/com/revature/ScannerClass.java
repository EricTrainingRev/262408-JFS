package com.revature;

import java.util.Scanner;

public class ScannerClass {
    /*
        The Scanner class allows you to pass a stream of data into it and then access
        the contents of that stream
     */

    public static void main(String[] args) {
        // you can have your app automatically close your scanner by initializing it
        // in a try with resources block
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("What is your name: ");
            String name = scanner.nextLine();
            System.out.println("hello " + name);
        }

    }

    // otherwise you have to manually close it when you are done with it
    public static void manualOpenClose(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tell me your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);
        System.out.print("How old are you: ");
        // this is commented out, but I recommend using this approach
//        String ageAsString = scanner.nextLine();
//        int age = Integer.parseInt(ageAsString);
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Got it, you are " + age + " years old");
        System.out.print("Where do you work: ");
        String location = scanner.nextLine();
        System.out.println("You work in " + location);
        scanner.close();
    }
}
