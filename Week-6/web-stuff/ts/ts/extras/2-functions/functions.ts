// =====================================================================
// FUNCTIONS — the TYPE layer on top of function behavior.
//
// The JS fundamentals already cover what functions do; this example is
// about the TYPES: how you annotate parameters and return values, how a
// function itself is a type you can assign, and the special parameter
// forms (optional, default, rest). All of it is compile-time — erased
// before the JS runs.
//
// Run it:
//   node dist/2-functions/functions.js   (after `npm run build`)
// =====================================================================

// === 1. ANNOTATING PARAMETERS & RETURN ===============================
// Each parameter gets a type after its name; the return type comes after
// the `)`. Under `strict`, omitting a parameter type is an error
// (implicit `any`), so annotate them.
function add(a: number, b: number): number {
  return a + b;
}
console.log(add(2, 3)); // 5

// A function that returns nothing uses `void` as its return type.
function logMessage(message: string): void {
  console.log(message); // hello
}
logMessage("hello");

// === 2. OPTIONAL & DEFAULT PARAMETERS ================================
// A `?` makes a parameter optional — it may be omitted. Under
// strictNullChecks its type becomes `T | undefined`.
function greet(name: string, greeting?: string): string {
  // `greeting` is `string | undefined` here, so we must handle the gap.
  return greeting ? `${greeting}, ${name}!` : `Hello, ${name}!`;
}
console.log(greet("Ada"));            // Hello, Ada!
console.log(greet("Ada", "Good day")); // Good day, Ada!

// A DEFAULT value is different from optional: the parameter is always
// present, just with a fallback when the caller omits it.
function repeat(text: string, times: number = 2): string {
  return text.repeat(times);
}
console.log(repeat("ab"));     // abab
console.log(repeat("ab", 3));  // ababab

// === 3. REST PARAMETERS ==============================================
// `...rest: T[]` collects any number of trailing arguments into an array.
function sumAll(...nums: number[]): number {
  return nums.reduce((total, n) => total + n, 0);
}
console.log(sumAll(1, 2, 3));    // 6
console.log(sumAll(10, 20));     // 30

// === 4. A FUNCTION IS A TYPE =========================================
// You can name a function's shape with a type alias, then assign any
// matching function to it. This is how callbacks and handlers are typed.
type BinaryOp = (a: number, b: number) => number;

const multiply: BinaryOp = (a, b) => a * b; // params inferred from the type
console.log(multiply(4, 5)); // 20

// The same shape can be reused for a different implementation.
const divide: BinaryOp = (a, b) => a / b;
console.log(divide(10, 2)); // 5

// === 5. PASSING A FUNCTION (CALLBACK) ================================
// A function that takes another function as an argument is a higher-order
// function. The callback's type is spelled out in the parameter list.
type TransformFunction = (n: number) => number;
function applyTwice(value: number, transform: TransformFunction): number {
  return transform(transform(value));
}
console.log(applyTwice(3, (n) => n + 1)); // 5  (3 -> 4 -> 5)
console.log(applyTwice(2, (n) => n * n)); // 16 (2 -> 4 -> 16)

// =====================================================================
// WHAT TO REMEMBER
// - Annotate each parameter and the return type; `void` = returns nothing.
// - `?` = optional (type becomes `T | undefined`); `= default` = fallback.
// - `...rest: T[]` collects trailing args into an array.
// - A function's shape is a type: `(a: number, b: number) => number`.
// - Higher-order functions type their callback in the parameter list.
// =====================================================================

export {};
