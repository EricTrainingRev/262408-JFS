// =====================================================================
// `as const` — freezing a value into its most specific literal type.
//
// Without `as const`, TypeScript infers broad types (string, number).
// With `as const`, it infers the EXACT literal value — and marks the
// object/array as read-only. This is the go-to trick for defining a fixed
// set of options or a lookup table with immutable, precise types.
//
// Run it:
//   node dist/extras/9-as-const/as-const.js   (after `npm run build`)
// =====================================================================

// === 1. THE PROBLEM — broad inference ===============================
// A plain `const` object's properties are inferred as WIDE types.
const config = { name: "app", port: 8080 };
// config.name is inferred as `string` (not the literal "app")
// config.port is inferred as `number` (not the literal 8080)

// === 2. `as const` — narrows literals AND freezes ===================
const frozenConfig = { name: "app", port: 8080 } as const;
// Now: name is the literal type "app", port is the literal type 8080,
// and both are read-only.

console.log(frozenConfig.name); // app
console.log(frozenConfig.port); // 8080
// frozenConfig.port = 9000; // ❌ Cannot assign to 'port' — read-only (as const)

// === 3. WHY IT MATTERS — precise literal unions =====================
// The classic use: build a list/object of allowed values, and derive a
// type from it, so the code and the type can't drift apart.
const pizzaToppings = ["cheese", "pepperoni", "mushroom"] as const;
// With as const, the array is typed as the literal list of those strings.
type Topping = (typeof pizzaToppings)[number]; // "cheese" | "pepperoni" | "mushroom"

function checkTopping(t: Topping): string {
  return `Topping: ${t}`;
}
console.log(checkTopping("pepperoni")); // Topping: pepperoni
// checkTopping("pineapple"); // ❌ "pineapple" is not one of the toppings

// WITHOUT as const, the same trick fails — `typeof arr[number]` would be
// just `string`, so any string would be accepted and the safety is lost.
// That is the payoff of `as const` here.

// === 4. OBJECT LOOKUP TABLES with as const ==========================
// Combine with a `keyof`/indexed access to make a typed lookup table.
const statusMessages = {
  ok: "All good",
  error: "Something broke",
  loading: "Please wait",
} as const;

// statusMessages is read-only, so lookups are safe and typed.
console.log(statusMessages.ok);   // All good
console.log(statusMessages.error); // Something broke

// =====================================================================
// WHAT TO REMEMBER
// - `as const` narrows primitives to their literal type AND makes the
//   whole object/array read-only.
// - Typical pattern: define constants `as const`, derive a union type
//   from them (`(typeof arr)[number]` or `keyof obj`), and the data and
//   its type stay in sync.
// - Without it, arrays/objects infer wide types (string/number) and the
//   strictness is lost.
// =====================================================================

export {};
