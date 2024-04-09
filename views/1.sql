USE bigcompany;

CREATE VIEW emp_list AS
SELECT e.EmployeeID,
  e.FirstName,
  e.LastName,
  e.StreetAddress,
  p.PostalCode,
  p.PostalArea
FROM Employee e
  JOIN Postalarea p ON e.PostalCode = p.PostalCode;