package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {

	private long vId;
	private String username;
	private String psWd;
	
	
	public Verification(ResultSet resultSet) throws SQLException {
		
		this(resultSet.getLong("vId"),
			 resultSet.getString("userName"),
			 resultSet.getString("passWd")
			);
	}
	
	
	// Setter and Getters
	public long getvId() {
		return vId;
	}
	public void setvId(long vId) {
		this.vId = vId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsWd() {
		return psWd;
	}
	public void setPsWd(String psWd) {
		this.psWd = psWd;
	}
	
	// Field constructor
	public Verification(long vId, String username, String psWd) {
		this.vId = vId;
		this.username = username;
		this.psWd = psWd;
	}
	
	@Override
	public String toString() {
		return "Username: " + username + 
				 "\nPassword: " + psWd;
	}
	
}
