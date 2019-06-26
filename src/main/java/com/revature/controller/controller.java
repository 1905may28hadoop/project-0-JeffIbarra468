package com.revature.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.Verification;
import com.revature.repository.accountDAO;
import com.revature.repository.accountDAOImplOJDBC;
import com.revature.repository.baseDAO;
import com.revature.repository.baseDAOImplOJDBC;
import com.revature.repository.customerDAO;
import com.revature.repository.customerDAOImplOJDBC;
import com.revature.repository.verificationDAO;
import com.revature.repository.verificationDAOImplOJDBC;

public class controller {

	public static void login () throws IOException {
		
	// tests connect with database	
	//	ConnectionUtil.getConnection();
		
	Scanner scan = new Scanner(System.in);
	
	 System.out.print("Please enter your username:");
	 String userName = scan.nextLine();
	 System.out.print("Please enter your Password:");
	 String password = scan.nextLine();
	 
	 try {
		 // Username and Password is verified
		 long verif = verification(userName, password);
	 
		 if(verif != -1) {
			 transactions(verification(userName, password));
		 } else { // Username and Password is incorrect
			 System.out.println("Invalid Username & Password");
		 }
	 	} catch (NullPointerException e) {
	 		System.out.println("Invalid Username & Password, goodbye.");
	 	}
	 }
//	 	
	
	
	// CALLS TO SERVICE FOR DATABASE ACCESS
	// Verifies if username & password is correct
	public static long verification(String userName, String password) {
		
		// Gets username and password from database
		verificationDAO  verificationDAO = new verificationDAOImplOJDBC();
		String verifyUN = verificationDAO.getVerification(userName, password).getUsername();
		String verifyPW = verificationDAO.getVerification(userName, password).getPsWd();
		long baseId = verificationDAO.getVerification(userName, password).getvId();

		// checks username & password 
		if(userName.equals(verifyUN)  && password.equals(verifyPW))
			return baseId;
		else // username & password not located in database
			return -1;
	}
	
	public static long transactions(long baseId) {
		
		// Gets user's account number
		baseDAO  baseDAO = new baseDAOImplOJDBC();
		long acctNum = baseDAO.getBase(baseId).getAcctNum();
		
		Scanner scan = new Scanner(System.in);		

		try {
			System.out.println("What would you like to do today?"
					+ "\n1) View My Balance \n2) Withdraw"
					+ "\n3) Deposit \n4) Logout");

			int choice = scan.nextInt();

			// Checks user choice
			if (choice > 0 && choice < 5) {

				switch (choice) {
				case 1:
					viewMyBalance(baseId, acctNum);
					break;
				case 2:
					withdraw(baseId, acctNum);
					break;
				case 3:
					deposit(baseId, acctNum);
					break;
				case 4:
					scan = null; // userInput is empty before program exits
					System.out.println("Thank You for using JI Lupercio and Pursued Bank");
					return 0;
				default:
					break;

				}
			} else {
				System.out.println("Invalid Choice, try again");
				transactions(baseId);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Choice, try again");
			transactions(baseId);
		}
		return 0;
	}
	
	public static int transactionloop(long baseId, long acNum) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Is there anything else you would like to do? \nYes \nNo");
		String choice = scan.nextLine().toLowerCase();
	
//		try {
			if (choice.equals("yes") || choice.equals("y"))
				transactions(baseId);
			else if (choice.equals("no") || choice.equals("n")) {
				System.out.println("Thank You for using JI Lupercio and Pursued Bank");
				return 0;
			} else {
				System.out.println("Invalid Choice, Try Again");
				transactionloop(baseId, acNum);
			}
//		} catch (Exception e) {
//			System.out.println("Invalid Choice, Try Again");
//			transactionloop();
//		}
	
		return 0;
	}
	
	
	
	/******* HAVE METHOD THAT CALLS SERIVE AND GETS *********************/
	
	// Makes calls to the database to display the balance of user
	public static void viewMyBalance(long baseId, long acNum) {
//		long acNum = 101;
		
		accountDAO accountDAO = new accountDAOImplOJDBC();
		System.out.println("Balance");
		System.out.println(accountDAO.getAccount(acNum));
		
		// Gives the user to choose again
		transactionloop(baseId, acNum);
	}
	
	// SERVICE
	// Calls the database updating Balance column
	public static void withdraw(long baseId, long acNum) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Withdraw");
//		long acNum = 101;

		// GET BALANCE OF ACCOUNT
		accountDAO accountDAO = new accountDAOImplOJDBC();
		double balance = accountDAO.getAccount(acNum).getBalance();
		System.out.println("Balance: " + balance);
		
		System.out.println("How much would you like to withdraw: ");
		double withdraw = scan.nextDouble();
		
		// Checks for negative
		if (withdraw < 0) {
			System.out.println("Invalid Input, try again");
			withdraw(baseId, acNum);
		}else if (withdraw > balance) {
			System.out.println
				("You have insufficient funds to complete this transaction, try again");
			withdraw(baseId, acNum);
		} else {

			balance -= withdraw;

			// UPDATE ACCOUNT
			Account updateBal = new Account(acNum, balance);
			accountDAO.updateAccount(updateBal);

			// GET NEW BALANCE
			System.out.println("New Balance: " + balance);

			// Gives the user to choose again
			transactionloop(baseId, acNum);
		}
	}
	
	// SERVICE
	// Calls the database updating Balance column
	public static void deposit(long baseId, long acNum) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Deposit");
//		long acNum = 101;

		// GET BALANCE OF ACCOUNT
		accountDAO accountDAO = new accountDAOImplOJDBC();
		double balance = accountDAO.getAccount(acNum).getBalance();
		System.out.println("Balance: " + balance);
		
		System.out.println("How much would you like to deposit: ");
		double deposit = scan.nextDouble();
		
		// Checks for negative
		if (deposit < 0) {
			System.out.println("Invalid Input, try again");
			deposit(baseId, acNum);
		}
		
		balance += deposit;
		
		// UPDATE ACCOUNT
		Account updateBal = new Account(acNum, balance);
		accountDAO.updateAccount(updateBal);
		
		// GET NEW BALANCE
		System.out.println("New Balance: " + balance);
		
		// Gives the user to choose again 
		transactionloop(baseId, acNum);
	}
	
}
