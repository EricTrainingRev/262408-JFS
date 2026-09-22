package com.revature.lambdas.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambdas filling the standard function-type slots (Predicate, Comparator,
 * Consumer, UnaryOperator) against a real collection.
 *
 * A Predicate is a function that tests a value and returns a boolean, so it
 * is the natural fit for deciding what to keep or drop.
 * 
 * A Comparator is a two-argument function that returns a negative, zero, or
 * positive number, which is exactly what an ordering algorithm needs.
 * 
 * A Consumer is a function that takes a value, returns nothing, and is used
 * for side effects such as printing.
 * 
 * A UnaryOperator is a function that takes one value and returns another of
 * the same type, which is how a whole collection can be mapped in place.
 */
public class Game {

    public static void main(String[] args) {
        showLambdas();
        showMethodReferences();
    }

    /**
     * The four function-type slots filled by lambdas written inline.
     */
    private static void showLambdas() {
        List<Player> roster = new ArrayList<>(List.of(
            new Player("Billy", 10, 500),
            new Player("Sally", 3, 60),
            new Player("Slagathor", 0, 0),
            new Player("Timmy", 7, 320)));

        // Predicate<Player>: the boolean test removeIf uses to decide which
        // elements stay. Anything the lambda returns false for is dropped in
        // place, so after this call the roster holds only survivors.
        roster.removeIf(player -> player.level() == 0);
        System.out.println("-- survivors, no special ordering --");
        System.out.println(roster);
//        We would need to write a lot more code to accomplish the same thing without the removeIf and lambda:
//        for(int i = 0; i < roster.size(); i++){
//            if(roster.get(1).isDead()){
//                roster.remove(i);
//            }
//        }

        // Comparator<Player>: the two-argument ordering function sort uses.
        // Returning a negative, zero, or positive number tells List how to lay
        // the players out, here gold descending, without us owning the loop.
        roster.sort((first, second) -> Integer.compare(second.gold(), first.gold()));

        // Consumer<Player>: a void function that does something with each
        // element. forEach feeds it every entry in list order, which is how we
        // print the survivors without writing an explicit loop.
        System.out.println("-- survivors, ranked by gold --");
        roster.forEach(player -> System.out.println("Survivor: " + player));

        // UnaryOperator<Player>: a function that takes a Player and returns a
        // Player. replaceAll swaps every element for the result, leveling the
        // whole roster up. Records are immutable, so the lambda builds a fresh
        // Player for each survivor.
        roster.replaceAll(player -> new Player(player.name(), player.level() + 1, player.gold()));

        System.out.println("-- after the level up --");
        roster.forEach(player -> System.out.println("Ranked: " + player));
    }

    /**
     * The same pipeline, this time with method references. A method reference
     * is shorthand for a lambda that just delegates to one existing method, so
     * it only fits when the behavior already lives as a named method.
     * Player::isDead reads as "player -> player.isDead()".
     */
    private static void showMethodReferences() {
        List<Player> referenceRoster = new ArrayList<>(List.of(
            new Player("Billy", 10, 500),
            new Player("Sally", 3, 60),
            new Player("Slagathor", 0, 0),
            new Player("Timmy", 7, 320)));

        System.out.println("-- the same work, as method references --");

        // Predicate as the unbound form Type::instanceMethod. Just like the unary
        // case, isDead() takes no argument, so the element passed to
        // test(element) becomes the receiver: element -> element.isDead().
        referenceRoster.removeIf(Player::isDead);

        // Comparator: no single pre-existing method matches, so it stays a
        // lambda rather than forcing an awkward method reference.
        referenceRoster.sort((first, second) -> Integer.compare(second.gold(), first.gold()));

        // Consumer as the bound form object::method. System.out is fixed as part of
        // the reference, so the element is NOT the receiver here — it becomes
        // the argument: element -> System.out.println(element). The flip side
        // of the unbound forms above, same signature-matching principle.
        System.out.println("-- survivors, ranked by gold --");
        referenceRoster.forEach(System.out::println);

        // UnaryOperator as an unbound form. This breaks the "element is
        // passed in" pattern from the lambdas above: levelUp() takes no
        // argument, so the mapped element becomes the receiver the method is
        // called ON, not a parameter handed to it.
        System.out.println("-- after the level up --");
        referenceRoster.replaceAll(Player::levelUp);
        referenceRoster.forEach(System.out::println);
    }
}