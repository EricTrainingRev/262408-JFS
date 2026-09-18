package com.revature.examples.sealed;

/**
 * non-sealed: Triangle gives the restriction back. It is still a permitted
 * subclass of Shape, but nothing stops a new class extending Triangle later.
 *
 * Note the hyphen in the modifier itself -- it is spelled non-sealed.
 */
public non-sealed class Triangle extends Shape {

    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void declareShape() {
        System.out.println("Triangle (base " + base + ", height " + height + ")");
    }
}
