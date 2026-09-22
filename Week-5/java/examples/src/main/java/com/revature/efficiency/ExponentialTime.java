package com.revature.efficiency;

/**
 * O(2^n) — exponential time. Each increment of n roughly doubles the work.
 *
 * Naive recursive Fibonacci is the classic example. To compute fib(n), the code
 * asks for fib(n-1) and fib(n-2); each of those asks for two more, and so on,
 * so the number of calls grows like 2^n. Notice the answer itself stays tiny
 * (fib(20) is only 6,765) — the work explodes even though the result does not.
 *
 * That is precisely why memoization (caching subproblem answers) exists: with
 * caching, this same computation collapses from ~2^n calls down to n.
 *
 * See naive-fibonacci.md for a visual walkthrough.
 */
public class ExponentialTime {

    public static void main(String[] args) {
        System.out.println("-- O(2^n): naive recursive Fibonacci --");
        showFibCost(10);
        showFibCost(15);
        showFibCost(20);
        // Observe: adding 5 to n multiplies the call count by about 11. That is
        // close to the 2^n rule of thumb (32 for +5), but a little smaller
        // because the recursion keeps re-visiting the same subproblems — the
        // wasted work that memoization would eliminate.
    }

    private static void showFibCost(int n) {
        long[] calls = {0};
        long result = fibonacci(n, calls);
        System.out.println("n=" + n + " -> result=" + result + ", recursive calls=" + calls[0]);
    }

    /**
     * The textbook Fibonacci definition, uncached. Every call bumps the counter
     * and, unless it is a base case, fans out into two child calls. A one-cell
     * array again serves as the mutable counter.
     */
    private static long fibonacci(int n, long[] calls) {
        calls[0]++;
        if (n <= 1) {
            return n;                          // fib(0) = 0, fib(1) = 1
        }
        return fibonacci(n - 1, calls) + fibonacci(n - 2, calls);
    }
}