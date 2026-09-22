package com.revature.lambdas.custom;

/**
 * Shows that a lambda is just a function value typed by the interface it is
 * assigned to. Each lambda below implements ScoreEffect in a single line.
 */
public class Game {

    public static void main(String[] args) {
        // A lambda lets us define the behavior right where we use it, instead
        // of writing a whole class per rule. Without lambdas, each effect
        // would need its own class implementing ScoreEffect (or an anonymous
        // inner class at every call site). Here each rule is a single
        // expression bound to a named variable we can hand around.
        ScoreEffect doubled = baseScore -> baseScore * 2;
        ScoreEffect flatBonus = baseScore -> baseScore + 50;
        ScoreEffect capped = baseScore -> Math.min(baseScore, 999);

        System.out.println("-- a lambda is a named function value --");
        System.out.println("doubled(100) = " + doubled.apply(100));
        System.out.println("flatBonus(100) = " + flatBonus.apply(100));
        System.out.println("capped(1500) = " + capped.apply(1500));
    }
}