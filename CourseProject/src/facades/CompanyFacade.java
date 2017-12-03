package facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javaBeans.ClientType;
import javaBeans.Coupon;
import javaBeans.CouponType;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import exceptions.CompanyExceptionHandler;
import exceptions.CouponExceptionHandler;
import exceptions.DuplicateEntryException;
import exceptions.EmptyEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;

/**
 * 
 * The CompanyFacade class is used by the company users of the couponsystem.
 * It grants them access to all of the relevant methods for their uses.
 *
 */
public class CompanyFacade implements CouponClientFacade
{

	private CompanyDBDAO companydbdao;
	private CouponDBDAO coupondbdao;
	private ArrayList<Coupon> coupons_array = new ArrayList<>();


	/**
	 * the CompanyFacade constructor.
	 * it initialize the companydbdao and the coupondbdao
	 */
	public CompanyFacade()
	{
		this.companydbdao = new CompanyDBDAO();
		this.coupondbdao = new CouponDBDAO();	

	}

	/**
	 * Receives a coupon instance and register it in the database
	 * @param coupon a coupon instance
	 */
	public void createCoupon(Coupon coupon)
	{	
		try
		{
			coupondbdao.createCoupon(coupon);
		}
		catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateEntryException | NullConnectionException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}
	}

	/**
	 * Receives a coupon instance and removes its entries from the database
	 * @param coupon a coupon instance
	 */
	public void removeCoupon(Coupon coupon)
	{
		try
		{
			coupondbdao.removeCoupon(coupon);
		}
		catch (ClassNotFoundException | SQLException | InterruptedException | NullConnectionException | EmptyEntryException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

	}

	/**
	 * Receives a coupon instance and update its entries in the database
	 * @param coupon a coupon instance
	 */
	public void updateCoupon(Coupon coupon)
	{
		try
		{
			coupondbdao.updateCoupon(coupon);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException | EmptyEntryException  e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

	}

	/**
	 * Receives a coupon' id and return an instance of that coupon from the database
	 * @param id a coupon's id
	 * @return an instance of the desired coupon from the database
	 */
	public Coupon getCoupon(int id)
	{
		Coupon coup = new Coupon();

		try
		{
			coup = coupondbdao.getCoupon(id);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException e)
		{
			CompanyExceptionHandler.companyExceptionHandler(e);
		}

		return coup;

	}

	/**
	 * returns an ArrayList of all the company's coupons in the database
	 * @return an ArrayList of all the company's coupons in the database
	 */
	public ArrayList<Coupon> getAllCoupon()
	{
		try
		{
			coupons_array = (ArrayList<Coupon>) companydbdao.getCoupons();
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException | EmptyEntryException |DuplicateEntryException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

		return coupons_array;


	}

	/**
	 * returns an ArrayList of all the company's coupons in the database by a given type
	 * @param couponType a coupon type
	 * @return an ArrayList of all the company's coupons in the database by a given type
	 */
	public ArrayList<Coupon> getCouponByType(CouponType couponType)
	{
		try
		{
			coupons_array = (ArrayList<Coupon>) companydbdao.getCompanyCouponByType(couponType);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException |DuplicateEntryException | EmptyEntryException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

		return coupons_array;
	}

	/**
	 * returns an ArrayList of all the company's coupons in the database with a price up to a given price
	 * @param price the max price of a coupon
	 * @return an ArrayList of all the company's coupons in the database with a price up to a given price
	 */
	public ArrayList<Coupon> getCouponByPrice(double price)
	{
		try
		{
			coupons_array = (ArrayList<Coupon>) companydbdao.getCompanyCouponByPrice(price);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException |EmptyEntryException | DuplicateEntryException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

		return coupons_array;
	}

	/**
	 * returns an ArrayList of all the company's coupons in the database with an end date up to a given date
	 * @param date a given end date
	 * @return an ArrayList of all the company's coupons in the database with an end date up to a given date
	 */
	public ArrayList<Coupon> getCouponByDate (Date date)
	{
		try
		{
			coupons_array = (ArrayList<Coupon>) companydbdao.getCompanyCouponByDate(date);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException | DuplicateEntryException | EmptyEntryException e)
		{
			CouponExceptionHandler.couponExceptionHandle(e);
		}

		return coupons_array;
	}

	/**
	 * checks the database for a company entry with the given name and the given password
	 * returns true if it found one, returns false if there is no such entry in the database
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType)
	{	
		try
		{
			if( companydbdao.login(name, password))
				return this;
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | WrongDataInputException | NullConnectionException e)
		{
			CompanyExceptionHandler.companyExceptionHandler(e);
		}

		return null;
	}

	/**
	 * sets the id of the current user in the coupon DBDAO
	 */
	public void setUserId()
	{
		this.coupondbdao.setUser_Company_id(companydbdao.getCompany_id());
	}
}
