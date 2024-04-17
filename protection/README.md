# Data protection

### 1.

```sql
person_marked_as_deleted BOOLEAN NOT NULL DEFAULT FALSE
```

This is better than just wiping out customers completely on the get go, but it should be ensured that the customers are actually deleted from the database after some time so that the site is GDPR compliant.

```sql
password VARCHAR(255) NOT NULL,
social_security_number CHAR(11),
ip_address VARCHAR(30),
fingerprint BLOB,
mother_maiden_name VARCHAR(100),
```

Storing sensitive information in the same table as other non-sensitive information is not great. With information like this it is crucial to keep the it hard and secure to reach.

Encryption, salting, possibly even completely different storage system altogether.

Also `mother_maiden_name` sounds like a answer to a "security" question. Those are not secure, so that could be omitted altogether.

```sql
card_number VARCHAR(20),
card_expiry_date VARCHAR(5),
card_cvv VARCHAR(4),
```

storing the whole card information is most likely illegal, and if not atleast violates security standards.

![alt text](image.png)

```sql
country VARCHAR(50),
supplier_contact VARCHAR(100),
```

it might make sense to extract these into their own tables. In general it looks like the schema could really benefit from some normalization, which would make it easier to manage.

### 2.

```sql
GRANT ALL PRIVILEGES
```

"Principle of Least Privilege". It should be examined what prileges the user actually needs.

The privileges could be wrapped to a sql role

```sql
CREATE ROLE some_role;
GRANT SELECT, ... ON webstore.customer TO some_role;
```

Which in turn would then be easier to manage and nominate or remove nomination from users

```sql
GRANT some_role TO 'webstore_user'@'localhost';
```

There probalby should be multiple users that are used for different tasks, each with either inherited or completely isolated privileges. Again adhering to the "Principle of Least Privilege"
