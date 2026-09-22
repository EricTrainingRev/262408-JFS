package com.revature.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A single showcase class for the java.util.stream API, using a small set of
 * purchase orders as the data. Each method highlights one or two stream
 * operations on the same shared list.
 */
public class StreamsDemo {

    private static final List<PurchaseOrder> ORDER_LIST = List.of(
        new PurchaseOrder(1, "Alice", 120.00, OrderStatus.PENDING),
        new PurchaseOrder(2, "Bob", 45.50, OrderStatus.SHIPPED),
        new PurchaseOrder(3, "Alice", 89.00, OrderStatus.SHIPPED),
        new PurchaseOrder(4, "Charlie", 300.00, OrderStatus.PENDING),
        new PurchaseOrder(5, "Bob", 22.00, OrderStatus.CANCELLED),
        new PurchaseOrder(6, "Diana", 150.00, OrderStatus.SHIPPED));

    public static void main(String[] args) {
        showFiltering();
        showTopOrders();
        showCustomerNames();
        showDistinctCustomers();
        showTotalRevenue();
        showShortCircuits();
    }

    /**
     * filter keeps only the elements that pass a Predicate; forEach consumes
     * the survivors.
     */
    private static void showFiltering() {
        System.out.println("-- PENDING orders only --");
        ORDER_LIST.stream()
            .filter(order -> order.status() == OrderStatus.PENDING)
            .forEach(order -> System.out.println(order));
    }

    /**
     * sorted orders the stream, here by amount descending; limit cuts it to
     * the first few after ordering.
     */
    private static void showTopOrders() {
        System.out.println("-- top 3 orders by amount --");
        ORDER_LIST.stream()
            .sorted(Comparator.comparingDouble(PurchaseOrder::amount).reversed())
            .limit(3)
            .forEach(order -> System.out.println(order));
    }

    /**
     * map transforms each element into something else; toList collects the
     * transformed stream back into a list.
     */
    private static void showCustomerNames() {
        System.out.println("-- every customer who has placed an order --");
        List<String> customers = ORDER_LIST.stream()
            .map(PurchaseOrder::customer)
            .toList();
        System.out.println(customers);
    }

    /**
     * distinct drops duplicates; count is a terminal operation that tallies
     * the elements that reached it.
     */
    private static void showDistinctCustomers() {
        System.out.println("-- how many distinct customers --");
        long distinctCount = ORDER_LIST.stream()
            .map(PurchaseOrder::customer)
            .distinct()
            .count();
        System.out.println(distinctCount);
    }

    /**
     * mapToDouble turns each element into a double, letting sum add them all
     * up without a loop.
     */
    private static void showTotalRevenue() {
        System.out.println("-- total revenue across all orders --");
        double total = ORDER_LIST.stream()
            .mapToDouble(PurchaseOrder::amount)
            .sum();
        System.out.println(total);
    }

    /**
     * Short-circuit operations stop as soon as the answer is known: anyMatch
     * returns true at the first match, findFirst returns the first element
     * that passes a filter.
     */
    private static void showShortCircuits() {
        System.out.println("-- short-circuit operations --");
        boolean hasPendingOrder = ORDER_LIST.stream()
            .anyMatch(order -> order.status() == OrderStatus.PENDING);
        System.out.println("any pending order? " + hasPendingOrder);

        Optional<PurchaseOrder> firstLargeOrder = ORDER_LIST.stream()
            .filter(order -> order.amount() > 200)
            .findFirst();
        System.out.println("first order over 200: " + firstLargeOrder.orElse(null));
    }
}
