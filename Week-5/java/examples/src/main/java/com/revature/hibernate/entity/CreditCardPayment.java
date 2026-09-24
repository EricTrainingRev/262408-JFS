package com.revature.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

/**
 * CreditCardPayment -- a concrete subclass in the Single-Table inheritance demo.
 *
 * Persistent fields of a subclass map to columns in the SAME table as the
 * parent ("payments"). Here the inherited id/amount columns are shared with
 * CashPayment; the card_number column below exists only for credit-card rows
 * and is NULL for cash-payment rows.
 *
 * @DiscriminatorValue("CC") is the marker value stored in the parent's
 * discriminator column (payment_type) so Hibernate can tell which class a row
 * belongs to. It must be unique across all subclasses of the hierarchy.
 */
@Entity
@DiscriminatorValue("CC")
public class CreditCardPayment extends Payment {

    @Column(name = "card_number")
    private String cardNumber;

    /*
     * JPA no-arg constructor.
     */
    public CreditCardPayment() {}

    /*
     * Convenience constructor. The super(id, amount) call populates the
     * inherited fields on the parent class.
     */
    public CreditCardPayment(UUID id, double amount, String cardNumber) {
        super(id, amount);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
