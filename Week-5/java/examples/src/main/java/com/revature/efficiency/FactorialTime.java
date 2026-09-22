package com.revature.efficiency;

/**
 * O(n!) — factorial time. Growth is faster than exponential and becomes
 * unusable almost immediately.
 *
 * Enumerating every permutation of n values is the canonical example: there
 * are exactly n! permutations (1, 2, 6, 24, 120, ...). The runtime is so steep
 * that moving from n=7 to n=8 multiplies the work by 8, and any real input
 * larger than about 10 is effectively impossible.
 *
 * The recursion below builds a permutation slot by slot, remembers which values
 * are already used, and counts every complete arrangement.
 *
 * See factorial-permutations.md for a visual walkthrough.
 */
public class FactorialTime {

    public static void main(String[] args) {
        System.out.println("-- O(n!): enumerating permutations --");
        showPermutationCount(5);
        showPermutationCount(6);
        showPermutationCount(7);
        showPermutationCount(8);
        showPermutationCount(9);
        showPermutationCount(10);
        // Observe the tally: 120, 720, 5040 (x6, then x7). Adding one element
        // multiplies the arrangement count by n. That is factorial growth.
    }

    private static void showPermutationCount(int n) {
        long[] permutations = {0};
        boolean[] used = new boolean[n];       // which values are already placed
        countPermutations(0, n, used, permutations);
        System.out.println("n=" + n + " -> permutations=" + permutations[0]);
    }

    /**
     * Backtracking permutation counter:
     *   - position is how many slots of the arrangement are already decided;
     *   - when all n slots are full, one complete permutation has been built,
     *     so the tally increments;
     *   - otherwise try every unused value in the current slot, and undo the
     *     claim (the used[...] = false is the "backtrack") before the next try.
     */
    private static void countPermutations(int position, int size, boolean[] used, long[] permutations) {
        if (position == size) {
            permutations[0]++; // a complete permutation
            return;
        }
        for (int value = 0; value < size; value++) {
            if (!used[value]) {
                used[value] = true; // claim this value for this slot
                countPermutations(position + 1, size, used, permutations);
                used[value] = false; // release it for other branches
            }
        }
    }
}