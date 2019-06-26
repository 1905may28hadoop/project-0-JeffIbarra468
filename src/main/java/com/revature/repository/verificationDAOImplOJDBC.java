package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Verification;
import com.revature.utli.CloseStreams;
import com.revature.utli.ConnectionUtil;

public class verificationDAOImplOJDBC implements verificationDAO {

	
		@Override
		// Verify that verification is present
		public Verification getVerification(String username, String password) {
			
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			Verification verification = null;
			
			//  Seperate SQL command from code - prevent SQL inject 
			try(Connection conn = ConnectionUtil.getConnection()) {
				
				statement = conn.prepareStatement("SELECT * FROM Verification WHERE userName = ? AND passWd = ?");
				statement.setString(1, username);
				statement.setString(2, password);
				statement.execute();
				resultSet = statement.getResultSet();
				resultSet.next();
				verification = new Verification(resultSet);
				
			} catch (SQLException e) {
//				System.out.println("Invalid Username & Password, Goodbye");
				e.printStackTrace();
			} finally {
				CloseStreams.close(statement);
				CloseStreams.close(resultSet);
			}
			
			return verification;
		}
		
		@Override
		// create username and password
		public boolean createVerification(Verification verification) {
			
			PreparedStatement statement = null;
			
			try(Connection conn = ConnectionUtil.getConnection()) {
				statement = conn.prepareStatement("INSERT INTO Verification VALUES(?,?)");
				// Question mark indices:									   	   1 2
				// We modify indices in Java:
				statement.setString(1, verification.getUsername());
				statement.setString(2, verification.getPsWd());
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
		public boolean updateVerification(Verification verification) {
			
			final String SQL_UPDATE = "UPDATE Verification SET username =? and passwd =? WHERE vId =?";
			PreparedStatement statement = null;
			
			try(Connection conn = ConnectionUtil.getConnection()) {
				statement = conn.prepareStatement(SQL_UPDATE);
				statement.setString(1, verification.getUsername());
				statement.setString(2, verification.getPsWd());
				statement.setLong(3, verification.getvId());
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
		public List<Verification> getVerification() {
			
			Statement statement = null;
			ResultSet resultSet = null;
			List<Verification> verification = new ArrayList<>();
			
			// we're going to do try with resources
			// lets us open resource, like db connex
			// part of starting our block and close
			// automatically when try block finishes
			try(Connection conn = ConnectionUtil.getConnection()) {
				// create our statement
				statement = conn.createStatement();	
				// Statement objects can execute SQL quiries
				// most basic form:
				resultSet = statement.executeQuery("SELECT * FROM Verification");
				
				// resultSet stores all results from successful query
				// Create ourselves a new object for each row:
				while(resultSet.next()) {
					verification.add(new Verification(resultSet));
				}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			} finally {
				CloseStreams.close(statement);
				CloseStreams.close(resultSet);
			}
			
			return verification;

		}
}
