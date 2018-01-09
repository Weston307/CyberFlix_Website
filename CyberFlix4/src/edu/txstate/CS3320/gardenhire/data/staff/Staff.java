package edu.txstate.CS3320.gardenhire.data.staff;

public class Staff
{
	private int    id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String username;
	private String password;
	
	public Staff(int id, String firstName, String lastName, String emailAddress, String username, String password)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.username = username;
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
	
	public void setEmail(String email)
	{
		this.emailAddress = email;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
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
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	@Override
	public String toString() {
		return "Staff [staffID =" + id + ", firstName =" + firstName + ", lastName ="
				+ lastName + ", email =" + emailAddress + ",username = " + username +", password =" + password + "]";
	}
}
