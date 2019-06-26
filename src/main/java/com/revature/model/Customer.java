package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

	private long id;
	private String firstName;
	private String lastName;
	
	
	public Customer(ResultSet resultSet) throws SQLException {
		
		this(resultSet.getLong("cId"),
				resultSet.getString("firstName"),
				resultSet.getString("lastName")
				);
	}
	
	
	// Getters and Setters for all private members
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	// Constructor
	public Customer(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	@Override
	public String toString() {
		return "Customer ID = " + id + "\nFirst Name: " 
				+ firstName + "\nLast Name: " + lastName;
	}
	

}
