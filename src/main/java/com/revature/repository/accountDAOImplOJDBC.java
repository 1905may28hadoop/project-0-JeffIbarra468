package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.utli.CloseStreams;
import com.revature.utli.ConnectionUtil;

public class accountDAOImplOJDBC implements accountDAO {

	
	@Override
	public Account getAccount(long acctNum) {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Account account = null;
		
		//  Seperate SQL command from code - prevent SQL inject 
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			statement = conn.prepareStatement("SELECT * FROM Account WHERE acctNum = ?");
			statement.setLong(1, acctNum);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			account = new Account(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return account;
	}
	
	@Override
	// create Account 
	public boolean createAccount(Account account) {
		
		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("INSERT INTO Customer VALUES(?,?)");
			// Question mark indices:									   1 2
			// We modify indices in Java:
			statement.setLong(1, account.getAcctNum());
			statement.setDouble(2, account.getBalance());
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
	// update Account information
	public boolean updateAccount(Account account) {
		
		final String SQL_UPDATE = "UPDATE Account SET Balance =? WHERE acctNum =?";
		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(SQL_UPDATE);
			statement.setDouble(1, account.getBalance());
			statement.setLong(2, account.getAcctNum());
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
	public List<Account> getAccount() {
		
		Statement statement = null;
		ResultSet resultSet = null;
		List<Account> account = new ArrayList<>();
		
		// we're going to do try with resources
		// lets us open resource, like db connex
		// part of starting our block and close
		// automatically when try block finishes
		try(Connection conn = ConnectionUtil.getConnection()) {
			// create our statement
			statement = conn.createStatement();	
			// Statement objects can execute SQL quiries
			// most basic form:
			resultSet = statement.executeQuery("SELECT * FROM Account");
			
			// resultSet stores all results from successful query
			// Create ourselves a new object for each row:
			while(resultSet.next()) {
				account.add(new Account(resultSet));
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return account;
	}
	
	
}
