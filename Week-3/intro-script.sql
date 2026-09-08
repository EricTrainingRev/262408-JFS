-- double dashes makes an in-line comment
/*
	this is how to make
	a multi-line comment
*/

/*
	In SQL there are 5 sublanguages that your queries can fall under
	- Data Definition Language (DDL)	-> These queries create the structure for your database
	- Data Manipulation language (DML)	-> These queries create the content for your database
	- Data Query Language (DQL)			-> These queries retrieve the content from your database
	- Data Control Language (DCL)		-> These queries control who can access/do what from your database
	- Transaction Control Language (TCL)-> The keywords in this sublanguage help us to manage our transactions
*/

-- A good place to start is DDL: if you want to interact with your database in a meaningful way you
-- need tables! The create keyword is use to make your tables
CREATE TABLE my_first_table(
	-- all columns need at least a name and a type
	first_column text,
	secound_column integer
);

-- Now we have a table and we can put some data inside of it. For this we move on to DML and we will use
-- the insert keyword
INSERT INTO my_first_table VALUES ('first column data', 10);

-- we can specify what data we want to insert
INSERT INTO my_first_table (secound_column) VALUES (10);

-- whatever order you specify the columns in the values should follow
INSERT INTO my_first_table (secound_column, first_column) VALUES (10,'first column data again');

-- you can insert multiple rows of data at once
INSERT INTO my_first_table VALUES 
	('more text data', 20),
	('text data again', 30);
	

-- let's fix my spelling mistake for the second column. We can alter the table using the
-- alter keyword
ALTER TABLE my_first_table RENAME secound_column to second_column;
	
-- Now that we have some data to work with we can start selecting it
SELECT * from my_first_table;

-- If you want specific data you can use the where keyword to limit the result set
SELECT * from my_first_table where second_column = 10;

-- you can filter by multiple checks
select * from my_first_table where second_column = 10 and first_column not null;

-- the where keyword is VERY important when you are editing records. If you do not limit the update you
-- are trying to make the entire table and its records can be updated
UPDATE my_first_table SET first_column = 'no longer null' where first_column is null;

-- without the where keyword this becomes a table-wide change
UPDATE my_first_table set second_column = 0;
	

-- if you want to clear out all data from a table use the truncate keyword
TRUNCATE table my_first_table; -- NOTE: sqlite does not support truncate
-- if for some reason you want to clear all data from a table without truncate you can use the delete
-- keyword
DELETE FROM my_first_table;
	
	
	
	
	
	
	
	
	
	
	