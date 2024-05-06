# Registers

### 1

#### a

Dao code

```java
 public List<SalesEvent> retrieveSmallSales(double limit) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<SalesEvent> result = em
            .createQuery("SELECT se FROM SalesEvent se WHERE se.amount < :limit", SalesEvent.class)
            .setParameter("limit", limit)
            .getResultList();

        em.getTransaction().commit();
        em.close();

        return result;
    }
```

#### b

Dao code

```java
   public void addServiceFeeToAllSales(double serviceFee) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em
            .createQuery("UPDATE SalesEvent se SET se.amount = se.amount + :serviceFee")
            .setParameter("serviceFee", serviceFee)
            .executeUpdate();

        em.getTransaction().commit();

    }
```

Validation code

```java
result = dao.retrieveSmallSales(50.0);
if (result != null) {
    for (SalesEvent s : result) {
        System.out.println("before service fee " + s);
    }
}
dao.addServiceFeeToAllSales(10);
result = dao.retrieveSmallSales(50.0);
if (result != null) {
    for (SalesEvent s : result) {
        System.out.println("after service fee " + s);
    }
}
```

#### c

Dao code

```java
 public void deleteAllSalesEvents() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM SalesEvent").executeUpdate();
        em.getTransaction().commit();
    }
```

validation code

```java
dao.deleteAllSalesEvents();
result = dao.retrieveSmallSales(50.0);
System.out.println("Small count after deletion: "+ result.size());
```

### 2

```java
    public List<SalesEvent> retrieveSmallSales(double limit) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SalesEvent> query = cb.createQuery(SalesEvent.class);
        Root<SalesEvent> root = query.from(SalesEvent.class);

        query.select(root)
                .where(cb.lt(root.get("amount"), limit));

        var result = em.createQuery(query).getResultList();
        em.close();
        return result;
    }
```

```java
    public void addServiceFeeToAllSales(double serviceFee) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        em.getTransaction().begin();

        CriteriaUpdate<SalesEvent> update = cb.createCriteriaUpdate(SalesEvent.class);
        Root<SalesEvent> root = update.from(SalesEvent.class);

        Path<Double> amountPath = root.get("amount");
        Expression<Double> updatedAmount = cb.sum(amountPath, cb.literal(serviceFee));

        update.set(amountPath, updatedAmount);

        em.getTransaction().commit();
        em.close();
    }
```

```java
    public void deleteAllSalesEvents() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        em.getTransaction().begin();

        CriteriaDelete<SalesEvent> delete = cb.createCriteriaDelete(SalesEvent.class);
        delete.from(SalesEvent.class);

        em.createQuery(delete).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
```
