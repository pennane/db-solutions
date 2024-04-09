USE bigcompany;

SELECT *
FROM emp_list
WHERE PostalCode = '00940'
LIMIT 5;

UPDATE emp_list
SET PostalArea = 'Vihti'
WHERE EmployeeID = '15236';

SELECT *
FROM emp_list
WHERE PostalCode = '00940'
LIMIT 5;