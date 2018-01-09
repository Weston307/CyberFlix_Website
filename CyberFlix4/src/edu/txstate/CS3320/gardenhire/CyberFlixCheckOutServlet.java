package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.CS3320.gardenhire.data.customer.CustomerCart;
import edu.txstate.CS3320.gardenhire.data.customer.RentalRecord;
import edu.txstate.CS3320.gardenhire.data.RentalDAO;
import edu.txstate.CS3320.gardenhire.data.film.Film;

/**
 * Servlet implementation class CyberFlixCheckOutServlet
 */
@WebServlet("/CyberFlixCheckOutServlet")
public class CyberFlixCheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixCheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();  
		session.getAttribute("cart");
		CustomerCart cart = (CustomerCart) session.getAttribute("cart");
		ArrayList <Film> films = cart.getFilms();
		
		//Required to calculate total of all movies(includes tax)
		double total = CyberFlixCart.numMovies;
		total = total * (1.0825);
		
		
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
		
		request.setAttribute("numMovies", CyberFlixCart.numMovies);
		
		//Holds if the user has logged in or not
		request.setAttribute("loginSuccess", CyberFlixLoginHandleServlet.loginCheck);
		//gets the users login... Not required... Just used it as proof in the checkOut
		String username = (String)request.getAttribute("username");
		
		request.setAttribute("username", username);
		//Used to check if cart has a movie in it
		request.setAttribute("cartNotEmpty", CyberFlixCart.cartExists);
		//Total for all movies currently in cart
		request.setAttribute("total", total);
		//Flat rate for a movie
		request.setAttribute("movieValue", "$1.00");
		
		request.setAttribute("checkOut",   
				   "http://localhost:8080/CyberFlix4/CyberFlixCheckOutServlet");
		
		request.setAttribute("staffLogin", CyberFlixLoginHandleServlet.staffLoginCheck);
		request.setAttribute("staffView",
				   "http://localhost:8080/CyberFlix4/CyberFlixStaffCustomerRentalViewServlet");
		
		//New Code 12/10/2017 11:25 -----------------------------------------------
		if(CyberFlixLoginHandleServlet.loginCheck)
		{
			int customerID = (int)session.getAttribute("customerID");
			RentalDAO object = new RentalDAO();
			for(int i = 0; i < films.size(); i++)
			{
				Date date = new Date();
				// 1234 is garbage. Gets overwritten in RentalDAO save method to null 
				//because it is auto incremented in the database
				RentalRecord rentalRecord = new RentalRecord(1234, date, films.get(i).getFilmID(), customerID, date);
				object.save(rentalRecord);
			}
		}
		//-------------------------------------------------------------------------
		
		request.setAttribute("films", films);
		request.setAttribute("posters", CyberFlixSplashServlet.posterLinks);
		request.getRequestDispatcher("checkOut.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
