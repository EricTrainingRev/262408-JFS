package com.revature.threads.safety;

/**
 * The fix for RaceConditionDemo: serialize the increment so only one thread
 * touches the counter at a time. A synchronized method locks the whole
 * instance for the duration of the call; the block-level equivalent locks a
 * specific object and is shown commented out below. With protection, the final
 * total is exactly right.
 */
public class SynchronizedCounter {

    private int count;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("expected: " + (10 * 10_000));
        System.out.println("actual:   " + counter.count());
    }

    /**
     * Method-level lock: the whole object is locked while this runs, so no two
     * threads can be mid-increment at once.
     *
     * Choose method synchronization when the entire body is one critical
     * section, and it stays short. It is the simplest to read and reason
     * about. Choose block synchronization when only part of the method needs
     * protection (so the rest of the work can still run in parallel), or when
     * you want to lock on a specific object other than "this". Block
     * synchronization also lets you keep the lock held for a shorter time,
     * which reduces contention.
     */
    public synchronized void increment() {
        System.out.println("count updated by " + Thread.currentThread().getName());
        count++;
    }

    // The block-level equivalent, protecting only this critical section:
    // public void increment() {
    //     synchronized (this) {
    //         count++;
    //     }
    // }

    public int count() {
        return count;
    }
}