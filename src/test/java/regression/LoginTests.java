package regression;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import orange.Login;

public class LoginTests {
	private WebDriver driver;
	Login login;
	@BeforeTest
	public void setup() {
		login = new Login(driver);
		driver = login.chromeDriverConnection();
		login.openUrl("https://opensource-demo.orangehrmlive.com/");
	}
	
	
	@Test
	public void validLogin() {
		login.loginApplication("Admin", "admin123");
	}
	
	@Test
	public void invalidLogin() {
		login.invalidLogin("Admi", "admin123");
	}
	
	@AfterTest
	public void tearDown() {
			driver.quit();
			Reporter.log("Driver was quited ", true);

	}
	

}
