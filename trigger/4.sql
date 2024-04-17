USE bigcompany;

DROP PROCEDURE IF EXISTS getEmployeeSalary;

DELIMITER $$ 

CREATE PROCEDURE getEmployeeSalary(IN emp_id INT, OUT emp_salary INT)
BEGIN
    SELECT Salary INTO emp_salary
    FROM employee
    WHERE EmployeeID = emp_id;
END$$

DELIMITER ;

-- To call the procedure with an OUT parameter, you need to declare a user variable to hold the output.
SET @out_salary = 0;  -- Initialize a variable
CALL getEmployeeSalary(1, @out_salary);

-- To see the result:
SELECT @out_salary AS EmployeeSalary;
