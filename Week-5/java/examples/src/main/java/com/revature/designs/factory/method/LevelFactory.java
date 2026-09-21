package com.revature.designs.factory.method;

/**
 * The creator in the factory method pattern. It declares the factory method
 * createLevel() but does not know which concrete Level will be built — each
 * subclass decides that.
 */
public abstract class LevelFactory {

    public abstract Level createLevel();

}
