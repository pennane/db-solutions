package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import entity.*;

public class Dao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DevPU");

    public void save(Device device) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(device);

        em.getTransaction().commit();
        em.close();
    }

    public Device load(int deviceId) {
        EntityManager em = emf.createEntityManager();
        try {
            Device device = em.find(Device.class, deviceId);
            return device;
        } finally {
            em.close();
        }
    }

    public boolean remove(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            if (device == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(device);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateDescription(int id, String newDescription) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            if (device == null) {
                em.getTransaction().rollback();
                return false;
            }
            device.setDescription(newDescription);
            em.merge(device);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
