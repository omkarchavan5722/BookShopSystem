package pojo;

import java.sql.Date;

public class Customer {
	private int customerId;
	private double depositeAmount;
	private String email, name, password;
	private Date regDate;
	private String role;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, double depositeAmount, String email, String name, String password, Date regDate,
			String role) {
		super();
		this.customerId = customerId;
		this.depositeAmount = depositeAmount;
		this.email = email;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
		this.role = role;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getDepositeAmount() {
		return depositeAmount;
	}

	public void setDepositeAmount(double depositeAmount) {
		this.depositeAmount = depositeAmount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", depositeAmount=" + depositeAmount + ", email=" + email
				+ ", name=" + name + ", regDate=" + regDate + ", role=" + role + "]";
	}
	
	
}
