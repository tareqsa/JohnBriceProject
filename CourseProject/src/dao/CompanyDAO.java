package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateEntryException;
import exceptions.EmptyEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Company;
import javaBeans.Coupon;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO 
{

	/**
	 * Creates the company.
	 *
	 * @param comp the comp
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void createCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException;
	
	/**
	 * Removes the company.
	 *
	 * @param comp the comp
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public void removeCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException;
	
	/**
	 * Update company.
	 *
	 * @param comp the comp
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException;
	
	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws ParseException the parse exception
	 */
	public Company getCompany(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException;
	
	/**
	 * Gets the all companies.
	 *
	 * @return the all companies
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws ParseException the parse exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public Collection<Company> getAllCompanies() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException, DuplicateEntryException, EmptyEntryException;
	
	/**
	 * Gets the coupons.
	 *
	 * @return the coupons
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public Collection<Coupon> getCoupons() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, DuplicateEntryException, EmptyEntryException; 
	
	/**
	 * Login.
	 *
	 * @param compName the comp name
	 * @param password the password
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws WrongDataInputException the wrong data input exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean login(String compName, String password) throws ClassNotFoundException, InterruptedException, SQLException, WrongDataInputException, NullConnectionException;

	
}
