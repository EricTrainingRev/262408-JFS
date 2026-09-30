// ------------------------------------------------------------------
// Arrays & spread/rest: working with data
// ------------------------------------------------------------------
// This file is loaded by arrays-spread-rest.html. Open that page in a
// browser, then open the console (F12) to see the output below.
//
// It demonstrates:
//   1. The "workhorse" array methods: .map(), .filter(), .reduce(),
//      .find(), .includes(), and .indexOf().
//   2. The spread operator (...): unpacking arrays and objects to copy
//      and merge them.
//   3. The rest operator (...): gathering arguments and "the rest" of
//      a destructured value.

// A small dataset of products we will work with.
const products = [
    { name: 'Laptop', price: 1200, inStock: true },
    { name: 'Mouse', price: 25, inStock: true },
    { name: 'Keyboard', price: 80, inStock: false },
    { name: 'Monitor', price: 300, inStock: true },
];

// ------------------------------------------------------------------
// 1. ARRAY METHODS
// ------------------------------------------------------------------
console.log('=== 1. ARRAY METHODS ===');

// .map() transforms every element and returns a new array.
const names = products.map(p => p.name);
console.log('map (names):', names); // ["Laptop", "Mouse", "Keyboard", "Monitor"]

// .filter() keeps only elements that pass a test.
const inStock = products.filter(p => p.inStock);
console.log('filter (in stock):', inStock.map(p => p.name)); // ["Laptop", "Mouse", "Monitor"]

// .reduce() folds the whole array into a single value.
const total = products.reduce((sum, p) => sum + p.price, 0);
console.log('reduce (total price):', total); // 1605

// .find() returns the first element that passes a test.
const found = products.find(p => p.name === 'Mouse');
console.log('find (Mouse):', found); // { name: 'Mouse', price: 25, inStock: true }

// .includes() checks whether a value exists (works on primitives).
console.log('includes (Laptop):', names.includes('Laptop')); // true
console.log('includes (Tablet):', names.includes('Tablet')); // false

// .indexOf() returns the position of a value, or -1 if absent.
console.log('indexOf (Keyboard):', names.indexOf('Keyboard')); // 2
console.log('indexOf (Tablet):', names.indexOf('Tablet'));     // -1

// ------------------------------------------------------------------
// 2. THE SPREAD OPERATOR (...) — unpacking
// ------------------------------------------------------------------
console.log('\n=== 2. SPREAD (...) ===');

// Copy an array so you can change the copy without touching the original.
const original = [1, 2, 3];
const copy = [...original];
copy.push(4);
console.log('original:', original); // [1, 2, 3] (untouched)
console.log('copy:', copy);         // [1, 2, 3, 4]

// Merge two arrays into one.
const basic = ['Code', 'Test'];
const advanced = ['Deploy', 'Monitor'];
const workflow = [...basic, ...advanced];
console.log('merged:', workflow); // ["Code", "Test", "Deploy", "Monitor"]

// Merge objects; later properties overwrite earlier ones.
const base = { name: 'Alice', role: 'User' };
const perms = { permissions: ['read', 'write'] };
const complete = { ...base, ...perms, role: 'Admin' };
console.log('merged object:', complete);
// { name: 'Alice', role: 'Admin', permissions: ['read', 'write'] }

// ------------------------------------------------------------------
// 3. THE REST OPERATOR (...) — gathering
// ------------------------------------------------------------------
console.log('\n=== 3. REST (...) ===');

// Rest gathers an unknown number of arguments into an array.
function sumAll(...args) {
    return args.reduce((total, n) => total + n, 0);
}
console.log('sumAll(1, 2):', sumAll(1, 2));           // 3
console.log('sumAll(10, 20, 30, 40):', sumAll(10, 20, 30, 40)); // 100

// Rest in destructuring: pull out one value, gather the rest.
const settings = {
    theme: 'dark',
    fontSize: '16px',
    language: 'en',
    timezone: 'UTC',
};
const { theme, ...otherSettings } = settings;
console.log('theme:', theme);            // "dark"
console.log('otherSettings:', otherSettings);
// { fontSize: '16px', language: 'en', timezone: 'UTC' }
