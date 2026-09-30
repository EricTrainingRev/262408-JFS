// ------------------------------------------------------------------
// Errors: try/catch, throw & custom errors
// ------------------------------------------------------------------
// This file is loaded by errors.html. Open that page in a browser,
// then open the console (F12) to see the output below.
//
// It demonstrates:
//   1. try/catch/finally: a "safety net" around risky code.
//   2. throw: raising your own errors based on business logic.
//   3. Custom error classes that extend the built-in Error.
//   4. The common built-in error types (ReferenceError, TypeError, etc.).

// ------------------------------------------------------------------
// 1. try / catch / finally
// ------------------------------------------------------------------
console.log('=== 1. try / catch / finally ===');

// try runs the risky code. catch runs only if an error is thrown.
// finally ALWAYS runs, whether there was an error or not (great for cleanup).
function processData(data) {
    console.log('Starting process...');

    try {
        if (!data) {
            throw new Error('No data provided!'); // manually throw
        }
        console.log(`Processing: ${data.name}`);

        if (data.value < 0) {
            throw new RangeError('Value cannot be negative!');
        }
    } catch (error) {
        console.log(`Caught: [${error.name}] ${error.message}`);
    } finally {
        console.log('Cleanup: process attempt complete.\n');
    }
}

processData({ name: 'Alice', value: 10 }); // success
processData(null);                        // manual Error
processData({ name: 'Bob', value: -5 });  // RangeError

// ------------------------------------------------------------------
// 2. throw: raising your own errors
// ------------------------------------------------------------------
console.log('=== 2. throw ===');

// You can throw any value, but throwing an Error object is best practice
// because it carries a name, a message, and a stack trace.
function checkAge(age) {
    if (age < 0) {
        throw new Error('Age cannot be negative.');
    }
    if (age < 18) {
        throw new Error('You must be 18 or older.');
    }
    return 'Welcome!';
}

try {
    console.log(checkAge(25)); // "Welcome!"
} catch (err) {
    console.log('Caught:', err.message);
}

try {
    console.log(checkAge(15)); // throws
} catch (err) {
    console.log('Caught:', err.message); // "You must be 18 or older."
}

// ------------------------------------------------------------------
// 3. CUSTOM ERROR CLASSES
// ------------------------------------------------------------------
console.log('\n=== 3. CUSTOM ERROR CLASSES ===');

// Extend the built-in Error to make errors that describe your domain.
class AuthenticationError extends Error {
    constructor(message) {
        super(message); // call the parent Error constructor
        this.name = 'AuthenticationError';
    }
}

function login(password) {
    if (password !== 'secret123') {
        throw new AuthenticationError('Invalid credentials provided.');
    }
    return 'Login successful!';
}

try {
    console.log(login('wrong_password')); // throws
} catch (err) {
    // instanceof lets you handle different error types appropriately.
    if (err instanceof AuthenticationError) {
        console.log('Security alert:', err.message);
    } else {
        console.log('General error:', err.message);
    }
}

// ------------------------------------------------------------------
// 4. COMMON BUILT-IN ERROR TYPES
// ------------------------------------------------------------------
console.log('\n=== 4. COMMON BUILT-IN ERROR TYPES ===');

// ReferenceError: using a variable that doesn't exist.
try {
    console.log(nonExistentVar);
} catch (err) {
    console.log('ReferenceError:', err.message);
}

// TypeError: an operation on an incompatible type.
try {
    null.toUpperCase();
} catch (err) {
    console.log('TypeError:', err.message);
}

// RangeError: a number outside its allowed range.
try {
    new Array(-1);
} catch (err) {
    console.log('RangeError:', err.message);
}

// SyntaxError: code that breaks the language rules. This one can't be
// caught at runtime (the parser rejects it first), so it is shown as a
// comment rather than executed.
// const x = ; // SyntaxError: Unexpected token ';'
