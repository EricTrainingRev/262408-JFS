-- DDL in-depth

-- The benefit of using relational databases is you can create connections between the data in your
-- tables. Think ownership relationships primarily

create table teams(
	id integer primary key,
	name text,
	city text
);

create table players(
	id integer primary key,
	name text,
	home_town text,
	team_id integer references teams(id)
);

insert into teams (name, city) values ('Blazers', 'Portland'), ('Knicks', 'New York');

-- Lillard's team Portland has the id of 1 so this is a valid query
insert into players (name, home_town, team_id) values ('Lillard', 'Oakland', 1);
-- there is no team currently with an id of 3, so this query will fail. The reason for this
-- is to prevent creating an orphaned record (no matching primary key with the foreign key)
insert into players (name, home_town, team_id) values ('Roy', 'Seatle', 3);

-- note no reference to teachers
create table students(
	id integer primary key,
	name text
);

-- note no reference to students
create table teachers(
	id integer primary key,
	name text
);

-- here is where the relationship is managed: this allows any number of students to have any number of
-- teachers, and vice versa
create table student_teacher_relationship(
	id integer primary key,
	student_id integer references students(id),
	teacher_id integer references teachers(id)
);

insert into students (name) values ('Billy');
insert into teachers (name) values ('Sally'), ('Slagathor');

insert into student_teacher_relationship (student_id, teacher_id) values (1, 1), (1, 2);

create table clients(
	id integer primary KEY
	-- assume there is more...
);


create table accounts(
	id integer primary key,
	client_id integer references clients(id),
	balance real check (balance >= 0.0),
	account_type text default ('checking')
);


-- tables can have their content altered:
--	table name can be changed
-- 	column names can be changed
--	column types can be changed (new type must be comptable with old type)
--	column constraints can be changed (think adding/removing a default value, etc.)


-- note this will fail of data can not be converted in the currently existing records
alter table accounts alter column balance type integer; -- NOTE: SQLite does not support this
-- you can also add columns
alter table accounts add column interest_rate real;
-- and remove them
alter table accounts drop column interest_rate;
-- also can rename columns
alter table accounts rename column balance to money; 
-- and change constraints (NOTE: these do not work in SQLite)
alter table accounts alter column account_type set not null;
alter table accounts alter column account_type drop not null;
-- you can rename your table
alter table accounts rename to bank_accounts;
















