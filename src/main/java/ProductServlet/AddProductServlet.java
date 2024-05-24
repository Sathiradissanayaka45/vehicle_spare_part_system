package ProductServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import DBUtils.ProductDBUtil;
import Model.Product;
import interfaces.iProduct;

/**
 * Servlet implementation class AddProductServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve form data
	    String name = request.getParameter("name");
	    String details = request.getParameter("details");
	    double price = Double.parseDouble(request.getParameter("price"));
	    int stock = Integer.parseInt(request.getParameter("stock"));

	    // Save uploaded image to server and get the saved filename
	    String imageName = saveImage(request.getPart("image"));

	    // Construct URL for the saved image
	    String imageUrl = request.getContextPath() + "/images/" + imageName; // Assuming images are stored in "/images" directory

	    // Create Product object with image URL
	    Product product = new Product(0, name, details, price, stock, imageUrl); // 0 for temporary ID

	    // Call database utility method to add product
	    iProduct dbUtil = new ProductDBUtil();
	    boolean success = dbUtil.addProduct(product);

	    if (success) {
	        // Product added successfully
	        response.sendRedirect("admin_dashboard.jsp"); // Redirect to the success page
	    } else {
	        // Product addition failed
	        response.sendRedirect("add-product.jsp"); // Redirect back to the add product page
	    }
	}

	// Method to save uploaded image to server and return the filename
	private String saveImage(Part part) throws IOException {
	    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	    String directory = getServletContext().getRealPath("/images"); // Store images in the "/images" directory within the web application
	    File uploads = new File(directory);
	    if (!uploads.exists()) {
	        uploads.mkdirs();
	    }
	    String filePath = directory + File.separator + fileName;
	    try {
	        part.write(filePath);
	    } catch (IOException e) {
	        e.printStackTrace(); // Print detailed error information
	        throw new IOException("Failed to save image: " + e.getMessage()); // Propagate the exception
	    }
	    return fileName;
	}

}
