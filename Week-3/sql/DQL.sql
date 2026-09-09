
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