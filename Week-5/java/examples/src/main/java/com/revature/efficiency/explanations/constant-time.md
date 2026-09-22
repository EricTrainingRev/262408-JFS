# Array access, explained in ASCII (O(1))

Companion to `ConstantTime.java` — and the baseline every other complexity in this package is measured against.

O(1) means the amount of work does not grow as the input grows. Indexed array access is the canonical example: an array is laid out in one contiguous block of memory, so every element sits at a fixed offset from the start and the CPU can jump straight to any index in a single step — no scanning.

The same single step, at two very different array sizes:

```
n = 4            index:     0    1    2    3
                 values:  [ 2,   7,   1,   9 ]
                          values[2]  ->  1      (1 step)

n = 1,000,000    index:     0    1    2   ...  999,999
                 values:  [ 5,   8,   2,  ...,  77 ]
                          values[500000]  ->  1  (still 1 step)
```

Why O(1): a single fixed memory offset produces the answer at a constant cost, regardless of n. The operation count does not move. that flat line is why every other example in this package is "worse": even linear search is O(n), because it has to walk each element instead of jumping straight to it.