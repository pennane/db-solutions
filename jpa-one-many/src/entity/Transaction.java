package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Amount")
    private float amount;

    @Column(name = "Description")
    private String description;

    @Column(name = "Timestamp")
    private java.sql.Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryId", referencedColumnName = "id")

    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SourceAccountId", referencedColumnName = "id")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DestinationAccountId", referencedColumnName = "id")
    private Account destinationAccount;

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    // Getters
    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    public Category getCategory() {
        return category;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", category=" + (category != null ? category.getDescription() : "null") +
                ", sourceAccount=" + (sourceAccount != null ? sourceAccount.getName() : "null") +
                ", destinationAccount=" + (destinationAccount != null ? destinationAccount.getName() : "null") +
                '}';
    }
}
