package com.revature.designs.observer;

/**
 * The subscriber side of the observer pattern. Any class that wants to be
 * notified when a subject changes implements this interface.
 */
public interface Observer {

    void update();
}