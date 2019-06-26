package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Customer;
import com.revature.utli.CloseStreams;
import com.revature.utli.ConnectionUtil;

public class customerDAOImplOJDBC implements customerDAO {

	
/* SQL COMMAND
 * 
 * CREATE TABLE Customer (
 * 		cId NUMBER(6) PRIMARY KEY,
 * 		firstName VARCHAR(200),
 * 		lastName VARCHAR(200)
 * );
 */
	
	@Override
	public Customer getCustomer(long cId) {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		
		//  Seperate SQL command from code - prevent SQL inject 
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			statement = conn.prepareStatement("SELECT * FROM Customer WHERE cID = ?");
			statement.setLong(1, cId);
//			statement.setString(2, fName);
//			statement.setString(3, lName);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			customer = new Customer(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return customer;
	}
	
	@Override
	public boolean createCustomer(Customer customer) {
		
		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("INSERT INTO Customer VALUES(?,?,?)");
			// Question mark indices:									   1 2 3
			// We modify indices in Java:
			statement.setLong(1, customer.getId());
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(statement);
		}
		
		return true;
	}
	
	@Override
	public boolean updateCustomer(Customer customer) {
		
		final String SQL_UPDATE = "UPDATE Customer SET firstName =?, lastName =? WHERE cId =?";
		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(SQL_UPDATE);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setLong(3, customer.getId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(statement);
		}
		
		return true;
	}
	
	
	@Override
	public List<Customer> getCustomer() {

		Statement statement = null;
		ResultSet resultSet = null;
		List<Customer> customer = new ArrayList<>();
		
		// we're going to do try with resources
		// lets us open resource, like db connex
		// part of starting our block and close
		// automatically when try block finishes
		try(Connection conn = ConnectionUtil.getConnection()) {
			// create our statement
			statement = conn.createStatement();	
			// Statement objects can execute SQL quiries
			// most basic form:
			resultSet = statement.executeQuery("SELECT * FROM Customer");
			
			// resultSet stores all results from successful query
			// Create ourselves a new object for each row:
			while(resultSet.next()) {
				customer.add(new Customer(resultSet));
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return customer;
	}
	
}
