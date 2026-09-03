package com.revature;

import com.revature.exceptions.MyCheckedException;
import com.revature.exceptions.MyUncheckedException;

import java.util.Arrays;

public class Exceptions {
    /*
        All Exceptions we handle in our code are going to fall under one of two
        categories: checked and unchecked exceptions

        Checked exceptions directly inherit from the base Exception class: these
        types of exceptions must be handled within your application

        Unchecked exceptions inherit from RuntimeException, and these types of
        exceptions do not have to be handled (though you really should handle them)
        in your code
     */

    public static void main(String[] args) {
//        throwsOurUncheckedException();
        throwsOurCheckedExceptionAndHandlesIt();
        try{
            throwsOurCheckedExceptionAndDoesNotHandleIt();
        } catch (MyCheckedException exception){
            exception.printStackTrace();
        }
        System.out.println("This is after the stack trace");
    }


    public static void throwsOurUncheckedException(){
        throw new MyUncheckedException("This message is stored in the exception");
    }

    public static void throwsOurCheckedExceptionAndHandlesIt(){
        // if your code might throw a checked exception you can wrap it in a try/catch block
        try{
            throw new MyCheckedException("This message is in my checked exception");
        // if the exception is thrown you can handle it in the catch block
        } catch(MyCheckedException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void throwsOurCheckedExceptionAndDoesNotHandleIt() throws MyCheckedException{
        throw new MyCheckedException("This message is in my checked exception that is not handled immediately");
    }

}
