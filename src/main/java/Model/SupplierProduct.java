package Model;

public class SupplierProduct {
	
    private int idproduct;
    private String name;
    private String details;
    private double price;
    private int stock;

    // Constructor
    public SupplierProduct(int idproduct, String name, String details, double price, int stock) {
        this.idproduct = idproduct;
        this.name = name;
        this.details = details;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public int getId() {
        return idproduct;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}
