package SupplierProductsServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierProductDBUtil;
import interfaces.iSupplierProducts;

/**
 * Servlet implementation class DeleteSupplierProductServlet
 */
public class DeleteSupplierProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve product ID from request parameters
	    int productId = Integer.parseInt(request.getParameter("idproduct"));

	    // Delete the product from the database
	    iSupplierProducts dbUtil = new SupplierProductDBUtil();
	    boolean success = dbUtil.deleteSupplierProduct(productId);

	    if (success) {
	        // Return success status
	        response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	        // Return error status
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product");
	    }
	}

}
