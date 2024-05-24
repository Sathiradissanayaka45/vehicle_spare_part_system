package AdminRequestServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierRequestDBUtil;
import Model.SupplierRequest;

/**
 * Servlet implementation class UpdateSendSupplierRequestServlet
 */
public class UpdateSendSupplierRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("idrequest"));

        SupplierRequestDBUtil dbUtil = new SupplierRequestDBUtil();
        SupplierRequest supplierRequest = dbUtil.getSupplierRequestById(requestId);

        request.setAttribute("supplierRequest", supplierRequest);
        request.getRequestDispatcher("UpdateSupplierRequest.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        int requestId = Integer.parseInt(request.getParameter("idrequest"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String details = request.getParameter("details");
        String status = request.getParameter("status");

        // Get the existing supplier request
        SupplierRequestDBUtil dbUtil = new SupplierRequestDBUtil();
        SupplierRequest existingRequest = dbUtil.getSupplierRequestById(requestId);

        // Create a new SupplierRequest object with the existing ID and updated details
        SupplierRequest updatedRequest = new SupplierRequest(
            requestId, // Existing ID
            productName,
            quantity,
            details,
            status,
            existingRequest.getRequestTime() // Keeping the existing request time
        );

        // Update the database
        boolean isSuccess = dbUtil.updateSupplierRequest(updatedRequest);

        // Redirect to appropriate page based on update success
        if (isSuccess) {
            response.sendRedirect("DisplaySendRequestServlet?page=supplier");
        } else {
            // Handle failure, maybe show an error message
            response.getWriter().println("Failed to update the request.");
        }
    }


}
