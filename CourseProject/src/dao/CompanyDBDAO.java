package dao;

import java.sql.Connection;
import java.util.Date;

import allSqlQueriesAndDateConv.CompanySqlQueries;
import allSqlQueriesAndDateConv.CouponSqlQueries;
import allSqlQueriesAndDateConv.DateConverter;
import conPool.ConnectionPool;
import exceptions.DuplicateEntryException;
import exceptions.EmptyEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;


import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.CouponType;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDBDAO.
 */
public class CompanyDBDAO implements CompanyDAO
{
	
	/** The pool. */
	private ConnectionPool pool;

	/** The companies array. */
	ArrayList<Company> companies_array = new ArrayList<>();
	
	/** The coupons array. */
	ArrayList<Coupon> coupons_array = new ArrayList<>();



	/** The user company id. */
	private long user_company_id;
	
	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public long getCompany_id() 
	{
		return user_company_id;
	}

	/**
	 * Sets the company id.
	 *
	 * @param company_id the new company id
	 */
	public void setCompany_id(long company_id)
	{
		this.user_company_id = company_id;
	}
	
	/**
	 * Instantiates a new company DBDAO.
	 */
	//======================================================Default constructor==============================================
	public CompanyDBDAO() 
	{
		pool = ConnectionPool.getInstance();
	}

	//======================================================Create company===================================================

	/**
	 * receives a company instance and writing it in the database
	 */
	@Override
	public void createCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException 
	{
		//System.out.println("going to insert");
		Connection con = pool.getConnection();
		Statement stt;
		ResultSet rs;
		stt = con.createStatement();
		rs = stt.executeQuery(String.format(CompanySqlQueries.companies_by_name, comp.getCompName()));
		if(rs.next())
		{
			throw new DuplicateEntryException("the admin tried to creat a company with a name that already exist in the datbase");		
		}
		else
		{
			String insert_company_query = CompanySqlQueries.insert_company;
			PreparedStatement prst = con.prepareStatement(insert_company_query);
			prst.setString(1, comp.getCompName());
			prst.setString(2, comp.getPassword());
			prst.setString(3, comp.getEmail());

			prst.execute();
			System.out.println("companies inserted:)");
		}
		pool.returnConnection(con);
	}

	//======================================================Remove company==================================================

