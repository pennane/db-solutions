package dao;

import entity.Account;
import jakarta.persistence.*;

public class Dao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harj1PU");


    public Account addAccount(Account account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(account);

        em.getTransaction().commit();
        em.close();

        return account;
    }

    public Account findAccount(int number) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Account account = em.find(Account.class, number);

        em.getTransaction().commit();
        em.close();

        return account;
    }

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
}
