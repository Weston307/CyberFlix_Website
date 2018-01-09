package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.CS3320.gardenhire.data.customer.*;
import edu.txstate.CS3320.gardenhire.data.film.Film;
import edu.txstate.CS3320.gardenhire.data.film.FilmDAO;

/**
 * Servlet implementation class CyberFlixCart
 */
@WebServlet("/CyberFlixCart")
public class CyberFlixCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static boolean cartExists = false;
    public static int numMovies = 0;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    public boolean getCartStatus () {
    	boolean status = this.cartExists;
    	return status;
    }
    
    public void setCartStatus (boolean status) {
    	this.cartExists = status;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		FilmDAO object = new FilmDAO();	
		int filmID = Integer.parseInt(request.getParameter("cart"));
		Film film = object.findFilmByID(filmID);
		
		if (this.getCartStatus() == false) {
		CustomerCart cart = new CustomerCart();
		cart.addFilmToCart(film);
		HttpSession session=request.getSession(); 
		this.setCartStatus(true);
		numMovies++;
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getHeader("referer"));
	}
		else {
			HttpSession session=request.getSession();
			CustomerCart cart = (CustomerCart) session.getAttribute("cart");
			cart.addFilmToCart(film);
			numMovies++;
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getHeader("referer"));
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