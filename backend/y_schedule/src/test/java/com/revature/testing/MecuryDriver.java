package com.revature.testing;

import static org.testng.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
			
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/div[1]/input")).sendKeys("manager");
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/div[2]/input")).sendKeys("manager");
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/button")).click();
			assertNotNull(driver.findElement(By.tagName("app-user-sidebar")));
			
			driver.findElement(By.linkText("Timesheet")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[1]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[1]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[1]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
//			driver.findElement(By.linkText("Register Employee")).click();
//			driver.findElement(By.linkText("timesheet")).click();
//			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
//			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/div/div[3]/a")).click();
//			driver.findElement(By.xpath("/html/body/app-root/app-edit-time-sheet/div/app-edit-ts-week/table/thead/tr/th[3]")).click();
//			
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[1]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[2]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[3]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[4]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[5]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[6]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[7]")).click();
//			driver.findElement(By.xpath("//*[@id=\"employeeSelect\"]/label[8]")).click();
			
			driver.findElement(By.linkText("Register Employee")).click();
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[1]/input")).sendKeys("FIRST NAME");
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/input")).sendKeys("LAST NAME");
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("USERNAME");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("PASSWORD");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("dogfind");
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/input")).click();
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/button")).click();
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/input")).click();
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[1]/input")).sendKeys("FIRST NAME");
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/button")).click();
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[1]/input")).sendKeys("FIRST NAME");
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/input")).sendKeys("LAST NAME");
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/button")).click();
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[1]/input")).sendKeys("FIRST NAME");
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/input")).sendKeys("LAST NAME");
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("USERNAME");
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/button")).click();
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[1]/input")).sendKeys("FIRST NAME");
			driver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/input")).sendKeys("LAST NAME");
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("USERNAME");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("PASSWORD");
			driver.findElement(By.xpath("//*[@id=\"button-group\"]/button")).click();
			
			driver.findElement(By.linkText("Logout")).click();
			
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/div[1]/input")).sendKeys("gai");
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/div[2]/input")).sendKeys("g");
			driver.findElement(By.xpath("//*[@id=\"loginDiv\"]/form/button")).click();
			
			driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[1]/a")).click();
			
			Select daySelect = new Select(driver.findElement(By.xpath("//*[@id=\"daySelect\"]")));
			daySelect.selectByVisibleText("Sunday");
			daySelect.selectByVisibleText("Monday");
			daySelect.selectByVisibleText("Tuesday");
			daySelect.selectByVisibleText("Wednesday");
			daySelect.selectByVisibleText("Thursday");
			daySelect.selectByVisibleText("Friday");
			daySelect.selectByVisibleText("Saturday");
			
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			
			daySelect.selectByVisibleText("Sunday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Monday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Tuesday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Wednesday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Thursday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Friday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			daySelect.selectByVisibleText("Saturday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();

			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Sunday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Monday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Tuesday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Wednesday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Thursday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
			
			daySelect.selectByVisibleText("Friday");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/a")).click();
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("9");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys("AM");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("4");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("10");
			driver.findElement(By.xpath("//*[@id=\"endTime\"]")).sendKeys("PM");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();

			driver.findElement(By.linkText("Logout")).click();


		}
	
		
	
}
