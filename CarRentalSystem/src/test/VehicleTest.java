package test;

import entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class VehicleTest {

    @Test
    void testVehicleCreationAndGetters() {
        Vehicle vehicle = new Vehicle(1, "Toyota", "Camry", 2022, 35.0, "available", 5, "2.5L");
        Assertions.assertEquals(1, vehicle.getVehicleID());
        Assertions.assertEquals("Toyota", vehicle.getMake());
        Assertions.assertEquals("Camry", vehicle.getModel());
        Assertions.assertEquals(2022, vehicle.getYear());
        Assertions.assertEquals(35.0, vehicle.getDailyRate(), 0.001);
        Assertions.assertEquals("available", vehicle.getStatus());
        Assertions.assertEquals(5, vehicle.getPassengerCapacity());
        Assertions.assertEquals("2.5L", vehicle.getEngineCapacity());
    }

    @Test
    void testVehicleSetters() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleID(2);
        vehicle.setMake("Honda");
        vehicle.setModel("Civic");
        vehicle.setYear(2023);
        Assertions.assertEquals(30.0, vehicle.getDailyRate(), 0.001);
        Assertions.assertEquals("notAvailable", vehicle.getStatus());
        Assertions.assertEquals(4, vehicle.getPassengerCapacity());
        Assertions.assertEquals("1.5L", vehicle.getEngineCapacity());
    }
}