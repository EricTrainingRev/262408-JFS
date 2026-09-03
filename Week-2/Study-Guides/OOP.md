# Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. The OOP Ecosystem: How the Pillars Interact](#2-the-oop-ecosystem-how-the-pillars-interact)
* [3. Deep Dive: The Four Pillars](#3-deep-dive-the-four-pillars)
    * [3.1 Encapsulation (Data Protection)](#31-encapsulation-data-protection)
    * [3.2 Inheritance (The \"Is-A\" Relationship)](#32-inheritance-the-is-a-relationship)
    * [3.3 Abstraction (Complexity Hiding)](#33-abstraction-complexity-hiding)
    * [3.4 Polymorphism (Many Forms)](#34-polymorphism-many-forms)
* [4. Overloading vs. Overriding](#4-overloading-vs-overriding)
* [5. The `Object` Class](#5-the-object-class)

***

## 1. High-Level Overview

**Object-Oriented Programming (OOP)** is a programming paradigm based on the concept of "objects," which can contain data (fields) and code (methods). Instead of writing a long list of instructions (procedural programming), we design a system of interacting entities.

This module covers the four fundamental principles—**Encapsulation**, **Inheritance**, **Abstraction**, and **Polymorphism**—that allow us to build complex, scalable, and maintainable software.

[↑ Back to Table of Contents](#table-of-contents)
***

## 2. The OOP Ecosystem: How the Pillars Interact

While the four pillars are often taught individually, they actually function as a single, cohesive system. Understanding how they interlock is the key to moving from a "coder" to an "architect."

| Pillar | Primary Role | Relationship to Other Pillars |
| :--- | :--- | :--- |
| **Encapsulation** | **Data Integrity** | Provides the "shell" that protects the data used by the other pillars. |
| **Abstraction** | **Interface** | Defines the "what" (the contract) that **Inheritance** and **Polymorphism** will later implement. |
| **Inheritance** | **Hierarchy** | Uses **Abstraction** to build specialized versions of general concepts. |
| **Polymorphism** | **Flexibility** | Uses inheritance and interface implementation to allow different objects to be treated as the same general type. |

*Think of it this way: **Abstraction** provides the blueprint, **Encapsulation** builds the walls, **Inheritance** allows you to build different models of the house, and **Polymorphism** allows you to interact with any house using a universal remote.*

[↑ Back to Table of Contents](#table-of-contents)
***

## 3. Deep Dive: The Four Pillars

### 3.1 Encapsulation (Data Protection)

**Encapsulation** is the practice of bundling data (fields) and methods into a single unit (class) and **restricting direct access** to some of the object's components.

*   **Common Implementation:** 
    1. Declare class variables as `private`.
    2. Provide public **Getter** and **Setter** methods to control how the data is viewed or modified.
*   **Why use it?** It prevents "corrupt" data (e.g., setting an `age` variable to `-5`) and allows you to change the internal implementation without breaking code that uses the class.

```java
public class BankAccount {
    private double balance; // Hidden from direct access

    public void deposit(double amount) {
        if (amount > 0) { // Logic to prevent "corrupt" data
            balance += amount;
        }
    }

    public double getBalance() { 
        return balance; 
    }
}
```

### 3.2 Inheritance (The "Is-A" Relationship)

**Inheritance** allows a new class (**Subclass/Child**) to acquire the properties and methods of an existing class (**Superclass/Parent**).

* **Keyword:** `extends`
* **Benefit:** Code reusability. You don't have to rewrite common logic for every new object type.
* **Example:** A `Dog` **is-a** `Animal`. The `Dog` class inherits `eat()` from `Animal` but can add its own unique behavior like `bark()`.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking!"); }
}

// Usage:
Dog myDog = new Dog();
myDog.eat();  // Inherited from Animal
myDog.bark(); // Specific to Dog
```

### 3.3 Abstraction (Complexity Hiding)

**Abstraction** focuses on the **essential qualities** of an object rather than the specific implementation details. It allows you to define a "contract" (what an object can do) without specifying "how" it does it.

* **Mechanism:** Achieved via **Abstract Classes** and **Interfaces**.
* **Goal:** To reduce complexity by allowing the programmer to focus on high-level interactions.

```java
// The "Contract": All remote controls must have a power button
interface RemoteControl {
    void pressPowerButton();
}

// Implementation 1: A TV Remote
class TVRemote implements RemoteControl {
    @Override
    public void pressPowerButton() {
        System.out.println("TV: Powering on/off...");
    }
}

// Implementation 2: An AC Remote
class ACRemote implements RemoteControl {
    @Override
    public void pressPowerButton() {
        System.out.println("AC: Powering on/off...");
    }
}
```

### 3.4 Polymorphism (Many Forms)

**Polymorphism** allows an object to take on many forms. In practice, this means a parent class reference can point to a child class object, and the program will automatically use the correct, specialized behavior at runtime.

* **Example:** If we use our `RemoteControl` interface from the previous section, we can treat different remotes exactly the same way.

```java
public class Main {
    public static void main(String[] args) {
        // Polymorphism: The reference type is 'RemoteControl', 
        // but the actual object is 'TVRemote'
        RemoteControl myRemote = new TVRemote();
        
        // The program "knows" to call the TV version of the method
        myRemote.pressPowerButton(); 

        // Swapping the object to an AC remote
        myRemote = new ACRemote();
        myRemote.pressPowerButton(); // Now it calls the AC version
    }
}
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 4. Overloading vs. Overriding

While both involve methods with the same name, they occur at different stages of the program lifecycle.

| Feature | **Method Overloading** | **Method Overriding** |
| :--- | :--- | :--- |
| **Concept** | Multiple methods in the **same class** with the same name but different parameters. | A method in a **subclass** that has the same name and parameters as a method in its superclass. |
| **Relationship** | Within a single class. | Between Parent and Child classes. |
| **Signature** | **Must change** (different arguments). | Must have the same method signature (name and parameter list). The return type must be compatible, and the overriding method cannot reduce visibility. |
| **Binding Time** | **Compile-time** (Static binding). | **Runtime** (Dynamic binding). |

**Vs: Overloading vs. Overriding**
**Overloading** is about **variety** (giving one name multiple uses, like `print(int)` and `print(String)`). **Overriding** is about **specialization** (changing the behavior of an inherited action to be more specific to the child).

```java
class Calculator {
    // OVERLOADING: Same name, different parameters (Compile-time)
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}

class SpecialCalculator extends Calculator {
    // OVERRIDING: Same name, same parameters, different behavior (Runtime)
    @Override
    int add(int a, int b) { return (a + b) * 2; } 
}
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 5. The `Object` Class

In Java, the `java.lang.Object` class is the **root of the entire class hierarchy**. Every single class you create—and every built-in Java class—automatically inherits from `Object`.

Because every class is an `Object`, every class inherits several fundamental methods:
*   **`toString()`**: Returns a string representation of the object. (Commonly overridden to provide useful info).
*   **`equals(Object obj)`**: Compares two objects for equality (as discussed in previous modules).
*   **`hashCode()`**: Returns an integer hash value used by hash-based collections such as HashMap and HashSet. Different objects may produce the same hash code.
*   **`getClass()`**: Returns the runtime class of the object.

[↑ Back to Table of Contents](#table-of-contents)
***

*Every class in Java, no matter how simple, has a common ancestor. Understanding this hierarchy is key to understanding how Java treats all objects.*
