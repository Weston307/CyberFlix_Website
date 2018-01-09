package edu.txstate.CS3320.gardenhire;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.CS3320.gardenhire.data.DataSource;
import edu.txstate.CS3320.gardenhire.data.customer.Customer;
import edu.txstate.CS3320.gardenhire.data.staff.Staff;


/**
 * Servlet implementation class CyberFlixLoginHandleServlet
 */
@WebServlet("/CyberFlixLoginHandleServlet")
public class CyberFlixLoginHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean loginCheck = false;
	public static boolean staffLoginCheck = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixLoginHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check user info
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session=request.getSession();
		session.setAttribute("username", username);
		
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
		
		request.setAttribute("cartNotEmpty", CyberFlixCart.cartExists);
		
		request.setAttribute("checkOut",   
				   "http://localhost:8080/CyberFlix4/CyberFlixCheckOutServlet");
		
		request.setAttribute("staffView",
				   "http://localhost:8080/CyberFlix4/CyberFlixStaffCustomerRentalViewServlet");
				
		if(!username.contains("staff"))
		{
			Customer customer = DataSource.customerLoginVerify(username, password);
			if(customer == null)
			{
				request.setAttribute("result", "Either your username or password are incorrect");
			}
			else
			{
				request.setAttribute("result", "Login Successful!");
				session.setAttribute("customerID", customer.getID());
				loginCheck = true;
			}
		}
		else if(username.contains("staff"))
		{
			Staff staff = DataSource.staffLoginVerify(username, password);
			if(staff == null)
			{
				request.setAttribute("result", "Either your username or password are incorrect");
			}
			else
			{
				request.setAttribute("result", "Welcome Staff Member "+ staff.getUsername());
				session.setAttribute("staffID", staff.getID());
				staffLoginCheck = true;
				request.setAttribute("staffLogin", staffLoginCheck);
			}
		}
		request.getRequestDispatcher("loginResult.jsp").forward(request,  response);
			
		
	}

}
