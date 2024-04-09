USE bigcompany;

CREATE VIEW bigsalaries AS
SELECT *
FROM Employee
WHERE Salary > 5000;

SELECT *
FROM bigsalaries
WHERE EmployeeID = "994";

UPDATE bigsalaries
SET Salary = 4800
WHERE EmployeeID = "994";

SELECT *
FROM bigsalaries
WHERE EmployeeID = "994";