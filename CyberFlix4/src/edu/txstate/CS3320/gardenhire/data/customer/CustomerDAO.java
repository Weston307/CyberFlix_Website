package edu.txstate.CS3320.gardenhire.data.customer;

import edu.txstate.CS3320.gardenhire.data.DAO;
import edu.txstate.CS3320.gardenhire.data.customer.*;
import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CustomerDAO extends DAO{
	
	private final static Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	
	private static final int    CUSTOMER_ID_COLUMN          = 1;
	private static final int    CUSTOMER_FIRST_NAME_COLUMN  = 2;
	private static final int    CUSTOMER_LAST_NAME_COLUMN   = 3;
	private static final int    CUSTOMER_EMAIL_COLUMN       = 4;
	private static final int    CUSTOMER_PASSWORD_COLUMN    = 5;
	
	public Customer findCustomer(String emailAddress, String password)
	{
		Customer customer = null;
		Connection dbConnection = null;
		String queryString = "SELECT customer.customer_id, customer.first_name, customer.last_name, "
				+ "customer.email, customer.password FROM customer "
				+ "WHERE customer.email = '" + emailAddress + "'";
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(queryString);
			customer = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			closeQuietly(dbConnection);
			return null;
		}
		//Checks if the user even exists
		if(customer == null)
			return null;
		//checks if password is correct... if not returns null
		if(!(customer.getPassword().equals(password)))
			return null;
		
		return customer;
	}
	
	public Customer findCustomerByID(int customerID)
	{
		Customer customer = null;
		Connection dbConnection = null;
		String queryString = "SELECT customer.customer_id, customer.first_name, customer.last_name, "
				+ "customer.email, customer.password FROM customer "
				+ "WHERE customer.customer_id = '" + customerID + "'";
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(queryString);
			customer = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			closeQuietly(dbConnection);
			return null;
		}
		//Checks if the user even exists
		if(customer == null)
			return null;
		
		return customer;
	}
	
	@Override
	public void save (Object object) {
		if ((object instanceof Customer) == false)
			return;
		Customer customer = (Customer)object;
		String insertString1 = 
		"INSERT INTO CUSTOMER (customer_id,store_id, first_name,last_name, email, address_id, " +
		                       "password, active,create_date,last_update) ";
		String insertString2 = " VALUES (null, 1, ?, ?, ?, ?, ?, 1, now(), null)";
		
		StringBuilder stringBuilder = new StringBuilder(insertString1).append(insertString2);
		String customerInsertString = stringBuilder.toString();
		LOGGER.info(customerInsertString);
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			PreparedStatement insertStatement = dbConnection.prepareStatement(customerInsertString);
			insertStatement.setString (CUSTOMER_ID_COLUMN,customer.getFirstName());
			insertStatement.setString (CUSTOMER_FIRST_NAME_COLUMN, customer.getLastName());
			insertStatement.setString (CUSTOMER_LAST_NAME_COLUMN,customer.getEmail());
			insertStatement.setInt    (CUSTOMER_EMAIL_COLUMN, randomAddress());
			insertStatement.setString (CUSTOMER_PASSWORD_COLUMN,customer.getPassword());	
			int results = insertStatement.executeUpdate();
			LOGGER.info(String.valueOf(results));
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("CustomerDAO.insertCustomer: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		
	}
	
	private Customer buildResults (ResultSet results) {
		Customer customer = null;
		try {
			while (results.next()) {
				int    id         = results.getInt   (CUSTOMER_ID_COLUMN);
				String firstName  = results.getString(CUSTOMER_FIRST_NAME_COLUMN);
				String lastName   = results.getString(CUSTOMER_LAST_NAME_COLUMN );
				String email      = results.getString(CUSTOMER_EMAIL_COLUMN );
				String password   = results.getString(CUSTOMER_PASSWORD_COLUMN);
				
				customer = new Customer(id, firstName, lastName, email, password);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return customer;
	}
	
	private int randomAddress() {
		// There are 603 addresses in the Sakila database, pick one
		// to assign to this customer
		int randomAddressId = (int) (Math.random() * 603);
		return randomAddressId;
	}
}
