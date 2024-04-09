CREATE USER 'dbuser' @'localhost' IDENTIFIED BY 'dbpass';
GRANT SELECT ON public.* TO 'dbuser' @'localhost';

CREATE DATABASE public;

USE public;

CREATE VIEW `public`.`emp_list` AS
SELECT e.EmployeeID,
  e.FirstName,
  e.LastName,
  e.StreetAddress,
  p.PostalCode,
  p.PostalArea
FROM `bigcompany`.`Employee` e
  JOIN `bigcompany`.`Postalarea` p ON e.PostalCode = p.PostalCode;