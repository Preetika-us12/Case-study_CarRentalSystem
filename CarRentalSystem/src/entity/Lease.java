package entity;

import java.util.Date;

public class Lease {
    private int leaseID;
    private int vehicleID;
    private int customerID;
    private Date startDate;
    private Date endDate;
    private String type;

    public Lease() {
    }

    public Lease(int leaseID, int vehicleID, int customerID, Date startDate, Date endDate, String type) {
        this.leaseID = leaseID;
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    // Getters
    public int getLeaseID() {
        return leaseID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setType(String type) {
        this.type = type;
    }
}
