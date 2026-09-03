package com.revature.oop.inheritance;

public class ChildTwo extends Child implements Contract{

    public ChildTwo(int age, String name, String grade) {
        // The Child constructor needs all 3 arguments, so we pass them to
        // the super call
        super(age, name, grade);
    }

    @Override
    public void sayCatchPhrase() {
        System.out.println("I love the Opera!");
    }

    public void sing(){
        System.out.println("La-la-la-la-LLLLLAAAAAAAAAAAA!");
    }

    @Override
    public String returnAnswerToBridgeKeeperQuestion() {
        return "I don't know.... AAAAAAHHHHHHH!";
    }
}
