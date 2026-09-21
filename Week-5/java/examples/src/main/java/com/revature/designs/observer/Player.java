package com.revature.designs.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The subject. It keeps a list of observers and notifies them whenever its
 * health changes. Player does not know what its observers do — it only knows
 * they implement the Observer interface.
 */
public class Player {

    private static final int MAX_HEALTH = 100;

    private final List<Observer> observers = new ArrayList<>();
    private int health;

    public Player() {
        this.health = MAX_HEALTH;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public boolean isDead() {
        return health == 0;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}