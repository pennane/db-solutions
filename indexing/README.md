## 1.

### employee

| Field         | Type        | Null | Key | Default | Extra |
| ------------- | ----------- | ---- | --- | ------- | ----- |
| EmployeeID    | int(11)     | NO   | PRI | NULL    |       |
| LastName      | varchar(40) | NO   | MUL | NULL    |       |
| FirstName     | varchar(40) | NO   |     | NULL    |       |
| DepartmentID  | int(11)     | YES  | MUL | NULL    |       |
| StreetAddress | varchar(40) | YES  |     | NULL    |       |
| PostalCode    | varchar(5)  | NO   | MUL | NULL    |       |
| PhoneNumber   | varchar(16) | YES  |     | NULL    |       |
| Salary        | int(11)     | YES  |     | NULL    |       |

```sql
COUNT(EmployeeID)
200000
```

### phonecall

| Field       | Type         | Null | Key | Default | Extra |
| ----------- | ------------ | ---- | --- | ------- | ----- |
| CallID      | int(11)      | NO   | PRI | NULL    |       |
| PhoneNumber | varchar(16)  | YES  |     | NULL    |       |
| Price       | decimal(4,2) | YES  |     | NULL    |       |

```sql
COUNT(CallId)
10000000
```

## 2. Queries

```sql
SELECT FirstName,
  Salary
FROM employee
WHERE LastName = 'Virtanen';
```

```sql
SELECT SUM(price)
FROM phonecall
WHERE PhoneNumber = '041-951114';
```

```sql
SELECT CallId,
  Price
FROM phonecall
  JOIN employee ON phonecall.PhoneNumber = employee.PhoneNumber
WHERE LastName = 'Virtanen';
```

| Query_ID | Duration   | Query                                                                                                                         |
| -------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------- |
| 1        | 0.09046900 | SELECT FirstName, Salary FROM employee WHERE LastName = 'Virtanen'                                                            |
| 2        | 3.24918100 | SELECT SUM(price) FROM phonecall WHERE PhoneNumber = '041-951114'                                                             |
| 3        | DNF        | SELECT CallId, Price FROM phonecall JOIN employee ON phonecall.PhoneNumber = employee.PhoneNumber WHERE LastName = 'Virtanen' |

## 3, 4, 5. Indices

```sql
CREATE INDEX idx_last_name ON employee(LastName);
CREATE INDEX idx_phone_number ON employee(PhoneNumber);
CREATE INDEX idx_phone_number ON phonecall(PhoneNumber);
```

| Query_ID | Duration   | Query                                                                                                                         |
| -------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------- |
| 1        | 0.05832100 | SELECT FirstName, Salary FROM employee WHERE LastName = 'Virtanen'                                                            |
| 2        | 0.00740100 | SELECT SUM(price) FROM phonecall WHERE PhoneNumber = '041-951114'                                                             |
| 3        | 0.49275800 | SELECT CallId, Price FROM phonecall JOIN employee ON phonecall.PhoneNumber = employee.PhoneNumber WHERE LastName = 'Virtanen' |

## 6.

```sql
SELECT * FROM employee WHERE LastName = 'Mynttinen';
SELECT * FROM employee WHERE LastName = 'Virtanen';
SELECT * FROM employee WHERE LastName = 'Rojola';
SELECT * FROM employee WHERE LastName = 'Ahola';
SELECT * FROM employee WHERE LastName = 'Mikkonen';
SELECT * FROM employee WHERE LastName = 'Mäki-Tuuri';
SELECT * FROM employee WHERE LastName = 'Joeli';
SELECT * FROM employee WHERE LastName = 'Palmu';
SELECT * FROM employee WHERE LastName = 'Koivistoinen';
SELECT * FROM employee WHERE LastName = 'Havia-Ahonen';
```

### Without indices

```
Running mysqlslap with concurrency=1 and iterations=10
Benchmark
        Average number of seconds to run all queries: 0.705 seconds
        Minimum number of seconds to run all queries: 0.651 seconds
        Maximum number of seconds to run all queries: 0.826 seconds
        Number of clients running queries: 1
        Average number of queries per client: 10

Running mysqlslap with concurrency=5 and iterations=20
Benchmark
        Average number of seconds to run all queries: 1.002 seconds
        Minimum number of seconds to run all queries: 0.893 seconds
        Maximum number of seconds to run all queries: 1.125 seconds
        Number of clients running queries: 5
        Average number of queries per client: 10

Running mysqlslap with concurrency=10 and iterations=5
Benchmark
        Average number of seconds to run all queries: 2.333 seconds
        Minimum number of seconds to run all queries: 1.897 seconds
        Maximum number of seconds to run all queries: 2.609 seconds
        Number of clients running queries: 10
        Average number of queries per client: 10

Running mysqlslap with concurrency=20 and iterations=1
Benchmark
        Average number of seconds to run all queries: 5.550 seconds
        Minimum number of seconds to run all queries: 5.550 seconds
        Maximum number of seconds to run all queries: 5.550 seconds
        Number of clients running queries: 20
        Average number of queries per client: 10

Done
```

### With indices

```
Running mysqlslap with concurrency=1 and iterations=10
Benchmark
        Average number of seconds to run all queries: 0.015 seconds
        Minimum number of seconds to run all queries: 0.013 seconds
        Maximum number of seconds to run all queries: 0.028 seconds
        Number of clients running queries: 1
        Average number of queries per client: 10

Running mysqlslap with concurrency=5 and iterations=20
Benchmark
        Average number of seconds to run all queries: 0.017 seconds
        Minimum number of seconds to run all queries: 0.016 seconds
        Maximum number of seconds to run all queries: 0.021 seconds
        Number of clients running queries: 5
        Average number of queries per client: 10

Running mysqlslap with concurrency=10 and iterations=5
Benchmark
        Average number of seconds to run all queries: 0.028 seconds
        Minimum number of seconds to run all queries: 0.026 seconds
        Maximum number of seconds to run all queries: 0.034 seconds
        Number of clients running queries: 10
        Average number of queries per client: 10

Running mysqlslap with concurrency=20 and iterations=1
Benchmark
        Average number of seconds to run all queries: 0.056 seconds
        Minimum number of seconds to run all queries: 0.056 seconds
        Maximum number of seconds to run all queries: 0.056 seconds
        Number of clients running queries: 20
        Average number of queries per client: 10

Done
```
