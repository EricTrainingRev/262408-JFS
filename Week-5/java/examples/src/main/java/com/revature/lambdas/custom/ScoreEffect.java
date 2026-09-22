package com.revature.lambdas.custom;

/**
 * A functional interface: it has exactly one abstract method, so a lambda can
 * stand in anywhere a ScoreEffect is expected. The annotation is optional but
 * tells the compiler to check that the interface stays single-method.
 */
@FunctionalInterface
public interface ScoreEffect {

    int apply(int baseScore);
}