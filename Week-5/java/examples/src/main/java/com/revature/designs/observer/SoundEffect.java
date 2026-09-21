package com.revature.designs.observer;

/**
 * An observer that plays a sound when the player's health changes. It reacts
 * differently depending on how low the health has fallen.
 */
public class SoundEffect implements Observer {

    private static final int CRITICAL_HEALTH = 30;

    private final Player player;

    public SoundEffect(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (player.isDead()) {
            System.out.println("Sound: game over jingle");
        } else if (player.getHealth() <= CRITICAL_HEALTH) {
            System.out.println("Sound: danger heartbeat");
        } else {
            System.out.println("Sound: hit grunt");
        }
    }
}