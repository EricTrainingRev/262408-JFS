package com.revature.hibernate.query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.hibernate.entity.Customer;
import com.revature.hibernate.entity.Order;
import com.revature.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * DynamicCriteriaDemo
 * ====================
 * The real reason to reach for the Criteria API (notes section 6.4) is BUILDING
 * QUERIES AT RUNTIME based on which filters a user happens to pick. Think of a
 * search screen with optional boxes: type a last name, tick a minimum number of
 * years, type a minimum order cost -- any combination. A string-based HQL/JPQL
 * query would force you to concatenate text and pray, and the query text can't
 * be validated by the compiler.
 *
 * The Criteria API solves that: every piece is a typed Java object, so you build
 * a list of Predicates from whatever filters are populated and pass them all to
 * cb.and(...). Unused filters are simply never added. The compiler catches
 * errors, and there is no brittle string building.
 *
 * This demo:
 *   1. Seeds a handful of customers with orders.
 *   2. Implements searchCustomers(...) -- a dynamic search whose WHERE clause
 *      grows and shrinks based on a CustomerSearch that carries optional filters.
 *   3. Runs the same search with DIFFERENT filter combinations so you can watch
 *      the query adapt to each one.
 *   4. Also shows a JOIN across the orders relationship (Customer 1--* Order),
 *      ordering, and a COUNT query -- all with the same typed, builder style.
 */
public class DynamicCriteriaDemo {

    // ---------------------------------------------------------------------
    // A tiny value object describing an optional search. Every field may be
    // null / unset to mean "no filter on this column." This is exactly what a
    // search form would hand you: whatever the user left blank is null.
    // ---------------------------------------------------------------------
    private static class CustomerSearch {
        final String lastNamePrefix;   // null => no last-name filter
        final int minYears;            // 0   => no minimum-years filter
        final Double minOrderCost;     // null => no order-cost filter

        CustomerSearch(String lastNamePrefix, int minYears, Double minOrderCost) {
            this.lastNamePrefix = lastNamePrefix;
            this.minYears = minYears;
            this.minOrderCost = minOrderCost;
        }

        // Print the search as a readable label so the console output shows
        // exactly what filters each run used.
        @Override
        public String toString() {
            return "{lastNamePrefix=" + lastNamePrefix
                    + ", minYears=" + minYears
                    + ", minOrderCost=" + minOrderCost + '}';
        }
    }

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            seedData(session);

            // Run the same dynamic search with different filter combinations.
            // The point: we call ONE method, and it builds a different WHERE
            // clause each time based on whichever filters are populated.
            run(session, new CustomerSearch(null, 0, null));                    // no filters at all
            run(session, new CustomerSearch("L", 0, null));                     // last name starts with "L"
            run(session, new CustomerSearch(null, 3, null));                    // at least 3 years
            run(session, new CustomerSearch("H", 2, null));                     // combine two customer filters
            run(session, new CustomerSearch(null, 0, 300.0));                   // joined filter: an order over $300

            // Bonus: a count query (how many rows match) in the same typed style.
            countAll(session);
        }

        HibernateUtil.shutdown();
    }

    /** Run one search and print how many customers matched. */
    private static void run(Session session, CustomerSearch search) {
        List<Customer> results = searchCustomers(session, search);
        System.out.println("search " + search + " => " + results.size() + " customer(s)");
        results.forEach(c -> System.out.println("   " + c.getFirstName() + " " + c.getLastName()));
        System.out.println();
    }

    /**
     * The heart of the demo: a dynamic query built from optional filters.
     *
     * Pattern to notice: we collect Predicate objects into a List, add only the
     * ones whose filter is populated, then AND them all together. If the list is
     * empty we skip where() entirely. No string concatenation, no null checks
     * sprinkled through the query string -- just a typed list of conditions.
     */
    private static List<Customer> searchCustomers(Session session, CustomerSearch search) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);

        // Accumulate the active predicates here.
        List<Predicate> predicates = new ArrayList<>();

        // Optional filter 1: last name STARTS WITH a prefix, case-insensitive.
        // cb.like with a "%" wildcard is the Criteria equivalent of a
        // SQL "LIKE 'L%'". Combined with cb.lower + toLowerCase it ignores case.
        if (search.lastNamePrefix != null && !search.lastNamePrefix.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("lastName")),
                    search.lastNamePrefix.toLowerCase() + "%"));
        }

        // Optional filter 2: at least N years as a customer.
        // A used value of 0 means "no minimum", so we only add it when > 0.
        if (search.minYears > 0) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("yearsAsCustomer"), search.minYears));
        }

        // Optional filter 3: only customers who have at least one order costing
        // MORE than a given amount. This crosses the relationship, so we need a
        // JOIN from Customer to its orders. Root.join("orders") navigates the
        // one-to-many by the field name on the entity.
        // Kept as a field (not local) so we can call distinct() on it below.
        Join<Customer, Order> ordersJoin = null;
        if (search.minOrderCost != null) {
            ordersJoin = root.join("orders");   // SQL INNER JOIN orders
            predicates.add(cb.greaterThan(ordersJoin.get("cost"), search.minOrderCost));
        }

        // Apply all accumulated conditions with a single cb.and(...). Passing
        // them as an array turns them into "cond1 AND cond2 AND cond3".
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        // potential gotcha: joining orders can yield the SAME customer twice (once per
        // matching order). distinct(true) collapses those duplicates.
        if (ordersJoin != null) {
            cq.distinct(true);
        }

        // Predictable output: order the results by last name.
        cq.orderBy(cb.asc(root.get("lastName")));

        return session.createQuery(cq).getResultList();
    }

    /** Show a COUNT query -- returning a Long instead of an entity. */
    private static void countAll(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Customer> root = cq.from(Customer.class);

        // Instead of selecting rows, select the count. The result type is Long.
        cq.select(cb.count(root));

        Long total = session.createQuery(cq).getSingleResult();
        System.out.println("count from Customer => " + total);
    }

    /** Seed a few customers with orders so the searches have data. */
    private static void seedData(Session session) {
        Transaction tx = session.beginTransaction();

        customer(session, "Grace", "Hopper", 3,
                order("Keyboard", 89.99),
                order("Monitor", 300.00));
        customer(session, "Ada", "Lovelace", 8,
                order("Laptop", 1200.00));
        customer(session, "Alan", "Turing", 5,
                order("Tape", 12.50));
        customer(session, "Donald", "Knuth", 4,
                order("Textbook", 75.00));

        tx.commit();
        System.out.println("Seeded 4 customers with orders.\n");
    }

    /** Helper that builds a customer with its orders and persists everything. */
    private static Customer customer(Session session, String first, String last,
                                     int years, Order... orders) {
        Customer c = new Customer();
        c.setFirstName(first);
        c.setLastName(last);
        c.setAddress("Placeholder address");
        c.setYearsAsCustomer(years);
        for (Order o : orders) {
            o.setCustomer(c);                  // owning side: sets the FK
            c.getOrders().add(o);              // inverse side: keep in sync
        }
        // Persist the customer once -- because Customer.orders has
        // cascade = CascadeType.ALL, the orders in its collection are saved
        // automatically. Persisting the orders first would fail: each order's
        // customer FK would reference a customer that isn't saved yet.
        session.persist(c);
        return c;
    }

    /** Helper to build an Order (id auto-generated); customer assigned later. */
    private static Order order(String product, double cost) {
        Order o = new Order();
        o.setProduct(product);
        o.setCost(cost);
        return o;
    }
}