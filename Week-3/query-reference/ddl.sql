/*
	Schema creation: used to store related tables, views, etc
*/

create schema example_schema;

/*
	Table creation: some tools will default to a public schema if none is provided 
*/
create table example_schema.people(
	id serial primary key,
	name varchar(30),
	age int default(0) check (age >= 0)
);

/*
	index creation: useful for fast searching/retrieval of data
*/
create index name_index on example_schema.people(name); 
-- delete it with the drop keyword
drop index example_schema.name_index;

/*
	view creation: useful when you have a standard way of accessing data
*/
create view example_schema.average_age as
select avg(years) from example_schema.people;
-- delete it with the drop keyword
drop view example_schema.average_age;

/*
 	Alter table: change the structure of a table. MUCH easier to do when there is no data
*/

-- note this will fail if data can not be converted in the currently existing records
alter table example_schema.people alter column name type text;
-- you can also add columns
alter table example_schema.people add column grade int;
-- and remove them
alter table example_schema.people drop column grade;
-- also can rename columns
alter table example_schema.people rename column age to years; 
-- and change constraints
alter table example_schema.people alter column years set not null;
alter table example_schema.people alter column years drop not null;
-- you can rename your table
alter table example_schema.people rename to persons;

/*
	Truncate table: clear out the data from the table without destroying it
*/

truncate table example_schema.persons;

/*
	Drop: used to destroy a DDL created resource	
*/
-- use "if exists" to prevent the query from failing if the table does not exist
drop table if exists example_schema.persons;
-- add cascade to the end if there is data in the resource (tables, views, indexes, etc)
drop schema example_schema cascade;














