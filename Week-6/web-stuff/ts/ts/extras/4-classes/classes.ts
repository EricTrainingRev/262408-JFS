// =====================================================================
// CLASSES — the TYPE layer on top of class behavior.
//
// The JS fundamentals already cover what classes DO (constructors,
// methods, inheritance). This example is about the TYPES TypeScript adds:
// access modifiers (public/private/protected), `readonly` fields,
// parameter properties, and the `implements` keyword for tying a class to
// an interface. All of it is compile-time — erased before the JS runs.
//
// Run it:
//   node dist/extras/4-classes/classes.js   (after `npm run build`)
// =====================================================================

// === 1. TYPED FIELDS & CONSTRUCTOR ==================================
// Fields declare their type. Under `strictPropertyInitialization`, a
// field without an initializer MUST be assigned in the constructor.
class Point {
  x: number;
  y: number;

  constructor(x: number, y: number) {
    this.x = x;
    this.y = y;
  }

  distanceFromOrigin(): number {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
}

const p = new Point(3, 4);
console.log(p.distanceFromOrigin()); // 5

// === 2. ACCESS MODIFIERS: public / private / protected ==============
// - `public` (default): accessible anywhere.
// - `private`: only inside the class.
// - `protected`: inside the class AND subclasses, not outside.
class BankAccount {
  public owner: string;      // public — anyone can read/write
  private balance: number;   // private — only methods inside can touch it
  protected bank: string;    // protected — this class and subclasses

  constructor(owner: string, balance: number, bank: string) {
    this.owner = owner;
    this.balance = balance;
    this.bank = bank;
  }

  deposit(amount: number): void {
    this.balance += amount; // allowed: we're inside the class
  }

  getBalance(): number {
    return this.balance;
  }
}

const acct = new BankAccount("Ada", 100, "Chase");
console.log(acct.owner);        // Ada  (public)
console.log(acct.getBalance()); // 100  (via a public method)
// acct.balance; // ❌ Property 'balance' is private — only accessible within the class

// === 3. `readonly` FIELDS ===========================================
// A `readonly` field can be set once (in the constructor) but never
// reassigned afterward.
class Product {
  readonly sku: string; // set once, then immutable

  constructor(sku: string) {
    this.sku = sku;
  }
}

const prod = new Product("A-123");
console.log(prod.sku); // A-123
// prod.sku = "B-999"; // ❌ Cannot assign to 'sku' because it is a read-only property

// === 4. PARAMETER PROPERTIES (shorthand) ============================
// Declaring a field AND assigning it in the constructor is verbose.
// TypeScript lets you do both at once by putting the modifier on the
// constructor parameter — it auto-creates the field and assigns it.
class User {
  constructor(
    public name: string,     // creates `name` field, assigns it
    private id: number,      // creates private `id` field, assigns it
    readonly role: string,   // creates readonly `role` field, assigns it
  ) {}

  describe(): string {
    return `${this.name} (id ${this.id}, ${this.role})`;
  }
}

const u = new User("Grace", 7, "admin");
console.log(u.describe()); // Grace (id 7, admin)
console.log(u.name);       // Grace  (public)
// u.id; // ❌ private

// === 5. `implements` — a class must match an interface ==============
// `implements` says "this class promises to have the shape of the
// interface." The compiler checks the class satisfies it.
interface Shape {
  area(): number
}

// multiple interfaces can be implemented
class Square implements Shape {
  constructor(private side: number) {}

  area(): number {
    return this.side * this.side;
  }
}

const sq = new Square(4);
console.log(sq.area()); // 16

// If a class forgets a required member, `implements` fails at compile time:
//   class Circle implements Shape { } // ❌ missing 'area()'

// =====================================================================
// WHAT TO REMEMBER
// - Fields declare types; strict requires them initialized in the ctor.
// - public (default) / private (class only) / protected (class + subclasses).
// - `readonly` = set once, never reassigned.
// - Parameter properties: put the modifier on the ctor param to declare+assign.
// - `implements` ties a class to an interface; the compiler enforces it.
// =====================================================================

export {};
