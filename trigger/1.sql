use bigcompany;

DROP TABLE IF EXISTS EMPLOG;
DROP TRIGGER IF EXISTS new_emp;
DELETE FROM employee
WHERE EmployeeID = 9001;

CREATE TABLE EMPLOG (
  LogID INT AUTO_INCREMENT,
  EmployeeID INT,
  RecordingTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (LogID),
  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);



CREATE TRIGGER new_emp
AFTER
INSERT ON Employee FOR EACH ROW
INSERT INTO EMPLOG (EmployeeID, RecordingTime)
VALUES (NEW.EmployeeID, NOW());


INSERT INTO employee (
    EmployeeID,
    LastName,
    FirstName,
    DepartmentID,
    StreetAddress,
    PostalCode,
    PhoneNumber,
    Salary
  )
VALUES (
    9001,
    'Ukko',
    'Yykaakoo',
    4,
    '123 tie',
    '00100',
    '1234567890',
    50000
  );

SELECT *
FROM EMPLOG;

SHOW CREATE TRIGGER new_emp