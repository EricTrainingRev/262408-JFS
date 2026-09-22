package com.revature.streams;
/**
 * A single purchase order. Record accessors (id, customer, amount, status)
 */
public record PurchaseOrder(int id, String customer, double amount, OrderStatus status) {
}
