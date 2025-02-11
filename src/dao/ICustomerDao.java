package dao;

import java.sql.SQLException;

import pojo.Customer;

public interface ICustomerDao {
	Customer authenticateCustomer(String email, String password) throws SQLException;
}
