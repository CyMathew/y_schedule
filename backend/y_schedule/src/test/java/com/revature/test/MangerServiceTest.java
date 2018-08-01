package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import beans.UserBean;
import daos.ManagerDao;
import daos.UserDao;
import services.ManagerService;

public class MangerServiceTest {

	JSONObject tempo = new JSONObject();
	UserBean bean = new UserBean();
	ManagerDao md = new ManagerDao();
	UserDao ud = new UserDao();

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void selectEmpInfoPass() {
		JSONAssert.assertEquals(
				"{\"userfname\":\"Richard\",\"seclvl\":\"employee\",\"storeId\":2367,\"userid\":10,\"userlname\":\"Parsons\"}",
				ManagerService.selectEmpInfo(10), JSONCompareMode.LENIENT);
		JSONAssert.assertEquals(
				"{\"userfname\":\"manager\",\"seclvl\":\"manager\",\"storeId\":2367,\"userid\":13,\"userlname\":\"manager\"}",
				ManagerService.selectEmpInfo(13), JSONCompareMode.LENIENT);
	}

	@Test
	public void selectEmpInforFail() {
		JSONAssert.assertNotEquals(
				"{\"userfname\":\"Richard\",\"seclvl\":\"employee\",\"storeId\":2367,\"userid\":10,\"userlname\":\"Parsons\"}",
				ManagerService.selectEmpInfo(0), JSONCompareMode.LENIENT);
		JSONAssert.assertNotEquals(
				"{\"userfname\":\"manager\",\"seclvl\":\"manager\",\"storeId\":2367,\"userid\":13,\"userlname\":\"manager\"}",
				ManagerService.selectEmpInfo(10), JSONCompareMode.LENIENT);
	}

	@Test
	public void selectScheduledTimesByWeek() {
		JSONAssert.assertEquals(ManagerService.selectScheduledTimesByWeek(2367, 4), ManagerService.selectScheduledTimesByWeek(2367, 4),
				JSONCompareMode.LENIENT);
		JSONAssert.assertNotEquals(ManagerService.selectScheduledTimesByWeek(2367, 7), ManagerService.selectScheduledTimesByWeek(2367, 4),
				JSONCompareMode.LENIENT);
		JSONAssert.assertEquals(ManagerService.selectScheduledTimesByWeek(121, 7), ManagerService.selectScheduledTimesByWeek(2123367, 7),
				JSONCompareMode.LENIENT);
		JSONAssert.assertNotEquals(ManagerService.selectScheduledTimesByWeek(2367, 4), ManagerService.selectScheduledTimesByWeek(2123367, 3),
				JSONCompareMode.LENIENT);
	}

}
