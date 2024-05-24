package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import Model.Customer;

public class CustomerDBUtil implements interfaces.iDBUtil{
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	public static int cusID;
	

	public  boolean validate (String userName, String password) 
	{
		
		//validate data
		
		try
		{
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			//sql query for retrieve
			String sql = "select * from customer where username='"+userName+"' and password = '"+password+"'";
            rs = stmt.executeQuery(sql);
			
			//user name and password match if get data
			if(rs.next())
			{
			  cusID = rs.getInt(1);
			  isSuccess = true;	
			}else
			{
			  isSuccess = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public List<Customer> getCustomer(String userName)
	{
		ArrayList<Customer> customer = new ArrayList();
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			String sql = "select * from customer where username = '"+userName+"'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String username = rs.getString(5);
				String password = rs.getString(6);
				
				//sent data to Customer constructor
				Customer cus = new Customer(id, name, email, phone, username,password);
				
				//sent data to arraylist
				customer.add(cus);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return customer;
	}
	
	public boolean insertcustomer(String name, String phone, String email, String username, String password)
	{
		boolean isSuccess = false;
		
		try 
		{
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			//insert data to database
			String sql = "insert into customer values(0, '"+name+"', '"+email+"', '"+phone+"', '"+username+"', '"+password+"')";
			int rs = stmt.executeUpdate(sql);
			//insert success out 1..unsuccess out 0
			if(rs>0) {
				isSuccess = true;
			}else {
				isSuccess = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isSuccess;
	}
	
    // Method Overriding: Implementing a method from the interface
    @Override
    public boolean updatecustomer(String id, String name, String email, String phone, String username, String password) {
        try {
            int ID = Integer.parseInt(id);
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "UPDATE customer SET name = ?, email = ? , phone = ?, username = ?, password = ? where id = ?";

            // Using PreparedStatement for better performance and security
            PreparedStatement statemnt = null;

            statemnt = con.prepareStatement(sql);
            statemnt.setString(1, name);
            statemnt.setString(2, email);
            statemnt.setString(3, phone);
            statemnt.setString(4, username);
            statemnt.setString(5, password);
            statemnt.setInt(6, ID);
            int rs = statemnt.executeUpdate();

            if (rs > 0) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Method Overriding: Implementing a method from the interface
    @Override
    public List<Customer> getCustomerDetails(String Id) {
        int convertedID = Integer.parseInt(Id);
        ArrayList<Customer> cus = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "select * from customer where id = '" + convertedID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);

                // Creating Customer objects to encapsulate customer data
                Customer c = new Customer(id, name, email, phone, username, password);
                cus.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }

    // Method Overriding: Implementing a method from the interface
    @Override
    public boolean deleteCustomer(String id) {
        int convId = Integer.parseInt(id);

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "delete from customer where id = '" + convId + "'";
            int r = stmt.executeUpdate(sql);

            if (r > 0) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }



}
