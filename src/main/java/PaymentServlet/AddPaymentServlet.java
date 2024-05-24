package PaymentServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import DBUtils.CustomerDBUtil;
import DBUtils.PaymentDBUtil;
import Model.Payment;
import interfaces.iPayment;

/**
 * Servlet implementation class AddPaymentServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String fullName = request.getParameter("fullName");
        String mobileNumber = request.getParameter("mobileNumber");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postalCode");
        
        // Fetch customer ID from CustomerDBUtil
        int customerId = CustomerDBUtil.cusID;

        // Handle file upload (payment slip)
	    String imageName = saveImage(request.getPart("paymentSlip"));
	    String imageUrl = request.getContextPath() + "/images/" + imageName; 

        // Process payment
        Payment payment = new Payment(0, customerId, imageUrl, fullName, mobileNumber, address, postalCode, "Pending");
        
        iPayment dbutil = new PaymentDBUtil();
        boolean success = dbutil.addPayment(payment);
        
        if(success) {
            response.sendRedirect("paymentSuccess.jsp");        	
        }else {
            response.sendRedirect("index.jsp");
        }
    }

    // Method to save uploaded image to server and return the filename
    private String saveImage(Part part) throws IOException {
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String directory = getServletContext().getRealPath("/uploads/paymentSlips");
        File uploads = new File(directory);
        if (!uploads.exists()) {
            uploads.mkdirs();
        }
        String filePath = directory + File.separator + fileName;
        try {
            part.write(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to save image: " + e.getMessage());
        }
        return fileName;
    }
}

