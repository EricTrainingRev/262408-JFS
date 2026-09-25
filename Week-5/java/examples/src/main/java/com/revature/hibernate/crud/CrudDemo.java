package com.revature.hibernate.crud;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.hibernate.util.HibernateUtil;
import com.revature.hibernate.entity.Customer;
import com.revature.hibernate.entity.Order;

import java.util.List;
import java.util.UUID;

/**
 * CrudDemo
 * ========
 * Demonstrates the four basic CRUD operations -- Create, Read, Update, Delete --
 * using a stateful {@link Session}. A stateful session is the normal choice for
 * this kind of interactive work because it maintains a persistence context: it
 * tracks the entities you touch and automatically propagates your changes to the
 * database, so you don't manage object state.
 *
 * Each operation lives in its own method. main() runs create first so the read,
 * update, and delete methods have a row to work with.
 */
public class CrudDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Create first, so the later operations have data to work with.
            UUID customerId = createCustomer(session);

//            readCustomer(session, customerId);
//            updateCustomer(session, customerId);
            deleteCustomer(session, customerId);
        }

        // create-drop: closing the SessionFactory triggers the schema teardown.
        HibernateUtil.shutdown();
    }

    /**
     * CREATE: persist a Customer and one Order, and return the generated id.
     */
    private static UUID createCustomer(Session session) {
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setFirstName("Grace");
        customer.setLastName("Hopper");
        customer.setAddress("1 Compiler Way");
        customer.setYearsAsCustomer(3);

        // persist() adds the object to the persistence context and schedules an
        // INSERT. The generated UUID id is assigned to the object right away.
        session.persist(customer);

        // Add an order too, to show the relationship in action.
        Order order = new Order();
        order.setProduct("Keyboard");
        order.setCost(89.99);
        order.setCustomer(customer);          // owning side: sets the FK
        customer.getOrders().add(order);      // keep inverse side in sync
        session.persist(order);

        tx.commit();
        System.out.println("CREATE: saved customer id=" + customer.getId()
                + " with order id=" + order.getId());

        return customer.getId();
    }

    /**
     * READ: fetch a single row by id, and run a selection query over all rows.
     */
    private static void readCustomer(Session session, UUID customerId) {
        // find() fetches the entity with the given primary key. The returned
        // object is associated with the persistence context, so changes to it
        // are tracked.
        Customer loaded = session.find(Customer.class, customerId);
        System.out.println("READ:   " + loaded);

        // A selection query retrieves data. HQL uses entity names, not table
        // names, so "from Customer" selects every Customer row.
        List<Customer> all = session.createSelectionQuery("from Customer", Customer.class)
                .getResultList();
        System.out.println("READ:   found " + all.size() + " customer(s) in total");
    }

    /**
     * UPDATE: modify a managed row and let dirty checking write the change.
     */
    private static void updateCustomer(Session session, UUID customerId) {
        Transaction tx = session.beginTransaction();

        // Modify a field on the managed object. We do NOT call update() -- the
        // stateful session's dirty checking detects the change and issues the
        // UPDATE automatically when we commit.
        Customer loaded = session.find(Customer.class, customerId);
        loaded.setYearsAsCustomer(4);
        loaded.setAddress("2 Debug Drive");

        tx.commit();
        System.out.println("UPDATE: changed yearsAsCustomer and address (auto-UPDATE on commit)");
    }

    /**
     * DELETE: remove the customer and, thanks to cascade, its orders too.
     */
    private static void deleteCustomer(Session session, UUID customerId) {
        Transaction tx = session.beginTransaction();

        Customer loaded = session.find(Customer.class, customerId);

        // Because Customer.orders is mapped with cascade = CascadeType.ALL,
        // removing the customer also removes its orders automatically.
        // Hibernate deletes the orders first and then the customer, so the FK
        // constraint on orders.customer_id is never violated. No manual order
        // cleanup is needed.
        session.remove(loaded);

        tx.commit();
        System.out.println("DELETE: removed the customer and its orders (via cascade)");

        // Verify the delete took effect.
        Customer gone = session.find(Customer.class, customerId);
        System.out.println("DELETE: customer now " + (gone == null ? "gone (null)" : "still present"));
    }
}
