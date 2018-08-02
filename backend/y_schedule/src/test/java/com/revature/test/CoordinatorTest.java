package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.CoodinatorDao;
import daos.EmployeeDao;
import daos.UserDao;
import services.CoordinatorService;

public class CoordinatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void getListOfEmpsThatHavePendingRequests() {
		CoordinatorService coorser = new CoordinatorService();
		
		CoodinatorDao mockCoorDao = Mockito.mock(CoodinatorDao.class);
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		UserDao mockUserDao       = Mockito.mock(UserDao.class);
		UserBean mockUserBean     = Mockito.mock(UserBean.class);
		UserBean UB = new UserBean("FIRSTNAME", "LASTNAME", "PASSWORD", "USERNAME");
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
		
		Mockito.when(mockCoorDao.getPendingRequests()).thenReturn(mockList);
		Mockito.when(mockUserDao.getUserById(30)).thenReturn(mockUserBean);
		Mockito.when(mockUserBean.getUser_fname()).thenReturn("FIRSTNAME");
		Mockito.when(mockUserBean.getUser_lname()).thenReturn("LASTNAME");
		Mockito.when(mockUserBean.getUser_id()).thenReturn(30);
		coorser.setTheDao(mockCoorDao);
		coorser.setUseBean(mockUserBean);
		coorser.setUseDao(mockUserDao);
		JSONObject command = new JSONObject();
		command.put("action", "getAllRequests");
		
		assertEquals(30, coorser.gateKeeper(command).getJSONArray("requestList").getJSONObject(0).get("empID"));
		assertEquals("FIRSTNAME LASTNAME", coorser.gateKeeper(command).getJSONArray("requestList").getJSONObject(0).get("empName"));

		
	}
	
	
	@Test
	public void getPendingTimes() {
		JSONObject command = new JSONObject();
		command.put("action", "getEmpAvail");
		command.put("empID", 30);
		command.put("empName", "FIRSTNAME LASTNAME");
		
		CoordinatorService coorser = new CoordinatorService();
		
		CoodinatorDao mockCoorDao = Mockito.mock(CoodinatorDao.class);
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		UserDao mockUserDao       = Mockito.mock(UserDao.class);
		UserBean mockUserBean     = Mockito.mock(UserBean.class);
		UserBean UB = new UserBean("FIRSTNAME", "LASTNAME", "PASSWORD", "USERNAME");
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
		
		Mockito.when(mockEmpDao.getPendingTimesCoor(30)).thenReturn(mockList);
		coorser.setEmpDao(mockEmpDao);
		System.out.println(coorser.gateKeeper(command));
		assertEquals("FIRSTNAME LASTNAME", coorser.gateKeeper(command).get("empName"));
		assertEquals("monday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(0).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(0).get("startTime"));
		assertEquals((float)10,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(0).get("endTime"));
		
		assertEquals("monday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(1).get("day"));
		assertEquals((float)12,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(1).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(1).get("endTime"));
		
		assertEquals("tuesday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(2).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(2).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(2).get("endTime"));
		
		assertEquals("wednesday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(3).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(3).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(3).get("endTime"));
		
		assertEquals("thursday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(4).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(4).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(4).get("endTime"));
		
		assertEquals("friday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(5).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(5).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(5).get("endTime"));
		
		assertEquals("saturday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(6).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(6).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(6).get("endTime"));
		
		assertEquals("sunday",coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(7).get("day"));
		assertEquals((float)9,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(7).get("startTime"));
		assertEquals((float)17,coorser.gateKeeper(command).getJSONArray("weekDetails").getJSONObject(7).get("endTime"));

	}
	
	@Test
	public void approveTest() {
		JSONObject command = new JSONObject();
		command.put("action", "requestAction");
		command.put("empID", 30);
		command.put("requestStatus", "Approved");
		
		CoordinatorService coorser = new CoordinatorService();
		
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		Mockito.when(mockEmpDao.approveNewAvail(30)).thenReturn(true);
		coorser.setEmpDao(mockEmpDao);
		assertEquals("success", coorser.gateKeeper(command).get("result"));

	}
	
	@Test
	public void approveFailureTest() {
		JSONObject command = new JSONObject();
		command.put("action", "requestAction");
		command.put("empID", 30);
		command.put("requestStatus", "Approved");
		
		CoordinatorService coorser = new CoordinatorService();
		
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		Mockito.when(mockEmpDao.approveNewAvail(30)).thenReturn(false);
		coorser.setEmpDao(mockEmpDao);
		assertEquals("failure", coorser.gateKeeper(command).get("result"));

	}
	
	@Test
	public void deniedTest() {
		JSONObject command = new JSONObject();
		command.put("action", "requestAction");
		command.put("empID", 30);
		command.put("requestStatus", "Denied");
		
		CoordinatorService coorser = new CoordinatorService();
		
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		Mockito.when(mockEmpDao.denyRequests(30)).thenReturn(true);
		coorser.setEmpDao(mockEmpDao);
		assertEquals("success", coorser.gateKeeper(command).get("result"));

	}
	
	@Test
	public void deniedFailureTest() {
		JSONObject command = new JSONObject();
		command.put("action", "requestAction");
		command.put("empID", 30);
		command.put("requestStatus", "Denied");
		
		CoordinatorService coorser = new CoordinatorService();
		
		EmployeeDao mockEmpDao    = Mockito.mock(EmployeeDao.class);
		Mockito.when(mockEmpDao.denyRequests(30)).thenReturn(false);
		coorser.setEmpDao(mockEmpDao);
		assertEquals("failure", coorser.gateKeeper(command).get("result"));

	}

}
