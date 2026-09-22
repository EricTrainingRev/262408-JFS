# Binary search, walked through in ASCII

Companion to `LogarithmicTime.java`. Binary search only works on a sorted array. Instead of scanning every element, it checks the middle and throws away the half the target cannot possibly be in. Each check halves the window, so 8 elements need only 3 checks.

The sorted array (8 elements):

```
[3, 9, 11, 17, 25, 31, 38, 42]
```

Search for: `25`

---

## Step 1 - check the middle of the whole array

```
window: [3, 9, 11, 17, 25, 31, 38, 42]    8 elements
mid (index 3) = 17

17 < 25   -> target is LARGER, so it must live in the RIGHT half
discard [3, 9, 11, 17]
keep    [25, 31, 38, 42]
```

## Step 2 - check the middle of the remaining window

```
window: [25, 31, 38, 42]                   4 elements
mid (index 1) = 31

31 > 25   -> target is SMALLER, so it must live in the LEFT half
discard [31, 38, 42]
keep    [25]
```

## Step 3 - one element left

```
window: [25]                               1 element
mid = 25

25 == 25  -> found it
```

---

## The halving, at a glance

```
[3, 9, 11, 17, 25, 31, 38, 42]   8 elements   step 1   compare 17, go right
          [25, 31, 38, 42]       4 elements   step 2   compare 31, go left
                    [25]         1 element    step 3   compare 25, found
```

---

## Why this is O(log n)

Every check cuts the window in half, so the number of checks is about log2(n): 3 checks for 8 elements, ~20 for a million — doubling n adds just ONE more check. That is the whole payoff over linear search (O(n)) from `LinearTime.java`, which would scan up to all 8 elements to reach the same answer. For the mid computation, the code uses `low + (high - low) / 2` rather than `(low + high) / 2` to avoid integer overflow on very large arrays