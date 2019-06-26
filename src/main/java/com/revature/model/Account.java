package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

	private long acctNum; 
	private double balance;
	
	
	public Account(ResultSet resultSet) throws SQLException {
		
		this(resultSet.getLong("acctNum"),
			 resultSet.getDouble("Balance")
			);
	}
	
	
	// Setter and Getters of Account
	public long getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(long acctNum) {
		this.acctNum = acctNum;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	// Field constructor
	public Account(long acctNum, double balance) {
		this.acctNum = acctNum;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account #: " + acctNum + 
				 "\nBalance: " + balance;
	}
	
}
