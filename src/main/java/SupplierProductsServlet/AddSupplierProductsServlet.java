package SupplierProductsServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierProductDBUtil;
import Model.SupplierProduct;
import interfaces.iSupplierProducts;

/**
 * Servlet implementation class AddSupplierProductsServlet
 */
public class AddSupplierProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve form data
	    String name = request.getParameter("name");
	    String details = request.getParameter("details");
	    double price = Double.parseDouble(request.getParameter("price"));
	    int stock = Integer.parseInt(request.getParameter("stock"));

	    // Create Product object with image URL
	    SupplierProduct product = new SupplierProduct(0, name, details, price, stock); // 0 for temporary ID

	    // Call database utility method to add product
	    iSupplierProducts dbUtil = new SupplierProductDBUtil();
	    boolean success = dbUtil.addProduct(product);

	    if (success) {
	        // Product added successfully
	        response.sendRedirect("supplier-dashboard.jsp"); // Redirect to the success page
	    } else {
	        // Product addition failed
	        response.sendRedirect("add-supplier-request.jsp"); // Redirect back to the add product page
	    }
	}

}
