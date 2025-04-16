package dao;

import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.DBConnection;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException; // Added import
import java.sql.Types;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {

    private Connection connection;

    public ICarLeaseRepositoryImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void addCar(Vehicle vehicle) {
        String sql = "INSERT INTO Vehicle (vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, vehicle.getVehicleID());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setDouble(5, vehicle.getDailyRate());
            preparedStatement.setString(6, vehicle.getStatus());
            preparedStatement.setInt(7, vehicle.getPassengerCapacity());
            preparedStatement.setString(8, vehicle.getEngineCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public void removeCar(int vehicleID) throws CarNotFoundException {
        String sql = "DELETE FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, vehicleID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new CarNotFoundException("Vehicle with ID " + vehicleID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            throw new CarNotFoundException("Error removing vehicle: " + e.getMessage());
        }
    }

    @Override
    public List<Vehicle> listAvailableCars() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'available'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                availableVehicles.add(mapResultSetToVehicle(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return availableVehicles;
    }

    @Override
    public List<Vehicle> listRentedCars() {
        List<Vehicle> rentedVehicles = new ArrayList<>();
        String sql = "SELECT v.* FROM Vehicle v JOIN Lease l ON v.vehicleID = l.vehicleID WHERE l.endDate >= CURDATE()"; // Adjust CURDATE() based on your DB
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                rentedVehicles.add(mapResultSetToVehicle(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return rentedVehicles;
    }

    @Override
    public Vehicle findCarById(int vehicleID) throws CarNotFoundException {
        String sql = "SELECT * FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, vehicleID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToVehicle(resultSet);
            } else {
                throw new CarNotFoundException("Vehicle with ID " + vehicleID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return null; // Or rethrow a more specific exception
        }
    }

    private Vehicle mapResultSetToVehicle(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getInt("vehicleID"),
                resultSet.getString("make"),
                resultSet.getString("model"),
                resultSet.getInt("year"),
                resultSet.getDouble("dailyRate"),
                resultSet.getString("status"),
                resultSet.getInt("passengerCapacity"),
                resultSet.getString("engineCapacity")
        );
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public void removeCustomer(int customerID) throws CustomerNotFoundException{
        String sql = "DELETE FROM Customer WHERE customerID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            } else {
                throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
            return null; // Or rethrow
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("customerID"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getString("phoneNumber")
        );
    }

    @Override
    public Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate) {
        String sql = "INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, vehicleID);
            preparedStatement.setInt(2, customerID);
            preparedStatement.setDate(3, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(4, new java.sql.Date(endDate.getTime()));
            preparedStatement.setString(5, calculateLeaseType(startDate, endDate));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int leaseID = generatedKeys.getInt(1);
                updateCarStatus(vehicleID, "notAvailable");
                return new Lease(leaseID, vehicleID, customerID, startDate, endDate, calculateLeaseType(startDate, endDate));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }
        return null;
    }

    private String calculateLeaseType(Date startDate, Date endDate) {
        long diffInDays = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        return diffInDays >= 30 ? "MonthlyLease" : "DailyLease";
    }

    private void updateCarStatus(int vehicleID, String status) {
        String sql = "UPDATE Vehicle SET status = ? WHERE vehicleID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, vehicleID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public Lease returnCar(int leaseID) throws LeaseNotFoundException { // Modified implementation
        String sql = "SELECT * FROM Lease WHERE leaseID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, leaseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Lease lease = mapResultSetToLease(resultSet);
                updateCarStatus(lease.getVehicleID(), "available");
                return lease;
            } else {
                throw new LeaseNotFoundException("Lease with ID " + leaseID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
            return null; // Or potentially re-throw as a different exception
        }
    }

    private Lease mapResultSetToLease(ResultSet resultSet) throws SQLException {
        return new Lease(
                resultSet.getInt("leaseID"),
                resultSet.getInt("vehicleID"),
                resultSet.getInt("customerID"),
                resultSet.getDate("startDate"),
                resultSet.getDate("endDate"),
                resultSet.getString("type")
        );
    }

    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> activeLeases = new ArrayList<>();
        String sql = "SELECT * FROM Lease WHERE endDate >= CURDATE()"; // Adjust based on your DB
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                activeLeases.add(mapResultSetToLease(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }
        return activeLeases;
    }

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leaseHistory = new ArrayList<>();
        String sql = "SELECT * FROM Lease";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                leaseHistory.add(mapResultSetToLease(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }
        return leaseHistory;
    }

    @Override
    public void recordPayment(Lease lease, double amount) {
        String sql = "INSERT INTO Payment (leaseID, paymentDate, amount) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lease.getLeaseID());
            preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDouble(3, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }
    }
}