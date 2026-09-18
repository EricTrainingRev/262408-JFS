package com.revature.examples.sealed;

import java.util.List;

/**
 * Driving the sealed Shape hierarchy with switch pattern matching (Java 21+).
 *
 * Because Shape is sealed, the compiler knows the four cases below cover the
 * whole hierarchy, so no default branch is needed. Add a fifth permitted
 * subclass and this switch stops compiling until it is handled -- that is the
 * payoff of sealing.
 */
public class ShapesExample {

    public static void main(String[] args) {
        var shapes = List.of(
                new Circle(2.0),
                new Rectangle(3.0, 4.0),
                new Square(5.0),
                new Triangle(6.0, 7.0));

        System.out.println("-- switch pattern matching --");
        for(Shape shape: shapes){
            shape.declareShape();
            System.out.println(describe(shape));
        }

        Shape supriseShape = new Circle(5.0);
        if(supriseShape instanceof Circle c){
            c.saySomethingUnique();
        }
    }

    /**
     * Square has to be tested before Rectangle: cases are tried in order, and a
     * Square is a Rectangle, so the reverse order would swallow every square.
     * Get that wrong and the compiler refuses the dominated label outright.
     */
    static String describe(Shape shape) {
        return switch (shape) {
            case Square s -> "a square";
            case Rectangle r -> "a rectangle that is not a square";
            case Circle c -> "a circle";
            case Triangle t -> "a triangle";
        };
    }
}
