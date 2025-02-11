package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDaoImpl;
import pojo.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl custDao;

	public void init() throws ServletException {
		try {
			custDao = new CustomerDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init", e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			custDao.cleanUp();
		} catch (SQLException e) {
			System.out.println("err in destroy " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Customer authenticatedCustomer = custDao.authenticateCustomer(email, password);
			if (authenticatedCustomer != null) {
				pw.print("<h4 align='center'>Login Successful!!!</h4>");
				pw.print("<h4 align='center'>Hello "+authenticatedCustomer.getName()+"</h4>");
				response.sendRedirect("category");
			} else {
				pw.print("<h4 align='center'>Invalid Login</h4>");
				pw.print(" <h4 align='center'><a href='login.html'>Please Retry</a></h4>");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass().getName(), e);
		}
	}

}
