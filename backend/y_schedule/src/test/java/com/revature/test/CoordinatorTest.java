package com.revature.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
	public void test() {
		CoordinatorService coorser = new CoordinatorService();
		
		CoodinatorDao mockCoorDao = Mockito.mock(CoodinatorDao.class);
		EmployeeDao mockEmpDao = Mockito.mock(EmployeeDao.class);
		UserDao mockUserDao = Mockito.mock(UserDao.class);
		

		
		UserBean UB = new UserBean("FIRSTNAME", "LASTNAM", "PASSWORD", "USERNAME");
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
		Mockito.when(mockUserDao.getUserById(30)).thenReturn(UB);
		coorser.setTheDao(mockCoorDao);
		JSONObject command = new JSONObject();
		command.put("action", "getAllRequests");
		
		System.out.println(coorser.idsOfPeopleRequesting());
		
	}

}
