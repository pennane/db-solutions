package application;

import dao.Dao;
import entity.Account;
import entity.Category;

public class FinanceTest {

    public static void main(String[] args) {
        Dao dao = new Dao("DevPU");

        Category leisure = dao.createCategory("Leisure");
        Category gifts = dao.createCategory("Gifts");
        Category transfer = dao.createCategory("Internal Transfer");

        Account savingsAccount = dao.createAccount("Savings", 400.00f);
        Account wallet = dao.createAccount("Wallet", 14.50f);

        dao.createTransaction(null, savingsAccount, gifts, 100.00f, "Gift from Aunt Mary");

        dao.createTransaction(savingsAccount, wallet, transfer, 40.00f, "Transfer to wallet");

        dao.createTransaction(wallet, null, leisure, 8.40f, "Spent in pub");
    }
}


