package Model;

import java.sql.Timestamp;

public class SupplierRequest {
    private int id;
    private String productName;
    private int quantity;
    private String details;
    private String status;
    private Timestamp requestTime;

    // Constructor with all fields
    public SupplierRequest(int id, String productName, int quantity, String details, String status, Timestamp requestTime) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.details = details;
        this.status = status;
        this.requestTime = requestTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }
}
