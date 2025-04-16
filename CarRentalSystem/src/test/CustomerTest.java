package test;

import entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CustomerTest {

    @Test
    void testCustomerCreationAndGetters() {
        Customer customer = new Customer(101, "John", "Doe", "john.doe@example.com", "123-456-7890");
        Assertions.assertEquals(101, customer.getCustomerID());
        Assertions.assertEquals("John", customer.getFirstName());
        Assertions.assertEquals("Doe", customer.getLastName());
        Assertions.assertEquals("john.doe@example.com", customer.getEmail());
        Assertions.assertEquals("123-456-7890", customer.getPhoneNumber());
    }

    @Test
    void testCustomerSetters() {
        Customer customer = new Customer();
        customer.setCustomerID(102);
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setEmail("jane.smith@example.com");
        customer.setPhoneNumber("987-654-3210");

        Assertions.assertEquals(102, customer.getCustomerID());
        Assertions.assertEquals("Jane", customer.getFirstName());
        Assertions.assertEquals("Smith", customer.getLastName());
        Assertions.assertEquals("jane.smith@example.com", customer.getEmail());
        Assertions.assertEquals("987-654-3210", customer.getPhoneNumber());
    }
}