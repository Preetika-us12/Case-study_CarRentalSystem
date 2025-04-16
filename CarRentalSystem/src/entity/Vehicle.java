package entity;

public class Vehicle {
	private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private String status;
    private int passengerCapacity;
    private String engineCapacity;

    public Vehicle() {
    }

    public Vehicle(int vehicleID, String make, String model, int year, double dailyRate, String status, int passengerCapacity, String engineCapacity) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }

    // Getters
    public int getVehicleID() {
        return vehicleID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public String getStatus() {
        return status;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    // Setters
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
