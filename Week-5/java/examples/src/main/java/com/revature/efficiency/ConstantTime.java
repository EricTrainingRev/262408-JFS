package com.revature.efficiency;

import java.util.Random;

/**
 * O(1) — constant time. The runtime does not depend on the input size at all.
 *
 * Indexed array access, array[i], is the canonical O(1) operation. An array is
 * laid out in one contiguous block of memory, and each element sits a fixed
 * byte distance from the start (index times the element size). The CPU can
 * jump straight to any element — no scanning involved — so the cost is the
 * same whether the array holds 10 values or 10 million.
 *
 * The demonstration builds two arrays and reads one element out of each. The
 * count of core operations is exactly 1, regardless of n.
 *
 * See constant-time.md for a visual walkthrough.
 */
public class ConstantTime {

    public static void main(String[] args) {
        System.out.println("-- O(1): indexed array access --");
        showAccessCost(100);
        showAccessCost(1_000_000);
        // Observe: growing n a thousandfold never changes the operation count. It stays exactly 1.
    }

    private static void showAccessCost(int size) {
        int[] values = randomArray(size);
        // the single index lookup
        int operations = 1;                    
        // The core operation: read whatever lives at the middle index.
        int value = values[values.length / 2];
        System.out.println("n=" + size + " -> operations=" + operations + " (value " + value + ")");
    }

    /**
     * Helper that fills an array of the requested size. A fixed seed keeps the
     * values identical on every run, so each example gives reproducible output.
     */
    private static int[] randomArray(int size) {
        Random random = new Random(42);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}