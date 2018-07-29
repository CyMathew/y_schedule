package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.EmployeeDao;
import services.EmployeeService;

public class EmployeeServiceTest {
	
	@Test
	public void getTimesTest() {
		EmployeeDao mockDao = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB = new UserBean();
		UB.setUser_id(30);
		EmployeeAvailabilityBean monday      = new EmployeeAvailabilityBean("9:00", "10:00", UB, "monday");
		EmployeeAvailabilityBean monday2     = new EmployeeAvailabilityBean("12:00", "5:00", UB, "monday");
		EmployeeAvailabilityBean tuesday     = new EmployeeAvailabilityBean("9:00", "5:00", UB, "tuesday");
		EmployeeAvailabilityBean wednesday   = new EmployeeAvailabilityBean("9:00", "5:00", UB, "wednesday");
		EmployeeAvailabilityBean thursday    = new EmployeeAvailabilityBean("9:00", "5:00", UB, "thursday");
		EmployeeAvailabilityBean friday      = new EmployeeAvailabilityBean("9:00", "5:00", UB, "friday");
		EmployeeAvailabilityBean saturday    = new EmployeeAvailabilityBean("9:00", "5:00", UB, "saturday");
		EmployeeAvailabilityBean sunday      = new EmployeeAvailabilityBean("9:00", "5:00", UB, "sunday");

		List<EmployeeAvailabilityBean> mockList = new ArrayList<>();
		mockList.add(monday);
		mockList.add(monday2);
		mockList.add(tuesday);
		mockList.add(wednesday);
		mockList.add(thursday);
		mockList.add(friday);
		mockList.add(saturday);
		mockList.add(sunday);
		Mockito.when(mockDao.getStartTimesById(30)).thenReturn(mockList);
		
		service.setEd(mockDao);

		JSONObject object = service.getAvailableById(30);
		JSONArray array = object.getJSONArray("availDetails");

		JSONObject sundayObject        = array.getJSONObject(0);
		JSONObject mondayObject        = array.getJSONObject(1);
		JSONObject tuesdayObject       = array.getJSONObject(2);
		JSONObject wednesdayObject     = array.getJSONObject(3);
		JSONObject thursdayObject      = array.getJSONObject(4);
		JSONObject fridayObject        = array.getJSONObject(5);
		JSONObject saturdayObject      = array.getJSONObject(6);

		assertEquals("sunday", 		sundayObject.get("day"));
		assertEquals("monday", 		mondayObject.get("day"));
		assertEquals("tuesday", 	tuesdayObject.get("day"));
		assertEquals("wednesday", 	wednesdayObject.get("day"));
		assertEquals("thursday", 	thursdayObject.get("day"));
		assertEquals("friday", 		fridayObject.get("day"));
		assertEquals("saturday", 	saturdayObject.get("day"));
		
		JSONArray sundayTimes 		= array.getJSONObject(0).getJSONArray("times");
		JSONArray mondayTimes 		= array.getJSONObject(1).getJSONArray("times");
		JSONArray tuesdayTimes 		= array.getJSONObject(2).getJSONArray("times");
		JSONArray wednesdayTimes 	= array.getJSONObject(3).getJSONArray("times");
		JSONArray thursdayTimes 	= array.getJSONObject(4).getJSONArray("times");
		JSONArray fridayTimes 		= array.getJSONObject(5).getJSONArray("times");
		JSONArray saturdayTimes 	= array.getJSONObject(6).getJSONArray("times");
		
		assertEquals("9:00", 	sundayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	sundayTimes.getJSONObject(0).get("endTime"));
		assertEquals("9:00", 	mondayTimes.getJSONObject(0).get("startTime"));
		assertEquals("10:00",	mondayTimes.getJSONObject(0).get("endTime"));
		assertEquals("12:00", 	mondayTimes.getJSONObject(1).get("startTime"));
		assertEquals("5:00", 	mondayTimes.getJSONObject(1).get("endTime"));
		assertEquals("9:00", 	tuesdayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	tuesdayTimes.getJSONObject(0).get("endTime"));
		assertEquals("9:00", 	wednesdayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	wednesdayTimes.getJSONObject(0).get("endTime"));
		assertEquals("9:00", 	thursdayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	thursdayTimes.getJSONObject(0).get("endTime"));
		assertEquals("9:00", 	fridayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	fridayTimes.getJSONObject(0).get("endTime"));
		assertEquals("9:00", 	saturdayTimes.getJSONObject(0).get("startTime"));
		assertEquals("5:00", 	saturdayTimes.getJSONObject(0).get("endTime"));
	}
	

	
	@Test
	public void editTimesTest() {
		EmployeeDao mockDao = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests("9:00", "5:00", "monday", UB)).thenReturn("success");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(true);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONArray times = new JSONArray();
		JSONObject timesobj = new JSONObject();
		obj.put("day", "monday");
		timesobj.put("startTime", "9:00");
		timesobj.put("endTime", "5:00");
		times.put(timesobj);
		obj.put("times", times);
		array.put(obj);
		
		assertEquals("success", service.editAvailDetails(array, UB).get("result"));
			
	}
	
	@Test
	public void editTimesTestfailure() {
		EmployeeDao mockDao = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests("9:00", "5:00", "monday", UB)).thenReturn("success");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(false);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONArray times = new JSONArray();
		JSONObject timesobj = new JSONObject();
		obj.put("day", "monday");
		timesobj.put("startTime", "9:00");
		timesobj.put("endTime", "5:00");
		times.put(timesobj);
		obj.put("times", times);
		array.put(obj);
		
		assertEquals("failure", service.editAvailDetails(array, UB).get("result"));
			
	}
	
	@Test
	public void editTimesTestfailure2() {
		EmployeeDao mockDao = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests("9:00", "5:00", "monday", UB)).thenReturn("failure");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(true);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONArray times = new JSONArray();
		JSONObject timesobj = new JSONObject();
		obj.put("day", "monday");
		timesobj.put("startTime", "9:00");
		timesobj.put("endTime", "5:00");
		times.put(timesobj);
		obj.put("times", times);
		array.put(obj);
		
		assertEquals("failure", service.editAvailDetails(array, UB).get("result"));
			
	}

	

}
