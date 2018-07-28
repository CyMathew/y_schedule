package util;

import java.text.NumberFormat;

public class StringManip {
	
	public static String formatCurrency(Double a) {

		if (a == null)
			return null;

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = nf.format(a);

		return s;
	}
}
