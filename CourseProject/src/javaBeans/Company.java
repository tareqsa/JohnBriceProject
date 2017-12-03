package javaBeans;

import java.util.Collection;

/**
 * 
 * The company class -include the class constructor and all the class attributes.
 *
 */

public class Company 
{
	
	private long id ;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	
	public Company()
	{
		
	}
	
	
	/**
	 * 
	 * @param id the company's id
	 * @param compName the company's name
	 * @param password the company's password
	 * @param email the company's email
	 */
	public Company(long id, String compName, String password, String email) 
	{
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	
	public Company(long id, String compName, String password, String email, Collection<Coupon> coupons) 
	{
		this(id,compName,password,email);
		this.coupons = coupons;
	}

	
	/**
	 *  return the company's id
	 * @return the company's id
	 */
	public long getId() 
	{
		return id;
	}

	
	/**
	 * sets the company's id
	 * @param id the company's id
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	
	/**
	 * return the company's name
	 * @return the company's name
	 */
	public String getCompName() 
	{
		return compName;
	}
	

	/**
	 * sets the company's name
	 * @param compName the company's name
	 */
	public void setCompName(String compName) 
	{
		this.compName = compName;
	}

	
	/**
	 * return the company's password
	 * @return the company's password
	 */
	public String getPassword() 
	{
		return password;
	}

	
	/**
	 * sets the company's password
	 * @param password the company's password
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	
	/**
	 * return the company's email
	 * @return the company's email
	 */
	public String getEmail()
	{
		return email;
	}

	
	/**
	 * sets the company's email
	 * @param email the company's email
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	
	/**
	 * return the company's list of created coupons
	 * @return the company's list of created coupons
	 */
	public Collection<Coupon> getCoupons() 
	{
		return coupons;
	}

	
	/**
	 * sets the company's list of coupons
	 * @param coupons the company's list of created coupons
	 */
	public void setCoupons(Collection<Coupon> coupons) 
	{
		this.coupons = coupons;
		//this.coupons = new Collection<Coupon>(coupons);
	}

	
	/**
	 * print all the class attributes
	 */
	@Override
	public String toString() 
	{
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]\n";
	}
	
	

	
	
}
