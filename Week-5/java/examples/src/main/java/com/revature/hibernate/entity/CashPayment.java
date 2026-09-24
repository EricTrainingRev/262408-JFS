package com.revature.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

/**
 * CashPayment -- a concrete subclass in the Single-Table inheritance demo.
 *
 * Like its sibling CreditCardPayment, CashPayment shares the parent's
 * "payments" table. Its own cash_received column is NULL for non-cash rows.
 *
 * Note there is NO @Table annotation here: in Single Table inheritance the
 * table lives on the root class (Payment). Subclasses only contribute their
 * extra columns to that shared table.
 */
@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    @Column(name = "cash_received")
    private double cashReceived;

    /*
     * JPA no-arg constructor.
     */
    public CashPayment() {}

    /*
     * Convenience constructor.
     */
    public CashPayment(UUID id, double amount, double cashReceived) {
        super(id, amount);
        this.cashReceived = cashReceived;
    }

    public double getCashReceived() {
        return cashReceived;
    }

    public void setCashReceived(double cashReceived) {
        this.cashReceived = cashReceived;
    }

    @Override
    public String toString() {
        return "CashPayment{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", cashReceived=" + cashReceived +
                '}';
    }
}
