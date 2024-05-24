package SupplierRequestServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierRequestDBUtil;
import interfaces.iSupplierRequest;

/**
 * Servlet implementation class UpdateStatusServlet
 */
public class UpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request parameters
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");
        String status = null;

        // Determine the status based on the action
        if (action.equals("accept")) {
            status = "accepted";
        } else if (action.equals("reject")) {
            status = "rejected";
        }

        // Update status in the database
        iSupplierRequest dbUtil = new SupplierRequestDBUtil();
        boolean success = dbUtil.updateStatus(requestId, status);

        if (success) {
            // Redirect back to the SupplierRequests.jsp page
            response.sendRedirect("DisplaySupplierRequestsServlet?page=supplier");
        } else {
            // Handle update failure
            response.getWriter().println("Failed to update status.");
        }
    }

}
