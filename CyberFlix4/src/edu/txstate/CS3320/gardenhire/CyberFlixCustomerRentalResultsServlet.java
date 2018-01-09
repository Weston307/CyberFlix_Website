package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.CS3320.gardenhire.data.RentalDAO;
import edu.txstate.CS3320.gardenhire.data.customer.RentalRecord;
import edu.txstate.CS3320.gardenhire.data.customer.Customer;
import edu.txstate.CS3320.gardenhire.data.customer.CustomerDAO;
import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;

/**
 * Servlet implementation class CyberFlixCustomerRentalResultsServlet
 */
@WebServlet("/CyberFlixCustomerRentalResultsServlet")
public class CyberFlixCustomerRentalResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixCustomerRentalResultsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		request.setAttribute("checkOut",   
				   "http://localhost:8080/CyberFlix4/CyberFlixCheckOutServlet");
		
		request.setAttribute("staffLogin", CyberFlixLoginHandleServlet.staffLoginCheck);
		request.setAttribute("staffView",
				   "http://localhost:8080/CyberFlix4/CyberFlixStaffCustomerRentalViewServlet");
		
		
		//This block gets the customer by ID then gets the customer's rental record
		int customerID = Integer.parseInt(request.getParameter("customerID"));
		CustomerDAO custDAO = new CustomerDAO();
		Customer custObject = custDAO.findCustomerByID(customerID);
		RentalDAO rentDAO = new RentalDAO();
		List <RentalRecord> rentalRecords = rentDAO.findRentalByCustomer(custObject);
		
		//Gets all films the user has ever rented and currently is renting
		ArrayList <Film> films = new ArrayList<Film>();
		FilmDAO filmObject= new FilmDAO();
		for(int i = 0; i < rentalRecords.size(); i++)
		{
			films.add(filmObject.findFilmByID(rentalRecords.get(i).getFilmID()));
		}
		
		//Sets rental records and customer
		request.setAttribute("rentalRecords", rentalRecords);
		request.setAttribute("customer", custObject);
		request.setAttribute("films", films);
		
		request.getRequestDispatcher("staffCustomerRentalViewResults.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
