package com.revature.lambdas.examples;

/**
 * A small roster entry for the collections example. Level 0 means the player
 * is knocked out of the fight.
 */
public record Player(String name, int level, int gold) {

    public boolean isDead() {
        return level() == 0;
    }

    public Player levelUp() {
        return new Player(name(), level() + 1, gold());
    }
}