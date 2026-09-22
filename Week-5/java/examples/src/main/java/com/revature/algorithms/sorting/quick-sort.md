# Quick sort, walked through in ASCII

Companion to `QuickSort.java`.

Quick sort is divide and conquer around a pivot. Take a section of the array, pick the last element as the pivot, then partition: everything **smaller-or-equal** to the pivot shifts left of it, everything **larger** shifts right. That puts the pivot in its final, permanent spot. The two sides are then sorted the same way, recursively.

We sort: `[5, 1, 9, 3, 7]`

## 1. Partition the whole array — pivot 7

Working region: `[5, 1, 9, 3, 7]`. Group relative to the pivot:

```
pivot = 7
smaller-or-equal:  5  1  3
larger:            9
```

Place the pivot between the two groups:

```
before:  5  1  3 |  9   [7]
after:   5  1  3  [7]    9
                     ^
        pivot 7 lands at index 3, where it stays forever
```

Recurse on the two sides:

```
left:  [5, 1, 3]
right: [9]              <- one element, already sorted, stop
```

## 2. Partition `[5, 1, 3]` — pivot 3

Working region: `[5, 1, 3]`:

```
pivot = 3
smaller-or-equal:  1
larger:            5
```

Place the pivot between them:

```
before:    1  |  5  [3]
after:     1  [3]  5
                ^
        pivot 3 lands at index 1
```

Recurse on both sides — each is a single element, so both are done:

```
left:  [1]
right: [5]
```

## The whole run, as a recursion tree

```
              [5, 1, 9, 3, 7]      pivot 7 -> index 3
             /                 \
        [5, 1, 3]              [9]
        pivot 3 -> index 1      done
        /          \
      [1]         [5]
      done        done
```

Every `pivot -> index` spot is frozen the moment it is placed; the recursion touches only the unsorted-left and unsorted-right pieces that still surround it.

Sorted: `[1, 3, 5, 7, 9]`.

## Why O(n log n) average, O(n^2) worst

Each partition visits its whole section once, so an even split gives ~log n levels of work, each O(n) — O(n log n). But the last-element pivot on an already-sorted array splits off just one element per level for n levels, which is O(n^2). More comprehensive quick sorts dodge that worst case by choosing a better pivot instead of always the last element.