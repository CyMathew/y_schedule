package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChckPasswordTest {
	final static Logger logger = Logger.getLogger(ChckPasswordTest.class);
	daos.User_TableBeansImpl utbi = new daos.User_TableBeansImpl();

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void ChckPasswordTrue() {
		assertEquals("Success", utbi.checkPassword("Test", "Test"));
		logger.info("test");
	}

	@Test
	public void ChckPasswordFalseUsername() {
		assertEquals("Failure", utbi.checkPassword("zack", "Test"));
	}

	@Test
	public void ChckPasswordFalsePassword() {
		assertEquals("Failure", utbi.checkPassword("Test", "zack"));
	}

	@Test
	public void ChckPasswordFalse() {
		assertEquals("Failure", utbi.checkPassword("zack", "zack"));
	}

}
