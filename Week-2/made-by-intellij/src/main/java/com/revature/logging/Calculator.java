package com.revature.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    /*
        When creating your loggers you should keep them isolated in the class they are providing logs
        for. Pass in the name of the class as an easy way to keep track of where your logs are
        coming from. Note you can provide a custom name if you want
     */
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public int add(int numOne, int numTwo){
        // you format your strings by putting {} placeholders and then providing the data you want
        // to inject into the log after the String
        logger.info("Adding together {} and {} to make {}", numOne, numTwo, numOne + numTwo);
        return numOne + numTwo;
    }

    public int divide(int numOne, int numTwo){
        try{
            logger.debug("user entered numbers {} and {}", numOne, numTwo);
            return numOne / numTwo;
        } catch (ArithmeticException exception){
            logger.error("Error trying to divide {} by {}", numOne, numTwo);
            throw new NoDividingByZero("Can't divide by zero");
        }
    }
}
