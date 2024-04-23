package application;

import dao.Dao;
import entity.Account;
import entity.Category;
import entity.Transaction;

import java.util.Scanner;

public class FinanceTest2 {
    public static void main(String[] args) {
        Dao dao = new Dao("FinanceTest2PU");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter transaction id: ");
        int id = sc.nextInt();
        Transaction transaction = dao.getTransaction(id);
        if (transaction == null) {
            System.out.println("failed to get");
        } else {
            System.out.println("got: " + transaction);
            System.out.println(transaction.getCategory().getDescription());
            System.out.println(transaction.getSourceAccount().getName());
            System.out.println(transaction.getDestinationAccount().getName());
        }
    }
}


