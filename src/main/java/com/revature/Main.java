package com.revature;

import java.io.IOException;

import com.revature.controller.controller;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.repository.accountDAO;
import com.revature.repository.accountDAOImplOJDBC;
import com.revature.repository.customerDAO;
import com.revature.repository.customerDAOImplOJDBC;
import com.revature.utli.ConnectionUtil;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main extends controller {

	public static void main(String[] args) {
		
		try { 
			//ConnectionUtil.getConnection();
			login();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
