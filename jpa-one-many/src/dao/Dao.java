package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import entity.*;

public class Dao {
    EntityManagerFactory emf;
    public Dao(String pu) {
        emf = Persistence.createEntityManagerFactory(pu);
    }


    public Category createCategory(String description) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Category category = new Category();
        category.setDescription(description);
        em.persist(category);
        em.getTransaction().commit();
        em.close();
        return category;
    }

    public Account createAccount(String name, float initialBalance) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Account account = new Account();
        account.setName(name);
        account.setBalance(initialBalance);
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return account;
    }

    public Transaction createTransaction(Account source, Account destination, Category category, float amount, String description) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(source);
        transaction.setDestinationAccount(destination);
        transaction.setCategory(category);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        em.persist(transaction);
        em.getTransaction().commit();
        em.close();
        return transaction;
    }

    public Transaction getTransaction(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Transaction transaction = em.find(Transaction.class, id);
            return transaction;
        } finally {
            em.close();
        }
    }
}
