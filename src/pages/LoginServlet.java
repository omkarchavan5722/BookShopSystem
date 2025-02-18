package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dao.BookDaoImpl;
import dao.CustomerDaoImpl;
import pojo.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl custDao;
	private BookDaoImpl bookDao;

	public void init() throws ServletException {
		try {
			custDao = new CustomerDaoImpl();
			bookDao = new BookDaoImpl();
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
			bookDao.cleanUp();
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
				// Get a HttpSession from WC
				HttpSession session = request.getSession();
				// adding clnt info to HttpSession object to get the details of clnt on every
				// web page of this web app (provided cookies are enabled)
				session.setAttribute("clnt_info", authenticatedCustomer);
				// adding dao instances into session scope attributes.
				session.setAttribute("cust_dao", custDao);
				session.setAttribute("book_dao", bookDao);
				// adding cart instance in session scope
				session.setAttribute("cart", new ArrayList<Integer>());
				pw.print("<h4 align='center'>Login Successful!!!</h4>");
				pw.print("<h4 align='center'>Hello " + authenticatedCustomer.getName() + "</h4>");
				// Above content is ignored by WC and PW buffer data is cleared as we are redirecting a clnt to next location 
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
