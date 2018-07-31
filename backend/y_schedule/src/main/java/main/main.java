package main;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import daos.ManagerDao;

public class main {

	public static void main(String[] args) {
		ManagerDao md = new ManagerDao();
		DateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		java.util.Date start;
		java.util.Date end;
		try {
			start = date.parse("2018.07.20.05.00.00");
			end = date.parse("2018.08.20.08.00.00");
			long startTime = start.getTime();
			long endTime = end.getTime();
			System.out.println(md.getScheduleByEmployee(22,(new Timestamp(startTime)),(new Timestamp(endTime))));
			System.out.println(new Timestamp(startTime));
			System.out.println(new Timestamp(endTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
