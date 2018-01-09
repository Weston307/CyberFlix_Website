package edu.txstate.CS3320.gardenhire.data.customer;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class RentalRecord {
	private int rentalID;
	//private Date rentalDate = new Date();
	private Date rentalDate;
	private int filmID;
	private int customerID;
	//private Date returnDate = addDays(rentalDate,1);
	private Date returnDate;
	
	
	
	public RentalRecord (int rentalID, Date rentalDate, int filmID, int customerID, Date returnDate) {
		this.rentalID = rentalID;
		this.rentalDate = rentalDate;
		this.filmID = filmID;
		this.customerID = customerID;
		this.returnDate = returnDate;
	}
	
	public Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);		
		return cal.getTime();
	}
	
	public int getFilmID () {
		return this.filmID;
	}
	public int getCustomerID() {
		return this.customerID;
	}
	
	@Override
	public String toString() {
		return "Rental [rentalID =" + rentalID + ", filmID =" + filmID + ", customerID =" + customerID  + "]";
	}
}
