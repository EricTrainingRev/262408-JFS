package com.revature.mocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisplayIT {

    public Display display;
    public Content content;

    @BeforeEach
    public void setup(){
        content = new Content();
        display = new Display(content);
    }

    /*
        This is an Integration Test: the test is really checking that both
        displayContent and getContent in the Content object are working correctly.
        For something small like this it's not an issue, but when dependencies are
        deep this can become problematic. If a test fails, it can be difficult to
        pinpoint what went wrong, or how many things went wrong
     */
    @Test
    public void displayContentPositive(){
        String message = display.displayContent();
        Assertions.assertEquals("This is the content", message);
    }


}
