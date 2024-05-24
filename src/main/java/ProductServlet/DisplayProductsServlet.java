package ProductServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DBUtils.ProductDBUtil;
import Model.Product;
import interfaces.iProduct;

/**
 * Servlet implementation class DisplayProductsServlet
 */
public class DisplayProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call database utility method to retrieve all products
        iProduct dbUtil = new ProductDBUtil();
        List<Product> productList = dbUtil.getAllProducts();

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("display-products.jsp").forward(request, response);
        
    }

}
