package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Base {

	private long id;
	private long cId;
	private long vId;
	private long acctNum;

	public Base(ResultSet resultSet) throws SQLException {
		
		this(resultSet.getLong("id"),
				resultSet.getLong("cId"),
				resultSet.getLong("vId"),
				resultSet.getLong("acctNum")
				);
	}
	
	
	// Getters and Setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	public long getvId() {
		return vId;
	}
	public void setvId(long vId) {
		this.vId = vId;
	}
	public long getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(long acctNum) {
		this.acctNum = acctNum;
	}	
	
	
	// Field Constructor
	public Base(long id, long cId, long vId, long acctNum) {
		this.id = id;
		this.cId = cId;
		this.vId = vId;
		this.acctNum = acctNum;
	}


	@Override
	public String toString() {
		
		return "Base ID: " + id + "\nCustomer ID: "
		+ cId + "\nVerifyID: " + vId + "\nAccount#: "
		+ acctNum;
	}

}
