# Insertion sort, walked through in ASCII

Companion to `InsertionSort.java`.

Insertion sort grows a sorted run at the start of the array. For each new element
it slides the element left, past every larger element, until it reaches the
right spot. This is the same starting array as `QuickSort.java`, and it ends in
the same sorted result — two different algorithms reaching the same answer.

We sort: `[5, 1, 9, 3, 7]`

The first element, `5`, is already a sorted run of one:
`[5] | 1, 9, 3, 7`

## Insert 1

```
sorted run:  [5]
new element:  1

compare 1 with 5:   5 > 1, so shift 5 right
insert into the gap:  [1, 5]

array now:  [1, 5 | 9, 3, 7]
```

## Insert 9

```
sorted run:  [1, 5]
new element:  9

compare 9 with 5:   5 < 9, stop (already in place)
sorted run:  [1, 5, 9]

array now:  [1, 5, 9 | 3, 7]
```

## Insert 3

```
sorted run:  [1, 5, 9]
new element:  3

compare 3 with 9:   9 > 3, shift 9 right
compare 3 with 5:   5 > 3, shift 5 right
compare 3 with 1:   1 < 3, stop
insert into the gap:  [1, 3, 5, 9]

array now:  [1, 3, 5, 9 | 7]
```

## Insert 7

```
sorted run:  [1, 3, 5, 9]
new element:  7

compare 7 with 9:   9 > 7, shift 9 right
compare 7 with 5:   5 < 7, stop
insert into the gap:  [1, 3, 5, 7, 9]

array now:  [1, 3, 5, 7, 9]
```

Sorted: `[1, 3, 5, 7, 9]`.

## The run at a glance

```
start  [5 | 1, 9, 3, 7]
step 1 [1, 5 | 9, 3, 7]
step 2 [1, 5, 9 | 3, 7]
step 3 [1, 3, 5, 9 | 7]
step 4 [1, 3, 5, 7, 9]
```

## Why O(n^2) average, O(n) best

In the worst case each new element shifts every element already in the sorted
run: 1 + 2 + ... + (n-1) = n(n-1)/2 shifts, which is O(n^2). On already-sorted
(or nearly sorted) data each element stops almost immediately — only a few
shifts per step — so the best case is O(n). That best case is exactly why the
notes recommend insertion sort for small or nearly sorted data.