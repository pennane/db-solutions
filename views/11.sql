USE bigcompany;

CREATE VIEW bigsalaries2 AS
SELECT *
FROM Employee
WHERE Salary > 4000 WITH CHECK OPTION;

SELECT *
FROM bigsalaries2
WHERE EmployeeID = "994";

UPDATE bigsalaries2
SET Salary = 3800
WHERE EmployeeID = "994";

SELECT *
FROM bigsalaries2
WHERE EmployeeID = "994";