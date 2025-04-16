package test;

import entity.Lease;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Date;

public class LeaseTest {

    @Test
    void testLeaseCreationAndGetters() {
        Date startDate = new Date();
        Date endDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)); // One week later
        Lease lease = new Lease(201, 1, 101, startDate, endDate, "DailyLease");
        Assertions.assertEquals(201, lease.getLeaseID());
        Assertions.assertEquals(1, lease.getVehicleID());
        Assertions.assertEquals(101, lease.getCustomerID());
        Assertions.assertEquals(startDate, lease.getStartDate());
        Assertions.assertEquals(endDate, lease.getEndDate());
        Assertions.assertEquals("DailyLease", lease.getType());
    }

    @Test
    void testLeaseSetters() {
        Lease lease = new Lease();
        lease.setLeaseID(202);
        lease.setVehicleID(2);
        lease.setCustomerID(102);
        Date startDate = new Date();
        Date endDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30)); // One month later
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);
        lease.setType("MonthlyLease");

        Assertions.assertEquals(202, lease.getLeaseID());
        Assertions.assertEquals(2, lease.getVehicleID());
        Assertions.assertEquals(102, lease.getCustomerID());
        Assertions.assertEquals(startDate, lease.getStartDate());
        Assertions.assertEquals(endDate, lease.getEndDate());
        Assertions.assertEquals("MonthlyLease", lease.getType());
    }
}