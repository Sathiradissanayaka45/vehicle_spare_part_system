package interfaces;

import Model.Payment;
import java.util.List;

public interface iPayment {
    boolean addPayment(Payment payment);
    
    List<Payment> getPaymentsByCustomerId(int customerId);
    
    Payment getPaymentById(int payment);
    
    boolean updatePayment(Payment payment);
    
    boolean deletePaymnet(int idpayment);
    
    boolean updatePaymentStatus(int paymentId, String status);
    
    List<Payment> getAllPayments();
}
