package AdminRequestServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierRequestDBUtil;

/**
 * Servlet implementation class DeleteSendSupplierRequestServlet
 */
public class DeleteSendSupplierRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the request to delete
        int requestId = Integer.parseInt(request.getParameter("idrequest"));

        // Create an instance of SupplierRequestDBUtil to perform the delete operation
        SupplierRequestDBUtil dbUtil = new SupplierRequestDBUtil();

        // Call the deletePendingRequest method to delete the request
        boolean isSuccess = dbUtil.deletePendingRequest(requestId);

        // Redirect to appropriate page based on deletion success
        if (isSuccess) {
            response.sendRedirect("DisplaySendRequestServlet?page=supplier");
        } else {
            // Handle failure, maybe show an error message
            response.getWriter().println("Failed to delete the request.");
        }
    }
}
