package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import Model.SupplierRequest;
import interfaces.iSupplierRequest;

public class SupplierRequestDBUtil implements iSupplierRequest {
    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    @Override
    public boolean addSupplierRequest(SupplierRequest request) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO supplier_requests (product_name, quantity, details, status) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getProductName());
            pstmt.setInt(2, request.getQuantity());
            pstmt.setString(3, request.getDetails());
            pstmt.setString(4, "Pending"); // Status automatically set to Pending
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
    public List<SupplierRequest> getAllSupplierRequests() {
        List<SupplierRequest> supplierRequests = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM supplier_requests";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idsupplier_requests");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                String details = rs.getString("details");
                String status = rs.getString("status");
                Timestamp requestTime = rs.getTimestamp("request_time");

                SupplierRequest supplierRequest = new SupplierRequest(id, productName, quantity, details, status, requestTime);
                supplierRequests.add(supplierRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return supplierRequests;
    }
    @Override
    public boolean updateStatus(int requestId, String status) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE supplier_requests SET status = ? WHERE idsupplier_requests = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, requestId);
            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Update failed
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean deletePendingRequest(int requestId){
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM supplier_requests WHERE idsupplier_requests=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, requestId);
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
    public SupplierRequest getSupplierRequestById(int requestId) {
        SupplierRequest supplierRequest = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM supplier_requests WHERE idsupplier_requests=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, requestId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                String details = rs.getString("details");
                String status = rs.getString("status");
                Timestamp requestTime = rs.getTimestamp("request_time");

                supplierRequest = new SupplierRequest(requestId, productName, quantity, details, status, requestTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return supplierRequest;
    }
    @Override
    public boolean updateSupplierRequest(SupplierRequest request) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE supplier_requests SET product_name=?, quantity=?, details=?, status=? WHERE idsupplier_requests=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getProductName());
            pstmt.setInt(2, request.getQuantity());
            pstmt.setString(3, request.getDetails());
            pstmt.setString(4, request.getStatus());
            pstmt.setInt(5, request.getId());
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
    public boolean deleteAdminRequest(int requestId) {
        boolean isSuccess = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM supplier_requests WHERE idsupplier_requests=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, requestId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
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

    // Add any necessary methods or code for closing resources here
}
