// =====================================================================
// ENUMS — a named set of related constants.
//
// An enum lets you group a fixed set of values under one name, so you
// write `Color.Red` instead of a magic number or string. TypeScript has
// two flavors: NUMERIC and STRING. They differ in a way that matters at
// runtime, so this example shows both side by side.
//
// Run it:
//   node dist/1-enums/enums.js   (after `npm run build`)
// =====================================================================

// === 1. NUMERIC ENUM =================================================
// Members get a number automatically, starting at 0 and counting up.
enum Direction {
  North,   // 0
  East,    // 1
  South,   // 2
  West,    // 3
}

console.log(Direction.North); // 0
console.log(Direction.West);  // 3

// You can also set the starting value explicitly; the rest still count up.
enum Level {
  Low = 1,
  Medium, // 2
  High,   // 3
}
console.log(Level.Medium); // 2

// NUMERIC enums have a REVERSE mapping: the number maps back to the name.
// This is a runtime feature — the compiled JS builds a two-way object.
console.log(Direction[1]); // "East"  (number -> name)

// === 2. STRING ENUM ==================================================
// Members hold string values. There is NO auto-increment — you must give
// each member a value.
enum Color {
  Red = "RED",
  Green = "GREEN",
  Blue = "BLUE",
}

console.log(Color.Red);   // RED
console.log(Color.Blue);  // BLUE

// STRING enums have NO reverse mapping. The compiled JS is a plain
// one-way object, so you cannot look a name up from its value.
// console.log(Color["RED"]); // ❌ undefined — no reverse mapping for strings

// === 3. USING AN ENUM AS A TYPE ======================================
// An enum's name doubles as a TYPE: a variable can only hold one of the
// enum's members (or the underlying number/string).
function describe(direction: Direction): string {
  switch (direction) {
    case Direction.North: return "heading up";
    case Direction.East:  return "heading right";
    case Direction.South: return "heading down";
    case Direction.West:  return "heading left";
  }
}

console.log(describe(Direction.South)); // heading down

// === 4. WHEN TO REACH FOR AN ENUM ====================================
// Enums are great for a FIXED, known set of options (directions, colors,
// statuses, roles). For a small set, a plain union of string literals is
// often simpler and lighter:
type Status = "active" | "inactive"; // no runtime object at all

// Rule of thumb: use a string-literal union when you just need the type;
// use an enum when you want a named, grouped, runtime value you can
// iterate or reverse-map.

// =====================================================================
// WHAT TO REMEMBER
// - Numeric enums auto-increment (0,1,2...) and have a REVERSE mapping.
// - String enums need explicit values and have NO reverse mapping.
// - An enum name works as a type: only its members are assignable.
// - For a tiny fixed set, a string-literal union is often the lighter pick.
// =====================================================================

export {};
