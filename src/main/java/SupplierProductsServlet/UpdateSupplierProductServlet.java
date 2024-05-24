package SupplierProductsServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBUtils.SupplierProductDBUtil;
import Model.SupplierProduct;
import interfaces.iSupplierProducts;

/**
 * Servlet implementation class UpdateSupplierProductServlet
 */
public class UpdateSupplierProductServlet extends HttpServlet {
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
        iSupplierProducts dbUtil = new SupplierProductDBUtil();
        SupplierProduct existingProduct = dbUtil.getSupplierProductById(productId);

        // If the existing product is found, update it with new data
        if (existingProduct != null) {

            // Create a new Product object with updated data
        	SupplierProduct updatedProduct = new SupplierProduct(productId, name, details, price, stock);

            // Update the product in the database
            boolean success = dbUtil.updateSupplierProduct(updatedProduct);

            if (success) {
                // Redirect to display-product.jsp
                response.sendRedirect("DisplaySupplierProductsServlet");
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
        iSupplierProducts dbUtil = new SupplierProductDBUtil();
        SupplierProduct product = dbUtil.getSupplierProductById(productId);

        if (product != null) {
            // Set product as an attribute
            request.setAttribute("product", product);

            // Forward the request
            request.getRequestDispatcher("update_supplier_product.jsp").forward(request, response);
        } else {
            // Handle case when product is not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }

}
