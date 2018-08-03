package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

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
		temp = temp.plusDays(7 * offset);

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
			return "thursday";
		case 5:
			return "friday";
		default:
			return "saturday";
		}
	}

	public static String timeString(int hour, int minute) {

		String minutes = "" + minute;

		if (minutes.length() == 1)
			minutes = "0" + minutes;

		return hour + ":" + minutes;
	}

	public static JSONArray getWeekDates(int weekOffset) {
		JSONArray dates = new JSONArray();
		LocalDateTime ldt = getWeekStartByOffset(weekOffset).toLocalDateTime();

		for (int i = 0; i < 7; i++) {
			dates.put(formatDate(ldt));
			ldt = ldt.plusDays(1);
		}

		return dates;
	}

	public static String formatDate(LocalDateTime localDateTime) {
		return localDateTime.getMonthValue() + "/" + localDateTime.getDayOfMonth() + "/" + localDateTime.getYear();
	}

	public static Timestamp getTimestamp(String date, String time) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

		Timestamp ts = null;

		try {
			ts = new Timestamp(simpleDateFormat.parse(date + " " + time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return ts;
	}

	public static float TimeToFloat(String time) {
		String timeParts[] = time.split(":");
		float t = Float.parseFloat(timeParts[0]) + Float.parseFloat(timeParts[1]) / 60f;
		return t;
	}

	public static Timestamp getWeekEnd(Timestamp date) {
		return Timestamp.valueOf(date.toLocalDateTime().plusDays(6));
	}

	public static Integer TimestampGetDay(Timestamp ts) {
		return ts.toLocalDateTime().getDayOfWeek().getValue() % 7;
	}

	public static float TimestampToTimeFloat(Timestamp ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String timeStr = sdf.format(ts);

		return (float)Math.floor(TimeToFloat(timeStr));
	}

}
