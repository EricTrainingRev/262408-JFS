# Design Patterns

## Table of Contents

* [1. Overview of Design Patterns](#1-overview-of-design-patterns)
* [2. Creational Patterns](#2-creational-patterns)
    * [2.1 Singleton](#21-singleton)
    * [2.2 Simple Factory](#22-simple-factory)
* [3. Behavioral Patterns](#3-behavioral-patterns)
    * [3.1 Observer Pattern](#31-observer-pattern)
* [4. Architectural Patterns](#4-architectural-patterns)
    * [4.1 Data Access Object (DAO)](#41-data-access-object-dao)

***

## 1. Overview of Design Patterns

**Design Patterns** are reusable solutions to commonly occurring problems in software design. They are not pre-written code, but rather templates or descriptions of how to solve a problem in a way that is flexible, maintainable, and scalable.

### The Purpose of Patterns
* **Standardization:** Provides a common vocabulary for developers. When you say "this is a Singleton," others immediately understand the intent.
* **Best Practices:** They encapsulate proven architectural decisions, helping avoid "reinventing the wheel" poorly.
* **Decoupling:** Many patterns focus on reducing dependencies between components, making systems easier to test and extend.

### Categorization
Design patterns are typically categorized into three main types:
1. **Creational Patterns:** Deal with object creation mechanisms, trying to create objects in a manner suitable to the situation.
2. **Structural Patterns:** Deal with how classes and objects are composed to form larger structures.
3. **Behavioral Patterns:** Deal with communication between objects and how responsibilities are distributed.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we have the high-level context, let's dive into the first category: how we manage the birth of objects.*

## 2. Creational Patterns

Creational patterns abstract the instantiation process. They help make a system independent of how its objects are created, composed, and represented.

### 2.1 Singleton

The **Singleton** pattern ensures that a class has only one instance and provides a global point of access to it.

#### The Goal
To control access to a shared resource (like a database connection pool or a configuration manager) where having multiple instances would cause conflicts or excessive resource usage.

#### Structural Framework
1. **Private Constructor:** Prevents other classes from using the `new` operator with the class.
2. **Private Static Instance:** A variable within the class that holds the single instance of itself.
3. **Public Static Method:** (Often called `getInstance()`) The entry point that checks if an instance exists. If it does, it returns it; if not, it creates it.

#### Implementation (Java)
```java
/**
 * A thread-safe implementation of the Singleton pattern 
 * using the "Initialization-on-demand holder" idiom.
 */
public class DatabaseConnection {

    // Private constructor prevents instantiation from other classes
    private DatabaseConnection() {
        System.out.println("Connecting to Database...");
    }

    // Static inner class responsible for holding the single instance
    private static class Holder {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    /**
     * Provides the global access point to the single instance.
     * @return The unique instance of DatabaseConnection.
     */
    public static DatabaseConnection getInstance() {
        return Holder.INSTANCE;
    }

    public void executeQuery(String query) {
        System.out.println("Executing: " + query);
    }
}

// Usage Example
class Main {
    public static void main(String[] args) {
        // Both variables will point to the exact same instance
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        DatabaseConnection conn2 = DatabaseConnection.getInstance();

        conn1.executeQuery("SELECT * FROM users");
        
        System.out.println("Are both instances the same? " + (conn1 == conn2));
    }
}
```

> [!IMPORTANT]
> **Thread Safety:** The implementation above uses the "Holder" idiom, which leverages the JVM's class-loading mechanism to ensure the instance is created only once and is inherently thread-safe without explicit synchronization overhead.

[↑ Back to Table of Contents](#table-of-contents)
***

*While the Singleton limits us to one instance, sometimes we need a more flexible way to generate many different types of objects.*

### 2.2 Simple Factory

The **Simple Factory** (also called Static Factory) is a pattern where a single method centralizes the logic for creating objects, deciding which concrete class to instantiate based on a parameter. It is not one of the canonical GoF patterns, but it is the foundation that more advanced factory patterns are built on.

#### The Goal
To delegate the responsibility of object instantiation to a specialized method, decoupling the client code from the specific concrete classes being instantiated.

#### Structural Framework
1. **Product Interface:** Defines the common interface for all objects the factory can create.
2. **Concrete Products:** The actual implementations of the Product interface.
3. **Creator (Factory):** The class/method that contains the logic to decide which concrete product to instantiate.

#### Implementation (Java)
```java
/**
 * The Product interface defines the behavior of all concrete products.
 */
interface Notification {
    void notifyUser();
}

/**
 * Concrete Product 1: Email notification.
 */
class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email notification...");
    }
}

/**
 * Concrete Product 2: SMS notification.
 */
class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification...");
    }
}

/**
 * The Factory class responsible for object creation logic.
 */
class NotificationFactory {
    /**
     * Returns a concrete Notification object based on the input type.
     * @param type The type of notification requested.
     * @return A concrete implementation of Notification.
     * @throws IllegalArgumentException if the type is null, blank, or unknown.
     */
    public Notification createNotification(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type must not be null or blank");
        }

        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SMSNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

// Usage Example
class Main {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        // Client doesn't know about EmailNotification or SMSNotification classes
        Notification n1 = factory.createNotification("EMAIL");
        n1.notifyUser();

        Notification n2 = factory.createNotification("SMS");
        n2.notifyUser();
    }
}
```

#### Comparison: Singleton vs. Simple Factory

| Feature | Singleton | Simple Factory |
| :--- | :--- | :--- |
| **Primary Intent** | Ensure **one** instance exists. | Provide a way to create **many** objects. |
| **Object Lifecycle** | Managed internally; lives for the app duration. | Created on demand by the client. |
| **Complexity** | Low (but can introduce global state). | Medium (adds more classes/interfaces). |

[↑ Back to Table of Contents](#table-of-contents)
***

*Moving from how we create objects to how they interact, we encounter patterns that manage communication.*

## 3. Behavioral Patterns

Behavioral patterns are concerned with algorithms and the assignment of responsibilities between objects.

### 3.1 Observer Pattern

The **Observer** pattern defines a one-to-many dependency between objects so that when one object (the **Subject**) changes state, all its dependents (**Observers**) are notified and updated automatically.

#### The Goal
To achieve loose coupling between a data source and the elements that need to react to its changes.

#### Structural Framework
1. **Subject:** Maintains a list of observers and provides methods to attach, detach, and notify them.
2. **Observer Interface:** Defines the `update()` method that the subject calls.
3. **Concrete Observer:** Implements the `update()` method to perform specific actions when notified.

#### Implementation (Java)
```java
import java.util.ArrayList;
import java.util.List;

/**
 * The Observer interface defines the contract for receiving updates.
 */
interface Observer {
    void update(String message);
}

/**
 * The Subject interface for managing observers.
 */
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message);
}

/**
 * Concrete Subject: A News Agency that broadcasts news.
 */
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    /**
     * Simulates a news update.
     */
    public void publishNews(String news) {
        System.out.println("Agency publishing: " + news);
        notifyObservers(news);
    }
}

/**
 * Concrete Observer: A News Channel subscribing to updates.
 */
class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received breaking news: " + message);
    }
}

// Usage Example
class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        NewsChannel bbc = new NewsChannel("BBC");
        NewsChannel cnn = new NewsChannel("CNN");

        agency.attach(bbc);
        agency.attach(cnn);

        agency.publishNews("Design Patterns are awesome!");

        agency.detach(cnn);
        agency.publishNews("Java is great for patterns.");
    }
}
```

#### Real-World Analogy
Think of a **YouTube Channel** (the Subject). When a creator uploads a new video, all the **Subscribers** (the Observers) receive a notification. The subscribers don't constantly check the channel; they simply wait to be told.

[↑ Back to Table of Contents](#table-of-contents)
***

*Finally, we look at patterns that dictate how we organize our data layers to keep our business logic clean.*

## 4. Architectural Patterns

While many patterns focus on individual object interactions, architectural patterns guide the overall structure of the application layers.

### 4.1 Data Access Object (DAO)

The **Data Access Object (DAO)** is a pattern used to isolate the application/business layer from the persistence layer (database).

#### The Goal
To provide a consistent interface for performing CRUD (Create, Read, Update, Delete) operations on a data source, regardless of whether that source is a SQL database, a NoSQL store, or an external API.

#### Structural Framework
1. **Model/Entity:** A simple object representing the data (e.g., a `User` class).
2. **DAO Interface:** Defines the required operations (e.g., `save(user)`, `findById(id)`).
3. **DAO Concrete Implementation:** Contains the actual logic (SQL queries, API calls) to interact with the database.
4. **Client/Service Layer:** Interacts only with the DAO interface, never directly with the database.

#### Implementation (Java)
```java
import java.util.HashMap;
import java.util.Map;

/**
 * The Model/Entity: Represents a User in our system.
 */
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    @Override
    public String toString() { return "User[id=" + id + ", name=" + name + "]"; }
}

/**
 * The DAO Interface: Defines standard CRUD operations.
 */
interface UserDAO {
    void save(User user);
    User findById(int id);
}

/**
 * Concrete DAO: Implements storage using an in-memory Map (simulating a DB).
 */
class InMemoryUserDAO implements UserDAO {
    private Map<Integer, User> database = new HashMap<>();

    @Override
    public void save(User user) {
        database.put(user.getId(), user);
        System.out.println("Saved to DB: " + user);
    }

    @Override
    public User findById(int id) {
        return database.get(id);
    }
}

// Usage Example
class Main {
    public static void main(String[] args) {
        // The Client/Service layer only knows about the UserDAO interface
        UserDAO userDAO = new InMemoryUserDAO();

        User user1 = new User(1, "Alice");
        userDAO.save(user1);

        User fetched = userDAO.findById(1);
        System.out.println("Fetched from DB: " + fetched);
    }
}
```

#### Why use DAO?
* **Separation of Concerns:** The business logic doesn't need to know about SQL syntax or database connection strings.
* **Testability:** You can easily swap a "Real Database DAO" with a "Mock DAO" (in-memory list) during unit testing.
* **Flexibility:** If you migrate from MySQL to MongoDB, you only need to write a new DAO implementation; your business logic remains untouched.

> [!TIP]
> **DAO vs. Repository Pattern:** While similar, a DAO is often thought of as a wrapper around a specific database table, whereas a **Repository** is often more "domain-centric," potentially aggregating multiple DAOs to provide a complete view of an aggregate root.

[↑ Back to Table of Contents](#table-of-contents)
