// ------------------------------------------------------------------
// Control Flow: making decisions & repeating work
// ------------------------------------------------------------------
// This file is loaded by control-flow.html. Open that page in a browser,
// then open the console (F12) to see the output below.
//
// "Control flow" is the order in which your statements run. By default
// JavaScript runs top-to-bottom, but these tools let you branch, loop,
// and jump so your code can react to data:
//   1. if / else if / else — branch on a condition
//   2. The ternary operator — a one-line if/else
//   3. switch — match one value against many cases
//   4. for loops — repeat a known number of times
//   5. while loops — repeat while a condition is true
//   6. do...while loops — run at least once, then check
//   7. for...of — loop over the values of an iterable
//   8. for...in — loop over the keys of an object
//   9. break & continue — jump out of or skip a loop
//   10. try/catch/finally — handle errors as control flow

// ------------------------------------------------------------------
// 1. if / else if / else
// ------------------------------------------------------------------
console.log('=== 1. if / else if / else ===');

// The most common way to branch. The condition inside the parentheses
// is evaluated as a boolean; if it's truthy, that block runs.
function gradeLabel(score) {
    if (score >= 90) {
        return 'A';
    } else if (score >= 80) {
        return 'B';
    } else if (score >= 70) {
        return 'C';
    } else {
        return 'F';
    }
}
console.log('gradeLabel(95):', gradeLabel(95)); // A
console.log('gradeLabel(83):', gradeLabel(83)); // B
console.log('gradeLabel(50):', gradeLabel(50)); // F

// The `else` branch is optional. If no condition matches and there is
// no else, nothing runs.
if (false) {
    console.log('this never runs');
}
console.log('(no else branch: nothing printed above)');

// ------------------------------------------------------------------
// 2. THE TERNARY OPERATOR
// ------------------------------------------------------------------
console.log('\n=== 2. TERNARY OPERATOR ===');

// A compact if/else that RETURNS a value. Syntax:
//   condition ? valueIfTrue : valueIfFalse
// Use it for short, single-expression choices — not complex logic.
const age = 20;
const status = age >= 18 ? 'adult' : 'minor';
console.log('age 20 ->', status); // adult

const price = 100;
const discount = price > 50 ? price * 0.9 : price;
console.log('discounted price:', discount); // 90

// ------------------------------------------------------------------
// 3. switch
// ------------------------------------------------------------------
console.log('\n=== 3. switch ===');

// switch compares one value against several `case` labels using strict
// equality (===). It's cleaner than a long chain of if/else when you
// are matching a single value against many options.
function dayName(dayNumber) {
    switch (dayNumber) {
        case 1:
            return 'Monday';
        case 2:
            return 'Tuesday';
        case 3:
            return 'Wednesday';
        default:
            return 'Unknown day';
    }
}
console.log('dayName(2):', dayName(2));       // Tuesday
console.log('dayName(9):', dayName(9));       // Unknown day

// `break` matters when cases FALL THROUGH. Without it, execution keeps
// running into the next case. This example shows the classic pitfall:
function fallThrough(n) {
    switch (n) {
        case 1:
            console.log('case 1 matched');
            // no break here — execution "falls through" to case 2!
        case 2:
            console.log('case 2 also ran');
            break;
        default:
            console.log('default ran');
    }
}
console.log('fallThrough(1):');
fallThrough(1); // prints BOTH "case 1 matched" and "case 2 also ran"

// ------------------------------------------------------------------
// 4. for LOOPS
// ------------------------------------------------------------------
console.log('\n=== 4. for LOOPS ===');

// A for loop has three parts: an initializer, a condition, and an
// update. It runs the body, then updates, then re-checks the condition.
console.log('counting 0 to 4:');
for (let i = 0; i < 5; i++) {
    console.log('  i =', i); // 0, 1, 2, 3, 4
}

// Loops are great for summing or building up a result.
let sum = 0;
for (let i = 1; i <= 10; i++) {
    sum += i;
}
console.log('sum of 1..10:', sum); // 55

