package com.revature.hibernate.state;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.hibernate.util.HibernateUtil;
import com.revature.hibernate.entity.Customer;
import com.revature.hibernate.entity.Order;

/**
 * StatefulSessionDemo
 * ===================
 * Demonstrates the "stateful" Session -- the classic, default way to
 * work with Hibernate.
 *
 * WHY USE A STATEFUL SESSION?
 * ---------------------------
 * A stateful Session maintains a persistence context: a first-level
 * cache of the entities you've touched, plus automatic change tracking. This
 * gives you three big benefits for normal application code:
 *
 *   1. Dirty checking -- modify a loaded object and Hibernate figures out the
 *      UPDATE for you on commit. No manual update() call needed.
 *   2. First-level cache / identity map -- loading the same id twice in one
 *      session returns the SAME Java object, so you never get two copies of
 *      one row fighting each other.
 *   3. Lazy loading -- related entities (like a Customer's orders) are fetched
 *      on demand, not eagerly.
 *
 * This is the right choice for typical interactive CRUD: a web request that
 * loads a record, lets the user edit it, and saves the changes.
 */
public class StatefulSessionDemo {

    public static void main(String[] args) {
        // A Session is AutoCloseable, so try-with-resources handles closing it.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // ---- 1. SAVE: persist a Customer and two Orders -------------------
            Transaction tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setFirstName("Ada");
            customer.setLastName("Lovelace");
            customer.setAddress("12 Analytical Engine Lane");
            customer.setYearsAsCustomer(5);

            Order order1 = new Order();
            order1.setProduct("Laptop");
            order1.setCost(1200.00);
            order1.setCustomer(customer);   // owning side: sets the FK

            Order order2 = new Order();
            order2.setProduct("Monitor");
            order2.setCost(300.00);
            order2.setCustomer(customer);

            // Keep the inverse side in sync too (good practice, though not required
            // for the FK to be written -- that's the owning side's job).
//            customer.getOrders().add(order1);
//            customer.getOrders().add(order2);

            // persist() makes the objects managed. Note: even though Customer.orders
            // has cascade, the orders are NOT in the customer's orders collection
            // here (those add() lines above are commented out), so cascade never fires.
            // That's why we still persist the orders explicitly.
            session.persist(customer);
            session.persist(order1);
            session.persist(order2);
            tx.commit();

            System.out.println("Saved customer with id=" + customer.getId());

            // ---- 2. LOAD + DIRTY CHECKING -------------------------------------
            // Open a fresh session so we start with an empty persistence context.
            try (Session session2 = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx2 = session2.beginTransaction();

                // Load the customer back from the DB by id.
                // getReference() returns a lazy proxy -- the row is only fetched
                // from the DB when a field is actually accessed.
                Customer loaded = session2.getReference(Customer.class, customer.getId());
                System.out.println("Loaded: " + loaded);
                System.out.println("customer orders: ");
                loaded.getOrders().forEach(System.out::println);

                // Modify a field. We do NOT call update() -- the stateful session
                // detects the change (dirty checking) and issues the UPDATE on commit.
                loaded.setYearsAsCustomer(6);

                tx2.commit();
                System.out.println("Committed -- Hibernate auto-generated the UPDATE for the changed field.");
            }
        }

        // create-drop: closing the SessionFactory triggers the schema teardown.
        HibernateUtil.shutdown();
    }
}
