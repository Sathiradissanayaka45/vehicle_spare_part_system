package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DBUtils.CustomerDBUtil;
import Model.Customer;
import interfaces.iDBUtil;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean isTrue;
	private static String Uname;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    // Catch insert data in login form
	    String userName = request.getParameter("uid");
	    String password = request.getParameter("pass");
	    
	    // Check if the username and password are admin credentials
	    if (userName.equals("admin") && password.equals("Admin@123")) {
	        // Navigate to admin_dashboard.jsp
	        response.sendRedirect("admin_dashboard.jsp");
	    } else if (userName.equals("supplier") && password.equals("Supplier@123")) {
	        // Navigate to supplier_dashboard.jsp
	        response.sendRedirect("supplier-dashboard.jsp");
	    } else {
	        // Proceed with regular user login
	        
	        // Validate user credentials using CustomerDBUtil
	        iDBUtil inter = new CustomerDBUtil();
	        isTrue = inter.validate(userName, password);
	        
	        if (isTrue) {
	            // Forward the request to index.jsp
	            RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
	            dis.forward(request, response);
	        } else {
	            // Print error message for incorrect credentials
	            out.println("<script type='text/javascript'>");
	            out.println("alert('Your username or password is incorrect');");
	            out.println("location='login.jsp'");
	            out.println("</script>");
	        }
	    }
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in before processing GET requests
        if (isTrue) {
            // Retrieve customer details for the logged-in user
        	iDBUtil dbUtil = new CustomerDBUtil();
            List<Customer> cusDetails = dbUtil.getCustomer(Uname);
            request.setAttribute("cusDetails", cusDetails);

            // Forward the request and response to the my-account.jsp page
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/my-account.jsp");
            dispatcher.forward(request, response);
        }
    }

}
