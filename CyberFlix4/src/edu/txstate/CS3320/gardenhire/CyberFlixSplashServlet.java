
package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.txstate.CS3320.gardenhire.*;
import edu.txstate.CS3320.gardenhire.data.DataSource;
import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;

import edu.txstate.CS3320.gardenhire.data.RentalDAO;
import edu.txstate.CS3320.gardenhire.data.customer.RentalRecord;

/**
 * Servlet implementation class CyberFlixSplashServlet
 */
@WebServlet("/CyberFlixSplashServlet")
public class CyberFlixSplashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList <String> posterLinks = new ArrayList<String>();
	public static List <Film>foundFilms;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixSplashServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init ( ) {
     		posterLinks.add("https://www.movieposter.com/posters/archive/main/226/MPW-113357");
     	posterLinks.add("https://www.movieposter.com/posters/archive/main/141/MPW-70980");
        	posterLinks.add("https://www.movieposter.com/posters/archive/main/63/MPW-31958");
      	posterLinks.add("https://www.movieposter.com/posters/archive/main/81/MPW-40696");
        	posterLinks.add("https://designfix.files.wordpress.com/2008/10/ironman.jpg");
        posterLinks.add("https://www.movieposter.com/posters/archive/main/95/MPW-47975");
        	posterLinks.add("https://www.movieposter.com/posters/archive/main/97/MPW-48795");
        posterLinks.add("https://www.movieposter.com/posters/archive/main/235/MPW-117979");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FilmDAO object = new FilmDAO();
		//foundFilms = object.findNewestFilms(3);
		foundFilms = object.findNewestFilms(8);
		request.setAttribute("detailServlet",   
				   "http://localhost:8080/CyberFlix4/CyberFlixDetailServlet");
		request.setAttribute("home",   
				   "http://localhost:8080/CyberFlix4/CyberFlixSplashServlet");
		//added this to be able to create a proper link to the search page
		//Look at the CyberFlixSearchServlet
		request.setAttribute("search",   
				   "http://localhost:8080/CyberFlix4/CyberFlixSearchServlet");
		request.setAttribute("login",   
				   "http://localhost:8080/CyberFlix4/CyberFlixLoginServlet");
		request.setAttribute("cart",   
				   "http://localhost:8080/CyberFlix4/CyberFlixViewCartServlet");
		
		request.setAttribute("cartNotEmpty", CyberFlixCart.cartExists);
		
		request.setAttribute("checkOut",   
				   "http://localhost:8080/CyberFlix4/CyberFlixCheckOutServlet");
		
		request.setAttribute("numMovies", CyberFlixCart.numMovies);
		
		request.setAttribute("staffLogin", CyberFlixLoginHandleServlet.staffLoginCheck);
		request.setAttribute("staffView",
				   "http://localhost:8080/CyberFlix4/CyberFlixStaffCustomerRentalViewServlet");

		// pass the list of films that matched the search query
		request.setAttribute("films", foundFilms);
		request.setAttribute("posters", posterLinks);
		request.getRequestDispatcher("index.jsp").forward(request,  response);
		HttpSession session=request.getSession(); 
		
		session.setAttribute("session", session);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);	
	}
}