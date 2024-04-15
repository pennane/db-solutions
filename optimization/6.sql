USE bigcompany;
set global slow_query_log = ON;
set global slow_launch_time = 2;
show variables like '%slow%';

SELECT a.EmployeeID,
  a.LastName,
  b.FirstName
FROM employee a,
  employee b
WHERE a.LastName LIKE '%Rojola%'
  AND b.LastName LIKE '%Mynttinen';