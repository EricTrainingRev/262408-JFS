package com.revature.designs.singleton.eager;

/**
 * Enum singleton: a single constant that implements the singleton.
 *
 * The enum is the cleanest modern singleton in Java: the JVM guarantees
 * INSTANCE is created exactly once, safely, with no explicit locking.
 */
public enum GameSettings {

    INSTANCE;

    private String difficulty = "normal";

    public static GameSettings getInstance() {
        return INSTANCE;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static void main(String[] args) {
        GameSettings firstHandle = GameSettings.getInstance();
        GameSettings secondHandle = GameSettings.getInstance();

        firstHandle.setDifficulty("hard");

        System.out.println("-- two handles, one object --");
        System.out.println("same instance? " + (firstHandle == secondHandle));
        System.out.println("difficulty seen through the second handle: " + secondHandle.getDifficulty());
    }
}