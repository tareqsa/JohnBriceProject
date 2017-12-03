package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateEntryException;
import exceptions.EmptyEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Coupon;
import javaBeans.Customer;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerDAO.
 */
public interface CustomerDAO 
{
	
	/**
	 * Creates the customer.
	 *
	 * @param cust the cust
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void createCustomer(Customer cust) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException;
	
	/**
	 * Removes the customer.
	 *
	 * @param cust the cust
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public void removeCustomer(Customer cust) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException;
	
	/**
	 * Update customer.
	 *
	 * @param cust the cust
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public void updateCustomer(Customer cust) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException;
	
	/**
	 * Gets the customer.
	 *
	 * @param id the id
	 * @return the customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Customer getCustomer(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException;
	
	/**
	 * Gets the all customer.
	 *
	 * @return the all customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Customer> getAllCustomer() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException;
	
	/**
	 * Gets the coupons.
	 *
	 * @return the coupons
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCoupons() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException; 
	
	/**
	 * Login.
	 *
	 * @param custName the cust name
	 * @param password the password
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws WrongDataInputException the wrong data input exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean login(String custName, String password) throws ClassNotFoundException, InterruptedException, SQLException, WrongDataInputException, NullConnectionException;
	

}