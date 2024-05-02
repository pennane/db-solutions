package entity;

public class ContractCustomer extends Customer {
    public int contractId;
    public java.sql.Timestamp startDate;
    public java.sql.Timestamp endDate;

    public ContractCustomer(int custId, String firstName, String lastName, int contractId, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
        super();
        super.setCustomerId(custId);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.contractId = contractId    ;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
