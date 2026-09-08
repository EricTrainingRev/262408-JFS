# SQL Transactions

## Table of Contents

* [1. High-Level Overview: The Concept of Atomicity](#1-high-level-overview-the-concept-of-atomicity)
* [2. The Foundation: CRUD Operations](#2-the-foundation-crud-operations)
* [3. The Gold Standard: ACID Properties](#3-the-gold-standard-acid-properties)
* [4. The Transaction Lifecycle: Commit & Rollback](#4-the-transaction-lifecycle-commit--rollback)
* [5. Managing Concurrency: Isolation Levels](#5-managing-concurrency-isolation-levels)

***

## 1. High-Level Overview: The Concept of Atomicity

In a database, a "**transaction**" is more than just a single command. It is a **logical unit of work** that may consist of many different operations. The core philosophy of a transaction is **Atomicity**: the idea that a series of steps should be treated as a single, indivisible unit.

Imagine you are transferring money between two bank accounts. This requires two steps:
1. Subtracting money from Account A.
2. Adding money to Account B.

If the system crashes after step 1 but before step 2, the money effectively vanishes. A transaction ensures that either **both** steps succeed, or **neither** of them happens, keeping the database in a valid state.

[↑ Back to Table of Contents](#table-of-contents)

***

*To understand how we manage these units of work, we must first look at the fundamental operations that occur within them: the CRUD operations.*

## 2. The Foundation: CRUD Operations

Transactions are almost always used to wrap **CRUD** operations. **CRUD** is an acronym representing the four basic functions of persistent storage:

| Acronym | Full Name | SQL Command | Purpose |
| :--- | :--- | :--- | :--- |
| **C** | **Create** | `INSERT` | Adding new data. |
| **R** | **Read** | `SELECT` | Retrieving existing data. |
| **U** | **Update** | `UPDATE` | Modifying existing data. |
| **D** | **Delete** | `DELETE` | Removing data. |

In a transactional context, we focus on the "mutating" operations (**Create**, **Update**, and **Delete**). While a `SELECT` (**Read**) doesn't change the data, it is often part of a transaction to ensure the data you are reading doesn't change while you are making decisions based on it.

[↑ Back to Table of Contents](#table-of-contents)

***

*While CRUD defines what we do, **ACID** defines the quality standards that a database must uphold to ensure those operations are safe and reliable.*

## 3. The Gold Standard: ACID Properties

For a database to be considered "transactional," it must strictly adhere to the **ACID** properties. These four principles guarantee that even in the event of a power failure, crash, or error, your data remains trustworthy.

### **A - Atomicity** ("All or Nothing")
A transaction is an **atomic** unit. If any part of the transaction fails, the entire transaction is aborted, and the database is rolled back to its state before the transaction started.

### **C - Consistency** ("Following the Rules")
A transaction must transition the database from one valid state to another. It ensures that all data written follows all defined rules, including **constraints**, **cascades**, and **triggers**.

### **I - Isolation** ("Invisible Intermediate States")
Transactions often happen simultaneously. **Isolation** ensures that the intermediate state of a transaction is invisible to other concurrent transactions. This prevents one user from seeing "half-finished" work from another user.

### **D - Durability** ("Permanent Success")
Once a transaction has been **committed**, it remains committed, even in the event of a system failure (e.g., power outage). The changes are recorded in non-volatile memory (the disk).

[↑ Back to Table of Contents](#table-of-contents)

***

*Having established the ACID principles, we now look at the practical commands used to control the start, end, and failure of a transaction.*

## 4. The Transaction Lifecycle: Commit & Rollback

The lifecycle of a transaction is managed by two primary commands: `COMMIT` and `ROLLBACK`.

### Commit
The `COMMIT` command tells the database that the transaction has been completed successfully. It makes all changes made during the transaction **permanent** and visible to other users.

```sql
BEGIN TRANSACTION;
  UPDATE Accounts SET Balance = Balance - 100 WHERE UserID = 1;
  UPDATE Accounts SET Balance = Balance + 100 WHERE UserID = 2;
COMMIT; -- Both changes are now permanent.
```

### Rollback
The `ROLLBACK` command is the "undo" button. If an error occurs during the transaction, `ROLLBACK` is used to abort the transaction and revert the database to the state it was in before the transaction began.

```sql
BEGIN TRANSACTION;
  UPDATE Accounts SET Balance = Balance - 100 WHERE UserID = 1;
  -- ERROR OCCURS HERE (e.g., system crash or constraint violation)
ROLLBACK; -- The money is returned to Account 1 as if nothing happened.
```

[↑ Back to Table of Contents](#table-of-contents)

***

*While isolation is a core requirement of ACID, there is a constant tension between keeping transactions perfectly isolated and allowing many users to work at the same time. This tension is managed through **Isolation Levels**.*

## 5. Managing Concurrency: Isolation Levels

In a high-traffic database, many users are performing transactions simultaneously. If we isolated every transaction perfectly, the database would be incredibly slow because users would have to wait in a single-file line.

To solve this, SQL provides different **Isolation Levels**. These levels allow developers to choose the balance between **Data Integrity** (strictness) and **Concurrency** (speed/throughput).

> [!IMPORTANT]
> To understand isolation levels, you must understand the "anomalies" they are designed to prevent:
> 1. **Dirty Read:** Reading data that has been modified by another transaction but **not yet committed**.
> 2. **Non-Repeatable Read:** Reading the same row twice in one transaction and getting **different values** because another transaction modified it in between.
> 3. **Phantom Read:** Running a query twice and getting **different sets of rows** because another transaction inserted/deleted rows in between.

### Comparison of Isolation Levels

| Isolation Level | Dirty Reads | Non-Repeatable Reads | Phantom Reads | Performance |
| :--- | :---: | :---: | :---: | :--- |
| **Read Uncommitted** | Allowed | Allowed | Allowed | 🚀 Highest |
| **Read Committed** | **Prevented** | Allowed | Allowed | Fast |
| **Repeatable Read** | **Prevented** | **Prevented** | Allowed | Moderate |
| **Serializable** | **Prevented** | **Prevented** | **Prevented** | 🐢 Lowest |

> [!TIP]
> The different levels of isolation provide varying trade-offs
> * **Lower Isolation (e.g., Read Uncommitted):** High performance, but high risk of seeing incorrect or "temporary" data.
> * **Higher Isolation (e.g., Serializable):** Perfect data integrity, but low performance because transactions must wait for each other to finish.

[↑ Back to Table of Contents](#table-of-contents)
