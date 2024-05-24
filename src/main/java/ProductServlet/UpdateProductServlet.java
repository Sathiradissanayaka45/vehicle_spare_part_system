package ProductServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import DBUtils.ProductDBUtil;
import Model.Product;
import interfaces.iProduct;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int productId = Integer.parseInt(request.getParameter("idproduct"));
        String name = request.getParameter("name");
        String details = request.getParameter("details");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // Get the existing product from the database
        iProduct dbUtil = new ProductDBUtil();
        Product existingProduct = dbUtil.getProductById(productId);

        // If the existing product is found, update it with new data
        if (existingProduct != null) {
            // Keep the existing image path
            String imagePath = existingProduct.getImagePath();

            // Create a new Product object with updated data
            Product updatedProduct = new Product(productId, name, details, price, stock, imagePath);

            // Update the product in the database
            boolean success = dbUtil.updateProduct(updatedProduct);

            if (success) {
                // Redirect to display-product.jsp
                response.sendRedirect("DisplayProductsServlet");
            } else {
                // Handle failure to update
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update product");
            }
        } else {
            // Handle case when product is not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve product ID from request parameters
        int productId = Integer.parseInt(request.getParameter("idproduct"));

        // Get the product from the database
        iProduct dbUtil = new ProductDBUtil();
        Product product = dbUtil.getProductById(productId);

        if (product != null) {
            // Set product as an attribute
            request.setAttribute("product", product);

            // Forward the request
            request.getRequestDispatcher("update_product.jsp").forward(request, response);
        } else {
            // Handle case when product is not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }
}
