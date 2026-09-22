package com.revature.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Fractional knapsack, solved greedily. Given items, each with a value and a
 * weight, and a knapsack of fixed capacity, the goal is to maximize the total
 * value carried. Unlike the 0/1 knapsack, a fraction of an item may be taken.
 *
 * The greedy strategy is to always take the item with the best value-to-weight
 * ratio first. Sorting by ratio costs O(n log n); the filling pass is O(n).
 *
 * This works for the fractional version, but would NOT be correct for the 0/1
 * knapsack; that problem needs dynamic programming instead.
 */
public class FractionalKnapsack {

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>(List.of(
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)));

        int capacity = 50;
        double maxValue = maxValue(items, capacity);

        System.out.println("Maximum value in a knapsack of capacity " + capacity + ": " + maxValue);
    }

    public static double maxValue(List<Item> items, int capacity) {
        // Work on a copy so the caller's list stays in its original order.
        List<Item> byRatio = new ArrayList<>(items);
        // Greedy choice step: best value-to-weight ratio first.
        byRatio.sort((first, second) -> Double.compare(ratio(second), ratio(first)));

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        for (Item item : byRatio) {
            if (item.weight() <= remainingCapacity) {
                // Take the whole item.
                remainingCapacity -= item.weight();
                totalValue += item.value();
            } else {
                // Take only the fraction needed to fill the remaining space.
                totalValue += ratio(item) * remainingCapacity;
                break; // the knapsack is now full
            }
        }

        return totalValue;
    }

    private static double ratio(Item item) {
        return item.value() / (double) item.weight();
    }
}

/**
 * One knapsack item: a value and the weight it takes up.
 */
record Item(int value, int weight) {
}