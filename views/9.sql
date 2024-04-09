USE public;

UPDATE `public`.`emp_list`
SET LastName = 'Broidi'
WHERE EmployeeID = '15236';

SELECT LastName
FROM `public`.`emp_list`
WHERE EmployeeID = '15236';