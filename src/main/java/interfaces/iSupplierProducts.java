package interfaces;

import java.util.List;

import Model.SupplierProduct;

public interface iSupplierProducts {
	
    boolean addProduct(SupplierProduct product);
    
    List<SupplierProduct> getAllSupplierProducts();
    
    SupplierProduct getSupplierProductById(int idproduct);
    
    boolean updateSupplierProduct(SupplierProduct product);
    
    boolean deleteSupplierProduct(int idproduct);

}
