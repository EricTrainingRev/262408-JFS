# TypeScript Compiler

## Table of Contents
* [1. High-Level Overview](#1-high-level-overview)
* [2. The TypeScript Compiler (tsc)](#2-the-typescript-compiler-tsc)
* [3. Configuration: tsconfig.json](#3-configuration-tsconfigjson)
    * [3.1 Compiler Options](#31-compiler-options)
    * [3.2 Strict Mode](#32-strict-mode)
    * [3.3 Target](#33-target)
    * [3.4 Example tsconfig.json](#34-example-tsconfigjson)
* [4. Usage & Workflow](#4-usage--workflow)

***

## 1. High-Level Overview
TypeScript is a superset of JavaScript that adds **Static Typing**. Because browsers cannot execute TypeScript directly, the **TypeScript Compiler (tsc)** is the essential engine that transforms `.ts` files into valid `.js` files. This process, known as **Transpilation**, allows developers to catch type errors during development rather than at runtime.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we understand the high-level purpose of the compiler, let's look at the tool itself and how it is invoked.*

## 2. The TypeScript Compiler (tsc)

The **`tsc`** command is the primary interface for the TypeScript compiler. It is responsible for checking the type safety of your code and generating the corresponding JavaScript output.

### 2.1 Basic Execution
You can use `tsc` in two main ways:

**1. Single File Compilation**
To compile a **specific file**:
```bash
tsc filename.ts
```
This will generate a `filename.js` in the same directory.

**2. Project-wide Compilation (Recommended)**
In **professional projects**, you rarely compile files one by one. Instead, you use a **configuration file** to compile the entire project at once:
```bash
tsc
```
When run without arguments, `tsc` automatically looks for a `tsconfig.json` file in the current directory and applies the rules defined within it.

[↑ Back to Table of Contents](#table-of-contents)
***

*While `tsc` provides the engine, the `tsconfig.json` file provides the instructions. Understanding how to configure this file is critical for controlling how your code is transformed and checked.*

## 3. Configuration: tsconfig.json

The **`tsconfig.json`** file is the heart of a TypeScript project. It defines the root files and the specific rules the compiler must follow.

### 3.1 Compiler Options
The `compilerOptions` object is the most significant part of the configuration. It dictates how the compiler behaves, how files are found, and what the output looks like.

| Category | Purpose | Common Examples |
| :--- | :--- | :--- |
| **Output Control** | Defines where and how code is written. | `outDir`, `rootDir` |
| **Module System** | Defines how imports/exports are handled. | `module` (CommonJS, ESNext) |
| **Interoperability** | Helps TS work with older JS patterns. | `allowJs`, `esModuleInterop` |
| **Type Checking** | Defines the "strictness" of the engine. | `strict`, `noImplicitAny` |

### 3.2 Strict Mode
**Strict Mode** is a collection of type-checking flags that enable the most rigorous verification. It is highly recommended to keep `strict: true` enabled in all new projects to prevent "type leakage" and common runtime bugs.

**`strict: true`** is a **shorthand** that enables several underlying flags, including:

*   **`noImplicitAny`**: Errors if a variable's type cannot be inferred and isn't explicitly defined (preventing the hidden use of `any`).
*   **`strictNullChecks`**: Forces you to explicitly handle `null` and `undefined` values, preventing the dreaded "Cannot read property of undefined" errors.
*   **`strictFunctionTypes`**: Ensures function parameters are checked more accurately.

> [!IMPORTANT]
> **Pro-Tip:** While `strict: true` can be frustrating when migrating old JavaScript projects to TypeScript, it is the single most effective way to leverage the full power of the type system.

### 3.3 Target
The **`target`** option determines the **version of JavaScript** that the compiler produces. This is a crucial decision based on the **environments** (browsers or Node.js versions) where your code will actually run.

| Target | Resulting JS Version | Compatibility |
| :--- | :--- | :--- |
| **ES3 / ES5** | Very old JS syntax | Works in nearly all legacy browsers (IE11). |
| **ES6 (ES2015)** | Modern JS syntax | Supported by all modern browsers and Node.js. |
| **ESNext** | The absolute latest JS features | Best for modern environments. |

**Example:**
If you set `"target": "ES5"`, the compiler will convert modern features like arrow functions (`() => {}`) into standard function expressions (`function() {}`) to ensure compatibility.

### 3.4 Example `tsconfig.json`

The following is a representative configuration for a modern web application.

```json
{
  "compilerOptions": {
    /* Output Settings */
    "target": "ES2020",                // Compile to ES2020 syntax
    "module": "ESNext",               // Use modern ES Modules
    "outDir": "./dist",               // Place compiled JS in the 'dist' folder
    "rootDir": "./src",               // Look for source files in 'src'

    /* Strictness & Safety */
    "strict": true,                   // Enable all strict type-checking options
    "noImplicitAny": true,            // Error on variables with implicit 'any'
    "strictNullChecks": true,         // Ensure null/undefined are handled

    /* Interoperability */
    "esModuleInterop": true,          // Better support for importing CommonJS modules
    "skipLibCheck": true,             // Speed up compilation by skipping library checks

    /* Module Resolution */
    "moduleResolution": "node",       // Use Node.js style module resolution
    "baseUrl": "./",                  // Base directory for non-relative imports
    "paths": {                        // Path mapping for cleaner imports
      "@/*": ["src/*"]
    }
  },
  "include": ["src/**/*"],            // Only include files in the src directory
  "exclude": ["node_modules", "**/*.spec.ts"] // Ignore dependencies and test files
}
```

[↑ Back to Table of Contents](#table-of-contents)
***

*With the configuration understood, we can now wrap up with how these pieces fit into a standard development workflow.*

## 4. Usage & Workflow

A standard TypeScript development lifecycle typically follows these steps:

1.  **Initialization:** Run `tsc --init` to generate a default `tsconfig.json`.
2.  **Development:** Write `.ts` code, relying on the **IDE** (like **VS Code**) to provide real-time feedback based on your `tsconfig.json`.
3.  **Compilation:** Run `tsc` (or use a **watcher** with `tsc -w`) to transform code into `.js`.
4.  **Execution:** Run the resulting `.js` files using a **runtime** like `node`.

**The Watch Mode (`-w`)**
During active development, running `tsc` manually after every change is tedious. The **Watch Mode** automates this:
```bash
tsc --watch
```
In this mode, the compiler stays active and re-compiles your files automatically every time you save a change.

[↑ Back to Table of Contents](#table-of-contents)
