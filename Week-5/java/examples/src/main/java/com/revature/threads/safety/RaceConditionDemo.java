package com.revature.threads.safety;

/**
 * A race condition, made visible. Ten threads each increment the SAME shared
 * int. count++ is really "read, add one, write back" and is not atomic: two
 * threads can read the same value and write the same result back, which
 * silently drops one increment. The final total comes out below expected.
 *
 * The increment is written as separate read/yield/write steps. The yield just
 * widens the read-modify-write window so another thread can step in between —
 * without it, on a quiet machine the tiny increments can happen to serialize
 * and hide the bug. The race itself is the point: use SynchronizedCounter to
 * see the fix.
 */
public class RaceConditionDemo {

    private static int count;
    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 200_000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int expected = THREADS * INCREMENTS_PER_THREAD;
        System.out.println("expected:   " + expected);
        System.out.println("actual:     " + count);
        System.out.println("lost updates: " + (expected - count));
    }

    /**
     * The non-atomic increment, with the read-modify-write window deliberately
     * widened (via yield) so another thread can interleave between read and
     * write.
     */
    private static void increment() {
        int value = count;    // read
        Thread.yield();        // added to make the read/modify/write window a bit wider
        count = value + 1;     // write
//        count++;
    }
}