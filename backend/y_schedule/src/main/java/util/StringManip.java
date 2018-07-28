package util;

import java.sql.Timestamp;
import java.text.NumberFormat;

public class StringManip {

	public static Timestamp timestampFromDateAndTime(String date, String time) {
		//TODO (date in dd/mm/yyyy must be converted to yyyy/mm/dd)
		return null;
	}
	
	public static String formatCurrency(Double a) {

		if (a == null)
			return null;

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = nf.format(a);

		return s;
	}
}
