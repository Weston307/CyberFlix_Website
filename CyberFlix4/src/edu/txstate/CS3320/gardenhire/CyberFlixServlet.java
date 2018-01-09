package edu.txstate.CS3320.gardenhire;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import edu.txstate.CS3320.gardenhire.data.*;
import edu.txstate.CS3320.gardenhire.*;
import edu.txstate.CS3320.gardenhire.data.helper.*;
import edu.txstate.CS3320.gardenhire.data.film.*;
import edu.txstate.CS3320.gardenhire.data.film.Film.FilmRating;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;
import edu.txstate.CS3320.gardenhire.data.utils.HTMLTags;
import edu.txstate.CS3320.gardenhire.data.utils.ServletUtils;



/**
 * Servlet implementation class CyberFlixServlet
 */
@WebServlet("/CyberFlixServlet")
public class CyberFlixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String MOVIE_DETAIL_SERVLET = "CyberFlixDetailServlet";
	//public static List <Film>foundFilms;
	public static ArrayList <String> posterLinks = new ArrayList<String>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixServlet() throws ServletException{
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	ServletConfig config = getServletConfig();
    ServletUtils.setAbsolutePath(config);
    	DataSource.init();
    //	DatabaseDriver.test();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		//Put outside if statements to avoid repetition
		//The extra links are added to take care of the task bar
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
		
		request.setAttribute("staffLogin", CyberFlixLoginHandleServlet.staffLoginCheck);
		request.setAttribute("staffView",
				   "http://localhost:8080/CyberFlix4/CyberFlixStaffCustomerRentalViewServlet");
		
		request.setAttribute("numMovies", CyberFlixCart.numMovies);
		
		if (request.getParameter("attribute")!= null)
		{
			String searchTitle = request.getParameter("film_title");
			String searchDescription = request.getParameter("film_description");
			//**********************************************************************
			//Kevin this line right here was returning null and causing a 505 error 
			//when the user didn't apply input.
			int	searchRunTime = Integer.parseInt(request.getParameter("run_time"));
			//**********************************************************************
			FilmRating searchRating = FilmFactory.convert(request.getParameter("film_rating")); 
			
			//Calls the method in DataSource that handles the attribute portion of film testing
			CyberFlixSplashServlet.foundFilms = DataSource.filmByAttributes(searchTitle, searchDescription,
					searchRunTime, searchRating);

		
			// pass the path of the detail servlet that will be encoded in the hyperlink for
			// associated with the films title
			
			// pass the list of films that matched the search query
			request.setAttribute("films", CyberFlixSplashServlet.foundFilms);
			request.setAttribute("posters", posterLinks);

			// forward this request to the following jsp page
			request.getRequestDispatcher("moviesearchresults.jsp").
		    	forward(request,  response);

			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.append(HTMLTags.HTML_HEADER_START);
			for (int i = 0;i < foundFilms.size();i++) {
				out.append(HTMLTags.BODY_START);
				out.append(HTMLTags.createHTMLLinkToFile(MOVIE_DETAIL_SERVLET + "?param=" + i, foundFilms.get(i).getTitle()));
				out.append("<i>").append("(").append(foundFilms.get(i).getReleaseYear()).append(")").append("</i>");
				out.append(" ").append(foundFilms.get(i).getDescription()).append("<br><br>" + HTMLTags.BODY_END);
			}
		
		}
		else System.out.println("No input String");*/
		}
		//else if (request.getParameter("film_category") != "") 
		else if(request.getParameter("category")!=null)
		{
			String searchCategory = request.getParameter("film_category");
			
			//FilmDAO object = new FilmDAO();
			FilmCategory category= FilmCategory.valueOf(searchCategory);
			
			CyberFlixSplashServlet.foundFilms = DataSource.filmByCategory(category);
			
			//foundfilms = object.findFilmsByCategory(category);
			// pass the list of films that matched the search query
			
			request.setAttribute("films", CyberFlixSplashServlet.foundFilms);
			request.setAttribute("posters", posterLinks);

			// forward this request to the following jsp page
			request.getRequestDispatcher("moviesearchresults.jsp").
			    forward(request,  response);
		}
		
		else if(request.getParameter("alphabetically")!=null)
		{
			String searchLetter = request.getParameter("letter");
			
			//FilmDAO object = new FilmDAO();
			
			CyberFlixSplashServlet.foundFilms = DataSource.filmByAlphabet(searchLetter);
			
			//foundfilms = object.findFilmsByCategory(category);
			// pass the list of films that matched the search query
			
			
			request.setAttribute("films", CyberFlixSplashServlet.foundFilms);
			request.setAttribute("posters", posterLinks);

			// forward this request to the following jsp page
			request.getRequestDispatcher("moviesearchresults.jsp").
			    forward(request,  response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}