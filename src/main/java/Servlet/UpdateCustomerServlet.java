package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DBUtils.CustomerDBUtil;
import Model.Customer;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for updating customer information.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String id = request.getParameter("cusid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        // Flag to check if the update was successful
        boolean isUpdateSuccessful;

        // Create an instance of the interface using polymorphism
        CustomerDBUtil dbUtil = new CustomerDBUtil();

        // Update the customer information in the database
        isUpdateSuccessful = dbUtil.updatecustomer(id, name, email, phone, username, password);

        if (isUpdateSuccessful) {
            // Retrieve updated customer details for display
            List<Customer> cusDetails = dbUtil.getCustomerDetails(id);
            request.setAttribute("cusDetails", cusDetails);

            // Forward the request and response to the my-account.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("my-account.jsp");
            dispatcher.forward(request, response);
        } else {
            // Handle the case where the update is not successful if needed
            List<Customer> cusDetails = dbUtil.getCustomerDetails(id);
            request.setAttribute("cusDetails", cusDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("my-account.jsp");
            dispatcher.forward(request, response);
        }
    }

}
