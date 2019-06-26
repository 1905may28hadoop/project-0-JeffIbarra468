package com.revature.repository;

import java.util.List;

import com.revature.model.Account;

public interface accountDAO {

	// Receive current Account information 
	Account getAccount(long id);
	
	// create Account 
	boolean createAccount(Account account);
		
	// update Account information
	boolean updateAccount(Account account);
		
	List<Account> getAccount();
		
}
