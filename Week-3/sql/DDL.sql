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





















