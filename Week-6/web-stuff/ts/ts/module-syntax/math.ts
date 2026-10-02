// =============================================================
// ES MODULES (ESM) — exports
// --------------------------
// This file exports values using the ESM `export` keyword.
// "type": "module" in package.json marks .js/.ts as ESM.
// =============================================================

export const version = "1.0.0";

export function add(a: number, b: number): number {
  return a + b;
}
