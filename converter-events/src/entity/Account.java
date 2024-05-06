package entity;

import converter.BalanceConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "ACC")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private int number;

    @Column(name = "balance")
    @Convert(converter = BalanceConverter.class)
    private double balance;

    @Version
    @Column(name = "version")
    private int version;


    public Account() { }

    public Account(double balance) {
        this.balance = balance;
    }

    public void setNumber(int number) {this.number = number; }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", version=" + version +
                '}';
    }
}
