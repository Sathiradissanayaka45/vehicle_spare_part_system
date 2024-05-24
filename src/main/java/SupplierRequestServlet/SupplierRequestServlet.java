package SupplierRequestServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierRequestDBUtil;
import Model.SupplierRequest;
import interfaces.iSupplierRequest;

/**
 * Servlet implementation class SupplierRequestServlet
 */
public class SupplierRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String productName = request.getParameter("product");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String details = request.getParameter("details");

        // Create SupplierRequest object
        SupplierRequest supplierRequest = new SupplierRequest(0, productName, quantity, details, "Pending", null); // 0 for temporary ID, status initially set to "Pending"

        // Call database utility method to add supplier request
        iSupplierRequest dbUtil = new SupplierRequestDBUtil();
        boolean success = dbUtil.addSupplierRequest(supplierRequest);

        if (success) {
            // Supplier request added successfully
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // Supplier request addition failed
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-supplier-request.jsp");
            dispatcher.forward(request, response);
        }
    }

}
