// =============================================================
// COMMONJS (CJS) — exports
// ------------------------
// Node's ORIGINAL module system. A module exposes its values by
// assigning to `module.exports` (or the shorthand `exports`).
// .cjs explicitly requests CommonJS so it runs even when the
// package.json sets "type": "module".
// =============================================================

const version = "1.0.0";

function add(a, b) {
  return a + b;
}

module.exports = { version, add };
