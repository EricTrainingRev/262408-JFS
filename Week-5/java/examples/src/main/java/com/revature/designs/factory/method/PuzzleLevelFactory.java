package com.revature.designs.factory.method;

/**
 * A concrete creator: decides that createLevel() builds a PuzzleLevel.
 */
public class PuzzleLevelFactory extends LevelFactory {

    private boolean isHard;

    public PuzzleLevelFactory(boolean isHard){
        this.isHard = isHard;
    }

    @Override
    public Level createLevel() {
        return new PuzzleLevel(isHard);
    }
}
