package application;

import entity.*;
import dao.*;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Dao dao = new Dao();
        // 1
        Account account = dao.addAccount(new Account(123.45));
        // 2
        Account retrievedAccount = dao.findAccount(account.getNumber());
        System.out.println("Retrieved account: " + retrievedAccount);
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

