package com.revature.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getSecLvlByUsernameTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameTest.class);
	daos.User_TableBeansImpl utbi = new daos.User_TableBeansImpl();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSecLvlTrue() {
		assertEquals(Integer.valueOf(1), utbi.getSecLvlByUsername("Milisdowered"));
	}
	
	@Test
	public void getSecLvlTrueCo() {
		assertNotEquals(Integer.valueOf(2), utbi.getSecLvlByUsername("Swuzzy"));
	}
	
	@Test
	public void getSecLvlTrueMan() {
		assertNotEquals(Integer.valueOf(3), utbi.getSecLvlByUsername("Posuraid68"));
	}
	
	@Test
	public void getSecLvlFalse() {
		assertNotEquals(Integer.valueOf(4), utbi.getSecLvlByUsername("Posuraid68"));
	}
	
	@Test
	public void getSecLvlFalseNull() {
		assertNotEquals(Integer.valueOf(4), utbi.getSecLvlByUsername(null));
	}
	

}
