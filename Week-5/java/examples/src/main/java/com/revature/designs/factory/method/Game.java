package com.revature.designs.factory.method;

public class Game {

    public static void main(String[] args) {
        LevelFactory battleFactory = new BattleLevelFactory("dungeon", 10);
        LevelFactory puzzleFactory = new PuzzleLevelFactory(true);

        System.out.println("-- factory gets access to the implementation we need --");
        Level battleLevel = battleFactory.createLevel();
        Level puzzLevel = puzzleFactory.createLevel();
        
        System.out.println(battleLevel.play());
        System.out.println(puzzLevel.play());
    }
    
}
