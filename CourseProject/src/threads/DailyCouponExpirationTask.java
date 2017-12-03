package threads;
import javaBeans.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.CouponDBDAO;
import exceptions.EmptyEntryException;
import exceptions.GeneralExceptionHandler;
import exceptions.NullConnectionException;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyCouponExpirationTask.
 */
public class DailyCouponExpirationTask implements Runnable 
{

	/** The coupon D. */
	private CouponDBDAO couponD = new CouponDBDAO();

	/** The flag. */
	private boolean flag = false;

	/** The coupons array. */
	public ArrayList<Coupon> coupons_array = new ArrayList<>();



	

	/**
	 * Retrieves from the database an ArrayList of all the coupons in the database and removes all coupons
	 * that their end date has past
	 */
	@Override
	public void run() 
	{
		//get the date of today
		Date today = Calendar.getInstance().getTime();

		while(!flag)
		{
			try 
			{
				coupons_array = (ArrayList<Coupon>) couponD.getAllCoupon();

			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException	| NullConnectionException e) 
			{
				GeneralExceptionHandler.couponExceptionHandle(e);
			}
			for(Coupon c : coupons_array)
			{
				if(c.getEndDate().before(today))
				{
					try 
					{
						couponD.removeCoupon(c);
						System.out.println("Coupon: '"+c.getTitle()+"' have been removed");

					} catch (ClassNotFoundException | SQLException | InterruptedException | NullConnectionException | EmptyEntryException e) 
					{
						GeneralExceptionHandler.couponExceptionHandle(e);
					}

				}

			}
			try 
			{
				Thread.sleep(24*60*60*1000);


			} catch (InterruptedException e) 
			{
				GeneralExceptionHandler.couponExceptionHandle(e);
			}


		}

	}
	
	/**
	 * Stop task.
	 */
	public void stopTask()
	{
		flag = true;
	}





}
