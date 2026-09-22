# Linear search, walked through in ASCII (O(n))

Companion to `LinearTime.java`. This is the "naive" search that
`binary-search.md` improves on, so it uses the SAME array and SAME target to
make the comparison easy.

Linear search scans the array from the front, checking one element at a time
until it finds the target. If the target is absent or near the end, it examines
all (or nearly all) of the elements.

```
sorted array: [3, 9, 11, 17, 25, 31, 38, 42]
target: 25
```

```
check 1   3  != 25   -> keep scanning
check 2   9  != 25   -> keep scanning
check 3   11 != 25   -> keep scanning
check 4   17 != 25   -> keep scanning
check 5   25 == 25   -> found  (5 checks out of 8)
```

Linear search needs 5 checks here; binary search finds the same 25 in 3. The gap
only widens as n grows: linear time checks up to n elements, binary time checks
about log2(n).

Why O(n): the worst case touches all n elements, so the work grows in direct
proportion to n. Doubling the input doubles the worst-case number of checks.