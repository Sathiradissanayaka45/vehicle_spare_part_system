package SupplierRequestServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import DBUtils.CustomerDBUtil;
import DBUtils.PaymentDBUtil;
import Model.Payment;
import interfaces.iPayment;

/**
 * Servlet implementation class ViewRecievedOrders
 */
public class ViewRecievedOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iPayment dbutil = new PaymentDBUtil();
        List<Payment> payments = dbutil.getAllPayments();

        // Filter payments by status (Pending)
        payments = payments.stream()
                          .filter(payment -> payment.getStatus().equalsIgnoreCase("Pending"))
                          .collect(Collectors.toList());

        // Set the payments attribute in the request
        request.setAttribute("payments", payments);

        // Forward the request to the received-orders.jsp page
        request.getRequestDispatcher("received-orders.jsp").forward(request, response);
    }
}
