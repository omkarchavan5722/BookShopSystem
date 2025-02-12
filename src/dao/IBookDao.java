package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Book;

public interface IBookDao {
	// Method to get all available categories in book shop
	List<String> getAllCategories() throws SQLException;
	// Method to fetch all available books in selected category
	List<Book> getAllBooksFromSelectedCategory(String category) throws SQLException;
}
