package PaymentServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.PaymentDBUtil;
import DBUtils.ProductDBUtil;
import interfaces.iPayment;
import interfaces.iProduct;

/**
 * Servlet implementation class DeletePaymentServlet
 */
public class DeletePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve product ID from request parameters
	    int idpayment = Integer.parseInt(request.getParameter("idpayment"));

	    // Delete the product from the database
	    iPayment dbUtil = new PaymentDBUtil();
	    boolean success = dbUtil.deletePaymnet(idpayment);

	    if (success) {
	        // Return success status
	        response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	        // Return error status
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product");
	    }
	}

}
