package com.revature.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.revature.hibernate.entity.Customer;
import com.revature.hibernate.entity.Order;
import com.revature.hibernate.entity.Payment;
import com.revature.hibernate.entity.CreditCardPayment;
import com.revature.hibernate.entity.CashPayment;

/**
 * HibernateUtil
 * =============
 * A small helper class whose only job is to build and hold the application's
 * single Sessionfactory.
 *
 * Why do we need this? SessionFactory is expensive to create
 * (it parses mappings, validates the config, connects to the DB, etc.),
 * so we should build it exactly once and reuse it for the whole lifetime of the
 * application. This class implements the singleton pattern around it.
 *
 * Configuration source: this class reads hibernate.properties
 * from the classpath. Hibernate automatically loads a file with that exact name
 * when a Configuration is created. Notice we do not call configure() because
 * doing so would load hibernate.cfg.xml instead. We instead keep the properties file
 * as our single source of truth (see the comment block in hibernate.cfg.xml)
 */
public class HibernateUtil {

    /*
     * The one-and-only SessionFactory for this application.
     * volatile + a double-checked-lock pattern below keeps it thread-safe.
     */
    private static volatile SessionFactory sessionFactory;

    /**
     * Private constructor -- this is a utility class and must never be
     * instantiated. All access goes through the static methods.
     */
    private HibernateUtil() {
    }

    /**
     * Returns the application-wide SessionFactory, building it lazily on
     * first use if it hasn't been created yet.
     *
     * Lazy + thread-safe: we only pay the (expensive) build cost once, and
     * concurrent callers can't trigger two builds.
     *
     */
    public static SessionFactory getSessionFactory() {
        // First (cheap) check -- avoids the synchronized block once the factory exists.
        if (sessionFactory == null) {
            // Synchronize so only one thread can enter the build section at a time.
            synchronized (HibernateUtil.class) {
                // Second check -- another thread may have built it while we waited.
                if (sessionFactory == null) {
                    sessionFactory = buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    /**
     * Builds the SessionFactory from the classpath config.
     *
     * Configuration is read from hibernate.properties (auto-loaded by Hibernate).
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Because we build from hibernate.properties WITHOUT calling configure(),
            // Hibernate does NOT auto-scan the classpath for @Entity classes. We must
            // register every entity explicitly so it knows about them.
            return new Configuration()
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(CreditCardPayment.class)
                    .addAnnotatedClass(CashPayment.class)
                    .buildSessionFactory();
            // xml version
//             return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Wrap the raw error so the caller gets a clear, actionable message.
            throw new ExceptionInInitializerError(
                    "Failed to create the SessionFactory. Check hibernate.properties. " + ex);
        }
    }

    /**
     * Closes the shared SessionFactory, releasing the database connection
     * and any other resources it holds. Safe to call multiple times.
     *
     * With "hibernate.hbm2ddl.auto=create-drop", closing the factory is
     * also what triggers the schema teardown (tables dropped) -- a good reason to
     * always shut it down cleanly.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }
}
