package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Book;

import static utils.DBUtils.getDBConnection;

public class BookDaoImpl implements IBookDao {
	private java.sql.Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;

	public BookDaoImpl() throws ClassNotFoundException, SQLException {
		cn = getDBConnection();
		pst1 = cn.prepareStatement("select distinct category from dac_books");
		pst2 = cn.prepareStatement("select * from dac_books where category=?");
		pst3 = cn.prepareStatement("select * from dac_books where id=?");
		System.out.println("book dao created...");
	}

	@Override
	public List<String> getAllCategories() throws SQLException {
		System.out.println("In Book Dao: getAllCategories");
		List<String> categories = new ArrayList<String>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				categories.add(rst.getString(1));
			}
		}
		return categories;
	}

	@Override
	public List<Book> getAllBooksFromSelectedCategory(String category) throws SQLException {
		System.out.println("In Book Dao: getAllBooksFromSelectedCategory");
		List<Book> books = new ArrayList<Book>();
		// Set IN parameters
		pst2.setString(1, category);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
				books.add(new Book(rst.getInt(1), rst.getString(2), rst.getString(3), category, rst.getDouble(5)));
			}
		}
		return books;
	}

	@Override
	public List<Book> getSelectedBookDetails(List<Integer> cart) throws SQLException {
		System.out.println("In Book Dao: getSelectedBookDetails");
		List<Book> books = new ArrayList<Book>();
		for (int bookId : cart) {
			pst3.setInt(1, bookId);
			try (ResultSet rst = pst3.executeQuery()) {
				if (rst.next()) {
					books.add(new Book(bookId, rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5)));
				}
			}
		}
		return books;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (cn != null)
			cn.close();
		System.out.println("Book dao cleaned...");
	}
}
