package com.revature.repository;

import java.util.List;

import com.revature.model.Verification;

public interface verificationDAO {

	// Verify that verification is present
	Verification getVerification(String username, String password);
	
	// create username and password
	boolean createVerification(Verification verification);
	
	boolean updateVerification(Verification verification);
	
	List<Verification> getVerification();
}
