// Tareq saeed
// 301032058
package maintests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

import allSqlQueriesAndDateConv.DateConverter;
import conPool.ConnectionPool;
import facades.*;
import javaBeans.*;

public class Tester {

	public static void main(String[] args) throws ParseException  
	{

		Connection conn = ConnectionPool.getInstance().getConnection();
		try
		{
			Statement stt = conn.createStatement();
			stt.execute("truncate company");
			stt.execute("truncate company_coupon");
			stt.execute("truncate customer");
			stt.execute("truncate customer_coupon");
			stt.execute("truncate coupon");
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AdminFacade adminfacade;
		CompanyFacade companyfacade;
		CustomerFacade customerfacade;
		System.out.println("STARTING");
		
		System.out.println("=======================================Login with admin=============================================");
		//admin login
		adminfacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		
		System.out.println("*****************Create companies*****************");
		//create companies
		Company comp1 = new Company(1,"amdocs","123","www@hello");
		Company comp2 = new Company(2,"intel","456","www@hello");
		Company comp3 = new Company(3,"redhat","789","www@hello");
		Company comp4 = new Company(4,"checkpoint","101","www@hello");
		adminfacade.createCompany(comp1);
		adminfacade.createCompany(comp2);
		adminfacade.createCompany(comp3);
		adminfacade.createCompany(comp4);
		//trying to create an exist company
		adminfacade.createCompany(comp4);
		
		System.out.println("****************GetAll companies*******************");
		//get all companies
		System.out.println(adminfacade.getAllCompanies());
		
		System.out.println("****************Update and get company*************");
		//update and get the company
		Company company1 = adminfacade.getCompany(1);
		company1.setEmail("tareq@gmail.com");
		company1.setPassword("321");
		adminfacade.updateCompany(company1);
		System.out.println(adminfacade.getCompany(1));
		
		System.out.println("*****************Create customers******************");
		//create customers
		Customer cust1 = new Customer(1,"eli","111");
		Customer cust2 = new Customer(2,"moshe","222");
		Customer cust3 = new Customer(3,"haim","333");
		Customer cust4 = new Customer(4,"erez","444");
		adminfacade.createCustomer(cust1);
		adminfacade.createCustomer(cust2);
		adminfacade.createCustomer(cust3);
		adminfacade.createCustomer(cust4);
		//trying to create an exist customer
		adminfacade.createCustomer(cust4);
		
		System.out.println("****************Get All customers******************");
		//get all customers
		System.out.println(adminfacade.getAllCustomer());
		
		System.out.println("*****************Update and get customer***********");
		//update and get the customer
		Customer customer1 = adminfacade.getCustomer(2);
		customer1.setPassword("555");
		adminfacade.updateCustomer(customer1);
		System.out.println(adminfacade.getCustomer(2));
		
		
		
		
		
		
		
		System.out.println("======================================Login with company =========================================");
		//trying to login with incorrect company password
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("amdocs", "123", ClientType.COMPANY);
		//login with the correct company password
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("amdocs", "321", ClientType.COMPANY);
		
		System.out.println("*******************Create company coupons******************");
		//create company coupons 
		Coupon coupon1 = new Coupon(0, "first", DateConverter.stringToDate("18-01-01"), DateConverter.stringToDate("18-01-29"), 5,CouponType.CAMPING, "hello", 21,"Image");
		Coupon coupon2 = new Coupon(0, "second", DateConverter.stringToDate("18-12-01"), DateConverter.stringToDate("18-02-20"), 5,CouponType.FOOD, "hello", 26.4,"Image");
		Coupon coupon3 = new Coupon(0, "third", DateConverter.stringToDate("18-01-12"), DateConverter.stringToDate("18-03-18"), 5,CouponType.SPORTS, "hello", 33.7,"Image");
		Coupon coupon4 = new Coupon(0, "fourth", DateConverter.stringToDate("18-01-10"), DateConverter.stringToDate("18-03-13"), 5,CouponType.SPORTS, "hello", 36,"Image");

		companyfacade.createCoupon(coupon1);
		companyfacade.createCoupon(coupon2);
		companyfacade.createCoupon(coupon3);
		companyfacade.createCoupon(coupon4);

		System.out.println("************Get company coupons by type*************");
		//get the company coupons with the same type
		System.out.println(companyfacade.getCouponByType(CouponType.SPORTS));
		
		System.out.println("************Get company coupons by price************");
		//get the company coupons with same price and less
		System.out.println(companyfacade.getCouponByPrice(35));		
		
		System.out.println("*************Get company coupons by date************");
		//get the company coupons with same date and before it
		Date test_date = DateConverter.stringToDate("18-10-19");
		System.out.println(companyfacade.getCouponByDate(test_date));

		System.out.println("*******************Get coupon***********************");
		//get specific coupon
		System.out.println(companyfacade.getCoupon(3));
		
		System.out.println("***************Get all company coupons**************");
		//get all the company coupons 
		System.out.println(companyfacade.getAllCoupon());
		
		System.out.println("***************Update coupon ***********************");
		//update specific coupon
		Coupon coupon = new Coupon(3, "first", DateConverter.stringToDate("18-01-22"), DateConverter.stringToDate("18-04-11"), 5,CouponType.CAMPING, "hello", 30,"Image");
		companyfacade.updateCoupon(coupon);
		System.out.println(companyfacade.getCoupon(3));
		
		
		
		
		
		System.out.println("===================================Login with another company====================================");
		//another login
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("intel", "456", ClientType.COMPANY);
		
		System.out.println("****************Create company coupon********************");
		//create coupon for the new company
		Coupon coupon5 = new Coupon(0, "fifth", DateConverter.stringToDate("18-10-20"), DateConverter.stringToDate("19-02-13"), 5,CouponType.CAMPING, "hello", 11.4,"Image");
		companyfacade.createCoupon(coupon5);
		
		System.out.println("*****************Get all company coupon******************");
		System.out.println(companyfacade.getAllCoupon());
		
		
		
		
		
		System.out.println("======================================Login with customer========================================");
		//trying to login with incorrect customer password
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("eli","110",ClientType.CUSTOMER);
		//trying to login with correct customer password
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("eli","111",ClientType.CUSTOMER);
		
		System.out.println("*********************Get all coupons*********************");
		//get all coupons 
		System.out.println(customerfacade.getAllCoupons());
		
		System.out.println("*********************Purchase coupon*********************");
		//purchase specific coupon
		Coupon coup = customerfacade.getCoupon(2);
		customerfacade.purchaseCoupon(coup);
		
		System.out.println("**********Get all Purchased customer coupons*************");
		System.out.println(customerfacade.getAllPurchasedCoupons());
		
		System.out.println("**********Get all Purchased customer coupons by type**************");
		System.out.println(customerfacade.getAllPurchasedCouponsByType(CouponType.FOOD));
		
		System.out.println("**********Get all Purchased customer coupons by price**************");
		System.out.println(customerfacade.getAllPurchasedCouponsByPrice(27));
		// must get null
		System.out.println(customerfacade.getAllPurchasedCouponsByPrice(25));
		
		
		
		
		
		
		System.out.println("======================================Login with company =========================================");
		//back to the first company to remove one of its coupon 
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("amdocs", "321", ClientType.COMPANY);
		
		System.out.println("**********************Remove coupon *********************");
		Coupon cpn = companyfacade.getCoupon(2);
		companyfacade.removeCoupon(cpn);
		System.out.println(companyfacade.getAllCoupon());

		
		
		
		
		System.out.println("======================================Login with customer========================================");
		//back to the customer to see if coupon 2 is deleted and to purchase two more coupons
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("eli","111",ClientType.CUSTOMER);
		System.out.println(customerfacade.getAllCoupons());
		customerfacade.purchaseCoupon(customerfacade.getCoupon(3));
		customerfacade.purchaseCoupon(customerfacade.getCoupon(5));
		
		
		
		
		
		System.out.println("=======================================Login with admin=============================================");
		//back to the admin to remove one company and one customer
		adminfacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		
		System.out.println("**********************Remove company *********************");
		adminfacade.removeCompany(adminfacade.getCompany(2));
		
		System.out.println("**********************Remove customer *********************");
		adminfacade.removeCustomer(adminfacade.getCustomer(2));

		System.out.println(adminfacade.getAllCompanies());
		System.out.println(adminfacade.getAllCustomer());




		
		
		


		




		



		
		



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}