package dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import entity.*;

import java.util.*;
import java.util.function.Function;

public class Dao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harj1PU");


    public void addRegister(Register reg) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(reg);

        em.getTransaction().commit();
        em.close();
    }

    public void addEvent(int eventNumber, int regNumber, double amount) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Register reg = em.find(Register.class, regNumber);
        SalesEvent evt = new SalesEvent(eventNumber, reg, amount);

        em.persist(evt);

        em.getTransaction().commit();
        em.close();
    }

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

  /*  public List<SalesEvent> retrieveSmallSales(double limit) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<SalesEvent> result = em.createQuery("SELECT se FROM SalesEvent se WHERE se.amount < :limit", SalesEvent.class)
                .setParameter("limit", limit)
                .getResultList();
        em.getTransaction().commit();

        return result;
    }

    public void addServiceFeeToAllSales(double serviceFee) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em
                .createQuery("UPDATE SalesEvent se SET se.amount = se.amount + :serviceFee")
                .setParameter("serviceFee", serviceFee)
                .executeUpdate();

        em.getTransaction().commit();

    }

    public void deleteAllSalesEvents() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM SalesEvent").executeUpdate();
        em.getTransaction().commit();
    }
*/
}
