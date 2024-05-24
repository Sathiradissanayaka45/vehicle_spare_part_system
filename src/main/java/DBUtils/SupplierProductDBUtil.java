package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import Model.SupplierProduct;
import interfaces.iSupplierProducts;

public class SupplierProductDBUtil implements iSupplierProducts{
	
    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    @Override
    public boolean addProduct(SupplierProduct product) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO supplier_products (name, details, price, stock) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDetails());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

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
    public List<SupplierProduct> getAllSupplierProducts() {
        List<SupplierProduct> productList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM supplier_products";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idproduct = rs.getInt("idsupplier_products");
                String name = rs.getString("name");
                String details = rs.getString("details");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                SupplierProduct product = new SupplierProduct(idproduct, name, details, price, stock);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return productList;
    }
    @Override
    public SupplierProduct getSupplierProductById(int idproduct) {
    	SupplierProduct product = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM supplier_products WHERE idsupplier_products=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idproduct);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("idsupplier_products");
                String name = rs.getString("name");
                String details = rs.getString("details");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                product = new SupplierProduct(productId, name, details, price, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return product;
    }

    @Override
    public boolean updateSupplierProduct(SupplierProduct product) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE supplier_products SET name=?, details=?, price=?, stock=? WHERE idsupplier_products=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDetails());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setInt(5, product.getId());
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
    public boolean deleteSupplierProduct(int idproduct) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM supplier_products WHERE idsupplier_products=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idproduct);
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
