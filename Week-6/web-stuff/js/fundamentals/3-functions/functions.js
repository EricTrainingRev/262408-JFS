// ------------------------------------------------------------------
// Functions: the many ways to make one
// ------------------------------------------------------------------
// This file is loaded by functions.html. Open that page in a browser,
// then open the console (F12) to see the output below.
//
// It demonstrates the different ways to create a function in JavaScript:
//   1. Function declarations
//   2. Function expressions
//   3. Arrow functions (and their shorthand forms)
//   4. Default parameters
//   5. Object methods (shorthand)
//   6. Immediately Invoked Function Expressions (IIFEs)
//   7. Functions as values: callbacks and higher-order functions

// ------------------------------------------------------------------
// 1. FUNCTION DECLARATION
// ------------------------------------------------------------------
console.log('=== 1. FUNCTION DECLARATION ===');

// The classic form: `function` keyword, a name, and a body.
// A declaration is HOISTED, so you can call it before it appears.
function add(a, b) {
    return a + b;
}
console.log('add(2, 3):', add(2, 3)); // 5

// Hoisting in action: this call works even though greet is defined below.
greet(); // "Hello from a declaration!"
function greet() {
    console.log('Hello from a declaration!');
}

// ------------------------------------------------------------------
// 2. FUNCTION EXPRESSION
// ------------------------------------------------------------------
console.log('\n=== 2. FUNCTION EXPRESSION ===');

// A function stored in a variable. It is NOT hoisted, so you must
// define it before you call it.
const multiply = function (a, b) {
    return a * b;
};
console.log('multiply(4, 5):', multiply(4, 5)); // 20

// A NAMED function expression: the name is only visible inside itself.
// This is handy for recursion or clearer stack traces.
const factorial = function fact(n) {
    return n <= 1 ? 1 : n * fact(n - 1);
};
console.log('factorial(5):', factorial(5)); // 120

// ------------------------------------------------------------------
// 3. ARROW FUNCTIONS
// ------------------------------------------------------------------
console.log('\n=== 3. ARROW FUNCTIONS ===');

// The most concise form. `=>` replaces the `function` keyword.
// With a single expression body, the result is returned implicitly.
const square = (x) => x * x;
console.log('square(6):', square(6)); // 36

// One parameter: the parentheses are optional.
const double = x => x * 2;
console.log('double(7):', double(7)); // 14

// Multiple parameters need parentheses.
const addArrow = (a, b) => a + b;
console.log('addArrow(1, 2):', addArrow(1, 2)); // 3

// A block body needs braces AND an explicit `return`.
const describe = (name, age) => {
    const greeting = `Hi, I am ${name}`;
    return `${greeting} and I am ${age} years old.`;
};
console.log(describe('Alice', 30)); // "Hi, I am Alice and I am 30 years old."

// ------------------------------------------------------------------
// 4. DEFAULT PARAMETERS
// ------------------------------------------------------------------
console.log('\n=== 4. DEFAULT PARAMETERS ===');

// If an argument is missing (undefined), the default value is used.
function createUser(name, role = 'Guest', status = 'Active') {
    console.log(`User: ${name}, Role: ${role}, Status: ${status}`);
}
createUser('Alice');              // Role: Guest, Status: Active
createUser('Bob', 'Admin');       // Role: Admin, Status: Active
createUser('Charlie', undefined); // Role: Guest (undefined triggers the default)

// ------------------------------------------------------------------
// 5. OBJECT METHODS (SHORTHAND)
// ------------------------------------------------------------------
console.log('\n=== 5. OBJECT METHODS ===');

// Inside an object literal, you can write a method without the
// `function` keyword. `this` refers to the object.
const user = {
    name: 'Alice',
    greet() {
        console.log(`Hi, I am ${this.name}`);
    },
};
user.greet(); // "Hi, I am Alice"

// ------------------------------------------------------------------
// 6. IIFE (IMMEDIATELY INVOKED FUNCTION EXPRESSION)
// ------------------------------------------------------------------
console.log('\n=== 6. IIFE ===');

// A function that runs the moment it is created. Wrapping it in
// parentheses turns it into an expression, then () calls it right away.
// IIFEs are a classic way to create a private scope.
(function () {
    const secret = 'only visible inside this IIFE';
    console.log('IIFE ran. secret =', secret);
})();
// console.log(secret); // ReferenceError: secret is not defined

// ------------------------------------------------------------------
// 7. FUNCTIONS AS VALUES: CALLBACKS & HIGHER-ORDER FUNCTIONS
// ------------------------------------------------------------------
console.log('\n=== 7. FUNCTIONS AS VALUES ===');

// Functions are "first-class citizens": you can pass them around like
// any other value. A function passed to another function is a CALLBACK.

// .map() takes a callback and calls it for every element.
const numbers = [1, 2, 3, 4];
const doubled = numbers.map(n => n * 2);
console.log('map with arrow callback:', doubled); // [2, 4, 6, 8]

// A named function can be used as a callback too.
function isEven(n) {
    return n % 2 === 0;
}
console.log('filter with named callback:', numbers.filter(isEven)); // [2, 4]

// A HIGHER-ORDER FUNCTION returns a function. This is how you build
// reusable, configurable behavior.
function makeMultiplier(factor) {
    return (n) => n * factor; // returns a new function
}
const triple = makeMultiplier(3);
console.log('triple(5):', triple(5)); // 15
