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
 * Servlet implementation class DeleteCustomerServlet
 */
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for deleting a customer.
     * Uses the iCustomerDButil interface to interact with the database.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the customer ID parameter from the request
        String customerID = request.getParameter("cusid");
        boolean isDeletionSuccessful;

        // Create an instance of the interface using polymorphism
        CustomerDBUtil dbUtil = new CustomerDBUtil();

        // Delete the customer and capture the result
        isDeletionSuccessful = dbUtil.deleteCustomer(customerID);

        if (isDeletionSuccessful) {
            // Successful deletion, navigate to the index.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            // Unsuccessful deletion, retrieve customer details and forward to the index.jsp for further action
            List<Customer> customerDetails = dbUtil.getCustomerDetails(customerID);
            request.setAttribute("cusDetails", customerDetails);

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
