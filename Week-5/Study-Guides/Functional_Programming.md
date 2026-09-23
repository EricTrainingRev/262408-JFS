# Functional Programming in Java

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. The Building Blocks: Lambdas](#2-the-building-blocks-lambdas)
* [3. Defining Behavior: Functional Interfaces](#3-defining-behavior-functional-interfaces)
* [4. Handling Absence: The Optional Class](#4-handling-absence-the-optional-class)
* [5. Data Processing: The Stream API](#5-data-processing-the-stream-api)
* [6. Summary: How They Work Together](#6-summary-how-they-work-together)

***

## 1. High-Level Overview

**Functional Programming (FP)** is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data. While Java is primarily an Object-Oriented (OO) language, the introduction of Java 8 brought powerful functional capabilities to the language.

In Java, functional programming allows developers to write more concise, readable, and expressive code by focusing on **what** to do (declarative) rather than **how** to do it (imperative).

> [!IMPORTANT]
> Functional programming in Java does not replace Object-Oriented programming; rather, it complements it by providing tools to handle data transformations and asynchronous logic more elegantly.

[↑ Back to Table of Contents](#table-of-contents)
***

*To understand how Java implements these functional concepts, we must first look at the simplest unit of functional behavior: the Lambda expression.*

## 2. The Building Blocks: Lambdas

A **Lambda Expression** is essentially an anonymous function—a function that does not have a name and does not belong to any class. It provides a clear and concise way to represent one method interface using an expression.

### 2.1 Structural Framework: Syntax

The syntax of a lambda expression consists of three parts:
1. **Argument List:** The input parameters (e.g., `(a, b)`).
2. **Arrow Token:** The `->` symbol that separates arguments from the body.
3. **Body:** The implementation of the function (e.g., `a + b`).

| Component | Example | Description |
| :--- | :--- | :--- |
| **No Parameters** | `() -> System.out.println("Hello")` | Uses empty parentheses. |
| **Single Parameter** | `s -> s.length()` | Parentheses are optional for a single parameter. |
| **Multiple Parameters** | `(a, b) -> a + b` | Parentheses are required. |
| **Multi-line Body** | `(a, b) -> { int sum = a + b; return sum; }` | Requires curly braces and an explicit `return`. |

### 2.2 Imperative vs. Functional

**Imperative (Traditional) vs. Lambda (Functional)**

| Feature | Imperative Approach | Lambda Approach |
| :--- | :--- | :--- |
| **Focus** | Step-by-step instructions (loops, counters). | The logic/operation being performed. |
| **Verbosity** | High (requires boilerplate classes/methods). | Low (expresses logic directly). |
| **State** | Often relies on mutating external variables. | Encourages immutability. |

**Example: Iterating a List**

**Imperative:**
```java
for (String name : names) {
    System.out.println(name);
}
```

**Lambda:**
```java
names.forEach(name -> System.out.println(name));
```

[↑ Back to Table of Contents](#table-of-contents)
***

*Lambdas cannot exist in isolation; they require a specific type of "container" to be recognized by the Java compiler. This brings us to Functional Interfaces.*

## 3. Defining Behavior: Functional Interfaces

A **Functional Interface** is an interface that contains exactly **one abstract method**. These interfaces serve as the "type" for lambda expressions.

### 3.1 Structural Framework: The @FunctionalInterface Annotation

While not strictly required, the `@FunctionalInterface` annotation is highly recommended. It tells the compiler to throw an error if the interface accidentally gains a second abstract method, ensuring it remains valid for lambdas.

### 3.2 Common Built-in Functional Interfaces

Java provides a rich set of standard functional interfaces in the `java.util.function` package. Most functional programming in Java relies on these four core categories:

| Category | Abstract Method | Input $\rightarrow$ Output | Purpose |
| :--- | :--- | :--- | :--- |
| **Predicate\<T>** | `boolean test(T t)` | `T -> boolean` | Takes an object and returns `true` or `false` (Filtering). |
| **Function\<T, R>** | `R apply(T t)` | `T -> R` | Takes an object and transforms it into something else (Mapping). |
| **Consumer\<T>** | `void accept(T t)` | `T -> void` | Takes an object and performs an action (Side-effects). |
| **Supplier\<T>** | `T get()` | `void -> T` | Takes nothing and produces an object (Creation). |

**Quick Example:**
```java
// Predicate
Predicate<Integer> isEven = n -> n % 2 == 0;

// Function
Function<String, Integer> lengthFunc = s -> s.length();

// Consumer
Consumer<String> printer = s -> System.out.println(s);

// Supplier
Supplier<Double> randomVal = () -> Math.random();
```

[↑ Back to Table of Contents](#table-of-contents)
***

*Once we can define functional behavior and transform data, we face a common problem in software: what happens when a value might be missing? The Optional class provides a safe way to handle this.*

## 4. Handling Absence: The Optional Class

The `java.util.Optional<T>` class is a container object which may or may not contain a non-null value. It was introduced to reduce the prevalence of `NullPointerException` (NPE) and to make APIs more expressive about potential absence.

### 4.1 The "Old Way" vs. The "Optional Way"

**The Problem: Null Checks**
```java
// Risk of NullPointerException
String name = user.getName();
if (name != null) {
    System.out.println(name.length());
}
```

**The Solution: Optional**
```java
// Declarative and safe
Optional<String> name = Optional.ofNullable(user.getName());
name.ifPresent(n -> System.out.println(n.length()));
```

### 4.2 Core Methods and Patterns

| Method | Description | Use Case |
| :--- | :--- | :--- |
| **`of(value)`** | Creates an Optional containing the value. | Use when you are **certain** the value is not null. |
| **`ofNullable(value)`**| Creates an Optional that may be empty. | Use when the value **might** be null. |
| **`isPresent()`** | Returns `true` if a value exists. | Manual check (use sparingly). |
| **`ifPresent(Consumer)`**| Performs an action only if a value exists. | Executing logic on a successful value. |
| **`orElse(default)`** | Returns the value, or a default if empty. | Providing a fallback value. |
| **`orElseThrow()`** | Returns the value, or throws an exception. | When an empty value represents an error state. |
| **`map(Function)`** | Transforms the value inside the Optional. | Chaining transformations safely. |

> [!TIP]
> **Avoid `.get()`!** Calling `.get()` on an empty Optional throws a `NoSuchElementException`. Always prefer `orElse`, `orElseGet`, or `ifPresent` to handle the empty case gracefully.

[↑ Back to Table of Contents](#table-of-contents)
***

*With Lambdas for logic, Functional Interfaces for types, and Optional for safety, we can now compose these into the ultimate data processing powerhouse: the Stream API.*

## 5. Data Processing: The Stream API

The **Stream API** (introduced in Java 8) allows for functional-style operations on sequences of elements (like Collections). A Stream is **not** a data structure; it is a way to move data through a pipeline of operations.

### 5.1 The Stream Pipeline Architecture

A stream pipeline consists of three distinct stages:

1.  **Source:** Where the data comes from (e.g., `List.stream()`, `Arrays.stream()`).
2.  **Intermediate Operations:** Operations that transform the stream into another stream. These are **lazy** (they don't execute until a terminal operation is called).
3.  **Terminal Operation:** The operation that triggers the processing and produces a result (e.g., a List, a single value, or a side effect).

### 5.2 Intermediate vs. Terminal Operations

| Type | Operation | Description |
| :--- | :--- | :--- |
| **Intermediate** | `filter(Predicate)` | Keeps only elements that match the condition. |
| **Intermediate** | `map(Function)` | Transforms each element into something else. |
| **Intermediate** | `sorted()` | Sorts the elements. |
| **Intermediate** | `distinct()` | Removes duplicates. |
| **Terminal** | `collect(Collector)` | Converts the stream back into a Collection (e.g., `toList()`). |
| **Terminal** | `forEach(Consumer)` | Performs an action on each element. |
| **Terminal** | `reduce(BinaryOperator)`| Combines elements into a single result (e.g., sum). |
| **Terminal** | `count()` | Returns the number of elements. |

### 5.3 Comprehensive Example

**Task:** From a list of names, find the lengths of names starting with "A", sort them, and collect them into a list.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Andrew", "Charlie", "Amy");

List<Integer> result = names.stream()
    .filter(name -> name.startsWith("A")) // Intermediate: Filter
    .map(String::length)                  // Intermediate: Transform
    .sorted()                             // Intermediate: Sort
    .collect(Collectors.toList());        // Terminal: Collect

// Result: [3, 5, 6] (for Amy, Alice, and Andrew)
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 6. Summary: How They Work Together

The power of functional programming in Java comes from the synergy of these four topics:

1.  **Lambdas** provide the lightweight logic.
2.  **Functional Interfaces** provide the type-safe containers for that logic.
3.  **Optional** provides the safety layer to handle missing data within those logic chains.
4.  **Stream API** provides the engine to run those logic chains across large datasets.

By combining these, you move from writing "how to loop and check nulls" to "how to transform and filter data," resulting in code that is significantly more robust and easier to maintain.
