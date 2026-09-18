package com.revature.examples.sealed;

/**
 * A sealed hierarchy. The permits clause names the complete set of direct
 * subclasses, and the compiler enforces it: nothing else in this package may
 * extend Shape.
 *
 * Each permitted subclass must pick one of three options:
 *
 *   Circle      final       -- the hierarchy stops here
 *   Rectangle   sealed      -- carries the restriction onward, permitting Square
 *   Triangle    non-sealed  -- lifts the restriction; anyone may extend it again
 *
 * Shape itself stays abstract, so the only way to get a Shape is one of these.
 */
public abstract sealed class Shape permits Circle, Rectangle, Triangle {

    /** Every shape knows how to announce itself. */
    public abstract void declareShape();
}
