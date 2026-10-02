# TypeScript Basics

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. The TypeScript Ecosystem](#2-the-typescript-ecosystem)
    * [2.1 What is TypeScript?](#21-what-is-typescript)
    * [2.2 The Compiler (tsc) & Configuration (tsconfig.json)](#22-the-compiler-tsc--configuration-tsconfigjson)
* [3. The Type System: Building Blocks](#3-the-type-system-building-blocks)
    * [3.1 Simple or Variable Types](#31-simple-or-variable-types)
    * [3.2 Special Types](#32-special-types)
    * [3.3 Union Types](#33-union-types)
* [4. Complex Data Structures](#4-complex-data-structures)
    * [4.1 Object Types](#41-object-types)
    * [4.2 Type Aliases](#42-type-aliases)
    * [4.3 Interfaces](#43-interfaces)

***

## 1. High-Level Overview

**TypeScript** is a strongly typed programming language that builds on JavaScript, giving you better tooling at any scale. It is a **superset** of JavaScript, meaning all valid JavaScript is also valid TypeScript. Instead of running directly in the browser, TypeScript code must be **transpiled** into JavaScript via the **TypeScript Compiler (tsc)**.

This module covers the fundamental building blocks of the TypeScript type system, moving from simple primitives to complex structural definitions.

[↑ Back to Table of Contents](#table-of-contents)
***

## 2. The TypeScript Ecosystem

Before writing code, you must understand the environment that turns your typed code into executable JavaScript.

### 2.1 What is TypeScript?

While JavaScript is **dynamically typed** (types are checked at runtime), TypeScript is **statically typed** (types are checked at compile-time). This allows you to catch errors during development rather than in production.

### 2.2 The Compiler (tsc) & Configuration (tsconfig.json)

To use TypeScript, you interact with the **TypeScript Compiler (`tsc`)**.

| Component | Purpose | Key Functionality |
| :--- | :--- | :--- |
| **`tsc` (CLI)** | The command-line tool. | Compiles `.ts` files to `.js` files. |
| **`tsconfig.json`** | The project's "brain." | Defines how the compiler should behave (target JS version, strictness, paths). |

**`tsconfig.json` vs. Manual Compilation**
Instead of running `tsc file.ts` for every single file, you create a `tsconfig.json` in your project root. Once created, running `tsc` (with no arguments) tells the compiler: *"Look at my config, find all files in my project, and compile them according to my rules."*

> [!TIP]
> **`strict: true`** is the most important setting in your `tsconfig.json`. It enables a suite of type-checking behaviors that make TypeScript truly powerful by preventing "silent" type errors.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we understand how the environment is set up, let's dive into the actual atoms of the language: the basic types.*

## 3. The Type System: Building Blocks

### 3.1 Simple or Variable Types

TypeScript provides several **primitive types** that represent the simplest forms of data.

| Type | Description | Example |
| :--- | :--- | :--- |
| `string` | Textual data. | `let name: string = "Hermes";` |
| `number` | All numeric values (integers and floats). | `let age: number = 25;` |
| `boolean` | True or false values. | `let isReady: boolean = true;` |
| `array` | A collection of elements of the same type. | `let scores: number[] = [10, 20, 30];` |

### 3.2 Special Types

Beyond primitives, TypeScript includes special types to handle more nuanced scenarios, such as "nothingness" or "anything."

| Type | Description | When to Use |
| :--- | :--- | :--- |
| `any` | Opt-out of type checking. | **Avoid this.** Use only when migrating JS or dealing with unknown external data. |
| `unknown` | The "safe" version of `any`. | When you don't know the type yet, but want to force a type check before using it. |
| `void` | Represents the absence of a value. | Typically used as the return type of functions that do not return anything. |
| `null` / `undefined` | Represent empty or uninitialized values. | Used to explicitly indicate the absence of an object or value. |

**`any` vs. `unknown`**
The difference is **safety**. 
- With `any`, you can do anything: `let x: any = 5; x.foo();` (This crashes at runtime).
- With `unknown`, you **cannot** do anything until you prove what it is:
  ```typescript
  let x: unknown = 5;
  // x.foo(); // ❌ Error: Object is of type 'unknown'
  
  if (typeof x === "number") {
    console.log(x.toFixed()); // ✅ OK: TypeScript knows it's a number here
  }
  ```

### 3.3 Union Types

*While single types are useful, real-world logic often requires combining them. This leads us to Union Types.*

A **Union Type** lets a value be one of several possible types, using the `|` (pipe) operator.

```typescript
let id: string | number;
id = "abc123"; // ✅ OK
id = 42; // ✅ OK
// id = true; // ❌ Error: Type 'boolean' is not assignable to type 'string | number'
```

TypeScript only lets you use operations that are valid for **every** member of the union, so you typically **narrow** the type with `typeof` before using it:

```typescript
function format(input: string | number) {
  if (typeof input === "string") {
    return input.toUpperCase(); // ✅ OK: narrowed to string
  }
  return input.toFixed(2); // ✅ OK: narrowed to number
}
```

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we understand the type system's building blocks, let's look at how to model more complex, structured data.*

## 4. Complex Data Structures

### 4.1 Object Types

In TypeScript, you can define the shape of an object directly.

```typescript
// Inline object type
let user: { name: string; id: number } = {
  name: "Alice",
  id: 1
};
```

### 4.2 Type Aliases

A **Type Alias** allows you to give a name to a type definition, making it reusable and much easier to read.

```typescript
type User = {
  name: string;
  id: number;
  email?: string; // The '?' makes this property optional
};

const admin: User = { name: "Bob", id: 2 };
```

### 4.3 Interfaces

**Interfaces** are similar to Type Aliases but are specifically designed to define the "shape" of objects and are extendable.

| Feature | Type Alias | Interface |
| :--- | :--- | :--- |
| **Primary Use** | General-purpose (unions, primitives, objects). | Defining object and class structures. |
| **Extensibility** | Uses **intersection** (`&`) to combine. | Uses `extends` keyword. |
| **Declaration Merging** | Cannot be redeclared. | **Can** be redeclared to add new properties (Merging). |

**Interface vs. Type Alias**
Use **Interfaces** when you are defining the structure of a public API or a class (where you want the ability for others to extend your definition). Use **Type Aliases** for everything else, especially when you need to define Union types or complex combinations.

**Interface Extension Example:**
```typescript
interface Animal {
  name: string;
}

interface Dog extends Animal {
  breed: string;
}

const myDog: Dog = { name: "Rex", breed: "Labrador" };
```

[↑ Back to Table of Contents](#table-of-contents)
