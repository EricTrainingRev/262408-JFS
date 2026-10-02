// =============================================================
// ES MODULES (ESM) — imports
// --------------------------
// `import` is static: written at the top level, resolved at
// parse time (before anything runs), so bundlers/compilers can
// "tree-shake" unused exports and know the dependency graph up front.
// The "./math.js" maps from this .ts to the sibling math.ts at compile.
// =============================================================
import { add, version } from "./math.js";

console.log(add(2, 3));               // 5
console.log("version " + version);    // version 1.0.0

// Other ESM import shapes (all static, all allowed at top level):
//   import def from "./math.js";      // default export (one per file)
//   import * as m from "./math.js";   // namespace: m.add, m.version
//   import { add as sum } from "./math.js"; // renamed import
