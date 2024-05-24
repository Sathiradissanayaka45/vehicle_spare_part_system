package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import DBUtils.CustomerDBUtil;
import interfaces.iDBUtil;

/**
 * Servlet implementation class CustomerinsertServlet
 */
public class CustomerinsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		//save insert data to variable
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("uid");
		String password = request.getParameter("psw");
		
		
		
		//catch output value from return value from dbutil
		boolean isTrue;
		
		iDBUtil inter = new CustomerDBUtil();
		
		isTrue = inter.insertcustomer(name, phone, email, username, password);
		
		if(isTrue == true) {
			RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		} else {
			RequestDispatcher dis2 = request.getRequestDispatcher("customerinsert.jsp");
			dis2.forward(request, response);
		}

}

}
