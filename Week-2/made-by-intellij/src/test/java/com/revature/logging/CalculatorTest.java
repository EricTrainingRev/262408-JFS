package com.revature.logging;

import org.junit.jupiter.api.*;

/*
    When setting up your tests you should aim to follow the Arrange Act Assert pattern:
    Arrange -> set up your test data
    Act     -> perform your actions that are being tested
    Assert  -> check if the expected outcome is achieved
 */
public class CalculatorTest {

    public Calculator calculator;

    // NOTE: before/after All is expected to be static
    @BeforeAll
    public static void globalSetup(){
        System.out.println("This runs only once before all the tests");
    }

    @BeforeEach
    public void setup(){
        System.out.println("This runs before each individual test");
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown(){
        System.out.println("This runs after each individual test");
    }

    @AfterAll
    public static void globalTearDown(){
        System.out.println("This runs after all the tests are done");
    }

    /*
        When deciding how many times you need to run a method to be able to
        accurately say "this has been tested well" you can use boundary value
        analysis and/or equivalence partitioning as a starting point.

        Boundary Value Analysis     -> test the "edges" of your requirements
            - example: if testing password length is 5-15 characters you can start with 4 different passwords:
                - positive data
                    - 5 character password
                    - 15 character password
                - negative data
                    - 4 character password
                    - 16 character password
        Equivalence Partitioning    -> let 1 value represent all possible values of a "class"
            - example: testing if a password is correctly checked for lower, upper, and numeric characters
                - positive data
                    - P0sitive
                - negative data
                    - p0sitive
                    - Positive
                    - P0SITIVE


        Always remember: exhaustive testing is impossible. there will always be more you could potentially test, but a
        better use of time is to cover your requirements, any edge cases you can think of and have the time to accommodate,
        and then be confident you adequately minimized the risk of something going wrong.
     */

    @Test
    public void addPositive(){
        int sum = calculator.add(10,5);
        Assertions.assertEquals(15, sum);
    }

    @Test
    public void dividePositive(){
        int quotient = calculator.divide(10,5);
        Assertions.assertEquals(2, quotient);
    }

    @Test
    public void divideThrowsCustomException(){
        NoDividingByZero exception = Assertions.assertThrows(NoDividingByZero.class, ()->{
            calculator.divide(10,0);
        });
        Assertions.assertEquals("Can't divide by zero", exception.getMessage());
    }

}
