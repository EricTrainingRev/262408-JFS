// =====================================================================
// TYPE GUARDS — narrowing a value's type through runtime checks.
//
// TypeScript knows more about a value after a check that proves what it
// is. Those checks — `typeof`, `instanceof`, `in`, and user-defined
// predicates — are called type guards. They let the compiler narrow a
// broad type (`string | number`, `unknown`) down to a specific one inside
// a branch, so the code there is type-safe.
//
// Run it:
//   node dist/extras/8-type-guards/type-guards.js (after `npm run build`)
// =====================================================================

// === 1. typeof — narrowing primitives ===============================
// `typeof` works for the primitive types (string, number, boolean, ...).
function printSize(value: string | number): string {
  if (typeof value === "string") {
    // Inside this branch, TS knows value is a string.
    return `string of length ${value.length}`;
  }
  // Here, TS knows value is a number.
  return `number ${value}`;
}

console.log(printSize("hello")); // string of length 5
console.log(printSize(42));      // number 42

// === 2. instanceof — narrowing class instances ======================
// `instanceof` checks whether a value came from a specific class.
class Rectangle {
  constructor(public width: number, public height: number) {}
}
class Circle {
  constructor(public radius: number) {}
}

type Shape = Rectangle | Circle;

function describeShape(shape: Shape): string {
  if (shape instanceof Rectangle) {
    // TS knows: shape is a Rectangle here.
    return `rect ${shape.width}x${shape.height}`;
  }
  // shape is a Circle here.
  return `circle of radius ${shape.radius}`;
}

console.log(describeShape(new Rectangle(2, 3)));   // rect 2x3
console.log(describeShape(new Circle(5)));          // circle of radius 5

// === 3. `in` — narrowing by a property's presence ===================
// `"prop" in obj` narrows an object union by which keys it has.
type Dog = { bark(): string };
type Cat = { meow(): string };
type Pet = Dog | Cat;

function speak(pet: Pet): string {
  if ("bark" in pet) {
    return pet.bark(); // TS knows pet is a Dog
  }
  return pet.meow();   // TS knows pet is a Cat
}

console.log(speak({ bark: () => "woof" })); // woof
console.log(speak({ meow: () => "meow" })); // meow

// === 4. USER-DEFINED PREDICATES (`value is Type`) ===================
// When a plain check isn't enough, YOU can write a guard whose return
// type is a TYPE PREDICATE. The tricky part: the function must guarantee
// the relationship, because the compiler trusts the predicate blindly.
function isDog(pet: Pet): pet is Dog {
  return "bark" in pet; // return a boolean; TS accepts it as proof
}

function describePet(pet: Pet): string {
  if (isDog(pet)) {
    // TS trusts the predicate: pet is a Dog here.
    return `dog says ${pet.bark()}`;
  }
  return `cat says ${pet.meow()}`;
}

console.log(describePet({ bark: () => "woof" })); // dog says woof
console.log(describePet({ meow: () => "meow" })); // cat says meow

// === 5. GUARDS vs CASTING ===========================================
// A guard is a SAFE narrowing: the compiler believes it only because you
// proved the value fits. A cast (`as`) is an UNSAFE override you assert
// by hand. Prefer guards — they keep the type-flow verifiable.

// =====================================================================
// WHAT TO REMEMBER
// - typeof: narrow primitives (string | number | boolean | ...).
// - instanceof: narrow class instances (Rectangle | Circle).
// - `in`: narrow object unions by which property is present.
// - A user-defined predicate (`x is Type`) lets you build custom guards.
// - Guards are safe, verifiable narrowing; casts are unchecked overrides.
// =====================================================================

export {};
