create table people(
	id serial primary key,
	name text,
	age int
);

/*
	insert is used to add a record to the table
*/
-- by default all values must be provided, default can be used when one is present
insert into people values (default, 'Billy', 24);
-- can specify what values you are providing as well
insert into people (name, age) values ('Sally', 25);
-- you can also insert multiple records at once
insert into people (name, age) values
	('Teddy', 42),
	('Manny', 15);

/*
	update is used to change one or more columns in a record.
*/
update people set name = 'Slagathor', age = 100000 where id = 2;
-- if you do not use "where" to specify the record/s to change all records will be changed
update people set name = 'whoops!';

/*
	delete is used to remove records from the table. Note that even if 0 records are deleted the query
	will be marked as being run successfully
*/
delete from people where id = 2;
-- same as update, no where clause to limit the action will delete all records from the table
-- this is a less efficient option than truncating the table
delete from people;







