package pages;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add_to_cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the session from WC
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> shoppingCart = (ArrayList<Integer>) session.getAttribute("cart");
		for (String s : request.getParameterValues("book_id")) {
			shoppingCart.add(Integer.parseInt(s));
		}
		System.out.println("cart contents " + shoppingCart);
		response.sendRedirect("category");
	}

}
