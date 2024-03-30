USE Bank;

-- 1.
START TRANSACTION;

UPDATE Account
SET Balance = Balance - 150.00
WHERE ID = 100;

UPDATE Account
SET Balance = Balance + 150.00
WHERE ID = 103;

COMMIT;

SELECT Balance
FROM Account
WHERE ID = 100;

SELECT Balance
FROM Account
WHERE ID = 103;

-- 2.
START TRANSACTION;

UPDATE Account
SET Balance = Balance - 150.00
WHERE ID = 100;

UPDATE Account
SET Balance = Balance + 150.00
WHERE ID = 103;

ROLLBACK;

SELECT Balance
FROM Account
WHERE ID = 100;

SELECT Balance
FROM Account
WHERE ID = 103;