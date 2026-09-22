# Bubble sort, walked through in ASCII

Companion to `QuadraticTime.java`, and the O(n^2) partner to `merge-sort.md` —
we sort the same 5-element array `[38, 27, 43, 3, 9]` so the two algorithms can be compared side by side.

The rule of bubble sort: walk the array left to right, and whenever two neighbors are out of order, swap them. One full pass "bubbles" the largest not-yet-sorted value to the far end, so each pass can stop one element earlier.

```
starting array:  [38, 27, 43, 3, 9]
```

---

## Pass 1 - 43 bubbles to the right end

```
compare(38, 27)  out of order -> swap -> [27, 38, 43, 3, 9]
compare(38, 43)  in order             -> [27, 38, 43, 3, 9]
compare(43, 3)   out of order -> swap -> [27, 38, 3, 43, 9]
compare(43, 9)   out of order -> swap -> [27, 38, 3, 9, 43]
end of pass: [27, 38, 3, 9, |43|]         (43 is now final)
```

## Pass 2 - 38 bubbles to its place

```
compare(27, 38)  in order             -> [27, 38, 3, 9, 43]
compare(38, 3)   out of order -> swap -> [27, 3, 38, 9, 43]
compare(38, 9)   out of order -> swap -> [27, 3, 9, 38, 43]
end of pass: [27, 3, 9, |38, 43|]         (38 is now final)
```

## Pass 3 - 27 bubbles to its place

```
compare(27, 3)   out of order -> swap -> [3, 27, 9, 38, 43]
compare(27, 9)   out of order -> swap -> [3, 9, 27, 38, 43]
end of pass: [3, 9, |27, 38, 43|]         (27 is now final)
```

## Pass 4 - the last adjacent check

```
compare(3, 9)    in order             -> [3, 9, 27, 38, 43]
end of pass: [3, |9, 27, 38, 43|]        (9 final; 3 final by elimination)
```

Sorted: `[3, 9, 27, 38, 43]`.

---

## The whole run at a glance

```
pass 1 -> [27, 38, 3, 9, |43|]
pass 2 -> [27, 3, 9, |38, 43|]
pass 3 -> [3, 9, |27, 38, 43|]
pass 4 -> [3, |9, 27, 38, 43|]
sorted -> [|3, 9, 27, 38, 43|]
```

---

## Why this is O(n^2)

Pass 1 compares 4 neighbors, pass 2 compares 3, then 2, then 1 — the sums of (n-1) + (n-2) + ... + 1 = n(n-1)/2 comparisons, which is O(n^2). Doubling the input quadruples the work (4x), where merge sort's n log n only roughly doubles (2x). That is the whole reason a quadratic sort becomes unusable long before a linearithmic one does.