
select * from Employee;

-- we can see who reports to who in the company
select 
	e.FirstName || ' ' || e.LastName as reportee, 
	e2.FirstName || ' ' || e2.LastName as reports_to 
from Employee e
join Employee e2 on e.ReportsTo = e2.EmployeeId;

-- we can match each customer with the employee who helped them make a purchase
SELECT 
	e.FirstName as employee_first_name, e.LastName as employee_last_name,
	c.FirstName as customer_first_name, c.LastName as customer_last_name
from Employee e 
inner join Customer c on e.EmployeeId = c.SupportRepId
order by e.FirstName asc; -- this orders the result set by the employee FirstName column
-- use asc for ascending, desc for descending

-- here we get the count of how many customers each employee helped. If no customers were helped
-- then the employee is not included in the result set
select
	e.FirstName || ' ' || e.LastName as employee,
	-- count is an aggregate function: here it "counts" how many matching records there are in the
	-- customer table records with employee records
	count(c.CustomerId) as customers_helped
from Employee e
join Customer c on e.EmployeeId = c.SupportRepId 
group by employee
order by employee asc;
-- sum is also a good aggregate function to keep in mind for QC

-- scalar functions work on individual data points. Here each record will have its FirstName
-- column in all lowercase, the LastName column in all uppercase
select lower(FirstName), upper(LastName) from Employee;

-- here we calculate how much each employee has assisted on in sales
-- note how we filter the results to return only those who made more than 750 in total sales
select
	e.FirstName || ' ' || e.LastName as employee,
	sum(i.Total) as sales
from Employee e
join Customer c on e.EmployeeId = c.SupportRepId 
join Invoice i on c.CustomerId = i.CustomerId 
group by employee
having sales >= 750 -- note we use "having" instead of "where" to filter aggregated results
order by employee asc;

-- here we can see we can join on many tables together to get access to the data
-- and the organization of the data we need
SELECT 
	t.Name as track,
	a.Title as album,
	ar.Name as artist,
	g.Name as genre
from Track t 
join Album a on t.AlbumId  = a.AlbumId
join Artist ar on a.ArtistId = ar.ArtistId 
join Genre g on t.GenreId = g.GenreId 
order by ar.name asc;

-- we can get all the data from the "left" table whether or not there is a match
select 
	e.FirstName || ' ' || e.LastName as reportee, 
	e2.FirstName || ' ' || e2.LastName as reports_to 
from Employee e left join Employee e2 on e.ReportsTo = e2.EmployeeId;

-- we can get all the data from the "right" table whether or not there is a match
select 
	e.FirstName || ' ' || e.LastName as reportee, 
	e2.FirstName || ' ' || e2.LastName as reports_to 
from Employee e right join Employee e2 on e.ReportsTo = e2.EmployeeId;

-- we can get all the data from both tables whether or not there is a match
select 
	e.FirstName || ' ' || e.LastName as reportee, 
	e2.FirstName || ' ' || e2.LastName as reports_to 
from Employee e full join Employee e2 on e.ReportsTo = e2.EmployeeId;


-- we can match all records with all possible combinations between the two tables
select 
	e.FirstName || ' ' || e.LastName as reportee, 
	e2.FirstName || ' ' || e2.LastName as reports_to 
from Employee e cross join Employee e2;

-- if you want to combine your data into shared columns you can use a union to
-- accomplish this. Note here we store the first and last name of employees and customers
-- in a single column for each name type
select Firstname, Lastname from Employee
union
select FirstName, Lastname from Customer;






















