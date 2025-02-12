package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.DBUtils.getDBConnection;

public class BookDaoImpl implements IBookDao {
	private java.sql.Connection cn;
	private PreparedStatement pst1;
	
	public BookDaoImpl() throws ClassNotFoundException, SQLException  {
		cn = getDBConnection();
		pst1 = cn.prepareStatement("select distinct category from dac_books");
		System.out.println("book dao created...");
	}
	
	@Override
	public List<String> getAllCategories() throws SQLException {
		System.out.println("In Book Dao: getAllCategories");
		List<String> categories = new ArrayList<String>();
		try(ResultSet rst = pst1.executeQuery()) {
			while(rst.next()) {
				categories.add(rst.getString(1));
			}
		}
		return categories;
	}

}
