package com.revature.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

/**
 * Payment entity -- abstract base of the inheritance demo.
 *
 * This class is the ROOT of a small inheritance hierarchy that exists purely
 * to demonstrate Hibernate's inheritance-mapping strategy (see notes, section
 * 5.1). Relational databases have no native concept of "inheritance": there is
 * no way to store a superclass/subclass relationship directly. Hibernate
 * therefore has to CHOOSE how to represent a Java class hierarchy as tables.
 *
 * ===========================================================================
 * THE STRATEGY USED HERE: SINGLE TABLE
 * ===========================================================================
 * @Inheritance(strategy = InheritanceType.SINGLE_TABLE) tells Hibernate to
 * store every class in the hierarchy in ONE single table. Here that table is
 * "payments" (from @Table below on this root class).
 *
 * Because all subclasses share one table, every column from every subclass
 * must exist in that table. A @DiscriminatorColumn holds a marker value that
 * tells Hibernate which class a given row actually belongs to:
 *
 *   - rows for CreditCardPayment store "CC"   in the payment_type column
 *   - rows for CashPayment        store "CASH" in the payment_type column
 *
 * This is the default strategy (you get it even without writing
 * @Inheritance), and it is the simplest:
 *   Pro: fast queries -- no joins needed, all data is in one table.
 *   Con: many columns are NULL for rows that don't belong to the subclass
 *        that owns them. E.g. a CashPayment row has no card number, so its
 *        card_number column is NULL.
 *
 * ===========================================================================
 * HOW TO SWITCH TO THE OTHER TWO STRATEGIES
 * ===========================================================================
 * 1) JOINED TABLE   -- change the annotation below to
 *        @Inheritance(strategy = InheritanceType.JOINED)
 *    Each class gets its OWN table, and subclass tables link to the parent
 *    table via a foreign key. Highly normalized/clean, but fetching an object
 *    requires a JOIN across those tables (slower).
 *
 * 2) TABLE PER CLASS -- change the annotation below to
 *        @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 *    Every concrete class gets its own table containing ALL of its fields,
 *    including inherited ones. Simple for per-class queries, but the parent
 *    class has no table at all, which can make polymorphic queries (querying
 *    the whole hierarchy at once) difficult or impossible.
 *
 * Try commenting out the strategy on this line and swapping in one of the two
 * alternatives above, then watch the generated DDL / table structure change.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "payments")
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Column(name = "amount")
    private double amount;

    /*
     * JPA REQUIRES a public/protected no-arg constructor, even on an abstract
     * entity -- Hibernate uses it (often via reflection) when loading rows.
     */
    public Payment() {}

    /*
     * Convenience constructor for building a populated Payment in code.
     */
    public Payment(UUID id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /*
     * equals/hashCode/toString use ONLY scalar fields -- never the id-less
     * subclass-specific collections (there are none here anyway). Keeping these
     * to simple fields avoids any recursion or inconsistent-hash pitfalls.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.getAmount(), getAmount()) == 0
                && Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", amount=" + amount + '}';
    }
}
