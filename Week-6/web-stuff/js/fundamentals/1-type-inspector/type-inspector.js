// ------------------------------------------------------------------
// Type Inspector: datatypes & coercion
// ------------------------------------------------------------------
// This file is loaded by type-inspector.html. Open that page in a
// browser, then open the console (F12) to see the output below.
//
// It demonstrates two ideas:
//   1. Every value has a TYPE, and `typeof` tells you what it is.
//   2. JavaScript COERCES (converts) values automatically in some
//      operations, which can produce surprising results.

// ------------------------------------------------------------------
// 1. DATATYPES: every value has a type, and `typeof` reveals it.
// ------------------------------------------------------------------
console.log('=== 1. DATATYPES ===');

// Primitives: single, immutable values.
console.log(typeof 'hello');   // "string"
console.log(typeof 42);        // "number"
console.log(typeof 3.14);      // "number" (no separate float type)
console.log(typeof true);      // "boolean"
console.log(typeof undefined); // "undefined"
console.log(typeof null);      // "object"  <-- a famous JS quirk!

// Objects: collections of key/value pairs (arrays and functions too).
console.log(typeof { name: 'Alice' }); // "object"
console.log(typeof [1, 2, 3]);         // "object" (arrays are objects)
console.log(typeof function () {});    // "function"

// ------------------------------------------------------------------
// 2. EXPLICIT COERCION: the developer asks for a specific type.
// ------------------------------------------------------------------
console.log('\n=== 2. EXPLICIT COERCION ===');

// Number() tries to turn a value into a number.
console.log(Number('42'));   // 42
console.log(Number('hello')); // NaN (Not a Number)
console.log(Number(true));   // 1
console.log(Number(null));   // 0

// String() turns a value into a string.
console.log(String(42));     // "42"
console.log(String(true));   // "true"

// Boolean() turns a value into true or false.
console.log(Boolean(1));     // true
console.log(Boolean(0));     // false
console.log(Boolean(''));    // false (empty string is "falsy")
console.log(Boolean('hi'));  // true

// ------------------------------------------------------------------
// 3. IMPLICIT COERCION: JavaScript converts values on its own.
// ------------------------------------------------------------------
console.log('\n=== 3. IMPLICIT COERCION ===');

// The + operator prefers strings, so it CONCATENATES.
console.log('5' + 5);   // "55"  (number coerced to string)
console.log('The answer is ' + 42); // "The answer is 42"

// The - operator only works on numbers, so it converts to a number.
console.log('10' - 5);  // 5  (string coerced to number)

// Loose equality (==) converts both sides before comparing.
console.log(5 == '5');  // true  (both become numbers)
// Strict equality (===) does NOT convert; it compares type too.
console.log(5 === '5'); // false (different types)

// A classic gotcha: an empty array coerces to the string "".
console.log([] + 1);    // "1"  (array -> "" -> concatenation)

// ------------------------------------------------------------------
// The 7 primitive types in JavaScript:
//   1. string   - textual data                 (typeof -> "string")
//   2. number   - integers & floats            (typeof -> "number")
//   3. boolean  - true / false                 (typeof -> "boolean")
//   4. undefined- declared but not assigned    (typeof -> "undefined")
//   5. null     - intentional absence          (typeof -> "object"  <-- quirk!)
//   6. symbol   - unique, immutable id         (typeof -> "symbol")
//   7. bigint   - integers beyond Number       (typeof -> "bigint")
// ------------------------------------------------------------------
