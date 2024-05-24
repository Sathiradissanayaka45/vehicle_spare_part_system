package SupplierProductsServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DBUtils.ProductDBUtil;
import DBUtils.SupplierProductDBUtil;
import Model.Product;
import Model.SupplierProduct;
import interfaces.iProduct;
import interfaces.iSupplierProducts;

/**
 * Servlet implementation class DisplaySupplierProductsServlet
 */
public class DisplaySupplierProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call database utility method to retrieve all products
    	iSupplierProducts dbUtil = new SupplierProductDBUtil();
        List<SupplierProduct> productList = dbUtil.getAllSupplierProducts();

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("display-supplier-products.jsp").forward(request, response);
        
    }

}
