package Model;

public class Product {
    
    private int idproduct;
    private String name;
    private String details;
    private double price;
    private int stock;
    private String imagePath;

    // Constructor
    public Product(int idproduct, String name, String details, double price, int stock, String imagePath) {
        this.idproduct = idproduct;
        this.name = name;
        this.details = details;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }
}
