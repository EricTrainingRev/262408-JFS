package com.revature.threads.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The same Producer-Consumer problem, using ArrayBlockingQueue — the modern,
 * safe approach. The queue itself enforces the rules: put() blocks when the buffer
 * is full (no overflow) and take() blocks when it is empty (no underflow), and all
 * the locking is handled internally. This lets the whole wait()/notifyAll() machinery
 * from ProducerConsumer shrink to two method calls.
 *
 * start() launches a worker thread to run its code in the background, while main
 * keeps going. join() then makes main pause until a specific worker finishes,
 * so main can act only once the work it depends on is done. Here start() lets all
 * four threads run concurrently, the joins on the producers ensure main does not
 * push the sentinels until both are finished, and the joins on the consumers
 * ensure the final printout runs only after all work is complete.
 */
public class ProducerConsumerBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3); // capacity 3
        final int sentinel = -1; // signals a consumer to stop

        Thread p1 = new Thread(() -> produce(queue, 10));
        Thread p2 = new Thread(() -> produce(queue, 10));
        Thread c1 = new Thread(() -> consumeUntilSentinel(queue, sentinel));
        Thread c2 = new Thread(() -> consumeUntilSentinel(queue, sentinel));

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        // Producers are done; push one sentinel per consumer so they exit.
        queue.put(sentinel);
        queue.put(sentinel);
        c1.join();
        c2.join();

        System.out.println("Worked a bounded capacity-3 queue; both consumers finished.");
    }

    private static void produce(BlockingQueue<Integer> queue, int howMany) {
        for (int i = 1; i <= howMany; i++) {
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void consumeUntilSentinel(BlockingQueue<Integer> queue, int sentinel) {
        try {
            while (true) {
                int value = queue.take();
                if (value == sentinel) {
                    System.out.println("Consumer saw sentinel and stopped");
                    return;
                }
                // real item consumed
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}