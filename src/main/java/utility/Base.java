package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;




public class Base {
	
	private WebDriver driver;
	private String path = System.getProperty("user.dir");
	private String osName =  System.getProperty("os.name");
	private String chromeDriver;
	private String geckoDriver;
	private String msedgeDriver;
	
	public Base(WebDriver driver) {
		this.driver=driver;
				
	}
	
	/**
	 * @Description get Operating system name 
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return String
	 * @exception 
	 * **/
	public String getOSname() {
		if (osName.contains("Mac")) {
			osName = "Mac";
		} else if (osName.contains("Windows")) {
			osName = "Windows";
		} else if (osName.contains("Linux")) {
			osName = "Linux";
		}
		return osName;
	}
	
	
	/**
	 * @Description set the path for driver according to Operating system
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @exception 
	 * **/
	public void setDriverPaths() {
		osName = getOSname();		
		switch (osName) {
		case "Mac":
		case "Linux":
			chromeDriver = path+"/chromedriver/chromedriver";
			geckoDriver = path+"/geckodriver/geckodriver";
			msedgeDriver = path+"/msedgedriver/msedgedriver";
			break;
		case "Windows":
		case "Windows 10":
			chromeDriver = path+"\\chromedriver\\chromedriver.exe";
			geckoDriver = path+"\\geckodriver\\geckodriver.exe";
			msedgeDriver = path+"\\msedgedriver\\msedgedriver.exe";
			break;

		}
	}
	
