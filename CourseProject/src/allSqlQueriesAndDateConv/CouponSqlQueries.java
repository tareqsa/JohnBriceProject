package allSqlQueriesAndDateConv;

// TODO: Auto-generated Javadoc
/**
 * The Class CouponSqlQueries.
 */
public class CouponSqlQueries
{
	
	/** The all coupons. */
	public static String all_coupons = "SELECT * FROM coupon";
	
	/** The coupon id by comp id. */
	public static String coupon_id_by_comp_id = "SELECT COUPON_ID FROM company_coupon WHERE COMP_ID = '%1s'";
	
	/** The delete coupon by coupon id. */
	public static String delete_coupon_by_coupon_id = "DELETE FROM coupon WHERE ID='%1s'";
	
	/** The delete customer coupon by coupon id. */
	public static String delete_customer_coupon_by_coupon_id = "DELETE FROM customer_coupon WHERE COUPON_ID='%1s'";
	
	/** The delete company coupon by coupon id. */
	public static String delete_company_coupon_by_coupon_id = "DELETE FROM company_coupon WHERE COUPON_ID='%1s'";
	
	/** The coupons by id. */
	public static String coupons_by_id = "SELECT * FROM coupon WHERE ID='%1s'";
	
	/** The coupons by type. */
	public static String coupons_by_type = "SELECT * FROM coupon WHERE TYPE='%1s'";
	
	/** The coupons by type and id. */
	public static String coupons_by_type_and_id = "SELECT * FROM coupon WHERE TYPE='%1s' AND ID='%1s'";
	
	/** The coupons by price and id. */
	public static String coupons_by_price_and_id = "SELECT * FROM coupon WHERE PRICE<='%1s' AND ID='%1s'";
	
	/** The coupons by title. */
	public static String coupons_by_title = "SELECT * FROM coupon WHERE TITLE='%1s'";
	
	/** The insert coupon to coupon. */
	public static String insert_coupon_to_coupon = "INSERT INTO coupon (TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) values(?,?,?,?,?,?,?,?)";
	
	/** The insert coupon to comp coup. */
	public static String insert_coupon_to_compCoup = "INSERT INTO company_coupon (COMP_ID, COUPON_ID) values (?, ?)";
	
	/** The update coupon by id. */
	public static String update_coupon_by_id = "UPDATE coupon SET END_DATE=?, PRICE=? WHERE ID=?";
	
	/** The amount and end date by id. */
	public static String amount_and_end_date_by_id = "SELECT AMOUNT, END_DATE FROM coupon WHERE ID = '%1s'";
	
	/** The coupon id by cust id. */
	public static String coupon_id_by_cust_id = "SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = '%1s'";
	
	/** The coupon type by id. */
	public static String coupon_type_by_id = "SELECT TYPE FROM coupon WHERE id = '%1s'";
	
	/** The update coupon amount. */
	public static String update_coupon_amount = "UPDATE coupon SET AMOUNT = ? WHERE ID = ?";

	
	
}
