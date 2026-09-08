create table teams(
	id integer primary key,
	name varchar(30),
	location varchar(30)
);

create table players(
	id integer primary key,
	name varchar(30),
	home varchar(30),
	team_id int references teams(id)
);

drop table if exists teams;

insert into teams (name, location) values
	('Blazers', 'Portland'),
	('Nets', 'Brooklyn'),
	('Knicks','Manhattan');

insert into players (name, home, team_id) values
	('Billy', 'Los Angeles', 1),
	('Teddy', 'Chicago', 2),
	('Slagathor', 'Portland', 3);

/*
	select has a myriad of uses
*/
-- use * to get all records from a table
select * from teams;
-- you can limit this via the where keyword
select * from teams where location = 'Portland';
-- you can also select specific columns of data
select name from teams where location = 'Portland';
-- you can also do partial matches (% matches 0 or more characters, _ a single character)
-- use \ to search for % or _ if part of the text
select name from teams where location like '%land%';
-- you can even use sub queries
select name from teams where id = (select sum(id) from teams where id < 3);
-- you can limit the number of records you see
select * from teams limit 2;
-- and you can offset the starting point of the records
select * from teams limit 2 offset 1;
-- you can also control the order in which the data is presented (asc=ascend, desc=descend)
select * from teams order by location asc;

/*
	joins are used to combine data from multiple tables. Use the on keyword to specify how to match
	the data
*/
-- join is shorthand for inner join
select * from players join teams on players.team_id = teams.id;
-- get all records from the "left" table even if there are no matches
select * from players left join teams on players.team_id = teams.id and teams.id < 3;
-- same for right join
select * from players right join teams on players.team_id = teams.id and players.id < 3;
-- you can return all records even if there is not a match
select * from players full join teams on players.home = teams.location;
-- you can match all records with one table with all records on another
-- note the use of aliases here to keep track of which name comes from what table
select p.name, t.name from players p cross join teams t;

/*
	unions allow you to "stack" data instead of combining it in rows. For this to work the data types
	must be compatible
*/
select p.id, p.name from players p
union
select t.id, t.name from teams t;


/*
	scalar functions transform column data individually: use these to transform/augument the data
	returned in each row
*/
select upper(name) from players;
select lower(name) from players;
select length(name) from players;
select typeof(name) from players;
select abs(id * -1) from players;

/*
	Aggregate functions combine data from multiple rows. These can be used to calculate statistics and
	other meaningful information from the raw data
*/
-- the results from aggregate functions can be given custom column names to better reflect the data
select count(*) as player_count from players;
select sum(id) as id_total from players;

/*
	when working with aggregate data you will often need to make use of grouping your data and filtering
	by some kind of aggregate function. The example below make use of the chinook example database
*/


-- see how many albums each artist has released
-- result set is ordered by artist name ascending
select count(a.Title) as albums_released , ar.Name from Album a
join Artist ar on a.ArtistId = ar.ArtistId
group by ar.name order by ar.name asc;

-- see how much money in sales each employee has made for the company
-- note how we use "having" instead of "where" when filtering aggregated values
select e.FirstName || ' ' || e.Lastname as employee, sum(i.Total) as sales_total from Employee e
join Customer c on c.SupportRepId = e.EmployeeId
join Invoice i on i.CustomerId = c.CustomerId
group by employee having sales_total > 750.00;





