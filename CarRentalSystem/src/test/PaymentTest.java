package test;

import entity.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Date;

public class PaymentTest {

    @Test
    void testPaymentCreationAndGetters() {
        Date paymentDate = new Date();
        Payment payment = new Payment(301, 201, paymentDate, 150.00);
        Assertions.assertEquals(301, payment.getPaymentID());
        Assertions.assertEquals(201, payment.getLeaseID());
        Assertions.assertEquals(paymentDate, payment.getPaymentDate());
        Assertions.assertEquals(150.00, payment.getAmount(), 0.001);
    }

    @Test
    void testPaymentSetters() {
        Payment payment = new Payment();
        payment.setPaymentID(302);
        payment.setLeaseID(202);
        Date paymentDate = new Date();
        payment.setPaymentDate(paymentDate);
        payment.setAmount(200.50);

        Assertions.assertEquals(302, payment.getPaymentID());
        Assertions.assertEquals(202, payment.getLeaseID());
        Assertions.assertEquals(paymentDate, payment.getPaymentDate());
        Assertions.assertEquals(200.50, payment.getAmount(), 0.001);
    }
}