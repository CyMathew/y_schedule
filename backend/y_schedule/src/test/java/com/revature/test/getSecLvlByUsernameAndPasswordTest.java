package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import beans.UserBean;
import daos.UserDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getSecLvlByUsernameAndPasswordTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	UserDao utbi = new UserDao();
	UserBean bean = null;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSecLvlByUserPassTrueEmp() {
		assertEquals((Integer)13,utbi.getSecLvlByUsernameAndPassword("manager", "manager").getUser_id());
		assertEquals((Integer)2367,utbi.getSecLvlByUsernameAndPassword("manager", "manager").getStore_Id());
		assertEquals((Integer)2,utbi.getSecLvlByUsernameAndPassword("manager", "manager").getSec_lvl());
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmp() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("TEST", "TEST"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("TEasdklfjklST", "CXZklcvcxzvc"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("346465", "asldfasdf"));
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmpUuser() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("Pinkston", "TEST"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("asaddasdasda", "TEST"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("asdasdasdasda", "TEST"));
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmpPass() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("ETET", "PASSWORD"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("tester", "PASSWORD"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("testetx", "PASSWORD"));
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("asdlasdlvl", "PASSWORD"));
	}
	
}
