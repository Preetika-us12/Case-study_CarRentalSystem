package entity;

import java.util.Date;

public class Payment {
    private int paymentID;
    private int leaseID;
    private Date paymentDate;
    private double amount;

    public Payment() {
    }

    public Payment(int paymentID, int leaseID, Date paymentDate, double amount) {
        this.paymentID = paymentID;
        this.leaseID = leaseID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    // Getters
    public int getPaymentID() {
        return paymentID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
