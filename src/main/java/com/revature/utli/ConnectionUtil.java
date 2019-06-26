package com.revature.utli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Connection conn = null;
	
	// this static block will run before anything -- when class
	// is loaded, not strictly necessary, but it will tell us if
	// we're missing our driver

	static {
		try {
			// this line checks for Class of our Driver and loads it
			// not necessary, but nice
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {

		try {
			// in here we need our url, username, and password for our DB
			// but we don't hardcore. We'll use Properties!
			Properties properties = new Properties();

			// we want to load properties from file. But path might change
			// depending on how we build our project.
			// instead of hardcoding, we'll look at classpath. It involves
			// method calls but will find out properties file
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			properties.load(loader.getResourceAsStream("connect.properties"));

			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			// now we actually make connection
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Please Wait....");
			
		} catch (IOException | SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
