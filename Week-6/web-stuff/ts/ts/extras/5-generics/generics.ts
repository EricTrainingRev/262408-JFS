// =====================================================================
// GENERICS — writing one function/type that works with MANY types.
//
// A generic is a "type parameter": you write the logic once with a
// placeholder (conventionally `T`), and the caller decides the concrete
// type. This gives you type safety that a plain `any` would throw away —
// the compiler tracks the specific type through the whole call.
//
// Run it:
//   node dist/extras/5-generics/generics.js   (after `npm run build`)
// =====================================================================

// === 1. A GENERIC FUNCTION ===========================================
// `<T>` declares a type parameter. `identity` takes a value of type T and
// returns the SAME type T — not `any`, not `unknown`, but whatever the
// caller passed in.
function identity<T>(value: T): T {
  return value;
}

// TypeScript infers T from the argument:
console.log(identity("hello")); // hello   (T = string)
console.log(identity(42));      // 42      (T = number)

// You can also state T explicitly with angle brackets:
console.log(identity<boolean>(true)); // true

// === 2. WHY NOT `any`? — the type is PRESERVED ======================
// With `any`, the return type is `any`, so you lose all checking on the
// result. With a generic, the return type is the SAME as the input, so
// the compiler still knows what you have afterward.
function firstElement<T>(arr: T[]): T | undefined {
  return arr[0]; // noUncheckedIndexedAccess: may be undefined
}

const first = firstElement(["a", "b", "c"]);
// `first` is `string | undefined` — the compiler KNOWS it's a string.
if (first !== undefined) {
  console.log(first.toUpperCase()); // A
}

// === 3. GENERIC CONSTRAINTS (`extends`) ==============================
// Sometimes you need the type to have certain properties. A constraint
// (`T extends SomeType`) says "T must be at least this shape."
interface HasLength {
  length: number;
}

function longest<T extends HasLength>(a: T, b: T): T {
  return a.length >= b.length ? a : b;
}

console.log(longest("cat", "giraffe")); // giraffe  (strings have .length)
console.log(longest([1, 2], [1, 2, 3, 4])); // [1,2,3,4]  (arrays have .length)
// longest(1, 2); // ❌ number has no .length — rejected by the constraint

// === 4. GENERIC TYPES (aliases & interfaces) =========================
// Types can be generic too — a reusable shape parameterized by T.
type Pair<T> = { first: T; second: T };

const numbers: Pair<number> = { first: 1, second: 2 };
const words: Pair<string> = { first: "hi", second: "bye" };
console.log(numbers.first + numbers.second); // 3
console.log(words.first + " " + words.second); // hi bye

// === 5. GENERIC CLASSES ==============================================
// A class can hold a type parameter, so instances can be typed.
class Box<T> {
  constructor(private contents: T) {}

  get(): T {
    return this.contents;
  }
}

const stringBox = new Box("treasure");
const numberBox = new Box(99);
console.log(stringBox.get()); // treasure
console.log(numberBox.get()); // 99

// =====================================================================
// WHAT TO REMEMBER
// - `<T>` = a type parameter; the caller picks the concrete type.
// - A generic PRESERVES the type (unlike `any`), so checking survives.
// - `T extends SomeType` constrains T to have at least that shape.
// - Types, interfaces, and classes can all be generic.
// =====================================================================

export {};
