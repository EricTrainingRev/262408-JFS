package com.revature.efficiency;

import java.util.Random;

/**
 * O(n) — linear time. Work grows in exact proportion to the input size.
 *
 * Linear search is the canonical example. It checks elements one by one from
 * the start until it finds the target, so in the worst case (target absent, or
 * sitting at the far end) it examines all n elements.
 *
 * Compare this to LogarithmicTime: both search for a value, but linear search
 * is O(n) while binary search is O(log n). That difference is the whole story
 * of why the sorted-array precondition on binary search is worth paying for.
 *
 * See linear-search.md for a step-by-step walkthrough.
 */
public class LinearTime {

    public static void main(String[] args) {
        System.out.println("-- O(n): linear search --");
        showSearchCost(1_000);
        showSearchCost(100_000);
        // Observe: 100x more elements -> ~100x more checks. That 1:1 ratio is
        // the signature of O(n).
    }

    private static void showSearchCost(int size) {
        int[] values = randomArray(size);
        // Again we search for an absent value to force the worst case, so the
        // entire array has to be scanned before we give up.
        int checks = linearSearch(values, size + 1);
        System.out.println("n=" + size + " -> checks=" + checks);
    }

    /**
     * Scans left to right, counting every comparison. It returns as soon as
     * the target is found, or n once the array is exhausted.
     */
    private static int linearSearch(int[] array, int target) {
        int checks = 0;
        for (int i = 0; i < array.length; i++) {
            checks++;
            if (array[i] == target) {
                return checks; // found it early
            }
        }
        return checks; // never found: all n checked
    }

    private static int[] randomArray(int size) {
        Random random = new Random(42);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}