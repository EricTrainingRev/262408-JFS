package com.revature.threads.core;

/**
 * The states a thread moves through, observed at guaranteed moments.
 *
 * A thread is always in exactly one of six states:
 *
 * NEW: constructed but never started.
 * RUNNABLE: running, or eligible to run once the scheduler picks it up.
 * BLOCKED: waiting to acquire a monitor lock held by another thread.
 * WAITING: waiting indefinitely for another thread to act.
 * TIMED_WAITING: waiting for a bounded amount of time.
 * TERMINATED: run() has finished.
 *
 * The exact state immediately after start() is scheduling-dependent: the
 * thread may still be RUNNABLE, or may already have reached TIMED_WAITING by
 * the time we check. The NEW, TIMED_WAITING, and TERMINATED states are the
 * reliable ones to demonstrate.
 */
public class LifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(200); // the thread waits, but only briefly
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // NEW: constructed but never started.
        System.out.println("state before start():  " + worker.getState());

        worker.start();
        // RUNNABLE (or already TIMED_WAITING if it got scheduled instantly).
        System.out.println("state right after start: " + worker.getState());

        // Pause the main thread so the worker is mid-sleep when we look.
        Thread.sleep(100);
        // TIMED_WAITING: sleeping for a bounded time.
        System.out.println("state while it sleeps:  " + worker.getState());

        // this tells the main thread to pause until the worker thread is done
        worker.join();
//         TERMINATED: run() finished.
        System.out.println("state after join:       " + worker.getState());
    }
}