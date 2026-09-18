package com.revature.examples.sealed;

/**
 * sealed: still restricted, and it names its own permits. A sealed class is
 * free to be concrete, as this one is.
 */
public sealed class Rectangle extends Shape permits Square {

    protected final double width;
    protected final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void declareShape() {
        System.out.println("Rectangle (" + width + " x " + height + ")");
    }
}

