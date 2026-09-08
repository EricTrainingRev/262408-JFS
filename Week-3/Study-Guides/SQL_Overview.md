# SQL Language Overview

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. The SQL Sublanguage Ecosystem](#2-the-sql-sublanguage-ecosystem)
    * [2.1 Classification Overview](#21-classification-overview)
* [3. Data Definition Language (DDL)](#3-data-definition-language-ddl)
* [4. Data Manipulation Language (DML)](#4-data-manipulation-language-dml)
* [5. Data Query Language (DQL)](#5-data-query-language-dql)
* [6. Data Control Language (DCL)](#6-data-control-language-dcl)
* [7. Transaction Control Language (TCL)](#7-transaction-control-language-tcl)

***

## 1. High-Level Overview

**SQL (Structured Query Language)** is the standard language used to communicate with Relational Database Management Systems (RDBMS). While most developers associate SQL primarily with retrieving data, it is actually a multi-functional language divided into several specialized **sublanguages**.

These sublanguages allow users to perform different categories of tasks: defining the structure of the database, managing the data within that structure, querying the data for insights, controlling access to the data, and managing the integrity of transactions.

[↑ Back to Table of Contents](#table-of-contents)

***

*Understanding that SQL is a collection of specialized tools, let's look at how these tools are categorized into distinct functional groups.*

## 2. The SQL Sublanguage Ecosystem

SQL is not a single monolithic command set. It is divided into functional categories based on the type of operation being performed.

### 2.1 Classification Overview

| Sublanguage | Full Name | Primary Purpose | Core Operations |
| :--- | :--- | :--- | :--- |
| **DDL** | Data Definition Language | Defines/modifies the **structure** (schema). | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` |
| **DML** | Data Manipulation Language | Manages the **content** (rows) within structures. | `INSERT`, `UPDATE`, `DELETE` |
| **DQL** | Data Query Language | Retrieves and **fetches** data. | `SELECT` |
| **DCL** | Data Control Language | Manages **permissions** and security. | `GRANT`, `REVOKE` |
| **TCL** | Transaction Control Language | Manages **database transactions**. | `COMMIT`, `ROLLBACK`, `SAVEPOINT` |

**DML vs. DQL**
A common point of confusion is the distinction between DML and DQL. Technically, `SELECT` (DQL) is often grouped under the broader umbrella of DML in some older textbooks. However, in modern technical practice, we separate them: **DML** is for *changing* data, while **DQL** is for *reading* data.

[↑ Back to Table of Contents](#table-of-contents)

***

*With the ecosystem mapped out, we will now dive into the first layer: defining the skeleton of the database using DDL.*

## 3. Data Definition Language (DDL)

**DDL** is used to define the database schema. It deals with the "containers" (tables, indexes, views, databases) rather than the data inside them. DDL commands are typically **auto-committed**, meaning once you run them, the change is permanent and cannot be rolled back in most RDBMS.

### Core DDL Commands

*   **`CREATE`**: Creates a new object (e.g., a table, a database, an index).
    ```sql
    CREATE TABLE Users (
        UserID INT PRIMARY KEY,
        Username VARCHAR(50),
        Email VARCHAR(100)
    );
    ```
*   **`ALTER`**: Modifies the structure of an existing object (e.g., adding a column).
    ```sql
    ALTER TABLE Users ADD Age INT;
    ```
*   **`DROP`**: Deletes an entire object and its structure from the database.
    ```sql
    DROP TABLE Users;
    ```
*   **`TRUNCATE`**: Removes all records from a table, but keeps the table structure intact.
    *   *Note:* `TRUNCATE` is faster than `DELETE` because it doesn't log individual row deletions.

> [!IMPORTANT]
> **DDL is Destructive:** Be extremely cautious with `DROP` and `TRUNCATE`. Unlike DML, these operations usually bypass transaction logs for individual rows, making "undoing" them nearly impossible without a full database backup.

[↑ Back to Table of Contents](#table-of-contents)

***

*Now that we have created our tables, we need a way to populate and modify the information they hold. This is the role of DML.*

## 4. Data Manipulation Language (DML)

**DML** is used to manage the actual data stored within the database objects. While DDL builds the "house," DML manages the "furniture" (the rows of data).

### Core DML Commands

*   **`INSERT`**: Adds new rows to a table.
    ```sql
    INSERT INTO Users (UserID, Username, Email) 
    VALUES (1, 'jdoe', 'jdoe@example.com');
    ```
*   **`UPDATE`**: Modifies existing data within a table.
    ```sql
    UPDATE Users SET Email = 'john.doe@newmail.com' WHERE UserID = 1;
    ```
*   **`DELETE`**: Removes specific rows from a table.
    ```sql
    DELETE FROM Users WHERE UserID = 1;
    ```

> [!WARNING]
> **The Missing WHERE Clause:** Always double-check your `WHERE` clause in `UPDATE` and `DELETE` statements. Running `DELETE FROM Users;` without a `WHERE` clause will wipe every single row in the table!

[↑ Back to Table of Contents](#table-of-contents)

***

*The data is now structured and populated. The most frequent task for any user is to extract specific information from this data, which leads us to DQL.*

## 5. Data Query Language (DQL)

**DQL** is used to fetch data from the database. It is the most widely used part of SQL by analysts and application developers.

### Core DQL Command: `SELECT`

The `SELECT` statement is the heart of DQL. It allows you to specify exactly which columns and which rows you want to see.

**Basic Syntax Structure:**
```sql
SELECT column1, column2
FROM table_name
WHERE condition
ORDER BY column1 ASC;
```

**Key DQL Components:**
*   **`SELECT`**: Specifies the columns to retrieve.
*   **`FROM`**: Specifies the table(s) to query.
*   **`WHERE`**: Filters the rows based on specific criteria.
*   **`GROUP BY`**: Aggregates data (often used with `COUNT`, `SUM`, `AVG`).
*   **`ORDER BY`**: Sorts the resulting data.

**DQL vs. DML Comparison**
| Feature | DQL (`SELECT`) | DML (`INSERT`/`UPDATE`/`DELETE`) |
| :--- | :--- | :--- |
| **Primary Action** | Reading / Fetching | Writing / Modifying |
| **Impact on Data** | None (Read-only) | Direct change to stored values |
| **Typical User** | Data Analysts, App UI | Backend Services, Data Entry |

[↑ Back to Table of Contents](#table-of-contents)

***

*Managing data and querying it is one thing, but in a multi-user environment, we must strictly control who is allowed to perform these actions.*

## 6. Data Control Language (DCL)

**DCL** is the security layer of SQL. It is used to grant or revoke permissions to database users, ensuring that sensitive data is only accessible to authorized personnel.

### Core DCL Commands

*   **`GRANT`**: Gives a user permission to perform specific tasks (e.g., reading a table).
    ```sql
    GRANT SELECT, INSERT ON Users TO 'analyst_user';
    ```
*   **`REVOKE`**: Removes permissions previously granted to a user.
    ```sql
    REVOKE INSERT ON Users FROM 'analyst_user';
    ```

> [!TIP]
> **Principle of Least Privilege:** In database administration, always grant the *minimum* level of access required for a user to do their job. Do not grant `DROP` or `DELETE` permissions to an application that only needs to read data.

[↑ Back to Table of Contents](#table-of-contents)

***

*Finally, when performing complex operations involving multiple steps, we need a way to ensure that either everything succeeds or nothing changes at all. This is where TCL comes in.*

## 7. Transaction Control Language (TCL)

**TCL** manages **Transactions**. A transaction is a logical unit of work that consists of one or more DML statements. TCL ensures the **ACID** properties (Atomicity, Consistency, Isolation, Durability) of the database.

### Core TCL Commands

*   **`COMMIT`**: Saves all changes made during the current transaction permanently to the database.
    ```sql
    UPDATE Accounts SET Balance = Balance - 100 WHERE UserID = 1;
    UPDATE Accounts SET Balance = Balance + 100 WHERE UserID = 2;
    COMMIT; -- Both updates are now permanent.
    ```
*   **`ROLLBACK`**: Undoes all changes made in the current transaction if an error occurs, returning the database to its previous state.
    ```sql
    -- If something fails midway:
    ROLLBACK; 
    ```
*   **`SAVEPOINT`**: Creates a "checkpoint" within a transaction that allows you to roll back to a specific point rather than the very beginning.
    ```sql
    SAVEPOINT point1;
    -- ... some operations ...
    ROLLBACK TO point1; -- Undoes only what happened after point1.
    ```

**The "Bank Transfer" Example (The classic use case for TCL)**
Imagine transferring \$100 from Account A to Account B. This requires two steps:
1. Subtract \$100 from A.
2. Add \$100 to B.

If the system crashes after step 1 but before step 2, the \$100 vanishes. **TCL prevents this** by wrapping both steps in a single transaction. If step 2 fails, the `ROLLBACK` command ensures step 1 is undone, and the money stays in Account A.

[↑ Back to Table of Contents](#table-of-contents)
