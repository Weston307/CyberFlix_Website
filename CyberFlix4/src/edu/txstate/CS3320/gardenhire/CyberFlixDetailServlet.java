package edu.txstate.CS3320.gardenhire;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.utils.HTMLTags;
import edu.txstate.CS3320.gardenhire.data.utils.ServletUtils;
import edu.txstate.CS3320.gardenhire.data.actor.Actor;
import edu.txstate.CS3320.gardenhire.data.actor.ActorDao;


/**
 * Servlet implementation class CyberFlixDetailServlet
 */
@WebServlet("/CyberFlixDetailServlet")
public class CyberFlixDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		List <Film> films = CyberFlixSplashServlet.foundFilms;
		int filmID = Integer.parseInt(request.getParameter("ID"));
		int posterPictureCounter = filmID;
		filmID = filmID - 1;
		Film film = films.get(filmID);
		ActorDao object = new ActorDao();
		List <Actor> actors = object.findActorsInFilm(film);
		request.setAttribute("actors", actors);
		ArrayList <String> posterLinks = CyberFlixSplashServlet.posterLinks;
		
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
				
		request.setAttribute("posters", posterLinks);
		request.setAttribute("counter", posterPictureCounter);
		
		request.setAttribute("film", film);
		request.setAttribute("actors", actors);
		for(int i = 0; i < 5; i++)
		{
			//System.out.println("Actor: " + actors.get(i).getFirstName());
		}
		request.getRequestDispatcher("movieDetail.jsp").
	    forward(request,  response);
		/*
		PrintWriter out = response.getWriter();
		int filmID = Integer.parseInt(request.getParameter("param"));
		out.append(HTMLTags.HTML_HEADER_START).append(HTMLTags.H4_START);
		out.append(CyberFlixServlet.foundFilms.get(filmID).getTitle());
		out.append("<i> (").append(CyberFlixServlet.foundFilms.get(filmID).getReleaseYear() + ")</i>" + HTMLTags.H4_END);
		out.append(HTMLTags.BODY_START + "<br> Running Time: " + CyberFlixServlet.foundFilms.get(filmID).getLength());
		out.append("<br> Rating: " + CyberFlixServlet.foundFilms.get(filmID).getRating());
		out.append("<br>" + CyberFlixServlet.foundFilms.get(filmID).getDescription());
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
