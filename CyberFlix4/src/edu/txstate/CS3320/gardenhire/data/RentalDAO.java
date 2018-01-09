package edu.txstate.CS3320.gardenhire.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.CS3320.gardenhire.data.actor.*;
import edu.txstate.CS3320.gardenhire.data.customer.Customer;
import edu.txstate.CS3320.gardenhire.data.customer.RentalRecord;

public class RentalDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(RentalDAO.class.getName());

	private static final int RENTAL_ID_COLUMN   = 1;
	private static final int RENTAL_DATE_COLUMN = 2;
	private static final int FILM_ID_COLUMN     = 3; // this is a spoof
	private static final int CUSTOMER_ID_COLUMN = 4;
	private static final int RETURN_DATE_COLUMN = 5;

	private static String SELECT_RENTAL1 = "SELECT rental_id, rental_date, inventory_id, customer_id, return_date FROM rental ";
	private static String SELECT_RENTAL2 = "WHERE customer_id = ";

	public List<RentalRecord> findRentalByCustomer(Customer customer) {
		StringBuilder stringBuilder = new StringBuilder(SELECT_RENTAL1);
		stringBuilder.append(SELECT_RENTAL2);
		stringBuilder.append(customer.getID());

		String selectString = stringBuilder.toString();
		List<RentalRecord> rentalRecords = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			rentalRecords = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("RentalDAO: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return rentalRecords;
	}
	
	public List<RentalRecord> findCheckedOutRentals() {
		StringBuilder stringBuilder = new StringBuilder(SELECT_RENTAL1);
		stringBuilder.append("WHERE isnull(return_date) ORDER BY customer_id");

		String selectString = stringBuilder.toString();
		LOGGER.info(selectString);
		List<RentalRecord> rentalRecords = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			rentalRecords = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("RentalDAO: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return rentalRecords;
	}
	
	@Override
	public void save(Object anObject) {
		if ((anObject instanceof RentalRecord) == false) return;
		RentalRecord rentalRecord = (RentalRecord)anObject;
		StringBuilder stringBuilder = new StringBuilder("INSERT INTO rental ");
		stringBuilder.append("(rental_id,rental_date,inventory_id,customer_id,return_date,staff_id,last_update)");
		stringBuilder.append(" values (null, now(), ?, ?, null, '1', now())");
		
		String insertString = stringBuilder.toString();
		LOGGER.info(insertString);
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			PreparedStatement insertStatement = dbConnection.prepareStatement(insertString);
			insertStatement.setInt (1,rentalRecord.getFilmID());
			insertStatement.setInt (2,rentalRecord.getCustomerID());
			
			int results = insertStatement.executeUpdate();
			LOGGER.info(String.valueOf(results));
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("RentalDAO.insertRentalRecord: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}

	}

	private List<RentalRecord> buildResults (ResultSet results) {
		List <RentalRecord> records = new ArrayList <RentalRecord> ();
		try {
			while (results.next()) {
				int rentalId = results.getInt(RENTAL_ID_COLUMN);
				Date rentalDate = results.getDate(RENTAL_DATE_COLUMN);
				int filmId      = spoofFilmID(results.getInt(FILM_ID_COLUMN));
				int customerId  = results.getInt(CUSTOMER_ID_COLUMN);
				Date returnDate = results.getDate(RETURN_DATE_COLUMN);
				RentalRecord record = new RentalRecord (rentalId, rentalDate,filmId,customerId,returnDate);
				records.add(record);
			}
			return records;
		} catch (SQLException e) {
			LOGGER.severe (e.toString());
		}
		return null;
	}
	
	/**
	 * The id in rental record is really an inventory id not a film id, but we want 
	 * to simplify the use of the schema and use this id to reference a film. Inventory
	 * ids can exceed the number of films we have in the db, so we "spoof" it to 
	 * be within the range of actual films 
	 * @param id
	 * @return
	 */
	private int spoofFilmID (int id) {
		final int MAXIMUM_FILMS_IN_DB = 1000;
		return id % MAXIMUM_FILMS_IN_DB;
	}
}