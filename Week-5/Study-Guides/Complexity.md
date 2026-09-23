# Complexity

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Big O Notation](#2-big-o-notation)
    * [2.1 What is Big O?](#21-what-is-big-o)
    * [2.2 Common Big O Complexities](#22-common-big-o-complexities)
    * [2.3 Complexity Comparison Table](#23-complexity-comparison-table)
* [3. Time and Space Complexity](#3-time-and-space-complexity)
    * [3.1 Time Complexity](#31-time-complexity)
    * [3.2 Space Complexity](#32-space-complexity)
    * [3.3 The Trade-off: Time vs. Space](#33-the-trade-off-time-vs-space)

***

## 1. High-Level Overview

In computer science, **Complexity Analysis** is the tool used to measure the efficiency of an algorithm. Instead of measuring performance in seconds (which varies based on hardware), we measure how the requirements of an algorithm grow as the input size grows. This module covers the foundational concept of **Big O Notation** and how it distinguishes between **Time** and **Space** complexity.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we understand the purpose of measuring efficiency, let's dive into the mathematical language used to describe it: Big O Notation.*

## 2. Big O Notation

**Big O Notation** is a mathematical notation used to describe the **upper bound** of an algorithm's growth rate. It characterizes the "worst-case scenario," providing a guarantee that the algorithm will never perform worse than a certain level.

### 2.1 What is Big O?

Big O focuses on the **rate of growth** rather than exact step counts. When analyzing an algorithm, we ignore:
1.  **Constant factors:** $O(2n)$ simplifies to $O(n)$.
2.  **Lower-order terms:** $O(n^2 + n)$ simplifies to $O(n^2)$.

We are interested in the **dominant term** as the input size ($n$) approaches infinity.

### 2.2 Common Big O Complexities

| Notation | Name | Description | Example |
| :--- | :--- | :--- | :--- |
| **$O(1)$** | **Constant** | Execution time/space stays the same regardless of input size. | Accessing an array index. |
| **$O(\log n)$** | **Logarithmic** | Growth increases very slowly; input is halved each step. | Binary Search. |
| **$O(n)$** | **Linear** | Growth is directly proportional to the input size. | Iterating through a list once. |
| **$O(n \log n)$** | **Linearithmic** | Often found in efficient sorting algorithms. | Merge Sort. |
| **$O(n^2)$** | **Quadratic** | Growth is proportional to the square of the input size. | Nested loops (e.g., Bubble Sort). |
| **$O(2^n)$** | **Exponential** | Growth doubles with each addition to the input. | Recursive Fibonacci. |
| **$O(n!)$** | **Factorial** | Extremely fast growth; practically unusable for large $n$. | Traveling Salesperson Problem. |

### 2.3 Complexity Comparison Table

To visualize how these complexities behave as $n$ grows:

| $n$ | $O(1)$ | $O(\log n)$ | $O(n)$ | $O(n \log n)$ | $O(n^2)$ | $O(2^n)$ |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **10** | 1 | ~3 | 10 | ~33 | 100 | 1,024 |
| **100** | 1 | ~7 | 100 | ~664 | 10,000 | ~$1.2 \times 10^{30}$ |
| **1,000** | 1 | ~10 | 1,000 | ~10,000 | 1,000,000 | $\infty$ |

> [!TIP]
> When choosing an algorithm, always aim for the lowest growth rate possible for your expected input size.

[↑ Back to Table of Contents](#table-of-contents)
***

*Having established how we measure growth, we must now distinguish between the two primary dimensions of complexity: Time and Space.*

## 3. Time and Space Complexity

Complexity is categorized into two distinct dimensions: how much **time** an algorithm takes to run and how much **memory (space)** it requires to complete its task.

### 3.1 Time Complexity

**Time Complexity** quantifies the number of operations an algorithm performs relative to the input size $n$. It is not a measure of clock time, but a measure of the **computational steps** required.

*   **Focus:** The number of primitive operations (comparisons, assignments, arithmetic).
*   **Goal:** Minimize the total steps to ensure responsiveness.

### 3.2 Space Complexity

**Space Complexity** quantifies the amount of working memory an algorithm uses relative to the input size $n$.

This is typically divided into two parts:
1.  **Auxiliary Space:** The extra space or temporary space used by the algorithm (e.g., extra arrays, temporary variables).
2.  **Input Space:** The space occupied by the input itself.

When people discuss "Space Complexity," they are often referring specifically to **Auxiliary Space**.

> [!IMPORTANT]
> An algorithm might be very fast ($O(n)$ time) but consume massive amounts of memory ($O(n^2)$ space) to achieve that speed.

### 3.3 The Trade-off: Time vs. Space

In many engineering scenarios, you cannot optimize both simultaneously. This is known as the **Time-Space Trade-off**.

| Strategy | Description | Example |
| :--- | :--- | :--- |
| **Optimize for Time** | Use extra memory to store pre-calculated results or faster lookups. | **Memoization** in dynamic programming or using a **Hash Map** for $O(1)$ lookups. |
| **Optimize for Space** | Re-calculate values on the fly or use in-place algorithms to save memory. | **In-place sorting** (like Heap Sort) instead of creating new arrays. |

**Summary Table of Trade-offs**

| If you want... | You might have to... | Common Technique |
| :--- | :--- | :--- |
| **Faster Execution** | Use more memory | Caching / Memoization |
| **Lower Memory Footprint** | Use more CPU cycles | Re-computation / In-place logic |

[↑ Back to Table of Contents](#table-of-contents)
