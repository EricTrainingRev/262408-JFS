package com.revature.hibernate.query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.hibernate.entity.Customer;
import com.revature.hibernate.entity.Order;
import com.revature.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * QueryingDemo
 * ============
 * Demonstrates the four ways to query the database in Hibernate / JPA
 * (notes section 6): HQL, JPQL, Native SQL, and the Criteria API.
 *
 * The examples all answer similar questions about the SAME Customer/Order
 * domain, so you can directly compare the syntax of each approach.
 *
 * Order of operations:
 *   1. Seed a couple of customers with orders (so queries have data).
 *   2. HQL        -- the object-oriented, Hibernate-proprietary language.
 *   3. JPQL       -- the JPA-standard sibling of HQL.
 *   4. Native SQL -- raw, vendor-specific SQL.
 *   5. Criteria   -- query built programmatically in Java (type-safe).
 *   6. A join HQL to show HQL's relationship-awareness (notes 6.1).
 */
public class QueryingDemo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            seedData(session);

            hql(session);
            jpql(session);
            nativeSql(session);
            criteria(session);
            joinHql(session);
        }

        HibernateUtil.shutdown();
    }

    /** INSERT a customer and two orders so the queries below have data. */
    private static void seedData(Session session) {
        Transaction tx = session.beginTransaction();

        Customer grace = new Customer();
        grace.setFirstName("Grace");
        grace.setLastName("Hopper");
        grace.setAddress("1 Compiler Way");
        grace.setYearsAsCustomer(3);

        Order keyboard = new Order();
        keyboard.setProduct("Keyboard");
        keyboard.setCost(89.99);
        keyboard.setCustomer(grace);
        grace.getOrders().add(keyboard);

        Order monitor = new Order();
        monitor.setProduct("Monitor");
        monitor.setCost(300.00);
        monitor.setCustomer(grace);
        grace.getOrders().add(monitor);

        session.persist(grace);

        tx.commit();
        System.out.println("Seeded 1 customer with 2 orders.\n");
    }

    // ---------------------------------------------------------------------
    // 6.1 HQL  -- Hibernate Query Language, proprietary to Hibernate.
    // Queries OBJECTS and their PROPERTIES, not tables/columns. Entity names
    // are used in place of table names, and you navigate fields like
    // c.lastName rather than SQL columns. Hibernate translates this string
    // into vendor-specific SQL for you.
    // ---------------------------------------------------------------------
    private static void hql(Session session) {
        // Named parameter ":surname" -- HQL understands the object model.
        List<Customer> results = session.createSelectionQuery(
                "from Customer c where c.lastName = :surname", Customer.class)
                .setParameter("surname", "Hopper")
                .getResultList();

        System.out.println("=== HQL (6.1) ===");
        System.out.println("'from Customer c where c.lastName = :surname'");
        System.out.println("Matched " + results.size() + " customer(s).\n");
    }

    // ---------------------------------------------------------------------
    // 6.2 JPQL -- Java Persistence Query Language, the JPA standard.
    // JPQL is deliberately almost identical to HQL: same syntax, same style.
    // The real difference is standardization -- JPQL follows the strict JPA
    // spec so it works on ANY JPA provider (EclipseLink, OpenJPA, ...), whereas
    // HQL can include Hibernate-specific extensions and is tied to Hibernate.
    // Strategically you write JPQL for portability.
    // ---------------------------------------------------------------------
    private static void jpql(Session session) {
        // Uses the standard, portable SELECT ... FROM ... WHERE shape.
        List<Customer> results = session.createSelectionQuery(
                "SELECT c FROM Customer c WHERE c.lastName = :surname", Customer.class)
                .setParameter("surname", "Hopper")
                .getResultList();

        System.out.println("=== JPQL (6.2) ===");
        System.out.println("'SELECT c FROM Customer c WHERE c.lastName = :surname'");
        System.out.println("Matched " + results.size() + " customer(s) -- same result as HQL.\n");
    }

    // ---------------------------------------------------------------------
    // 6.3 Native SQL -- raw SQL, straight to the database.
    // When ORM abstractions are too restrictive, or you need a vendor-specific
    // feature, you hand Hibernate a raw SQL string. You lose portability and
    // object-oriented querying, but gain maximum control. The trade-off is
    // portal / performance for control.
    // Note the positional parameter "?" (index-based) and the real table/
    // column names (customers, last_name).
    // ---------------------------------------------------------------------
    private static void nativeSql(Session session) {
        List<Customer> results = session.createNativeQuery(
                "SELECT * FROM customers WHERE last_name = ?", Customer.class)
                .setParameter(1, "Hopper")
                .getResultList();

        System.out.println("=== Native SQL (6.3) ===");
        System.out.println("'SELECT * FROM customers WHERE last_name = ?'");
        System.out.println("Matched " + results.size() + " customer(s).\n");
    }

    // ---------------------------------------------------------------------
    // 6.4 Criteria API -- query built as Java code, not a string.
    // Instead of writing a query STRING, you construct the query with Java
    // methods: a CriteriaBuilder creates a CriteriaQuery, which you build up
    // from a Root + a where-predicate. Because every piece is a typed Java
    // object, the compiler catches errors (type-safe) -- unlike HQL/JPQL which
    // are only validated at runtime. Great for building dynamic searches where
    // the filters depend on user input.
    // ---------------------------------------------------------------------
    private static void criteria(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        query.select(root).where(cb.equal(root.get("lastName"), "Hopper"));

        List<Customer> results = session.createQuery(query).getResultList();

        System.out.println("=== Criteria API (6.4) ===");
        System.out.println("cb.equal(root.get(\"lastName\"), \"Hopper\") instead of a query string");
        System.out.println("Matched " + results.size() + " customer(s).\n");
    }

    // ---------------------------------------------------------------------
    // Because HQL understands the object model, you can join across relationships
    // by name (c.orders) and filter on the related entity's fields (o.cost).
    // No manual JOIN syntax against tables and foreign keys needed.
    // ---------------------------------------------------------------------
    private static void joinHql(Session session) {
        List<Customer> results = session.createSelectionQuery(
                "from Customer c join c.orders o where o.cost > :minCost", Customer.class)
                .setParameter("minCost", 100.0)
                .getResultList();

        System.out.println("=== HQL join across the relationship (6.1) ===");
        System.out.println("'from Customer c join c.orders o where o.cost > :minCost'");
        System.out.println("Found " + results.size() + " customer(s) with an order over $100.\n");
    }
}