	/**
	 * receives a company instance and removes it from the database
	 */
	@Override
	public void removeCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException
	{

		Connection con = pool.getConnection();

		ResultSet rs;
		Statement stt;
		stt = con.createStatement();
		rs = stt.executeQuery(String.format(CompanySqlQueries.companies_by_id, comp.getId()));
		if(rs.next())
		{
			long compId = comp.getId();
			stt.execute(String.format(CompanySqlQueries.remove_company_by_id, compId));

			ResultSet rs1;
			Statement stt1 = con.createStatement(); 
			rs1 = stt1.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, comp.getId()));
			while(rs1.next())
			{
				Statement stt2 = con.createStatement();
				stt2.execute(String.format(CouponSqlQueries.delete_coupon_by_coupon_id, rs1.getInt("COUPON_ID")));
				stt2.execute(String.format(CouponSqlQueries.delete_customer_coupon_by_coupon_id, rs1.getLong("COUPON_ID")));
				stt2.execute(String.format(CouponSqlQueries.delete_company_coupon_by_coupon_id, compId));
			}

		}
		else
		{
			pool.returnConnection(con);
			throw new EmptyEntryException("trying to remove un existed company!");
		}
	}

	//======================================================Update company===================================================

	/**
	 * receives a company instance and update it's values in the database
	 */
	@Override
	public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, EmptyEntryException
	{
		Connection con = pool.getConnection();

		ResultSet rs;
		Statement stt;
		stt = con.createStatement();
		rs = stt.executeQuery(String.format(CompanySqlQueries.companies_by_id, comp.getId()));
		if(rs.next())
		{
			String update_company_query = CompanySqlQueries.update_company_by_id;
			PreparedStatement prpst = con.prepareStatement(update_company_query);
			prpst.setString(1, comp.getPassword());
			prpst.setString(2, comp.getEmail());
			prpst.setLong(3, comp.getId());
			prpst.execute();
		}
		else
		{
			pool.returnConnection(con);
			throw new EmptyEntryException("trying to update un existed company!");
			
		}

	}

	//======================================================Get company by id===============================================

	/**
	 * receives an id of a company and returns an instance of the company with
	 * it's values from the database
	 * @throws ParseException thrown when the date is not in the correct format
	 */
	@Override
	public Company getCompany(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Connection con = pool.getConnection();
		Company cpny = new Company();
		Statement stt;
		ResultSet rs;
		stt = con.createStatement();
		rs = stt.executeQuery(String.format(CompanySqlQueries.companies_by_id, id));
		if(rs.next())
		{
			cpny.setId(rs.getInt("ID"));
			cpny.setCompName(rs.getString("COMP_NAME"));
			cpny.setEmail(rs.getString("EMAIL"));
			cpny.setPassword(rs.getString("PASSWORD"));
			ArrayList<Coupon> arrayCoupon = new ArrayList<>();
			Statement stt2 = con.createStatement();
			ResultSet rs1;
			rs1 = stt2.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, rs.getInt("ID")));

			while(rs1.next())
			{
				Statement stt3 = con.createStatement();
				ResultSet rs2;
				rs2 = stt3.executeQuery(String.format(CouponSqlQueries.coupons_by_id, rs1.getInt("COUPON_ID")));
				while(rs2.next())
				{
					Coupon cpon = new Coupon();
					cpon.setId(rs2.getInt("ID"));
					cpon.setTitle(rs2.getString("TITLE"));
					cpon.setStartDate(rs2.getDate("START_DATE"));
					cpon.setEndDate(rs2.getDate("END_DATE"));
					cpon.setAmount(rs2.getInt("AMOUNT"));
					cpon.setType(CouponType.valueOf(rs2.getString("TYPE")));
					cpon.setMessage(rs2.getString("MESSAGE"));
					cpon.setPrice(rs2.getDouble("PRICE"));
					cpon.setImage(rs2.getString("IMAGE"));

					arrayCoupon.add(cpon);
				}


			}
			cpny.setCoupons(arrayCoupon);
		}
		pool.returnConnection(con);
		return cpny;


	}

	//======================================================Get all companies=================================================

	/**
	 * returns an ArrayList of all the companys in the database
	 * @throws ParseException thrown when the date is not in the correct format 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException, DuplicateEntryException, EmptyEntryException
	{

		companies_array.removeAll(companies_array);

		Connection con = pool.getConnection() ;

		Statement stt3 = con.createStatement();
		ResultSet rs3;
		rs3 = stt3.executeQuery(String.format(CompanySqlQueries.all_companies));
		while(rs3.next())
		{
			Company thecompany = new Company();
			thecompany.setId(rs3.getLong("ID"));
			thecompany.setCompName(rs3.getString("COMP_NAME"));
			thecompany.setPassword(rs3.getString("PASSWORD"));
			thecompany.setEmail(rs3.getString("EMAIL"));
			companies_array.add(thecompany);

		}
		for (Company c : companies_array)
		{
			this.setCompany_id(c.getId());
			c.setCoupons(this.getCoupons());

		}
		pool.returnConnection(con);
		return companies_array;
	}

	//======================================================Get coupons=====================================================
	/**
	 * returns an ArrayList of all the company's coupons in the database
	 */
	@Override
	public Collection<Coupon> getCoupons() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, DuplicateEntryException, EmptyEntryException
	{
		coupons_array.removeAll(coupons_array);

		ArrayList<Long> couponId = new ArrayList<>(); 
		Connection con = pool.getConnection();

		Statement stt = con.createStatement();
		ResultSet rs3;
		rs3 = stt.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, this.getCompany_id()));
		while(rs3.next())
		{
			couponId.add(rs3.getLong("COUPON_ID"));

		}

		for (int i=0;i<couponId.size();i++)
		{
			Statement stt1 = con.createStatement();
			ResultSet rs4;
			rs4 = stt1.executeQuery(String.format(CouponSqlQueries.coupons_by_id, couponId.get(i)));
			while(rs4.next())
			{
				Coupon coupons1 = new Coupon();
				coupons1.setId(rs4.getInt("ID"));
				coupons1.setTitle(rs4.getString("TITLE"));
				coupons1.setStartDate(DateConverter.stringToDate(rs4.getString("START_DATE")));
				coupons1.setEndDate(DateConverter.stringToDate(rs4.getString("END_DATE")));
				coupons1.setAmount(rs4.getInt("AMOUNT"));
				coupons1.setType(CouponType.valueOf(rs4.getString("TYPE")));
				coupons1.setMessage(rs4.getString("MESSAGE"));
				coupons1.setPrice(rs4.getDouble("PRICE"));
				coupons1.setImage(rs4.getString("IMAGE"));

				coupons_array.add(coupons1);
			}
		}

		pool.returnConnection(con);
		return coupons_array;
	}
	//======================================================Get company coupon by type==========================================

	/**
	 * Gets the company coupon by type.
	 *
	 * @param couponType the coupon type
	 * @return the company coupon by type
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public Collection<Coupon> getCompanyCouponByType(CouponType couponType) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, DuplicateEntryException, EmptyEntryException
	{

		coupons_array.removeAll(coupons_array);

		ArrayList<Long> couponId = new ArrayList<>(); 
		Connection con = pool.getConnection();
		Statement stt = con.createStatement();
		ResultSet rs3;
		rs3 = stt.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, this.getCompany_id()));
		while(rs3.next())
		{
			couponId.add(rs3.getLong("COUPON_ID"));

		}

		for (int i=0;i<couponId.size();i++)
		{
			Statement stt1 = con.createStatement();
			ResultSet rs4;
			rs4 = stt1.executeQuery(String.format(CouponSqlQueries.coupons_by_type_and_id, couponType.toString(), couponId.get(i)));
			while(rs4.next())
			{
				Coupon coupons1 = new Coupon();
				coupons1.setId(rs4.getInt("ID"));
				coupons1.setTitle(rs4.getString("TITLE"));
				coupons1.setStartDate(DateConverter.stringToDate(rs4.getString("START_DATE")));
				coupons1.setEndDate(DateConverter.stringToDate(rs4.getString("END_DATE")));
				coupons1.setAmount(rs4.getInt("AMOUNT"));
				coupons1.setType(CouponType.valueOf(rs4.getString("TYPE").trim()));
				coupons1.setMessage(rs4.getString("MESSAGE"));
				coupons1.setPrice(rs4.getDouble("PRICE"));
				coupons1.setImage(rs4.getString("IMAGE"));

				coupons_array.add(coupons1);

			}

		}
		pool.returnConnection(con);
		return coupons_array;

	}

	//======================================================Get company coupon by price==========================================

	/**
	 * Gets the company coupon by price.
	 *
	 * @param price the price
	 * @return the company coupon by price
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public Collection<Coupon> getCompanyCouponByPrice(double price) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, DuplicateEntryException, EmptyEntryException
	{

		coupons_array.removeAll(coupons_array);

		ArrayList<Long> couponId = new ArrayList<>(); 
		Connection con = pool.getConnection();

		Statement stt = con.createStatement();
		ResultSet rs3;
		rs3 = stt.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, this.getCompany_id()));
		while(rs3.next())
		{
			couponId.add(rs3.getLong("COUPON_ID"));

		}

		for (int i=0;i<couponId.size();i++)
		{
			Statement stt1 = con.createStatement();
			ResultSet rs4;
			rs4 = stt1.executeQuery(String.format(CouponSqlQueries.coupons_by_price_and_id, price, couponId.get(i)));
			while(rs4.next())
			{
				Coupon coupons1 = new Coupon();
				coupons1.setId(rs4.getInt("ID"));
				coupons1.setTitle(rs4.getString("TITLE"));
				coupons1.setStartDate(DateConverter.stringToDate(rs4.getString("START_DATE")));
				coupons1.setEndDate(DateConverter.stringToDate(rs4.getString("END_DATE")));
				coupons1.setAmount(rs4.getInt("AMOUNT"));
				coupons1.setType(CouponType.valueOf(rs4.getString("TYPE").trim()));
				coupons1.setMessage(rs4.getString("MESSAGE"));
				coupons1.setPrice(rs4.getDouble("PRICE"));
				coupons1.setImage(rs4.getString("IMAGE"));

				coupons_array.add(coupons1);
			}

		}
		pool.returnConnection(con);
		return coupons_array;

	}
	//======================================================Get company coupon by date==========================================

	/**
	 * Gets the company coupon by date.
	 *
	 * @param date the date
	 * @return the company coupon by date
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws EmptyEntryException the empty entry exception
	 */
	public Collection<Coupon> getCompanyCouponByDate(Date date) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, DuplicateEntryException, EmptyEntryException
	{

		coupons_array.removeAll(coupons_array);

		ArrayList<Long> couponId = new ArrayList<>(); 
		Connection con = pool.getConnection();
		Statement stt = con.createStatement();
		ResultSet rs3;
		rs3 = stt.executeQuery(String.format(CouponSqlQueries.coupon_id_by_comp_id, this.getCompany_id()));
		while(rs3.next())
		{
			couponId.add(rs3.getLong("COUPON_ID"));

		}

		for (int i=0;i<couponId.size();i++)
		{
			Statement stt1 = con.createStatement();
			ResultSet rs4;
			rs4 = stt1.executeQuery(String.format(CouponSqlQueries.coupons_by_id, couponId.get(i)));
			while(rs4.next())
			{

				if(DateConverter.stringToDate(rs4.getString("END_DATE")).before(date))
				{
					Coupon coupons1 = new Coupon();
					coupons1.setId(rs4.getInt("ID"));
					coupons1.setTitle(rs4.getString("TITLE"));
					coupons1.setStartDate(DateConverter.stringToDate(rs4.getString("START_DATE")));
					coupons1.setEndDate(DateConverter.stringToDate(rs4.getString("END_DATE")));
					coupons1.setAmount(rs4.getInt("AMOUNT"));
					coupons1.setType(CouponType.valueOf(rs4.getString("TYPE").trim()));
					coupons1.setMessage(rs4.getString("MESSAGE"));
					coupons1.setPrice(rs4.getDouble("PRICE"));
					coupons1.setImage(rs4.getString("IMAGE"));

					coupons_array.add(coupons1);
				}
			}
		}
		pool.returnConnection(con);
		return coupons_array;

	}

	//======================================================Login===============================================================

	/**
	 * receives a company's name and it's password and checks for compatibility in the database
	 */
	@Override
	public boolean login(String compName, String password) throws ClassNotFoundException, InterruptedException, SQLException, WrongDataInputException, NullConnectionException
	{

		Connection con = pool.getConnection();
		Statement stt4 = con.createStatement();
		ResultSet rs;
		rs = stt4.executeQuery(String.format(CompanySqlQueries.companies_by_compName_and_password, compName, password));
		if(rs.next())
		{
			pool.returnConnection(con);
			this.user_company_id = rs.getLong("ID");
			return true;
		}
		else
		{
			pool.returnConnection(con);
			throw new WrongDataInputException("The user tried to login and entered the compName " + compName + " and the password " + password);

		}
	}


}
