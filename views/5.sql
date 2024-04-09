USE bigcompany;

UPDATE emp_list
SET LastName = 'Kauriainen'
WHERE EmployeeID = '15236';

SELECT LastName
FROM Employee
WHERE EmployeeID = '15236';