package allSqlQueriesAndDateConv;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerSqlQueries.
 */
public class CustomerSqlQueries 
{
	
	/** The all customers. */
	public static String all_customers = "SELECT * FROM customer";
	
	/** The customers by cust name. */
	public static String customers_by_cust_name = "SELECT * FROM customer WHERE CUST_NAME ='%1s'";
	
	/** The customers by id. */
	public static String customers_by_id = "SELECT * FROM customer WHERE ID ='%1s'";

	/** The delete customer by cust name. */
	public static String delete_customer_by_cust_name = "DELETE FROM customer WHERE CUST_NAME ='%1s'";
	
	/** The delete customer coupon by cust id. */
	public static String delete_customer_coupon_by_cust_id = "DELETE FROM customer WHERE CUST_NAME ='%1s'";


	/** The insert customer coupon. */
	public static String insert_customer_coupon = " INSERT INTO customer_coupon (CUST_ID,COUPON_ID) values (?, ?)";
	
	/** The customers by name and password. */
	public static String customers_by_name_and_password = "SELECT * FROM customer WHERE CUST_NAME = '%1s' AND PASSWORD = '%2s'";
	
	/** The insert into customer. */
	public static String insert_into_customer = "INSERT INTO customer (CUST_NAME, PASSWORD) values (?, ?)";
	
	/** The update customer by id. */
	public static String update_customer_by_id = "UPDATE customer SET PASSWORD=? WHERE ID=?";

	
}
