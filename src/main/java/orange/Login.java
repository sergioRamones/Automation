package orange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import utility.Base;

public class Login extends Base{
	
	
	By txt_userName = By.id("txtUsername");
	By txt_password = By.id("txtPassword");
	By btn_login = By.xpath("//input[@id='btnLogin']");
	By link_forgotPassword = By.linkText("Forgot your password?");
	By text_errorMessage = By.id("spanMessage");
	By link_welcomeMessage = By.xpath("//div[@id='branding']//*[@id='welcome']");

	public Login(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void loginApplication(String user, String password) {
		type(user, txt_userName);
		type(password,txt_password);
		click(btn_login);
//		String oldTab = getCurrentTabOrWindow();
//		openNewTab();
//		switchToDefaultTabWindow(oldTab);
		if(isDisplayed(text_errorMessage)) {
			Assert.fail("Is not possible to Login: "+getText(text_errorMessage));
		}
		verifyElementIsVisible(link_welcomeMessage);
		
			reporter("You successfully login");

	}//end loginApplication
	
	
	public void invalidLogin(String user, String password) {
		type(user, txt_userName);
		type(password,txt_password);
		click(btn_login);
//		String oldTab = getCurrentTabOrWindow();
//		openNewTab();
//		switchToDefaultTabWindow(oldTab);
		if(isDisplayed(text_errorMessage)) {
			Reporter.log("You can't Login : "+ getText(text_errorMessage), true);
			return;

		}else {
			Assert.fail("There are not error in login : "+ getText(text_errorMessage));
		}
	}
}//end class
