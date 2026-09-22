package com.revature.algorithms.sorting;

import java.util.Arrays;

/**
 * Insertion sort. It builds a sorted region at the front of the array by
 * taking each new element and inserting it into the correct position among
 * the elements already sorted before it.
 *
 * Best case O(n), average and worst case O(n^2). Stable and sorts in place,
 * which is why it is a good choice for small or nearly-sorted inputs.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] data = {5, 1, 9, 3, 7};
        System.out.println("Original: " + Arrays.toString(data));
        sort(data);
        System.out.println("Sorted:   " + Arrays.toString(data));
    }

    public static void sort(int[] array) {
        // The element at index 0 is already a sorted region of one.
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int position = i - 1;

            // Shift larger elements right to make room for the current one.
            while (position >= 0 && array[position] > current) {
                array[position + 1] = array[position];
                position--;
            }

            array[position + 1] = current;
        }
    }
}