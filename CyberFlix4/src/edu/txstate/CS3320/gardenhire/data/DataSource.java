package edu.txstate.CS3320.gardenhire.data;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import edu.txstate.CS3320.gardenhire.data.actor.Actor;
import edu.txstate.CS3320.gardenhire.data.actor.ActorInventory;
import edu.txstate.CS3320.gardenhire.data.customer.Customer;
import edu.txstate.CS3320.gardenhire.data.customer.CustomerDAO;
import edu.txstate.CS3320.gardenhire.data.staff.Staff;
import edu.txstate.CS3320.gardenhire.data.staff.StaffDAO;
import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.film.FilmCatalog;
import edu.txstate.CS3320.gardenhire.data.film.FilmCategory;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;
import edu.txstate.CS3320.gardenhire.data.film.Film.FilmRating;
import edu.txstate.CS3320.gardenhire.data.helper.ActorReader;
import edu.txstate.CS3320.gardenhire.data.helper.FilmActorBuilder;
import edu.txstate.CS3320.gardenhire.data.helper.FilmActorReader;
import edu.txstate.CS3320.gardenhire.data.helper.FilmReader;
import edu.txstate.CS3320.gardenhire.data.strategy.SelectorStrategy;
import edu.txstate.CS3320.gardenhire.data.strategy.StrategyFindFilmByDescription;
import edu.txstate.CS3320.gardenhire.data.strategy.StrategyFindFilmByLength;
import edu.txstate.CS3320.gardenhire.data.strategy.StrategyFindFilmByTitle;
import edu.txstate.CS3320.gardenhire.data.utils.ServletUtils;

public class DataSource {

	public static List <Film> films;
	//added this here to acces object elsewhere in DataSource
	public static FilmDAO object;
	public static void init () {
		object = new FilmDAO();
		List <Film> films = null;
		films   = object.buildFilmList();
		
		
        FilmCatalog filmInventory = FilmCatalog.getInstance();
        filmInventory.addAll (films);
	}
	
	public static Customer customerLoginVerify(String username, String password)
	{
		CustomerDAO cus = new CustomerDAO();
		Customer customer = cus.findCustomer(username, password);
		return customer;
	}
	
	public static Staff staffLoginVerify(String username, String password)
	{
		StaffDAO staff = new StaffDAO();
		Staff staffMember = staff.findStaff(username, password);
		return staffMember;
	}
	
	public static List <Film>filmByAttributes(String title, String description, int length, FilmRating rating)
	{
		List <Film>attributeFilms = object.findFilmsByAttributes(title, description, length, rating);
		return attributeFilms;
	}
	
	public static List <Film>filmByCategory(FilmCategory category)
	{
		List <Film>categoryFilms = object.findFilmsByCategory(category);
		return categoryFilms;
	}
	
	public static List <Film>filmByAlphabet(String firstCharacter)
	{
		List <Film>alphabetFilms = object.findFilmsAlphabetically(firstCharacter);
		return alphabetFilms;
	}
	
	public static List <Film>findFilmByTitle (String title) {
	    //
		SelectorStrategy strategy = new StrategyFindFilmByTitle(title);
		List <Film>foundFilms = findFilmByStrategy(strategy);
		return foundFilms;
	}

	private static List <Film> findFilmByStrategy (SelectorStrategy strategy) {
		List <Film>foundFilms = FilmCatalog.getInstance().findFilmByStrategy(strategy);
		return foundFilms;
	}
}
