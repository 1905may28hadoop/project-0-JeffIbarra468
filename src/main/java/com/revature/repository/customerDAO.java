package com.revature.repository;

import java.util.List;

import com.revature.model.Customer;

public interface customerDAO {

//	Customer getCustomer(long id);
	
	// Get customer table
	Customer getCustomer(long id);
	
	// create Customer 
	boolean createCustomer(Customer customer);
	
	// update current Customer information
	boolean updateCustomer(Customer customer);
	
	List<Customer> getCustomer();
}
