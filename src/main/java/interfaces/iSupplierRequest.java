package interfaces;

import java.util.List;

import Model.SupplierRequest;

public interface iSupplierRequest {
    boolean addSupplierRequest(SupplierRequest request);
    
    List<SupplierRequest> getAllSupplierRequests();
    
    boolean updateStatus(int requestId, String status);
    
    boolean deletePendingRequest(int requestId);
    
    SupplierRequest getSupplierRequestById(int requestId);
    
    boolean updateSupplierRequest(SupplierRequest request);
    
    boolean deleteAdminRequest(int requestId);
    
}
