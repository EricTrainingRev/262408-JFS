package com.revature;

public class Loops {
    public static void main(String[] args) {
        /*
            Often you will want your code to loop, or continue running, either for a determined or undetermined
            amount of time. Java uses for loops to handle situations where you know how many times you want to loop,
            and while loops when the loop amount is undetermined
         */

        /*
            When making a loop you can manually control the execution with the example below
            int x = 1   -> this is the control variable
            x <= 10     -> this is the condition under which the loop will continue
            x++         -> this is what happens to our control variable at the end of each loop

            so in the example below the code runs 10 times and then the loop ends
         */
        for(int x = 1; x <= 10; x++){
            System.out.println(x);
        }

        /*
            A more common use case for loops is to iterate over data: typically you would store that data in an Array,
            List, or some other type of collection. Always remember with Arrays that indexing starts at 0, so the
            first element can be referenced via that index, the second at index 1, etc.

            Arrays are immutable in size once created: you can add and remove content from them as you please, but the
            size can not change. If you want to "resize" the Array you must make a new one
         */
        int[] myEmptyNumbers = new int[10]; // this initializes an empty Array with 10 spaces
        int[] myNumbers = {1,2,3,4,5,6,7,8,9,10}; // same as above, but there is starting data in the 10 spaces

        // You can iterate through a collection manually
        for(int i = 0; i < myNumbers.length; i++){
            // each iteration the index position we access changes
            int currentNum = getNumber(i, myNumbers);
            System.out.println(currentNum);
        }

        // or you can let an enhanced for loop handle the setup for you
        for(int number : myNumbers){
            System.out.println(number);
        }

        /*
            If you ever need to loop an undetermined amount of times you can use while loops. There are two options:
            a standard while loop will check your logical condition before the code runs, but a do while loop checks
            the condition after the code has run
         */

        int count = 1;
        while(count <= 10){
            System.out.println(count);
            count++; // without this we have an infinite loop: the logical check will always return true
        }

        do {
            System.out.println("This will print even though we hard code false below");
        } while(false);

    }

    public static int getNumber(int index, int[] nums){
        return nums[index];
    }
}
