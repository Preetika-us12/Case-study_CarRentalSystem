package dao;

import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import java.util.Date;
import java.util.List;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException; // Added import

public interface ICarLeaseRepository {

    // Vehicle Management
    void addCar(Vehicle vehicle);
    void removeCar(int vehicleID) throws CarNotFoundException;
    List<Vehicle> listAvailableCars();
    List<Vehicle> listRentedCars();
    Vehicle findCarById(int vehicleID) throws CarNotFoundException;

    // Customer Management
    void addCustomer(Customer customer);
    void removeCustomer(int customerID) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    Customer findCustomerById(int customerID) throws CustomerNotFoundException;

    // Lease Management
    Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate);
    Lease returnCar(int leaseID) throws LeaseNotFoundException; // Modified signature
    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

    // Payment Handling
    void recordPayment(Lease lease, double amount);
}