// ------------------------------------------------------------------
// 5. while LOOPS
// ------------------------------------------------------------------
console.log('\n=== 5. while LOOPS ===');

// A while loop checks the condition BEFORE each iteration. If the
// condition is false from the start, the body never runs.
let count = 0;
while (count < 3) {
    console.log('  count =', count);
    count++;
}
// count is now 3, so the loop stops.

// while is ideal when you don't know how many iterations you need.
let n = 100;
let steps = 0;
while (n > 1) {
    n = n % 2 === 0 ? n / 2 : n * 3 + 1; // the Collatz sequence (if even divide by 2, else multiply by 3 and add 1 till you reach 1)
    steps++;
}
console.log('Collatz reached 1 in', steps, 'steps'); // 25

// ------------------------------------------------------------------
// 6. do...while LOOPS
// ------------------------------------------------------------------
console.log('\n=== 6. do...while LOOPS ===');

// A do...while checks the condition AFTER the body, so it ALWAYS runs
// at least once — even if the condition is false immediately.
let attempts = 0;
do {
    attempts++;
    console.log('  attempt', attempts);
} while (attempts < 2);
// runs twice: attempt 1, attempt 2

// Contrast with while, which would run zero times here:
let x = 0;
while (x > 0) {
    console.log('while body never runs');
}
do {
    console.log('do...while body runs once even though x is 0');
} while (x > 0);

// ------------------------------------------------------------------
// 7. for...of LOOPS
// ------------------------------------------------------------------
console.log('\n=== 7. for...of LOOPS ===');

// for...of iterates over the VALUES of an iterable (arrays, strings,
// Maps, Sets). It's the modern, readable way to walk an array.
const fruits = ['apple', 'banana', 'cherry'];
console.log('fruits:');
for (const fruit of fruits) {
    console.log('  -', fruit); // apple, banana, cherry
}

// Strings are iterable too — you get each character.
let vowels = 0;
for (const char of 'hello world') {
    if ('aeiou'.includes(char)) {
        vowels++;
    }
}
console.log('vowels in "hello world":', vowels); // 3

// ------------------------------------------------------------------
// 8. for...in LOOPS
// ------------------------------------------------------------------
console.log('\n=== 8. for...in LOOPS ===');

// for...in iterates over the KEYS (property names) of an object.
// Use it for objects; prefer for...of for arrays.
const user = { name: 'Alice', age: 30, role: 'admin' };
console.log('user keys:');
for (const key in user) {
    console.log(`  ${key} = ${user[key]}`); // name = Alice, etc.
}

// ------------------------------------------------------------------
// 9. break & continue
// ------------------------------------------------------------------
console.log('\n=== 9. break & continue ===');

// `break` exits the loop entirely.
console.log('break at 3:');
for (let i = 0; i < 10; i++) {
    if (i === 3) {
        break; // stop the whole loop
    }
    console.log('  i =', i); // 0, 1, 2
}

// `continue` skips the rest of THIS iteration and moves to the next.
console.log('continue past even numbers:');
for (let i = 0; i < 6; i++) {
    if (i % 2 === 0) {
        continue; // skip even numbers
    }
    console.log('  odd i =', i); // 1, 3, 5
}

// ------------------------------------------------------------------
// 10. try / catch / finally
// ------------------------------------------------------------------
console.log('\n=== 10. try / catch / finally ===');

// Errors are also a form of control flow: when something throws, the
// normal top-to-bottom flow is interrupted and jumps to the catch block.
// finally always runs, whether an error happened or not.
function riskyDivide(a, b) {
    try {
        if (b === 0) {
            throw new Error('Cannot divide by zero');
        }
        return a / b;
    } catch (error) {
        console.log('  Caught:', error.message);
        return null;
    } finally {
        console.log('  finally: cleanup always runs');
    }
}
console.log('riskyDivide(10, 2):', riskyDivide(10, 2)); // 5
console.log('riskyDivide(10, 0):', riskyDivide(10, 0)); // null (error caught)
