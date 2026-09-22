package com.revature.algorithms.sorting;

import java.util.Arrays;

/**
 * Quick sort. A divide-and-conquer sort that picks a pivot, partitions the
 * array so everything smaller than the pivot is left of it and everything
 * larger is right of it, then recursively sorts each side.
 *
 * Average case O(n log n), worst case O(n^2) (for example on an already-sorted
 * input when the last element is used as the pivot). Sorts in place and is not
 * stable.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] data = {5, 1, 9, 3, 7};
        System.out.println("Original: " + Arrays.toString(data));
        sort(data, 0, data.length - 1);
        System.out.println("Sorted:   " + Arrays.toString(data));
    }

    public static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return; // a section of zero or one element is already sorted
        }
        int pivotIndex = partition(array, low, high);
        sort(array, low, pivotIndex - 1);
        sort(array, pivotIndex + 1, high);
    }

    /**
     * Rearranges array[low..high] so the pivot sits in its final position,
     * with every smaller element to its left and every larger to its right.
     * Returns the pivot's final index.
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];          // simplest choice: the last element
        int smallerIndex = low;            // boundary of the "smaller" region

        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) {
                swap(array, smallerIndex, i);
                smallerIndex++;
            }
        }

        swap(array, smallerIndex, high);
        return smallerIndex;
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}