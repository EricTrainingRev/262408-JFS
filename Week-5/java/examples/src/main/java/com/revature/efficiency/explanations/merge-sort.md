# Merge sort walked through

Companion to `LinearithmicTie.java`. Merge sort is divide and conquer: split the array in half, sort each half by the same rule, then merge the two sorted halves back into one. We watch it happen on a small 5-element array so every step is visible.

Start with one unsorted array of 5 elements:

```
[38, 27, 43, 3, 9]
```

---

## Phase 1 - Divide

Keep splitting every sub-array at its middle until each piece holds a single
element (a one-element array is trivially sorted).

```
                [38, 27, 43, 3, 9]
                       /        \
              [38, 27, 43]      [3, 9]
                 /     \         /   \
            [38, 27]  [43]    [3]   [9]
              /   \
           [38]  [27]
```

Halving n=5 down to singles produces roughly log2(5) ~ 3 levels of splitting.
That "log n" is the first half of the O(n log n).

---

## Phase 2 - Conquer (the merge)

Now walk back up, merging pairs of sorted pieces. Each merge takes two sorted
lists and weaves them into one by always taking the smaller front element.

### Merge [38] and [27]

```
left:  [38]
right: [27]

compare 27 < 38  -> take 27
take 38 left over
result [27, 38]
```

### Merge [27, 38] and [43]

```
left:  [27, 38]
right: [43]

compare 27 < 43  -> take 27
compare 38 < 43  -> take 38
left empty      -> take 43
result [27, 38, 43]
```

### Merge [3] and [9]

```
left:  [3]
right: [9]

compare 3 < 9  -> take 3
take 9 left over
result [3, 9]
```

### Merge [27, 38, 43] and [3, 9]  (the final weave)

```
half A = [27, 38, 43]
half B = [3, 9]

step 1   3 < 27  => take 3            (from B)
step 2   9 < 27  => take 9            (from B)
step 3   B is empty => take 27, 38, 43

result = [3, 9, 27, 38, 43]
```

The whole merge phase at a glance, level by level:

```
singles:        [38]   [27]     [43]      [3]   [9]
                   \     /                  \     /
level 1:      [27, 38]          [43]     [3, 9]
                   \             /         /
level 2:      [27, 38, 43]               [3, 9]
                        \                 /
level 3:             [3, 9, 27, 38, 43]
```

Sorted: `[3, 9, 27, 38, 43]`.

---

## Why this is O(n log n)

At each of the ~log2(n) levels, the merge step walks across all n elements exactly once (every element moves once into the merged output). So total work is roughly n elements x log2(n) levels = O(n log n). Doubling the input adds only one more level, not double the levels — which is why it stays fast.