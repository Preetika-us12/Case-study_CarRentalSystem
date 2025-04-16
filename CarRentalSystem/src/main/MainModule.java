package main;

import dao.ICarLeaseRepository;
import dao.ICarLeaseRepositoryImpl;
import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;
import util.DBConnection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nVehicle Rental Application Menu:");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. List Available Vehicles");
            System.out.println("4. List Rented Vehicles");
            System.out.println("5. Find Vehicle by ID");
            System.out.println("6. Add Customer");
            System.out.println("7. Remove Customer");
            System.out.println("8. List Customers");
            System.out.println("9. Find Customer by ID");
            System.out.println("10. Create Lease");
            System.out.println("11. Return Vehicle");
            System.out.println("12. List Active Leases");
            System.out.println("13. List Lease History");
            System.out.println("14. Record Payment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Implement add vehicle functionality
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID to remove: ");
                    int vehicleIdToRemove = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        carLeaseRepository.removeCar(vehicleIdToRemove);
                        System.out.println("Vehicle removed successfully.");
                    } catch (CarNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    List<Vehicle> availableVehicles = carLeaseRepository.listAvailableCars();
                    System.out.println("\nAvailable Vehicles:");
                    for (Vehicle vehicle : availableVehicles) {
                        System.out.println(vehicle.getVehicleID() + " - " + vehicle.getMake() + " " + vehicle.getModel());
                    }
                    break;
                case 4:
                    List<Lease> rentedLeases = carLeaseRepository.listActiveLeases(); // Assuming active leases mean rented
                    System.out.println("\nRented Vehicles:");
                    for (Lease lease : rentedLeases) {
                        try {
                            Vehicle rentedVehicle = carLeaseRepository.findCarById(lease.getVehicleID());
                            System.out.println("Lease ID: " + lease.getLeaseID() + ", Vehicle: " + rentedVehicle.getMake() + " " + rentedVehicle.getModel());
                        } catch (CarNotFoundException e) {
                            System.err.println("Error finding vehicle for lease: " + e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter Vehicle ID to find: ");
                    int vehicleIdToFind = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Vehicle foundVehicle = carLeaseRepository.findCarById(vehicleIdToFind);
                        System.out.println("Found Vehicle: " + foundVehicle.getMake() + " " + foundVehicle.getModel());
                    } catch (CarNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Enter customer details:");
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    // You'll likely want to generate a unique customer ID.
                    // For simplicity here, we'll ask the user for it.
                    System.out.print("Customer ID: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Customer newCustomer = new Customer(customerID, firstName, lastName, email, phoneNumber);
                    carLeaseRepository.addCustomer(newCustomer);
                    System.out.println("Customer added successfully.");
                    break;
                case 7:
                    System.out.print("Enter Customer ID to remove: ");
                    int customerIdToRemove = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        carLeaseRepository.removeCustomer(customerIdToRemove);
                        System.out.println("Customer removed successfully.");
                    } catch (CustomerNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 8:
                    List<Customer> allCustomers = carLeaseRepository.listCustomers();
                    System.out.println("\nAll Customers:");
                    for (Customer customer : allCustomers) {
                        System.out.println(customer.getCustomerID() + " - " + customer.getFirstName() + " " + customer.getLastName());
                    }
                    break;
                case 9:
                    System.out.print("Enter Customer ID to find: ");
                    int customerIdToFind = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Customer foundCustomer = carLeaseRepository.findCustomerById(customerIdToFind);
                        System.out.println("Found Customer: " + foundCustomer.getFirstName() + " " + foundCustomer.getLastName());
                    } catch (CustomerNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 10:
                    System.out.print("Enter Customer ID: ");
                    int customerIdForLease = scanner.nextInt();
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleIdForLease = scanner.nextInt();
                    System.out.print("Enter Start Date (yyyy-MM-dd): ");
                    String startDateStr = scanner.next();
                    System.out.print("Enter End Date (yyyy-MM-dd): ");
                    String endDateStr = scanner.next();
                    try {
                        Date startDate = java.sql.Date.valueOf(startDateStr);
                        Date endDate = java.sql.Date.valueOf(endDateStr);
                        Lease newLease = carLeaseRepository.createLease(customerIdForLease, vehicleIdForLease, startDate, endDate);
                        if (newLease != null) {
                            System.out.println("Lease created with ID: " + newLease.getLeaseID());
                        } else {
                            System.out.println("Failed to create lease.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid date format.");
                    }
                    break;
                case 11:
                    System.out.print("Enter Lease ID to return Vehicle: ");
                    int leaseIdToReturn = scanner.nextInt();
                    try {
                        Lease returnedLease = carLeaseRepository.returnCar(leaseIdToReturn);
                        if (returnedLease != null) {
                            System.out.println("Vehicle returned for Lease ID: " + returnedLease.getLeaseID());
                        }
                    } catch (LeaseNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 12:
                    List<Lease> activeLeases = carLeaseRepository.listActiveLeases();
                    System.out.println("\nActive Leases:");
                    for (Lease lease : activeLeases) {
                        System.out.println("Lease ID: " + lease.getLeaseID() + ", Vehicle ID: " + lease.getVehicleID() + ", Customer ID: " + lease.getCustomerID());
                    }
                    break;
                case 13:
                    List<Lease> leaseHistory = carLeaseRepository.listLeaseHistory();
                    System.out.println("\nLease History:");
                    for (Lease lease : leaseHistory) {
                        System.out.println("Lease ID: " + lease.getLeaseID() + ", Vehicle ID: " + lease.getVehicleID() + ", Customer ID: " + lease.getCustomerID() + ", Start Date: " + lease.getStartDate() + ", End Date: " + lease.getEndDate());
                    }
                    break;
                case 14:
                    System.out.print("Enter Lease ID for payment: ");
                    int leaseIdForPayment = scanner.nextInt();
                    System.out.print("Enter Payment Amount: ");
                    double paymentAmount = scanner.nextDouble();
                    // In a real application, you would likely fetch the Lease object first
                    Lease tempLease = new Lease();
                    tempLease.setLeaseID(leaseIdForPayment); // Just setting ID for demonstration
                    carLeaseRepository.recordPayment(tempLease, paymentAmount);
                    System.out.println("Payment recorded successfully for Lease ID: " + leaseIdForPayment);
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        DBConnection.closeConnection();
        scanner.close();
    }
}