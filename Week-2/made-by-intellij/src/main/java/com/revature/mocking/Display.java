package com.revature.mocking;

public class Display {

    private Content content;

    public Display(Content content){
        this.content = content;
    }

    public String displayContent(){
        return content.getContent();
    }

}
