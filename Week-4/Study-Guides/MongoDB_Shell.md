# MongoDB Shell: A Comprehensive Guide

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Connecting to MongoDB](#2-connecting-to-mongodb)
* [3. Basic CRUD Operations](#3-basic-crud-operations)
    * [3.1 Create](#31-create)
    * [3.2 Read](#32-read)
    * [3.3 Update](#33-update)
    * [3.4 Delete](#34-delete)
* [4. Query Syntax and Operators](#4-query-syntax-and-operators)
    * [4.1 Comparison Operators](#41-comparison-operators)
    * [4.2 Logical Operators](#42-logical-operators)
    * [4.3 Element Operators](#43-element-operators)
    * [4.4 Array Operators](#44-array-operators)

***

## 1. High-Level Overview

The **MongoDB Shell** (`mongosh`) is an interactive JavaScript interface to MongoDB. It allows developers to query and manipulate data, manage administrative tasks, and perform complex data operations directly from the command line. While most applications interact with MongoDB via drivers (like Node.js or Python), `mongosh` is the essential tool for rapid prototyping, debugging, and database administration.

[↑ Back to Table of Contents](#table-of-contents)

***

*Now that we understand what the shell is, let's move into the first practical step: establishing a connection to your database.*

## 2. Connecting to MongoDB

To interact with a MongoDB instance, you must first establish a session via the `mongosh` executable.

### 2.1 Connection Methods

Depending on your environment (local vs. cloud), your connection string will vary.

| Connection Type | Connection String Format | Typical Use Case |
| :--- | :--- | :--- |
| **Local Instance** | `mongosh` | Development on your own machine (defaulting to `localhost:27017`). |
| **Remote/Atlas** | `mongosh "mongodb+srv://<user>:<password>@cluster.mongodb.net/"` | Production or shared cloud databases (MongoDB Atlas). |
| **Specific Database** | `mongosh "mongodb://localhost:27017/myDatabase"` | Connecting and automatically switching to a specific database. |

> [!TIP]
> If you are using MongoDB Atlas, always copy the "Connection String" provided in the Atlas UI to ensure you have the correct SRV protocol and credentials.

### 2.2 Basic Shell Commands

Once connected, use these commands to navigate your environment:

*   **`show dbs`**: Lists all available databases on the server.
*   **`use <database_name>`**: Switches the current context to the specified database (creates it if it doesn't exist).
*   **`db`**: Prints the name of the database you are currently using.
*   **`show collections`**: Lists all collections (tables) within the current database.
*   **`cls`**: Clears the shell screen.

[↑ Back to Table of Contents](#table-of-contents)

***

*With a connection established and a database selected, we can begin performing the fundamental actions of data management: CRUD.*

## 3. Basic CRUD Operations

**CRUD** stands for **C**reate, **R**ead, **U**pdate, and **D**elete. These are the four essential functions of any database interaction. In MongoDB, these operations are performed on **collections** of **documents**.

### 3.1 Create

To insert new documents into a collection, use `insertOne()` or `insertMany()`.

```javascript
// Insert a single document
db.users.insertOne({
  name: "Alice",
  age: 30,
  email: "alice@example.com",
  status: "active"
});

// Insert multiple documents
db.users.insertMany([
  { name: "Bob", age: 25, email: "bob@example.com", status: "active" },
  { name: "Charlie", age: 35, email: "charlie@example.com", status: "inactive" }
]);
```

### 3.2 Read

To retrieve documents, use the `find()` method.

| Method | Description | Example |
| :--- | :--- | :--- |
| **`find()`** | Returns all documents in the collection. | `db.users.find()` |
| **`findOne()`** | Returns the first document that matches the criteria. | `db.users.findOne({ name: "Alice" })` |
| **`find().limit(n)`** | Returns a maximum of `n` documents. | `db.users.find().limit(5)` |
| **`find().sort({ field: 1 })`** | Sorts results (1 for Ascending, -1 for Descending). | `db.users.find().sort({ age: 1 })` |

### 3.3 Update

To modify existing documents, use `updateOne()` or `updateMany()`. **Important:** Always use update operators like `$set` to avoid overwriting the entire document.

```javascript
// Update a single field in one document
db.users.updateOne(
  { name: "Alice" },           // Filter (Who to update)
  { $set: { age: 31 } }        // Update (What to change)
);

// Update multiple documents
db.users.updateMany(
  { status: "active" },        // Filter
  { $set: { verified: true } } // Update
);
```

### 3.4 Delete

To remove documents, use `deleteOne()` or `deleteMany()`.

```javascript
// Delete one specific document
db.users.deleteOne({ name: "Bob" });

// Delete all documents matching a criteria
db.users.deleteMany({ status: "inactive" });
```

> [!WARNING]
> **The "Empty Filter" Danger:** Running `db.collection.deleteMany({})` with an empty filter will **wipe the entire collection**. Always verify your filter before executing a delete.

[↑ Back to Table of Contents](#table-of-contents)

***

*Basic CRUD allows us to manipulate data, but real-world applications require complex logic. This is achieved through the power of MongoDB's query syntax and operators.*

## 4. Query Syntax and Operators

MongoDB uses a JSON-like query language. Instead of a string-based language like SQL, you pass **objects** as filters to the `find()` method.

### 4.1 Comparison Operators

These allow you to filter documents based on ranges or specific values.

| Operator | Meaning | Example | Result |
| :--- | :--- | :--- | :--- |
| **`$eq`** | Equal to | `{ age: { $eq: 30 } }` | Documents where age is exactly 30. |
| **`$ne`** | Not equal to | `{ status: { $ne: "active" } }` | Documents where status is NOT "active". |
| **`$gt`** | Greater than | `{ age: { $gt: 25 } }` | Documents where age is > 25. |
| **`$gte`** | Greater than or equal | `{ age: { $gte: 25 } }` | Documents where age is $\ge$ 25. |
| **`$lt`** | Less than | `{ age: { $lt: 40 } }` | Documents where age is < 40. |
| **`$lte`** | Less than or equal | `{ age: { $lte: 40 } }` | Documents where age is $\le$ 40. |
| **`$in`** | In an array | `{ status: { $in: ["active", "pending"] } }` | Status is either "active" OR "pending". |

### 4.2 Logical Operators

Logical operators allow you to combine multiple query conditions.

*   **`$and`**: Returns documents that satisfy **all** conditions.
    *   `db.users.find({ $and: [ { age: { $gt: 20 } }, { status: "active" } ] })`
*   **`$or`**: Returns documents that satisfy **at least one** condition.
    *   `db.users.find({ $or: [ { name: "Alice" }, { age: { $lt: 25 } } ] })`
*   **`$not`**: Inverts the effect of a query operator.
*   **`$nor`**: Returns documents that fail **all** the provided conditions.

### 4.3 Element Operators

These operators check for the presence or type of fields within a document.

| Operator | Description | Example |
| :--- | :--- | :--- |
| **`$exists`** | Matches documents that have (or don't have) a specific field. | `{ email: { $exists: true } }` |
| **`$type`** | Matches documents where the field is a specific BSON type. | `{ age: { $type: "number" } }` |

### 4.4 Array Operators

Since MongoDB is document-oriented, it handles arrays natively. These operators allow you to query inside those arrays.

*   **`$all`**: Matches arrays that contain **all** the specified elements.
    *   `db.users.find({ tags: { $all: ["admin", "editor"] } })`
*   **`$elemMatch`**: Matches documents where at least one element in the array satisfies **all** specified criteria.
    *   `db.users.find({ scores: { $elemMatch: { value: { $gt: 80 }, subject: "Math" } } })`
*   **`$size`**: Matches arrays of a specific length.
    *   `db.users.find({ tags: { $size: 3 } })`

[↑ Back to Table of Contents](#table-of-contents)
