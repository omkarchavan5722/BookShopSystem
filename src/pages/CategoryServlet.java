package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import pojo.Customer;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// Get a HttpSession from WC
			HttpSession session = request.getSession();
			Customer clntInfo = (Customer) session.getAttribute("clnt_info");
			BookDaoImpl bookDao = (BookDaoImpl) session.getAttribute("book_dao");
			if (clntInfo != null) {
				pw.print("<h4>Hi " + clntInfo.getName() + "</h4>");
				List<String> allCategories = bookDao.getAllCategories();
				//dyn form generation
				pw.print("<form action='cat_details'>");
				pw.print("Choose Category");
				pw.print("<select name='cat'>");
				for(String s : allCategories)
					pw.print("<option value="+s+">"+s+"</option>");
				pw.print("</select><br>");
				//submit btn
				pw.print("<input type='submit' value='Choose'>");
				pw.print("</form>");
				pw.print("<form action='view_cart' method='post'>");
				pw.print("<input type='submit' value='View Cart'>");
				pw.print("</form>");
				pw.print("<form action='checkout'>");
				pw.print("<input type='submit' value='Checkout'>");
				pw.print("</form>");
			} else {
				pw.print("<h4>Cookies disabled.. No session tracking!!!!</h4>");
			}

		} catch (Exception e) {
			throw new ServletException("err in do get of " + getClass().getName(), e);
		}
	}

}
