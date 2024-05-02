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


    public Customer createCustomer(String firstName, String lastName, BasicProfile profile) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setBasicProfile(profile);
        em.persist(profile);
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
        return customer;
    }



}