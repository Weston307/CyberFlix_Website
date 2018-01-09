package edu.txstate.CS3320.gardenhire.data.customer;

public class Customer {
	
	private int    id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	
	public Customer(int id, String firstName, String lastName, String email, String password)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = email;
		this.password = password;
	}
	
	public void setID(int ID)
	{
		this.id = ID;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return emailAddress;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	@Override
	public String toString() {
		return "Customer [customerID =" + id + ", firstName =" + firstName + ", lastName ="
				+ lastName + ", email =" + emailAddress + ", password =" + password + "]";
	}

}
