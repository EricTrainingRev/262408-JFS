// =====================================================================
// TYPE ALIASES vs INTERFACES
//
// Both let you give a reusable name to a shape, so you're not typing the
// same inline object annotation over and over. This example shows where
// they overlap, and — crucially — that they are a COMPILE-TIME idea:
// the compiled JavaScript is identical, because the types are erased.
//
// Run it:
//   node dist/aliases-vs-interfaces/aliases-vs-interfaces.js
// (after `npm run build`)
// =====================================================================

// === 1. TYPE ALIAS — name any shape =================================
// `type` is general-purpose: it can name objects, unions, primitives,
// anything. You assign the shape with an equals sign.
type User = {
  name: string;
  id: number;
  email?: string; // the `?` makes the property OPTIONAL
};

const admin: User = { name: "Bob", id: 2 };
const withEmail: User = { name: "Cara", id: 3, email: "cara@example.com" };
console.log(admin.name);            // Bob
console.log(withEmail.email);       // cara@example.com
console.log(admin.email);           // undefined — that is why `?` is optional

// A type alias can also name a NON-object, e.g. a union type:
type Status = "active" | "inactive"; // a union of two literal strings

function describe(status: Status): string {
  return status === "active" ? "ON" : "OFF";
}
console.log(describe("active"));    // ON

// === 2. INTERFACE — extendable object shapes ========================
// `interface` is purpose-built for OBJECT and CLASS shapes. It is not
// written with an equals sign.
interface Animal {
  name: string;
}

// Interfaces extend other interfaces with the `extends` keyword.
interface Dog extends Animal {
  breed: string;
}

const myDog: Dog = { name: "Rex", breed: "Labrador" };
console.log(myDog.name + " is a " + myDog.breed); // Rex is a Labrador

// === 3. EXTENDING: `extends` vs `&` ==================================
// Interfaces combine with `extends`. Type aliases combine with an
// intersection `&`. They produce the same result.
type HasColor = { color: string };
type Car = HasColor & { wheels: number }; // intersection

interface OtherAnimal extends Animal { legs: number; }
interface Fish extends OtherAnimal { freshwater: boolean; }

const car: Car = { color: "red", wheels: 4 };
const goldfish: Fish = { name: "Goldie", legs: 0, freshwater: true };
console.log(car.color + " " + car.wheels);             // red 4
console.log(goldfish.freshwater);                      // true

// === 4. DECLARATION MERGING (interface-only superpower) =============
// The SAME interface can be declared twice — the two declarations MERGE.
// This is what lets third-party packages ("ambient" type patches) add
// fields to an existing type. `type` aliases CANNOT do this.
interface Settings { theme: string; }
interface Settings { notifications: boolean; } // reopens: merges the field in

// `Settings` now has BOTH fields, as if written once.
const merged: Settings = { theme: "dark", notifications: true };
console.log(merged.theme + " / notification=" + merged.notifications); // dark / notification=true

// A `type` alias CANNOT be redeclared:
  // type Settings2 = { a: string };
  // type Settings2 = { b: string }; // ❌ TS2300: Duplicate identifier 'Settings2'

// === 5. THE ERASURE POINT — types disappear at compile ==============
// The compiled aliases-vs-interfaces.js below is ~identical regardless of
// whether you used `type` or `interface`. Open it and you'll find plain
// objects with NO User/Dog/Animal names anywhere:
//   User   →  just an object literal
//   Dog    →  just an object literal
//   Status →  just a value ("active"/"inactive")
// The type layer is a DEV-time aid that vanishes before this runs.

// =====================================================================
// WHAT TO REMEMBER
// - Both give a shape a reusable name; both are erased at compile time.
// - `type` = general (objects, unions, primitives); combine with `&`.
// - `interface` = object/class shapes; extend with `extends`; supports
//   declaration merging. Reach for it when you want others to extend it.
// - Rule of thumb: interface for public/class API, type for everything
//   else (especially unions).
// =====================================================================

export {};
