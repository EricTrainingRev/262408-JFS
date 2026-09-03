package com.revature.oop.inheritance;

/*
    Interfaces are like contracts: any class that "implements" an interface is
    agreeing to implement the content defined within the interface
 */
public interface Contract {
    /*
        All interface fields are public static final by default. There is no harm
        in adding the declarations yourself, but it is not needed. It is assumed
        whenever you are in an interface
     */
    String airSpeedVelocityOfAnUnladenSwallow = "African or European?";
    public static final int num = 0;

    // methods are public abstract by default, but again it does not hurt anything
    // if you add those keywords
    String returnAnswerToBridgeKeeperQuestion();

    // you can make static methods in your interfaces
    static String returnAnswerInStaticForm(){
        return airSpeedVelocityOfAnUnladenSwallow;
    }

    // you can also do default implementations for your interface methods
    default String returnAnswerDefaultVersion(){
        return Contract.airSpeedVelocityOfAnUnladenSwallow;
    }

}
