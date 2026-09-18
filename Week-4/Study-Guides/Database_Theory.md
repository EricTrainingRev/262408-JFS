# Database Theory & AWS RDS

## Table of Contents

* [1. High-Level Overview: What is a Database?](#1-high-level-overview-what-is-a-database)
* [2. Relational Databases (RDBMS)](#2-relational-databases-rdbms)
    * [2.1 What is SQL?](#21-what-is-sql)
    * [2.2 The ACID Standard: Ensuring Consistency](#22-the-acid-standard-ensuring-consistency)
* [3. SQL vs. NoSQL: Choosing the Right Tool](#3-sql-vs-nosql-choosing-the-right-tool)
* [4. AWS RDS: Relational Database Service](#4-aws-rds-relational-database-service)
* [5. Summary Checklist](#5-summary-checklist)

***

## 1. High-Level Overview: What is a Database?

At its simplest, a **Database** is an organized collection of structured information, or data, typically stored electronically in a computer system. 

While you could store data in a simple text file or a spreadsheet, a database management system (DBMS) provides the power to handle massive amounts of data, ensure data integrity, and allow multiple users to access the data simultaneously without corruption.

### Why not just use a Spreadsheet?
* **Scale:** Databases can handle terabytes of data; spreadsheets struggle after a few thousand rows.
* **Concurrency:** Databases allow hundreds of users to read/write at once; spreadsheets often "lock" when one person is editing.
* **Relationships:** Databases excel at connecting different types of data (e.g., connecting a "Customer" to an "Order").

[↑ Back to Table of Contents](#table-of-contents)
***

*To manage these complex relationships effectively, we rely on a specific architecture known as the Relational model.*

## 2. Relational Databases (RDBMS)

A **Relational Database Management System (RDBMS)** is a type of database that stores data in tables consisting of rows and columns. These tables are linked to one another using unique identifiers called **Keys**.

### 2.1 What is SQL?

**SQL (Structured Query Language)** is the standardized programming language used to communicate with an RDBMS. It is not a "coding language" in the same way Python is; rather, it is a **declarative language** used to tell the database *what* you want, rather than *how* to get it.

**Core SQL Operations (CRUD):**
* **C**reate: `INSERT INTO users (name) VALUES ('Alice');`
* **R**ead: `SELECT * FROM users WHERE name = 'Alice';`
* **U**pdate: `UPDATE users SET name = 'Bob' WHERE name = 'Alice';`
* **D**elete: `DELETE FROM users WHERE name = 'Bob';`

### 2.2 The ACID Standard: Ensuring Consistency

In a relational database, **Consistency** is a core requirement. To guarantee that the database remains in a valid state even in the event of errors or crashes, RDBMS follow the **ACID** properties:

| Property | Name | Meaning |
| :--- | :--- | :--- |
| **A** | **Atomicity** | "All or nothing." A transaction is treated as a single unit. If one part fails, the whole transaction is rolled back. |
| **C** | **Consistency** | A transaction transforms the database from one valid state to another, respecting all rules (constraints). |
| **I** | **Isolation** | Concurrent transactions do not interfere with each other; they appear to run sequentially. |
| **D** | **Durability** | Once a transaction is committed, it remains saved even in the event of a system failure. |

> [!IMPORTANT]
> **The "Bank Transfer" Example of Atomicity:** If you transfer \$100 from Account A to Account B, the database must subtract \$100 from A **and** add \$100 to B. If the system crashes halfway through, Atomicity ensures the money isn't "lost in the void"—the entire transaction is undone.

[↑ Back to Table of Contents](#table-of-contents)
***

*While RDBMS are the industry standard for structured data, modern application needs have given rise to an alternative: NoSQL.*

## 3. SQL vs. NoSQL: Choosing the Right Tool

The choice between a Relational (SQL) and Non-Relational (NoSQL) database is one of the most important architectural decisions a developer makes.

| Feature | **SQL (Relational)** | **NoSQL (Non-Relational)** |
| :--- | :--- | :--- |
| **Data Model** | Structured (Tables/Rows/Columns). | Unstructured/Semi-structured (Document, Key-Value, Graph). |
| **Schema** | **Rigid:** You must define the structure before inserting data. | **Dynamic:** You can add data without a predefined schema. |
| **Scaling** | **Vertical:** You make the server bigger (more RAM/CPU). | **Horizontal:** You add more servers (sharding). |
| **Relationships** | Excellent at complex joins. | Generally poor at joins; data is often "nested." |
| **Best Use Case** | Financial systems, ERPs, highly structured data. | Real-time big data, social media feeds, IoT sensor data. |

[↑ Back to Table of Contents](#table-of-contents)
***

*Managing these databases in a cloud environment can be complex, which is why AWS provides a specialized service to handle the heavy lifting.*

## 4. AWS RDS: Relational Database Service

**AWS RDS (Relational Database Service)** is a managed service that makes it easy to set up, operate, and scale a relational database in the AWS Cloud.

### The "Managed" Advantage
In a traditional setup, you are responsible for "Undifferentiated Heavy Lifting"—tasks that are necessary but don's add unique value to your business. RDS takes these tasks away from you:

| Task | **Self-Managed (EC2)** | **Managed (AWS RDS)** |
| :--- | :--- | :--- |
| **Patching** | You must manually update the OS and DB. | **Automated** by AWS. |
| **Backups** | You must write scripts to snapshot data. | **Automated** snapshots and point-in-time recovery. |
| **High Availability**| You must manually set up replication/failover. | **Multi-AZ deployment** with one click. |
| **Scaling** | Requires manual hardware upgrades. | **Push-button scaling** of compute and storage. |

### Supported Engines
RDS is not a single database; it is a platform that supports several popular engines:
*   **MySQL**
*   **PostgreSQL**
*   **MariaDB**
*   **Oracle**
*   **Microsoft SQL Server**
*   **Amazon Aurora** (AWS's proprietary, high-performance engine)

[↑ Back to Table of Contents](#table-of-contents)
