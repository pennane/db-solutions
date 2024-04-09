USE bigcompany;

UPDATE Employee
SET FirstName = 'Viivi'
WHERE EmployeeID = '68946';

SELECT FirstName
FROM emp_list
WHERE EmployeeID = '68946';