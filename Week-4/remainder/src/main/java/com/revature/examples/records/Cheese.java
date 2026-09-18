package com.revature.examples.records;

/**
 * A simple record. This one line gives you a private final field per component,
 * a constructor, an accessor per component (name(), never getName()), plus
 * equals, hashCode and toString -- all generated, none of it written out here.
 *
 * Records are shallowly immutable and fully transparent: every component is
 * public, so this shape suits data you don't mind exposing. Mutable state, or
 * something that needs hiding, belongs in an ordinary class instead.
 */
public record Cheese(String name, String milk, int ageInMonths) {
}
