package com.revature.designs.factory.simple;

/**
 * An archer NPC. Final, as required by the sealed contract.
 */
public final class Archer extends NPC {

    @Override
    public String describe() {
        return "An archer, watching from a distance.";
    }
}
