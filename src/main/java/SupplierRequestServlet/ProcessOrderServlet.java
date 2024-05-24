package SupplierRequestServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.PaymentDBUtil;
import interfaces.iPayment;

/**
 * Servlet implementation class ProcessOrderServlet
 */
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the paymentId and action from the request
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        String action = request.getParameter("action");

        iPayment dbutil = new PaymentDBUtil();

        // Process the action
        if ("accept".equalsIgnoreCase(action)) {
            dbutil.updatePaymentStatus(paymentId, "Accepted");
        } else if ("reject".equalsIgnoreCase(action)) {
            dbutil.updatePaymentStatus(paymentId, "Rejected");
        }

        // Redirect back to the received orders page
        response.sendRedirect("ViewRecievedOrders?page=Pending");
    }

}
