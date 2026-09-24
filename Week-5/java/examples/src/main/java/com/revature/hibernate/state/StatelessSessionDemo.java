package com.revature.hibernate.state;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import com.revature.hibernate.util.HibernateUtil;
import com.revature.hibernate.entity.Customer;

import java.util.UUID;

/**
 * StatelessSessionDemo
 * ====================
 * Demonstrates the StatelessSession: a stripped-down, high-throughput
 * alternative to the Session.
 *
 * WHY USE A STATELESS SESSION?
 * ----------------------------
 * A StatelessSession has NO persistence context. That means:
 *
 *   - No first-level cache -- every operation goes straight to the database.
 *   - No dirty checking -- if you modify a loaded object, Hibernate will NOT
 *     auto-detect it. You must call update() explicitly.
 *   - No lazy loading and no cascading -- you manage related entities yourself.
 *   - No identity map -- loading the same id twice returns two different objects.
 *
 * That sounds like a downgrade, but it's a deliberate trade-off. Because it
 * skips all the bookkeeping, a StatelessSession is much faster and uses far
 * less memory. It's the right tool for:
 *
 *   - Bulk operations (inserting/updating thousands of rows in a loop).
 *   - Batch jobs / ETL where you don't need change tracking.
 *   - Read-only reporting queries.
 *
 * Rule of thumb: use a stateful Session for interactive CRUD where you want
 * change tracking and caching; use a StatelessSession for bulk/batch work
 * where raw throughput matters more than convenience.
 */
public class StatelessSessionDemo {

    public static void main(String[] args) {
        // A StatelessSession is also AutoCloseable.
        try (StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession()) {

            // ---- 1. BULK INSERT ------------------------------------------------
            // Insert 1000 customers in a loop. Because there is no persistence
            // context, each insert() is written straight to the DB -- no cache to
            // fill up, no change tracking overhead. This is the classic use case.
            Transaction tx = session.beginTransaction();

            UUID firstId = null;
            for (int i = 0; i < 1000; i++) {
                Customer c = new Customer();
                c.setFirstName("Bulk" + i);
                c.setLastName("Customer");
                c.setAddress("Address " + i);
                c.setYearsAsCustomer(i % 10);
                session.insert(c);   // explicit insert -- no cascade, no cache
                if (i == 0) {
                    firstId = c.getId();   // capture the generated UUID of the first row
                }
            }

            tx.commit();
            System.out.println("Inserted 1000 customers via StatelessSession.");

            // ---- 2. NO DIRTY CHECKING -----------------------------------------
            // Load one customer, change a field, and commit. Unlike the stateful
            // session, NOTHING is written back -- the change is silently lost
            // because there is no persistence context tracking it.
            Transaction tx2 = session.beginTransaction();

            // get() actually fetches the row from the DB (returns null if absent).
            Customer loaded = session.get(Customer.class, firstId);
            System.out.println("Loaded: " + loaded);

            loaded.setYearsAsCustomer(999);   // this change will NOT be persisted
            tx2.commit();
            System.out.println("Committed -- but the change was NOT saved (no dirty checking).");
            Customer notUpdatedCustomer = session.get(Customer.class,firstId);
            System.out.println(notUpdatedCustomer);

            // To actually persist a change with a StatelessSession you must call
            // update() explicitly:
            //   session.update(loaded);

        }

        HibernateUtil.shutdown();
    }
}
