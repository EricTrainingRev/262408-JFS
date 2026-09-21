package com.revature.designs.observer;

/**
 * An observer that displays the player's health as a simple bar. It holds a
 * reference to the Player and pulls the current health whenever it is notified.
 */
public class HealthBar implements Observer {

    private final Player player;

    public HealthBar(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        int health = player.getHealth();
        int maxHealth = player.getMaxHealth();
        int filledSegments = (health * 10) / maxHealth;
        String bar = "#".repeat(filledSegments) + ".".repeat(10 - filledSegments);
        System.out.println("Health bar: [" + bar + "] " + health + "/" + maxHealth);
    }
}