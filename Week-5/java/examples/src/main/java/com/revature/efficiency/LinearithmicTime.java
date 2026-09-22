package com.revature.efficiency;

import java.util.Arrays;
import java.util.Random;

/**
 * O(n log n) — linearithmic time. Work grows as n times the log of n, which is
 * the best any comparison-based sort can do in the worst case.
 *
 * The easy approach is Arrays.sort, which hides the algorithm (Java uses
 * TimSort). To see what happens under the hood, a merge sort implementation is
 * included. Merge sort is O(n log n) because it does O(n) work per "level" of
 * its recursion, and there are about log2(n) levels.
 *
 * See merge-sort.md for a step-by-step walkthrough.
 */
public class LinearithmicTime {

    public static void main(String[] args) {
        System.out.println("-- O(n log n): sorting --");
        showSortCost(100);
        showSortCost(1_000);
        showSortCost(10_000);
        // Observe: n jumps by 10x, but comparison counts jump by only ~10x —
        // nowhere near the ~100x that quadratic growth would show. The modest
        // extra factor is the "log n" part of n log n.
    }

    private static void showSortCost(int size) {
        long libraryComparisons = sortWithArrays(size);
        long manualComparisons = sortWithMergeSort(size);
        System.out.println("n=" + size + " -> Arrays.sort=" + libraryComparisons
                + " comparisons, merge sort=" + manualComparisons + " comparisons");
    }

    /**
     * The easy path: lean on the standard library. A counting Comparator is
     * injected purely to observe how many comparisons the library performs
     * under the hood. (A lambda cannot mutate an outer long, so a one-cell
     * array is used as a mutable counter.)
     */
    private static long sortWithArrays(int size) {
        Integer[] values = boxedRandomArray(size);
        long[] comparisons = {0};
        Arrays.sort(values, (first, second) -> {
            comparisons[0]++;
            return Integer.compare(first, second);
        });
        return comparisons[0];
    }

    /**
     * The under-the-hood path: merge sort, counting its own comparisons.
     *
     * Merge sort is divide and conquer in two phases:
     *   divide - split the array in half, recursively sort each half;
     *   merge  - walk both sorted halves and stitch them back into one order.
     * The recursion tree has about log2(n) levels. At every level the merge
     * touches all n elements once, so total work is n * log2(n).
     */
    private static long sortWithMergeSort(int size) {
        int[] values = randomArray(size);
        long[] comparisons = {0};
        mergeSort(values, 0, values.length - 1, comparisons);
        return comparisons[0];
    }

    /**
     * Recursively sorts array[low..high] (inclusive) in place.
     */
    private static void mergeSort(int[] array, int low, int high, long[] comparisons) {
        // Base case: a section of zero or one element is already sorted.
        if (low >= high) {
            return;
        }
        // Split the section in half, then conquer each side before combining.
        int middle = low + (high - low) / 2;
        mergeSort(array, low, middle, comparisons);
        mergeSort(array, middle + 1, high, comparisons);
        // Both halves are sorted; now blend them into one sorted section.
        merge(array, low, middle, high, comparisons);
    }

    /**
     * Merges the two sorted halves array[low..middle] and array[middle+1..high]
     * into one sorted section covering array[low..high].
     */
    private static void merge(int[] array, int low, int middle, int high, long[] comparisons) {
        // Copy each half into a scratch buffer so we can write over the source.
        int[] left = Arrays.copyOfRange(array, low, middle + 1);
        int[] right = Arrays.copyOfRange(array, middle + 1, high + 1);

        int leftIndex = 0;
        int rightIndex = 0;
        int writeIndex = low;

        // Repeatedly take the smaller front element of the two halves. Each
        // trip through this loop is one comparison and fills one output slot.
        while (leftIndex < left.length && rightIndex < right.length) {
            comparisons[0]++;
            if (left[leftIndex] <= right[rightIndex]) {
                array[writeIndex++] = left[leftIndex++];
            } else {
                array[writeIndex++] = right[rightIndex++];
            }
        }

        // One half is exhausted; copy over whatever remains in the other.
        while (leftIndex < left.length) {
            array[writeIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            array[writeIndex++] = right[rightIndex++];
        }
    }

    private static Integer[] boxedRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random(42);
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    private static int[] randomArray(int size) {
        int[] array = new int[size];
        Random random = new Random(42);
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}