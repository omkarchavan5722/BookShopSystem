package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import pojo.Customer;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set response content type
		response.setContentType("text/html");
		// get the session from WC
		HttpSession session = request.getSession();
		Customer clntInfo = (Customer) session.getAttribute("clnt_info");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("Successfully Logged out!!!"); 
			pw.print("Thanks for visiting "+clntInfo.getName());
			// discarding the session: clnt's conversational state
			session.invalidate();
			pw.print("<a href='login.html'>Visit Again</a>");
		}
	}

}
