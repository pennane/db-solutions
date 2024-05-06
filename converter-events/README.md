# Converter / events

### 1

Entity

```java
@Entity
@Table(name = "ACC")
public class Account {
    ...
    @Column(name = "balance")
    @Convert(converter = BalanceConverter.class)
    private double balance;
    ...
}

```

Converter

```java
@Converter
public class BalanceConverter implements AttributeConverter<Double, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Double balance) {
        if (balance == null) {
            return null;
        }
        return (int) Math.round(balance * 100);
    }

    @Override
    public Double convertToEntityAttribute(Integer balanceInCents) {
        if (balanceInCents == null) {
            return null;
        }
        return balanceInCents / 100.0;
    }
}
```

Test

```java
public class App {
    public static void main(String[] args) {
        Dao dao = new Dao();
        // 1
        Account account = dao.addAccount(new Account(123.45));
    }
}
```

The dao code is irrelevant, just same business as in old exercises.

what is stored in db

```sh
mysql> select * FROM acc;
+--------+---------+
| number | balance |
+--------+---------+
|      1 |   12345 |
+--------+---------+
1 row in set (0.00 sec)
```

### 2

Works dandy

```java
public class App {
    public static void main(String[] args) {
        ...
        // 2
        Account retrievedAccount = dao.findAccount(account.getNumber());
        System.out.println("Retrieved account: " + retrievedAccount);
        // prints: Retrieved account: Account{number=1, balance=123.45}
    }
}
```

(dao code)

```java
public Account findAccount(int number) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Account account = em.find(Account.class, number);

        em.getTransaction().commit();
        em.close();

        return account;
}
```

### 3

adding version field to the entity

```java
@Entity
@Table(name = "ACC")
public class Account {
    ...

    @Version
    @Column(name = "version")
    private int version;

    ...

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", version=" + version +
                '}';
    }
}

```

dao code

```java
 public void transfer(int sourceAccountNumber, int destinationAccountNumber, double amount) {

        if (amount <= 0) {
            return;
        }

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Account sourceAccount = em.find(Account.class, sourceAccountNumber, LockModeType.OPTIMISTIC);
            Account destinationAccount = em.find(Account.class, destinationAccountNumber, LockModeType.OPTIMISTIC);

            if (sourceAccount == null || destinationAccount == null) {
                tx.rollback();
                return;
            }

            if (sourceAccount.getBalance() < amount) {
                tx.rollback();
                return;
            }

            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);

            em.merge(sourceAccount);
            em.merge(destinationAccount);

            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
```

test code

```java
public class App {
    public static void main(String[] args) {
        ...
        // 3
        Account acc1 = dao.addAccount(new Account(9000.1));
        Account acc2 = dao.addAccount(new Account(99.9));
        System.out.println("acc1 before: "+ acc1);
        System.out.println("acc2 before: "+ acc2);
        dao.transfer(acc1.getNumber(), acc2.getNumber(), 0.1);
        Account acc1after = dao.findAccount(acc1.getNumber());
        Account acc2after =  dao.findAccount(acc2.getNumber());
        System.out.println("acc1 after: "+ acc1after);
        System.out.println("acc2 after: "+ acc2after);
    }

}
```

test output

```
acc1 before: Account{number=2, balance=9000.1, version=0}
acc2 before: Account{number=3, balance=99.9, version=0}
acc1 after: Account{number=2, balance=9000.0, version=1}
acc2 after: Account{number=3, balance=100.0, version=1}
```
