package com.revature.hibernate.inheritance;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.hibernate.entity.CashPayment;
import com.revature.hibernate.entity.CreditCardPayment;
import com.revature.hibernate.entity.Payment;
import com.revature.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * InheritanceDemo
 * ===============
 * Demonstrates how Hibernate maps a Java inheritance hierarchy to tables
 * This project uses the SINGLE TABLE strategy, the
 * default and simplest option.
 *
 * Recall the problem: relational databases have no concept of inheritance.
 * So when we have an abstract Payment base class with two concrete subclasses
 * (CreditCardPayment and CashPayment), Hibernate has to pick a way to lay
 * those Java classes out as tables. With Single Table inheritance, ALL of
 * them share ONE table named "payments", and a discriminator column
 * (payment_type) records which class each row belongs to.
 *
 * This demo:
 *   1. Persists one CreditCardPayment and one CashPayment into the SAME table.
 *   2. Queries them back polymorphically (through the base type), showing that
 *      both subclass rows are returned from a single "from Payment" query.
 *   3. Uses type(p) to filter the hierarchy by concrete class.
 *
 * To explore the other two strategies (Joined Table / Table Per Class), edit
 * the @Inheritance annotation on Payment.java as described in its comments and
 * re-run -- watch how the generated tables change.
 */
public class InheritanceDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Save one of each concrete subclass. Both are stored in the single
            // "payments" table, distinguished by the payment_type discriminator
            // column ("CC" vs "CASH").
            CreditCardPayment card = new CreditCardPayment(null, 89.99, "4111-1111-1111-1111");
            CashPayment cash = new CashPayment(null, 20.00, 20.00);

            session.persist(card);
            session.persist(cash);

            tx.commit();
            System.out.println("Saved a CreditCardPayment and a CashPayment into the shared 'payments' table.\n");

            // POLYMORPHIC QUERY.
            // Querying the BASE type returns rows of EVERY subclass. Because
            // single-table stores everything in one place, this is a single,
            // fast, join-free query -- the "Pro" side from the notes.
            List<Payment> all = session.createSelectionQuery("from Payment", Payment.class)
                    .getResultList();
            System.out.println("from Payment => " + all.size() + " payments:");
            all.forEach(p -> System.out.println("   " + p));

            // FILTER BY SUBTYPE.
            // The type(p) operator returns the concrete Java class of each row,
            // letting us ask for only one subclass. Equivalent to
            // "from CreditCardPayment", but written against the base type.
            List<Payment> onlyCards = session.createSelectionQuery(
                    "from Payment p where type(p) = CreditCardPayment", Payment.class)
                    .getResultList();
            System.out.println("\ntype(p) = CreditCardPayment => " + onlyCards.size() + " card payment(s)");
        }

        HibernateUtil.shutdown();
    }
}
