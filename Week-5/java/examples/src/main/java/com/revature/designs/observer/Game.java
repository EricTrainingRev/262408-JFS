package com.revature.designs.observer;

/**
 * A demo that wires up a player and two observers, then runs a fight to show
 * each observer reacting to every change in the player's health. Game only
 * knows about Player, HealthBar and SoundEffect — it never talks to the
 * Observer list handling directly.
 */
public class Game {

    public static void main(String[] args) {
        Player player = new Player();
        HealthBar healthBar = new HealthBar(player);
        SoundEffect soundEffect = new SoundEffect(player);

        player.attach(healthBar);
        player.attach(soundEffect);

        System.out.println("-- player takes a few hits --");
        player.takeDamage(25);
        player.takeDamage(50);
        player.takeDamage(30);
    }
}