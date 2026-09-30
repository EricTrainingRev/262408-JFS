// ------------------------------------------------------------------
// Scope Explorer: scopes, hoisting & the TDZ
// ------------------------------------------------------------------
// This file is loaded by scope-explorer.html. Open that page in a
// browser, then open the console (F12) to see the output below.
//
// It demonstrates:
//   1. The three kinds of scope: global, function, and block.
//   2. How `var` ignores block boundaries (and leaks out of them).
//   3. Hoisting: why some things work before they are "defined".
//   4. The Temporal Dead Zone (TDZ) that protects `let` and `const`.

// ------------------------------------------------------------------
// 1. THE THREE SCOPES
// ------------------------------------------------------------------
console.log('=== 1. THE THREE SCOPES ===');

// GLOBAL scope: declared outside any function or block.
const globalVar = 'I am global';

function scopeDemo() {
    // FUNCTION scope: only visible inside this function.
    const functionVar = 'I am function-scoped';

    if (true) {
        // BLOCK scope: only visible inside these { } braces.
        let blockVar = 'I am block-scoped';
        console.log('Inside the block:', blockVar); // works
        console.log("Inside the block:", functionVar);
        console.log("Inside the block:", globalVar);
    }

    console.log('Inside the function:', functionVar); // works
    // console.log(blockVar); // ReferenceError: blockVar is not defined
}

scopeDemo();
console.log('At the top level:', globalVar); // works
// console.log(functionVar); // ReferenceError: functionVar is not defined

// ------------------------------------------------------------------
// 2. THE `var` PITFALL: var ignores block boundaries
// ------------------------------------------------------------------
console.log('\n=== 2. THE `var` PITFALL ===');

if (true) {
    var leakedVar = 'I leaked out of the block!'; // var, not let/const
    let trappedVar = 'I am safely contained.';
}

console.log(leakedVar);  // "I leaked out of the block!" (unexpected!)
// console.log(trappedVar); // ReferenceError: trappedVar is not defined

// ------------------------------------------------------------------
// 3. HOISTING: what works before it is "defined"
// ------------------------------------------------------------------
console.log('\n=== 3. HOISTING ===');

// A function DECLARATION is fully hoisted, so you can call it early.
greet(); // "Hello!" (works, even though greet is defined below)

function greet() {
    console.log('Hello!');
}

// A `var` is hoisted but initialized to `undefined`.
console.log(myVar); // undefined (no error, but not the value yet)
var myVar = 'I am a var';

// A function expression is NOT hoisted like a declaration.
// const sayHi = function () { console.log('Hi!'); };
// sayHi(); // would work AFTER the assignment, but not before

// ------------------------------------------------------------------
// 4. THE TEMPORAL DEAD ZONE (TDZ)
// ------------------------------------------------------------------
console.log('\n=== 4. THE TEMPORAL DEAD ZONE (TDZ) ===');

// `let` and `const` are hoisted but NOT initialized. Between the start
// of the scope and the declaration line, they are in the "dead zone".
// Accessing them there throws a ReferenceError. We catch it here so the
// script keeps running and you can see the error message.
try {
    console.log(myLet); // ReferenceError: Cannot access 'myLet' before initialization
} catch (err) {
    console.log('TDZ error:', err.message);
}
let myLet = 'I am a let';
