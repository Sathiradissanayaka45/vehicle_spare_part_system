package DBUtils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import DBConnect.DBConnect;
import Model.Product;
import interfaces.iProduct;

public class ProductDBUtil implements iProduct {
	
    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    @Override
    public boolean addProduct(Product product) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO product (name, details, price, stock, image_path) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDetails());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getImagePath());

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
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM product";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idproduct = rs.getInt("idproduct");
                String name = rs.getString("name");
                String details = rs.getString("details");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String imagePath = rs.getString("image_path");
                Product product = new Product(idproduct, name, details, price, stock, imagePath);
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
    public Product getProductById(int idproduct) {
        Product product = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM product WHERE idproduct=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idproduct);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("idproduct");
                String name = rs.getString("name");
                String details = rs.getString("details");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String imagePath = rs.getString("image_path");
                product = new Product(productId, name, details, price, stock, imagePath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE product SET name=?, details=?, price=?, stock=?, image_path=? WHERE idproduct=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDetails());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getImagePath());
            pstmt.setInt(6, product.getId());
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
    public boolean deleteProduct(int idproduct) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM product WHERE idproduct=?";
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
