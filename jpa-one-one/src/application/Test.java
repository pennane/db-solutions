package application;

import dao.Dao;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        Dao dao = new Dao("DevPU");
        BasicProfile profile = new BasicProfile();
        profile.setWeight(90);
        profile.setHeight(200);
        profile.setBirthYear(2000);
        Customer customer = dao.createCustomer("Ukkeli", "jne", profile);
        BasicProfile profile2 = new BasicProfile();
        profile2.setWeight(70);
        profile2.setHeight(170);
        profile2.setBirthYear(1990);
        Customer customer2 = dao.createCustomer("Toinen", "Hahmo", profile2);
    }
}
