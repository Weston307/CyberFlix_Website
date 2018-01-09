package edu.txstate.CS3320.gardenhire.data.actor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import edu.txstate.CS3320.gardenhire.data.DAO;
import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;

public class ActorDao extends DAO {
	
	private final static Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());
	
	public List <Actor> findActorsInFilm (Film film) {
		ArrayList <Actor> actors  = new ArrayList <Actor> ();
		int filmID = film.getFilmID();
		Connection dbConnection = null;
		String queryString = "select actor_id, first_name, last_name from actor where actor_id in (select actor_id from film_actor where film_id = " + filmID + ")";
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results  = statement.executeQuery(queryString);
			//actors = buildResults (results);
			while (results.next()) {
			int actorID = results.getInt(1);
			String firstName = results.getString(2);
			String lastName = results.getString(3);
			Actor actor = new Actor(actorID,firstName,lastName);
			actors.add(actor);
			}
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findActorsInFilm: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		
		return actors;
	}
	
	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}
	

}
