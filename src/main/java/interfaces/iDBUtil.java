package interfaces;

import java.util.List;
import Model.Customer;


public interface iDBUtil {
		
	public  boolean validate (String userName, String password) ;
	
	public List<Customer> getCustomer(String userName);
	
	public boolean insertcustomer(String name, String phone, String email, String username, String password);
	
	// Encapsulation: Method to update customer information in the database
	public boolean updatecustomer(String id, String name, String email, String phone, String username, String password);

	// Abstraction: Method to retrieve customer details based on customer ID
	public List<Customer> getCustomerDetails(String Id);

	// Encapsulation: Method to delete a customer from the database
	public boolean deleteCustomer(String id);

}
