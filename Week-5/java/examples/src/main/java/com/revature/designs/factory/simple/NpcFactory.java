package com.revature.designs.factory.simple;

/**
 * A simple factory: one static method that decides which NPC to build from a
 * type name. The caller asks for a type and gets back the right concrete NPC
 */
public final class NpcFactory {

    private NpcFactory() {
        // Utility class: not meant to be instantiated.
    }

    public static NPC createNpc(String type) {
        return switch (type) {
            case "soldier" -> new Soldier();
            case "archer" -> new Archer();
            default -> throw new IllegalArgumentException("Unknown NPC type: " + type);
        };
    }

    public static void main(String[] args) {
        NPC soldier = NpcFactory.createNpc("soldier");
        NPC archer = NpcFactory.createNpc("archer");

        System.out.println("-- factory returns the right concrete type --");
        System.out.println(soldier.describe());
        System.out.println(archer.describe());
        System.out.println("soldier is a Soldier? " + (soldier instanceof Soldier));
        System.out.println("archer is an Archer? " + (archer instanceof Archer));
    }
}
