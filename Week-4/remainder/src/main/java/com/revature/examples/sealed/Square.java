package com.revature.examples.sealed;

/** final: the last branch of the Rectangle arm of the hierarchy. */
public final class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    @Override
    public void declareShape() {
        System.out.println("Square (side " + width + ")");
    }
}

