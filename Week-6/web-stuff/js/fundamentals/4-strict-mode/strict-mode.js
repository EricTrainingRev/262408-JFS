// ------------------------------------------------------------------
// Strict mode: sloppy vs. strict
// ------------------------------------------------------------------
// This file is loaded by strict-mode.html. Open that page in a browser,
// then open the console (F12) to see the output below.
//
// "use strict" opts into a stricter, safer variant of JavaScript. It
// turns silent failures into loud errors. Because the directive applies
// to the WHOLE file, this demo uses function-level strict mode: a sloppy
// function and a strict function side by side, so you can see the
// contrast in one script.

// ------------------------------------------------------------------
// 1. ASSIGNING TO AN UNDECLARED VARIABLE
// ------------------------------------------------------------------
console.log('=== 1. UNDECLARED VARIABLE ===');

// SLOPPY: assigning to a variable you never declared silently creates a
// global variable. This can pollute the global scope by accident.
function sloppyAssign() {
    leakedVar = 'I became a global!'; // no `let`, `const`, or `var`
}
sloppyAssign();
console.log('sloppy: leakedVar =', leakedVar); // "I became a global!"

// STRICT: the same mistake throws a ReferenceError instead.
function strictAssign() {
    'use strict';
    leakedVar2 = 'oops'; // no declaration -> ReferenceError
}
try {
    strictAssign();
} catch (err) {
    console.log('strict: ReferenceError ->', err.message);
}

// ------------------------------------------------------------------
// 2. `this` IN A PLAIN FUNCTION CALL
// ------------------------------------------------------------------
console.log('\n=== 2. `this` IN A PLAIN CALL ===');

// SLOPPY: a plain function call sets `this` to the global object
// (window in a browser, global in Node).
function sloppyThis() {
    console.log('sloppy this:', this);
}
sloppyThis(); // the global object

// STRICT: `this` stays undefined, which is usually what you want.
function strictThis() {
    'use strict';
    console.log('strict this:', this);
}
strictThis(); // undefined

// ------------------------------------------------------------------
// 3. ASSIGNING TO A READ-ONLY PROPERTY
// ------------------------------------------------------------------
console.log('\n=== 3. READ-ONLY PROPERTY ===');

// Object.freeze makes an object's properties read-only.
const frozen = Object.freeze({ name: 'Alice' });

// SLOPPY: the assignment fails silently; the value does not change.
function sloppyFreeze() {
    frozen.name = 'Bob';
}
sloppyFreeze();
console.log('sloppy: frozen.name =', frozen.name); // still "Alice" (silent)

// STRICT: the same assignment throws a TypeError.
function strictFreeze() {
    'use strict';
    frozen.name = 'Bob'; // TypeError: Cannot assign to read only property
}
try {
    strictFreeze();
} catch (err) {
    console.log('strict: TypeError ->', err.message);
}

// ------------------------------------------------------------------
// 4. DUPLICATE PARAMETER NAMES
// ------------------------------------------------------------------
console.log('\n=== 4. DUPLICATE PARAMETER NAMES ===');

// SLOPPY: duplicate parameter names are allowed; the last one wins.
function sloppyDup(a, a) {
    console.log('sloppy: a =', a);
}
sloppyDup(1, 2); // 2 (the second value wins)

// STRICT: duplicate parameter names are a SyntaxError, so the function
// can't even be defined. It is shown as a comment, not run:
// function strictDup(a, a) {
//     'use strict';
//     return a;
// }
// SyntaxError: Duplicate parameter name not allowed in this context
