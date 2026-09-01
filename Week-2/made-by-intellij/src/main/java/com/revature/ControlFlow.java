package com.revature;

public class ControlFlow {
    public static void main(String[] args) {
        /*
            Your code will eventually become complex enough that you will have to check
            your data and change how your code operates depending on that data. To
            accomplish this we use control flow
         */

        // The simplest check to make is if a condition is true
        int num = 10;
        if(num > 5){
            System.out.println("Num is greater than 5");
        }

        // you can create either-or blocks of code depending on the logic
        int age = 18;
        if(age >= 18){
            System.out.println("User is an adult, no parent permission needed");
        } else {
            System.out.println("Prompt user to get parent permission");
        }

        // you can make multiple multiple sequential logical checks
        String name = "Slagathor";
        if(name.equals("Sally")){
            System.out.println("Welcome Sally");
        } else if(name.equals("Slagathor")){
            System.out.println("How did you get in here?????");
        } else {
            System.out.println("Welcome newcomer!");
        }

        /*
            When making logical checks you have a couple options for how to
            make those checks

            Note: for one liners like the examples below you can skip the {}
         */
        int x = 1;
        int y = 2;
        if(x > y) System.out.println("X is greater than y");
        /*
            >   -> greater than
            >=  -> greater or equal to
            <   -> less than
            <=  -> less or equal to
            ==  -> equal
            !   -> used to check for the opposite
         */

        if(!(x > y)) System.out.println("X is less than Y");
        String password = "Super-Secret";
        String username = "Super-username";
        if(!password.equals(username)){
            System.out.println("Password checks out");
        }

        /*
            Sometimes you will need to check multiple conditions as part of an
            if statement. You can break the logical checks into multiple nested
            if statements, but that can get difficult to read
         */
        if(true){
            if(true){
                if(false){
                    if(true){

                    }
                }
            }
        }

        boolean CorrectLength = true;
        boolean IncludesUppercase = true;
        boolean IncludesLowercase = false;
        boolean IncludesNumber = true;
        /*
            && us the logical and operator: it allows you to make multiple assertions in a single if or if else block
         */
        if(CorrectLength && IncludesUppercase && IncludesLowercase && IncludesNumber){
            System.out.println("Password set");
        } else {
            System.out.println("Invalid password");
        }

        /*
            Sometimes you will want to trigger code if one or more conditions are met: this is where you use the logcal
            or operator
         */

        String visitor = "Sally";
        if(visitor.equals("Billy") || visitor.equals("Sally")){
            System.out.println("Welcome in friend!");
        } else {
            System.out.println("Get out of here Slagathor!");
        }

    }
}
