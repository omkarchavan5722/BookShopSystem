package dao;

import java.sql.SQLException;
import java.util.List;

public interface IBookDao {
	List<String> getAllCategories() throws SQLException;
}
