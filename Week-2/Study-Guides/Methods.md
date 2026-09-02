# Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Method Mechanics](#2-method-mechanics)
    * [2.1 Method Declaration and Syntax](#21-method-declaration-and-syntax)
    * [2.2 Method Scope and Visibility](#22-method-scope-and-visibility)
* [3. Practical Application: Methods for Object Comparison](#3-practical-application-methods-for-object-comparison)
    * [3.1 The Equality Problem: Solving the `==` vs. `.equals()` Dilemma](#31-the-equality-problem-solving-the-vs-equals-dilemma)
    * [3.2 The `equals()` and `hashCode()` Contract](#32-the-equals-and-hashcode-contract)

***

## 1. High-Level Overview

In Object-Oriented Programming (OOP), an object is more than just a collection of data; it is defined by its **behavior**. **Methods** are the fundamental building blocks of behavior in Java. They allow us to encapsulate logic, perform actions on an object's state, and provide a controlled interface for interacting with data.

By using methods, we achieve **abstraction** (hiding complex logic behind a simple name) and **reusability** (writing code once and calling it many times).

[↑ Back to Table of Contents](#table-of-contents)
***

## 2. Method Mechanics

*Now that we understand the purpose of methods, let's look at the specific syntax required to define and execute them.*

### 2.1 Method Declaration and Syntax

A **method** is a block of code that only runs when it is called. To use a method, you must first define its structure.

**Syntax Anatomy:**
`[Modifier] [Return Type] [Method Name] ([Parameters]) { // Body }`

```java
public int addNumbers(int a, int b) { 
    return a + b; 
}
```

*   **Method Parameters:** The variables listed in the method declaration (the "inputs").
*   **Return Type:** The type of value the method sends back to the caller. Use `void` if the method returns nothing.
*   **Method Invocation:** The act of "calling" or executing the method (e.g., `int sum = addNumbers(5, 10);`).

### 2.2 Method Scope and Visibility

Understanding where a method's data lives is critical for preventing bugs and ensuring security.

*   **Method Scope:** Variables declared inside a method are **local** to that method. They exist during the execution of the method and are no longer accessible after the method returns.
*   **Method Visibility:** Controlled by **access modifiers** (e.g., `public`, `private`), which determine which other classes can "see" and call the method.

[↑ Back to Table of Contents](#table-of-contents)
***

*Having mastered the syntax of methods, let's look at a real-world challenge where we must use methods to solve a specific logic problem: determining if two complex objects are actually "the same."*

## 3. Practical Application: Methods for Object Comparison

In real-world programming, we often use methods to encapsulate complex comparison logic. A prime example is how Java handles **Object Equality**. When we want to know if two objects are "equal," we don't simply compare whether they are the same object reference; we invoke a method that performs custom content comparison.

### 3.1 The Equality Problem: Solving the `==` vs. `.equals()` Dilemma

The challenge is deciding whether to use the built-in `==` operator or the `.equals()` method. We use these different "tools" depending on the problem we are trying to solve.

| Feature | `==` Operator | `.equals()` Method |
| :--- | :--- | :--- |
| **Problem Solved** | **Identity** (Are they the same instance?) | **Equality** (Do they have the same content?) |
| **Logic Performed** | checks if referenced objects are the same | Executes a method containing custom comparison logic. |
| **Best Use Case** | Checking for `null` or comparing primitive types (`int`, `boolean`). | Comparing objects like `String`, `Date`, or custom business entities. |

**Implementation Example:**

```java
String s1 = new String("Java");
String s2 = new String("Java");

// Using the operator to check identity
System.out.println(s1 == s2);      // false (They are different objects in memory)

// Using the method to check content equality
System.out.println(s1.equals(s2)); // true  (The method logic confirms content is identical)
```

### 3.2 The `equals()` and `hashCode()` Contract

Because we use the `.equals()` method to solve comparison problems, Java requires that this method works in harmony with `hashCode()`. If you write a method to define equality, you must also provide a method that generates a hash value used by hash-based collections such as HashMap and HashSet.

1.  **`equals(Object obj)`:** The method you implement to define your custom equality logic.
2.  **`hashCode()`:** The method that provides an integer "fingerprint" of the object used by hash-based collections.

> [!IMPORTANT]
> **The Contract:** If your implementation of `objA.equals(objB)` returns `true`, then the `hashCode()` method **must** return the same integer for both objects. If you fail to do this, your method-based equality checks run the risk of failing when the object is used in a `HashMap` or `HashSet`.

[↑ Back to Table of Contents](#table-of-contents)
