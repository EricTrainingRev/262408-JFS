package com.revature.threads.core;

/**
 * The two ways to define a unit of work for a thread:
 *   - extend the Thread class and override run(), or
 *   - implement the Runnable interface and hand it to a Thread.
 *
 * Extending Thread uses up the single-inheritance slot and couples the task to
 * the runner. Implementing Runnable keeps the task separate, reusable, and is
 * the generally recommended approach.
 */
public class ThreadAndRunnable {

    public static void main(String[] args) {
        // Way 1: a class that extends Thread.
        new WorkerThread().start();

        // Way 2: a Runnable handed to a Thread constructor.
        Thread viaRunnable = new Thread(new WorkerRunnable());
        viaRunnable.start();
    }
}

/** A task defined by extending the Thread class. */
class WorkerThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread task running (extended Thread)");
    }
}

/** A task defined by implementing Runnable. */
class WorkerRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable task running (implemented Runnable)");
    }
}