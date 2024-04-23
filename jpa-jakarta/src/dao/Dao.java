package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import entity.*;

public class Dao {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DevPU");

	public void saveDevice(Device device) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        em.persist(device);
        
        em.getTransaction().commit();
        em.close();
	}	
}
