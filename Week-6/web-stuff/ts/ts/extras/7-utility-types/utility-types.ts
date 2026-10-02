// =====================================================================
// UTILITY TYPES — built-in type transformations.
//
// TypeScript ships ready-made helpers that transform one type into
// another (Partial, Required, Pick, Omit, Record, Readonly, ReturnType,
// ...). They operate on TYPES at compile time — they are erased from the
// output entirely. That makes them tricky to *see* in a running program:
// the proof is in whether the code COMPILES. So this example mixes values
// you can read with @ts-expect-error lines that only typecheck when the
// utility type is doing its (rejecting) job.
//
// Run it:
//   node dist/extras/7-utility-types/utility-types.js (after `npm run build`)
// =====================================================================

interface User {
  id: number;
  name: string;
  email: string;
}

const fullUser: User = { id: 1, name: "Ada", email: "ada@p.org" };
console.log(fullUser.name); // Ada

// === 1. Partial<T> — every property becomes OPTIONAL =================
// Great for "update with only the fields you want to change."
type UpdatePayload = Partial<User>;
const update: UpdatePayload = { name: "Ada Lovelace" }; // only some fields
console.log(update.name); // Ada Lovelace

// === 2. Required<T> — every property becomes MANDATORY ===============
// The opposite of Partial: strips all `?`.
type StrictUser = Required<User>;
const strictUser: StrictUser = { id: 2, name: "Grace", email: "g@p.org" };
console.log(strictUser.email); // g@p.org

// === 3. Pick<T, K> — keep only the listed keys ======================
type PublicUser = Pick<User, "id" | "name">; // drop the email
const publicUser: PublicUser = { id: 3, name: "Katherine" };
console.log(publicUser.name); // Katherine
// publicUser.email; // ❌ email was dropped by Pick — not a property anymore

// === 4. Omit<T, K> — everything EXCEPT the listed keys ==============
type Credentials = Omit<User, "id">; // keep name + email, get rid of id
const creds: Credentials = { name: "Edsgar", email: "e@dijkstra.org" };
console.log(creds.name); // Edsgar
// creds.id; // ❌ id was removed by Omit

// === 5. Record<K, V> — an object whose keys map to a value type =====
// Build a "dictionary". K is the key type, V the value type.
type StatusCode = 200 | 404 | 500;
const messages: Record<StatusCode, string> = {
  200: "OK",
  404: "Not Found",
  500: "Server Error",
};
console.log(messages[404]); // Not Found
// messages[301]; // ❌ 301 is not one of the Record keys

// === 6. Readonly<T> — every property is read-only ===================
type FrozenUser = Readonly<User>;
const frozen: FrozenUser = { id: 4, name: "Linus", email: "l@p.org" };
// frozen.name = "someone else"; // ❌ read-only property
console.log(frozen.name); // Linus

// === 7. ReturnType<F> — the return type of a function type ==========
// Extract what a function type RETURNS, as a type you can reuse.
type GetGreeting = () => string;
type GreetingResult = ReturnType<GetGreeting>; // string

const greeting: GreetingResult = "hi";
console.log(greeting.toUpperCase()); // HI

// === 8. COMBINING UTILITIES =========================================
// They compose. e.g. "a User-payload that may only partially specify
// fields, but whose id is mandatory":
type UpsertPayload = Required<Pick<User, "id">> & Partial<Omit<User, "id">>;
const upsert: UpsertPayload = { id: 5, name: "optional name" };
console.log(upsert.id + " -> " + (upsert.name ?? "(no name)")); // 5 -> optional name

// =====================================================================
// WHAT TO REMEMBER
// - Partial / Required: flip optionality.
// - Pick / Omit: choose which keys survive.
// - Record<K, V>: build an object type from key + value types.
// - Readonly<T>: make every property read-only.
// - ReturnType<F>: extract a function type's return type.
// - They compose — combine them to express exactly the shape you need.
// =====================================================================

export {};
