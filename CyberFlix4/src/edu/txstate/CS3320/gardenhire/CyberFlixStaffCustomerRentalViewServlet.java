package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CyberFlixStaffCustomerRentalView
 */
@WebServlet("/CyberFlixStaffCustomerRentalViewServlet")
public class CyberFlixStaffCustomerRentalViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixStaffCustomerRentalViewServlet() {
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
		
		request.getRequestDispatcher("staffCustomerRentalView.jsp").forward(request,  response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
