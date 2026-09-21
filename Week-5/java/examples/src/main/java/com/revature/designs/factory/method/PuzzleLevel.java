package com.revature.designs.factory.method;

/**
 * A puzzle level. Final, as required by the sealed contract.
 */
public final class PuzzleLevel extends Level {

    private boolean isHard;

    public PuzzleLevel(boolean isHard){
        this.isHard = isHard;
    }

    @Override
    public String play() {
        return isHard ? "Starting a hard puzzle level" : "Starting an easy puzzle level";
    }
}
