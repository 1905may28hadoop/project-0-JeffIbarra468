package com.revature.repository;

import java.util.List;

import com.revature.model.Base;

public interface baseDAO {

	// Receive current Account information 
	Base getBase(long id);
		
	// create Account 
	boolean createBase(Base base);
			
	// update Account information
	boolean updateBase(Base base);
			
	List<Base> getBase();
}
