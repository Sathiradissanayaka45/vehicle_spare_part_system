package PaymentServlet;

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
 * Servlet implementation class GetPaymentsServlet
 */
public class GetPaymentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = CustomerDBUtil.cusID;
        System.out.println("Customer ID: " + customerId); // Log customer ID

        iPayment dbutil = new PaymentDBUtil();
        List<Payment> payments = dbutil.getPaymentsByCustomerId(customerId);

        System.out.println("Number of payments fetched: " + payments.size()); // Log number of payments fetched

        // Get the value of 'page' parameter from the request
        String page = request.getParameter("page");

        // Filter payments based on the 'page' parameter
        if (page != null) {
            if (page.equalsIgnoreCase("Pending")) {
                payments = payments.stream()
                                  .filter(payment -> payment.getStatus().equalsIgnoreCase("Pending"))
                                  .collect(Collectors.toList());
                // Forward to pending-orders.jsp for Pending orders
                request.setAttribute("payments", payments);
                request.getRequestDispatcher("payments.jsp").forward(request, response);
                return;
            } else if (page.equalsIgnoreCase("Accepted")) {
                payments = payments.stream()
                                  .filter(payment -> payment.getStatus().equalsIgnoreCase("Accepted"))
                                  .collect(Collectors.toList());
                // Forward to accepted-orders.jsp for Accepted orders
                request.setAttribute("payments", payments);
                request.getRequestDispatcher("accepted-orders.jsp").forward(request, response);
                return;
            }
        }

        // Default forward (this shouldn't happen if the above conditions are met)
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("payments.jsp").forward(request, response);
    }
}
