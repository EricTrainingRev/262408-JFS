# JavaScript Fundamentals

## Table of Contents

* [1. JavaScript Introduction](#1-javascript-introduction)
* [2. Datatypes](#2-datatypes)
    * [2.1 Primitives](#21-primitives)
    * [2.2 Objects](#22-objects)
    * [2.3 Primitives vs. Objects](#23-primitives-vs-objects)
* [3. Type Coercion](#3-type-coercion)
    * [3.1 Loose vs. Strict Equality](#31-loose-vs-strict-equality)
* [4. Variable Scopes](#4-variable-scopes)
    * [4.1 Let and Const Keywords](#41-let-and-const-keywords)
    * [4.2 Practical Examples](#42-practical-examples)
* [5. Hoisting](#5-hoisting)
    * [5.1 Hoisting Behaviors by Declaration Type](#51-hoisting-behaviors-by-declaration-type)
    * [5.2 The Temporal Dead Zone (TDZ)](#52-the-temporal-dead-zone-tdz)
    * [5.3 Practical Examples](#53-practical-examples)
* [6. Strict Mode](#6-strict-mode)
    * [6.1 Why Use Strict Mode?](#61-why-use-strict-mode)
    * [6.2 Implementation & Examples](#62-implementation--examples)
    * [6.3 Comparison: Sloppy vs. Strict Mode](#63-comparison-sloppy-vs-strict-mode)
* [7. Functions](#7-functions)
    * [7.1 Function Declarations vs. Expressions](#71-function-declarations-vs-expressions)
    * [7.2 Arrow Functions and Default Parameters](#72-arrow-functions-and-default-parameters)
    * [7.3 Closures and the 'this' Context](#73-closures-and-the-this-context)
* [8. The 'this' Keyword](#8-the-this-keyword)
    * [8.1 The Four Binding Rules](#81-the-four-binding-rules)
    * [8.2 Implicit, Explicit, and Default Binding](#82-implicit-explicit-and-default-binding)
    * [8.3 Arrow Functions and Lexical 'this'](#83-arrow-functions-and-lexical-this)
* [9. Template Literals](#9-template-literals)
* [10. Arrays](#10-arrays)
* [11. Spread and Rest Operators](#11-spread-and-rest-operators)
* [12. Errors](#12-errors)
* [13. Classes](#13-classes)

***

## 1. JavaScript Introduction

**JavaScript** is a high-level, **interpreted** (just-in-time compiled) programming language that conforms to the **ECMAScript specification**. It is a **multi-paradigm** language, supporting **event-driven**, **functional**, and **imperative** programming styles.

In modern web development, JavaScript is the engine of interactivity, enabling everything from simple form validation to complex **single-page applications (SPAs)**.

```javascript
// A simple example of JavaScript logic
const greeting = "Hello, World!";
console.log(greeting);

function add(a, b) {
  return a + b;
}
console.log(`Sum: ${add(5, 10)}`);
```

## 2. Datatypes

In JavaScript, data is categorized into two primary groups: **Primitives** and **Objects**.

### 2.1 Primitives
**Primitives** are immutable and represent a single value.

*   **String**: Textual data.
*   **Number**: Integers and floating-point numbers.
*   **Boolean**: `true` or `false`.
*   **Undefined**: A variable that has been declared but not assigned a value.
*   **Null**: Represents the intentional absence of any object value.
*   **Symbol**: A unique and immutable identifier.
*   **BigInt**: For integers larger than the **Number** limit.

### 2.2 Objects
**Objects** are collections of properties (key-value pairs) and are mutable. This includes **Arrays**, **Functions**, and **Dates**.

```javascript
// Example of Primitives
let name = "Alice"; // String
let age = 30;       // Number
let isStudent = true; // Boolean

// Example of Objects
let user = { name: "Alice", age: 30 }; // Object
let colors = ["red", "green", "blue"]; // Array (a type of Object)
```

### 2.3 Primitives vs. Objects
Understanding the conceptual boundary between **Primitives** and **Objects** is crucial for mastering JavaScript memory management and behavior.

*   **Value vs. Reference**: **Primitives** are passed by **value**. When you assign one primitive to another, a copy of the value is created. **Objects** are passed by **reference**. When you assign one object to another, both variables point to the same memory address.
*   **Immutability vs. Mutability**: **Primitives** are **immutable**; you cannot change the value of a primitive itself, only reassign the variable. **Objects** are **mutable**; you can modify their properties or elements without changing the reference.

| Feature | Primitives | Objects |
| :--- | :--- | :--- |
| **Storage** | Stored on the **Stack** | Stored on the **Heap** |
| **Access** | Direct value access | Via reference/pointer |
| **Behavior** | Passed by value | Passed by reference |

> [!NOTE]
> The **Stack vs. Heap** row is a useful mental model, but it is a simplification. Modern JavaScript engines may place primitives on the heap too (e.g., large strings or primitives captured by a closure). The important, always-true distinction is **value vs. reference** and **immutability vs. mutability**.

[↑ Back to Table of Contents](#table-of-contents)
***

## 3. Type Coercion

**Type Coercion** is the automatic or implicit conversion of values from one data type to another (e.g., strings to numbers).

| Type of Coercion | Description | Example |
| :--- | :--- | :--- |
| **Implicit Coercion** | Performed by JS during operations. | `5 + "5" // "55"` (Number to String) |
| **Explicit Coercion** | Performed by the developer manually. | `Number("5") // 5` |

> [!WARNING]
> **Implicit Coercion** can lead to unexpected bugs. Always prefer **Explicit Coercion** to make your intent clear and code predictable.

### Examples of Coercion

```javascript
// Implicit Coercion (Numeric to String)
console.log("The answer is " + 42); // "The answer is 42"

// Implicit Coercion (String to Number)
console.log("10" - 5); // 5

// Explicit Coercion (String to Number)
const strNum = "100";
const actualNum = Number(strNum);
console.log(actualNum + 50); // 150

// Explicit Coercion (Boolean)
console.log(Boolean(1)); // true
console.log(Boolean(0)); // false
```

### 3.1 Loose vs. Strict Equality

Equality is where coercion most often bites. The **loose** operators (`==` / `!=`) coerce both sides before comparing, while the **strict** operators (`===` / `!==`) compare type **and** value with no coercion.

| Operator | Name | Coerces? | Example | Result |
| :--- | :--- | :--- | :--- | :--- |
| `==` | Loose equality | ✅ Yes | `5 == '5'` | `true` |
| `===` | Strict equality | ❌ No | `5 === '5'` | `false` |
| `!=` | Loose inequality | ✅ Yes | `5 != '5'` | `false` |
| `!==` | Strict inequality | ❌ No | `5 !== '5'` | `true` |

```javascript
// Loose equality coerces, which produces surprising results:
console.log(0 == '');            // true  ('' coerces to 0)
console.log(0 == false);        // true  (false coerces to 0)
console.log(null == undefined); // true  (special rule: they are "equal")
console.log([] == '');          // true  ([] coerces to '')

// Strict equality compares type AND value, so no coercion happens:
console.log(0 === '');          // false
console.log(0 === false);       // false
console.log(null === undefined); // false

// NaN is never equal to itself, even with ===:
console.log(NaN === NaN);       // false
```

> [!WARNING]
> **Always prefer `===` and `!==`.** Loose equality (`==`) performs type coercion, which can silently produce `true` for values that look different (e.g., `0 == ''`). Strict equality is predictable and avoids these bugs. The one common exception is checking for both `null` and `undefined` at once: `value == null` is `true` for both, whereas `value === null` only matches `null`.

[↑ Back to Table of Contents](#table-of-contents)
***

## 4. Variable Scopes

**Scope** determines the accessibility (visibility) of variables within different parts of your code. Understanding scope is fundamental to preventing variable collisions and managing memory efficiently.

*   **Global Scope**: Variables declared outside any function or block. They are accessible from anywhere in the script.
*   **Function Scope**: Variables declared within a function. They are only accessible within that specific function.
*   **Block Scope**: Variables declared within a `{}` block (like `if` statements or `for` loops). This was introduced in ES6 to provide more granular control.

### 4.1 Let and Const Keywords

Before ES6, `var` was the primary way to declare variables. However, `var` is function-scoped and can lead to unpredictable behavior due to hoisting. Modern JavaScript relies on `let` and `const` to provide predictable **block-level scoping**.

| Keyword | Scope | Re-assignable? | Redeclarable? | Behavior |
| :--- | :--- | :--- | :--- | :--- |
| `var` | Function | ✅ Yes | ✅ Yes | Subject to hoisting; can leak out of blocks. |
| `let` | Block | ✅ Yes | ❌ No | Predictable; stays within its `{}` block. |
| `const` | Block | ❌ No | ❌ No | Constant reference; requires immediate initialization. |

**`const` vs `let`**
Use `const` by default for all variables that do not need to be reassigned. This signals intent to other developers and prevents accidental bugs. Use `let` only when you know the variable's value must change (e.g., a loop counter or a toggle).

> [!TIP]
> **Prefer `const` over `let`.** Even if you think a value might change, try to use `const` first. If you find you need to reassign it, refactor to `let`. This makes your code easier to reason about.

### 4.2 Practical Examples

The following examples demonstrate how scope boundaries behave in real-world scenarios.

```javascript
// 1. Global Scope
const globalVar = "I am everywhere";

function scopeDemo() {
  // 2. Function Scope
  const functionVar = "I am only inside this function";
  
  if (true) {
    // 3. Block Scope
    let blockVar = "I only exist inside this IF block";
    const constantBlockVar = "I am also trapped in this block";
    
    console.log(blockVar); // Works
    console.log(constantBlockVar); // Works
  }

  console.log(functionVar); // Works
  // console.log(blockVar); // ❌ ReferenceError: blockVar is not defined
}

scopeDemo();
console.log(globalVar); // Works
// console.log(functionVar); // ❌ ReferenceError: functionVar is not defined
```

**The "Var" Pitfall (Function vs Block Scope):**
Unlike `let` and `const`, `var` does not respect block boundaries, which often leads to "leaked" variables.

```javascript
if (true) {
  var leakedVar = "I leaked out of the block!";
  let trappedVar = "I am safely contained.";
}

console.log(leakedVar); // "I leaked out of the block!" (Unexpected behavior)
// console.log(trappedVar); // ❌ ReferenceError: trappedVar is not defined (Expected behavior)
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 5. Hoisting

**Hoisting** is a JavaScript mechanism where variables and function declarations are moved to the top of their containing scope during the compilation phase. This allows some functions to be called before they appear to be defined in the code.

However, how "hoisting" behaves depends entirely on how the variable was declared.

### 5.1 Hoisting Behaviors by Declaration Type

| Declaration Type | Hoisted? | Initialized Value | Result of Accessing Before Declaration |
| :--- | :--- | :--- | :--- |
| **Function Declaration** | ✅ Yes | The actual function | ✅ Works perfectly. |
| **`var`** | ✅ Yes | `undefined` | ⚠️ Returns `undefined` (can cause silent bugs). |
| **`let` / `const`** | ✅ Yes | None | ❌ `ReferenceError` (due to Temporal Dead Zone). |

### 5.2 The Temporal Dead Zone (TDZ)

While `let` and `const` are technically hoisted, they are not initialized. The period between the entering of the scope and the actual line of declaration is known as the **Temporal Dead Zone (TDZ)**. Accessing the variable in this zone results in a `ReferenceError`.

> [!IMPORTANT]
> The TDZ is a safety feature. It prevents you from using variables before they are properly initialized, which helps avoid the "silent failure" bugs common with `var`.

### 5.3 Practical Examples

The following examples contrast the different behaviors in action.

```javascript
// 1. Function Declaration Hoisting
greet(); // ✅ "Hello!" (Works because the whole function is hoisted)

function greet() {
  console.log("Hello!");
}

// 2. The 'var' Pitfall (Undefined, not Error)
console.log(myVar); // ⚠️ undefined (Hoisted, but initialized as undefined)
var myVar = "I am a var";

// 3. The 'let/const' Safety (TDZ)
// console.log(myLet); // ❌ ReferenceError: Cannot access 'myLet' before initialization
let myLet = "I am a let";

// 4. Function Expressions (Follow variable rules)
// try {
//   sayHi(); 
// } catch (e) {
//   console.log(e.message); // ❌ "sayHi is not a function" (TypeError: var name is undefined at call time)
// }
// var sayHi = () => console.log("Hi!"); 
```

**Key Takeaway:** Always declare your variables and functions at the top of their scope to write cleaner, more predictable code, even though hoisting makes it technically possible to do otherwise.

[↑ Back to Table of Contents](#table-of-contents)
***

## 6. Strict Mode

**Strict Mode** (`"use strict";`) is a way to opt into a restricted variant of JavaScript. It is not a separate language, but a mode that makes the engine enforce stricter parsing and error handling, helping you write more secure and optimized code.

### 6.1 Why Use Strict Mode?

Without strict mode, JavaScript often fails silently or allows "bad" code to run, which can lead to difficult-to-debug issues.

**Key Benefits:**
*   **Prevents Accidental Globals:** In sloppy mode, assigning a value to an undeclared variable automatically creates a global variable. Strict mode throws a `ReferenceError`.
*   **Eliminates Silent Errors:** It turns silent failures (like assigning to a read-only property) into explicit errors.
*   **Secures the `this` Keyword:** In sloppy mode, if `this` is null or undefined, it defaults to the global object. In strict mode, it remains `undefined`.
*   **Optimization Ready:** It allows JavaScript engines to perform more aggressive optimizations because the code follows more predictable rules.

### 6.2 Implementation & Examples

To enable strict mode, you place the `"use strict";` directive at the very top of your script or within a function.

```javascript
// --- Example 1: Preventing Global Leaks ---

// Sloppy Mode (Default)
function sloppyFunction() {
  leakedVar = "I am a global variable now!"; // No declaration!
}
sloppyFunction();
console.log(leakedVar); // "I am a global variable now!"

// Strict Mode
function strictFunction() {
  "use strict";
  // leakedVar = "I am a global variable now!"; // ❌ ReferenceError: leakedVar is not defined
}
strictFunction();


// --- Example 2: The 'this' Keyword Behavior ---

// Sloppy Mode: a plain function call sets `this` to the global object.
function checkThis() {
  console.log("this is:", this);
}
checkThis(); // ⚠️ logs the Window/Global object

// Strict Mode: `this` stays undefined inside a strict function.
// Note: strictness is per-function, so "use strict" must be INSIDE the
// function whose `this` we are inspecting.
function strictCheckThis() {
  "use strict";
  console.log("this is:", this);
}
strictCheckThis(); // ✅ logs undefined
```

### 6.3 Comparison: Sloppy vs. Strict Mode

| Feature | Sloppy Mode | Strict Mode |
| :--- | :--- | :--- |
| **Undeclared Variables** | Creates a global variable | Throws `ReferenceError` |
| **`this` in global function** | Refers to `window` / `global` | Is `undefined` |
| **Duplicate Parameter Names** | Allowed | Throws `SyntaxError` |
| **Deleting non-deletable props** | Fails silently | Throws `TypeError` |

> [!IMPORTANT]
> **Modern JavaScript (ES6 Modules):** All code inside ES6 modules is **automatically in strict mode**. You do not need to add `"use strict";` if you are using `import` and `export` statements.

[↑ Back to Table of Contents](#table-of-contents)
***

## 7. Functions

**Functions** are the fundamental building blocks of JavaScript. They are reusable blocks of code designed to perform specific tasks, allowing for abstraction, modularity, and the implementation of complex logic through composition.

In JavaScript, functions are **First-Class Citizens**, meaning they can be treated like any other variable: they can be passed as arguments to other functions, returned from functions, and assigned to variables.

### 7.1 Function Declarations vs. Expressions

The way a function is defined in JavaScript significantly impacts its behavior, particularly regarding **hoisting** and **scope**.

| Feature | Function Declaration | Function Expression |
| :--- | :--- | :--- |
| **Syntax** | `function name() { ... }` | `const name = function() { ... };` |
| **Hoisting** | ✅ **Fully hoisted**. Can be called before definition. | ⚠️ The binding follows its variable's hoisting rules (`var` → `undefined`; `const`/`let` → TDZ), so it cannot be called before the assignment runs. |
| **Use Case** | General-purpose, top-level logic. | Closures, callbacks, and limiting scope. |
| **Flexibility** | Fixed name and structure. | Can be anonymous or assigned to variables. |

> [!TIP]
> Use **Function Declarations** when you want a function to be available throughout the entire scope. Use **Function Expressions** when you want to control exactly when the function becomes available, which is often safer in large-scale applications.

```javascript
// 1. Function Declaration (Hoisted)
greet(); // ✅ Works!

function greet() {
  console.log("Hello from the declaration!");
}

// 2. Function Expression (Not Hoisted)
// sayHi(); // ❌ ReferenceError: Cannot access 'sayHi' before initialization

const sayHi = function() {
  console.log("Hi from the expression!");
};
sayHi(); // ✅ Works!
```

### 7.2 Arrow Functions and Default Parameters

Modern JavaScript (ES6+) introduced more concise ways to write functions, specifically targeting common patterns like short callbacks and parameter handling.

#### Arrow Functions
**Arrow functions** provide a compact syntax and, crucially, **lexical `this` binding**. Unlike traditional functions, they do not create their own `this` context; instead, they inherit it from the enclosing scope.

```javascript
// Traditional Function
const multiply = function(a, b) {
  return a * b;
};

// Arrow Function (Concise)
const multiplyArrow = (a, b) => a * b;

// Single Parameter (No parentheses needed)
const square = x => x * x;

// Multi-line Body (Requires explicit return)
const complexTask = (x) => {
  const y = x * 2;
  return y + 10;
};
```

#### Default Parameters
To prevent errors caused by `undefined` arguments, **Default Parameters** allow you to specify fallback values directly in the function signature.

```javascript
function createUser(name, role = "Guest", status = "Active") {
  console.log(`User: ${name}, Role: ${role}, Status: ${status}`);
}

createUser("Alice");                // User: Alice, Role: Guest, Status: Active
createUser("Bob", "Admin");         // User: Bob, Role: Admin, Status: Active
createUser("Charlie", undefined);   // User: Charlie, Role: Guest, Status: Active
```

### 7.3 Closures and the 'this' Context

The most common source of bugs in JavaScript is the misunderstanding of how `this` and **Closures** interact within functions.

#### The `this` Context
The value of `this` is determined by **how** a function is called, not where it is defined (except for arrow functions).

> [!WARNING]
> When passing a method as a callback (e.g., in `setTimeout` or an event listener), the `this` context is often lost and reverts to the global object or `undefined`. Use **Arrow Functions** or `.bind()` to preserve the context.

```javascript
const user = {
  name: "Alice",
  greet: function() {
    console.log(`Hi, I am ${this.name}`);
  },
  // The Arrow Function trap
  greetArrow: () => {
    console.log(`Hi, I am ${this.name}`); 
  }
};

user.greet();      // ✅ "Hi, I am Alice"
user.greetArrow(); // ❌ "Hi, I am " (this is the global/window object — its .name is not the user's)
```

#### Closures: Function + Lexical Environment
A **Closure** is the combination of a function bundled together with references to its surrounding state (the lexical environment). This allows an inner function to access variables from an outer function even after the outer function has finished executing.

```javascript
function createCounter() {
  let count = 0; // Private variable

  return {
    increment: () => {
      count++;
      return count;
    },
    decrement: () => {
      count--;
      return count;
    }
  };
}

const myCounter = createCounter();
console.log(myCounter.increment()); // 1
console.log(myCounter.increment()); // 2
// count is inaccessible from the outside, providing true encapsulation.
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 8. The 'this' Keyword

**`this`** is one of the most misunderstood concepts in JavaScript. It is a keyword that refers to an **execution context**—essentially, it points to the object that is currently "owning" or executing the code.

Unlike variables, the value of `this` is not static. It is **dynamically determined** at runtime based on *how* a function is invoked, rather than where it is defined (with the notable exception of arrow functions).

> [!IMPORTANT]
> Mastering `this` is the key to understanding object-oriented programming in JavaScript and avoiding the common "lost context" bugs that occur when passing methods as callbacks.

### 8.1 The Four Binding Rules

To predict the value of `this`, you must identify which of the following four binding rules applies to the current call site.

| Binding Type | Rule | Context / Target |
| :--- | :--- | :--- |
| **1. Default Binding** | Function called as a standalone function. | The **Global Object** (`window` in browsers) or `undefined` in strict mode. |
| **2. Implicit Binding** | Function is called as a method of an object. | The **Object** before the dot (the "owner"). |
| **3. Explicit Binding** | Function is called using `.call()`, `.apply()`, or `.bind()`. | The **first argument** passed to those methods. |
| **4. `new` Binding** | Function is invoked as a constructor with the `new` keyword. | The **newly created empty object**. |

---

### 8.2 Implicit, Explicit, and Default Binding

#### 1. Default & Implicit Binding
The difference between a standalone function call and a method call is the presence of an "owner" object.

```javascript
const user = {
  name: "Alice",
  // Implicit Binding: 'this' refers to 'user'
  greet() {
    console.log(`Hello, my name is ${this.name}`);
  }
};

function standalone() {
  // Default Binding: 'this' refers to Window/Global
  console.log("Global name:", this.name);
}

user.greet();      // ✅ "Hello, my name is Alice"
standalone();      // ⚠️ "Global name: " (in non-strict mode; this.name is the window's, which is empty/undefined)
```

#### 2. Explicit Binding (`call`, `apply`, `bind`)
You can manually "force" a function to use a specific object as its `this` context.

```javascript
function introduce(greeting, punctuation) {
  console.log(`${greeting}, I am ${this.name}${punctuation}`);
}

const person = { name: "Bob" };

// .call() - Arguments passed individually
introduce.call(person, "Hi", "!"); // "Hi, I am Bob!"

// .apply() - Arguments passed as an array
introduce.apply(person, ["Hey", "."]); // "Hey, I am Bob."

// .bind() - Returns a NEW function with 'this' permanently bound
const boundIntroduce = introduce.bind(person);
boundIntroduce("Greetings", "..."); // "Greetings, I am Bob..."
```

#### 3. Constructor Binding (`new`)
When you use the `new` keyword, JavaScript creates a new object and sets `this` to point to that object.

```javascript
function Car(make, model) {
  this.make = make;
  this.model = model;
}

const myCar = new Car("Tesla", "Model 3");
console.log(myCar.make); // ✅ "Tesla"
```

---

### 8.3 Arrow Functions and Lexical 'this'

#### The Arrow Function Exception (Lexical `this`)
Arrow functions do **not** have their own `this` binding. Instead, they capture the `this` value of the enclosing lexical scope at the time they are created. This makes them perfect for callbacks but dangerous for object methods.

```javascript
const timer = {
  seconds: 0,
  start() {
    // Arrow function inherits 'this' from start(), which is 'timer'
    setInterval(() => {
      this.seconds++;
      console.log(this.seconds);
    }, 1000);
  }
};

timer.start(); // ✅ Works perfectly. 'this' is correctly 'timer'.
```

#### Comparative Analysis: `this` Behavior Summary

| Context | `this` Value | Typical Use Case |
| :--- | :--- | :--- |
| **Global Scope** | `window` / `global` | Top-level variables/functions |
| **Object Method** | The Object itself | Encapsulated object logic |
| **Arrow Function** | Inherited from parent | Callbacks (e.g., `setTimeout`, `.map`) |
| **Constructor** | The new instance | Creating multiple similar objects |

> [!WARNING]
> **The Callback Trap:** When you pass an object method as a callback (e.g., `setTimeout(user.greet, 1000)`), the implicit binding is lost, and `this` reverts to the global object. Always use an arrow function or `.bind()` to maintain context.

[↑ Back to Table of Contents](#table-of-contents)
***

## 9. Template Literals

**Template Literals** are a modern, more powerful way to work with strings in JavaScript. Introduced in ES6, they replace the older method of string concatenation (using the `+` operator) with a much cleaner, more readable syntax using **backticks** (`` ` ``).

Template literals are essential for creating dynamic strings where variables or complex logic need to be embedded directly within the text.

### 9.1 Key Features

*   **String Interpolation:** Use the `${expression}` syntax to embed variables, function calls, or any JavaScript expression directly into the string.
*   **Multi-line Strings:** Unlike standard single or double-quoted strings, template literals preserve line breaks, allowing you to write multi-line text without using escape characters like `\n`.
*   **Expression Evaluation:** You can perform math or logic inside the interpolation braces, making the string a dynamic result of code execution.

### 9.2 Implementation & Examples

The following examples demonstrate the transition from traditional concatenation to modern template literals.

```javascript
const user = "Alice";
const itemsCount = 5;
const price = 19.99;

// --- 1. Traditional Concatenation (The "Old" Way) ---
// Hard to read, error-prone with spaces, and messy with newlines.
const oldWay = "Hello, " + user + "!\n" + 
               "You have " + itemsCount + " items in your cart.\n" + 
               "Total: $" + price;

console.log("--- Old Way ---");
console.log(oldWay);


// --- 2. Template Literals (The Modern Way) ---
// Highly readable, easy to maintain, and handles newlines naturally.
const modernWay = `Hello, ${user}!
You have ${itemsCount} items in your cart.
Total: $${price}`;

console.log("\n--- Modern Way ---");
console.log(modernWay);


// --- 3. Embedding Expressions & Logic ---
// You aren't limited to just variables; you can run code inside the `${}`.
const isPremium = true;

const statusMessage = `Account Status: ${isPremium ? "⭐ Premium Member" : "Standard Member"}
Next billing cycle: ${new Date().toLocaleDateString()}
Discount Applied: ${10 * 0.5}%`; // Calculating 50% of 10 inline

console.log("\n--- Logic in Literals ---");
console.log(statusMessage);
```

> [!TIP]
> **Avoid "String Soup":** If you find yourself using more than two `+` operators to build a single string, refactor it to a **Template Literal**. It significantly reduces the chance of missing a space or a comma.

[↑ Back to Table of Contents](#table-of-contents)
***

## 10. Arrays

**Arrays** are high-level, list-like objects used to store ordered collections of multiple values under a single variable name. Unlike many other languages, JavaScript arrays are **dynamic**—they can grow or shrink in size automatically, and they can hold a mix of different data types (though keeping them homogeneous is a best practice for clarity).

In JavaScript, arrays are specialized objects that come equipped with a vast library of built-in methods for searching, transforming, and manipulating data.

### 10.1 Array Fundamentals

| Feature | Description |
| :--- | :--- |
| **Indexing** | Elements are accessed via a **zero-based index** (the first element is at index `0`). |
| **Length Property** | The `.length` property automatically tracks the number of elements in the array. |
| **Mutability** | Arrays are **mutable**; you can change, add, or remove elements without creating a new array. |
| **Heterogeneity** | An array can contain primitives, objects, other arrays, or even functions. |

### 10.2 Common Array Methods

To master arrays, you must understand the distinction between methods that **mutate** (change) the original array and those that are **non-mutating** (return a new array).

#### A. Basic Manipulation (Mutating)
These methods directly alter the array they are called upon.

```javascript
const fruits = ["Apple", "Banana"];

// .push() - Adds to the end
fruits.push("Cherry"); // ["Apple", "Banana", "Cherry"]

// .pop() - Removes from the end
fruits.pop(); // ["Apple", "Banana"]

// .unshift() - Adds to the beginning
fruits.unshift("Mango"); // ["Mango", "Apple", "Banana"]

// .shift() - Removes from the beginning
fruits.shift(); // ["Apple", "Banana"]

console.log("Basic Manipulation:", fruits);
```

#### B. Transformation & Iteration (Non-Mutating)
These are the "workhorse" methods of modern JavaScript. They do not change the original array but instead return a new version of the data.

```javascript
const numbers = [1, 2, 3, 4, 5];

// .map() - Transforms every element
// Creates a new array where every number is doubled
const doubled = numbers.map(num => num * 2); 
// doubled: [2, 4, 6, 8, 10]

// .filter() - Selects elements based on a condition
// Creates a new array containing only numbers greater than 3
const filtered = numbers.filter(num => num > 3); 
// filtered: [4, 5]

// .reduce() - Accumulates array values into a single result
// Calculates the sum of all numbers
const sum = numbers.reduce((accumulator, current) => accumulator + current, 0); 
// sum: 15

console.log("Mapped:", doubled);
console.log("Filtered:", filtered);
console.log("Reduced Sum:", sum);
```

#### C. Searching & Inspection
Methods used to find specific elements or check for existence.

```javascript
const colors = ["red", "green", "blue", "green"];

// .indexOf() - Returns the first index of a value, or -1 if not found
console.log(colors.indexOf("green")); // 1

// .includes() - Returns true/false if the value exists
console.log(colors.includes("blue")); // true

// .find() - Returns the first element that passes a test
const found = colors.find(color => color.startsWith("b")); 
// found: "blue"
```

> [!WARNING]
> **Mutation Side-Effects:** Be careful when using mutating methods like `.push()` or `.splice()` on arrays that are being used as "state" in frameworks like React. In those environments, you should almost always use non-mutating methods (like the spread operator or `.map()`) to ensure predictable data flow.

[↑ Back to Table of Contents](#table-of-contents)
***

## 11. Spread and Rest Operators

While they look identical in syntax—using the triple-dot (`...`)—the **Spread** and **Rest** operators serve diametrically opposite purposes. The easiest way to distinguish them is by their context: **Spread** "unpacks" an array into individual elements, while **Rest** "packs" multiple elements into a single array.

---

### 11.1 The Spread Operator (`...`)
**Role: Unpacking.**
Spread takes an iterable (like an array or an object) and expands its contents into individual components. This is incredibly useful for copying, merging, and passing arguments.

#### A. Copying and Merging Arrays
Instead of using complex loops, you can use spread to create shallow copies or combine multiple arrays.

```javascript
const basicTask = ["Code", "Test"];
const advancedTask = ["Deploy", "Monitor"];

// Merging arrays
const fullWorkflow = [...basicTask, ...advancedTask]; 
// ["Code", "Test", "Deploy", "Monitor"]

// Creating a shallow copy (Prevents mutating the original)
const original = [1, 2, 3];
const copy = [...original];

copy.push(4);
console.log(original); // [1, 2, 3] (Stays safe!)
console.log(copy);     // [1, 2, 3, 4]
```

#### B. Merging Objects
Spread works on objects too, allowing you to combine properties or overwrite existing ones.

```javascript
const userBase = { name: "Alice", role: "User" };
const userPermissions = { permissions: ["read", "write"] };

// Combining objects
const completeUser = { ...userBase, ...userPermissions, role: "Admin" };

console.log(completeUser);
// { name: "Alice", role: "Admin", permissions: ["read", "write"] }
// Note: 'role' was overwritten by the later property.
```

---

### 11.2 The Rest Operator (`...`)
**Role: Gathering.**
Rest is used in function parameters or destructuring assignments. It "gathers" the remaining parts of an argument list or an object into a single, cohesive array or object.

#### A. Gathering Function Arguments
When you don't know how many arguments will be passed to a function, use Rest to capture them all.

```javascript
// The 'rest' parameter gathers all arguments into an array named 'args'
function sumAll(...args) {
  return args.reduce((total, current) => total + current, 0);
}

console.log(sumAll(1, 2));          // 3
console.log(sumAll(10, 20, 30, 40)); // 100
```

#### B. Destructuring with Rest
Rest is powerful when you want to extract specific pieces of data and group "everything else" together.

```javascript
const settings = {
  theme: "dark",
  fontSize: "16px",
  language: "en",
  timezone: "UTC"
};

// Extract 'theme', and gather everything else into 'otherSettings'
const { theme, ...otherSettings } = settings;

console.log(theme);          // "dark"
console.log(otherSettings);  // { fontSize: "16px", language: "en", timezone: "UTC" }
```

---

### 11.3 Summary Comparison

| Feature | **Spread** (`...`) | **Rest** (`...`) |
| :--- | :--- | :--- |
| **Action** | **Unpacks** elements/properties | **Gathers** elements/properties |
| **Location** | In an array literal, object literal, or function call | In a function parameter list or destructuring pattern |
| **Goal** | To expand/distribute data | To collect/condense data |
| **Visual Aid** | `[...array]` → `element1, element2...` | `[first, ...rest]` → `rest` becomes `[second, third...]` |

> [!IMPORTANT]
> **The "Last Position" Rule:** When using the **Rest** operator in destructuring or function parameters, it **must be the very last element**. You cannot have another variable following a rest parameter (e.g., `function(...args, last) {}` is a `SyntaxError`).

[↑ Back to Table of Contents](#table-of-contents)
***

## 12. Errors

In JavaScript, an **Error** is an object that represents a problem encountered during the execution of a script. Rather than allowing a program to continue in an unstable or unpredictable state, JavaScript uses an **Exception** mechanism to signal that something has gone wrong, allowing the developer to "catch" the error and handle it gracefully.

---

### 12.1 The Error Object Hierarchy

All errors in JavaScript are instances of the built-in `Error` class. This class provides three fundamental properties:
*   **`name`**: The type of error (e.g., `"TypeError"`).
*   **`message`**: A human-readable description of the error.
*   **`stack`**: (Non-standard but widely supported) A trace of the function calls that led to the error.

#### Common Built-in Error Types

| Error Type | Trigger Condition | Example |
| :--- | :--- | :--- |
| **`Error`** | Generic error used for custom logic. | `throw new Error("Something went wrong");` |
| **`SyntaxError`** | Code that breaks the rules of the language. | `const x = ; // Missing value` |
| **`ReferenceError`** | Attempting to access a variable that doesn't exist. | `console.log(nonExistentVar);` |
| **`TypeError`** | Performing an operation on an incompatible type. | `"string".toUpperCase()` is fine, but `null.toUpperCase()` throws this. |
| **`RangeError`** | A numeric value is outside its allowed range. | `new Array(-1);` |

---

### 12.2 Exception Handling: `try...catch...finally`

To prevent a single error from crashing your entire application, you use a `try...catch` block. This creates a "safety net" around risky code.

#### The Workflow
1.  **`try`**: The block of code you want to attempt to run.
2.  **`catch`**: The block that executes **only if** an error occurs inside the `try` block. It receives the error object as an argument.
3.  **`finally`**: A block that executes **regardless** of whether an error occurred or not. This is ideal for "cleanup" tasks (like closing a file or stopping a loading spinner).

```javascript
function processData(data) {
  console.log("Starting process...");

  try {
    // Risky operation: accessing a property on potentially null data
    if (!data) {
      throw new Error("No data provided!"); // Manually throwing an error
    }
    
    console.log(`Processing: ${data.name}`);
    
    if (data.value < 0) {
      throw new RangeError("Value cannot be negative!");
    }

  } catch (error) {
    // Handle the error
    console.error(`❌ ERROR: [${error.name}] ${error.message}`);
  } finally {
    // This always runs, whether the try succeeded or the catch triggered
    console.log("Cleanup: Process attempt complete.\n");
  }
}

// Case 1: Success
processData({ name: "Alice", value: 10 });

// Case 2: Manual Error (No data)
processData(null);

// Case 3: Range Error
processData({ name: "Bob", value: -5 });
```

---

### 12.3 Throwing Custom Errors

You are not limited to the built-in error types. You can use the `throw` keyword to trigger your own errors based on your application's specific business logic.

```javascript
class AuthenticationError extends Error {
  constructor(message) {
    super(message);
    this.name = "AuthenticationError";
  }
}

function login(password) {
  if (password !== "secret123") {
    throw new AuthenticationError("Invalid credentials provided.");
  }
  return "Login successful!";
}

try {
  console.log(login("wrong_password"));
} catch (err) {
  if (err instanceof AuthenticationError) {
    console.error("Security Alert:", err.message);
  } else {
    console.error("General Error:", err.message);
  }
}
```

> [!IMPORTANT]
> **Don't Swallow Errors:** Avoid empty `catch` blocks (e.g., `catch (e) {}`). This is known as "swallowing" an error. It makes debugging nearly impossible because the failure happens silently, leaving the application in an unknown state. Always at least `console.error` the error or log it to a monitoring service.

[↑ Back to Table of Contents](#table-of-contents)
***

## 13. Classes

**Classes** in JavaScript are a syntactic sugar built on top of the language's existing **prototypal inheritance**. While JavaScript remains a prototype-based language under the hood, the `class` syntax provides a much cleaner, more familiar, and more organized way to implement **Object-Oriented Programming (OOP)**.

Classes act as "blueprints" for creating objects, allowing you to group data (properties) and behavior (methods) together into a single, reusable structure.

---

### 13.1 Core Components

A standard class consists of several key elements that define its structure and lifecycle.

| Component | Purpose |
| :--- | :--- |
| **`constructor`** | A special method that runs automatically when a new instance is created. It is used to initialize object properties. |
| **Properties** | Variables attached to the class that hold the state of the object. |
| **Methods** | Functions defined within the class that define the behavior of the objects. |
| **`extends`** | The keyword used to create a **Subclass**, inheriting all properties and methods from a **Parent Class**. |
| **`super()`** | A function called within a subclass constructor to execute the parent class's constructor. |

---

### 13.2 Basic Implementation

Here is a fundamental example of a class defining a blueprint for a `User`.

```javascript
class User {
  // The constructor initializes the unique state of each user
  constructor(name, email) {
    this.name = name;
    this.email = email;
    this.isLoggedIn = false;
  }

  // A method defining behavior
  login() {
    this.isLoggedIn = true;
    console.log(`${this.name} has logged in.`);
  }

  logout() {
    this.isLoggedIn = false;
    console.log(`${this.name} has logged out.`);
  }
}

// Creating "instances" of the User class
const user1 = new User("Alice", "alice@example.com");
const user2 = new User("Bob", "bob@example.com");

user1.login();   // "Alice has logged in."
console.log(user2.isLoggedIn); // false (Bob's state is independent)
```

---

### 13.3 Inheritance: The `extends` and `super` Keywords

Inheritance allows you to create specialized versions of a general class. This follows the **"is-a" relationship** (e.g., a `Dog` *is a* `Animal`).

```javascript
// Parent Class
class Animal {
  constructor(name) {
    this.name = name;
  }

  makeSound() {
    console.log(`${this.name} makes a generic noise.`);
  }
}

// Subclass (Child)
class Dog extends Animal {
  constructor(name, breed) {
    // 1. Call the parent constructor using super()
    // This MUST be called before using 'this' in a subclass
    super(name); 
    
    // 2. Add new properties unique to Dog
    this.breed = breed;
  }

  // Method Overriding: Replacing a parent method with a specific version
  makeSound() {
    console.log(`${this.name} (a ${this.breed}) barks: Woof! Woof!`);
  }
}

const myDog = new Dog("Buddy", "Golden Retriever");
myDog.makeSound(); // "Buddy (a Golden Retriever) barks: Woof! Woof!"
```

---

### 13.4 Getters and Setters

Classes allow you to control how properties are accessed and modified using **Accessors**. This is useful for validation or performing logic whenever a value is read or changed.

```javascript
class BankAccount {
  #balance = 0; // Private field (denoted by #)

  constructor(owner) {
    this.owner = owner;
  }

  // Getter: Allows reading a property like a regular variable
  get balance() {
    return `Your current balance is $${this.#balance}`;
  }

  // Setter: Allows logic to run when a property is assigned
  set balance(amount) {
    if (amount <= 0) {
      console.error("Deposit must be a positive number!");
      return;
    }
    this.#balance += amount;
    console.log(`Successfully deposited $${amount}`);
  }
}

const account = new BankAccount("Charlie");

account.balance = 100;  // Triggers the setter
account.balance = -50;  // Triggers validation error

console.log(account.balance); // Triggers the getter: "Your current balance is $100"
```

> [!IMPORTANT]
> **Private Fields (`#`):** By prefixing a property name with `#`, you make it truly private. It cannot be accessed or modified from outside the class instance, ensuring strict **encapsulation** and preventing accidental state corruption.

[↑ Back to Table of Contents](#table-of-contents)
