# Java Collections

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Collection API](#2-collection-api)
* [3. List Interface](#3-list-interface)
* [4. Set Interface](#4-set-interface)
* [5. Queues & Queue Interface](#5-queues--queue-interface)
* [6. Maps & Map Interface](#6-maps--map-interface)
* [7. ArrayList and LinkedList](#7-arraylist-and-linkedlist)
* [8. HashSet and TreeSet](#8-hashset-and-treeset)
* [9. ArrayDeque and PriorityQueue](#9-arraydeque-and-priorityqueue)
* [10. Iterators](#10-iterators)
* [11. Comparable Interface](#11-comparable-interface)
* [12. Comparator Interface](#12-comparator-interface)
* [13. Generics](#13-generics)
* [14. Records, Sealed Classes and Pattern Matching](#14-records-sealed-classes-and-pattern-matching)

***

## 1. High-Level Overview

In the Java programming language, managing groups of objects efficiently is a fundamental requirement. Instead of manually managing arrays—which have fixed sizes and limited functionality—Java provides a robust **Collections Framework**. This framework is a unified architecture for representing and manipulating collections, offering high-performance implementations of common data structures.

This module explores the **Java Collections Framework (JCF)**, starting with the foundational **Collection API** and moving through specialized interfaces and implementations like **Lists**, **Sets**, **Queues**, and **Maps**.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we have established the high-level context, let's dive into the core architecture by exploring the **Collection API**.*

## 2. Collection API

The **Collection API** is the heart of the Java Collections Framework. It defines a set of core interfaces that describe how data is stored, accessed, and manipulated, regardless of the underlying data structure.

### 2.1 The Hierarchy of the Collection Interface

At the top of the hierarchy (excluding Maps) sits the `java.util.Collection` interface. It defines the fundamental operations that every collection must support.

| Interface | Primary Purpose | Key Characteristics |
| :--- | :--- | :--- |
| **`Collection`** | The root interface for most data structures. | Provides basic operations like `add()`, `remove()`, `size()`, and `isEmpty()`. |
| **`List`** | Represents an ordered collection (a sequence). | Allows **duplicate elements** and **positional access** via an index. |
| **`Set`** | Represents a collection of unique elements. | Does **not allow duplicates**. Models mathematical sets. |
| **`Queue`** | Designed for holding elements prior to processing. | Typically follows a **First-In-First-Out (FIFO)** order. |

### 2.2 Core Operations

Regardless of the specific implementation, the `Collection` interface provides a consistent set of methods to interact with your data:

*   **Adding Elements:** `add(E e)`, `addAll(Collection<? extends E> c)`
*   **Removing Elements:** `remove(Object o)`, `removeAll(Collection<?> c)`, `clear()`
*   **Querying State:** `size()`, `isEmpty()`, `contains(Object o)`, `containsAll(Collection<?> c)`
*   **Bulk Operations:** `retainAll(Collection<?> c)` (Intersection), `addAll` (Union)

> [!IMPORTANT]
> **The Map Distinction:** It is crucial to note that the `java.util.Map` interface **does not** extend the `Collection` interface. While Maps are part of the Collections Framework, they operate on **Key-Value pairs** rather than single elements, meaning they follow a different structural logic.

### 2.3 Complexity Overview (Big O)

When choosing a collection, understanding the time complexity of common operations is vital for performance.

| Operation | List (ArrayList) | List (LinkedList) | Set (HashSet) | Queue (ArrayDeque) |
| :--- | :--- | :--- | :--- | :--- |
| **`add()`** | $O(1)$ (amortized) | $O(1)$ | $O(1)$ | $O(1)$ |
| **`remove(index)`** | $O(n)$ | $O(n)$ | N/A | N/A |
| **`get(index)`** | $O(1)$ | $O(n)$ | N/A | N/A |
| **`contains()`** | $O(n)$ | $O(n)$ | $O(1)$ | $O(n)$ |
| **Space Complexity** | $O(n)$ | $O(n)$ | $O(n)$ | $O(n)$ |

[↑ Back to Table of Contents](#table-of-contents)
***

*Having established the hierarchy and complexity of the base API, let's move into the first major sub-category: ordered sequences defined by the **List Interface**.*

## 3. List Interface

The **`List` interface** extends the `Collection` interface and represents an **ordered collection** of elements, often referred to as a **sequence**. Unlike a `Set`, a `List` allows for **duplicate elements** and provides precise control over where in the sequence an element is inserted.

### 3.1 Key Characteristics

*   **Positional Access:** Elements can be accessed, inserted, or removed based on their integer **index** (starting from 0).
*   **Ordering:** The collection maintains the initial **insertion order** of elements.
*   **Duplicates:** The list can contain multiple occurrences of the same object.

### 3.2 Common Methods

Beyond the standard `Collection` methods, the `List` interface provides specialized functionality for index-based manipulation:

| Method | Description |
| :--- | :--- |
| `get(int index)` | Returns the element at the specified position. |
| `set(int index, E element)` | Replaces the element at the specified position with the specified element. |
| `add(int index, E element)` | Inserts the specified element at the specified position. |
| `remove(int index)` | Removes the element at the specified position. |
| `indexOf(Object o)` | Returns the index of the first occurrence of the specified element. |
| `lastIndexOf(Object o)` | Returns the index of the last occurrence of the specified element. |
| `subList(int fromIndex, int toIndex)` | Returns a view of the portion of this list between the specified indices. |

### 3.3 List vs. Set

| Feature | **List** | **Set** |
| :--- | :--- | :--- |
| **Duplicates** | Allowed | **Not Allowed** |
| **Ordering** | Maintains insertion order | Usually unordered (except `SortedSet`) |
| **Access Method** | By **Index** (positional) | By **Element** (contains/iterator) |

> [!TIP]
> Use a **List** when the order of elements matters or when you need to access elements by their position. Use a **Set** when the uniqueness of elements is the primary requirement.

[↑ Back to Table of Contents](#table-of-contents)
***

*While Lists handle ordered duplicates, we must now explore the opposite paradigm: the unordered, unique world of **Sets**.*

## 4. Set Interface

The **`Set` interface** represents a collection that cannot contain duplicate elements. It models the mathematical set abstraction and is used when the primary goal is to ensure **uniqueness** within a group of objects.

### 4.1 Key Characteristics

*   **Uniqueness:** A `Set` will not allow two elements `e1` and `e2` such that `e1.equals(e2)`.
*   **No Positional Access:** Unlike a `List`, you cannot ask a `Set` for the "3rd element." You must iterate through the set or check for existence.
*   **Implementation Variance:** Because `Set` is an interface, its behavior (ordering, speed) depends heavily on the implementation (e.g., `HashSet` vs. `TreeSet`).

### 4.2 Core Implementation Models

| Implementation | Ordering | Performance (Avg) | Use Case |
| :--- | :--- | :--- | :--- |
| **`HashSet`** | **Unordered** | $O(1)$ | General purpose, fastest. |
| **`LinkedHashSet`** | **Insertion Order** | $O(1)$ | When you need uniqueness *and* to remember insertion order. |
| **`TreeSet`** | **Sorted Order** | $O(\log n)$ | When you need elements to be automatically sorted. |

### 4.3 Set vs. List

| Feature | **Set** | **List** |
| :--- | :--- | :--- |
| **Duplicates** | **Forbidden** | Allowed |
| **Access** | Via Iterator / `contains()` | Via Index / `get(i)` |

> [!WARNING]
> When using a `Set`, you **must** ensure that the objects you store have correctly implemented `equals()` and `hashCode()`. If these methods are inconsistent, the `Set` may fail to detect duplicates, breaking its fundamental contract.

[↑ Back to Table of Contents](#table-of-contents)
***

*With uniqueness handled by Sets, we move to the concept of processing elements in a specific order, which is the domain of the **Queues interface**.*

## 5. Queues & Queue Interface

The **`Queue` interface** is designed for holding elements prior to processing. Typically, queues follow the **First-In-First-Out (FIFO)** principle, where the first element added is the first one removed.

### 5.1 Key Characteristics

*   **Ordering:** Queue implementations may process elements using FIFO or priority-based ordering. LIFO behavior is generally provided through the Deque interface.
*   **Processing Model:** Designed for scenarios like task scheduling, message buffers, or breadth-first searches.

### 5.2 Core Operations

The `Queue` interface provides two forms for most operations: one that throws an exception if the operation fails, and one that returns a special value (`null` or `false`).

| Operation | Throws Exception | Returns Special Value | Description |
| :--- | :--- | :--- | :--- |
| **Insert** | `add(e)` | `offer(e)` | Inserts an element according to the ordering rules |
| **Remove** | `remove()` | `poll()` | Removes and returns the head of the queue. |
| **Examine** | `element()` | `peek()` | Returns the head of the queue without removing it. |

> [!IMPORTANT]
> **`poll()` vs `remove()`:** Use `poll()` when you want to handle an empty queue gracefully (it returns `null`), whereas `remove()` will throw a `NoSuchElementException`.

[↑ Back to Table of Contents](#table-of-contents)
***

*Having covered the behavior of queues, let's transition to the unique key-value structure of the **Map interface**.*

## 6. Maps & Map Interface

The **`Map` interface** represents an object that maps **keys to values**. A map cannot contain duplicate keys, and each key can map to at most one value. While not a true `Collection`, it is a core part of the Collections Framework.

### 6.1 Key Characteristics

*   **Key-Value Pairs:** Data is stored as entries (`Map.Entry<K, V>`).
*   **Unique Keys:** Every key must be unique. If you insert a value with an existing key, the old value is overwritten.
*   **Search Efficiency:** Maps are optimized for very fast retrieval of values based on their associated keys.

### 6.2 Core Operations

| Method | Description |
| :--- | :--- |
| `put(K key, V value)` | Associates the specified value with the specified key. |
| `get(Object key)` | Returns the value to which the specified key is mapped. |
| `remove(Object key)` | Removes the mapping for a key from this map if present. |
| `containsKey(Object key)` | Returns `true` if this map contains a mapping for the specified key. |
| `containsValue(Object value)` | Returns `true` if this map maps one or more keys to the specified value. |
| `keySet()` | Returns a `Set` view of the keys contained in this map. |
| `values()` | Returns a `Collection` view of the values contained in this map. |
| `entrySet()` | Returns a `Set` view of the mappings (`Map.Entry`) contained in this map. |

### 6.3 Map vs. Collection

| Feature | **Map** | **Collection** |
| :--- | :--- | :--- |
| **Data Unit** | **Key-Value Pair** | **Single Element** |
| **Duplicates** | Keys must be unique; Values can duplicate | Elements can duplicate (except in Sets) |
| **Access** | Via **Key** | Via **Iterator / Index** |

> [!TIP]
> When iterating over a Map, it is almost always more efficient to iterate over its `entrySet()` rather than `keySet()` if you need both the key and the value.

### 6.4 Common Map Implementations

The `Map` interface provides several implementations, each optimized for a different balance of ordering and performance. Choosing the right implementation depends on whether you need maximum lookup speed, predictable iteration order, or automatic key sorting. In most applications, `HashMap` is the default choice due to its excellent average-case performance, while `LinkedHashMap` is useful when insertion order must be preserved, and `TreeMap` is preferred when keys need to remain sorted automatically.

| Implementation | Ordering | Performance |
|---------------|----------|-------------|
| HashMap | Unordered | O(1) average |
| LinkedHashMap | Insertion Order | O(1) average |
| TreeMap | Sorted Order | O(log n) |

[↑ Back to Table of Contents](#table-of-contents)
***

*With the high-level interfaces established, we will now examine the specific, widely-used implementations of these interfaces, starting with the List implementations: **ArrayList and LinkedList**.*

## 7. ArrayList and LinkedList

While both `ArrayList` and `LinkedList` implement the `List` interface, they differ fundamentally in how they store and access data in memory, leading to different performance characteristics.

### 7.1 ArrayList

An **`ArrayList`** is backed by a dynamic array. Unlike a traditional array, its size can grow automatically as elements are added. When the internal array reaches capacity, a larger array is created and the existing elements are copied into it.

**Pros:**

* **Fast Random Access:** Provides **O(1)** time complexity for retrieving elements by index.
* **Efficient Iteration:** Elements are stored contiguously in memory, resulting in excellent cache locality and fast traversal.
* **Memory Efficient:** Requires less memory overhead than linked structures because it does not store additional node references.
* **Fast End Insertions:** Adding elements to the end of the list is typically **O(1)** amortized.

**Cons:**

* **Slow Insertions/Deletions in the Middle or Beginning:** Operations such as `add(index, element)` and `remove(index)` typically require shifting elements, resulting in **O(n)** performance.
* **Resizing Overhead:** When the internal array grows, all existing elements must be copied to a new array. Although infrequent, this resize operation has a cost of **O(n)**.
* **Fixed Capacity Internally:** While the list appears dynamic, resizing requires allocating a new array and copying existing data.

> [!TIP]
> **ArrayList is the default List implementation for most applications.** Its combination of fast random access, low memory overhead, and strong real-world performance makes it preferable to `LinkedList` in the majority of use cases.

> [!NOTE]
> **Understanding O(1) Amortized Performance**
>
> Adding an element to the end of an `ArrayList` is often described as **O(1) amortized** rather than simply **O(1)**.
>
> Most calls to `add(E e)` take constant time because the element is placed directly into the next available position in the internal array. However, when the array reaches capacity, the `ArrayList` must:
>
> 1. Allocate a larger internal array.
> 2. Copy all existing elements into the new array.
> 3. Add the new element.
>
> This resize operation takes **O(n)** time, where *n* is the number of elements in the list. Fortunately, resizing occurs infrequently. When averaged across many insertions, the occasional expensive resize is spread over numerous cheap insertions, resulting in an **amortized cost of O(1) per insertion**.
>
> **Example:**
>
> If 100,000 elements are added to an `ArrayList`, only a small number of those insertions trigger a resize. The vast majority are simple constant-time operations, so the overall average cost per insertion remains **O(1)**.

### 7.2 LinkedList

A **`LinkedList`** is composed of nodes, where each node contains the data and pointers to the previous and next nodes.

*   **Pros:**
    *   **Fast Insertions/Deletions:** $O(1)$ once the position is found (no shifting required).
    *   **No Resizing:** Grows organically with each new element.
*   **Cons:**
    *   **Slow Random Access:** $O(n)$ because you must traverse from the head or tail to the desired index.
    *   **High Memory Overhead:** Each element requires extra memory for the pointers.

### 7.3 Comparison: ArrayList vs. LinkedList

| Feature | **ArrayList** | **LinkedList** |
| :--- | :--- | :--- |
| **Access (Index)** | $O(1)$ | $O(n)$ |
| **Add/Remove (End)** | $O(1)$ (amortized) | $O(1)$ |
| **Add/Remove (Start/Middle)**| $O(n)$ | $O(1)$ (once node is found) |
| **Memory Usage** | Low | High (due to pointers) |

> [!TIP]
> **Rule of Thumb:** In most modern applications, `ArrayList` is the default choice due to better cache locality and faster random access. Only use `LinkedList` if you have a specific requirement for frequent insertions/deletions at the beginning or middle of the list.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we have covered the List implementations, let's look at the Set implementations: **HashSet and TreeSet**.*

## 8. HashSet and TreeSet

The choice of `Set` implementation depends on whether you care about the order of elements and the speed of operations.

### 8.1 HashSet

A **`HashSet`** is the most common `Set` implementation. It uses a hash table for storage.

*   **Ordering:** Does **not** guarantee any specific order of elements.
*   **Performance:** Provides constant-time $O(1)$ performance for basic operations (`add`, `remove`, `contains`).
*   **Nulls:** Allows one `null` element.

### 8.2 TreeSet

A **`TreeSet`** is backed by a Red-Black tree (a self-balancing binary search tree).

*   **Ordering:** Elements are stored in their **natural sorted order** (or a custom order provided via a `Comparator`).
*   **Performance:** Provides logarithmic-time $O(\log n)$ performance for basic operations.
*   **Nulls:** Does **not** allow `null` elements (as it needs to compare elements for sorting).

### 8.3 Comparison: HashSet vs. TreeSet

| Feature | **HashSet** | **TreeSet** |
| :--- | :--- | :--- |
| **Ordering** | Unordered | **Sorted** |
| **Time Complexity** | $O(1)$ | $O(\log n)$ |
| **Null Support** | Yes | No |

[↑ Back to Table of Contents](#table-of-contents)
***

*Moving from Sets to Queues, we will examine the performance and behavior of **ArrayDeque and PriorityQueue**.*

## 9. ArrayDeque and PriorityQueue

The `Queue` and `Deque` (Double-Ended Queue) interfaces provide specialized ways to manage element processing.

### 9.1 ArrayDeque

An **`ArrayDeque`** (Array Double-Ended Queue) implements the `Deque` interface using a resizable circular array. It can be used as both a **Queue** (FIFO) and a **Stack** (LIFO).

*   **Pros:**
    *   **Faster than Stack/LinkedList:** Faster than `Stack` class when used as a stack and faster than `LinkedList` when used as a queue.
    *   **No Nulls:** Does **not** allow `null` elements.
*   **Cons:**
    *   **Not Thread-Safe:** Not suitable for concurrent access without external synchronization.

### 9.2 PriorityQueue

A **`PriorityQueue`** is a specialized queue where elements are ordered based on their **priority** rather than their insertion order.

*   **Ordering:** Elements are ordered according to their natural ordering or a provided `Comparator`.
*   **Head of Queue:** The head is the **least** element (smallest value) according to the specified ordering.
*   **Performance:** $O(\log n)$ for `add` and `poll`, $O(1)$ for `peek`.

### 9.3 Comparison: ArrayDeque vs. PriorityQueue

| Feature | **ArrayDeque** | **PriorityQueue** |
| :--- | :--- | :--- |
| **Ordering Logic** | FIFO / LIFO | **Priority-based** |
| **Complexity (Add)** | $O(1)$ | $O(\log n)$ |
| **Use Case** | Standard buffers/stacks | Task scheduling by urgency |

[↑ Back to Table of Contents](#table-of-contents)
***

*With the data structures covered, we must now learn how to navigate these collections efficiently using **Iterators**, and how to define custom ordering.*

## 10. Iterators

To traverse a collection without knowing its underlying implementation, Java uses the **`Iterator`** interface. This provides a uniform way to access elements one by one.

### 10.1 The Iterator Pattern

The `Iterator` acts as a cursor that moves through a collection.

**Core Methods:**
*   `hasNext()`: Returns `true` if there are more elements to visit.
*   `next()`: Returns the next element in the iteration.
*   `remove()`: Removes the last element returned by the iterator from the underlying collection.

### 10.2 Code Example

```java
List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
Iterator<String> it = names.iterator();

while (it.hasNext()) {
    String name = it.next();
    if (name.equals("Bob")) {
        it.remove(); // Safely remove element while iterating
    }
}
```

> [!WARNING]
> **ConcurrentModificationException:** If you try to modify a collection (e.g., `list.remove()`) while iterating over it with an `Iterator` (unless you use the `iterator.remove()` method), Java will likely throw a `ConcurrentModificationException` (this is JDK dependent). Always use the iterator's own `remove()` method for safe deletions.

[↑ Back to Table of Contents](#table-of-contents)
***

*To maintain the integrity of our collections, we need tools to compare objects. Let's explore the **Comparable and Comparator interfaces**.*

## 11. Comparable Interface

The **`Comparable` interface** is used to define the **natural ordering** of a class. When a class implements `Comparable`, it is saying, "I know how to compare myself to other objects of my type."

### 11.1 Usage

You implement the `compareTo(T o)` method.

```java
public class Student implements Comparable<Student> {
    private int id;
    private String name;

    @Override
    public int compareTo(Student other) {
        // Sort by ID ascending
        return Integer.compare(this.id, other.id);
    }
}
```

*   **Return Value:**
    *   `Negative`: `this` < `other`
    *   `Zero`: `this` == `other`
    *   `Positive`: `this` > `other`

[↑ Back to Table of Contents](#table-of-contents)
***

*While `Comparable` defines a default order, sometimes we need multiple, different ways to sort. That's where **`Comparator`** comes in.*

## 12. Comparator Interface

The **`Comparator` interface** is used to define **custom, external ordering** logic. Unlike `Comparable`, which is built into the class itself, a `Comparator` is a separate object used to compare two other objects.

### 12.1 When to use Comparator

*   When you cannot modify the original class (e.g., a third-party library).
*   When you need **multiple different sorting strategies** (e.g., sort by Name, then sort by Age).

### 12.2 Implementation Styles

**1. Classic Implementation:**
```java
Comparator<Student> nameComparator = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
};
```

**2. Modern Lambda Approach (Recommended):**
```java
Comparator<Student> ageComparator = (s1, s2) -> Integer.compare(s1.getAge(), s2.getAge());
```

**3. Comparator Chaining (Advanced):**
```java
// Sort by name, then by age if names are equal
Comparator<Student> complexComparator = 
    Comparator.comparing(Student::getName)
              .thenComparingInt(Student::getAge);
```

### 12.3 Comparison: Comparable vs. Comparator

| Feature | **Comparable** | **Comparator** |
| :--- | :--- | :--- |
| **Method** | `compareTo(T o)` | `compare(T o1, T o2)` |
| **Definition** | Internal (inside the class) | External (separate object) |
| **Flexibility** | One single "natural" order | Multiple custom orders |

[↑ Back to Table of Contents](#table-of-contents)
***

*Understanding how to sort data is vital, but as modern Java evolves, we must also understand how to ensure type safety and handle new language features like **Generics** and **Records**.*

## 13. Generics

**Generics** allow you to parameterize types. In the context of Collections, they allow a collection to be restricted to a specific type, ensuring **compile-time type safety**.

### 13.1 The Problem: Raw Types

Before Generics, you had to use `Object` and cast everything, which was error-prone.

```java
// OLD WAY (Dangerous)
List list = new ArrayList();
list.add("Hello");
String s = (String) list.get(0); // Manual cast required
```

### 13.2 The Solution: Type Parameters

With Generics, you specify the type using angle brackets `<T>`.

```java
// MODERN WAY (Safe)
List<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0); // No cast needed!
```

### 13.3 Wildcards in Generics

Wildcards (`?`) allow for more flexible code when dealing with different type parameters.

*   **Unbounded Wildcard (`<?>`):** Represents any type.
*   **Upper Bounded Wildcard (`<? extends T>`):** Represents `T` or any subclass of `T`. Use this for **reading** from a collection (Producer).
*   **Lower Bounded Wildcard (`<? super T>`):** Represents `T` or any superclass of `T`. Use this for **writing** to a collection (Consumer).

> [!IMPORTANT]
> **PECS Rule:** **P**roducer **E**xtends, **C**onsumer **S**uper.
> - If you are **getting** items out of a collection, use `<? extends T>`.
> - If you are **putting** items into a collection, use `<? super T>`.

[↑ Back to Table of Contents](#table-of-contents)
***

*Finally, we conclude with the modern evolution of Java: **Records, Sealed Classes and Pattern Matching**, and how they interact with modern data processing.*

## 14. Records, Sealed Classes and Pattern Matching

Modern Java (Version 14+) has introduced features that drastically reduce boilerplate and enhance data modeling, which integrates beautifully with the Collections Framework.

### 14.1 Records

**Records** are a concise way to create "data carrier" classes. They automatically generate `equals()`, `hashCode()`, `toString()`, and immutable fields.

```java
// Instead of 50 lines of boilerplate...
public record User(int id, String name) {}

// Usage
User u = new User(1, "Alice");
System.out.println(u.name()); // Accessor
```

### 14.2 Sealed Classes

**Sealed Classes** allow you to restrict which classes can extend or implement them. This provides "controlled inheritance."

```java
// Shape must be extended by the permitted classes
public sealed class Shape permits Circle, Rectangle, Triangle {}

// Circle can not be extended
public final class Circle extends Shape { ... }

// must be extended by Square
public sealed class Rectangle extends Shape permits Square { ... }

// can be extended like a regular class
public non-sealed class Triangle extends Shape { ... }
```

### 14.3 Pattern Matching

**Pattern Matching** allows you to check the type of an object and automatically cast it in a single step, making code much cleaner.

```java
// OLD WAY
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.toLowerCase());
}

// MODERN WAY (Pattern Matching for instanceof)
if (obj instanceof String s) {
    System.out.println(s.toLowerCase()); // 's' is already cast!
}
```

### 14.4 The "Grand Finale": Pattern Matching in Switch

Combined with **Sealed Classes**, pattern matching in `switch` expressions allows for exhaustive, type-safe data processing.

```java
int area = switch (shape) {
    case Circle c    -> (int) (Math.PI * c.radius() * c.radius());
    case Square s    -> s.side() * s.side();
    case Triangle t  -> (int) (0.5 * t.base() * t.height());
    // No 'default' needed if Shape is sealed and all permitted classes are covered!
};
```

[↑ Back to Table of Contents](#table-of-contents)
