package allSqlQueriesAndDateConv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter 
{
	
	public static Date stringToDate(String date) throws ParseException
	{
		SimpleDateFormat simlpe_df = new SimpleDateFormat("yy-MM-dd");
		java.util.Date new_date = simlpe_df.parse(date);

		return new_date;

	}

	public static String dateToString(Date date)
	{
		DateFormat simlpe_df = new SimpleDateFormat("yy-MM-dd");
		String new_date = simlpe_df.format(date);

		return new_date;
	}

}
