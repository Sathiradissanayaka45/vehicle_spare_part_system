package ProductServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.ProductDBUtil;
import interfaces.iProduct;

/**
 * Servlet implementation class DeleteProductServlet
 */
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve product ID from request parameters
	    int productId = Integer.parseInt(request.getParameter("idproduct"));

	    // Delete the product from the database
	    iProduct dbUtil = new ProductDBUtil();
	    boolean success = dbUtil.deleteProduct(productId);

	    if (success) {
	        // Return success status
	        response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	        // Return error status
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product");
	    }
	}


}
