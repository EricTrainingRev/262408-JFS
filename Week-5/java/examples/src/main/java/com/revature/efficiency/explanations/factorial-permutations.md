# Counting permutations, walked through in ASCII (O(n!))

Companion to `FactorialTime.java`.

The example counts every way to arrange n distinct values. The recursion fills
the arrangement slot by slot; at each slot there is one fewer unused value to
choose from.

The branching for n = 3 (slot 0 has 3 choices, slot 1 has 2, slot 2 has 1):

```
                 start
              /    |    \
             1     2     3         slot 0: 3 choices
            / \   / \   / \
           2   3 1   3 1   2       slot 1: 2 choices
           |   | |   | |   |
           3   2 3   1 2   1       slot 2: 1 choice
         123 132 213 231 312 321
```

Each leaf is one complete arrangement: 123, 132, 213, 231, 312, 321 — exactly
3! = 6. The branching multiplies at every level: 3 x 2 x 1.

Why O(n!): the complete tree has n! leaves — n choices at the first slot, n-1
at the second, and so on. Adding one element multiplies the total work by n,
which grows far faster than even the exponential 2^n.