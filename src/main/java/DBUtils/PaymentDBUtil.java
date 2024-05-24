package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import Model.Payment;
import Model.Product;
import interfaces.iPayment;

public class PaymentDBUtil implements iPayment{
    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    @Override
    public boolean addPayment(Payment payment) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO payments (customer_id, payment_slip, full_name, mobile_number, address, postal_code,status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, payment.getCustomerId());
            pstmt.setString(2, payment.getPaymentSlip());
            pstmt.setString(3, payment.getFullName());
            pstmt.setString(4, payment.getMobileNumber());
            pstmt.setString(5, payment.getAddress());
            pstmt.setString(6, payment.getPostalCode());
            pstmt.setString(7,"Pending");
            
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        
        return isSuccess;
    }
    @Override
    public List<Payment> getPaymentsByCustomerId(int customerId) {
        List<Payment> payments = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM payments WHERE customer_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int paymentId = rs.getInt("idpayments");
                String paymentSlip = rs.getString("payment_slip");
                String fullName = rs.getString("full_name");
                String mobileNumber = rs.getString("mobile_number");
                String address = rs.getString("address");
                String postalCode = rs.getString("postal_code");
                String status = rs.getString("status");

                Payment payment = new Payment(paymentId, customerId, paymentSlip, fullName, mobileNumber, address, postalCode, status);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return payments;
    }
    
    @Override
    public Payment getPaymentById(int idpayment) {
    	Payment payment = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM payments WHERE idpayments=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idpayment);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int paymentId = rs.getInt("idpayments");
                int customerId = rs.getInt("customer_id");
                String paymentSlip = rs.getString("payment_slip");
                String fullName = rs.getString("full_name");
                String mobileNumber = rs.getString("mobile_number");
                String address = rs.getString("address");
                String postalCode = rs.getString("postal_code");
                String status = rs.getString("status");
                payment = new Payment(paymentId, customerId, paymentSlip, fullName, mobileNumber, address, postalCode, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return payment;
    }
    
    @Override
    public boolean updatePayment(Payment payment) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE payments SET full_name=?, mobile_number=?, address=?, postal_code=? WHERE idpayments=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, payment.getFullName());
            pstmt.setString(2, payment.getMobileNumber());
            pstmt.setString(3, payment.getAddress());
            pstmt.setString(4, payment.getPostalCode());
            pstmt.setInt(5, payment.getPaymentId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return isSuccess;
    }
    @Override
    public boolean deletePaymnet(int idpayment) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM payments WHERE idpayments=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idpayment);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return isSuccess;
    }
    @Override
    public boolean updatePaymentStatus(int paymentId, String status) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE payments SET status=? WHERE idpayments=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, paymentId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return isSuccess;
    }
    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM payments";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int paymentId = rs.getInt("idpayments");
                int customerId = rs.getInt("customer_id");
                String paymentSlip = rs.getString("payment_slip");
                String fullName = rs.getString("full_name");
                String mobileNumber = rs.getString("mobile_number");
                String address = rs.getString("address");
                String postalCode = rs.getString("postal_code");
                String status = rs.getString("status");

                Payment payment = new Payment(paymentId, customerId, paymentSlip, fullName, mobileNumber, address, postalCode, status);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return payments;
    }
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
