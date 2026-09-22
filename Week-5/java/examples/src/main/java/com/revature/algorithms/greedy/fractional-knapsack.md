# Fractional knapsack, walked through in ASCII

Companion to `FractionalKnapsack.java`.

We have three items, each with a value and a weight, and a knapsack of capacity 50. Unlike the 0/1 knapsack, we may take a *fraction* of an item. The goal is to maximize the total value carried.

Items and capacity:

```
capacity 50

item   value   weight
  A      60      10
  B     100      20
  C     120      30
```

The greedy rule: always take the item with the best value-to-weight ratio first.

## Step 1 — compute each item's ratio

```
ratio = value / weight

item   value   weight   ratio
  A      60      10     60 / 10  = 6.0
  B     100      20    100 / 20  = 5.0
  C     120      30    120 / 30  = 4.0
```

## Step 2 — sort by ratio, highest first

```
A (6.0)   B (5.0)   C (4.0)
```

Now fill the knapsack in that order.

## Step 3 — take A whole

```
best ratio now:  A (6.0)

A.weight 10  <=  capacity 50   -> take the WHOLE item

knapsack so far:  [ A:10 ]
remaining: 40          value: 60
```

## Step 4 — take B whole

```
best ratio now:  B (5.0)

B.weight 20  <=  capacity 40   -> take the WHOLE item

knapsack so far:  [ A:10 ][ B:20 ]
remaining: 20          value: 160
```

## Step 5 — take part of C

```
best ratio now:  C (4.0)

Capacity left is 20: the knapsack held 50, and A (10) + B (20) already used 30.
Item C weighs 30, which is more than the 20 that remain, so only a fraction of
C can fit.

   amount of C taken    = capacity left / C.weight
                        = 20 / 30
                        = 2/3 of item C

        Value gained    = fraction taken x C.value
                        = (20 / 30) x 120
                        = 80
                        (same answer as C.ratio x capacity left = 4.0 x 20 = 80)

Final load: A (full) + B (full) + 2/3 of C
Remaining capacity: 0         Value: 60 + 100 + 80 = 240
```

## The fill, at a glance

```
step   item   amount taken   value added   total value
  1      A      10 of 10          60            60
  2      B      20 of 20         100           160
  3      C      20 of 30          80           240
```

## Result

```
maximum value = 240.0      (matches the program's output)
```

## Why greedy is optimal here (but not for 0/1 knapsack)

Picking the best value-to-weight ratio first is optimal for the *fractional* version because you can always top up the last gap with a fraction — no capacity is wasted, so a greedy choice never forces you to leave better value behind. The sort is O(n log n) and the filling pass is O(n). For the *0/1* knapsack (whole items only) this rule can be wrong: a high-ratio item can crowd out a better combination, which is why that problem needs dynamic programming instead.