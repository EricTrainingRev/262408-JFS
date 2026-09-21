package com.revature.designs.factory.simple;

/**
 * A soldier NPC. Final, as required by the sealed contract.
 */
public final class Soldier extends NPC {

    @Override
    public String describe() {
        return "A soldier, armed and ready.";
    }
}
