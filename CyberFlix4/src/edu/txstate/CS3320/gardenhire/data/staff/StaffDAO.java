package edu.txstate.CS3320.gardenhire.data.staff;

import edu.txstate.CS3320.gardenhire.data.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import edu.txstate.CS3320.gardenhire.data.DAO;
//import edu.txstate.CS3320.gardenhire.data.customer.Customer;
//import edu.txstate.CS3320.gardenhire.data.customer.CustomerDAO;

public class StaffDAO extends DAO
{
private final static Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());
	
	private static final int    STAFF_ID_COLUMN          = 1;
	private static final int    STAFF_FIRST_NAME_COLUMN  = 2;
	private static final int    STAFF_LAST_NAME_COLUMN   = 3;
	private static final int    STAFF_EMAIL_COLUMN       = 4;
	private static final int	STAFF_USERNAME_COLUMN	 = 5;
	private static final int    STAFF_PASSWORD_COLUMN    = 6;
	
	public Staff findStaff(String emailAddress, String password)
	{
		Staff staff = null;
		Connection dbConnection = null;
		String queryString = "SELECT staff.staff_id, staff.first_name, staff.last_name, "
				+ "staff.email, staff.username, staff.password FROM staff "
				+ "WHERE staff.email = '" + emailAddress + "'";
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(queryString);
			staff = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			closeQuietly(dbConnection);
			return null;
		}
		//Checks if the user even exists
		if(staff == null)
			return null;
		//checks if password is correct... if not returns null
		if(!(staff.getPassword().equals(password)))
			return null;
		
		return staff;
	}
	
	private Staff buildResults (ResultSet results) {
		Staff staff = null;
		try {
			while (results.next()) {
				int    id         = results.getInt   (STAFF_ID_COLUMN);
				String firstName  = results.getString(STAFF_FIRST_NAME_COLUMN);
				String lastName   = results.getString(STAFF_LAST_NAME_COLUMN );
				String email      = results.getString(STAFF_EMAIL_COLUMN );
				String username   = results.getString(STAFF_USERNAME_COLUMN);
				String password   = results.getString(STAFF_PASSWORD_COLUMN);
				
				staff = new Staff(id, firstName, lastName, email, username, password);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return staff;
	}
	
	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}
}

