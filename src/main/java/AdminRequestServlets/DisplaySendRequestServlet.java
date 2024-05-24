package AdminRequestServlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DBUtils.SupplierRequestDBUtil;
import Model.SupplierRequest;
import interfaces.iSupplierRequest;

/**
 * Servlet implementation class DisplaySendRequestServlet
 */
public class DisplaySendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iSupplierRequest dbUtil = new SupplierRequestDBUtil();
        List<SupplierRequest> supplierRequests = dbUtil.getAllSupplierRequests();

        // Get the parameter indicating which page to navigate to
        String page = request.getParameter("page");
        if (page != null && !page.isEmpty()) {
            if (page.equals("supplier")) {
                // Forward the request to SupplierRequests.jsp to display pending requests
                request.setAttribute("supplierRequests", filterPendingRequests(supplierRequests));
                RequestDispatcher dispatcher = request.getRequestDispatcher("adminViewPendingOrdes.jsp");
                dispatcher.forward(request, response);
            } else if (page.equals("orders")) {
                // Forward the request to ViewOrders.jsp to display accepted requests
                request.setAttribute("supplierRequests", filterAcceptedRequests(supplierRequests));
                RequestDispatcher dispatcher = request.getRequestDispatcher("adminviewAcceptedOrdes.jsp");
                dispatcher.forward(request, response);
            } else if (page.equals("rejected")) {
                // Forward the request to ViewRejectedOrders.jsp to display rejected requests
                request.setAttribute("supplierRequests", filterRejectedRequests(supplierRequests));
                RequestDispatcher dispatcher = request.getRequestDispatcher("adminViewRejectedOrders.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // If no page parameter is specified, forward to a default page
            // For example, forward to SupplierRequests.jsp by default
            request.setAttribute("supplierRequests", filterPendingRequests(supplierRequests));
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminViewPendingOrdes.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Method to filter pending requests
    private List<SupplierRequest> filterPendingRequests(List<SupplierRequest> supplierRequests) {
        List<SupplierRequest> pendingRequests = new ArrayList<>();
        for (SupplierRequest request : supplierRequests) {
            String status = request.getStatus();
            if (status != null && status.equalsIgnoreCase("Pending")) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    private List<SupplierRequest> filterAcceptedRequests(List<SupplierRequest> supplierRequests) {
        List<SupplierRequest> acceptedRequests = new ArrayList<>();
        for (SupplierRequest request : supplierRequests) {
            String status = request.getStatus();
            if (status != null && status.equalsIgnoreCase("Accepted")) {
                acceptedRequests.add(request);
            }
        }
        return acceptedRequests;
    }

    private List<SupplierRequest> filterRejectedRequests(List<SupplierRequest> supplierRequests) {
        List<SupplierRequest> rejectedRequests = new ArrayList<>();
        for (SupplierRequest request : supplierRequests) {
            String status = request.getStatus();
            if (status != null && status.equalsIgnoreCase("Rejected")) {
                rejectedRequests.add(request);
            }
        }
        return rejectedRequests;
    }

}
