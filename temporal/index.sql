DROP DATABASE IF EXISTS db_solutions;
CREATE DATABASE db_solutions;
USE db_solutions;

-- 1.
CREATE TABLE service (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  price_per_day FLOAT
) WITH SYSTEM VERSIONING;

CREATE TABLE customer (
  id INT,
  firstname VARCHAR(255),
  lastname VARCHAR(255),
  subscription_startdate DATE,
  subscription_enddate DATE,
  PERIOD FOR subscription_period(subscription_startdate, subscription_enddate),
  service_id INT,
  CONSTRAINT `fk_customer_service` FOREIGN KEY (service_id) REFERENCES service (id),
  UNIQUE (id, subscription_period WITHOUT OVERLAPS)
) WITH SYSTEM VERSIONING;

-- 2.
INSERT INTO service (name, price_per_day)
VALUES ('Gold', 0.95),
  ('Silver', 0.45),
  ('Bronze', 0.00);

SET @gold_service_id = 0;
SET @silver_service_id = 0;
SET @bronze_service_id = 0;

SELECT id INTO @gold_service_id
FROM service
WHERE name = 'Gold'
LIMIT 1;

SELECT id INTO @silver_service_id
FROM service
WHERE name = 'Silver'
LIMIT 1;

SELECT id INTO @bronze_service_id
FROM service
WHERE name = 'Bronze'
LIMIT 1;

INSERT INTO customer (
    id,
    firstname,
    lastname,
    subscription_startdate,
    subscription_enddate,
    service_id
  )
VALUES (
    1,
    'Mary',
    'Smith',
    '2023-09-01',
    '2023-09-09',
    @silver_service_id
  ),
  (
    2,
    'Ville',
    'Puro',
    '2023-09-10',
    '9999-12-31',
    @bronze_service_id
  );
;



-- 3.
UPDATE customer FOR PORTION OF subscription_period
FROM '2023-10-03' TO '2023-10-08'
SET service_id = @gold_service_id
WHERE firstname = 'Ville'
  AND lastname = 'Puro';

SELECT *,
  row_start,
  row_end
FROM customer FOR SYSTEM_TIME ALL;


-- 4.
SELECT SLEEP(4);

UPDATE customer
SET lastname = "Virtanen"
WHERE firstname = 'Ville'
  AND lastname = 'Puro';


SELECT *,
  row_start,
  row_end
FROM customer FOR SYSTEM_TIME ALL;

-- 5.
SELECT customer.id AS customer_id,
  customer.firstname,
  customer.lastname,
  customer.subscription_startdate,
  customer.subscription_enddate,
  service.id AS service_id,
  service.name AS service_name,
  service.price_per_day
FROM customer FOR SYSTEM_TIME AS OF (NOW() - INTERVAL 3 SECOND) AS customer
  INNER JOIN service FOR SYSTEM_TIME AS OF (NOW() - INTERVAL 3 SECOND) AS service ON customer.service_id = service.id;