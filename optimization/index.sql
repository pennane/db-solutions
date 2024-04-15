-- SHOW INDEX
-- FROM bigcompany.employee;
use bigcompany;

EXPLAIN
SELECT e.FirstName,
  e.LastName,
  d.Name AS DepartmentName
FROM Employee e
  JOIN Department d ON e.DepartmentID = d.DepartmentID
WHERE e.LastName = "Pulkkinen";

EXPLAIN
SELECT e.FirstName,
  e.LastName,
  d.Name AS DepartmentName
FROM Employee e FORCE INDEX (idx_last_name)
  JOIN Department d ON e.DepartmentID = d.DepartmentID
WHERE e.LastName = "Pulkkinen";

EXPLAIN
SELECT e.FirstName,
  e.LastName,
  d.Name AS DepartmentName
FROM Employee e USE INDEX (idx_last_name)
  JOIN Department d ON e.DepartmentID = d.DepartmentID
WHERE e.LastName = "Pulkkinen";

EXPLAIN
SELECT e.FirstName,
  e.LastName,
  d.Name AS DepartmentName
FROM Employee e IGNORE INDEX (idx_last_name)
  JOIN Department d ON e.DepartmentID = d.DepartmentID
WHERE e.LastName = "Pulkkinen";

CREATE INDEX idx_salary ON employee(Salary);
CREATE INDEX idx_Family_name(Family_name (6));