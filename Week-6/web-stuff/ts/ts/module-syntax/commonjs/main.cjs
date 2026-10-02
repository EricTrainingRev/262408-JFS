// =============================================================
// COMMONJS (CJS) — imports
// ------------------------
// `require` is DYNAMIC: it can appear anywhere (inside a function,
// inside an `if`) and is resolved when that line actually executes.
// No static parsing/tree-shaking — the whole module is loaded.
// =============================================================
const math = require("./math.cjs");

console.log(math.add(2, 3));        // 5
console.log("version " + math.version); // version 1.0.0
