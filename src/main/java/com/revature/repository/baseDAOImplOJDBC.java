package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Base;
import com.revature.utli.CloseStreams;
import com.revature.utli.ConnectionUtil;

public class baseDAOImplOJDBC implements baseDAO {

	
	@Override
	// Receive current Account information 
	public Base getBase(long id) {
			
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Base base = null;
		
		//  Seperate SQL command from code - prevent SQL inject 
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			statement = conn.prepareStatement("SELECT * FROM Base WHERE id = ?");
			statement.setLong(1, id);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			base = new Base(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return base;
	}
	
	@Override
	// create Account 
	public boolean createBase(Base base) {

		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("INSERT INTO Base VALUES(?,?,?,?)");
			// Question mark indices:								   1 2 3 4
			// We modify indices in Java:
			statement.setLong(1, base.getId());
			statement.setLong(2, base.getcId());
			statement.setLong(3, base.getvId());
			statement.setLong(4, base.getAcctNum());
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
	public boolean updateBase(Base base) {
			
		final String SQL_UPDATE = "UPDATE Base SET cId = ? vId = ? acctNum = ? WHERE id =?";
		PreparedStatement statement = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(SQL_UPDATE);
			statement.setLong(1, base.getcId());
			statement.setLong(2, base.getvId());
			statement.setLong(3, base.getAcctNum());
			statement.setLong(4, base.getId());
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
	public List<Base> getBase() {
			
		Statement statement = null;
		ResultSet resultSet = null;
		List<Base> base = new ArrayList<>();
		
		// we're going to do try with resources
		// lets us open resource, like db connex
		// part of starting our block and close
		// automatically when try block finishes
		try(Connection conn = ConnectionUtil.getConnection()) {
			// create our statement
			statement = conn.createStatement();	
			// Statement objects can execute SQL quiries
			// most basic form:
			resultSet = statement.executeQuery("SELECT * FROM Base");
			
			// resultSet stores all results from successful query
			// Create ourselves a new object for each row:
			while(resultSet.next()) {
				base.add(new Base(resultSet));
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
		return base;

	}
}