	/**
	 * @Description start google Chrome session
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return WebDriver
	 * @exception
	 **/
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		ChromeOptions option = new ChromeOptions();
//		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
		return driver;
	}

	/**
	 * @Description start Firefox session
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return WebDriver
	 * @exception
	 **/
	public WebDriver firefoxDriverConnection() {
		FirefoxOptions option = new FirefoxOptions();
		System.setProperty("webdriver.gecko.driver", geckoDriver);
		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		driver = new FirefoxDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
		return driver;
	}

	/**
	 * @Description start Edge session
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return WebDriver
	 * @exception
	 **/
	public WebDriver edgeDriverConnection() {
		EdgeOptions option = new EdgeOptions();
		System.setProperty("webdriver.edge.driver", msedgeDriver);
		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		driver = new EdgeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
		return driver;
	}
	
	/**
	 * @Description Return WebDriver 
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return WebDriver
	 * @exception 
	 * **/
	public WebDriver getDriver() {
		return driver;
	}
	
	
	
	/**
	 * @Description Find Element by locator
	 * @author sramones
	 * @Date 17/02/2022
	 * @param By
	 * @return WebElement
	 * @exception 
	 * **/
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	/**
	 * @Description Find Elements by locator
	 * @author sramones
	 * @Date 17/02/2022
	 * @param By
	 * @return List<WebElement> 
	 * @exception 
	 * **/
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
	/**
	 * @Description Find Elements by locator and getText
	 * @author sramones
	 * @Date 17/02/2022
	 * @param By
	 * @return String
	 * @exception 
	 * **/
	public String getText(By locator) {
		return findElement(locator).getText();
	}
	
	
	/**
	 * @Description getText from WebElement
	 * @author sramones
	 * @Date 17/02/2022
	 * @param WebElement
	 * @return String
	 * @exception 
	 * **/
	public String getText(WebElement element) {
		return element.getText();
	}
	
	/**
	 * @Description Type text by locator
	 * @author sramones
	 * @Date 17/02/2022
	 * @param locator
	 * @return N/A
	 * @exception 
	 * **/
	public void type(String inputText, By locator) {
		findElement(locator).sendKeys(inputText);
		reporter("Text inserted is", inputText);
		reporterLocator("was inserted", findElement(locator));
	}
	
	/**
	 * @Description click by locator
	 * @author sramones
	 * @Date 17/02/2022
	 * @param locator
	 * @return N/A
	 * @exception 
	 * **/
	public void click(By locator) {
		findElement(locator).click();
		reporterLocator("was clicked", driver.findElement(locator));
	}
	
	/**
	 * @Description verify if element exist by locator
	 * @author sramones
	 * @Date 17/02/2022
	 * @param locator
	 * @return boolean
	 * @exception 
	 * **/
	public boolean isDisplayed(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			reporterLocator("is Displayed",element);
			return element.isDisplayed();
		}catch(NoSuchElementException e) {
			reporter("Web element is not displayed");
			return false;
		}
	}
	
	public void openUrl(String url) {
		driver.get(url);
		reporter("The URL was opened: " + url);
	}
	
	
	/**
	 * @Description get Original tab / window
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return String
	 * @exception 
	 * **/
	public String getCurrentTabOrWindow(){
		return driver.getWindowHandle();
	}
	
	
	/**
	 * @Description Switch to tab / window
	 * @author sramones
	 * @Date 17/02/2022
	 * @param String
	 * @return N/A
	 * @exception 
	 * **/
	public void switchToDefaultTabWindow(String originalWindow ) {
		
		for (String windowHandle : driver.getWindowHandles()) {
			if (originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(originalWindow);
				reporter("switch to", originalWindow);
				return;
			}
		}
		Assert.fail("Window or Tab do not exist");
	}
	
	/**
	 * @Description Open new tab
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return N/A
	 * @exception 
	 * **/
	public void openNewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	/**
	 * @Description Open new tab
	 * @author sramones
	 * @Date 17/02/2022
	 * @param N/A
	 * @return N/A
	 * @exception 
	 * **/
	public void openNewWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	/**
	 * @Description switch to a frame 
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By
	 * @return N/A
	 * @throws InterruptedException 
	 */
	public void switchToFrame(By locator) {
		driver.switchTo().frame(findElement(locator));
	}
	
	/**
	 * @Description switch to a Default frame 
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By
	 * @return N/A
	 * @throws InterruptedException 
	 */
	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * @Description verify element is visible wait till timeout
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By
	 * @return N/A
	 * @throws InterruptedException 
	 */
	public void verifyElementIsVisible(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(findElement(locator)));
		reporterLocator("is visible", findElement(locator));
		highlighElement(locator);
	}
	
	
	/**
	 * @Description highligh element
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By
	 * @return N/A
	 * @throws InterruptedException 
	 */
	public void highlighElement(By locator) {
			WebElement element = findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border:  solid red');",element);
			js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white');", element);
			reporterLocator("was highlighted", element);
	}
	
	
	
	/**
	 * @Description report in the log
	 * @Author Sergio Ramones
	 * @Date 17/02/2022
	 * @Parameter String, String
	 * @return N/A
	 **/
	public void reporter(String message, String value) {
		Reporter.log(message + "<b> [ " + value + " ] </b>", true);

	}
	
	/**
	 * @Description report in the log
	 * @Author Sergio Ramones
	 * @Date 17/02/2022
	 * @Parameter String, String
	 * @return N/A
	 **/
	public void reporter(String message) {
		Reporter.log( "<b> [ " + message + " ] </b>", true);

	}

	/**
	 * @Description report in the log the locator used
	 * @Author Sergio Ramones
	 * @Date 17/02/2022
	 * @Parameter WebElement
	 * @return N/A
	 **/
	public void reporterLocator(String text, WebElement element) {
		if (element.toString().contains("By.") == true) {
			
			Reporter.log("WebElement "+text+ "by locatior ---> <b> " + element.toString().split("By.")[1] + "</b> ",
					true);
		} else if (element.toString().contains("->") == true) {
			Reporter.log("WebEement "+text+ " by locatior ---> <b> " + element.toString().split("->")[1] + "</b> ",
					true);
		}
	}

	/**
	 * @Description select drop down by index
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By, int
	 * @return N/A
	 * @throws StaleElementReferenceException 
	 */
	public void selectDropDownByIndex(By locator, int index) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);

		int attempts = 0;

		while (attempts < 2) {
			try {

				action.selectByIndex(index);
				reporter("Element was selected by index", String.valueOf(index));
				break;

			} catch (StaleElementReferenceException e) {
				Assert.fail("Cannot select element: "+ dropdown.toString());
			}
			 catch (NoSuchElementException e) {
					Assert.fail("Cannot select element: "+ dropdown.toString());
				}
			attempts++;
		}

	}
	
	/**
	 * @Description select drop down by value
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By, String
	 * @return N/A
	 * @throws StaleElementReferenceException 
	 */
	public void selectDropDownByValue(By locator, String value) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);

		int attempts = 0;

		while (attempts < 2) {
			try {

				action.selectByValue(value);
				reporter("Element was selected by Value", value);
				break;

			} catch (StaleElementReferenceException e) {
				Assert.fail("Cannot select element: "+ dropdown.toString());
			}
			 catch (NoSuchElementException e) {
					Assert.fail("Cannot select element: "+ dropdown.toString());
				}
			attempts++;
		}

	}
	
	/**
	 * @Description select drop down by visible text
	 * @author sramones
	 * @Date 17/02/2022
	 * @Parameter By, String
	 * @return N/A
	 * @throws StaleElementReferenceException 
	 */
	public void selectDropDownByVisibleText(By locator, String text) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);

		int attempts = 0;

		while (attempts < 2) {
			try {

				action.selectByVisibleText(text);
				reporter("Element was selected by Value", text);
				break;

			} catch (StaleElementReferenceException e) {
				Assert.fail("Cannot select element: "+ dropdown.toString());
			}
			 catch (NoSuchElementException e) {
					Assert.fail("Cannot select element: "+ dropdown.toString());
				}
			attempts++;
		}

	}
	
	
}//end class
