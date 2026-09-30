// ------------------------------------------------------------------
// Closures & this: functions and context
// ------------------------------------------------------------------
// This file is loaded by closures-this.html. Open that page in a
// browser, then open the console (F12) to see the output below.
//
// It demonstrates:
//   1. Closures: an inner function that "remembers" its outer variables,
//      even after the outer function has finished running.
//   2. The `this` keyword and its four binding rules: default, implicit,
//      explicit (.call/.apply/.bind), and `new`.
//   3. How arrow functions capture `this` lexically (from their parent).

// ------------------------------------------------------------------
// 1. CLOSURES: a function bundled with its surrounding state
// ------------------------------------------------------------------
console.log('=== 1. CLOSURES ===');

// createCounter returns an object of functions. Each returned function
// "closes over" the `count` variable, so it keeps working even though
// createCounter has already returned. `count` is effectively private.
function createCounter() {
    let count = 0; // private variable, only reachable via the closures

    return {
        increment: () => { 
                count++; 
                return count; 
            },
        decrement: () => { 
                count--; 
                return count; 
            },
        getCount: () => count,
    };
}

const counter = createCounter();
console.log(counter.increment()); // 1
console.log(counter.increment()); // 2
console.log(counter.decrement()); // 1
console.log(counter.getCount());  // 1

// `count` is NOT a property of the returned object, so it can't be
// reached from outside. The closures are the only way in.
console.log(counter.count); // undefined

// Each call to createCounter gets its OWN private `count`.
const secondCounter = createCounter();
console.log(secondCounter.increment()); // 1 (independent of the first)

// ------------------------------------------------------------------
// 2. THE `this` KEYWORD: four binding rules
// ------------------------------------------------------------------
console.log('\n=== 2. THE `this` KEYWORD ===');

// RULE 1 - DEFAULT BINDING: a plain function call.
// In a browser (non-strict), `this` is the global `window` object.
// In strict mode, it is `undefined`.
function showThis() {
    console.log('default this:', this);
}
showThis(); // window (or undefined in strict mode)

// RULE 2 - IMPLICIT BINDING: called as a method of an object.
// `this` becomes the object before the dot.
const user = {
    name: 'Alice',
    greet() {
        console.log('implicit this.name:', this.name);
    },
};
user.greet(); // "Alice"

// RULE 3 - EXPLICIT BINDING: .call(), .apply(), .bind().
// You force `this` to be whatever object you pass in.
function introduce(greeting) {
    console.log(`${greeting}, I am ${this.name}`);
}
const person = { name: 'Bob' };

introduce.call(person, 'Hi');        // "Hi, I am Bob" (args one by one)
introduce.apply(person, ['Hey']);    // "Hey, I am Bob" (args as an array)
const bound = introduce.bind(person); // bind returns a NEW function
bound('Greetings');                  // "Greetings, I am Bob"

// RULE 4 - `new` BINDING: called as a constructor.
// `this` becomes the brand-new object being created.
function Car(make) {
    this.make = make;
}
const myCar = new Car('Tesla');
console.log('new this.make:', myCar.make); // "Tesla"

// ------------------------------------------------------------------
// 3. ARROW FUNCTIONS: lexical `this`
// ------------------------------------------------------------------
console.log('\n=== 3. ARROW FUNCTIONS & LEXICAL `this` ===');

// An arrow function does NOT get its own `this`. It inherits `this`
// from the surrounding scope where it was created.
const obj = {
    name: 'Lexical',
    method() {
        // This arrow inherits `this` from method(), which is `obj`.
        const arrow = () => console.log('arrow this.name:', this.name);
        arrow(); // "Lexical"
    },
};
obj.method();

// The trap: an arrow used as an object METHOD does not bind to the
// object. It inherits `this` from the outer (global) scope instead.
const trap = {
    name: 'Trap',
    greetArrow: () => console.log('arrow method this.name:', this.name),
};
trap.greetArrow(); // undefined (this is the global scope, not `trap`)

// The classic fix: use a regular method, then an arrow INSIDE it for
// callbacks, so `this` stays correct. (setInterval is commented out so
// this page doesn't run forever.)
// const timer = {
//     seconds: 0,
//     start() {
//         setInterval(() => {
//             this.seconds++; // `this` is `timer`, thanks to the arrow
//             console.log(this.seconds);
//         }, 1000);
//     },
// };
// timer.start();
