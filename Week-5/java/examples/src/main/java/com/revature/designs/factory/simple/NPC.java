package com.revature.designs.factory.simple;

/**
 * A sealed base type for every non-player character in the game.
 */
public sealed abstract class NPC permits Soldier, Archer {

    public abstract String describe();
}
