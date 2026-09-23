package com.revature.threads.pitfalls;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Livelock: neither thread is BLOCKED, both stay RUNNABLE and busy, yet
 * neither makes progress. Each worker permanently holds its own lock and keeps
 * retrying to grab the other's. tryLock() is the non-blocking acquire: it
 * returns false immediately instead of waiting, so these are active busy loops,
 * not blocked threads. If they used plain synchronized they would DEADLOCK
 * instead: blocked, not busy.
 *
 * Attempts are capped so the demo ends; uncapped the livelock would spin forever.
 *
 * A ReentrantLock plays the same guard role as a synchronized block but is an
 * explicit object from java.util.concurrent. It offers what synchronized
 * cannot: non-blocking tryLock(), timed lock acquisition, and interruptible
 * waiting. Reentrant means the owning thread may acquire the same lock again
 * without deadlocking on itself. Every lock() must be paired with an
 * unlock(), typically in a finally block — there is no automatic release as
 * with synchronized.
 */
public class LivelockDemo {

    private static final ReentrantLock LOCK_A = new ReentrantLock();
    private static final ReentrantLock LOCK_B = new ReentrantLock();
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Thread first = new Thread(() -> workerHolding(LOCK_A, LOCK_B, "Worker 1"));
        Thread second = new Thread(() -> workerHolding(LOCK_B, LOCK_A, "Worker 2"));

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Both workers gave up after " + MAX_ATTEMPTS + " attempts.");
    }

    private static void workerHolding(ReentrantLock homeLock, ReentrantLock otherLock, String name) {
        homeLock.lock();
        sleep(100);
        try {
            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
                if (otherLock.tryLock()) {
                    try {
                        System.out.println(name + " got both locks on attempt " + attempt);
                        return;
                    } finally {
                        otherLock.unlock();
                    }
                }
                // Could not get the other lock. Both workers back off for the
                // same short time, so they stay in step and keep colliding.
                System.out.println(name + ": holding home lock, cannot grab the other (attempt " + attempt + ")");
                sleep(10);
            }
        } finally {
            homeLock.unlock();
        }
        System.out.println(name + " gave up");
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}