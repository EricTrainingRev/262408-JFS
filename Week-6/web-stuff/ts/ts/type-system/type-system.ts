// =====================================================================
// TYPE-SYSTEM — the building blocks of TypeScript's static types.
//
// TypeScript adds TYPES on top of JavaScript. Types are a compile-time
// concept: they are used to check your code, then erased when the file
// is compiled to JavaScript — the runtime sees only plain JS.
//
// Run this one two ways and compare — they print the same thing, which
// proves the types are gone at runtime:
//   npm run build        → compiles every .ts under /ts to /dist
//   node dist/type-system/type-system.js
// =====================================================================

// === 1. PRIMITIVE TYPES ==============================================
// The simplest types: string, number, boolean, array.
// You can write the annotation after a colon (`: type`), as below.

let userName: string = "Billy";      // string: text
let age: number = 25;                 // number: integers and decimals treated the same
let isReady: boolean = true;          // boolean: true or false
let scores: number[] = [10, 20, 30];  // number[]: an array of numbers

console.log(userName);                // Billy
console.log(age);                     // 25
console.log(isReady);                 // true
console.log(scores.length);           // 3

// userName = 1000; // this will cause a compiler error since 1000 is not a string

// You usually DON'T have to write the type — TypeScript infers it from
// the value (type inference). `const pageCount = 5` is a number with no
// annotation needed. Annotations are there for when inference isn't
// enough, like complex shapes where you want to be explicit.

// === 2. SPECIAL TYPES: void, null, undefined =========================
// `void` is the return type of a function that returns nothing.
function logVersion(): void {
  console.log("v1.0.0"); // v1.0.0
}
logVersion();

// `null` / `undefined` represent "no value". Under strictNullChecks they
// are NOT automatically allowed where a number is expected — you must ask
// for them explicitly with a union (see section 4).

// === 3. any vs unknown ===============================================
// `any` opts out of type checking: the value can be anything, and you can
// do anything with it — no errors until the code runs (and crashes).
let loose: any = 5;
loose = "now a string";           // any lets you change the type freely
console.log(loose.toUpperCase()); // now a string, was a number before

// `unknown` is the safe version of `any`: it also accepts anything, but
// you can't use the value until you prove what it actually is.
let safeValue: unknown = 42;
// safeValue.toFixed(2); // ❌ compile error: "Object is of type 'unknown'"

// Narrow it with a `typeof` check (a type guard), and unknown becomes usable.
if (typeof safeValue === "number") {
  console.log(safeValue.toFixed(2)); // 42.00
}

// Rule of thumb: avoid `any`. If you don't know the type yet,
// reach for `unknown` and narrow it before using the value.

// === 4. UNION TYPES + NARROWING ======================================
// A union `A | B` means "either A or B". Combine with a type guard to
// shrink the type to the specific branch you're in.
function format(input: string | number): string {
  if (typeof input === "number") {
    return input.toFixed(1);       // here TS knows input is a number
  }
  return input.toUpperCase();      // here TS knows input is a string
}

console.log(format(3.14159));      // 3.1
console.log(format("hello"));      // HELLO

// Unions are how you handle nullable data under strictNullChecks:
// `string | undefined` = "a string, or nothing".

// === 5. OBJECT TYPES =================================================
// You can describe an object's shape inline with a type annotation.
let user: { name: string; id: number } = { name: "Alice", id: 1 };
console.log(user.name);            // Alice
console.log(user.id);              // 1

// This inline shape works, but it gets verbose once reused. That is why
// TypeScript gives you reusable names for shapes — type aliases and
// interfaces.

// =====================================================================
// WHAT TO REMEMBER
// - Core primitives: string, number, boolean, array (T[]).
// - void = "returns nothing"; null/undefined must be handled explicitly.
// - any = escape hatch (avoid); unknown = safe unknown (narrow first).
// - Union (A | B) + typeof-narrowing lets you handle mixed/optional data.
// - Types are a compile-time layer — erased before the JS actually runs.
// =====================================================================

export {};
