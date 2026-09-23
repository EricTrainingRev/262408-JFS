# Algorithms

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Sorting Algorithms](#2-sorting-algorithms)
* [3. Search Algorithms](#3-search-algorithms)
* [4. Greedy Algorithms](#4-greedy-algorithms)

***

## 1. High-Level Overview

**Algorithms** are well-defined, step-by-step procedures used for calculations, data processing, and automated reasoning. In computer science, an algorithm takes a set of inputs, processes them through a series of logical steps, and produces an output.

The efficiency of an algorithm is typically measured in two ways:
1. **Time Complexity:** How the execution time increases with the size of the input.
2. **Space Complexity:** How much additional memory is required as the input grows.

We use **Big O Notation** (e.g., $O(n)$, $O(log\ n)$, $O(n^2)$) to describe these complexities.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we have a foundational understanding of what algorithms are, let's dive into one of the most common categories: organizing data through sorting.*

## 2. Sorting Algorithms

**Sorting** is the process of arranging elements in a specific order (typically ascending or descending). Sorting is a fundamental building block for more complex operations like searching.

### 2.1 Comparison of Common Sorting Algorithms

| Algorithm | Best Case | Average Case | Worst Case | Space Complexity | Stability | Method |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | $O(n)$ | $O(n^2)$ | $O(n^2)$ | $O(1)$ | Yes | Comparison/Exchange |
| **Insertion Sort** | $O(n)$ | $O(n^2)$ | $O(n^2)$ | $O(1)$ | Yes | Comparison/Insertion |
| **Merge Sort** | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(n)$ | Yes | Divide & Conquer |
| **Quick Sort** | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$ | $O(\log n)$ | No | Divide & Conquer |

### 2.2 Key Concepts

**Stability in Sorting**
A sorting algorithm is **Stable** if it preserves the relative order of records with equal keys. For example, if you sort a list of people by first name, and then by last name, a stable sort ensures that people with the same last name remain sorted by their first name.

**Divide and Conquer**
Both **Merge Sort** and **Quick Sort** use the "Divide and Conquer" paradigm:
1. **Divide:** Break the problem into smaller sub-problems.
2. **Conquer:** Solve the sub-problems recursively.
3. **Combine:** Merge the solutions of the sub-problems to solve the original problem.

> [!TIP]
> Use **Insertion Sort** for very small datasets or datasets that are already nearly sorted. Use **Merge Sort** when stability is required, or **Quick Sort** for general-purpose high-performance sorting in memory.

 ### 2.3 Java Implementation Example: Bubble Sort
 ```java
 /**
  * Bubble Sort implementation.
  * 
  * Complexity:
  * - Time: O(n^2) - due to nested loops comparing adjacent elements.
  * - Space: O(1) - sorts in-place.
  */
 public class BubbleSort {
     /**
      * Sorts an array of integers using the Bubble Sort algorithm.
      * It repeatedly steps through the list, compares adjacent elements,
      * and swaps them if they are in the wrong order.
      * 
      * @param arr The array of integers to be sorted.
      */
     public static void sort(int[] arr) {
         int n = arr.length;
         // Outer loop: ensures we pass through the array n-1 times
         for (int i = 0; i < n - 1; i++) {
             // Inner loop: compares adjacent elements. 
             // After each pass, the largest element 'bubbles up' to the end.
             for (int j = 0; j < n - i - 1; j++) {
                 if (arr[j] > arr[j + 1]) {
                     // Swap elements if they are in the wrong order
                     int temp = arr[j];
                     arr[j] = arr[j + 1];
                     arr[j + 1] = temp;
                 }
             }
         }
     }

     public static void main(String[] args) {
         int[] data = {64, 34, 25, 12, 22, 11, 90};
         System.out.println("Original array: " + java.util.Arrays.toString(data));
        
         sort(data);
        
         System.out.println("Sorted array: " + java.util.Arrays.toString(data));
     }
 }
 ```

[↑ Back to Table of Contents](#table-of-contents)
***

*Once data is sorted, finding specific information within that data becomes significantly more efficient. This leads us to the study of search algorithms.*

## 3. Search Algorithms

**Searching** involves finding the location of a target element within a collection of data.

### 3.1 Linear Search vs. Binary Search

The most fundamental distinction in searching is whether the data is ordered.

| Feature | Linear Search | Binary Search |
| :--- | :--- | :--- |
| **Data Requirement** | None (works on unsorted data) | **Must be sorted** |
| **Time Complexity** | $O(n)$ | $O(\log n)$ |
| **Approach** | Sequential check | Divide and conquer |

**Linear Search**
The simplest method: check every element one by one until the target is found or the list ends. It is reliable but inefficient for large datasets.

**Binary Search**
A highly efficient algorithm that repeatedly divides the search interval in half. If the target value is less than the item in the middle of the interval, narrow the interval to the lower half. Otherwise, narrow it to the upper half.

> [!IMPORTANT]
> **Binary Search Requirement:** You cannot use Binary Search on an unsorted list. The cost of sorting the list first ($O(n \log n)$) is usually higher than just doing a Linear Search ($O(n)$) if you only need to search once.

 ### 3.2 Java Implementation Example: Binary Search
 ```java
 /**
  * Binary Search implementation.
  * 
  * Complexity:
  * - Time: O(log n) - divides the search space in half each step.
  * - Space: O(1) - iterative approach uses constant space.
  */
 public class BinarySearch {
     /**
      * Searches for a target integer in a sorted array.
      * 
      * @param arr The sorted array to search.
      * @param target The integer to find.
      * @return The index of the target, or -1 if not found.
      */
     public static int search(int[] arr, int target) {
         int left = 0, right = arr.length - 1;
        
         while (left <= right) {
             // Calculate mid point avoiding potential integer overflow
             int mid = left + (right - left) / 2;
            
             // Check if target is at mid
             if (arr[mid] == target) {
                 return mid;
             }
            
             // If target is greater, ignore left half
             if (arr[mid] < target) {
                 left = mid + 1;
             } 
             // If target is smaller, ignore right half
             else {
                 right = mid - 1;
             }
         }
         // Target was not present in array
         return -1;
     }

     public static void main(String[] args) {
         int[] data = {11, 12, 22, 25, 34, 64, 90}; // Array MUST be sorted
         int target = 25;
        
         System.out.println("Searching for: " + target);
         int result = search(data, target);
        
         if (result != -1) {
             System.out.println("Element found at index: " + result);
         } else {
             System.out.println("Element not found in the array.");
         }
     }
 }
 ```

[↑ Back to Table of Contents](#table-of-contents)
***

*While sorting and searching focus on organizing and finding data, some algorithms focus on making the "best" choice at every step to solve optimization problems.*

## 4. Greedy Algorithms

A **Greedy Algorithm** is an algorithmic paradigm that follows the heuristic of making the **locally optimal choice** at each stage with the hope of finding a **globally optimal solution**.

### 4.1 The Greedy Strategy

The core idea is: "Take what looks best right now."

1. **Selection Procedure:** Choose the best candidate available.
2. **Feasibility Check:** Ensure the candidate doesn't violate constraints.
3. **Integration:** Add the candidate to the solution set.

### 4.2 Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Speed:** Usually very fast and computationally efficient. | **Sub-optimality:** May fail to find the absolute best solution. |
| **Simplicity:** Easy to design and implement. | **Short-sightedness:** Cannot "undo" a decision once made. |

### 4.3 Classic Examples

* **Dijkstra's Algorithm:** Finds the shortest path between nodes in a graph by always picking the closest unvisited node.
* **Huffman Coding:** Used for lossless data compression by building a tree based on frequency.
* **Fractional Knapsack Problem:** Choosing items with the highest value-to-weight ratio to maximize total value.

> [!WARNING]
> **Greedy vs. Dynamic Programming:** Greedy algorithms make decisions that are final. **Dynamic Programming** explores all possible sub-problems and combines them to ensure a global optimum. If a problem requires looking ahead to make the right decision, a Greedy approach will likely fail.

 ### 4.4 Java Implementation Example: Fractional Knapsack
 ```java
 import java.util.*;

 /**
  * Represents an item in the Knapsack problem.
  */
 class Item {
     int value, weight;
     double ratio;

     /**
      * @param value The total value of the item.
      * @param weight The total weight of the item.
      */
     Item(int value, int weight) {
         this.value = value;
         this.weight = weight;
         // Pre-calculate ratio for greedy selection
         this.ratio = (double) value / weight;
     }
 }

 /**
  * Greedy Algorithm implementation for the Fractional Knapsack problem.
  * 
  * Complexity:
  * - Time: O(n log n) - due to sorting items by their value/weight ratio.
  * - Space: O(n) - to store the list of items.
  */
 public class GreedyKnapsack {
     /**
      * Solves the Fractional Knapsack problem using a greedy approach.
      * It picks items with the highest value-to-weight ratio first.
      * 
      * @param values Array of item values.
      * @param weights Array of item weights.
      * @param capacity The maximum weight the knapsack can hold.
      * @return The maximum total value possible.
      */
     public static double getMaxValue(int[] values, int[] weights, int capacity) {
         List<Item> items = new ArrayList<>();
         for (int i = 0; i < values.length; i++) {
             items.add(new Item(values[i], weights[i]));
         }

         // Step 1: Sort items by ratio in descending order (Greedy Choice)
         items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

         double totalValue = 0.0;
        
         // Step 2: Iterate through sorted items and take as much as possible
         for (Item item : items) {
             if (capacity - item.weight >= 0) {
                 // Take the whole item
                 capacity -= item.weight;
                 totalValue += item.value;
             } else {
                 // Take a fraction of the item to fill the remaining capacity
                 totalValue += item.ratio * capacity;
                 // Knapsack is now full
                 break;
             }
         }
         return totalValue;
     }

     public static void main(String[] args) {
         int[] values = {60, 100, 120};
         int[] weights = {10, 20, 30};
         int capacity = 50;
        
         double maxValue = getMaxValue(values, weights, capacity);
         System.out.println("Maximum value achievable in Knapsack: " + maxValue);
     }
 }
 ```

[↑ Back to Table of Contents](#table-of-contents)
