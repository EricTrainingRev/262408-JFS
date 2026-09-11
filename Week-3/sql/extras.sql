drop table if exists people;
drop table if exists employee;
drop table if exists date_joined;
drop table if exists customer;
drop table if exists body;
drop table if exists accounts;


-- CASCADE (SQLITE)
create table people(
	id integer primary key,
	name text
);

create table body(
	id integer primary key,
	person integer references people(id) on delete cascade
);

insert into people (name) values ('Billy');

insert into body (person) values (1);

delete from people where id = 1;

select * from body;


-- SUB QUERY (SQLITE)
create table employee(
	id integer primary key,
	name text not null,
	salary real check (salary >= 0)
);

insert into employee (name, salary) values ('Billy', 100), ('Saly', 130), ('Slagathor', 200);

select name from employee where salary >= (select avg(salary) from employee);

select printf('%.2f', avg(salary)) from employee;


-- SCHEMA (POSTGRES)
create schema example_schema;


-- NORMALIZATION

-- none
CREATE TABLE CustomerData_0NF (
    CustomerID INTEGER PRIMARY KEY,
    CustomerName TEXT,
    CustomerPhone TEXT,
    OrderIDs TEXT,
    ProductNames TEXT
);

-- 1NF: all columns contain atomic values; no repeating groups
CREATE TABLE CustomerData_1NF (
    CustomerID INTEGER,
    FirstName TEXT,
    LastName TEXT,
    CustomerPhone TEXT,
    OrderID INTEGER,
    ProductName TEXT,
    PRIMARY KEY (CustomerID, OrderID)
);


-- 2NF: in 1NF and no partial dependencies
-- every non-key attribute depends on the whole key
CREATE TABLE Customers_2NF (
    CustomerID INTEGER PRIMARY KEY,
    FirstName TEXT,
    LastName TEXT,
    CustomerPhone TEXT
);

CREATE TABLE Orders_2NF (
    OrderID INTEGER PRIMARY KEY,
    CustomerID INTEGER NOT NULL,
    ProductName TEXT,
    Category TEXT,
    FOREIGN KEY (CustomerID)
        REFERENCES Customers_2NF(CustomerID)
);

-- 3NF: in 2NF and no transitive dependencies
-- non-key attributes depend only on the key
CREATE TABLE Customers (
    CustomerID INTEGER PRIMARY KEY,
    FirstName TEXT,
    LastName TEXT,
    CustomerPhone TEXT
);

CREATE TABLE Orders (
    OrderID INTEGER PRIMARY KEY,
    CustomerID INTEGER NOT NULL,
    FOREIGN KEY (CustomerID)
        REFERENCES Customers(CustomerID)
);

CREATE TABLE Products (
    ProductID INTEGER PRIMARY KEY,
    ProductName TEXT NOT NULL,
    Category TEXT
);

CREATE TABLE OrderItems (
    OrderID INTEGER,
    ProductID INTEGER,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID)
        REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID)
        REFERENCES Products(ProductID)
);


-- INDEX (SQLITE)
create table people(
	id serial primary key,
	name text
);

create index names on people(name);


-- TRIGGER (SQLITE)
drop table if exists customer;
create table customer(
	id integer primary key,
	name text not null
);


drop table if exists date_joined;
create table date_joined(
	id integer primary key,
	customer_id integer references customer(id),
	date_joined text
);

create trigger log_date_joined
after insert on customer
for each row
begin
	insert into date_joined (customer_id, date_joined)
	values (NEW.id, datetime('now'));
end;

insert into customer (name) values ('Billy');

select * from customer c join date_joined l on c.id = l.customer_id;

-- STORED PROCEDURE (POSTGRES)
drop table if exists account;
create table account(
	id serial primary key,
	balance float check (balance >= 0)
);

insert into account values (default, 100), (default, 100);
drop procedure if exists transfer;
CREATE PROCEDURE transfer(
    accountOne integer,
    accountTwo integer,
    cash numeric,
    OUT rows_affected integer
)
LANGUAGE plpgsql
AS $$
DECLARE
    rows1 integer;
    rows2 integer;
BEGIN

    UPDATE account
    SET balance = balance + cash
    WHERE id = accountTwo;

 	GET DIAGNOSTICS rows1 = ROW_COUNT;

    UPDATE account
    SET balance = balance - cash
    WHERE id = accountOne;

    GET DIAGNOSTICS rows2 = ROW_COUNT;

    rows_affected := rows1 + rows2;

END;
$$;

select * from account;
call transfer(2,1,55.00,null);

-- USER DEFINED FUNCTION (POSTGRES)
CREATE OR REPLACE FUNCTION get_at_risk_accounts()
RETURNS TABLE(id int, balance float) AS $$
BEGIN
RETURN QUERY
SELECT *
FROM public."account" a
WHERE a.balance <= 50;
END;
$$ LANGUAGE plpgsql;

select * from get_at_risk_accounts();


-- MANUAL TRANSACTION CONTROL
drop table if exists accounts;
create table accounts(
	id integer primary key,
	balance float check (balance >= 0)
);


insert into accounts (id, balance) values (1, 100), (2, 100);	


select * from accounts;


begin;
	savepoint step_1;
	update accounts set balance = balance - 50 where id = 1;
	release savepoint step_1;
	savepoint step_2;
	update accounts set balance = balance + 50 where id = 2;
	rollback to step_2;
commit;

-- SEQUENCE (POSTGRES)
drop table if exists pets;

create sequence pet_id
start with 1
increment by 1;
create table pets(
	id integer primary key default nextval('pet_id'),
	name text
);

insert into pets values (default, 'Billy'), (default, 'Poodles');
select * from pets;





drop table if exists accounts;








