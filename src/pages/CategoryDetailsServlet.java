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
import pojo.Book;

/**
 * Servlet implementation class CategoryDetails
 */
@WebServlet("/cat_details")
public class CategoryDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String category = request.getParameter("cat");
			pw.print("Books under category " + category);
			// Get HttpSession from WC
			HttpSession session = request.getSession();
			// Get book dao instance to call bookDaoImpl method
			BookDaoImpl bookDao = (BookDaoImpl) session.getAttribute("book_dao");
			if (bookDao != null) {
				List<Book> books = bookDao.getAllBooksFromSelectedCategory(category);
				// dyn form generation
				pw.print("<form action='add_to_cart'>");
				pw.print("Choose Books<br>");
				// add checkboxes n book details
				for (Book b : books)
					pw.print("<input type='checkbox' name='book_id' value='" + b.getBookId() + "'>" + b+"<br>");
				// submit btn
				pw.print("<input type='submit' value='Add To Cart'>");
				pw.print("</form>");
			} else {
				pw.print("<h4>Cookies disabled...No session tracking!!!</h4>");
			}

		} catch (Exception e) {
			throw new ServletException("Err in do-get of " + getClass().getName(), e);
		}
	}

}
