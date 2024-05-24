package interfaces;

import java.util.List;
import Model.Product;

public interface iProduct {
    boolean addProduct(Product product);
    
    List<Product> getAllProducts();
    
    Product getProductById(int idproduct);
    
    boolean updateProduct(Product product);
    
    boolean deleteProduct(int idproduct);
}
