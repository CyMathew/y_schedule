package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.UserBean;
import daos.UserDao;
import services.ManagerService;
import services.UserService;

public class MangerServiceTest {
	
	JSONObject tempo = new JSONObject();
	ManagerService ms = new ManagerService();
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(ms.selectEmpInfo(10));
	}

}
