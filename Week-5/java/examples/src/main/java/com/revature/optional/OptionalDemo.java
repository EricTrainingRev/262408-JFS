package com.revature.optional;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Optional in action over a HashMap of shop stock. Without Optional, a missing
 * key comes back as null and every lookup needs a null check. Optional wraps
 * the value so the "is it there?" question and the "use it" answer become
 * method calls instead of if statements.
 *
 * Note the distinction the data makes: "shield" is a key that exists with a
 * stock of 0, while "sword" is a key that does not exist at all. Map.get
 * returns 0 for the first and null for the second — Optional treats the
 * present-but-zero case as a value and only the missing case as empty.
 */
public class OptionalDemo {

    private static final Map<String, Integer> INVENTORY = new HashMap<>(Map.of(
        "potion", 5,
        "elixir", 2,
        "shield", 0));

    public static void main(String[] args) {
        showNullFromMap();
        showDefaultValues();
        showActOnPresence();
        showTransformAndFilter();
        showResolveRequired();
    }

    /**
     * The reason Optional exists: Map.get returns null for a missing key.
     */
    private static void showNullFromMap() {
        System.out.println("-- Map.get returns null for a missing key --");
        Integer shield = INVENTORY.get("shield");
        Integer sword = INVENTORY.get("sword");
        System.out.println("shield (in stock, 0 left): " + shield);
        System.out.println("sword (never stocked):     " + sword);
    }

    /**
     * orElse supplies a fallback only when the Optional is empty, so an absent
     * key and a present-but-zero key can be told apart.
     */
    private static void showDefaultValues() {
        System.out.println("-- orElse supplies a fallback when the key is missing --");
        int shieldStock = Optional.ofNullable(INVENTORY.get("shield")).orElse(10);
        int swordStock = Optional.ofNullable(INVENTORY.get("sword")).orElse(0);
        System.out.println("shield stock (present, keeps 0): " + shieldStock);
        System.out.println("sword stock (absent, becomes 0): " + swordStock);
    }

    /**
     * ifPresent runs only when a value is present, folding "is it null?" and
     * "then do something" into one call.
     */
    private static void showActOnPresence() {
        System.out.println("-- ifPresent acts only when a value exists --");
        Optional.ofNullable(INVENTORY.get("potion")).ifPresent(stock -> System.out.println("potions in stock: " + stock));
        Optional.ofNullable(INVENTORY.get("sword")).ifPresent(System.out::println);
    }

    /**
     * filter and map on an Optional replace the nested null checks you would
     * otherwise write by hand, and leave the Optional empty if the value does
     * not measure up.
     */
    private static void showTransformAndFilter() {
        System.out.println("-- filtering and mapping an Optional --");
        Optional<String> message = Optional.ofNullable(INVENTORY.get("potion"))
            .filter(stock -> stock > 0)
            .map(stock -> "You can buy " + stock + " potions");
        System.out.println("potion message: " + message.orElse("Nothing to buy"));

        Optional<String> swordMessage = Optional.ofNullable(INVENTORY.get("sword"))
                .filter(stock -> stock > 0)
                .map(stock -> "You can buy " + stock + " potions");
        System.out.println("sword message: " + swordMessage.orElse("Nothing to buy"));
    }

    /**
     * orElseThrow turns a missing value into an error, when absence is not an
     * acceptable outcome.
     */
    private static void showResolveRequired() {
        System.out.println("-- orElseThrow for a value that must exist --");
        int elixirStock = Optional.ofNullable(INVENTORY.get("elixir"))
            .orElseThrow(() -> new IllegalStateException("Required stock missing"));
        System.out.println("elixir stock: " + elixirStock);

        int swordStock = Optional.ofNullable(INVENTORY.get("sword"))
                .orElseThrow(() -> new IllegalStateException("Required stock missing"));
        System.out.println("sword stock: " + elixirStock);
    }
}