// =====================================================================
// keyof — the KEY operator for object types.
//
// `keyof` takes an object type and produces a UNION of its property names
// (as string/number literal types). It's how you say "one of the keys of
// this object" — which lets you write helpers that work generically over
// any property of a shape, with full type safety.
//
// Run it:
//   node dist/extras/6-keyof/keyof.js   (after `npm run build`)
// =====================================================================

// === 1. WHAT IS keyof? ===============================================
interface Person {
  name: string;
  age: number;
  email?: string;
}

// `keyof Person` is the union "name" | "age" | "email".
type PersonKey = keyof Person;

// So a variable typed `keyof Person` can only hold one of those keys.
const k1: PersonKey = "name"; // ok
const k2: PersonKey = "age";  // ok
// const k3: PersonKey = "height"; // ❌ not a key of Person
console.log(k1, k2); // name age

// === 2. keyof + INDEXED ACCESS = a safe property getter =============
// Combine `keyof` with indexed access (`T[K]`) to read a property whose
// name is a key, while keeping the VALUE's type correct.
function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key]; // the return type is the type of that specific property
}

const person: Person = { name: "Ada", age: 36 };

const nameValue = getProperty(person, "name"); // T[K] = string
const ageValue = getProperty(person, "age");   // T[K] = number
console.log(nameValue.toUpperCase()); // ADA
console.log(ageValue + 1);            // 37

// `K extends keyof T` means: only a real key of T is allowed.
// getProperty(person, "height"); // ❌ "height" is not a key of Person

// === 3. keyof on a TYPE ALIAS ========================================
// keyof works on any object type, not just interfaces.
type Config = {
  host: string;
  port: number;
  debug: boolean;
};

type ConfigKey = keyof Config; // "host" | "port" | "debug"
const keys: ConfigKey[] = ["host", "port", "debug"];
console.log(keys.join(", ")); // host, port, debug

// === 4. keyof with `in` — iterating keys (mapped types preview) =====
// `keyof` is the raw material for MAPPED types: you can build a new type
// by transforming each key. (Utility types like Partial/Pick build on this
// — that's the next example.)
type Optional<T> = { [K in keyof T]?: T[K] };

// Optional<Person> makes every property optional.
const partialPerson: Optional<Person> = { name: "Only name" };
console.log(partialPerson.name); // Only name
// (age and email are optional, so omitting them is fine.)

// =====================================================================
// WHAT TO REMEMBER
// - `keyof T` = a union of T's property names ("name" | "age" | ...).
// - `T[K]` = indexed access: the TYPE of property K on T.
// - `K extends keyof T` constrains a generic to only real keys.
// - keyof is the foundation for mapped types and utility types.
// =====================================================================

export {};
