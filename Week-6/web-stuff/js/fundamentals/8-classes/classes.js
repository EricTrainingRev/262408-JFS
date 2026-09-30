// ------------------------------------------------------------------
// Classes: object-oriented programming in JavaScript
// ------------------------------------------------------------------
// This file is loaded by classes.html. Open that page in a browser,
// then open the console (F12) to see the output below.
//
// It demonstrates:
//   1. A basic class: constructor, properties, and methods.
//   2. Inheritance with `extends` and `super`.
//   3. Getters and setters for controlled access to properties.
//   4. Private fields (#) for true encapsulation.

// ------------------------------------------------------------------
// 1. A BASIC CLASS
// ------------------------------------------------------------------
console.log('=== 1. A BASIC CLASS ===');

// A class is a blueprint for creating objects. The constructor runs
// automatically when you use `new`, and methods define behavior.
class User {
    constructor(name, email) {
        this.name = name;
        this.email = email;
        this.isLoggedIn = false;
    }

    login() {
        this.isLoggedIn = true;
        console.log(`${this.name} has logged in.`);
    }

    logout() {
        this.isLoggedIn = false;
        console.log(`${this.name} has logged out.`);
    }
}

// Each `new` call creates an independent instance.
const user1 = new User('Alice', 'alice@example.com');
const user2 = new User('Bob', 'bob@example.com');

user1.login();                    // "Alice has logged in."
console.log('user1 isLoggedIn:', user1.isLoggedIn); // true
console.log('user2 isLoggedIn:', user2.isLoggedIn); // false (independent)

// ------------------------------------------------------------------
// 2. INHERITANCE: extends and super
// ------------------------------------------------------------------
console.log('\n=== 2. INHERITANCE ===');

// A subclass inherits everything from its parent, then adds or overrides.
class Animal {
    constructor(name) {
        this.name = name;
    }

    makeSound() {
        console.log(`${this.name} makes a generic noise.`);
    }
}

class Dog extends Animal {
    constructor(name, breed) {
        super(name); // MUST call the parent constructor before using `this`
        this.breed = breed;
    }

    // Override the parent's method with a Dog-specific version.
    makeSound() {
        console.log(`${this.name} (a ${this.breed}) barks: Woof! Woof!`);
    }
}

const myDog = new Dog('Buddy', 'Golden Retriever');
myDog.makeSound(); // "Buddy (a Golden Retriever) barks: Woof! Woof!"

// ------------------------------------------------------------------
// 3. GETTERS AND SETTERS
// ------------------------------------------------------------------
console.log('\n=== 3. GETTERS AND SETTERS ===');

// Getters and setters let you run logic when a property is read or set.
class Temperature {
    constructor(celsius) {
        this._celsius = celsius; // underscore = "internal" by convention
    }

    // Getter: read it like a normal property, but it computes a value.
    get fahrenheit() {
        return (this._celsius * 9) / 5 + 32;
    }

    // Setter: run validation/logic when the property is assigned.
    set fahrenheit(value) {
        this._celsius = ((value - 32) * 5) / 9;
    }
}

const temp = new Temperature(25);
console.log('25C in Fahrenheit:', temp.fahrenheit); // 77 (getter runs)
temp.fahrenheit = 212;                              // setter runs
console.log('212F in Celsius:', temp._celsius);     // 100

// ------------------------------------------------------------------
// 4. PRIVATE FIELDS (#) FOR ENCAPSULATION
// ------------------------------------------------------------------
console.log('\n=== 4. PRIVATE FIELDS (#) ===');

// A field prefixed with # is truly private: it cannot be read or changed
// from outside the class. This prevents accidental state corruption.
class BankAccount {
    #balance = 0; // private field

    constructor(owner) {
        this.owner = owner;
    }

    // Getter exposes a read-only view of the private balance.
    get balance() {
        return `Your current balance is $${this.#balance}`;
    }

    // Setter validates before changing the private balance.
    set balance(amount) {
        if (amount <= 0) {
            console.log('Deposit must be a positive number!');
            return;
        }
        this.#balance += amount;
        console.log(`Successfully deposited $${amount}`);
    }
}

const account = new BankAccount('Charlie');
account.balance = 100;  // setter runs -> "Successfully deposited $100"
account.balance = -50;  // setter rejects -> "Deposit must be a positive number!"
console.log(account.balance); // getter runs -> "Your current balance is $100"

// The private field is NOT accessible from outside the class. Trying to
// read it here is a SyntaxError, so it is shown as a comment, not run:
// console.log(account.#balance); // SyntaxError: Private field '#balance' must be declared in an enclosing class
