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
	@Ignore
	public void getTimesTest() {
		EmployeeDao mockDao = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB = new UserBean();
		UB.setUser_id(30);
		EmployeeAvailabilityBean monday      = new EmployeeAvailabilityBean(9,  10, UB, "monday");
		EmployeeAvailabilityBean monday2     = new EmployeeAvailabilityBean(12, 17,  UB, "monday");
		EmployeeAvailabilityBean tuesday     = new EmployeeAvailabilityBean(9,  17,  UB, "tuesday");
		EmployeeAvailabilityBean wednesday   = new EmployeeAvailabilityBean(9,  17,  UB, "wednesday");
		EmployeeAvailabilityBean thursday    = new EmployeeAvailabilityBean(9,  17,  UB, "thursday");
		EmployeeAvailabilityBean friday      = new EmployeeAvailabilityBean(9,  17,  UB, "friday");
		EmployeeAvailabilityBean saturday    = new EmployeeAvailabilityBean(9,  17,  UB, "saturday");
		EmployeeAvailabilityBean sunday      = new EmployeeAvailabilityBean(9,  17,  UB, "sunday");

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
		JSONArray array = object.getJSONArray("weekDetails");
		
		assertEquals(9,      array.getJSONObject(0).get("startTime"));
		assertEquals(10,     array.getJSONObject(0).get("endTime"));
		assertEquals("monday",    array.getJSONObject(0).get("day"));
		assertEquals(12,     array.getJSONObject(1).get("startTime"));
		assertEquals(17,      array.getJSONObject(1).get("endTime"));
		assertEquals("monday",    array.getJSONObject(1).get("day"));
		
		assertEquals(9,      array.getJSONObject(2).get("startTime"));
		assertEquals(17,      array.getJSONObject(2).get("endTime"));
		assertEquals("tuesday",   array.getJSONObject(2).get("day"));
		
		assertEquals(9,      array.getJSONObject(3).get("startTime"));
		assertEquals(17,      array.getJSONObject(3).get("endTime"));
		assertEquals("wednesday", array.getJSONObject(3).get("day"));
		
		assertEquals(9,      array.getJSONObject(4).get("startTime"));
		assertEquals(17,      array.getJSONObject(4).get("endTime"));
		assertEquals("thursday",  array.getJSONObject(4).get("day"));
		
		assertEquals(9,      array.getJSONObject(5).get("startTime"));
		assertEquals(17,      array.getJSONObject(5).get("endTime"));
		assertEquals("friday",    array.getJSONObject(5).get("day"));
		
		assertEquals(9,      array.getJSONObject(6).get("startTime"));
		assertEquals(17,      array.getJSONObject(6).get("endTime"));
		assertEquals("saturday",  array.getJSONObject(6).get("day"));
		
		assertEquals(9,      array.getJSONObject(7).get("startTime"));
		assertEquals(17,      array.getJSONObject(7).get("endTime"));
		assertEquals("sunday",    array.getJSONObject(7).get("day"));


	}
	

	
	@Test
	@Ignore
	public void editTimesTest() {
		EmployeeDao mockDao     = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB             = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests(9, 17, "monday", UB)).thenReturn("success");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(true);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj  = new JSONObject();
		JSONObject time = new JSONObject().put("day", "monday").put("startTime", 9).put("endTime", 17);
		array.put(time);
		obj.put("availDetails", array);
		
		assertEquals("success", service.editAvailDetails(array, UB).get("result"));
			
	}
	
	@Test
	//@Ignore
	public void editTimesTestfailure() {
		EmployeeDao mockDao     = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB             = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests(9, 17, "monday", UB)).thenReturn("success");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(false);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj  = new JSONObject();
		JSONObject time = new JSONObject().put("day", "monday").put("startTime", 9).put("endTime", 17);
		array.put(time);
		obj.put("availDetails", array);
		
		assertEquals("failure", service.editAvailDetails(array, UB).get("result"));
			
	}
	
	@Test
	@Ignore
	public void editTimesTestfailure2() {
		EmployeeDao mockDao     = Mockito.mock(EmployeeDao.class);
		EmployeeService service = new EmployeeService();
		UserBean UB             = new UserBean();
		UB.setUser_id(30);
		Mockito.when(mockDao.updateRequests(9, 17, "monday", UB)).thenReturn("failure");
		Mockito.when(mockDao.removeAllReguests(30)).thenReturn(true);
		service.setEd(mockDao);
		
		JSONArray array = new JSONArray();
		JSONObject obj  = new JSONObject();
		JSONObject time = new JSONObject().put("day", "monday").put("startTime", 9).put("endTime", 17);
		array.put(time);
		obj.put("availDetails", array);
		
		assertEquals("failure", service.editAvailDetails(array, UB).get("result"));
			
	}



}

/*
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

assertEquals(9, 	sundayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	sundayTimes.getJSONObject(0).get("endTime"));
assertEquals(9, 	mondayTimes.getJSONObject(0).get("startTime"));
assertEquals("10:00",	mondayTimes.getJSONObject(0).get("endTime"));
assertEquals("12:00", 	mondayTimes.getJSONObject(1).get("startTime"));
assertEquals(17, 	mondayTimes.getJSONObject(1).get("endTime"));
assertEquals(9, 	tuesdayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	tuesdayTimes.getJSONObject(0).get("endTime"));
assertEquals(9, 	wednesdayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	wednesdayTimes.getJSONObject(0).get("endTime"));
assertEquals(9, 	thursdayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	thursdayTimes.getJSONObject(0).get("endTime"));
assertEquals(9, 	fridayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	fridayTimes.getJSONObject(0).get("endTime"));
assertEquals(9, 	saturdayTimes.getJSONObject(0).get("startTime"));
assertEquals(17, 	saturdayTimes.getJSONObject(0).get("endTime"));


JSONArray times = new JSONArray();
JSONObject timesobj = new JSONObject();
obj.put("day", "monday");
timesobj.put("startTime", 9);
timesobj.put("endTime", 17);
times.put(timesobj);
obj.put("times", times);
array.put(obj);
* */
