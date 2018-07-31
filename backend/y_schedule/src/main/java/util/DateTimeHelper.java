package util;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

public class DateTimeHelper {

	public static Timestamp getWeekStart(Timestamp ldt) {

		return getWeekStart(ldt.toLocalDateTime());
	}

	public static Timestamp getWeekStart(LocalDateTime ldt) {

		LocalDateTime temp = ldt;

		while (temp.getDayOfWeek() != DayOfWeek.SUNDAY)
			temp = temp.minusDays(1);

		return Timestamp.valueOf(temp);
	}

	public static Timestamp getWeekStartByOffset(int offset) {
		LocalDateTime temp = LocalDateTime.now();
		temp.minusDays(7 * offset);

		return getWeekStart(temp);
	}

	public static String getDayOfWeekName(int day) {
		switch (day) {
		case 0:
			return "sunday";
		case 1:
			return "monday";
		case 2:
			return "tuesday";
		case 3:
			return "wednesday";
		case 4:
			return "thrusday";
		case 5:
			return "friday";
		default:
			return "saturday";
		}
	}

	public static String timeString(int hour, int minute) {
		
		String minutes = ""+minute;
		
		if (minutes.length() == 1)
			minutes = "0" + minutes;
		
		return hour + ":" + minutes;
	}
	
	public static JSONArray getWeekDates(int weekOffset) {
		JSONArray dates = new JSONArray();
		LocalDateTime ldt = getWeekStartByOffset(weekOffset).toLocalDateTime();
		
		for(int i = 0; i < 7; i++) {
			dates.put(formatDate(ldt));
			ldt = ldt.plusDays(1);
		}
		
		return dates;
	}

	public static String formatDate(LocalDateTime localDateTime) {
		return localDateTime.getDayOfMonth()+"/"+localDateTime.getMonthValue()+"/"+localDateTime.getYear();
	}

	public static Timestamp getTimestamp(String date, String startTime) {
		// TODO Auto-generated method stub
		return null;
	}
}
