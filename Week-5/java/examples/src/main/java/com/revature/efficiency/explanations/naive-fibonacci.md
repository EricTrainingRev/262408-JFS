# Naive Fibonacci, walked through in ASCII (O(2^n))

Companion to `ExponentialTime.java`. The naive recursive Fibonacci computes fib(n) by asking for fib(n-1) and fib(n-2). That looks harmless, but each of those asks for two more, and so on — the call tree doubles at every level.

The tree for fib(5), with the duplicate subproblems deliberately left in:

```
                          fib(5)
                        /        \
                    fib(4)         fib(3)
                  /       \       /      \
             fib(3)      fib(2)  fib(2)  fib(1)
            /     \      /   \   /   \
       fib(2)   fib(1) fib(1) fib(0) fib(1) fib(0)
       /    \
   fib(1)  fib(0)
```

Look at how often fib(2) and fib(0) reappear — the same tiny subproblem is solved over and over because the code never remembers an answer. The tree holds about 2^n nodes even though the actual result stays small: fib(20) is only 6,765, yet the naive code makes 21,891 calls to compute it.

Why O(2^n): every call branches into two, so each added n roughly doubles the work. Caching subproblem answers (memoization) collapses this tree from ~2^n calls down to n.