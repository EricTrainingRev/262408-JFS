package com.revature.efficiency;

import java.util.Random;

/**
 * O(n^2) — quadratic time. Work grows as the square of the input size.
 *
 * Bubble sort is the canonical example. Its two nested loops compare pairs
 * over and over. The outer loop runs n-1 passes while the inner loop compares
 * a shrinking window, which adds up to roughly n^2/2 comparisons. That is far
 * too slow for large inputs.
 *
 * There is no standard-library O(n^2) sort to lean on, because Java's built-in
 * sorts are all faster (O(n log n)) — so here the hand-written version IS the
 * demonstration.
 *
 * See bubble-sort.md for a step-by-step walkthrough.
 */
public class QuadraticTime {

    public static void main(String[] args) {
        System.out.println("-- O(n^2): bubble sort --");
        showSortCost(100);
        showSortCost(1_000);
        // Observe: n jumps 10x but the comparison count jumps ~100x. That
        // squaring of growth is the signature of O(n^2).
    }

    private static void showSortCost(int size) {
        int[] values = randomArray(size);
        long comparisons = bubbleSort(values);
        System.out.println("n=" + size + " -> comparisons=" + comparisons);
    }

    /**
     * Bubble sort: adjacent elements are compared, and if they are out of
     * order they swap. Each full pass "bubbles" the largest unsorted value to
     * its final position at the end, so the inner loop shrinks by one per pass.
     */
    private static long bubbleSort(int[] array) {
        long comparisons = 0;
        for (int i = 0; i < array.length - 1; i++) {
            // i elements at the end are already in their final position.
            for (int j = 0; j < array.length - 1 - i; j++) {
                comparisons++;
                // out of order? swap them
                if (array[j] > array[j + 1]) {  
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return comparisons;
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