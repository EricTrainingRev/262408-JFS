package com.revature.designs.factory.method;

/**
 * A sealed base type for every level in the game.
 */
public sealed abstract class Level permits BattleLevel, PuzzleLevel {

    public abstract String play();
}
