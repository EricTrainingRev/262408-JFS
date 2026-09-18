package com.revature.examples.sealed;

/** final: this branch of the hierarchy ends here. */
public final class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void declareShape() {
        System.out.println("Circle (radius " + radius + ")");
    }
}
