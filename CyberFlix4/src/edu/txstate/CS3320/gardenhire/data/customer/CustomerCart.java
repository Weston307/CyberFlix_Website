package edu.txstate.CS3320.gardenhire.data.customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.utils.HTMLTags;
import edu.txstate.CS3320.gardenhire.data.DAO;


public class CustomerCart {
	private ArrayList <Film> films  = new ArrayList <Film> ();
	
	
	public CustomerCart () {
		
	}
	
	public void addFilmToCart(Film film) {
		films.add(film);
	}
	
	public ArrayList <Film> getFilms () {
		ArrayList <Film> films = this.films;
		
		return films;
	}

}