package com.revature.threads.demo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The classic Producer-Consumer problem, solved with wait()/notifyAll().
 *
 * Producers add items to a bounded buffer; consumers take items out. Three
 * rules must hold: never add when full (overflow), never remove when empty
 * (underflow), and only one thread touches the buffer at a time (mutual
 * exclusion). Each wait() releases the lock so the other side can run; each
 * notifyAll() wakes whoever is waiting.
 *
 * The notes point out that ArrayBlockingQueue (see ProducerConsumerBlockingQueue)
 * handles all of this safely and is the modern recommendation — this class
 * exists to show the manual mechanics underneath.
 */
public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        BlockingBuffer buffer = new BlockingBuffer(3); // capacity 3
        final int total = 20; // 2 producers x 10 items each

        Thread p1 = new Thread(() -> produce(buffer, 10));
        Thread p2 = new Thread(() -> produce(buffer, 10));
        Thread c1 = new Thread(() -> consumeUntilDone(buffer));
        Thread c2 = new Thread(() -> consumeUntilDone(buffer));

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        buffer.finishProduction(); // tell consumers there is no more coming
        c1.join();
        c2.join();

        System.out.println("Produced: " + buffer.producedCount() + ", Consumed: " + buffer.consumedCount()
                + " (expected " + total + ")");
    }

    private static void produce(BlockingBuffer buffer, int howMany) {
        for (int i = 1; i <= howMany; i++) {
            try {
                buffer.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void consumeUntilDone(BlockingBuffer buffer) {
        try {
            while (buffer.consume() != null) {
                // consumed items are tallied inside the buffer
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * A custom bounded buffer that enforces the three producer-consumer rules.
     *
     * Waiting and notifying are handled by hand here. A full producer calls
     * wait(), which releases the monitor lock so a consumer can enter and
     * drain the buffer; wait() returns only after a notifyAll() reacquires
     * the lock. Consumers do the mirror image when the buffer is empty.
     * Every wake path calls notifyAll() so whichever side is blocked gets a
     * chance to run.
     *
     * Every method here is synchronized because wait(), notifyAll(), and the
     * shared fields (items, produced, consumed, productionDone) require safe
     * mutual exclusion. Beyond that, wait() and notifyAll() are only legal
     * while holding the intrinsic lock of the object they are called on, so
     * they cannot appear outside a synchronized block or method.
     *
     * Note the while loops around each wait(): the condition (full or empty)
     * must be re-checked after waking, because a notifyAll() can wake a thread
     * for a reason other than the exact condition it was waiting on.
     */
    static class BlockingBuffer {

        private final Deque<Integer> items = new ArrayDeque<>();
        private final int capacity;
        private int produced;
        private int consumed;
        private boolean productionDone;

        BlockingBuffer(int capacity) {
            this.capacity = capacity;
        }

        synchronized void produce(int value) throws InterruptedException {
            while (items.size() == capacity) {
                wait(); // full: release the lock and wait for a consumer
            }
            items.addLast(value);
            produced++;
            notifyAll();
        }

        synchronized Integer consume() throws InterruptedException {
            while (items.isEmpty() && !productionDone) {
                wait(); // empty: release the lock and wait for a producer
            }
            if (items.isEmpty()) {
                return null; // production finished and all items drained
            }
            int value = items.removeFirst();
            consumed++;
            notifyAll();
            return value;
        }

        synchronized void finishProduction() {
            productionDone = true;
            notifyAll();
        }

        synchronized int producedCount() {
            return produced;
        }

        synchronized int consumedCount() {
            return consumed;
        }
    }
}