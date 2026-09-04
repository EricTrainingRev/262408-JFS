package com.revature.mocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DisplayTest {

    public Display display;
    public Content content;

    @BeforeEach
    public void setup(){
        content = Mockito.mock(Content.class);
        display = new Display(content);
    }

    @Test
    public void unitTest(){
        Mockito.when(content.getContent()).thenReturn("We can return whatever we want");
        String message = display.displayContent();
        Assertions.assertEquals("We can return whatever we want", message);
        Mockito.verify(content, Mockito.times(2)).getContent();
    }

}
