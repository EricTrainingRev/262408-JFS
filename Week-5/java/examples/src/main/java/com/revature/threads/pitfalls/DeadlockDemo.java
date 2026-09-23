package com.revature.threads.pitfalls;

/**
 * A classic deadlock: two threads each hold one lock and wait forever for the
 * other's. Thread 1 grabs LOCK_A then wants LOCK_B; thread 2 grabs LOCK_B then
 * wants LOCK_A. Neither can proceed, and the program hangs by design.
 *
 * A lock (here an intrinsic/synchronized lock, taken with the synchronized
 * keyword) gives a thread exclusive access to the block it guards. Only one
 * thread may hold a given lock at a time, and any other thread that tries to
 * enter a synchronized block on the same lock blocks until it is released.
 *
 * Note a deadlock does not crash — it just quietly stops making progress, which
 * is why it is so hard to debug.
 */
public class DeadlockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread 1: holding LOCK_A");
                pause(50); // give thread 2 time to grab LOCK_B
                System.out.println("Thread 1: waiting for LOCK_B");
                synchronized (LOCK_B) {
                    System.out.println("Thread 1: got LOCK_B");
                }
            }
        });

        Thread second = new Thread(() -> {
            synchronized (LOCK_B) {
                System.out.println("Thread 2: holding LOCK_B");
                pause(50); // give thread 1 time to grab LOCK_A
                System.out.println("Thread 2: waiting for LOCK_A");
                synchronized (LOCK_A) {
                    System.out.println("Thread 2: got LOCK_A");
                }
            }
        });

        first.start();
        second.start();
        // main returns, but the non-daemon threads keep the JVM alive while
        // they block on each other forever, so the program hangs here.
    }

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}