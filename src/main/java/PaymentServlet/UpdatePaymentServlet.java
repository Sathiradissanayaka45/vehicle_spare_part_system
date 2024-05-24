package PaymentServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.CustomerDBUtil;
import DBUtils.PaymentDBUtil;
import DBUtils.ProductDBUtil;
import Model.Payment;
import Model.Product;
import interfaces.iPayment;
import interfaces.iProduct;

/**
 * Servlet implementation class UpdatePaymentServlet
 */
public class UpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        String fullName = request.getParameter("fullName");
        String mobileNumber = request.getParameter("mobileNumber");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postalCode");
        String status = request.getParameter("postalCode");
        int customerId = CustomerDBUtil.cusID;

        // Get the existing product from the database
        iPayment dbUtil = new PaymentDBUtil();
        Payment existingPaymnet = dbUtil.getPaymentById(paymentId);

        // If the existing product is found, update it with new data
        if (existingPaymnet != null) {
            // Keep the existing image path
            String paymentSlip = existingPaymnet.getPaymentSlip();

            // Create a new Product object with updated data
            Payment updatedPayment = new Payment(paymentId, customerId,paymentSlip, fullName, mobileNumber, address, postalCode, status);

            // Update the product in the database
            boolean success = dbUtil.updatePayment(updatedPayment);

            if (success) {
                // Redirect to display-product.jsp
                response.sendRedirect("GetPaymentsServlet");
            } else {
                // Handle failure to update
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update payment");
            }
        } else {
            // Handle case when product is not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment not found");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve product ID from request parameters
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));

        // Get the product from the database
        iPayment dbUtil = new PaymentDBUtil();
        Payment payment = dbUtil.getPaymentById(paymentId);

        if (payment != null) {
            // Set product as an attribute
            request.setAttribute("payment", payment);

            // Forward the request
            request.getRequestDispatcher("update_payment.jsp").forward(request, response);
        } else {
            // Handle case when product is not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }

}
