use bigcompany;

DROP TABLE IF EXISTS EmployeeCount;
DROP EVENT IF EXISTS record_employee_count;

SET GLOBAL event_scheduler = ON;

CREATE TABLE EmployeeCount (
  Timestamp DATETIME PRIMARY KEY,
  EmployeeCount INT
);

CREATE EVENT record_employee_count ON SCHEDULE EVERY 1 MINUTE DO
INSERT INTO EmployeeCount (timestamp, EmployeeCount)
SELECT NOW(),
  COUNT(*)
FROM employee;