# SQL Joins

## Table of Contents

* [1. What is a Join?](#1-what-is-a-join)
* [2. Aliases](#2-aliases)
* [3. Inner Join](#3-inner-join)
* [4. Left and Right Joins](#4-left-and-right-joins)
* [5. Equi and Theta Joins](#5-equi-and-theta-joins)
* [6. Outer Joins](#6-outer-joins)
* [7. Cross Join](#7-cross-join)
* [8. Troubleshooting](#8-troubleshooting)

***

## 1. What is a Join?

In a relational database, data is often spread across multiple tables to reduce redundancy (a process called **normalization**). A **JOIN** is an SQL operation used to combine rows from two or more tables based on a related column between them.

Without joins, you would have to perform multiple separate queries and manually stitch the data together in your application code. Joins allow the database engine to perform this relational logic efficiently.

> [!IMPORTANT]
> Joins rely on **relationships** established via **Primary Keys** (unique identifiers in a table) and **Foreign Keys** (references to a primary key in another table).

[↑ Back to Table of Contents](#table-of-contents)
***

*Understanding that joins are the bridge between related tables, let's look at how we use aliases to make these complex queries much easier to write and read.*

## 2. Aliases

**Aliases** are temporary names assigned to columns or tables during the execution of a query. They are created using the `AS` keyword.

In the context of Joins, aliases are not just for readability; they are often **essential** for simplifying complex queries involving multiple tables.

### Use Cases for Aliases in Joins
1. **Shortening Table Names:** Instead of writing `employees.department_id`, you can alias `employees` as `e` and write `e.department_id`.
2. **Resolving Ambiguity:** If two tables both have a column named `id`, aliasing them (e.g., `e.id` vs `d.id`) tells the database exactly which one you mean.
3. **Naming Calculated Columns:** Giving a meaningful name to a column created by a join condition or a calculation.

**Example:**
```sql
SELECT e.first_name, d.department_name
FROM employees AS e
JOIN departments AS d ON e.dept_id = d.id;
```

[↑ Back to Table of Contents](#table-of-contents)
***

*With aliases in place to manage our table names, we can now dive into the most common way to combine data: the Inner Join.*

## 3. Inner Join

The `INNER JOIN` is the most common type of join. It returns only the rows where there is a **match in both tables** based on the join condition.

If a row in Table A does not have a corresponding match in Table B, that row is excluded from the result set entirely.

**Example:**
Find all employees who belong to a department.
```sql
SELECT e.name, d.dept_name
FROM employees AS e
INNER JOIN departments AS d ON e.dept_id = d.id;
```
*If an employee has a `NULL` `dept_id`, or a department has no employees, they will **not** appear in this list.*

[↑ Back to Table of Contents](#table-of-contents)
***

*Sometimes, an Inner Join is too restrictive. If you need to keep records from one table even when no match exists in the other, you should use Left or Right joins.*

## 4. Left and Right Joins

`LEFT` and `RIGHT` joins are types of **Outer Joins** that allow you to retain rows even when there is no match in the opposing table.

### Left Join (Left Outer Join)
Returns **all** records from the **left** table, and the matched records from the right table. If there is no match, the result will contain `NULL` values for all columns of the right table.

**Example:**
List *all* employees, including those who haven't been assigned to a department yet.
```sql
SELECT e.name, d.dept_name
FROM employees AS e
LEFT JOIN departments AS d ON e.dept_id = d.id;
```

### Right Join (Right Outer Join)
Returns **all** records from the **right** table, and the matched records from the left table. If there is no match, the result will contain `NULL` values for all columns of the left table.

**Example:**
List *all* departments, including those that currently have no employees.
```sql
SELECT e.name, d.dept_name
FROM employees AS e
RIGHT JOIN departments AS d ON e.dept_id = d.id;
```

[↑ Back to Table of Contents](#table-of-contents)
***

*While Left and Right joins focus on which side of the data to preserve, we can also categorize joins by the mathematical logic used in their comparison clauses.*

## 5. Equi and Theta Joins

These terms describe the **logic** used in the `ON` clause of a join.

| Join Type | Logic Operator | Description |
| :--- | :--- | :--- |
| **Equi Join** | `=` (Equality) | Matches columns that are exactly equal. |
| **Theta Join** | `>`, `<`, `!=`, etc. | Matches columns based on a range or inequality. |

### Equi Join
An **Equi Join** is a join that uses the **equality operator (`=`)** to compare columns. This is what most developers are doing 99% of the time.

```sql
-- This is an Equi Join
SELECT * FROM employees JOIN departments ON employees.dept_id = departments.id;
```

### Theta Join
A **Theta Join** is a more general term. It refers to any join that uses a **comparison operator** other than equality.

```sql
-- This is a Theta Join (finding employees paid more than a specific threshold)
SELECT e.name, s.min_salary
FROM employees e
JOIN salary_grades s ON e.salary > s.min_salary;
```

[↑ Back to Table of Contents](#table-of-contents)
***

*If you need to capture data that falls outside the strict intersection of an Inner Join, we move into the broader realm of Outer Joins.*

## 6. Outer Joins

An **Outer Join** is a category of joins that returns all rows from at least one of the tables, even if the join condition is not met. 

| Join Type | Result Includes... |
| :--- | :--- |
| **Inner Join** | Only matches. |
| **Left Join** | All Left + Matches from Right. |
| **Right Join** | All Right + Matches from Left. |
| **Full Join** | Everything from both tables. |

### Full Outer Join
A `FULL OUTER JOIN` returns all records when there is a match in **either** the left or the right table. It essentially combines the results of both a `LEFT JOIN` and a `RIGHT JOIN`.

*Note: Some databases (like MySQL) do not support `FULL OUTER JOIN` directly and require a `UNION` of a `LEFT` and `RIGHT` join to achieve the same result.*

[↑ Back to Table of Contents](#table-of-contents)
***

*Finally, let's look at a special case where we don't use a comparison condition at all, but instead create every possible combination of rows.*

## 7. Cross Join

A **Cross Join** produces a **Cartesian Product** of the two tables. This means every single row from the first table is paired with every single row from the second table.

Unlike other joins, a `CROSS JOIN` **does not require a join condition** (`ON` clause).

**Warning:** If Table A has 100 rows and Table B has 100 rows, a `CROSS JOIN` will result in **10,000 rows**. Using this accidentally on large tables can crash a query or severely impact performance.

**Example:**
Generate all possible combinations of colors and sizes for a clothing inventory.
```sql
SELECT colors.name, sizes.name
FROM colors
CROSS JOIN sizes;
```

[↑ Back to Table of Contents](#table-of-contents)
***

## 8. Troubleshooting

### The NULL Trap
When using `LEFT` or `RIGHT` joins, unmatched rows return `NULL`. 
* **Symptom:** A calculation (like `price * tax`) returns `NULL` unexpectedly.
* **Diagnosis:** The join failed to find a match, resulting in a `NULL` value being passed into your math.
* **Rescue:** Use `COALESCE(column, 0)` to provide a default value.

### Performance Degradation
* **Symptom:** Queries become exponentially slower as the table grows.
* **Diagnosis:** Missing indexes on the columns used in the `ON` clause.
* **Rescue:** Create an index on the Foreign Key columns involved in the join.

[↑ Back to Table of Contents](#table-of-contents)