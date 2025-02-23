package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set response content type
		response.setContentType("text/html");
		// get the session from WC
		HttpSession session = request.getSession();
		BookDaoImpl bookDao = (BookDaoImpl) session.getAttribute("book_dao");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("Here is your cart details!!!");
			if (cart.size() > 0) {
				double total = 0;
				List<Book> booksInCart = bookDao.getSelectedBookDetails(cart);
				for (Book b : booksInCart) {
					pw.print(b + "</br>");
					total += b.getPrice();
				}
				pw.print("<h4>Total: Rs." + total + "</h4>");
			}
			pw.print("<a href='logout'>Log me out</a>");
		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass().getName(), e);
		}
	}

}
