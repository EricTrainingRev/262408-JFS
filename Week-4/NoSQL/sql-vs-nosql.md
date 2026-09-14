# General Comparison

## SQL
- tables facilitate relationships
- SQL works with rigid schemas (tables, indexes, schemas, etc.)
- scaling is handled vertically
- priority is data accuracy

## NoSQL
- collections have no built-in relationship tooling
- collections and data stored in them are schema-less
- collections scale horizontally
- priority is data availability

# ACID & BASE

## SQL ACID
- Atomic
    - an entire transaction succeeds/fails together
- Consistent
    - database rules should be enforced
- Isolated
    - transactions should not affect each other
- Durability
    - data should persist even in the event of catastrophic failure

## NoSQL BASE
- Basically Available
    - the priority is availability: this may have a cost in data accuracy
- Soft State
    - your collections will update over time even without direct interaction
- Eventual Consistency
    - your distributed nodes will become in sync over time

# MongoDB
- MongoDB is a NoSQL database
- the database has collections which store documents
    - collection ~= table
    - document ~= record
- MongoDB Compass is a GUI tool for interacting with MongoDB instances
- Mongosh is a CLI tool for interacting with MongoDB instances
    - comes embedded in MongoDB Compass
- MongoDB utilizes BSON in its queries (Binary JSON). This allows the database to work with a larger collection of types than if it was just JSON. It is also more performant.
- Mongosh syntax generally follows this pattern:
    - `db.collection_name.action();`