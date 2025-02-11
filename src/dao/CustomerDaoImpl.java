package dao;

import static utils.DBUtils.getDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Customer;

public class CustomerDaoImpl implements ICustomerDao {
	private Connection cn;
	private PreparedStatement pst1;

	public CustomerDaoImpl() throws SQLException, ClassNotFoundException {
		cn = getDBConnection();
		pst1 = cn.prepareStatement("select * from my_customers where email=? and password=?");
		System.out.println("customer dao created...");
	}

	@Override
	public Customer authenticateCustomer(String email, String password) throws SQLException {
		System.out.println("In customer Dao: authenticateCustomer");
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				return new Customer(rst.getInt(1), rst.getDouble(2), email, rst.getString(4), password, rst.getDate(6),
						rst.getString(7));
		}
		return null;
	}
	
	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (cn != null) {
			cn.close();
		}
		System.out.println("Customer dao cleaned..");
	}
}
