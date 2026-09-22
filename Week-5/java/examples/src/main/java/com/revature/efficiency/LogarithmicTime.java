package com.revature.efficiency;

/**
 * O(log n) — logarithmic time. With every step the amount of remaining work is
 * halved, so it rises only as fast as the base-2 logarithm of the input size.
 *
 * Binary search is the canonical example. It only works on a sorted array. It
 * compares the target against the middle element, then discards the half of
 * the array the target cannot be in, and repeats on what remains. Because each
 * comparison halves the search space, an array of n elements needs at most
 * about log2(n) comparisons.
 *
 * See binary-search.md for a step-by-step walkthrough.
 */
public class LogarithmicTime {

    public static void main(String[] args) {
        System.out.println("-- O(log n): binary search on a sorted array --");
        showSearchCost(8);
        showSearchCost(64);
        showSearchCost(512);
        // Observe: n goes 8 -> 64 -> 512 (a x8 jump each time), but the count
        // only climbs 4 -> 7 -> 10. That slow climb is log2 growth.
    }

    private static void showSearchCost(int size) {
        int[] sorted = sortedArray(size);
        // We search for a value that is NOT present. That forces the worst
        // case, so every halving step runs before the search gives up.
        int checks = binarySearch(sorted, size + 1);
        System.out.println("n=" + size + " -> checks=" + checks);
    }

    /**
     * Returns the number of checks performed while searching for target.
     *
     * The window is the range [low, high]. Each iteration inspects the middle
     * and, based on the comparison, narrows the window to the left or right
     * half. The loop ends once the window closes (low overtakes high).
     */
    private static int binarySearch(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        int checks = 0;
        while (low <= high) {
            checks++;
            // Writing the midpoint as low + (high - low)/2 avoids the integer
            // overflow that low + high could cause on very large arrays.
            int middle = low + (high - low) / 2;
            if (sorted[middle] == target) {
                return checks; // found it
            } else if (sorted[middle] < target) {
                low = middle + 1; // target must be in the right half
            } else {
                high = middle - 1; // target must be in the left half
            }
        }
        return checks; // target absent: window closed
    }

    private static int[] sortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
}