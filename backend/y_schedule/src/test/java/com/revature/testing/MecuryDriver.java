package com.revature.testing;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MecuryDriver {
	public static WebDriver driver;
	public final String url = "http://localhost:4200";
	WebDriverWait wait;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// We can configure our driver's implicit wait as soon as it is
		// instantiated.
		// For any element, wait 3 seconds before determining a fail.
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		/*
		 * Implicit waits are applied by default in this case. But should we
		 * decided that there is a specific point in time I want to wait, we
		 * have to use explicit waits. These are one time uses and must be
		 * executed at a specific point.
		 */
		wait = new WebDriverWait(driver, 7);

		driver.get(url);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}

	@Test
	public void logInToMercury() {
		
			driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/form/div[1]/input")).sendKeys("manager");
			driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/form/div[2]/input")).sendKeys("manager");
			driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/form/button")).click();
			assertNotNull(driver.findElement(By.tagName("app-user-sidebar")));
			driver.findElement(By.xpath("/html/body/app-root/app-navbar/nav/button/span")).click();
			driver.findElement(By.linkText("Logout")).click();


		}
		
	
}
