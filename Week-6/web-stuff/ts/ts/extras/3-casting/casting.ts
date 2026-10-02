// =====================================================================
// CASTING (type assertions) — telling TypeScript "trust me, it's this".
//
// Casting is how you OVERRIDE a type TypeScript inferred or narrowed for
// you. It is a compile-time-only idea: the `as` keyword is erased, and
// the value is passed through unchanged at runtime. Casting does NOT
// convert data — it only changes what the compiler believes the type is.
//
// Run it:
//   node dist/extras/3-casting/casting.js   (after `npm run build`)
// =====================================================================

// === 1. WHY CAST — narrowing from `unknown` ==========================
// When data comes from an untyped source (an API, JSON.parse, a library),
// its type is often `unknown` or `any`. To use it as a specific type,
// you cast it.
const raw: unknown = "hello";

// Without a cast, `unknown` blocks us from using it as a string:
// raw.toUpperCase(); // ❌ Object is of type 'unknown'

// Cast it to string, and the compiler lets us call string methods.
const text = raw as string;
console.log(text.toUpperCase()); // HELLO

// === 2. THE `as` SYNTAX ==============================================
// `value as Type` is the modern, recommended form.
const num = 42 as number; // (redundant here — already a number, but valid)
console.log(num + 1); // 43

// === 3. CASTING BETWEEN OVERLAPPING TYPES ============================
// TypeScript only allows a cast when the two types OVERLAP enough. Casting
// a string to a number is NOT allowed directly — they don't overlap.
// const n = "5" as number; // ❌ Conversion of type 'string' to 'number' may be a mistake

// To force it anyway, go through `unknown` first (a double cast). This is
// a deliberate "I know better" escape hatch — use sparingly.
const n = "5" as unknown as number;
console.log(n + 1); // 51  (string concat! the cast did NOT convert it)

// === 4. CASTING OBJECTS — a common real-world use ====================
// A common pattern: an API returns a broad shape, and you cast it to a
// narrower, more convenient one.
interface ApiUser {
  id: number;
  name: string;
  email?: string;
}

// Pretend this came from a fetch response typed as `unknown`.
const apiData: unknown = { id: 7, name: "Grace" };

const user = apiData as ApiUser;
console.log(user.name); // Grace
console.log(user.id);   // 7

// === 5. CASTING vs NARROWING — prefer narrowing =====================
// Casting is a promise you make to the compiler. If you're wrong, the
// compiler won't catch it — the error surfaces at runtime. Prefer real
// narrowing (typeof / instanceof / in checks) when you can, because the
// compiler VERIFIES it. Casting is for when you know more than the
// compiler does (e.g. data you trust from a known API shape).

// =====================================================================
// WHAT TO REMEMBER
// - `value as Type` overrides the inferred type; erased at compile time.
// - Casting does NOT convert data — it only changes the compiler's belief.
// - Direct casts need overlapping types; otherwise go `as unknown as Type`.
// - Prefer narrowing (typeof/instanceof) over casting when possible.
// =====================================================================

export {};
